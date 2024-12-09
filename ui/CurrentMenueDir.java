package ui;

import java.util.ArrayList;
import java.util.List;

// a class that represents the directory we are currently in
// یک کلاس برام نمایش مسیری که در منو ها اومدیم
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

    // حضف آخرین لایه
    public void removeTop() {
        menues.remove((menues.size()-1));
    }

    // اضافه کردن یک لایه
    public void addTop(String menueName) {
        menues.add(menueName);
    }
    
}
