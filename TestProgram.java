//CSC 370 Project Music Group
//This project will ideally be able to generate and classify nes music
//When the program is ran the user is promoted to either choose to generate or classify their nes music
//When classify is chosen, the user must upload a picture of a the notes generated via audacity
//Afterword, the program will read the file and return the genre that is guesses the music belongs to
//When the generate option is chosen the user is prompted with the ability to choose the project rate and the genre of the music to be created
//Afterword, the generated music will be played along with the data you chose will be displayed

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TestProgram {

	public JFileChooser fc = new JFileChooser();

	public JFrame frame = new JFrame();
	public JFrame classifyFrame = new JFrame();
	public JFrame generateFrame = new JFrame();
	public JFrame generateFinalFrame = new JFrame();
	
	public JPanel panel = new JPanel();
	public JPanel generatePanel = new JPanel();
	public JPanel generateSouthPanel = new JPanel();
	public JPanel generateFinalPanel = new JPanel();
	
	public JLabel generateLabel = new JLabel("What specifications do you have for the music that will be generated?");
	public JLabel tempoLabel = new JLabel("    What do you want the project rate (in Hz) of the music to be?");
	public JLabel finalTempo = new JLabel();
	public JLabel finalGenre = new JLabel();
	
	public JTextField tempo = new JTextField("44100");
	
	public JButton classify = new JButton("Classify Music");
	public JButton generate = new JButton("Generate Music");
	public JButton load = new JButton("Load Generated Music");
	
	String[] genre = new String[] { "Select a Genre", "Horror", "Action", "Adventure" };
	JComboBox<String> genreList = new JComboBox<>(genre);
	
	int xCord;
	int yCord;

	TestProgram() {
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.add(panel);
		panel.add(classify);
		panel.add(generate);

		classify.addActionListener(new ButtonListener());
		generate.addActionListener(new ButtonListener());

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			System.out.println(s);

			if (s == "Classify Music") {
				frame.dispose();
				BufferedImage img = null;
				int result = fc.showOpenDialog(frame);
				try {
					System.out.println(fc.getSelectedFile());
					File f = fc.getSelectedFile();
					img = ImageIO.read(f);
					xCord = img.getWidth();
					System.out.println(xCord);
					yCord = img.getHeight();
					System.out.println(yCord);
				} catch (IOException exception) {
					System.out.println("File not found");
				}
				JComponent picture = new GraphComponent(img);
				classifyFrame.setSize(xCord + 16, yCord + 39);
				classifyFrame.setVisible(true);
				classifyFrame.add(picture);
				
				classifyFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				
			}
			if (s == "Generate Music") {
				frame.dispose();
				generateFrame.setSize(600, 150);
				generateFrame.setVisible(true);
				BorderLayout b = new BorderLayout();
				generatePanel.setLayout(b);
				generateFrame.add(generatePanel);
				generatePanel.add(generateSouthPanel, b.CENTER);
				generatePanel.add(generateLabel, b.NORTH);
				generatePanel.add(genreList, b.WEST);
				generateSouthPanel.add(tempoLabel);
				generateSouthPanel.add(tempo);
				generatePanel.add(load, b.SOUTH);
				
				load.addActionListener(new ButtonListener());
				
				generateFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
			if(s == "Load Generated Music") {
				generateFrame.dispose();
				
				String genre = "Not implemented yet";
				finalGenre.setText("The genre of the music generated is " + genre);
				String speed = tempo.getText();
				finalTempo.setText("The project rate of the music generate is " + speed);
				
				generateFinalFrame.setSize(600, 800);
				generateFinalFrame.setVisible(true);
				generateFinalFrame.add(generateFinalPanel);
				generateFinalPanel.add(finalTempo);
				generateFinalPanel.add(finalGenre);
				
				generateFinalFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
		}
	}
	public class GraphComponent extends JComponent {
		private BufferedImage y;

		private GraphComponent(BufferedImage x) {
			y = x;
		}

		public void paintComponent(Graphics g) {
			g.setColor(Color.BLACK);
			g.drawImage(y, 0, 0, xCord, yCord, Color.BLACK, null);
		}
	}

	public static void main(String[] args) {
		TestProgram t = new TestProgram();
	}
}
