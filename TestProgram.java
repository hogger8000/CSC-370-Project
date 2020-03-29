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
	public JFrame newFrame = new JFrame();
	public JPanel panel = new JPanel();
	public JPanel newPanel = new JPanel();
	public JLabel label = new JLabel();
	public JButton button = new JButton("Load");

	TestProgram() {
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.add(panel);
		panel.add(button);

		button.addActionListener(new ButtonListener());

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();

			if (s == "Load") {
				frame.dispose();
				BufferedImage img = null;
				int result = fc.showOpenDialog(frame);
				try {
					System.out.println(fc.getSelectedFile());
					File f = fc.getSelectedFile();
					img = ImageIO.read(f);
				} catch (IOException exception) {
					System.out.println("File not found");
				}
				JComponent picture = new GraphComponent(img);
				newFrame.setSize(1600, 900);
				newFrame.setVisible(true);
				//newFrame.add(newPanel);
				//newPanel.add(picture);
				newFrame.add(picture);
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
			g.drawImage(y, 0, 0, 800, 600, Color.BLACK, null);
		}
	}

	public static void main(String[] args) {
		TestProgram t = new TestProgram();
	}
}
