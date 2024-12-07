package ui;

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
        return "<" + this.name + " | MenueOption>";
    }

    public ActionTrigger getActionTrigger() {
        return this.trigger;
    }
}
