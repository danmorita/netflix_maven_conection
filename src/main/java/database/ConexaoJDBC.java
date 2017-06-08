package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author danmo
 */
public class ConexaoJDBC {
    Connection c;
    public Connection ConectaBD() throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/netflix","teste","senhadificil");
    	System.out.println("banco de dados conectado!");
        return c;
    }

    
}
