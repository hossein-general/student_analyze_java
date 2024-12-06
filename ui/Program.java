package ui;

import datamanage.*;
import faker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Scanner;
import java.util.List;

import java.util.Dictionary;
import java.util.Hashtable;

// the UserIn class is used to store previous user inputs and maybe some more functionality in the future
class UserIn {
    String catched; // the last value that was inputted
    Scanner inp = new Scanner(System.in); // the scanner object for inputs

    public UserIn() {
        this.catched = "";
    }

    // getting the user input and returning it
    public String user_option(String showing_text) {
        String new_inp = ""; // the new value (if user inputs null, the ctched value will be used )

        // formatting the self.catch in a way that it doesnt take so much space when is
        // used as a placeholder
        String catched_local = this.catched;
        String placeholder_text = (catched_local.length() <= 6) ? catched_local
                : (catched_local.substring(0, 3) + "...");

        // getting input and printing the "showing_text" value, as well as the last
        // entered value as a placeholder value
        if (placeholder_text == "")
            System.out.printf("%s", showing_text);
        else
            System.out.printf("%s <%s> ", showing_text, placeholder_text);
        new_inp = this.inp.nextLine();

        if (new_inp == "")
            return catched_local;

        this.catched = new_inp;
        return new_inp;
    }

    public void clear_catche() {
        this.catched = "";
    }
}

// this class contians each menue option, the name of that option, the thing
// that that menue does (the trigger), and other properties
class MenueOption {
    String name;
    ActionTrigger trigger;

    public MenueOption(String name, ActionTrigger trigger) {
        this.name = name;
        this.trigger = trigger;
    }

    public String toString() {
        return "<" + this.name + ": MenueOption>";
    }

    public ActionTrigger getActionTrigger() {
        return this.trigger;
    }
}

// this class contian general properties for each menue page, including menue
// options, the menue name, the kind of data that is involved and etc.
class Menue {
    DataObject dataObject;
    String name;
    MenueOption[] moList;

    public Menue(String name, MenueOption... moList) {
        this.name = name;
        this.moList = moList;

    }

    public Menue(String name, DataObject dataObject, MenueOption... moList) {
        this.name = name;
        this.moList = moList;
        this.dataObject = dataObject;

    }

    public String toString() {
        return this.name;
    }

}

// the program is the main body of the ui
// it contians a loop in wich it handles inputs, creates visual menues based on
// Menue and MenueOptions, and redirects to other menues base on user inputs
// also the constructor of this class is where the menues are built so they
// could be used in runtime
public class Program {
    UserIn getUserIn = new UserIn();
    RuntimeDataAccessor data;

    // MenueOption declaration
    MenueOption moShow;
    MenueOption moAdd;
    MenueOption moEdit;
    MenueOption moRemove;
    MenueOption moSchool;
    MenueOption moClassroom;
    MenueOption moStudent;
    MenueOption moTeacher;
    MenueOption moClassGroup;
    MenueOption moPerson;
    MenueOption moGender;
    MenueOption moDirectClassManagements;
    MenueOption moSchools;
    MenueOption moEducatoinState;
    MenueOption moEducationGrade;
    MenueOption moEducationGroup;
    MenueOption moLesson;
    MenueOption moGlobalAttributeManagement;
    MenueOption moPersonRealatedManagement;
    MenueOption moScoolRelatedManagements;
    MenueOption moExit;

    // Menue declaration
    Menue mo_cg_manage;
    Menue mo_teacher_manage;
    Menue mo_student_manage;
    Menue mo_croom_manage;
    Menue mo_school_manage;
    Menue mo_gender_manage;
    Menue mo_lesson_manage;
    Menue mo_egp_manage;
    Menue mo_egd_manage;
    Menue mo_es_manage;
    Menue mo_person_manage;
    Menue mo_school_base_direct_manage;
    Menue mo_person_related_manage;
    Menue mo_school_related_manage;
    Menue mo_glob_attr_manage;
    Menue mo_main_menue;

