package dam.olympiad.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dam.olympiad.dao.DAOException;
import dam.olympiad.dao.ParticipantsDAO;
import dam.olympiad.model.Participant;

public class MysqlParticipantsDAO implements ParticipantsDAO {
	private Connection conn;
	
	private String insert  = "INSERT INTO participantes(dni, nombre, origen, fecha_nacimiento, disciplina) VALUES(?, ?, ?, ?, ?)";
	private String update = "UPDATE participantes SET nombre = ?, origen = ?, fecha_nacimiento = ?, disciplina = ? WHERE dni = ?";
	private String delete = "DELETE FROM participantes WHERE dni = ?";
	private String getById = "SELECT dni, nombre, origen, fecha_nacimiento, disciplina FROM participantes WHERE dni = ?";
	private String getAll = "SELECT dni, nombre, origen, fecha_nacimiento, disciplina FROM participantes";

	public MysqlParticipantsDAO(Connection conn) {
		this.conn = conn;
	}

    @Override
    public void delete(String t) throws DAOException {
    	PreparedStatement statement = null;
		try {
			 statement = conn.prepareStatement(delete);
			 statement.setString(1, t);
			
			 if(statement.executeUpdate()==0) 
			{
				throw new DAOException("es probable que los datos no se hayan borrado!");
			}
		} catch(SQLException se) {
			throw new DAOException("Error en SQL", se);
			
		}
		
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	
    }

    @Override
    public List<Participant> getAll() throws DAOException {
    	PreparedStatement stm = null;
		ResultSet rs = null;
		List<Participant> participantsDev = new ArrayList<>();
		try {
			stm = conn.prepareStatement(getAll);
			 rs = stm.executeQuery();
			 while(rs.next())
			 {
				 participantsDev.add(convertir(rs));
			 }
		}catch(SQLException se) {
			throw new DAOException("Error en la consulta", se);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch(SQLException se) {
					throw new DAOException("Error en sql", se);
				}
			}
			
		}
		return participantsDev;
    }

    @Override
    public Participant getById(String id) throws DAOException {
    	PreparedStatement stm = null;
		ResultSet rs = null;
		Participant a = null;
		try {
			stm = conn.prepareStatement(getById);
			 stm.setString(1, id);
			 rs = stm.executeQuery();
			 if(rs.next()) {
				 a = convertir(rs);
			 }else {
				 throw new DAOException("No se encontró ese registro! >:(");
			 }
		}catch(SQLException se) {
			throw new DAOException("Error en la consulta", se);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch(SQLException se) {
					throw new DAOException("Error en sql", se);
				}
			}
		}
		return a;
    }
    
    private Participant convertir(ResultSet rs) throws SQLException
	{
		String dni = rs.getString("dni");
		String nombre = rs.getString("nombre");
		LocalDate fechaNac= rs.getDate("fecha_nacimiento").toLocalDate();
		String origen = rs.getString("origen");
		int disciplina = rs.getInt("disciplina");
		Participant a = new Participant(dni, nombre, origen, fechaNac, disciplina);
		return a;
	}

    @Override
    public void insert(Participant t) throws DAOException {
       PreparedStatement ps = null;
       
      try 
      {
    	  ps = conn.prepareStatement(insert);
    	  ps.setString(1, t.getName());
    	  ps.setString(2, t.getCountry());
    	  ps.setDate(3, new Date(t.getBirthday().getYear(), t.getBirthday().getMonthValue(), t.getBirthday().getDayOfMonth()));
    	  ps.setInt(4, t.getDiscipline());
    	  ps.setString(5, t.getDni());
      if(ps.executeUpdate()==0) 
		{
			throw new DAOException("es probable que los datos no se hayan guardado!");
		}
	} catch(SQLException se) {
		throw new DAOException("Error en SQL", se);
		
	}
	
	finally {
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    }
    
    

    @Override
    public void update(Participant t) throws DAOException {
    	 PreparedStatement ps = null;
         
         try 
         {
       	  ps = conn.prepareStatement(update);
       	  ps.setString(1, t.getName());
       	  ps.setString(2, t.getCountry());
       	  ps.setDate(3, new Date(t.getBirthday().getYear(), t.getBirthday().getMonthValue(), t.getBirthday().getDayOfMonth()));
       	  ps.setInt(4, t.getDiscipline());
       	 ps.setString(5, t.getDni());
         
         if(ps.executeUpdate()==0) 
   		{
   			throw new DAOException("es probable que los datos no se hayan actualizado!");
   		}
   	} catch(SQLException se) {
   		throw new DAOException("Error en SQL", se);
   		
   	}
   	
   	finally {
   		try {
   			ps.close();
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   	}
    }
}