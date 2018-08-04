package savingModule;
public class SaveItem {
	String valueString="-1";
	public SaveItem() {}
	public SaveItem(String value) {
	    this.valueString=value;
	}
	public SaveItem(double value) {
		this.valueString=""+value;
	}
	public SaveItem(boolean value) {
	    this.valueString=""+value;
	}
	public boolean getValueBoolean() {
	    return Boolean.valueOf(valueString);
	}
	public SaveItem(int value) {
	    this.valueString=""+value;
    }
	public String getValueString() {
		return valueString;
	}
	public void setValueString(String valueString) {
		this.valueString = valueString;
	}
	public int getValueInt() {
		return Integer.valueOf(valueString);
	}
	public void setValueInt(int value) {
        this.valueString=""+value;
	}
	public double getValueDouble() {
		return Double.valueOf(valueString);
	}
	public void setValueDouble(double value) {
	      this.valueString=""+value;
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
