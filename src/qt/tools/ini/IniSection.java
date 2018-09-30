package qt.tools.ini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class IniSection {
	private String name;
	protected List<NoteLine> notes = new ArrayList<>();

	protected HashMap<String, IniItem> items = new LinkedHashMap<>();
	public IniSection(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public IniItem get(String key){
		return items.get(key);
	}


	public void put(String key,String value){
		put(key,value,new ArrayList<>());
	}

	public void put(String key,String value,List<NoteLine> notes){
		items.put(key, new IniItem(key, value,notes));
	}

	public void put(String key){
		put(key, new ArrayList<>());
	}

	public void put(String key,List<NoteLine> notes){
		items.put(key, new IniItem(key,notes));
	}

	public List<IniItem> getItems() {
		return new ArrayList<>(items.values());
	}

	public List<NoteLine> getNotes() {
		return notes;
	}

	@Override
	public String toString() {
		return "["+name+"]";
	}
}
