package qt.tools.ini;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by QuiTee on 2018/9/30.
 */
public class IniPrintWriter extends PrintWriter{

    public IniPrintWriter(File file) throws FileNotFoundException {
        super(file);
    }

    public void printNotes(List<NoteLine> noteLines){
        for(NoteLine noteLine:noteLines){
            println(noteLine);
        }
    }

    public void printItem(IniItem iniItem){
        printNotes(iniItem.getNoteLines());
        println(iniItem);
    }

    public void printSection(IniSection section){
        printNotes(section.getNotes());
        println(section);
        for(IniItem iniItem:section.getItems()){
            printItem(iniItem);
        }
        println();
    }

    public void println(int time){
        for (int i=0;i<time;i++){
            println();
        }
    }
}
