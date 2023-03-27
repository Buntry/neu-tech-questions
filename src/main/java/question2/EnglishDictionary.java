package question2;

import java.math.BigInteger;

// Represents a lexicographically-ordered collection of words that are paginated.
// Ex. Page 0 has a header word of "apple", Page 102 has a header word of "banana"
public interface EnglishDictionary {

  /**
   * Gets the word at the top of the page, all words on this page come lexicographically
   * after this word. For example, if the header word is "banana" than the word "breakfast"
   * cannot come before this page. "breakfast" must come on the same or later page, or it may
   * not be present in the dictionary.
   *
   * @param pageNumber - the page number of a large dictionary
   * @return the header word
   */
  String getPageHeaderWord(BigInteger pageNumber);

  /**
   * Determines if the given word is present on the given page number.
   * @param word
   * @param pageNumber
   * @return word presence on page
   */
  boolean isWordOnPage(String word, BigInteger pageNumber);
}
