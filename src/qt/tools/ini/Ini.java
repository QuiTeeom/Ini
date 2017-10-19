package qt.tools.ini;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Ini extends IniSection{
	private File ini;
	private HashMap<String, IniSection> sections = new HashMap<>();
	
	public Ini(File ini) {
		super(ini.getName());
		this.ini = ini;
		init();
	}
	
	private void init(){
		try {
			IniSection currentSection = null;
			
			BufferedReader reader = new BufferedReader(new FileReader(ini));
			String line;
			while((line = reader.readLine())!=null){
				line = line.trim();
				if(line.length()>0&&!line.startsWith("#")){
					if(line.startsWith("[")&&line.endsWith("]")){
						String sectionName = line.substring(1,line.length()-1);
						currentSection = new IniSection(sectionName);
						sections.put(sectionName, currentSection);
					}else{
						String [] kv = line.split("=", 2);
						if(kv.length==2){
							if(currentSection!=null){
								currentSection.put(kv[0].trim(), kv[1].trim());
							}else{
								put(kv[0].trim(), kv[1].trim());
							}
						}else if(kv.length==1){
							if(currentSection!=null){
								currentSection.put(kv[0].trim());
							}else{
								put(kv[0].trim());
							}
						}
					}
				}
			}
			reader.close();
		} catch (Exception e) {

		}
	}
	
	public IniSection getSection(String sectionName){
		return sections.get(sectionName);
	}
	
	public void put(String sectionName,IniSection section){
		sections.put(sectionName, section);
	}
	
	public void store(File file){
		
		try {
			PrintWriter writer = new PrintWriter(file);
			Iterator<Entry<String, IniItem>> itemIt =  items.entrySet().iterator();
			while(itemIt.hasNext()){
				writer.println(itemIt.next());
			}
			Iterator<Entry<String, IniSection>> sectionsIt =  sections.entrySet().iterator();
			while(sectionsIt.hasNext()){
				IniSection section = sectionsIt.next().getValue();
				writer.println("["+section.getName()+"]");
				Iterator<Entry<String, IniItem>> sectionitemIt =  section.getItems().entrySet().iterator();
				while(sectionitemIt.hasNext()){
					writer.println(sectionitemIt.next().getValue());
				}
				writer.println("");
			}
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void store(){
		store(ini);
	}
	
	public static void main(String[] args) {
		Ini ini = new Ini(new File("T:/QtInstaller/config.ini"));
		System.out.println(ini.getSection("test").get("aa").getValue());
		ini.getSection("test").put("bb","T:/sda/ad");
		System.out.println(ini.getSection("test").get("bb").getValue());
		ini.store();
	}
}
