### Element

```
public interface Element extends AnnotatedConstruct {
    
    // 返回此元素定义的类型
    TypeMirror asType();

    ElementKind getKind();

    Set<Modifier> getModifiers();

    Name getSimpleName();

    Element getEnclosingElement();

    List<? extends Element> getEnclosedElements();

    boolean equals(Object var1);

    int hashCode();

    List<? extends AnnotationMirror> getAnnotationMirrors();

    <A extends Annotation> A getAnnotation(Class<A> var1);

    <R, P> R accept(ElementVisitor<R, P> var1, P var2);
}
```

#### AnnotatedConstruct
> 表示一个可以被注解的结构(元素),这个结构可以是元素和类型
```
public interface AnnotatedConstruct {

     /**
      *  返回直接声明在该结构上的注解.
      *  如果没有直接声明的注解,则返回空集合.    
     */
    List<? extends AnnotationMirror> getAnnotationMirrors();

    /**
      *  返回指定类型所对应的注解,如果不存在,则返回null.
      *  注解的返回值可能会包含指定类型的class的元素.这个class不能直接返回:
      *  定位和加载类（如要使用的类加载器）所需的信息不可用，并且该类可能根本不可加载。
      *  通过调用返回的注释上的相关方法尝试读取Class对象将导致MirroredTypeException，从中可以提取相应的TypeMirror。类似地，尝试读取Class[]-元素会导致MirroredTypesException
      *  注意：此方法与此相关接口中的其他方法不同。它对运行时反射信息（当前加载到VM中的注
      *  解类型的表示）进行操作，而不是对这些接口定义和使用的表示进行操作。因此，在调用由
      *  反射返回的注解对象上的方法时，对返回的注解对象上调用方法可以引发许多异常。此方法是
      *  调用方用于编写，已知的固定类型的注解类型上操作的。
     */
    <A extends Annotation> A getAnnotation(Class<A> var1);

    /**
     * 返回与该结构体关联的注解.
     * 如果没有的话,则返回长度为0的数组.
     * 注解的顺序是按照直接或者间接的声明的顺序,间接声明的注解就好像直接声明在C上一样.
     * 此方法与getAnnotation(Class)的区别在于，此方法检测其参数是否为可重复注解类型，
     * 如果是，则试图通过“looking through”来查找该类型的一个或多个容器注解。
     */
    <A extends Annotation> A[] getAnnotationsByType(Class<A> var1);
}
```

#### TypeMirror
> 代表一个注解.注解将值与注解类型的每个元素相关联
```
public interface TypeMirror extends AnnotatedConstruct {
    TypeKind getKind();

    boolean equals(Object var1);

    int hashCode();

    String toString();

    <R, P> R accept(TypeVisitor<R, P> var1, P var2);
}
```

#### ElementKind
> 关于元素类型的枚举
```
public enum ElementKind {
    PACKAGE,                    // 包
    
    /**********  声明类型 **********/
    ENUM,                       // 枚举
    CLASS,                      // 类类型
    ANNOTATION_TYPE,            // 注解类型 
    INTERFACE,                  // 接口 
    
    /**********  变量 **********/
    ENUM_CONSTANT,              // 一个枚举常量
    FIELD,                      // 字段
    PARAMETER,                  // 方法或者构造器中的参数
    LOCAL_VARIABLE,             // 局部变量
    EXCEPTION_PARAMETER,        // try-catch中的catch语句的异常类型
    
    /**********  可执行的类型 **********/
    METHOD,                     // 方法
    CONSTRUCTOR,                // 构造器
    STATIC_INIT,                // 静态初始块
    INSTANCE_INIT,              // 初始块
    TYPE_PARAMETER,             // 类型参数
    /**********  其他 **********/
    /**
     * An implementation-reserved element.  This is not the element
     * you are looking for.
     */
    OTHER,
    
    RESOURCE_VARIABLE;          // 资源变量

    private ElementKind() {
    }

    //如果当前是CLASS或者是枚举则返回true
    public boolean isClass() {
        return this == CLASS || this == ENUM;
    }

    //如果当前是INTERFACE或者ANNOTATION_TYPE则返回tru
    public boolean isInterface() {
        return this == INTERFACE || this == ANNOTATION_TYPE;
    }

    //如果当前是FIELD或者是ENUM_CONSTANT则返回true
    public boolean isField() {
        return this == FIELD || this == ENUM_CONSTANT;
    }
}
```