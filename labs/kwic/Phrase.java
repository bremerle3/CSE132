package kwic;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Represent a phrase.
 * 
 */
public class Phrase {

	final protected String phrase;

	public Phrase(String s) {
		phrase = s;
	}

	/**
	 * Provide the words of a phrase. Each word may have to be cleaned up:
	 * punctuation removed, put into lower case
	 */

	/**
	 * Use StringTokenizer to break the phrase into words. Return a Set of such
	 * words. This should never return null
	 */

	public Set<Word> getWords() {
		StringTokenizer st = new StringTokenizer(phrase);
		Set<Word> temp = new HashSet<Word>();
		while (st.hasMoreTokens())
			temp.add(new Word(cleanUp(st.nextToken())));
		return temp;
	}

	/**
	 * The behavior of this lab depends on how you view this method. Are two
	 * phrases the same because they have the same words? Or are they the same
	 * because they are string-equlivalent.
	 * <UL>
	 * <LI>What song, Is that Becky
	 * <LI>What song is that, Becky
	 * </UL>
	 * The above phrases have the same words but are different strings.
	 */

	/**
	 * It returns true when Object o and this Phrase are the same objects or
	 * their lower case strings are the same.
	 */
	public boolean equals(Object o) {
		if (this.getClass() != o.getClass())
			return false;
		if (this == o)
			return true;
		Phrase other_phrase = (Phrase) o;
		String this_lower = this.toString().toLowerCase();
		String other_lower = other_phrase.toString().toLowerCase();
		if (this_lower.equals(other_lower))
			return true;
		return false;

	}

	/**
	 * This method must also be properly defined, or else your {@link HashSet}
	 * structure won't operate properly.
	 */
	
	/** 
	 * Return a hash code value for the Phrase object. 
	 * This method is supported for the benefit of hashmap.
	 */
	public int hashCode() {

		int hashnumber = 1;
		if (phrase == null)
			hashnumber = 31 * hashnumber;
		else
			hashnumber = 31 * hashnumber + phrase.hashCode();
		return hashnumber;
		// return super.hashCode();
	}

	/**
	 * Filter the supplied {@link String} (which is the String of a
	 * {@link Phrase} presumably) into a canonical form for subsequent matching.
	 * The actual filtering depends on what you consider to be insignificant in
	 * terms of matching.
	 * <UL>
	 * <LI>If punctuation is irrelevant, remove puncutation.
	 * <LI>If case does not matter, than convert to lower (or upper) case.
	 * </UL>
	 */
	
	/**
	 * 
	 * Don't just return s, but return a cleaned up version of s
	 * as described above
	 */
	protected static String cleanUp(String s) {
		WordFilter toFilter = WordFilter.instance();
		String result = toFilter.makeCanonical(s);
		return result;
	}

	public String toString() {
		return phrase;
	}

}
