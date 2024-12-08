
// importing classes from packages
import faker.Faker;
import ui.Program;
import datamanage.*;

public class Main {
    public static void main(String[] args) {
        System.out.print("\033c"); // ANSI code for clearing screen
        System.out.println("program stats");

        RuntimeDataAccessor data = new RuntimeDataAccessor();
        Faker.init_data(data); // initializing data with fake data

        Program program = new Program(data);
        program.run();

        System.out.println("Good Bye!");

    }
}