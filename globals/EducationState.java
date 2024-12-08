package globals;

public class EducationState {
    // Initializing Variables
    String name;

    // Constructor
    public EducationState(String nameParam) {
        this.name = nameParam;
    }
    
    // Name Getter and Setter
    public String get_name() {
        return name;
    }
    public void set_name(String nameParam) {
        this.name = nameParam;
    }
    
    // Creating other class
    // Creating Education Grades for current Education State
    public EducationGrade addGrade(String name) {
        EducationGrade egd = new EducationGrade(name, this);
        return egd;
    }

    public EducationGroup addGroup(String name, boolean directUse, EducationGroup... egps) {
        EducationGroup egp = new EducationGroup(this, name, directUse, egps);
        return egp;
    }

    
    // the string that represents the name of each instance
    public String toString(){
        return this.name;
    }

}
