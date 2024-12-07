package ui;

// importings
import datamanage.*;

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
