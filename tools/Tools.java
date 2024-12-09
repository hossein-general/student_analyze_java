package tools;

// جهت بررسی عدد شدنی بودن یک استرینگ
public class Tools {
    public static boolean isNumeric(String str) { 
        try {  
          Integer.parseInt(str);
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
      }
    
}
