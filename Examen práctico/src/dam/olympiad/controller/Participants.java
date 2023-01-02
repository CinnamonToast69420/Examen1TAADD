package dam.olympiad.controller;

import java.time.LocalDate;
import java.util.List;

import dam.olympiad.dao.DAOException;
import dam.olympiad.dao.ParticipantsDAO;
import dam.olympiad.model.Participant;

public class Participants {
    private static Participants instance;
    private ParticipantsDAO participantsDAO;

    public static Participants getInstance(ParticipantsDAO participantsDAO) {
        if (instance == null) {
            instance = new Participants(participantsDAO);
        }

        return instance;
    }

    private Participants(ParticipantsDAO participantsDAO) {
        this.participantsDAO = participantsDAO;
    }

    public List<Participant> getParticipants() {

        List<Participant> participants = null;
        
        try {
            participants = participantsDAO.getAll();
        } catch (DAOException e) {}

        return participants;
    }
    
    public void newParticipant(String dni, String nombre, String origen, LocalDate fecha, int disciplina) 
    {
    	Participant p  = new Participant(dni, nombre, origen, fecha, disciplina);
    	try {
    		participantsDAO.insert(p);
    	} catch (DAOException de) 
    	{
    		de.printStackTrace();
    	}
    }
    
    public void deleteParticipant(String dni) 
    {
    	try 
    	{
    		participantsDAO.delete(dni);
    	}catch(DAOException de) 
    	{
    		de.printStackTrace();
    	}
    }
    
    public void modifyParticipant(Participant t) 
    {
    	try {
    		participantsDAO.update(t);
    	} catch(DAOException de) 
    	{
    		de.printStackTrace();
    	}
    }
}
