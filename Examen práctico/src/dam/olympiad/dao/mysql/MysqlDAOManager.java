package dam.olympiad.dao.mysql;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dam.olympiad.dao.DAOManager;
import dam.olympiad.dao.DisciplineDAO;
import dam.olympiad.dao.ParticipantsDAO;
import org.apache.ibatis.jdbc.ScriptRunner;
public class MysqlDAOManager implements DAOManager {

    Connection conn;
    ParticipantsDAO participantsDAO;
    DisciplineDAO disciplineDAO;

    
  
    
    public MysqlDAOManager(String host, String username, String password, String database) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://" + host + "/"+database, username, password);
    }


    @Override
    public ParticipantsDAO geParticipantsDAO() {
        if (participantsDAO == null) {
            participantsDAO = new MysqlParticipantsDAO(conn);
        }

        return participantsDAO;
    }


	@Override
	public DisciplineDAO getDisciplineDAO() {
		if(disciplineDAO==null) 
		{
			disciplineDAO = new MysqlDisciplinesDAO(conn);
		}
		return disciplineDAO;
	}
    
    
    
    
    
}
