package savingModule;
public class SaveItem {
	public enum Type{
		INT,DOUBLE,STRING,BOOLEAN;
	}
	Type type;
	String valueString;
	int valueInt;
	double valueDouble;
	boolean valueBoolean;
	public SaveItem() {}
	public SaveItem(String value) {
		this.valueString=value;
		type=Type.STRING;
	}
	public SaveItem(double value) {
		this.valueDouble=value;
		type=Type.DOUBLE;
	}
	public SaveItem(boolean value) {
		this.valueBoolean=value;
		type=Type.BOOLEAN;
	}
	public boolean getValueBoolean(String valueString) {
	    return valueBoolean;
	}
	public SaveItem(int value) {
        this.valueInt=value;
        type=Type.INT;
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
	public void setType(Type type) {
		this.type = type;
	}
	public Type getType() {
		return type;
	}
}
