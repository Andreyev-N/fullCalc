package Server.CalcEngine;
class Operation {
	private float number; 
	private String sign; // math operation symbol after number 
	private int priority; //max value is max priority
	Operation(float num, String sign, int priority) {
		this.number = num;
		this.sign = sign;
		this.priority = priority;
	}
	public float getNum(){
		return number;
	}
	public int getPriority(){
		return priority;
	}
	
	public String getSign() {
		return sign;
	}
	
	public void replaceObj(Operation nextObj){
		this.number = nextObj.getNum();
		this.sign = nextObj.getSign();
		this.priority = nextObj.getPriority();
	}
	public float Calculate(Operation nextObj) {
		float a = number;
		float b = nextObj.getNum();
		float c = 0;
		if(sign.equals("+")) {
			c = a+b;
		}
		else if(sign.equals("-")){
			c = a-b;
		}
		else if(sign.equals("x")){
			c = a*b;
		}
		else if(sign.equals("/")){
			c = a/b;
		}
		else if(sign.equals("=")){
			return c;
		}
		else
		{
			System.out.println("ERROR");
			System.exit(1);
		}
		this.sign = nextObj.getSign();
		this.priority = nextObj.getPriority();
		number = c;
		return c;
	}


	
	
}
