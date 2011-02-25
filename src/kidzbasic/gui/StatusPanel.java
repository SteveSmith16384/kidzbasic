package kidzbasic.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lbl = new JLabel("Welcome to KidzBasic!");
	
	public StatusPanel() {
		super();
		this.setLayout(new GridLayout(1, 1));
		this.add(lbl);
	}

}
