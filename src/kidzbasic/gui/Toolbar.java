package kidzbasic.gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class Toolbar extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Toolbar(MainWindow m) {
		super();
		
		this.setLayout(new FlowLayout());
		
		this.add(new Icon(m, Icon.COMPILE, "Run"));
	}

}
