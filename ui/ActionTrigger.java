package ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import javax.tools.Tool;

// importings 
import datamanage.*;
import tools.*;

// the ActionGrigger class is class containing different methods, and you create
// instance of it, you define which one of its functions is going to be used by
// this instance
class ActionTrigger {
    // the trigger is a keyword that describes whta is this instance going to when
    // the .run method of it is called
    public final String trigger;
    public final Menue menue;
    public final String actionType;
    UserIn getUserIn;
    String errStatus = "the error status for run() was not initialized";

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

    public String run(Program program) {
        if (this.menue != null) {
            errStatus = this.menue(menue, program);
        }
        return errStatus;
    }

    public String run(DataObject data, UserIn userInObject) {
        this.getUserIn = userInObject;
        // checks if the constructor in put was of type keyword or menue, and then calls
        // the relatable function based on the value
        if (this.trigger != null) {
            switch (this.trigger) {
                case "show_all":
                    errStatus = this.showAll(data);
                    break;

                case "add":
                    errStatus = this.add(data);
                    break;

                case "edit":
                    errStatus = this.edit(data);
                    break;

                case "remove":
                    errStatus = this.remove(data);
                    break;

                case "none":
                    errStatus = this.none();
                    break;

                default:
                    errStatus = "undefined trigger keyword";
            }
        }
        return errStatus;

    }

    private String showAll(DataObject data) {
        // Clearing the catche for
        this.getUserIn.clear_catche();

        // retrieving data from data object
        Collection recordsCollection = ((Hashtable) data.items).values();

        // the count of records within the data object
        Integer recordCount = recordsCollection.size();

        // converting collection to indexed iterable
        List recordsList = new ArrayList(recordsCollection);

        // a variable that defines the current page we are in
        Integer currentPage = 1;

        // the count of showing rows
        Integer rowCount = 10;

        // calculating the page count (considering the number of rows and count of data)
        Integer pageCount;

        // only used for counting the number of pages needed for showing data
        Integer countFloorDiv = Math.floorDiv(recordCount, rowCount);

        // Initializations
        Integer maxRow = 99;
        Integer minRow = 5;
        Integer curPageStartingRow;
        Integer curPageEndingRow;
        Integer endRow;

        // checking for exit option input
        boolean breakLoop = false;

        // user input
        String inp = "";
        Integer inpInt;

        while (true) {
            System.out.print("\033c"); // ANSI code for clearing screen

            // page related calculations
            curPageStartingRow = (currentPage - 1) * rowCount + 1;
            curPageEndingRow = currentPage * rowCount;
            endRow = (curPageEndingRow <= recordCount) ? curPageEndingRow : recordCount;
            pageCount = (recordCount % rowCount) == 0 ? countFloorDiv : countFloorDiv + 1;

            System.out.println(data.dataName + String.format(": (page: %d/%d) (rows: %d-%d)\n", currentPage, pageCount,
                    curPageStartingRow, endRow));

            System.out.println(String.format("%-8s", "rows") + data.dataName);
            for (int i = curPageStartingRow - 1; i < endRow; i++) {
                System.out.println(String.format("%-8d", i) + recordsList.get(i));
            }

            // getting user input for further actions
            inp = this.getUserIn.user_option(String
                    .format("\nq: quit / n: next page / p: previous page / row number (%d-%d): ", minRow, maxRow));

            switch (inp) {
                case "n":
                    // preventing user from going further than the last page
                    if (currentPage < pageCount)
                        currentPage += 1;
                    break;

                case "p":
                    if (currentPage > 1)
                        currentPage -= 1;
                    break;

                case "q":
                    breakLoop = true;
                    break;
            }

            if (breakLoop) break;

            if (Tools.isNumeric(inp)) {
                inpInt = Integer.parseInt(inp);
                if (inpInt >= minRow && inpInt <= maxRow) {
                    rowCount = inpInt;
                    currentPage = 1;
                }
            }

        }

        return "show all not implemented yet";
    }

    private String add(DataObject data) {
        return "add not implemented yet";

    }

    private String edit(DataObject data) {
        return "edit not implemented yet";

    }

    private String remove(DataObject data) {
        return "remove not implemented yet";

    }

    private String menue(Menue menue, Program program) {
        program.menue(menue);
        return "";

    }

    private String none() {
        return "this Menue options is not implemented yet";

    }

    public String toString() {
        return ("<menue:" + this.menue.toString() + " trigger:" + this.trigger + " actionType:" + this.actionType
                + " | ActionTrigger>");
    }

}
