package breakbricks;

import javax.swing.JFrame;

public class Main extends JFrame {

	public static void main(String[] args) {
		JFrame obj=new JFrame();
		Gameplay gameplay=new Gameplay();
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Breakout ball");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(3);
		obj.add(gameplay);
		
	}

}
