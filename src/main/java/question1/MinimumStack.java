package question1;

/**
 * Question:
 * Below is an interface for a standard stack data structure,
 * except it can provide the minimum value in the entire stack.
 *
 * Please implement this interface. Take note of the time/space
 * complexities of the implementation.
 */
public interface MinimumStack<T extends Comparable<T>> {

  /**
   * Returns the top value of the stack.
   * If the stack is empty it should throw {@link java.util.EmptyStackException}.
   *
   * | |       | |
   * |2| pop() | |
   * |1| ----> |1|
   * |3|       |3|
   *
   * @return the item at the top of the stack
   */
  T pop();

  /**
   * Places an item on the top of the stack.
   * @param item
   */
  void push(T item);

  /**
   * Returns the minimum value within the stack. If the stack
   * is empty it should throw {@link java.util.EmptyStackException}.
   *
   * | |
   * |2| minimum()
   * |1| --------> 1
   * |3|
   * @return the minimum item
   */
  T minimum();
}
