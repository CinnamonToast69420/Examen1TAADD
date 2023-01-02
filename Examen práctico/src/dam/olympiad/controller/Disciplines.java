package dam.olympiad.controller;

import java.util.List;

import dam.olympiad.dao.DAOException;
import dam.olympiad.dao.DisciplineDAO;
import dam.olympiad.model.Disciplina;

public class Disciplines {
	public static Disciplines instance;
	private static DisciplineDAO disciplineDAO;
	
	
	public static Disciplines getInstance(DisciplineDAO disciplina) 
	{
		if(instance==null) 
		{
			instance = new Disciplines(disciplina);
		}
		return instance;
	}
	
	public Disciplines(DisciplineDAO disciplineDao) 
	{
		this.disciplineDAO = disciplineDao;
	}
	
	public List<Disciplina> getDisciplinas(){
	
		List<Disciplina> listaD = null;
		
		try 
		{
			listaD = this.disciplineDAO.getAll();
		} catch(DAOException e) { e.printStackTrace();}
		
		return listaD;
	}
	
}
