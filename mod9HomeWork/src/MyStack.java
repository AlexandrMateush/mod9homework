import java.util.Arrays;

public class MyStack<E> {
    private static Object[] array = new Object[0];
    private int indexValue = 0;


    private void add(E value){
        array = Arrays.copyOf(array, array.length+1);
        array[indexValue] = value;
        indexValue++;
    }
    private void clear(){
        array = new Object[array.length];
    }
    private void remove(int index){
        Object[] array1 = new Object[array.length - 1];
        for (int i = 0 ;i<array1.length;i++){
            for (int j = 1; j<array1.length;i++){
                array1[i]=array[i];
                if (i == index){
                    array1[i] = array[j];
                }

            }

        }
        array1 = array;

    }
    private int size(){
        return array.length;
    }
    private E peek(){
        return (E) array[0];
    }
    private E pop(){
        Object value = array[0];
        Object[] array1 = new Object[array.length - 1];
        for (int i = 0 ;i<array1.length;i++){
            array1[i] = array[i];
        }
        array1 = array;
        return (E) value;
    }


}
