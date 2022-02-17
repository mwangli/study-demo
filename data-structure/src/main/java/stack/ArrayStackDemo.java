package stack;

import java.util.Arrays;

/**
 * @author mwangli
 * @date 2022/2/15 9:55
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(3);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(3);
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        arrayStack.print();
        String abc = new String("abc");
//        int i = abc.charAt(0)
    }

    static class ArrayStack {
        private final int maxSize;
        private int top;
        private final int[] values;

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            this.top = -1;
            this.values = new int[maxSize];
        }

        public boolean isFull() {
            return top == maxSize - 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public void push(int value) {
            if (isFull()) {
                System.out.println("is full");
            } else {
                values[++top] = value;
            }
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("is empty");
            }
            return values[top--];
        }

        public void print() {
            for (int value : values) {
                System.out.println(value);
            }
        }
    }


}
