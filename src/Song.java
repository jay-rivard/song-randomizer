import java.util.Scanner;
import java.util.Random;


public class Song {
	/**
	 * The amount of chords in the song
	 */
	private int ChordProgressionLength;
	/**
	 * The song's key
	 */
	private String key[];
	/**
	 * The song's tempo
	 */
	int tempo = 0;
	/**
	 * The chord progression to be output to the user. Generated using setChordProgression().
	 */
	private String chordProgression;
	/**
	 * Random instance from java.util used for generating random numbers
	 */
	Random r = new Random();
	/**
	 * Constants representing the minimum and maximum tempo values
	 */
	private static final int MIN_BPM = 60;
	private static final int MAX_BPM = 160;
	
	/*
	 * These arrays represent the basic 12 Major & 12 minor scales / keys.
	 * 
	 * The multidimensional array holds all scales.
	 * 
	 * Keys/chords are named according to the circle of fifths (Bb over A#, Eb over D#, etc).
	 * Chords within the enharmonic scales are represented as sharp over flat
	*/
	
	String[] AMaj = { "(I)A-Maj", "(II)B-min", "(III)C#-min", "(IV)D-Maj", "(V)E-Maj", "(VI)F#-min", "(VII)G#-dim"};
	
	String[] Amin = { "(I)A-min", "(II)B-dim", "(III)C-Maj", "(IV)D-min", "(V)E-min", "(VI)F-Maj", "(VII)G-Maj"};
	
	String[] BFlatMaj = { "(I)Bb-Maj", "(II)C-min", "(III)D-min", "(IV)Eb-Maj", "(V)F-Maj", "(VI)G-min", "(VII)A-dim"};
	
	String[] BFlatASharpmin = { "(I)A#-min", "(II)B#-dim", "(III)C#-Maj", "(IV)D#-min", "(V)E#-min", "(VI)F#-Maj", "(VII)G#-Maj"};
	
	String[] CFlatBMaj = { "(I)B-Maj", "(II)C#-min", "(III)D#-min", "(IV)E-Maj", "(V)F#-Maj", "(VI)G#-min", "(VII)A#-dim"};
	
	String[] Bmin = { "(I)B-min", "(II)C#-dim", "(III)D-Maj", "(IV)E-min", "(V)F#-min", "(VI)G-Maj", "(VII)A-Maj"};
	
	String[] CMaj = { "(I)C-Maj", "(II)D-min", "(III)E-min", "(IV)F-Maj", "(V)G-Maj", "(VI)A-min", "(VII)B-dim"};
	
	String[] Cmin = { "(I)C-min", "(II)D-dim", "(III)D#-Maj", "(IV)F-min", "(V)G-min", "(VI)G#-Maj", "(VII)A#-Maj"};
	
	String[] DFlatCSharpMaj = { "(I)C#-Maj", "(II)D#-min", "(III)E#-min", "(IV)F#-Maj", "(V)G#-Maj", "(VI)A#-min", "(VII)B#-dim"};
	
	String[] CSharpmin = { "(I)C#-min", "(II)D#-dim", "(III)E-Maj", "(IV)F#-min", "(V)G#-min", "(VI)A-Maj", "(VII)B-Maj"};
	
	String[] DMaj = { "(I)D-Maj", "(II)E-min", "(III)F#-min", "(IV)G-Maj", "(V)A-Maj", "(VI)B-min", "(VII)C#-dim"};
	
	String[] Dmin = { "(I)D-min", "(II)E-dim", "(III)F-Maj", "(IV)G-min", "(V)A-min", "(VI)A#-Maj", "(VII)C-Maj"};
	
	String[] EFlatMaj = { "(I)Eb-Maj", "(II)F-min", "(III)G-min", "(IV)Ab-Maj", "(V)Bb-Maj", "(VI)C-min", "(VII)D-dim"};
	
	String[] EFlatDSharpmin = { "(I)D#-min", "(II)E#-dim", "(III)F#-Maj", "(IV)G#-min", "(V)A#-min", "(VI)B-Maj", "(VII)C#-Maj"};

	String[] EMaj = { "(I)E-Maj", "(II)F#-min", "(III)G#-min", "(IV)A-Maj", "(V)B-Maj", "(VI)C#-min", "(VII)D#-dim"};
	
	String[] Emin = { "(I)E-min", "(II)F#-dim", "(III)G-Maj", "(IV)A-min", "(V)B-min", "(VI)C-Maj", "(VII)D-Maj"};
	
