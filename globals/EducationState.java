package globals;

import globals.EducationGrade;

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
    public EducationGrade addGrade() {
        EducationGrade eg = new EducationGrade();
        return eg;
    }
    
    // the string that represents the name of each instance
    public String toString(){
        return this.name;
    }

}
