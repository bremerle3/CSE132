package kwic;

/** Represents the original and matching forms of a word.  
 * You must implement 
 * {@link Object#hashCode()} correctly as well as
 * {@link Object#equals(Object)} 
 * for this to work.
 */

public class Word {
	public String word;

	/** Represent a word of a {@link Phrase}
	 * @param w The original word
	 */
	public Word(String w){
		word = w;
	}

	/**
	 * The word used for matching is the original word run through
	 * the WordCanonical filter.
	 * @return the form of the word used for matching.
	 * 
	 */
	public String getMatchWord() {
		
		if (word.isEmpty()) return "";
		WordFilter match = WordFilter.instance();
		String matchWord = match.makeCanonical(word);
		return matchWord;
		
	}

	/**
	 * 
	 * @return the original word
	 */
	public String getOriginalWord() {
		
		return word;
	}

	/** 
	 * Return a hash code value for the Word object. 
	 * This method is supported for the benefit of hashmap.
	 */
	public int hashCode() { 
		String hashword = getMatchWord();
		int hashnumber = 1;
		if (hashword == null)
			hashnumber = 31*hashnumber;
		else
			hashnumber = 31*hashnumber+hashword.hashCode();
		return hashnumber;
	}


	/**
	 * You must implement this so that two words equal each
	 * other if their matched forms equal each other.
	 * You can let eclipse generate this method automatically,
	 * but you have to modify the resulting code to get the
	 * desired effect.
	 * 
	 * This method is commented out so you can have eclipse generate
	 * a skeleton of it for you.
	 * 
	 * It returns true when Object o and this Word are the same objects or
	 * their lower case words are the same.
	 * 
	 */
	public boolean equals(Object o) {
		if(this.getClass() != o.getClass())
			return false;
		if(this == o) return true;
		Word other_word = (Word) o;
		String lower_word = this.getMatchWord();
		if(lower_word.equals(other_word.getMatchWord())) 
			return true;
		return false;
		
		
	}

	/**
	 * @return the word and its matching form, if different
	 */
	public String toString(){
		if (getOriginalWord().equals(getMatchWord()))
			return getOriginalWord();
		else
			return getOriginalWord() + " --> " + getMatchWord();
	}

}