    private void createMenueOptions() {
        // MenueOption declarations
        moShow = new MenueOption("Show All", new ActionTrigger("show_all"));
        moAdd = new MenueOption("Add", new ActionTrigger("add"));
        moEdit = new MenueOption("Edit", new ActionTrigger("edit"));
        moRemove = new MenueOption("Remove", new ActionTrigger("remove"));

        moSchool = new MenueOption("School", new ActionTrigger(mo_school_manage));
        moClassroom = new MenueOption("Classroom", new ActionTrigger(mo_croom_manage));
        moStudent = new MenueOption("Student", new ActionTrigger(mo_student_manage));
        moTeacher = new MenueOption("Teacher", new ActionTrigger(mo_teacher_manage));
        moClassGroup = new MenueOption("Class Group", new ActionTrigger(mo_cg_manage));
        moPerson = new MenueOption("Person", new ActionTrigger(mo_person_manage));
        moGender = new MenueOption("Gender", new ActionTrigger(mo_gender_manage));
        moDirectClassManagements = new MenueOption("Direct Class Managements <dev>",
                new ActionTrigger(mo_school_base_direct_manage));
        moSchools = new MenueOption("Schools", new ActionTrigger("none"));
        moEducatoinState = new MenueOption("Education States", new ActionTrigger(mo_es_manage));
        moEducationGrade = new MenueOption("Education Grade", new ActionTrigger(mo_egd_manage));
        moEducationGroup = new MenueOption("Education Group", new ActionTrigger(mo_egp_manage));
        moLesson = new MenueOption("Lesson", new ActionTrigger(mo_lesson_manage));
        moGlobalAttributeManagement = new MenueOption("Global Attribute management",
                new ActionTrigger(mo_glob_attr_manage));
        moPersonRealatedManagement = new MenueOption("Person realated management",
                new ActionTrigger(mo_person_related_manage));
        moScoolRelatedManagements = new MenueOption("School related managements",
                new ActionTrigger(mo_school_related_manage));
        moExit = new MenueOption("Exit", new ActionTrigger("back"));
    }

    private void createMenues() {
        // Menue declarations
        // manage menues
        mo_cg_manage = new Menue("Class Group management", moShow, moAdd, moEdit, moRemove);
        mo_teacher_manage = new Menue("Teacher management", moShow, moAdd, moEdit, moRemove);
        mo_student_manage = new Menue("Student management", moShow, moAdd, moEdit, moRemove);
        mo_croom_manage = new Menue("Classroom management", moShow, moAdd, moEdit, moRemove);
        mo_school_manage = new Menue("School management", moShow, moAdd, moEdit, moRemove);
        mo_gender_manage = new Menue("Gender management", moShow, moAdd, moEdit, moRemove);
        mo_lesson_manage = new Menue("Lesson management", moShow, moAdd, moEdit, moRemove);
        mo_egp_manage = new Menue("Education Group management", moShow, moAdd, moEdit, moRemove);
        mo_egd_manage = new Menue("Education Grade management", moShow, moAdd, moEdit, moRemove);
        mo_es_manage = new Menue("Education State management", moShow, moAdd, moEdit, moRemove);
        mo_person_manage = new Menue("Person management", moShow, moAdd, moEdit, moRemove);

        // main menues
        mo_school_base_direct_manage = new Menue(
                "School Base Direct manage",
                moSchool,
                moClassroom,
                moStudent,
                moTeacher,
                moClassGroup);
        mo_person_related_manage = new Menue(
                "Person Related managements",
                moPerson,
                moGender);
        mo_school_related_manage = new Menue(
                "School Related managements",
                moDirectClassManagements,
                moSchools);
        mo_glob_attr_manage = new Menue(
                "Global Attribute management",
                moEducatoinState,
                moEducationGrade,
                moEducationGroup,
                moLesson);
        mo_main_menue = new Menue(
                "Main Menue",
                moGlobalAttributeManagement,
                moPersonRealatedManagement,
                moScoolRelatedManagements);
    }

    public static void main(String[] args) {
        System.out.print("\033c"); // ANSI code for clearing screen
        System.out.println("program stats");

        RuntimeDataAccessor data = new RuntimeDataAccessor();
        Faker.init_data(data); // initializing data with fake data

        Program program = new Program(null);
        program.run();

    }

    public Program(RuntimeDataAccessor data) {
        this.data = data;
        this.createMenueOptions ();
        this.createMenues();

    }

