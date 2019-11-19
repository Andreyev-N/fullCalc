package CasheModul;

public class CacheElem {
	String value = new String();
	String res = new String();
	
	public CacheElem(String value, String res) {
		super();
		this.value = value;
		this.res = res;
	}
	
	public CacheElem() {
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}

	@Override
	public String toString() {
		return "CacheElem [value=" + value + ", res=" + res + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((res == null) ? 0 : res.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CacheElem other = (CacheElem) obj;
		if (res == null) {
			if (other.res != null)
				return false;
		} else if (!res.equals(other.res))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
}
