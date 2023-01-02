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
import dam.olympiad.dao.DisciplineDAO;
import dam.olympiad.model.Disciplina;
import dam.olympiad.model.Participant;

public class MysqlDisciplinesDAO implements DisciplineDAO{
	
	private String insert = "INSERT INTO disciplinas (nombre) VALUES(?)";
	private String update =  "UPDATE disciplinas SET nombre = ? WHERE id = ?";
	private String delete = "DELETE from disciplinas WHERE id = ?";
	private String getAll = "SELECT id, nombre FROM disciplinas";
	private String getById = "SELECT id, nombre FROM disciplinas WHERE id = ?";
	
	private Connection conn;
	public MysqlDisciplinesDAO(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void insert(Disciplina t) throws DAOException {
		// TODO Auto-generated method stub
		
		
		
		
	}

	@Override
	public void update(Disciplina t) throws DAOException {
		PreparedStatement ps = null;
         
         try 
         {
       	  ps = conn.prepareStatement(insert);
       	  ps.setString(1, t.getNombre());
       	  ps.setInt(2, t.getId());
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

	@Override
	public void delete(Integer idDisciplina) throws DAOException {
		PreparedStatement statement = null;
		try {
			 statement = conn.prepareStatement(delete);
			 statement.setInt(1, idDisciplina);
			
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
	
	private Disciplina convertir(ResultSet rs) throws SQLException
	{
		int id = rs.getInt("id");
		String nombre = rs.getString("nombre");
		Disciplina d = new Disciplina(id, nombre);
		return d;
	}

	@Override
	public List<Disciplina> getAll() throws DAOException {
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Disciplina> disciplinasDev = new ArrayList<>();
		try {
			stm = conn.prepareStatement(getAll);
			 rs = stm.executeQuery();
			 while(rs.next())
			 {
				 disciplinasDev.add(convertir(rs));
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
		return disciplinasDev;
	}

	@Override
	public Disciplina getById(Integer k) throws DAOException {
		PreparedStatement stm = null;
		ResultSet rs = null;
		Disciplina d = null;
		try {
			stm = conn.prepareStatement(getById);
			 stm.setInt(1, k);
			 rs = stm.executeQuery();
			 if(rs.next()) {
				 d = convertir(rs);
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
		return d;
	}

}
