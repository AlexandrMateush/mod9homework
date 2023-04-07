public interface MyType {
    default String getTypeName() {
        return toString();
    }
}
