public class stack {
    private int[] elements = new int[10];
    private int top = -1;

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == 9;
    }

    public void push(int value) {
        if (!isFull()) {
            elements[++top] = value;
        }
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return elements[top--];
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return elements[top];
    }

    public int size() {
        return top + 1;
    }

    public void clear() {
        top = -1;
    }

    public int getElement(int index) {
        return elements[index];
    }
}