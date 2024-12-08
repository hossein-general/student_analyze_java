package globals;

import java.util.ArrayList;
import java.util.List;

public class EducationGroup {
    EducationState parentES;
    String name;
    boolean directUse = true;
    List genericDependency = new ArrayList();

    public EducationGroup(EducationState parentES, String name, boolean directUse, EducationGroup... egps) {
        this.parentES = parentES;
        this.name = name;
        this.directUse = directUse;
        
        for (int i = 0; i < egps.length; i++) {
            this.genericDependency.add(egps[i]);
        }

    }

    public Lesson addLesson(String name, EducationGrade egd, Lesson... gradeBasePrerequisite) {
        Lesson lesson = new Lesson(this, name, egd, gradeBasePrerequisite);
        return lesson;

    }
}
