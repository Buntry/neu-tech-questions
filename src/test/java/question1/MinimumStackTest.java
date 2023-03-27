package question1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.EmptyStackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import question1.answer.AnswerMinimumStack;

class MinimumStackTest {

  private MinimumStack<Integer> minimumStack;

  @BeforeEach
  public void setupMinimumStackOfIntegers() {
    // Modify the minimum stack used for testing
    minimumStack = new AnswerMinimumStack<>();
  }

  @Test
  public void testPopOnEmptyStackThrowsNoSuchElementException() {
    assertThatThrownBy(() -> minimumStack.pop())
        .isInstanceOf(EmptyStackException.class);
  }

  @Test
  public void testMinimumOnEmptyStackThrowsNoSuchElementException() {
    assertThatThrownBy(() -> minimumStack.minimum())
        .isInstanceOf(EmptyStackException.class);
  }

  @Test
  public void testPushingAndPoppingSingleValue() {
    Integer singleValue = 3;
    minimumStack.push(singleValue);

    Integer poppedValue = minimumStack.pop();

    assertThat(poppedValue).isEqualTo(singleValue);
  }

  @Test
  public void testPoppingSingleValueMultipleTimes() {
    Integer singleValue = 3;
    minimumStack.push(singleValue);
    minimumStack.pop();

    // additional pop should fail
    assertThatThrownBy(() -> minimumStack.pop())
        .isInstanceOf(EmptyStackException.class);
  }

  @Test
  public void testPushingAndGettingMinimum() {
    Integer singleValue = 3;
    minimumStack.push(singleValue);

    Integer minimumValue = minimumStack.minimum();

    assertThat(minimumValue).isEqualTo(singleValue);
  }

  @Test
  public void testMultipleDescendingMinimums() {
    Integer largeItem = 3;
    Integer smallItem = 1;

    minimumStack.push(largeItem);
    assertThat(minimumStack.minimum()).isEqualTo(largeItem);

    minimumStack.push(smallItem);
    assertThat(minimumStack.minimum()).isEqualTo(smallItem);
  }

  @Test
  public void testMultipleAscendingMinimums() {
    Integer largeItem = 3;
    Integer smallItem = 1;

    minimumStack.push(smallItem);
    assertThat(minimumStack.minimum()).isEqualTo(smallItem);

    minimumStack.push(largeItem);
    assertThat(minimumStack.minimum()).isEqualTo(smallItem);
  }

  @Test
  public void testMultipleMinimumsWithDuplicates() {
    // [ 3, 2, 3, 1, 2]
    Integer small = 1;
    Integer medium = 2;
    Integer large = 3;

    // [ 3 ]
    minimumStack.push(large);
    assertThat(minimumStack.minimum()).isEqualTo(large);

    // [ 3, 2 ]
    minimumStack.push(medium);
    assertThat(minimumStack.minimum()).isEqualTo(medium);

    // [ 3, 2, 3 ]
    minimumStack.push(large);
    assertThat(minimumStack.minimum()).isEqualTo(medium);

    // [ 3, 2, 3, 1 ]
    minimumStack.push(small);
    assertThat(minimumStack.minimum()).isEqualTo(small);

    // [ 3, 2, 3, 1, 2 ]
    minimumStack.push(medium);
    assertThat(minimumStack.minimum()).isEqualTo(small);

    // [ 3, 2, 3, 1 ]
    minimumStack.pop();
    assertThat(minimumStack.minimum()).isEqualTo(small);

    // [ 3, 2, 3 ]
    minimumStack.pop();
    assertThat(minimumStack.minimum()).isEqualTo(medium);

    // [ 3, 2 ]
    minimumStack.pop();
    assertThat(minimumStack.minimum()).isEqualTo(medium);

    // [ 3 ]
    minimumStack.pop();
    assertThat(minimumStack.minimum()).isEqualTo(large);

    // []
    minimumStack.pop();
    assertThatThrownBy(() -> minimumStack.minimum())
        .isInstanceOf(EmptyStackException.class);
  }
}