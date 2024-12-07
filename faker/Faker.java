package faker;

// imports
import globals.*;
import person.*;
import school.*;
import datamanage.*;

public final class Faker {
    private Faker() {} // to prevent instance creatin

    // initializing data within objects with fake data
    public static void init_data(RuntimeDataAccessor data) {
        data.es.items.put("ps", new EducationState("Primary School"));
        data.es.items.put("hs1", new EducationState("High School 1st"));
        data.es.items.put("hs2", new EducationState("High School 2nd"));
        data.es.items.put("u", new EducationState("University"));
        // System.out.println(data.es.items);
    }

}
