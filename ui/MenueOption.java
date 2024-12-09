package ui;

// this class contians each menue option, the name of that option, the thing
// that that menue does (the trigger), and other properties
// هر منو آپشن شامل اسم و کاری که اون آپشن انجام می دهد می شود 
// (دلیل جدا کردن کلاس های از این مدل قابلیت ارگانایز کردن اون ها در آینده هست)
class MenueOption {
    String name;
    ActionTrigger trigger;

    public MenueOption(String name, ActionTrigger trigger) {
        this.name = name;
        this.trigger = trigger;
    }

    public String toString() {
        return "<" + this.name + " | MenueOption>";
    }

    public ActionTrigger getActionTrigger() {
        return this.trigger;
    }
}
