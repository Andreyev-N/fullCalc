package Server.dataBase;
import java.util.Arrays;

public class Element {
	
	private int id;
	private String login;
	private String password;

	private final char[] SYMBOLS;
	
	public Element(){
		final String ALL_SYM_RAND = new String("hs:q_tufC3pEW8n|Jcbl1},LNO7QMX-zjV;vRB60!g{/U5GdIKrkFP2He+aiToY4ymDSA.9Zxw");
		SYMBOLS = ALL_SYM_RAND.toCharArray();
	}
	
	public Element(int id, String login, String password) {
		 this.id=id;
		 this.login=login;
		 this.password = password;
		 final String ALL_SYM_RAND = new String("hs:q_tufC3pEW8n|Jcbl1},LNO7QMX-zjV;vRB60!g{/U5GdIKrkFP2He+aiToY4ymDSA.9Zxw");
			SYMBOLS = ALL_SYM_RAND.toCharArray();
	}

	public void cryptPassword(String openPass) {
		for(int i = 1; i < SYMBOLS.length ; i++){
			openPass = openPass.replace(SYMBOLS[i], SYMBOLS[i-1]);
		}
		password = openPass;
	}
	
	public String decrypt(){
		String openPass = new String(password);
		for(int i = SYMBOLS.length - 2 ; i >= 0; i--){
			openPass = openPass.replace(SYMBOLS[i], SYMBOLS[i+1]);
		}
		return openPass;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword(){
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return id + " " + login + " " + password;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Element other = (Element) obj;
		if (!Arrays.equals(SYMBOLS, other.SYMBOLS))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}	
}
