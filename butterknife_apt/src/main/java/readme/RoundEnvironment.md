### RoundEnvironment

```java
public interface RoundEnvironment {
    
    boolean processingOver();

    boolean errorRaised();

    Set<? extends Element> getRootElements();

    Set<? extends Element> getElementsAnnotatedWith(TypeElement var1);

    Set<? extends Element> getElementsAnnotatedWith(Class<? extends Annotation> var1);
}
```