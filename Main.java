
// importing classes from packages
import faker.Faker;
import datamanage.*;

public class Main {
    public static void main(String[] args) {
        RuntimeDataAccessor data = new RuntimeDataAccessor();
        Faker.init_data(data); // initializing data with fake data

    }
}