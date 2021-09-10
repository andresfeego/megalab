package pruebas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import visor.VisorPDF;

public class ventana extends JFrame {

	private JPanel contentPane;
private ventana esta;

	public ventana() {
		esta=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAbrirVisor = new JButton("abrir visor");
		btnAbrirVisor.setBounds(176, 96, 89, 23);
		contentPane.add(btnAbrirVisor);
		btnAbrirVisor.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent e) {
				NativeInterface.open();
				String  path = "C:/6342522/temp/001.pdf";
				(new VisorPDF(path, "fhlksadhfkjasdhfjkas")).execute();
				
				System.out.println("abriwendo");
				
			}
		});
		setVisible(true);
	}
}
