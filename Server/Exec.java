package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import Server.CalcEngine.SmartCalc;
import Server.Logger.Logger;
import Server.dataBase.WorkTable;

public class Exec implements Runnable {

	private Socket socket;
	private WorkTable UBase;
	private Logger logger;

	PrintWriter writer;// = new PrintWriter(os);
	BufferedReader reader;// = new BufferedReader(new InputStreamReader(is));

	public Exec(Socket socket, WorkTable UserBase, Logger logger) throws IOException {
		super();
		this.socket = socket;
		this.UBase = UserBase;
		this.logger = logger;

		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();

		writer = new PrintWriter(os);
		reader = new BufferedReader(new InputStreamReader(is));
	}

	@Override
	public void run() {
		System.out.println("connected!");
		try {

			while (true) {
				while (reader.ready()) {
					Thread.sleep(1000);
				}

				String line = reader.readLine();
				if (line.equals("sign in")) {
					Entrance();
				} else if (line.equals("reg")) {
					Registration();
				} else if (line.equals("exit")) {
					break;
				}
			}

			while (true) {
				break;
			}
			writer.close();
			reader.close();
			socket.close();

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void Entrance() throws IOException {
		String line = reader.readLine();
		String[] ArrLine = new String[10];
		ArrLine = line.split("%%");
		if (ArrLine.length == 1) {
			String[] tempArr = new String[2];
			tempArr[0] = new String(ArrLine[0]);
			tempArr[1] = new String("");
			ArrLine = tempArr;
		}
		if (UBase.findElement(ArrLine[0], ArrLine[1])) {
			writer.println("success");
			writer.flush();
			Calculating(ArrLine[0]);
		} else {
			writer.println("not found");
			writer.flush();
		}
	}

	private void Registration() throws IOException {
		String line = reader.readLine();
		String ArrLine[] = line.split("%%");
		if (ArrLine.length == 1) {
			String[] tempArr = new String[2];
			tempArr[0] = new String(ArrLine[0]);
			tempArr[1] = new String("");
			ArrLine = tempArr;
		}

		UBase.addNewElem(ArrLine[0], ArrLine[1]);
	}

	private void Calculating(String login) throws IOException {

		while (true) {
			String line = reader.readLine();
			SmartCalc calc = new SmartCalc(line.split(" "));
			writer.println(calc.getResult());
			writer.flush();
			logger.log(login, line);
		}
	}
}
