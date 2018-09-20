
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class NidoGUI extends JFrame implements ActionListener, WindowListener {

	private JTextArea output;	
	private JTextField textFieldNumeroLettino;	
	private JButton erogaButton;
		
	private Nido nido;
	
	
	public NidoGUI(Nido nido){
		super("Nido");
		this.nido = nido;
		
	
		output = new JTextArea();
		output.setEditable(false);
		output.setBackground(Color.LIGHT_GRAY);
		refreshOutput();

		JScrollPane scroll = new JScrollPane(output);
		add(scroll, BorderLayout.CENTER);
				
		JPanel lettiniOccupatiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lettiniOccupatiPanel.setBorder(new TitledBorder("Selezionare il numero di lettino di cui si vuole avere informazioni"));		
		lettiniOccupatiPanel.add(new JLabel("Numero lettino "));
		textFieldNumeroLettino = new JTextField(15);
		
		lettiniOccupatiPanel.add(textFieldNumeroLettino);
		erogaButton = new JButton("Visualizza");	
		erogaButton.addActionListener(this);
		
		lettiniOccupatiPanel.add(erogaButton);		
		add(lettiniOccupatiPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowListener(this);		
	}
	
	private void refreshOutput(){
		output.setText("");
		Iterator<Integer> i = nido.getLettiniOccupati().iterator();
		while (i.hasNext()) {
			output.append(i.next() + "\n");
		}
		
		output.append("----------------------\n\n");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		refreshOutput();
		String numeroLettinoStringa = textFieldNumeroLettino.getText();		
		int numeroLettino = Integer.parseInt(numeroLettinoStringa.trim());
		Bambino bimbo = nido.getBambino(numeroLettino);
			if(bimbo != null){
				output.append(bimbo.toString());
			} else {
				output.append("Lettino non occupato");
			}
	}	
	
	public void windowClosing(WindowEvent e) {
		int value = JOptionPane.showConfirmDialog(this, "Vuoi uscire?",
				"Uscita", JOptionPane.YES_NO_OPTION);
		if (value == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}	
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
		
	
	public static void main(String a[]) throws Exception{
		Nido nido = new Nido();
		try {
			nido.aggiungiBambino(1, new Bambino(new Braccialetto(23), 1.5, 48, 7));
			nido.aggiungiBambino(2, new Bambino(new Braccialetto(26), 2.5, 49, 5 ));
		}
		catch (OperazioneNonConsentitaException onc){}
		
		NidoGUI gui = new NidoGUI(nido);
		gui.setSize(600, 300);
		gui.setVisible(true);
	}

}