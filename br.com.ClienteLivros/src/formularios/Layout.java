package formularios;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Layout extends JPanel {
	
	BorderLayout layout;
	
	public Layout(){
		initialize();
	}
	
	
	private void initialize(){
		setPreferredSize(new
				Dimension(400, 300));
				layout = new
				BorderLayout(20, 20);
				setLayout(layout);
		
	}
	public BorderLayout getLayout(){
		
		return layout;
		
	}

}
