package datamanage;

// importings
import java.util.Dictionary;
import java.util.Hashtable; 


// یک کلاس که آبجکت های دیگه رو داخل خودش نگه می داره - هر دیتا آبجکت یک تایپ خاص از دیتا رو نگهداری می کند (مانند تیبل ها)
public class DataObject {
    public String dataName;
    public Dictionary items;
    
    public DataObject(String name) {
        this.dataName = name;
        this.items = new Hashtable();
    }

    public String toString(){
        return this.dataName;
    }

    public Dictionary getItems() {
        return this.items;
    }
    
}
