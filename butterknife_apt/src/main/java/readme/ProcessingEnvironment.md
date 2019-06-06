### ProcessingEnvironment
* ProcessingEnvironment 提供了一些实用的工具类Elements, Types和Filer
```java
public interface ProcessingEnvironment {

    /**
    * 配置说明 请看 {init方法}
    * {@link com.framework.xhb.butterknife_apt.MyButterKnifeProcessor}
    */
    //返回传递给注释处理工具的特定于处理器的选项
    Map<String, String> getOptions();

    //返回用于报告错误，警告和其他通知的消息
    Messager getMessager();

    //返回用于创建新的源，类或辅助文件的文件管理器
    Filer getFiler();

    //返回一些用于操作元素的实用程序方法的实现
    Elements getElementUtils();

    //返回一些用于对类型进行操作的实用程序方法的实现
    Types getTypeUtils();

    //返回任何生成的源文件和类文件应符合的源版本
    SourceVersion getSourceVersion();

    //返回当前区域设置或者null没有区域设置生效
    Locale getLocale();
}
```