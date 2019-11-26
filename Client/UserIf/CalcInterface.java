package Client.UserIf;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Client.CasheModul.CasheMod;
import Server.Logger.Logger;

public class CalcInterface {
	
	private JFrame CalcFrame = new JFrame();
	private final JButton[] Buttons = new JButton[16];
	
	private Container mainPane = CalcFrame.getContentPane();
	private Container ButtonPane = new JPanel(new GridLayout(4, 5));
	private Container LabelPane = new JPanel(new GridLayout(2, 2));
	
	private final String[] BUTTON_NAMES = { "1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "x", "0", ".", "=", "/"};
	private JTextField calcLine = new JTextField();
	private JTextField resLine = new JTextField();
	
	private String calcLineString = new String();
	private String login = new String();
	
	private Logger Logger = new Logger();
	
	public CalcInterface() {;
		CalcFrame.setName(login);
		CalcFrame.setLocation(500, 200);
		CalcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPane.setLayout(new GridLayout(2, 1));

		setButtonPane(ButtonPane);
		setLabelPane(LabelPane);
		mainPane.add(LabelPane);
		mainPane.add(ButtonPane);
	}

	
	

	public void openCalc(String login) {
		this.login = login;
		CalcFrame.pack();
		CalcFrame.setVisible(true);
		HearButtons();
		HearTextField();
	}

	private void setButtonPane(Container ButtonPane) {

		for (int i = 0; i < 16; i++) {
			Buttons[i] = new JButton(BUTTON_NAMES[i]);
			ButtonPane.add(Buttons[i]);
		}

	}

	private void setLabelPane(Container LabelPane) {
		LabelPane.add(new JLabel(""));
		LabelPane.add(calcLine);
		LabelPane.add(new JLabel("result:"));
		LabelPane.add(resLine);
	}

	private void HearTextField() {
		calcLine.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLine.getText();
			}
			
		});
	}
	private void HearButtons() {  // cannot do listeners in loop
		Buttons[0].addActionListener(new ActionListener() { // button 1 
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(BUTTON_NAMES[0]);
				calcLine.setText(calcLineString);
			}
		});

		Buttons[1].addActionListener(new ActionListener() {   // 2
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(BUTTON_NAMES[1]);
				calcLine.setText(calcLineString);
			}
		});

		Buttons[2].addActionListener(new ActionListener() {  // 3
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(BUTTON_NAMES[2]);
				calcLine.setText(calcLineString);
			}
		});

		Buttons[3].addActionListener(new ActionListener() {   // +
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(" " + BUTTON_NAMES[3] + " ");
				calcLine.setText(calcLineString);
			}
		});

		Buttons[4].addActionListener(new ActionListener() {  // 4
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(BUTTON_NAMES[4]);
				calcLine.setText(calcLineString);
			}
		});

		Buttons[5].addActionListener(new ActionListener() { // 5
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(BUTTON_NAMES[5]);
				calcLine.setText(calcLineString);
			}
		});

		Buttons[6].addActionListener(new ActionListener() {   //6
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(BUTTON_NAMES[6]);
				calcLine.setText(calcLineString);
			}
		});

		Buttons[7].addActionListener(new ActionListener() {  // - 
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(" " + BUTTON_NAMES[7] + " "); 
				calcLine.setText(calcLineString);
			}
		});

		Buttons[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(BUTTON_NAMES[8]);
				calcLine.setText(calcLineString);
			}
		});

		Buttons[9].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(BUTTON_NAMES[9]);
				calcLine.setText(calcLineString);
			}
		});

		Buttons[10].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(BUTTON_NAMES[10]);
				calcLine.setText(calcLineString);
			}
		});

		Buttons[11].addActionListener(new ActionListener() {  // x
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(" " + BUTTON_NAMES[11] + " ");
				calcLine.setText(calcLineString);
			}
		});

		Buttons[12].addActionListener(new ActionListener() {  // 0 
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(BUTTON_NAMES[12]);
				calcLine.setText(calcLineString);
			}
		});

		Buttons[13].addActionListener(new ActionListener() {   // .
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(BUTTON_NAMES[13]);
				calcLine.setText(calcLineString);
			}
		});

		Buttons[14].addActionListener(new ActionListener() { // =
			@Override
			public void actionPerformed(ActionEvent e) {
				CasheMod Cache = new CasheMod(10);
				resLine.setText(Cache.newItem(calcLineString));
				Logger.log(login, calcLineString + " = " + resLine.getText());
				calcLineString = "";
			}
		});

		Buttons[15].addActionListener(new ActionListener() {  // /
			@Override
			public void actionPerformed(ActionEvent e) {
				calcLineString = calcLineString.concat(" " + BUTTON_NAMES[15] + " ");
				calcLine.setText(calcLineString);
			}
		});
	}
}
