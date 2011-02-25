package kidzbasic.gui;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Toolbar toolbar;
	private StatusPanel status_panel;
	private TextArea text_area;
	
	public MainWindow() {
		super();
		
		toolbar = new Toolbar(this);
		status_panel = new StatusPanel();
		text_area = new TextArea();
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(toolbar, BorderLayout.NORTH);
		this.getContentPane().add(status_panel, BorderLayout.SOUTH);
	
		JScrollPane scroll = new JScrollPane(text_area);
		this.getContentPane().add(scroll, BorderLayout.CENTER);
		
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		if (evt.getComponent() instanceof Icon) {
			Icon ic = (Icon)evt.getComponent();
			if (ic.getCmd() == Icon.COMPILE) {
				// todo
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
