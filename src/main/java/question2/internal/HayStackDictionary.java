package question2.internal;

import com.google.common.collect.ImmutableList;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import question2.EnglishDictionary;

/**
 * This is the implementation of English-dictionary that the
 * tests will use, feel free to look at its internals, however
 * it's not relevant to solving the problem.
 */
@Value
@RequiredArgsConstructor
public class HayStackDictionary implements EnglishDictionary {
  static Integer INTERNAL_PAGE_LIMIT = 10000;

  SortedMap<BigInteger, List<String>> dictionary;

  public HayStackDictionary(Map<BigInteger, List<String>> initialPages) {
    dictionary = new TreeMap<>(initialPages);
  }

  @Override
  public String getPageHeaderWord(BigInteger pageNumber) {
    if (!dictionary.containsKey(pageNumber)) {
      populateDictionaryAtPageNumber(pageNumber);
    }
    return dictionary.get(pageNumber).get(0);
  }

  @Override
  public boolean isWordOnPage(String word, BigInteger pageNumber) {
    if (!dictionary.containsKey(pageNumber)) {
      populateDictionaryAtPageNumber(pageNumber);
    }
    return dictionary.get(pageNumber).contains(word);
  }

  /**
   * If a random page is accessed, populate it. Don't go over a threshold.
   * @param pageNumber
   */
  private void populateDictionaryAtPageNumber(BigInteger pageNumber) {
    if (pageNumber.compareTo(BigInteger.ZERO) < 0) {
      throw new IllegalArgumentException("There are no negative page numbers in this dictionary.");
    }

    SortedMap<BigInteger, List<String>> headMap = dictionary.headMap(pageNumber);
    List<String> lastHeadPage = headMap.get(headMap.lastKey());
    String startWord = lastHeadPage.get(lastHeadPage.size() - 1);


    SortedMap<BigInteger, List<String>> tailMap = dictionary.tailMap(pageNumber);
    String endWord = "";
    if (tailMap.size() != 0) {
      endWord = tailMap.get(tailMap.firstKey()).get(0);
    }

    String newHeaderWord = getBetween(startWord, endWord);
    dictionary.put(pageNumber, ImmutableList.of(newHeaderWord));

    validateDictionarySize();
  }

  private void validateDictionarySize() {
    if (dictionary.size() > INTERNAL_PAGE_LIMIT) {
      throw new IllegalStateException("You've reached the access limit on the dictionary. "
          + "See if you can call it less.");
    }
  }


  /**
   * Generates string lexicographically ordered between two given strings.
   *
   * @param left - left string in lexicographical order
   * @param right - right string in lexicographical order
   * @return string of minimal length that is between left and right lexicographically
   *
   * Link: https://stackoverflow.com/questions/38923376/return-a-new-string-that-sorts-between-two-given-strings
   */
  private String getBetween(String left, String right) {
    final char ALPHABET_LEFT = 'a' - 1;
    final char ALPHABET_RIGHT = 'z' + 1;
    char l = 'a';
    char r = 'a';
    int pos;
    StringBuilder str;
    for (pos = 0; l == r; pos++) {                                  // find leftmost non-matching character
      l = pos < left.length() ? left.charAt(pos) : ALPHABET_LEFT;
      r = pos < right.length() ? right.charAt(pos) : ALPHABET_RIGHT;
    }
    str = new StringBuilder(left.substring(0, pos - 1));            // copy identical part of string
    if (l == ALPHABET_LEFT) {                                       // prev string equals beginning of next
      while (r == 'a') {                                          // next character is 'a'
        r = pos < right.length() ? right.charAt(pos++) : ALPHABET_RIGHT;   // get char from next
        str.append('a');                                        // insert an 'a' to match the 'a'
      }
      if (r == 'b') {                                             // next character is 'b'
        str.append('a');                                        // insert an 'a' to match the 'b'
        r = ALPHABET_RIGHT;                                     // set to end of alphabet
      }
    }
    else if (l + 1 == r) {                                          // found consecutive characters
      str.append(l);                                              // insert character from prev
      r = ALPHABET_RIGHT;                                         // set to end of alphabet
      while ((l = pos < left.length() ? left.charAt(pos++) : ALPHABET_LEFT) == 'z') {  // p='z'
        str.append('z');                                        // insert 'z' to match 'z'
      }
    }
    return str.toString() + (char)(Math.ceil((l + r) / 2.0));       // append middle character
  }
}
