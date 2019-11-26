package Server.CalcEngine;


public class SmartCalc {
	
	private String[] args = new String[128];
	private static Operation[] Opers = new Operation[64]; // main array of
															// Objects
	private int lenOperArr = 0;
	private int lenTask;

	public SmartCalc(String data[]) {
		lenTask = data.length; // quantity of arguments
		for (int i = 0; i < lenTask; i++) {
			args[i] = data[i];
		}

	}

	private void setOpersArr() {
		int priority = 0;
		float num = 0; // last number, what should be written in Oper Array
		boolean lastLoopNum = false; // true, if in last loop was a number
		for (int i = 0; i < lenTask; i++) {
			try {
				num = Float.parseFloat(args[i]);
				lastLoopNum = true;
			} catch (NumberFormatException e) {
				if (args[i].equals("+") || args[i].equals("-")) {
					if (lastLoopNum) {
						Operation Temp = new Operation(num, args[i], priority);
						Opers[lenOperArr++] = Temp;
					}
				} else if (args[i].equals("x") || args[i].equals("/")) {
					if (lastLoopNum) {
						Operation Temp = new Operation(num, args[i], priority + 1);
						Opers[lenOperArr++] = Temp;
					}
					lastLoopNum = false;
				} else if (args[i].equals("(")) {
					priority++;
				} else if (args[i].equals(")")) {
					if (priority != 0) {
						priority--;
					}
				} else {
					System.out.println("incorrect");
					System.exit(1);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("no arguments");
				System.exit(1);
			}

			if (i == lenTask - 1 - priority && lastLoopNum) { // for last number
																// of task
				Operation Temp = new Operation(num, "=", 0);
				Opers[lenOperArr++] = Temp;
			}
		}
	}

	public float getResult() {
		setOpersArr();
		int localPriority = 0;
		for (int i = 0; i < lenOperArr; i++) {
			if (Opers[i].getPriority() > localPriority) {
				localPriority = Opers[i].getPriority(); // find max priority
														// oper
			}
		}
		for (; localPriority >= 0; localPriority--) {
			for (int i = 0; i < lenOperArr; i++) {
				if (Opers[i].getPriority() == localPriority) {
					Opers[i].Calculate(Opers[i + 1]);
					//if(lenOperArr == 1){
						
					//}
					for (int j = i + 1; j < lenOperArr - 1; j++) {
						Opers[j].replaceObj(Opers[j + 1]);
					}
					lenOperArr--;
					i--;
				}
			}
		}
		return Opers[0].getNum();
		
	}
	
}
