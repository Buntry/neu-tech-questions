package question2.answer;

import java.math.BigInteger;
import question2.EnglishDictionary;
import question2.EnglishDictionarySearcher;

public class AnswerEnglishDictionarySearcher implements EnglishDictionarySearcher {

  private static BigInteger TWO = BigInteger.valueOf(2);

  /**
   * Given a word and a dictionary, find which page the word is on. Return a
   * negative number if the word is not found.
   *
   * @param word
   * @param dictionary
   * @return page number where word is present, negative if not found.
   */
  @Override
  public BigInteger findPageNumberOfWord(String word, EnglishDictionary dictionary) {
    BigInteger startPage = BigInteger.ZERO;
    BigInteger endPage = BigInteger.ONE;
    String endHeaderWord = dictionary.getPageHeaderWord(endPage);

    // Quadratically expand until a valid range has been found
    while (endHeaderWord.compareTo(word) <= 0) {
      startPage = endPage;
      endPage = endPage.multiply(TWO);
      endHeaderWord = dictionary.getPageHeaderWord(endPage);
    }

    // Binary search on the determined range
    BigInteger middlePage = (startPage.add(endPage)).divide(TWO);
    while (!startPage.equals(middlePage)) {
      String middleWord = dictionary.getPageHeaderWord(middlePage);
      if (word.compareTo(middleWord) < 0) {
        endPage = middlePage;
      } else {
        startPage = middlePage;
      }
      middlePage = (startPage.add(endPage)).divide(TWO);
    }

    // Determine if word is on start page
    return dictionary.isWordOnPage(word, startPage) ? startPage : BigInteger.ONE.negate();
  }
}
