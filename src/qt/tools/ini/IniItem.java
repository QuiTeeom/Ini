package qt.tools.ini;

import java.util.ArrayList;
import java.util.List;

public class IniItem {
	private String key;
	private String value;
	private boolean isKV;
	private List<NoteLine> noteLines = new ArrayList<>();

	public IniItem(String key,String value,List<NoteLine> noteLines) {
		this.key = key;
		this.value = value;
		this.isKV = true;
		this.noteLines = noteLines;
	}
	public IniItem(String key,List<NoteLine> noteLines) {
		this.key = key;
		this.value = null;
		this.isKV = false;
		this.noteLines = noteLines;
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

	public List<NoteLine> getNoteLines() {
		return noteLines;
	}
}