    public void menue(Menue currMenue) {
        // bottom message is a list containing errors that program have encountered that
        // user has to fix when inputting data
        ArrayList<String> bottomMessage = new ArrayList<String>();

        // refactored menue is a dictionary containing menue options, that the menue()
        // method will call triggers using that instead of the Menue object itself
        Dictionary<String, MenueOption> refactoredMenueDict;

        // a temporarely holder for chosen temp ActionTrigger
        ActionTrigger tempActionTrigger;

        // the user input
        String inp = "";

        // the flag for breaking the loop
        boolean breakLoop = false;

        while (true) {
            // cleaning the cli screen
            System.out.print("\033c"); // ANSI code for clearing screen
            System.out.println(currMenue);

            // refactoring the menue so it would contain the "exit" option and having the
            // method stored only (instead of the MenueOption class instance)
            refactoredMenueDict = this.optionPrintRefactor(currMenue);

            // printing the bottom messages
            for (int i = (bottomMessage.size() - 1); i >= 0; i--) {
                System.out.println(bottomMessage.get(i));
                bottomMessage.remove(0);
            }

            // Getting the user input
            inp = this.getUserIn.user_option("pls enter your choice: ");
            
            System.out.println(refactoredMenueDict);
            System.out.println(refactoredMenueDict.get(inp));
            System.out.println(refactoredMenueDict.get(inp).getActionTrigger());
            inp = this.getUserIn.user_option("pls enter your choice: ");
            
            // tempActionTrigger = refactoredMenueDict.get(inp).trigger;
            // switch (tempActionTrigger.getActionType()) {
            //     case "back":
            //         breakLoop = true;
            //         break;
            //     case "menue":
            //         tempActionTrigger.run(this);
            //         break;
            //     case "function":
            //         tempActionTrigger.run(currMenue.dataObject);
            //         break;
            //     default:
            //         System.out.println("undefined action type");
            // }
            // if (breakLoop) break;
        }

    }

    // this method will print menue options and adds some extra options to it, while
    // adding indexes to them
    private Dictionary optionPrintRefactor(Menue menue) {
        Dictionary refactoredMenueDict = new Hashtable();

        for (int i = 0; i < menue.moList.length; i++) {
            refactoredMenueDict.put(String.valueOf(i + 1), menue.moList[i]);
            System.out.println((i + 1) + "- " + menue.moList[i].name);
        }

        return refactoredMenueDict;
    }

    public void run() {
        this.menue(mo_main_menue);
    }
}

// the ActionGrigger class is class containing different methods, and you create
// instance of it, you define which one of its functions is going to be used by
// this instance
class ActionTrigger {
    // the trigger is a keyword that describes whta is this instance going to when
    // the .run method of it is called
    public final String trigger;
    public final Menue menue;
    public final String actionType;

    public ActionTrigger(String trigger) {
        // TODO: add some validation for trigger keyword
        this.trigger = trigger;
        this.menue = null;
        if (trigger == "back")
            this.actionType = "control_flow";
        else
            this.actionType = "function";
    }

    public ActionTrigger(Menue menue) {
        // TODO: add some validation for trigger keyword
        this.trigger = null;
        this.menue = menue;
        this.actionType = "menue";
    }

    public String getActionType() {
        return this.actionType;
    }

    public void run(Program program) {
        if (this.menue != null) {
            this.menue(menue, program);
        }
    }

    public void run(DataObject data) {
        // checks if the constructor in put was of type keyword or menue, and then calls
        // the relatable function based on the value
        if (this.trigger != null) {
            switch (this.trigger) {
                case "show_all":
                    this.showAll(data);
                    break;

                case "add":
                    this.add(data);
                    break;

                case "edit":
                    this.edit(data);
                    break;

                case "remove":
                    this.remove(data);
                    break;

                case "none":
                    this.none();
                    break;

                default:
                    System.out.println("undefined trigger keyword");
            }
        }

    }

    private void showAll(DataObject data) {
        System.out.println("show all not defined yet");

    }

    private void add(DataObject data) {
        System.out.println("add not defined yet");

    }

    private void edit(DataObject data) {
        System.out.println("edit not defined yet");

    }

    private void remove(DataObject data) {
        System.out.println("remove not defined yet");

    }

    private void menue(Menue menue, Program program) {
        program.menue(menue);

    }

    private void none() {
        System.out.println("thing not defined yet");

    }

    public String toString() {
        System.out.println(this.menue);
        System.out.println(this.trigger);
        System.out.println(this.actionType);
        if (this.menue != null) {
            return this.menue.toString();
        }
        else if (this.trigger != null) {
            return this.trigger;
        }
        else return "tostring not found (unexpected)";

    }

}
