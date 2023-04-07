
public interface MyParameterizedType extends MyType {
    MyType[] getActualTypeArguments();
    MyType getRawType();
    MyType getOwnerType();
}
