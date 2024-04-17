package pop.webcobranzas.util;
 
import  java.sql.*;

public class ObjConnection  {

    private Connection cn;
        
    
    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

  
}
