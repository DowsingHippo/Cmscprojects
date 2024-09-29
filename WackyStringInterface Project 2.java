package cmsc256;

/**
 * This is an interface for a class that represents a string,
 * defined as a sequence of characters.
 */
public interface WackyStringInterface {

	/**
	 * Sets the value of the current string.
	 *
	 * @param string   The value to be set
	 */
	public void setWackyString(String string);

	/**
	 * Returns the current string
	 *
	 * @return Current string
	 */
	public String getWackyString();

	/**
	 * Returns a string that consists of all and only the characters
	 * in every fifth positions (i.e., fifth, tenth, and so on) in
	 * the current string, in the same order and with the same case as
	 * in the current string. The first character in the string is
	 * considered to be in Position 1.
	 *
	 * @return String made of characters in every third positions in the
	 * current string
	 */
	public String getEveryFifthCharacter();

	/**
	 * Returns a string that consists of all and only the characters
	 * in the even positions (i.e., second, fourth, sixth, and so on) in
	 * current string, in the same order and with the same case as in
	 * the current string. The first character in the string is
	 * considered to be in Position 1.
	 * @return String made of characters in the even positions in the
	 * current string
	 */
	public String getEvenCharacters();

	/**
	 * Returns a string that consists of all and only the characters
	 * in the odd positions (i.e., first, third, fifth, and so on) in
	 * current string, in the same order and with the same case as in
	 * the current string. The first character in the string is
	 * considered to be in Position 1.
	 * @return String made of characters in odd positions in the
	 * current string
	 * @throws  IllegalArgumentException if parameter is other than "odd" or "even"
	 */
	public String getOddCharacters();

	/**
	 * Returns the number of characters that are digits in the current string
	 *  if two (and no more than two) of the same digit appear side by side.
	 *
	 * @return Number of double-digits in the current string
	 */
	public int countDoubleDigits();

	/**
	 * Returns the number of characters that are either 'a' 'e' 'i' 'o' 'u' or 'y'
	 * in the current string regarless of case, 'A' or 'a'.
	 *
	 * @return Number of vowel in the current string
	 */
	public int countVowels();

	/**
	 * Converts a multi-word String that represents a first name and last name
	 * into the format of last name, first name.
	 *   For example: "Debra Duke" --> "Duke, Debra"
	 * In the case of more than single word names, assume the first word is
	 * the first name and all remaining words make up the last names
	 *   For example:  "Oscar de la Renta" --> "de la Renta, Oscar"
	 *
=	 * @return			String containing the formatted phone number
	 * @throws UnsupportedOperationException if the argument contains a single word
	 */
	public String reformatName();

    /**
     * Replace all occurrences of a single zero (0) with the string "Go VCU"
	  * in the current string,
     * and all occurrences of a double zero (00) with the string "CS@VCU"
     */
    void ramifyString();

	/**
	 * Replace the _individual_ digits in the current string, between
	 * startPosition and endPosition (included), with the corresponding
	 * Roman numeral symbol(s). The first character in the string is
	 * considered to be in Position 1. Digits are converted individually,
	 * even if contiguous, and digit "0" is not converted (e.g., 460 is
	 * converted to IVVI0). In case you are not familiar with Roman
	 * numerals, see https://en.wikipedia.org/wiki/Roman_numerals
	 *
	 * @param startPosition  Position of the first character to consider
	 * @param endPosition    Position of the last character to consider
	 * @throws IllegalArgumentException
	 *            If "startPosition" > "endPosition" (but both are
	 *            within bounds)
	 */
	void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition)
			throws IllegalArgumentException;
}
