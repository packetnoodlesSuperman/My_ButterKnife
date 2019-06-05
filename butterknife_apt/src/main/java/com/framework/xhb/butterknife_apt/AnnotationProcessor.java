package com.framework.xhb.butterknife_apt;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * @author xhb
 * @desc annotation deal tool
 * @desc 该module创建类型为Java工程
 *        重要的事情说三遍：
 *                  需要建立Java库工程
 *                  需要建立Java库工程
 *                  需要建立Java库工程
 */
@AutoService(Processor.class)//自动生成 javax.annotation.processing.IProcessor 文件
@SupportedSourceVersion(SourceVersion.RELEASE_8)//java版本支持
//标记注解处理器支持的注解类型，就是我们刚才定义的接口Test，可以写入多个annotation type
@SupportedAnnotationTypes({"com.framework.xhb.butterknife_apt.Test"})
public class AnnotationProcessor extends AbstractProcessor {

    public Messager mMessager;
    public Elements mElements;
    public Filer mFiler;

    /**
     * process 是实际处理注解的地方
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mFiler = processingEnv.getFiler();//文件相关的辅助类
        mElements = processingEnv.getElementUtils();//元素相关的辅助类
        mMessager = processingEnv.getMessager();//日志相关的辅助类

        MethodSpec methodMain = MethodSpec.methodBuilder("main")//创建main方法
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)//定义修饰符为 public static
                //在生成代码前增加注释
                .addJavadoc(new String("@  此类由apt自动生成".getBytes(), Charset.forName("utf-8")))
                .returns(void.class)//定义返回类型
                .addParameter(String[].class, "args")//定义方法参数
                .addStatement("$T.out.println($S)", System.class, "helloWorld")//定义method body
                .build();
        //创建HelloWorld class
        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)//定义修饰符为 public final
                .addMethod(methodMain)//添加方法
                .addJavadoc(new String("@  此方法由apt自动生成".getBytes(), Charset.forName("utf-8")))//定义方法参数
                .build();
        //生成source code
        JavaFile javaFile = JavaFile.builder("com.bob.apt", helloWorld).build();
        try {
            /**
             * app/module/build/generated/source/apt 生成one source code
             */
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * init方法是初始化的地方可以获取到很多有用的工具Class
     * @param processingEnvironment
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }

    /**
     * @return getSupportedAnnotationTypes 这个方法指定deal annotation,deal的注解的全名放到集合中return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet();
        types.add(Test.class.getCanonicalName());
        return super.getSupportedAnnotationTypes();
    }


    /**
     * getSupportedSourceVersion 这个方法用来指定支持的java版本
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return super.getSupportedSourceVersion();
    }
}
