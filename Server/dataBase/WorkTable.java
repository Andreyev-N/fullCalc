package Server.dataBase;
import java.io.IOException;
import java.util.Random;

public class WorkTable extends WorkFile {

	public WorkTable() throws IOException {
		super();
		setTable();
	}

	public void addNewElem(String login, String password) throws IOException {
		Element elem = new Element();
		Random random = new Random();
		
		elem.setLogin(login);
		elem.cryptPassword(password);
		elem.setId(random.nextInt());
		if(!findElement(login, password)){
		Table[TableLen++] = elem;
		addElem(elem);
		}
	}

	
	public boolean findElement(String login, String password) {
		for (int i = 0; i < TableLen; i++) {
			if (login.equals(Table[i].getLogin())) {
				if (password.equals(Table[i].decrypt())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void deleteElem() {

	}

	public void editElem() {

	}

}
