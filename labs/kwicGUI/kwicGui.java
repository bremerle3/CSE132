package kwicGUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import kwic.KWIC;
import kwic.Phrase;
import kwic.Word;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class kwicGui extends JFrame {
	private JPanel contentPane;
	private KWIC kwic;
	private PropertyChangeSupport pcs;
	int selectedIndex, selectedPIndex;
	boolean selection;
	JList Wordslist;
	JScrollPane Keywords;
	DefaultListModel<Word> keyword_model;
	Word w;
	int IndexSave, IndexPSave;
	JScrollPane phrase_panel;
	Phrase p;
	JList Phraselist;
	JList emptylist;
	DefaultListModel<Phrase> phrase_model, allphrase_model;
	private JTextField keywordText;
	private JTextField phraseText;
	private JButton DropAssoc;
	JScrollPane allPhrasePanel;
	Set<Phrase> emptysetPhrase;
	Word addWord;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					kwicGui frame = new kwicGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public kwicGui() {
		this.kwic = new KWIC();
		keyword_model = new DefaultListModel<Word>();
		phrase_model = new DefaultListModel<Phrase>();
		allphrase_model = new DefaultListModel<Phrase>();
		kwic.getPCS().addPropertyChangeListener("Phrase Added",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent arg0) {
						keyword_model.removeAllElements();
						Set<Word> allWords = kwic.getWords();
						
						//Set<Word> sortedAllWords = new TreeSet<>(allWords);
						for (Word w : allWords) {
							// System.out.println("Adding word: " + w);
							keyword_model.addElement(w);
						}
						
						
					}
				});
		kwic.getPCS().addPropertyChangeListener("Word Deleted",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent arg0) {
						keyword_model.removeElement(w);
					}
				});
		kwic.getPCS().addPropertyChangeListener("Phrase Deleted",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent arg0) {
						// System.out.println(" before phrase_model removes element.");
						// System.out.println(phrase_model.getElementAt(0)+":"+p);
						//boolean a = (phrase_model.getElementAt(0) == p);
						// System.out.println(a+"");
						// System.out.println("after phrase_model removes element.");
						phrase_model.removeElement(p); // //////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
						// System.out.println("after phrase_model removes element.");
					}
				});
		kwic.getPCS().addPropertyChangeListener("Word Added",
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent arg0) {
						keyword_model.addElement(addWord);
					}
				});
		
		kwic.addPhrases(new File("datafiles/kwic/fortunes.txt"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1209, 527);
		// create JPanel for our GUI
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// make scroll panel for keywords (but don't populate it yet)
		Keywords = new JScrollPane();
		Keywords.setBounds(32, 176, 276, 280);
		contentPane.add(Keywords);
		// make scroll panel for all phrases
		allPhrasePanel = new JScrollPane();
		allPhrasePanel.setBounds(761, 176, 320, 280);
		contentPane.add(allPhrasePanel);
		// Create scroll panel for phrases matched to key words
		phrase_panel = new JScrollPane();
		phrase_panel.setBounds(389, 176, 291, 280);
		contentPane.add(phrase_panel);
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pcs = kwic.getPCS();
				kwic.addPhrases(new File("datafiles/kwic/fortunes.txt"));
				System.out.println("OK");
				// Create scroll panel for key words
				Wordslist = new JList();
				Wordslist.setModel(keyword_model);
				Keywords.setViewportView(Wordslist);
				Phraselist = new JList();
				Phraselist.setModel(phrase_model);
				phrase_panel.setViewportView(Phraselist);
				Phraselist
						.addListSelectionListener(new ListSelectionListener() {
							@Override
							public void valueChanged(ListSelectionEvent e) {
								if (e.getValueIsAdjusting()) {
								} else {
									selectedPIndex = 0;
									selectedPIndex = Phraselist
											.getSelectedIndex();
									if (selectedPIndex < 0)
										selectedPIndex = IndexPSave;
									else
										IndexPSave = selectedPIndex;
									// System.out.println("You clicked on PHRASE index "+
									// selectedPIndex);
								}
							}
						});
				Wordslist.addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
						} else {
							selectedIndex = Wordslist.getSelectedIndex();
							if (selectedIndex < 0)
								selectedIndex = IndexSave;
							else
								IndexSave = selectedIndex;
							w = keyword_model.get(selectedIndex);

							phrase_model.removeAllElements();
							Set<Phrase> allPhrases = kwic.getPhrases(w);
							for (Phrase P : allPhrases) {
								// System.out.println("Adding phrase: " + P);
								phrase_model.addElement(P);
							}
							phrase_panel.setViewportView(Phraselist);
						}
					}
				});
				Wordslist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			}
		});
		btnStart.setBounds(32, 22, 89, 23);
		contentPane.add(btnStart);
		JButton DeleteKeyword = new JButton("Delete Keyword");
		DeleteKeyword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				kwic.deleteWord(w);
				Wordslist.setModel(keyword_model);
				Keywords.setViewportView(Wordslist);
				phrase_panel.setViewportView(emptylist);
			}
		});
		DeleteKeyword.setBounds(32, 56, 276, 23);
		contentPane.add(DeleteKeyword);
		
		JButton DeletePhrase = new JButton("Delete Phrase");
		DeletePhrase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p = phrase_model.get(selectedPIndex);
				kwic.deletePhrase(p);
				phrase_panel.setViewportView(Phraselist);
			}
		});
		DeletePhrase.setBounds(389, 56, 291, 23);
		contentPane.add(DeletePhrase);
		
		keywordText = new JTextField();
		keywordText.setBounds(35, 90, 123, 20);
		contentPane.add(keywordText);
		keywordText.setColumns(10);
		
		//Add Keyword Button
		JButton AddKeyword = new JButton("Add Keyword");
		AddKeyword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputword = keywordText.getText();
				addWord = new Word(inputword);
				kwic.addWord(addWord, emptysetPhrase);
				Keywords.setViewportView(Wordslist);
			}
		});
		AddKeyword.setBounds(167, 90, 141, 23);
		contentPane.add(AddKeyword);
		phraseText = new JTextField();
		phraseText.setBounds(389, 90, 187, 20);
		contentPane.add(phraseText);
		phraseText.setColumns(10);
		
		//Add Phrase Button
		JButton btnAddPhrase = new JButton("Add Phrase");
		btnAddPhrase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputPhrase = phraseText.getText();
				kwic.addPhrase(inputPhrase);
			}
		});
		btnAddPhrase.setBounds(591, 90, 89, 23);
		contentPane.add(btnAddPhrase);
		
		//Drop association button
		DropAssoc = new JButton("Drop Assoc");
		DropAssoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p = phrase_model.get(selectedPIndex);
				kwic.dropAssoc(w, p);
				phrase_panel.setViewportView(Phraselist);
			}
		});
		DropAssoc.setBounds(32, 121, 114, 23);
		contentPane.add(DropAssoc);
		
		//Labels
		JLabel lblWords = new JLabel("All Words");
		lblWords.setBounds(148, 151, 63, 14);
		contentPane.add(lblWords);
		JLabel lblAssociatedPhrases = new JLabel("Associated Phrases");
		lblAssociatedPhrases.setBounds(490, 151, 130, 14);
		contentPane.add(lblAssociatedPhrases);
		JLabel lblAllPhrases = new JLabel("All Phrases");
		lblAllPhrases.setBounds(888, 151, 94, 14);
		contentPane.add(lblAllPhrases);
	}
}

