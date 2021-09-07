package app.linkedStack;

/**
 * @author Oleksandr Haleta
 */
public interface Stack<T> extends Iterable<T> {
    T peek();
    T push(T t);
    T pop();
    int search(T t);
    boolean empty();
    int size();
}
