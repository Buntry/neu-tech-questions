package question1;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;

@NoArgsConstructor
public class MinimumStackImpl<T extends Comparable<T>> implements MinimumStack<T>  {

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
  @Override
  public T pop() {
    // TODO: Implement this method
    throw new NotImplementedException();
  }

  /**
   * Places an item on the top of the stack.
   * @param item
   */
  @Override
  public void push(T item) {
    // TODO: Implement this method
    throw new NotImplementedException();
  }

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
  @Override
  public T minimum() {
    // TODO: Implement this method
    throw new NotImplementedException();
  }
}
