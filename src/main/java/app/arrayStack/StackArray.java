package app.arrayStack;


import app.linkedStack.Stack;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author Oleksandr Haleta
 */
public class StackArray<T> implements Stack<T> {

    private Object[] stack;
    private int top;

    public StackArray() {
        stack = new Object[10];
    }

    public StackArray(int size) {
        stack = new Object[size];
        top = -1;
    }

    public StackArray(Collection<? extends T> c) {
        Object[] a = c.toArray();
        if ((top = a.length) != 0) {
            if (c.getClass() == ArrayList.class) {
                stack = a;
            } else {
                stack = Arrays.copyOf(a, top, Object[].class);
            }
        } else {
            stack = new Object[10];
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T peek() {
        if (empty()) throw new IllegalStateException("The Stack is empty!");
        return (T) stack[top];
    }

    @Override
    public T push(T t) {
        if (top == stack.length - 1) {
            increaseVolumeStack();
            push(t);
        } else {
            stack[++top] = t;
        }
        return t;
    }

    private void increaseVolumeStack() {
        Object[] newStack = new Object[(int) (stack.length * 1.5 + 1)];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T pop() {
        if (empty()) throw new IllegalStateException("The Stack is already empty!");
        return (T) stack[top--];
    }

    @Override
    public int search(T t) {
        if (t == null) throw new NullPointerException("Enter the correct value!");
        return IntStream.range(0, top).filter(i -> stack[i] == t)
                .map(i -> top - (--i)).findAny().orElse(-1);
    }

    @Override
    public boolean empty() {
        return top == 0;
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int counter = size() - 1;
            @Override
            public boolean hasNext() {
                return counter >= 0;
            }

            @Override
            public Object next() {
                return stack[counter--];
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StackArray)) return false;
        StackArray<?> that = (StackArray<?>) o;
        return top == that.top &&
                Arrays.equals(stack, that.stack);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(top);
        result = 31 * result + Arrays.hashCode(stack);
        return result;
    }

    @Override
    public String toString() {
        return "StackArray{" +
                "stack=" + Arrays.toString(stack) +
                '}';
    }
}
