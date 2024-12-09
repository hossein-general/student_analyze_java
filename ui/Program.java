package ui;

import datamanage.*;
import faker.Faker;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;



// the program is the main body of the ui
// it contians a loop in wich it handles inputs, creates visual menues based on
// Menue and MenueOptions, and redirects to other menues base on user inputs
// also the constructor of this class is where the menues are built so they
// could be used in runtime
// این کلاس وضیفه مدیریت تمامی کلاس های مربوط به نمایش و دریافت ورودی، و مدیریت منو های داخل سیستم را دارد
public class Program {
    UserIn getUserIn = new UserIn();
    CurrentMenueDir menueDir = new CurrentMenueDir();
    RuntimeDataAccessor data;

    // MenueOption declarations
    // ساختن منو آپشن های مربوط به مدیریت اطلاعات
    MenueOption moShow = new MenueOption("Show All", new ActionTrigger("show_all"));
    MenueOption moAdd = new MenueOption("Add", new ActionTrigger("add"));
    MenueOption moEdit = new MenueOption("Edit", new ActionTrigger("edit"));
    MenueOption moRemove = new MenueOption("Remove", new ActionTrigger("remove"));

    MenueOption moExit = new MenueOption("Exit", new ActionTrigger("back"));

    // Menue declarations
    // manage menues
    // تعریف منو ها
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



    public void initMenue() {
        // Menue initialization
        // manage menues
        // مقدار دهی به منو ها
        // (منو ها شامل اسم اون منو + کاری که اون منو انجام می ده می شه)
        this.mo_cg_manage = new Menue("Class Group management", this.data.cg, moShow, moAdd, moEdit, moRemove);
        this.mo_teacher_manage = new Menue("Teacher management", this.data.teacher, moShow, moAdd, moEdit, moRemove);
        this.mo_student_manage = new Menue("Student management", this.data.student, moShow, moAdd, moEdit, moRemove);
        this.mo_croom_manage = new Menue("Classroom management", this.data.croom, moShow, moAdd, moEdit, moRemove);
        this.mo_school_manage = new Menue("School management", this.data.school, moShow, moAdd, moEdit, moRemove);
        this.mo_gender_manage = new Menue("Gender management", this.data.gender, moShow, moAdd, moEdit, moRemove);
        this.mo_lesson_manage = new Menue("Lesson management", this.data.lesson, moShow, moAdd, moEdit, moRemove);
        this.mo_egp_manage = new Menue("Education Group management", this.data.egp, moShow, moAdd, moEdit, moRemove);
        this.mo_egd_manage = new Menue("Education Grade management", this.data.egd, moShow, moAdd, moEdit, moRemove);
        this.mo_es_manage = new Menue("Education State management", this.data.es, moShow, moAdd, moEdit, moRemove);
        this.mo_person_manage = new Menue("Person management", this.data.person, moShow, moAdd, moEdit, moRemove);

        // main menues
        // منو هایی که کارایی که می دن در اصل صدا زدن یک منوی دیگه هستش
        this.mo_school_base_direct_manage = new Menue(
                "School Base Direct manage",
                new MenueOption("School", new ActionTrigger(mo_school_manage)),
                new MenueOption("Classroom", new ActionTrigger(mo_croom_manage)),
                new MenueOption("Student", new ActionTrigger(mo_student_manage)),
                new MenueOption("Teacher", new ActionTrigger(mo_teacher_manage)),
                new MenueOption("Class Group", new ActionTrigger(mo_cg_manage)));
        this.mo_person_related_manage = new Menue(
                "Person Related managements",
                new MenueOption("Person", new ActionTrigger(mo_person_manage)),
                new MenueOption("Gender", new ActionTrigger(mo_gender_manage)));
        this.mo_school_related_manage = new Menue(
                "School Related managements",
                new MenueOption("Direct Class Managements <dev>", new ActionTrigger(mo_school_base_direct_manage)),
                new MenueOption("Schools", new ActionTrigger("none")));
        this.mo_glob_attr_manage = new Menue(
                "Global Attribute management",
                new MenueOption("Education States", new ActionTrigger(mo_es_manage)),
                new MenueOption("Education Grade", new ActionTrigger(mo_egd_manage)),
                new MenueOption("Education Group", new ActionTrigger(mo_egp_manage)),
                new MenueOption("Lesson", new ActionTrigger(mo_lesson_manage)));
        this.mo_main_menue = new Menue(
                "Main Menue",
                new MenueOption("Global Attribute management", new ActionTrigger(mo_glob_attr_manage)),
                new MenueOption("Person realated management", new ActionTrigger(mo_person_related_manage)),
                new MenueOption("School related managements", new ActionTrigger(mo_school_related_manage)));
    }

    public static void main(String[] args) {
        System.out.print("\033c"); // ANSI code for clearing screen
        System.out.println("program stats");

        RuntimeDataAccessor data = new RuntimeDataAccessor();
        Faker.init_data(data); // initializing data with fake data

        Program program = new Program(data);
        program.run();

    }

