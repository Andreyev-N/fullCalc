package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Server.Logger.Logger;
import Server.dataBase.WorkTable;

public class main_server{
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8081);
		WorkTable UBase = new WorkTable();
		Logger logger = new Logger();
		
		while (true) {
			System.out.println("waiting...");
			Socket socket = server.accept();
			Thread t = new Thread(new Exec(socket, UBase, logger));
			t.start();
		}
	}
}
