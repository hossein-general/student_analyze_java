package globals;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    EducationGroup parentEGP;
    String name;
    EducationGrade egd;
    List gradeBasePrerequisite = new ArrayList();

    public Lesson(EducationGroup parentEGP, String name, EducationGrade egd, Lesson... gradeBasePrerequisite) {
        this.parentEGP = parentEGP;
        this.name = name;
        this.egd = egd;
        
        for (int i = 0; i < gradeBasePrerequisite.length; i++) {
            this.gradeBasePrerequisite.add(gradeBasePrerequisite[i]);
        }
    }
}
