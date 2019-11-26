package Client.UserIf;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

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
	

	public EntranceIf(PrintWriter writer, BufferedReader reader) throws IOException {
		super(UBase);
		super.writer = writer;
		super.reader = reader;
		
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
		

		InButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				writer.println("sign in");
				writer.println(loginField.getText() + "%%" + String.valueOf(passwordField.getPassword()));
				writer.flush();
				try {
					Thread.sleep(1000);
					String line = reader.readLine();
					if (line.equals("success")) {
						CalcInterface Calc = new CalcInterface();
						Calc.openCalc(loginField.getText());
						InFrame.setVisible(false);
					} else {
						answerLabel.setText("not found");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
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
