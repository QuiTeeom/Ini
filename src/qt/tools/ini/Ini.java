package qt.tools.ini;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

public class Ini extends IniSection{
	private File ini;
	private HashMap<String, IniSection> sections = new LinkedHashMap<>();
    public static final String type = "##quiteeom/ini\t";

    public Ini(File ini) {
		super(ini.getName());
		this.ini = ini;
		init();
	}
	
	private void init(){
		try {
			IniSection currentSection = null;
            List<NoteLine> currentNotes = new ArrayList<>();

			BufferedReader reader = new BufferedReader(new FileReader(ini));
			String line;

			while((line = reader.readLine())!=null){
				line = line.trim();
				if(line.length()>0){
                    System.out.println(line);
                    //注释
                    if(line.startsWith(type)&&currentSection==null){
                    }else if(line.startsWith("#")){
                        NoteLine noteLine = new NoteLine("#",line.substring(1));
                        currentNotes.add(noteLine);
                    }else {
				        //节
                        if(line.startsWith("[")&&line.endsWith("]")){
                            String sectionName = line.substring(1,line.length()-1);
                            currentSection = new IniSection(sectionName);
                            sections.put(sectionName, currentSection);
                            currentSection.notes = currentNotes;
                        }else{
                            //键值
                            String [] kv = line.split("=", 2);
                            if(kv.length==2){
                                if(currentSection!=null){
                                    currentSection.put(kv[0].trim(), kv[1].trim(),currentNotes);
                                }else{
                                    put(kv[0].trim(), kv[1].trim(),currentNotes);
                                }
                            }else if(kv.length==1){
                                if(currentSection!=null){
                                    currentSection.put(kv[0].trim(),currentNotes);
                                }else{
                                    put(kv[0].trim(),currentNotes);
                                }
                            }
                        }
                        currentNotes = new ArrayList<>();
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

	private List<NoteLine> genIniHeader(){
	    List<NoteLine> noteLines = new ArrayList<>();
	    noteLines.add(new NoteLine(type,"create\t"+new Date()));
	    return noteLines;
    }

	public void store(File file){
		
		try {
			IniPrintWriter writer = new IniPrintWriter(file);

			writer.printNotes(genIniHeader());
            writer.println(2);


			for(IniItem iniItem:new ArrayList<>(items.values())){
			    writer.printItem(iniItem);
            }

			for(IniSection section:new ArrayList<>(sections.values())){
			    writer.printSection(section);
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
		ini.store();
	}
}
