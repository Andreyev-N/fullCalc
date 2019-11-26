package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import Client.UserIf.EntranceIf;

public class main_client {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("127.0.0.1", 8081);
		
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();

		PrintWriter writer = new PrintWriter(os);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		EntranceIf In = new EntranceIf(writer, reader);
		In.openWindow();
	}
}
