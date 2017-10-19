package qt.tools.ini;

import java.util.HashMap;

public class IniSection {
	private String name;
	protected HashMap<String, IniItem> items = new HashMap<>();
	public IniSection(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IniItem get(String key){
		return items.get(key);
	}
	public void put(String key,String value){
		items.put(key, new IniItem(key, value));
	}
	public void put(String key){
		items.put(key, new IniItem(key));
	}
	public HashMap<String, IniItem> getItems() {
		return items;
	}
}
