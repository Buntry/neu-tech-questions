package question2;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import question2.answer.AnswerEnglishDictionarySearcher;
import question2.internal.DictionaryFactory;

class EnglishDictionarySearcherTest {

  private final static List<BigInteger> hardPagesWithTargets = DictionaryFactory
      .hardPages()
      .keySet()
      .stream()
      .sorted()
      .collect(Collectors.toList());
  private EnglishDictionarySearcher englishDictionarySearcher;


  @BeforeEach public void setupEnglishDictionarySearcher() {
    // Modify the english dictionary searcher used for testing
    englishDictionarySearcher = new AnswerEnglishDictionarySearcher();
  }

  @Test public void testReturnsNegativeValueIfWordMissingFirstPage() {
    EnglishDictionary dictionary = DictionaryFactory.easyDictionary();
    String word = "aardvark";

    BigInteger pageNumber = englishDictionarySearcher.findPageNumberOfWord(word, dictionary);

    assertThat(pageNumber).isLessThan(BigInteger.ZERO);
  }

  @Test public void testReturnsNegativeValueIfWordMissingSecondPage() {
    EnglishDictionary dictionary = DictionaryFactory.easyDictionary();
    String word = "avalon";

    BigInteger pageNumber = englishDictionarySearcher.findPageNumberOfWord(word, dictionary);

    assertThat(pageNumber).isLessThan(BigInteger.ZERO);
  }

  @Test public void testGetWordOnFirstPage() {
    EnglishDictionary dictionary = DictionaryFactory.easyDictionary();
    String word = "apple";

    BigInteger pageNumber = englishDictionarySearcher.findPageNumberOfWord(word, dictionary);

    assertThat(pageNumber).isEqualTo(BigInteger.ZERO);
  }

  @Test public void testGetWordOnSecondPage() {
    EnglishDictionary dictionary = DictionaryFactory.easyDictionary();
    String word = "banana";

    BigInteger pageNumber = englishDictionarySearcher.findPageNumberOfWord(word, dictionary);

    assertThat(pageNumber).isEqualTo(BigInteger.ONE);
  }

  @Test public void testGetNonHeaderWordOnSecondPage() {
    EnglishDictionary dictionary = DictionaryFactory.easyDictionary();
    String word = "breakfast";

    BigInteger pageNumber = englishDictionarySearcher.findPageNumberOfWord(word, dictionary);

    assertThat(pageNumber).isEqualTo(BigInteger.ONE);
  }

  @Test public void testGetWordInMiddleOfDictionary() {
    EnglishDictionary dictionary = DictionaryFactory.easyDictionary();
    String word = "target";

    BigInteger pageNumber = englishDictionarySearcher.findPageNumberOfWord(word, dictionary);

    assertThat(pageNumber).isEqualTo(BigInteger.valueOf(102));
  }

  @Test public void testGetWordAtEndOfDictionary() {
    EnglishDictionary dictionary = DictionaryFactory.easyDictionary();
    String word = "zebra";

    BigInteger pageNumber = englishDictionarySearcher.findPageNumberOfWord(word, dictionary);

    assertThat(pageNumber).isEqualTo(BigInteger.valueOf(193));
  }

  @Test public void testGetWordInMiddleOfHardDictionary() {
    EnglishDictionary dictionary = DictionaryFactory.hardDictionary();
    String word = "target";
    int hardPageIndex = 2;

    BigInteger pageNumber = englishDictionarySearcher.findPageNumberOfWord(word, dictionary);

    assertThat(pageNumber).isEqualTo(hardPagesWithTargets.get(hardPageIndex));
  }

  @Test public void testGetWordAtEndOfHardDictionary() {
    EnglishDictionary dictionary = DictionaryFactory.hardDictionary();
    String word = "zyzzyva";
    int hardPageIndex = 4;

    BigInteger pageNumber = englishDictionarySearcher.findPageNumberOfWord(word, dictionary);

    assertThat(pageNumber).isEqualTo(hardPagesWithTargets.get(hardPageIndex));
  }
}