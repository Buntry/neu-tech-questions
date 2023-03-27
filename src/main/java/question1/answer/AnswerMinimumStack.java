package question1.answer;

import java.util.Stack;
import lombok.NoArgsConstructor;
import question1.MinimumStack;

@NoArgsConstructor
public class AnswerMinimumStack<T extends Comparable<T>> implements MinimumStack<T> {

  Stack<T> itemStack = new Stack<>();
  Stack<T> minimumStack = new Stack<>();

  /**
   * Returns the top value of the stack.
   * If the stack is empty it should throw {@link java.util.NoSuchElementException}.
   *
   * | |       | |
   * |2| pop() | |
   * |1| ----> |1|
   * |3|       |3|
   *
   * @return the item at the top of the stack
   */
  @Override
  public T pop() {
    minimumStack.pop();
    return itemStack.pop();
  }

  /**
   * Places an item on the top of the stack.
   * @param item
   */
  @Override
  public void push(T item) {
    if (itemStack.size() == 0) {
      minimumStack.push(item);
    } else {
      T currentMinimumItem = minimumStack.peek();
      if (item.compareTo(currentMinimumItem) < 0) {
        minimumStack.push(item);
      } else {
        minimumStack.push(currentMinimumItem);
      }
    }
    itemStack.push(item);
  }

  /**
   * Returns the minimum value within the stack. If the stack
   * is empty it should throw {@link java.util.NoSuchElementException}.
   *
   * | |
   * |2| minimum()
   * |1| --------> 1
   * |3|
   * @return the minimum item
   */
  @Override
  public T minimum() {
    return minimumStack.peek();
  }
}
