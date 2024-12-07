package ui;

import java.util.ArrayList;
import java.util.List;

public class CurrentMenueDir {
    List menues = new ArrayList();
    String curDir = "";

    public String getCurrentDir() {
        curDir = "Dir: \"/";
        for (int i = 0; i < menues.size(); i++) {
            curDir += ("(" + menues.get(i) + ")");
            if (i != menues.size()-1) 
                curDir += "/";
        }
        curDir += "\"";
        return curDir;
    }

    public void removeTop() {
        menues.remove((menues.size()-1));
    }

    public void addTop(String menueName) {
        menues.add(menueName);
    }
    
}
