package app.linkedStack;

import java.util.*;

/**
 * @author Oleksandr Haleta
 */
public class StackLinked<T> implements Stack<T> {
    private List<T> stackList;

    public StackLinked() {
        stackList = new LinkedList<>();
    }

    public StackLinked(Collection<? extends T> c) {
        this();
        if (c == null) throw new NullPointerException();
        c.stream().forEach(this::push);
    }

    @Override
    public T peek() {
        if (empty()) throw new IllegalStateException("The Stack is empty!");
        return stackList.get(stackList.size() - 1);
    }

    @Override
    public T push(T item) {
        stackList.add(item);
        return item;
    }

    @Override
    public T pop() {
        if (empty()) throw new IllegalStateException("The Stack is already empty!");
        return stackList.remove(stackList.size() - 1);
    }

    @Override
    public int search(T t) {
        if (t == null) throw new NullPointerException("Enter the correct value!");
        int index = stackList.lastIndexOf(t);
        if (index >= 0) return stackList.size() - index;
        return -1;
    }

    @Override
    public boolean empty() {
        return stackList.isEmpty();
    }

    @Override
    public int size() {
        return stackList.size();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int counter = stackList.size() - 1;

            @Override
            public boolean hasNext() {
                return counter >= 0;
            }

            @Override
            public T next() {
                return stackList.get(counter--);
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StackLinked)) return false;
        StackLinked<?> that = (StackLinked<?>) o;
        return Objects.equals(stackList, that.stackList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stackList);
    }

    @Override
    public String toString() {
        return "StackLinked{" +
                stackList +
                '}';
    }
}
