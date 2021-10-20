import java.awt.Color;

import javax.swing.JFrame;

public class App {
	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame();
		GamePanel gamePanel = new GamePanel();

		frame.setBounds(10, 10, 905, 700);
		frame.setBackground(Color.WHITE );
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(gamePanel);
	}
}
