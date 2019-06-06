### Elements
* 一个用来处理Element的工具类

```
public interface Elements {

    //给出完全限定名称的包返回
    PackageElement getPackageElement(CharSequence var1);

    //给出其规范名称的返回类型元素
    TypeElement getTypeElement(CharSequence var1);

    //返回注释元素的值，包括默认值
    Map<? extends ExecutableElement, ? extends AnnotationValue> getElementValuesWithDefaults(AnnotationMirror var1);

    //返回元素的文档（“Javadoc”）注释的文本
    String getDocComment(Element var1);

    //rue如果元素已弃用，false则相反
    boolean isDeprecated(Element var1);

    // 返回类型元素的二进制名称
    Name getBinaryName(TypeElement var1);

    //返回元素的包
    PackageElement getPackageOf(Element var1);

    //返回类型元素的所有成员，无论是继承还是直接声明
    List<? extends Element> getAllMembers(TypeElement var1);

    //返回元素的所有注释，无论是继承还是直接存在
    List<? extends AnnotationMirror> getAllAnnotationMirrors(Element var1);

    //测试一种类型，方法或字段是否隐藏另一种类型，方法或字段
    boolean hides(Element var1, Element var2);

    //测试一个方法（作为给定类型的成员）是否会覆盖另一个方法
    boolean overrides(ExecutableElement var1, ExecutableElement var2, TypeElement var3);

    //返回表示原始值或字符串的常量表达式的文本
    String getConstantExpression(Object var1);

    //以指定的顺序将元素的表示形式打印到给定的writer
    void printElements(Writer var1, Element... var2);

    //返回与参数具有相同字符序列的名称
    Name getName(CharSequence var1);

    //返回true如果类型元件是功能性的接口，false相反
    boolean isFunctionalInterface(TypeElement var1);
}   
```
    
    

### 举例
* Element代表程序中的元素，比如说 包、类/接口、属性变量、方法/方法形参、泛型参数
* 每一个元素代表一个静态的，语言级别的结构

```
public class Foo { // TypeElement

    private int a; // VariableElement
    private Foo other; // VariableElement

    public Foo() {} // ExecuteableElement

    public void setA( // ExecuteableElement
            int newA // TypeElement
    ) {
        // TODO
    }
}
```
