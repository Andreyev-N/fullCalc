package Client.CasheModul;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

public class CasheMod {

	private PrintWriter writer;
	private BufferedReader reader;
	private final int lev1Size;
	private LinkedList<CacheElem> level1 = new LinkedList<CacheElem>();
	private HashMap<String, String> level2 = new HashMap<String, String>();
	private int lev1Len = 0;

	public CasheMod(PrintWriter writer, BufferedReader reader) {
		this.writer = writer;
		this.reader = reader;

		lev1Size = 11;
	}

	public CasheMod(int lev1Size, PrintWriter writer, BufferedReader reader) {
		this.lev1Size = lev1Size + 1;
		this.writer = writer;
		this.reader = reader;
	}

	public String newItem(String value) {
		if (!findLev1(value) || !findLev2(value)) {
			writer.println(value);
			writer.flush();
			String res = new String();
			try {
				res = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			addFirst(value, res);
		}
		return level1.getFirst().getRes();
	}

	private void addLev2(String key, String res) {
		level2.put(key, res); // in map value = res, key = key
	}

	private boolean findLev1(String value) {

		Iterator<CacheElem> i = level1.iterator();
		while (i.hasNext()) {
			CacheElem tempEl = i.next();
			if (tempEl.getValue().equals(value)) {
				addFirst(value, tempEl.getRes());
				level1.remove(tempEl);
				return true;
			}
		}
		return false;
	}

	private boolean findLev2(String value) {
		Iterator<Entry<String, String>> it = level2.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> temp = it.next();
			if (temp.getKey().equals(value)) {
				addFirst(value, temp.getValue());
				level2.remove(temp);
				return true;
			}
		}
		return false;
	}

	private void addFirst(String value, String res) {
		level1.add(0, new CacheElem(value, res));
		lev1Len++;
		if (lev1Len >= lev1Size) {
			String lastVal = new String(level1.getLast().getValue());
			String lastRes = new String(level1.getLast().getRes());
			addLev2(lastVal, lastRes);
			level1.removeLast();
		}
	}

	public void getLev1() {
		// System.out.println(level1.get());

		Iterator<CacheElem> i = level1.iterator();

		while (i.hasNext()) {
			CacheElem a = i.next();
			System.out.println(a);
		}
	}

	public void getLev2() {
		Iterator<Entry<String, String>> it = level2.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> pair = it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
		}
	}
}
