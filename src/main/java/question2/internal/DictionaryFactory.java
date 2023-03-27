package question2.internal;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import question2.EnglishDictionary;

/**
 * Feel free to look at internals, however it's not relevant to solving the problem.
 */
public class DictionaryFactory {

  public static EnglishDictionary easyDictionary() {
    return new HayStackDictionary(easyPages());
  }

  public static EnglishDictionary hardDictionary() {
    return new HayStackDictionary(hardPages());
  }

  private static Map<BigInteger, List<String>> easyPages() {
    return ImmutableMap.<BigInteger, List<String>>builder()
        .put(BigInteger.ZERO, ImmutableList.of("apple"))
        .put(BigInteger.valueOf(1), ImmutableList.of("banana", "breakfast"))
        .put(BigInteger.valueOf(102), ImmutableList.of("tangerine", "target"))
        .put(BigInteger.valueOf(193), ImmutableList.of("yankee", "zebra"))
        .build();
  }

  // Wow... these pages are really large, wonder how we can get there quick
  public static Map<BigInteger, List<String>> hardPages() {
    BigInteger mediumPageNumber = doubleValue(BigInteger.valueOf(5), 64);
    BigInteger largePageNumber = doubleValue(BigInteger.valueOf(3), 2164);

    return ImmutableMap.<BigInteger, List<String>>builder()
        .put(BigInteger.ZERO, ImmutableList.of("apple"))
        .put(BigInteger.valueOf(1), ImmutableList.of("banana", "breakfast"))
        .put(mediumPageNumber, ImmutableList.of("tangerine", "target"))
        .put(largePageNumber.subtract(BigInteger.ONE), ImmutableList.of("yankee", "yolo"))
        .put(largePageNumber, ImmutableList.of("zebra", "zyzzyva"))
        .build();

  }

  private static BigInteger doubleValue(BigInteger value, int timesToDouble) {
    for (int i = 0; i < timesToDouble; i += 1) {
      value = value.multiply(BigInteger.valueOf(2));
    }
    return value;
  }


}
