package loginSystem;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class EntranceIf extends RegIf {

	private JFrame InFrame = new JFrame("calculator");

	private Container mainPane = InFrame.getContentPane();
	private Container LoginPane = new JPanel(new GridLayout());
	private Container PasswordPane = new JPanel(new GridLayout());
	private Container ButtonPane = new JPanel(new FlowLayout());

	private JTextField loginField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JLabel answerLabel = new JLabel();

	private JButton InButton = new JButton("Sign in");
	private JButton RegButton = new JButton("Sign up");

	public EntranceIf() throws IOException {
		super(UBase);

		InFrame.setLocation(300, 200);
		InFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPane.setLayout(new GridLayout(4, 1));

		LoginPane.add(new JLabel("login:"));
		LoginPane.add(loginField);
		PasswordPane.add(new JLabel("password:"));
		PasswordPane.add(passwordField);
		ButtonPane.add(InButton);
		ButtonPane.add(RegButton);

		mainPane.add(LoginPane);
		mainPane.add(PasswordPane);
		mainPane.add(answerLabel);
		mainPane.add(ButtonPane);
		
		passwordField.setEchoChar('*');
	}

	public void openWindow() {
		InFrame.pack();
		InFrame.setVisible(true);

		Hear();
	}

	private void Hear() {
		
		/*InFrame.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
					System.out.println("da");
					CalcInterface Calc = new CalcInterface();
					Calc.openCalc();
					InFrame.setVisible(false);
				}
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
		});*/

		loginField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		passwordField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		InButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (UBase.findElement(loginField.getText(), String.valueOf(passwordField.getPassword()))) {
					CalcInterface Calc = new CalcInterface(loginField.getText());
					Calc.openCalc();
					InFrame.setVisible(false);

				} else {
					answerLabel.setText("not found");
				}
			}

		});

		RegButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openRegWindow();
			}

		});

	}
}
