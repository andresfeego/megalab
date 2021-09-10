package visor;

import java.awt.BorderLayout;

import javax.swing.*;

import com.sun.javafx.tk.Toolkit;

import chrriis.dj.nativeswing.swtimpl.*;
import chrriis.dj.nativeswing.swtimpl.components.*;


public class Visor2 extends JFrame{

	
	
	public Visor2(){
		JPanel panelvisor=new JPanel();
		panelvisor.setLayout(new BorderLayout());
		final JWebBrowser browser = new JWebBrowser();
		browser.navigate("file:///C:/001.pdf");
		panelvisor.add(browser);
		
		add(panelvisor);
	}
	
	
	public static void main(String[] args) {
		NativeInterface.open();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				Visor2 nav=new Visor2();
				nav.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				nav.setVisible(true);
				nav.setSize(1600, 900);
				
				
			}
		});
		
	}
	
}
