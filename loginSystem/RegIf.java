package loginSystem;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dataBase.WorkTable;
import dataBase.WorkFile;

public class RegIf {
	
	protected JFrame RegFrame = new JFrame("calculator");

	private Container mainPane = RegFrame.getContentPane();
	private Container LoginPane = new JPanel(new GridLayout());
	private Container PasswordPane = new JPanel(new GridLayout());
	private Container PasswordPane2 = new JPanel(new GridLayout());
	private Container ButtonPane = new JPanel(new FlowLayout());

	private JTextField loginField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JPasswordField passwordField2 = new JPasswordField();
	private JLabel answerLabel = new JLabel();
	private JButton RegButton = new JButton("Sign up");
	
	protected static WorkTable UBase;

	public RegIf(WorkTable UsersBase) throws IOException {
		this.UBase = UsersBase;
		UBase = new WorkTable();
		RegFrame.setLocation(500, 200);
		mainPane.setLayout(new GridLayout(5, 1));

		LoginPane.add(new JLabel("login:"));
		LoginPane.add(loginField);
		PasswordPane.add(new JLabel("password:"));
		PasswordPane.add(passwordField);
		PasswordPane2.add(new JLabel("repeat password:"));
		PasswordPane2.add(passwordField2);
		ButtonPane.add(RegButton);

		mainPane.add(LoginPane);
		mainPane.add(PasswordPane);
		mainPane.add(PasswordPane2);
		mainPane.add(answerLabel);
		mainPane.add(ButtonPane);
		
		passwordField.setEchoChar('*');
		passwordField2.setEchoChar('*');

	}



	public void openRegWindow() {

		RegFrame.pack();
		RegFrame.setVisible(true);
		Hear();
	}

	private void Hear() {
		
		
		RegButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String login = new String(loginField.getText());
					System.out.println(":" + String.valueOf(passwordField.getPassword()) + ":");
					System.out.println(":" + passwordField2.getPassword().toString() + ":");
					String password = new String(String.valueOf(passwordField.getPassword()));
					if (!login.equals("")) {
						
						if (password.equals(String.valueOf(passwordField2.getPassword()))) {
							UBase.addNewElem(login, password);
							answerLabel.setText("success");
							RegFrame.setVisible(false);
						} else {
							answerLabel.setText("passwords no match");
						}
					} else {
						answerLabel.setText("enter login");
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

				//
			}
		});
		
	

	}
	
}
