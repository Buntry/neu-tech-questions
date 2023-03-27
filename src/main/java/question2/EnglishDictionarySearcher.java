package question2;

import java.math.BigInteger;

public interface EnglishDictionarySearcher {

  /**
   * Given a word and a dictionary, find which page the word is on. Return a
   * negative number if the word is not found.
   *
   * @param word
   * @param dictionary
   * @return page number where word is present, negative if not found.
   */
  BigInteger findPageNumberOfWord(String word, EnglishDictionary dictionary);
}
