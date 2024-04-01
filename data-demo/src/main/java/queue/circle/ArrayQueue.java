package queue.circle;

/**
 * @author mwangli
 * @date 2022/1/25 10:34
 */
public class ArrayQueue {

    private int size;
    private int front;
    private int rear;
    private int[] value;

    public ArrayQueue(int size) {
        this.size = size;
        value = new int[size];
        front = rear = 0;
    }

    public int add(int e){

        int index = rear % size;
        if (index==front){
            System.out.println("Full Queue");
            return 0;
        }
        value[index] =e;
        rear++;
        return 1;
    }
}


