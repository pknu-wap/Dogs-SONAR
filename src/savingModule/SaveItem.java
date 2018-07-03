package savingModule;
public class SaveItem {
	enum Type{
		INT,DOUBLE,STRING;
	}
	public String key;
	Type type;
	String valueString;
	int valueInt;
	double valueDouble;
	public SaveItem(String key,String value) {
		this.key=key;
		this.valueString=value;
		type=Type.STRING;
	}
	public SaveItem(String key,double value) {
		this.key=key;
		this.valueDouble=value;
		type=Type.DOUBLE;
	}
	public SaveItem(String key,int value) {
		this.key=key;
		this.valueInt=value;
		type=Type.INT;
	}
	public String getKey() {
		return key;
	}
	public String getValueString() {
		return valueString;
	}
	public void setValueString(String valueString) {
		this.valueString = valueString;
	}
	public int getValueInt() {
		return valueInt;
	}
	public void setValueInt(int valueInt) {
		this.valueInt = valueInt;
	}
	public double getValueDouble() {
		return valueDouble;
	}
	public void setValueDouble(double valueDouble) {
		this.valueDouble = valueDouble;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Type getType() {
		return type;
	}
	public String toString() {
		String ret="";
		ret+=key+" : ";
		switch(type) {
		case STRING:
			ret+=valueString;
			break;
		case INT:
			ret+=valueInt;
			break;
		case DOUBLE:
			ret+=valueDouble;
			break;
		}
		return ret; 
	}
}