    public Program(RuntimeDataAccessor data) {
        this.data = data;
        this.initMenue();

    }

    public void menue(Menue currMenue) {
        this.menueDir.addTop(currMenue.name);
        // bottom message is a list containing errors that program have encountered that
        // user has to fix when inputting data
        // یک متغیر که خطا های هر سری انجام شدن لوپ رو داخل خودش نکه می داره تا با چاپ اطلاعات در سری بعدش نمایش دهد
        ArrayList<String> bottomMessage = new ArrayList<String>();

        // refactored menue is a dictionary containing menue options, that the menue()
        // method will call triggers using that instead of the Menue object itself
        // یک دیکشنری که مقدار های معقول ورودی رو به کار هایی که انجام میدن وصل می کنه
        Dictionary<String, MenueOption> refactoredMenueDict;

        // a temporarely holder for chosen temp ActionTrigger
        // یک متغیر که موقتا اون کاری که کاربر خواسته انجام بشه رو داخل خودش نگه می داره
        ActionTrigger tempActionTrigger;

        // the user input
        // متغیری که ورودی رو ذخیره می کنه
        String inp = "";

        // error status (this variable holds the return of actionTrigger instances which by default is empty, but in case of an error occurance it will be stored here)
        // هر کاری که انجام میشه یک استرینگ به عنوان نتیجه ارسال می شه که اگر متد با موفقیت تموم شه استرینگ خالی برمی گرده
        String actTriggerErrStatus = "";

        // the flag for breaking the loop
        // نشون می ده کی کاربر درخواست می ده که حلقه شکسته شه
        boolean breakLoop = false;

        while (true) {
            // cleaning the cli screen
            // تمیز گردن صفجه و نمایش منو ای که در حال حاضر در آن هستیم
            System.out.print("\033c"); // ANSI code for clearing screen
            System.out.println(this.menueDir.getCurrentDir() + "\n");
            System.out.println(currMenue);

            // refactoring the menue so it would contain the "exit" option and having the
            // method stored only (instead of the MenueOption class instance)
            // استفاده از این تابع برای تبدیل آبجکت منو به یک دیکشنری از گزینه ها و کار هایی که انجام می دهد و اضافه کردن گزینه های اضافه مثل گزینه بازگشت
            refactoredMenueDict = this.optionPrintRefactor(currMenue);

            // printing the bottom messages
            // نمایش خطا های پیش آمده در گردش قبلی
            for (int i = (bottomMessage.size() - 1); i >= 0; i--) {
                System.out.println(bottomMessage.get(i));
                bottomMessage.remove(0);
            }

            // Getting the user input
            // دریافت ورودی از کاربر به کمک آبجکت دریاف ورودی
            System.out.println(); // creating an empty line
            inp = this.getUserIn.user_option("pls enter your choice: "); // getting the input from the user input object and storing it to inp
            // چک کردن این که گزینه ورودی در دیکشنری وجود دارد یا ننه
            if (!(((Hashtable) refactoredMenueDict).keySet().contains(inp))){ // checking if the input (as a key for dictionary) exists
                bottomMessage.add("invalid input"); // appending the message for invalid inputs
                continue;
            }

            tempActionTrigger = refactoredMenueDict.get(inp).trigger;
            switch (tempActionTrigger.getActionType()) {
                case "control_flow":
                    breakLoop = true;
                    break;
                case "menue":
                    actTriggerErrStatus = tempActionTrigger.run(this);
                    break;
                case "function":
                    actTriggerErrStatus = tempActionTrigger.run(currMenue.dataObject, getUserIn);
                    break;
                default:
                    bottomMessage.add("undefined action type detected for ActionTrigger");
            }

            if (actTriggerErrStatus != "") {
                bottomMessage.add(actTriggerErrStatus);
            }

            if (breakLoop) {
                breakLoop = false;
                break;
            }

        }
        System.out.print("\033c"); // ANSI code for clearing screen

        this.menueDir.removeTop();
    }

    // this method will print menue options and adds some extra options to it, while
    // adding indexes to them
    // این متد وضیفه داره منو آپشن هارو نمایش بده و یک دیکشنری از گزینه هارو به برنامه برگردونه تا بر اثاث اون ورودی را تحلیل کند
    private Dictionary optionPrintRefactor(Menue menue) {
        Dictionary refactoredMenueDict = new Hashtable();

        for (int i = 0; i < menue.moList.length; i++) {
            refactoredMenueDict.put(String.valueOf(i + 1), menue.moList[i]);
            System.out.println((i + 1) + "- " + menue.moList[i].name);
        }

        // appending and printing the exit menue option (Exit/Back)
        // اضافه کردن گزینه واسه خروج و انتخواب نام مناسب برای نمایش آن
        refactoredMenueDict.put(String.valueOf("q"), this.moExit);
        if (menue.name == "Main Menue") System.out.println("\n(q)- Exit");
        else System.out.println("\n(q)- Back");

        return refactoredMenueDict;
    }

    public void run() {
        this.menue(mo_main_menue);
    }
}



