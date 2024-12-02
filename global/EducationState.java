package global;

import global.EducationGrade;

public class EducationState {
    // Initializing Variables
    String name;

    // Constructor
    public EducationState(String nameParam) {
        name = nameParam;
    }
    
    // Name Getter and Setter
    public String get_name() {
        return name;
    }
    
    public void set_name(String nameParam) {
        name = nameParam;
    }
    
    // Creating other class
    // Creating Education Grades for current Education State
    public EducationGrade addGrade() {
        EducationGrade eg = new EducationGrade();
        return eg;
    }
    

}
