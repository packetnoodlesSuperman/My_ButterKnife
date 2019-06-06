package com.framework.xhb.butterknife_apt;

import com.framework.xhb.butterknife_apt.annotation.BindArray;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic.Kind;

public class MyButterKnifeProcessor extends AbstractProcessor {

    private int sdk = 1;

    private Elements elementUtils;
    private Types typeUtils;
    private Filer filer;

    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        // Map<String, String> getOptions() 返回指定的参数选项 拿到的是传递给注释处理工具的Map选项
        // 怎样获取呢 下面就是例子
        // defaultConfig {
        //     javaCompileOptions {
        //         annotationProcessorOptions {
        //             arguments = [ eventBusIndex : 'org.greenrobot.eventbusperf.MyEventBusIndex' ]
        //         }
        //     }
        // }
        //
        // public synchronized void init(ProcessingEnvironment processingEnv) {
        //     Map<String, String> options = processingEnv.getOptions();
        //     if (MapUtils.isNotEmpty(options)) {
        //         moduleName = options.get('eventBusIndex');
        //     }
        // }
        String sdk = (String)env.getOptions().get("butterknife.minSdk");
        if (sdk != null) {
            try {
                this.sdk = Integer.parseInt(sdk);
            } catch (NumberFormatException var5) {
                env.getMessager().printMessage(Kind.WARNING, "Unable to parse supplied minSdk option '" + sdk + "'. Falling back to API 1 support.");
            }
        }

        this.elementUtils = env.getElementUtils();
        this.typeUtils = env.getTypeUtils();
        this.filer = env.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        Map<TypeElement, BindingSet> bindingMap = this.findAndParseTargets(env);

        return false;
    }

    private Map<TypeElement, BindingSet> findAndParseTargets(RoundEnvironment env) {
        Map<TypeElement, BindingSet.Builder> builderMap = new LinkedHashMap();
        Set<TypeElement> erasedTargetNames = new LinkedHashSet();

        //
        Set<? extends Element> elementsAnnotatedWith = env.getElementsAnnotatedWith(BindArray.class);
        Iterator<? extends Element> iterator = elementsAnnotatedWith.iterator();

        Element element;
        while (iterator.hasNext()) {
            element = iterator.next();
        }

        return null;
    }

    private void parseResourceArray(Element element, Map<TypeElement, BindingSet.Builder> builderMap, Set<TypeElement> erasedTargetNames) {
        Element enclosingElement = element.getEnclosingElement();

    }

    private static void getArrayResourceMethodName(Element element) {
        // 返回此元素定义的类型
        TypeMirror typeMirror = element.asType();
        if ("android.content.res.TypedArray".equals(typeMirror.toString())) {
            return;
        }
    }

}
