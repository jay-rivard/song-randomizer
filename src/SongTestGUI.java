import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.portable.InputStream;

public class SongTestGUI {

	public static void main(String[] args) {
		
		Song song = new Song();
		Scanner in = new Scanner(System.in);
		
		Font defaultFont = new Font("Times New Roman", Font.PLAIN, 16);
		Font titleFont = null;
		Font resultFont = null;
		Font smallResultFont = null;
		
		try {
			resultFont = Font.createFont(Font.TRUETYPE_FONT, new File("resource/yataghan.ttf")).deriveFont(16f);
			titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("resource/Blackshadow.ttf")).deriveFont(45f);
			smallResultFont = Font.createFont(Font.TRUETYPE_FONT, new File("resource/yataghan.ttf")).deriveFont(14f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resource/Blackshadow.ttf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resource/yataghan.ttf")));
		} catch (FontFormatException | IOException e1) {
			e1.printStackTrace();
		}

		// TITLE PANEL (NORTH) - CONTAINS THE TITLE, PROMPT, AND INPUT
		JLabel title = new JLabel("Song Randomizer");
			title.setHorizontalAlignment(JLabel.CENTER);
			title.setFont(titleFont);
			Border titleMargin = new EmptyBorder(10, 0, 0, 0);
			title.setBorder(titleMargin);
			
		JLabel prompt = new JLabel("Enter the length of your chord progression : ");
			prompt.setHorizontalAlignment(JLabel.CENTER);
			prompt.setFont(smallResultFont);
		
		JTextField input = new JTextField();
			input.setHorizontalAlignment(JLabel.CENTER);
			input.setBackground(new Color(223, 203, 170)); 
			Border inputMargin = new EmptyBorder(0, 0, 10, 0);
			input.setBorder(inputMargin);
			input.setFont(smallResultFont);
		
		JPanel titlePanel = new JPanel();
			titlePanel.setLayout(new GridLayout(3, 1));
			titlePanel.add(title);
			titlePanel.add(prompt);
			titlePanel.add(input);
			titlePanel.setBackground(new Color(223, 203, 170));
		   
		// RESULT PANEL (CENTER) - CONTAINS RESULT OF RANDOMIZATION
		JLabel result = new JLabel();
			result.setHorizontalAlignment(JLabel.CENTER);
			result.setFont(resultFont);
			result.setForeground(new Color(128,5,5));
		JLabel melody = new JLabel();
			melody.setHorizontalAlignment(JLabel.CENTER);
			melody.setFont(resultFont);
			melody.setForeground(new Color(128,5,5));
			
		JPanel resultPanel = new JPanel();
			resultPanel.setLayout(new GridLayout(2,1));
			resultPanel.setBackground(new Color(223, 203, 170));
			resultPanel.add(result);
			resultPanel.add(melody);
		
		// BUTTON PANEL (SOUTH) - CONTAINS THE BUTTONS
		JButton randomize = new JButton("Go!");
			randomize.setBackground(new Color(189, 164, 105));
			randomize.setFont(smallResultFont);
			
		JButton reset = new JButton("Reset");
			reset.setBackground(new Color(189, 164, 105));
			reset.setFont(smallResultFont);
			
		JButton addNote = new JButton("Get Note");
			addNote.setBackground(new Color(189, 164, 105));
			addNote.setFont(smallResultFont);
			addNote.setVisible(false);
			
		JPanel buttonPanel = new JPanel();
			buttonPanel.add(randomize);
			buttonPanel.add(reset);
			buttonPanel.add(addNote);
			buttonPanel.setBackground(new Color(223, 203, 170));
	
		// MAIN FRAME 
		JFrame frame = new JFrame();
			frame.setTitle("Song Randomizer");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(new Dimension(700, 550));
			frame.setResizable(false);
			frame.add(titlePanel, BorderLayout.NORTH);
			frame.add(resultPanel, BorderLayout.CENTER);
			frame.add(buttonPanel, BorderLayout.SOUTH);

		/**
		 * Adds an action listener to the randomize button which will produce and ouput a random key, tempo, and chord progression.
		 * Input must be an integer between 0 & 8.
		 * @see Song
		 */
		randomize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					melody.setText("");
					song.setKey();
					song.setTempo();
					if((Integer.parseInt(input.getText()) < 1) || (Integer.parseInt(input.getText()) > 8  )) {
						result.setText("I DON'T LIKE THAT NUMBER <(o)<>(o)>");
					} else {
						song.setChordProgressionLength(Integer.parseInt(input.getText()));
						song.setChordProgression();
						result.setText(song.generateReport());
						addNote.setVisible(true);
					}
				}
				catch(NumberFormatException err) {
					result.setText("I DON'T THINK THAT'S A NUMBER <(o)<>(o)>");
				}
			}
		});
		
		/**
		 * Adds an action listener to the reset button which clears the text of both the input and result.
		 */
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.setText("");
				result.setText("");
				melody.setText("");
				addNote.setVisible(false);
			}
		});
		
		/**
		 * Adds an action listener to the addNote button which will pick a random note from the currently randomized scale and 
		 * output it on screen along with a note length ranging from 1/8 - 4.
		 */
		addNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String note = song.getKeyNote();
				if(melody.getText() == "")  {
					melody.setText("MELODY : " + note);
				} else {
					melody.setText(melody.getText() + " - " + note);
				}
			}
		});

		// Make the frame visible
		frame.setVisible(true);
		
		
	}

	
}
