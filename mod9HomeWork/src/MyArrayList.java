import java.util.Arrays;

class MyArrayList <E>{
    private static Object[] array = new Object[0];
    private int indexValue = 0;

    private void add(E value){
        array = Arrays.copyOf(array, array.length+1);
        array[indexValue] = value;
        indexValue++;
    }
    private void remove(int index){
        if(index < 0 || index >array.length ){
            throw new IndexOutOfBoundsException("index is out of range" + index);
        }

        Object[] arrays = Arrays.copyOf(array,array.length-1);
        for (int i = 0;i < array.length;i++ ){
            for (int j = 1;j < array.length;j++ )
             if(index == i){
                arrays[i] = array[j];
             }
        }
            array = arrays;
    }
    private void clear(){
        array = new Object[array.length];
    }
    private int size(){
        return array.length;
    }
    private E get(int index){
        if(index < 0 || index >array.length ){
            throw new IndexOutOfBoundsException("index is out of range" + index);
        }


        return (E) array[index];
    }







//    public static void main(String[] args) {
//        MyArrayList myArrayList = new MyArrayList();
//        myArrayList.add("Pasha");
//        myArrayList.add(25);
//        myArrayList.add(58);
//        myArrayList.add(65);
//        myArrayList.add(85);
//        System.out.println(Arrays.toString(array));
//        myArrayList.remove(2);
//        System.out.println(Arrays.toString(array));
//        myArrayList.clear();
//        System.out.println("Arrays.toString = " + Arrays.toString(array));
//        System.out.println(myArrayList.get(2));
//
//
//    }

}