package qt.tools.ini;

/**
 * Created by QuiTee on 2018/9/30.
 */
public class NoteLine {
    String line;
    String type;

    public NoteLine(String type,String line) {
        this.line = line;
        this.type = type;
    }

    @Override
    public String toString() {
        return type+line;
    }
}
