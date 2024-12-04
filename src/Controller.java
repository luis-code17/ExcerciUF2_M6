import dao.AthleteDAO;
import dao.SportDAO;
import model.Athlete;
import model.Sport;
import view.View;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {
        ConnectXML config = new ConnectXML("src/database.xml");
        Scanner sc = new Scanner(System.in);
        Connection conn = config.dbConnect();
        View view = new View(sc);
        SportDAO sportDAO = new SportDAO(conn);
        AthleteDAO athleteDAO = new AthleteDAO(conn);
        int choice = 0;

        do{
            boolean result = false;
            choice = view.printMenu();
            switch (choice) {
                case 1:
                    while (result == false) {
                        String athleteName = view.AthleteForm();
                        ArrayList<Sport> sports= (ArrayList<Sport>) sportDAO.getAll();
                        int sport = view.AskSport(sports);
                        int code = athleteDAO.LastCode()+1;
                        String sportName = athleteDAO.getSportName(code);
                        Athlete athlete = new Athlete(athleteName, code, sport, sportName);

                        result = athleteDAO.exists(athlete);
                        if (result == false) {
                            athleteDAO.add(athlete);
                            result = true;
                        } else {
                            System.out.println("The sport does not exist. Please enter a different sport.");
                        }
                    }
                    result = false;
                    break;
                case 2:
                    while (result == false) {
                        String name = view.SportForm(sc);
                        int code = sportDAO.LastCode()+1;
                        Sport sport = new Sport(name, code);
                        result = sportDAO.exists(sport);
                        if (result) {
                            System.out.println("The sport already exists. Please enter a different sport.");
                        } else {
                            sportDAO.add(sport);
                            result = true;
                        }
                    }
                    break;
                case 3:
                    ArrayList<Athlete> athletes = (ArrayList<Athlete>) athleteDAO.getAll();
                    view.AthleteList(athletes);
                    break;
                case 4:
                    ArrayList<Sport> sports = (ArrayList<Sport>) sportDAO.getAll();
                    view.SportList(sports);
                    break;
                case 5:
                    ArrayList<Sport> sportss= (ArrayList<Sport>) sportDAO.getAll();
                    int code = view.AskSport(sportss);
                    ArrayList<Athlete> athletesBySport = (ArrayList<Athlete>) athleteDAO.getAthleteBySport(code);
                    view.AthleteList(athletesBySport);
                    break;

                case 6:
                    String name = view.AskName();
                    ArrayList<Athlete> athletess = athleteDAO.getAthletesByPartialName(name);
                    if (athletess != null) {
                        view.AthleteList(athletess);
                    } else {
                        System.out.println("No athletes found with that name.");
                    }
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    break;
            }
        }while (choice != 7);


    }


}