	String[] FMaj = { "(I)F-Maj", "(II)G-min", "(III)A-min", "(IV)Bb-Maj", "(V)C-Maj", "(VI)D-min", "(VII)E-dim"};
	
	String[] Fmin = { "(I)F-min", "(II)G-dim", "(III)Ab-Maj", "(IV)Bb-min", "(V)C-min", "(VI)Db-Maj", "(VII)Eb-Maj"};
	
	String[] GFlatFSharpMaj = { "(I)F#-Maj", "(II)G#-min", "(III)A#-min", "(IV)B-Maj", "(V)C#-Maj", "(VI)D#-min", "(VII)E#-dim"};

	String[] FSharpmin = { "(I)F#-min", "(II)G#-dim", "(III)A-Maj", "(IV)B-min", "(V)C#-min", "(VI)D-Maj", "(VII)E-Maj"};
	
	String[] GMaj = { "(I)G-Maj", "(II)A-min", "(III)B-min", "(IV)C-Maj", "(V)D-Maj", "(VI)E-min", "(VII)F#-dim"};
	
	String[] Gmin = { "(I)G-min", "(II)A-dim", "(III)Bb-Maj", "(IV)C-min", "(V)D-min", "(VI)Eb-Maj", "(VII)F-Maj"};
	
	String[] AFlatMaj = { "(I)Ab-Maj", "(II)Bb-min", "(III)C-min", "(IV)Db-Maj", "(V)Eb-Maj", "(VI)F-min", "(VII)G-dim"};

	String[] AFlatGSharpmin = { "(I)G#-min", "(II)A#-dim", "(III)B-Maj", "(IV)C#-min", "(V)D#-min", "(VI)E-Maj", "(VII)F#-Maj"};
		
	String[][] keyArray = new String[][] {AMaj, Amin, BFlatMaj, BFlatASharpmin, CFlatBMaj, Bmin, CMaj, Cmin, DFlatCSharpMaj, CSharpmin, DMaj, Dmin,
				EFlatMaj, EFlatDSharpmin, EMaj, Emin, FMaj, Fmin, GFlatFSharpMaj, FSharpmin, GMaj, Gmin, AFlatMaj, AFlatGSharpmin};
	
	String[] noteLengths = {"1/8", "1/4", "1/2", "1", "2", "4"};
	/**
	 * Sets the length of the chord progression
	 * @param input The length of the chord progression.
	 */
	public void setChordProgressionLength(int input) {
		ChordProgressionLength = input;
	}
	
	/**
	 * Randomly selects a scale from the multidimensional keyArray and sets it as the song's key.
	 */
	public void setKey() {
		key = keyArray[r.nextInt(keyArray.length)];
	}
	
	public String[] getKey() {
		return this.key;
	}
	
	/**
	 * Randomly selects a tempo between 60 & 160 BPM and sets its as the song's tempo.
	 */
	public void setTempo() {
		tempo = r.nextInt(MAX_BPM - MIN_BPM + 1) + MIN_BPM;
	}
	

	/**
	 * Creates a chord pattern based on the randomly generated scale and tempo.
	 * Does not allow two chords to repeat one after the other.
	 */
	public void setChordProgression() {
		
		 String priorChord = "";
		
		for(int i = 0; i < ChordProgressionLength; i++) {
			
			String currentChord;
			
			do {
				currentChord = key[r.nextInt(7)];
			} while(currentChord == priorChord);
			
			priorChord = currentChord;
			
			if(i==0) {chordProgression = currentChord;}
			else{chordProgression = chordProgression + " -> " + currentChord;}
		}
		
	}
	
	/**
	 * Returns a note from within the current value of Key[].
	 * @return A note from Key[]
	 */
	public String getKeyNote() {
		int random = r.nextInt(7);
		String note = key[random].substring((key[random].lastIndexOf(")") + 1), key[random].lastIndexOf("-") );
		note += " [" + noteLengths[r.nextInt(6)] + "]";
		return note;
	}
	
	/**
	 * Generates a report describing the song's info in String format
	 * @return A report describing the song's info.
	 */
	public String generateReport() {
		String report = "<html> <p style=\"text-align: center\">Your song is in the key of " + key[0].substring(key[0].lastIndexOf(")") + 1) + "</p><hr><p style=\"text-align: center\">Your song plays at a tempo of " + tempo + " BPM</p><hr>" +
					"<p style=\"text-align: center\">Your chord progression is " + chordProgression + "</p></html>";
		return report;
	};
}
