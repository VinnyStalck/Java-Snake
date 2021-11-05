import java.awt.Color;

import javax.swing.JFrame;

public class App {
	public static final int frameWidth = 905;
	public static final int frameHeight = 700;
	
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame();
		GamePanel gamePanel = new GamePanel();

		frame.setBounds(10, 10, frameWidth, frameHeight);
		frame.setBackground(Color.WHITE );
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(gamePanel);
	}
}
