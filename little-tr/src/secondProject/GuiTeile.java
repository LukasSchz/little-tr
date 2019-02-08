package secondProject;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GuiTeile extends JFrame{

	String s = "Eingabe hier!";
	int counter = 0;
	
	JLabel header;
	JTextField eingabe;
	JButton istGleich;
	JButton klammerAuf;
	JButton klammerZu;
	JButton plus;
	JButton minus;
	JButton mal;
	JButton geteilt;
	JButton nullZahl;
	JButton eins;
	JButton zwei;
	JButton drei;
	JButton vier;
	JButton fuenf;
	JButton sechs;
	JButton sieben;
	JButton acht;
	JButton neun;
	JPanel zentrum;
	JPanel zentrumbtn;
	JPanel zentrumLinks;
	JPanel zentrumRechts;
	
	public GuiTeile() {
		
		this.setTitle("Taschenrechner");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(950, 300);
		createWidget();
		addWidget();
		setupInteraction();
		pack();
	}
	
	public void createWidget() {
		
		header = new JLabel("Taschenrechner");
		header.setFont(header.getFont().deriveFont(Font.BOLD + Font.ITALIC, 30));
		header.setHorizontalAlignment(SwingConstants.CENTER);
		header.setBorder(new EmptyBorder(5,5,5,5));
		
		eingabe = new JTextField(s);
		eingabe.setBorder(new EmptyBorder(5,5,5,5));
		eingabe.setHorizontalAlignment(JTextField.CENTER);
		
		istGleich = new JButton("=");
		klammerAuf = new JButton("(");
		klammerZu = new JButton(")");
		plus = new JButton("+");
		minus = new JButton("-");
		mal = new JButton("*");
		geteilt = new JButton("/");
		nullZahl = new JButton("0");
		eins = new JButton("1");
		zwei = new JButton("2");
		drei = new JButton("3");
		vier = new JButton("4");
		fuenf = new JButton("5");
		sechs = new JButton("6");
		sieben = new JButton("7");
		acht = new JButton("8");
		neun = new JButton("9");
		
		zentrum = new JPanel(new BorderLayout(5,5));
		
		zentrumbtn = new JPanel(new GridLayout(0,4,5,5));
		
		zentrumLinks = new JPanel(new GridLayout());
		
		zentrumRechts = new JPanel(new GridLayout());
	}
	
	public void addWidget() {
		
		getContentPane().setLayout(new BorderLayout(5,5));
		getContentPane().add(BorderLayout.NORTH, header);
		getContentPane().add(BorderLayout.CENTER, zentrum);
		getContentPane().add(BorderLayout.SOUTH, istGleich);
		((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		zentrum.add(BorderLayout.NORTH, eingabe);
		zentrum.add(BorderLayout.CENTER, zentrumbtn);
		
		zentrumbtn.add(sieben);
		zentrumbtn.add(acht);
		zentrumbtn.add(neun);
		zentrumbtn.add(plus);
		zentrumbtn.add(vier);
		zentrumbtn.add(fuenf);
		zentrumbtn.add(sechs);
		zentrumbtn.add(minus);
		zentrumbtn.add(eins);
		zentrumbtn.add(zwei);
		zentrumbtn.add(drei);
		zentrumbtn.add(mal);
		zentrumbtn.add(klammerAuf);
		zentrumbtn.add(nullZahl);
		zentrumbtn.add(klammerZu);
		zentrumbtn.add(geteilt);
	}
	
	public void setupInteraction() {
		eins.addActionListener(new AddActionListener("1"));
		zwei.addActionListener(new AddActionListener("2"));
		drei.addActionListener(new AddActionListener("3"));
		vier.addActionListener(new AddActionListener("4"));
		fuenf.addActionListener(new AddActionListener("5"));
		sechs.addActionListener(new AddActionListener("6"));
		sieben.addActionListener(new AddActionListener("7"));
		acht.addActionListener(new AddActionListener("8"));
		neun.addActionListener(new AddActionListener("9"));
		nullZahl.addActionListener(new AddActionListener("0"));
		klammerAuf.addActionListener(new AddActionListener("("));
		klammerZu.addActionListener(new AddActionListener(")"));
		plus.addActionListener(new AddActionListener("+"));
		minus.addActionListener(new AddActionListener("-"));
		mal.addActionListener(new AddActionListener("*"));
		geteilt.addActionListener(new AddActionListener("/"));
		istGleich.addActionListener(new ErgebnisActionListener());
	}
	
	private class AddActionListener implements ActionListener{
		
		String zwischenEingabe;
		
		public AddActionListener(String zwischenEingabe) {
			this.zwischenEingabe = zwischenEingabe;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String inhalt = eingabe.getText();
			char[] test = inhalt.toCharArray();
			if((Character.isDigit(test[0])) && counter == 0) {
				eingabe.setText(inhalt + zwischenEingabe);
				counter = 0;
			} else {
				eingabe.setText(zwischenEingabe);
				counter = 0;
			}
		}		
	}
	
	private class ErgebnisActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Rechner rechner = new Rechner(eingabe.getText());
			counter = 1;
			eingabe.setText(rechner.toString());
		}
	}
}
