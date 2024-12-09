package ui;

// importings
import datamanage.*;

// this class contian general properties for each menue page, including menue
// options, the menue name, the kind of data that is involved and etc.
// کلاس منو برای دسته دسته کردن منو آپشن هاست، هر صفجه یک منو به حساب میاد
// هر منو یک سری منو آپشن دارد
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
