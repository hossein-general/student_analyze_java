package ui;

// importings
import java.util.Scanner;


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
