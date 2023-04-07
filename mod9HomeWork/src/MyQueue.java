import java.util.Arrays;


public class MyQueue {

    private static Object[] array = new Object[0];
    private int indexValue = 0;


    private void add(Object value){
        array = Arrays.copyOf(array, array.length+1);
        array[indexValue] = value;
        indexValue++;
    }
    private void clear(){
        array = new Object[array.length];
    }
    private int size(){
        return array.length;
    }
    private Object peek(){
      return array[0];
    }
    private Object poll(){
        Object value = array[0];
        Object[] array1 = new Object[array.length - 1];
        for (int i = 0 ;i<array1.length;i++){
            array1[i] = array[i];
        }
        array1 = array;
        return value;
    }

}
