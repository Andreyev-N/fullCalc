package dataBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public abstract class WorkFile {

	private File file = new File("C:\\Javaprojects\\DataBase.txt");
	private Writer fWrite = new FileWriter(file, true);
	private BufferedReader fRead = new BufferedReader(new FileReader(file));

	protected Element[] Table = new Element[64];
	protected int TableLen = 0;

	public WorkFile() throws IOException {
		if (!file.exists()) {
			System.out.println("файл не найден");
			if (file.createNewFile()) {
				System.out.println("создан новый файл");
			}
		} else {
			System.out.println("файл найден");
		}
	}

	protected void addElem(Element elem) throws IOException {
		fWrite.write(Integer.toString(elem.getId()) + " ");
		fWrite.write(elem.getLogin() + " " + elem.getPassword() + "\n");
		fWrite.flush();

	}

	protected boolean findElemCrypted(String login, String password) {
		for (int i = 0; i < TableLen; i++) {
			if (login.equals(Table[i].getLogin())) {
				if (password.equals(Table[i].getPassword())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean setTable() throws IOException {

		String strEl = new String();
		int i = 0;
		while (true) {
			strEl = fRead.readLine();
			if (strEl == null) {
				break;
			}
			String[] ArrStrEl = strEl.split(" ");
			Element TempEl = new Element();
			try {
				TempEl.setId(Integer.parseInt(ArrStrEl[0]));
				TempEl.setLogin(ArrStrEl[1]);
				TempEl.setPassword(ArrStrEl[2]);
			} catch (NumberFormatException e) {
				return false;
			} catch (NullPointerException e) {
				return false;
			}catch (ArrayIndexOutOfBoundsException e){
				return false;
			}
			if (!findElemCrypted(TempEl.getLogin(), TempEl.getPassword())) {
				Table[i++] = TempEl;
				TableLen++;
			}
		}
		return true;
	}
}
