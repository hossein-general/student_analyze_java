package datamanage;

// importings
import java.util.Dictionary;
import java.util.Hashtable; 


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
    
}
