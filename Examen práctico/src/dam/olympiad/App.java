package dam.olympiad;
import org.apache.ibatis.jdbc.ScriptRunner;
import dam.olympiad.dao.mysql.MysqlDAOManager;
import dam.olympiad.ui.TextUI;

public class App {
    private static final String DATABASE_HOST = "localHost:3306";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASS = "nkptv";
    private static final String DATABASE_NAME = "olimpiadas";

    public static void main(String[] args) throws Exception {
        new TextUI(new MysqlDAOManager(DATABASE_HOST, DATABASE_USER, DATABASE_PASS, DATABASE_NAME));
        
        
        
        
    }
}
