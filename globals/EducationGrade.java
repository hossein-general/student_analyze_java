package globals;

public class EducationGrade {
    EducationState parentES;
    String name;

    public EducationGrade(String name, EducationState es) {
        this.name = name;
        this.parentES = es;

    }
}
