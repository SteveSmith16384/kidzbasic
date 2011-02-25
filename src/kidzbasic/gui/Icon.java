package kidzbasic.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Icon extends JComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Commands
	public static final int COMPILE = 1;
	
	//private MainWindow main;
	private int cmd;
	private String text;
	
	public Icon(MainWindow m, int _cmd, String _text) {
		super();
		
		cmd = _cmd;
		text =_text;
		
		this.setPreferredSize(new Dimension(40, 40));
		
		this.addMouseListener(m);
	}
	
	
	public int getCmd() {
		return cmd;
	}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawString(text, 10, 10);
	}

}
