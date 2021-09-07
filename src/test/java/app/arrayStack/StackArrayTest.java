package app.arrayStack;

import app.linkedStack.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Oleksandr Haleta
 */
class StackArrayTest {

    Stack<String> stack;
    Stack<Integer> stackInt;

    @BeforeEach
    void setUp() {
        stack = new StackArray<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        stackInt = new StackArray<>();
        stackInt.push(1);
        stackInt.push(2);
        stackInt.push(3);
    }

    @Test
    public void shouldCreateStackFromArrayList() {
        List<String> list = new ArrayList<>();
        list.add("5");
        list.add("6");
        list.add("7");
        Stack<String> newStack = new StackArray<>(list);
        assertEquals(3, newStack.size());
    }

    @Test
    public void shouldReturnSizeString() {
        int expected = 3;
        int actual = stack.size();
        assertEquals(expected, actual);

        stack.pop();
        int expected2 = 2;
        int actual2 = stack.size();
        assertEquals(expected2, actual2);
    }

    @Test
    public void shouldPushString() {
        stack.push("4");
        int expected = 4;
        int actual = stack.size();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchString() {
        int expected = 3;
        int actual = stack.search("1");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowIllegalStateExceptionString() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            for (int i = 0; i < 5; i++) {
                stack.pop();
            }
        });
        assertEquals("The Stack is already empty!", exception.getMessage());
    }

    @Test
    public void shouldThrowNPEString() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> stack.search(null));
        assertEquals("Enter the correct value!", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalStateExceptionPeek() {
        Stack<String> newStack = new StackArray<>();
        IllegalStateException exception = assertThrows(IllegalStateException.class, newStack::peek);
        assertEquals("The Stack is empty!", exception.getMessage());
    }

    @Test
    public void shouldReturnSizeInteger() {
        int expected = 3;
        int actual = stackInt.size();
        assertEquals(expected, actual);

        stackInt.pop();
        int expected2 = 2;
        int actual2 = stackInt.size();
        assertEquals(expected2, actual2);
    }

    @Test
    public void shouldSearchInteger() {
        int expected = 3;
        int actual = stackInt.search(1);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowIllegalStateExceptionInteger() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            for (int i = 0; i < 5; i++) {
                stackInt.pop();
            }
        });
        assertEquals("The Stack is already empty!", exception.getMessage());
    }

    @Test
    public void shouldIncreaseVolumeStack() {
        IntStream.range(3, 11).forEach(i -> stackInt.push(i));
        assertEquals(11, stackInt.size());
    }

    @Test
    public void shouldThrowNPEInteger() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> stackInt.search(null));
        assertEquals("Enter the correct value!", exception.getMessage());
    }
}