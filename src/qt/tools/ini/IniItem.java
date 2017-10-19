package qt.tools.ini;

public class IniItem {
	private String key;
	private String value;
	private boolean isKV;
	
	public IniItem(String key,String value) {
		this.key = key;
		this.value = value;
		this.isKV = true;
	}
	public IniItem(String key) {
		this.key = key;
		this.value = null;
		this.isKV = false;
	}
	
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isKV() {
		return isKV;
	}
	public void setKV(boolean isKV) {
		this.isKV = isKV;
	}
	
	@Override
	public String toString() {
		return isKV?(key+"="+value):key;
	}
}
