
class MyLinkedList<T>{
    Node<T> last;
    Node<T> first;
    private int size;
    private int index =-1;
    protected transient int modCount = 0;
    private int expectedModCount = modCount;


    public void add(T obj){
        if(first== null){
            first = new Node<>(null,null,obj);
        }else  if (last==null){
            last = new Node<>(first,null,obj);
            first.setNextElement(last);
        }else {
            Node<T>tNode =new Node<>(last,null,obj);
            last.setNextElement(tNode);
            last = tNode;
            size++;
            index++;

        }
    }
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    T unlink(Node<T> x) {

        final T element = x.currentElement;
        final Node<T> next = x.nextElement;
        final Node<T> prev = x.previousElement;

        if (prev == null) {
            first = next;
        } else {
            prev.nextElement = next;
            x.previousElement = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.previousElement = prev;
            x.nextElement = null;
        }
        x.currentElement = null;
        size--;
        modCount++;
        return element;
    }
    Node<T> node(int index) {

        if (index < (size >> 1)) {
            Node<T> x = first;
            for (int i = 0; i < index; i++)
                x = x.nextElement;
            return x;
        } else {
            Node<T> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.previousElement;
            return x;
        }
    }

    public T remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }
    private void clear(){
        for (Node<T> x = first; x != null; ) {
            Node<T> next = x.nextElement;
            x.currentElement = null;
            x.nextElement = null;
            x.previousElement = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;

    }
    private int size(){
        return size;
    }
    public T get(int index) {
        checkElementIndex(index);
        return node(index).currentElement;
    }


}
