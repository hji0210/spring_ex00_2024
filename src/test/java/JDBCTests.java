import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTests {

    @Test
    public void testConnection() throws Exception{


        //DB Driver class
       Class.forName("org.mariadb.jdbc.Driver");




       //URL
       Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/springdb?allowPublicKeyRetrieval=true&useSSL=false",
                   "springdbuser",
                   "springdbuser");

       System.out.println(connection);



        //username/password


        connection.close();


    }






}
