package Server.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;

public class Logger {

	private final File logDir = new File("C:\\Javaprojects\\FullCalc\\logs");
	private File mainFile = new File(logDir.getAbsolutePath() + "\\log.txt");
	private int lineIndex = 0;
	private int fileIndex = 1;
	private Thread t = new Thread(new copyThread());

	public Logger() {
		logDir.mkdir();
		mainFile.delete();
		try {
			mainFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void log(String name, String oper) {
		try {
			t.join();
			Date time = new Date();
			Writer fWrite = new FileWriter(mainFile, true);
			fWrite.write("Date:[" + time.toString() + "]; name: " + name + "; operation: " + oper + ";\n");
			fWrite.flush();
			lineIndex++;

			if (lineIndex == 5) {
				fWrite.close();
				lineIndex = 0;
				t = new Thread(new copyThread());
				t.start();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class copyThread extends Thread {

		@Override
		public void run() {
			File newFile = new File(logDir.getAbsolutePath() + "\\log" + fileIndex + ".txt");
			try {
				Files.copy(mainFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				Writer remover = new FileWriter(mainFile);
				remover.write("");
				remover.close();
				fileIndex++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
