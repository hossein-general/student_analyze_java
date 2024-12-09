package datamanage;

// importings
import datamanage.DataObject;

// این کلاس دیتا آبچکت ها رو نگه داری می کنه - مثل یک دیتا بیس که شامل تیبل هاست
public class RuntimeDataAccessor {
    // data objects that are contained within the program and need to be stored somewhere
    public DataObject es;
    public DataObject egd;
    public DataObject egp;
    public DataObject lesson;
    public DataObject gender;
    public DataObject person;
    public DataObject school;
    public DataObject croom;
    public DataObject student;
    public DataObject teacher;
    public DataObject cg;


    public RuntimeDataAccessor () {
        this.es = new DataObject("Education State");
        this.egd = new DataObject("Education Grade");
        this.egp = new DataObject("Education Group");
        this.lesson = new DataObject("Lesson");
        this.gender = new DataObject("Gender");
        this.person = new DataObject("Person");
        this.school = new DataObject("School");
        this.croom = new DataObject("ClassRoom");
        this.student = new DataObject("Student");
        this.teacher = new DataObject("Teacher");
        this.cg = new DataObject("Class Group");
        
    }    
}
