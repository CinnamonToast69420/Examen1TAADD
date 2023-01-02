package dam.olympiad.ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dam.olympiad.controller.Participants;
import dam.olympiad.dao.DAOManager;
import dam.olympiad.model.Participant;

public class TextUI {

    private int option = -1;
    private Scanner sc = null;
    private Map<Integer, String> options = null;
    private DAOManager daoManager = null;

    public TextUI(DAOManager daoManager) {
        this.daoManager = daoManager;

        initOptions();
        
        sc = new Scanner(System.in);

        while (option != 0) {
            showMenu();
            getOption();

            try {
                Method m = getClass().getDeclaredMethod(options.get(option));
                m.setAccessible(true);
                m.invoke(this);
                } catch (InvocationTargetException e) {
                    e.getCause().printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            option = -1;
        }
    }

    private void initOptions() {
        options = Map.of(
            0, "quit",
            1, "showParticipants",
            2, "createParticipant",
            3, "modificarParticipante",
            4, "eliminarParticipante"
            
        );
    }
    
    private void showMenu() {
        System.out.print("Select an option\n================1. Show participants\n2. create participant\n3. Modify Participant"
        		+ "\n4. eliminate participant"+
                "\n5. Show Disciplines without participants" + 
        		"\n6. Create Discipline" +
        		"\n7. Modify discipline" + 
        		"\n8. Delete discipline" + 
        		"\n9. Average age of participants for every discipline" + 
        		"\n10. Get the disciplines with younger and older participants"
        		);
    }

    private void getOption() {
        while (option < 0 || option >= options.size()) {
            option = sc.nextInt();
        }
    }

    private void showParticipants() {
        System.out.println("Participants");
        
        List<Participant> list = Participants.getInstance(daoManager.geParticipantsDAO()).getParticipants();
        list.forEach(System.out::println);
    }
    
    private void createParticipant() 
    {
    
    	String dni;
    	String nombre;
    	String origen;
    	LocalDate fecha;
    	int disciplina;
    	
    	System.out.println("Inserta el nombre del nuevo: ");
    	nombre = sc.nextLine();
    	System.out.println("inserta el DNI del nuevo: ");
    	dni = sc.next();
    	System.out.println("inserta el pais del nuevo: ");
    	origen = sc.next();
    	System.out.println("inserta la fecha de nacimiento del nuevo:  con formato YYYY-MM-DD");
    	String fechaString = sc.next();
    	fecha = LocalDate.parse(fechaString);
    	System.out.println("inserta la disciplina del nuevo: ");
    	disciplina = sc.nextInt();
    	Participant p  =new Participant(dni, nombre, origen, fecha, disciplina);
    	Participants.getInstance(daoManager.geParticipantsDAO()).newParticipant(dni, nombre, origen, fecha, disciplina);
    
    	
    }
    
    
    private void eliminarParticipante() 
    
    {
    	String dni;
    	System.out.println("Inserta el dni del participante que deseas eliminar: ");
    	dni = sc.next();
    	Participants.getInstance(daoManager.geParticipantsDAO()).deleteParticipant(dni);
    	System.out.println("Participante con DNI %d eliminado!"+ dni );
    	
    }
    
    private void modificarParticipante() 
    {
    	String dni;String nombre; String origen; String fecha; int disciplina;LocalDate fechaLocalDate;
    	System.out.println("Inserta el DNI del participante que deseas modificar: ");
    	dni = sc.next();
    	System.out.println("Inserta el  nuevo nombre del participante que deseas modificar: ");
    	nombre = sc.next();
    	System.out.println("Inserta el nuevo país del participante que deseas modificar: ");
    	origen = sc.next();
    	System.out.println("Inserta la nueva fecha de nacimiento del participante que deseas modificar en formato YYYY-MM-DD: ");
    	fecha= sc.next(); fechaLocalDate = LocalDate.parse(fecha);
    	System.out.println("Inserta la nueva disciplina del participante que deseas modificar: ");
    	disciplina = sc.nextInt();
    	
    	Participant p = new Participant(dni, nombre, origen, fechaLocalDate, disciplina);
    	
    	Participants.getInstance(daoManager.geParticipantsDAO()).modifyParticipant(p);
    	
    }

    private void quit(){
        sc.close();
        System.exit(0);
    };
}
