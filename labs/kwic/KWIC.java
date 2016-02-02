package kwic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.beans.PropertyChangeSupport;
import java.io.*;

/**
 * Key Word in Context
 */

public class KWIC {

	protected PropertyChangeSupport pcs;
	public Map<Word, Set<Phrase>> map = new HashMap<Word, Set<Phrase>>();

	public KWIC() {
		pcs = (new PropertyChangeSupport(this));
	}

	/**
	 * Required for part (b) of this lab. Accessor for the
	 * {@link PropertyChangeSuppport}
	 */

	public PropertyChangeSupport getPCS() {
		return pcs;
	}

	/**
	 * Convenient interface, accepts a standard Java {@link String}
	 * 
	 * @param s
	 * String to be added
	 */
	public void addPhrase(String s) {
		addPhrase(new Phrase(s));
	}

	/**
	 * Add each line in the file as a phrase. For each line in the file, call
	 * {@link addPhrase(String)} to add the line as a phrase.
	 * 
	 * @param file
	 *            the file whose lines should be loaded as phrases
	 * @throws IOException
	 */
	public void addPhrases(File file) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			BufferedReader d = new BufferedReader(new InputStreamReader(fis));

			while (d.ready()) {
				addPhrase(d.readLine());
			}
			d.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * For each {@link Word} in the {@link Phrase}, add the {@link Word} to the
	 * association. Use reduction to {@link #forceAssoc(Word, Phrase)}.
	 * 
	 * @param p
	 *            Phrase to be added
	 */
	public void addPhrase(Phrase p) {
		for (Word w : p.getWords()) {
			forceAssoc(w, p);
		}
		pcs.firePropertyChange("Phrase Added", false, true);
	}

	/**
	 * For each word in the {@link Phrase}, delete the association between the
	 * word and the phrase. Use reduction to {@link #dropAssoc(Word, Phrase)}.
	 */
	public void deletePhrase(Phrase p) {
		for (Word w : p.getWords()) {
			dropAssoc(w, p);
		}
		pcs.firePropertyChange("Phrase Deleted", false, true);
	}

	/** Force a mapping between the speicified {@link Word} and {@link Phrase} */
	public void forceAssoc(Word w, Phrase p) {
		Word key = new Word(w.getMatchWord());

		if (map.containsKey(key))
			map.get(key).add(p);
		else {
			Set<Phrase> set_Phrase = new HashSet<Phrase>();
			set_Phrase.add(p);
			map.put(key, set_Phrase);
		}
		// Leave the following line as the last line of this method
		pcs.firePropertyChange("Phrase Added", false, true);
	}

	/**
	 * Drop the association between the specified {@link Word} and
	 * {@link Phrase}, if any
	 */
	public void dropAssoc(Word w, Phrase p) {
		if (map.containsKey(w)) {
			map.get(w).remove(p);
		}
		// Leave the following line as the last line of this method
		pcs.firePropertyChange("Phrase Deleted", false, true);
	}

	/**
	 * Return a Set that provides each {@link Phrase} associated with the
	 * specified {@link Word}.
	 */
	public Set<Phrase> getPhrases(Word w) {
		// This method should never return null
		if (map.get(w) == null)
			return new HashSet<Phrase>();
		else
			return map.get(w);

	}

	/**
	 * Drop a word completely from the KWIC
	 * 
	 * @param w
	 *            Word to be dropped
	 */
	public void deleteWord(Word w) {
		map.remove(w);
		// Leave the following line as the last line
		pcs.firePropertyChange("Word Deleted", false, true);
	}
	
	/**
	 * Add a word to the KWIC
	 * 
	 * @param w
	 *            Word to be added
	 */
	public void addWord(Word w, Set<Phrase> p) {
		map.put(w, p);
		// Leave the following line as the last line
		pcs.firePropertyChange("Word Added", false, true);
	}

	/** Rerturn a Set of all words */
	public Set<Word> getWords() {
		// This method should never return null
		if (map.keySet().isEmpty())
			return new HashSet<Word>();
		return map.keySet();
	}
}
