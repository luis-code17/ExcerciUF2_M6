package view;

import model.Athlete;
import model.Sport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    private Scanner sc;

    public View(Scanner sc) {
        this.sc = sc;
    }

    public int printMenu() {
        int choice = 0;

        System.out.println("|-------------------------------|");
        System.out.println("| Menu:                         |");
        System.out.println("|-------------------------------|");
        System.out.println("| 1. Add athlete                |");
        System.out.println("| 2. Add sport                  |");
        System.out.println("| 3. List athletes              |");
        System.out.println("| 4. List sports                |");
        System.out.println("| 5. List athletes by Sport     |");
        System.out.println("| 6. Athletes by name           |");
        System.out.println("| 7. Exit                       |");
        System.out.println("|-------------------------------|");
        System.out.println(" Enter your choice:");
        do{
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                if (choice < 1 || choice > 6) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                sc.next();
            }
        }while (choice < 1 || choice > 7);

        return choice;
    }

    public String AthleteForm() {
        String name;
        System.out.println("Enter athlete name:");
        do {
            name = sc.next();
            if (name.length() < 3) {
                System.out.println("Invalid name. Please enter a name with at least 3 characters.");
            }
        } while (name.length() < 3);
        sc.nextLine();

        return name;
    }

    public int AskSport(ArrayList<Sport> sports) {
        int max = sports.get(sports.size()-1).getCode();

        for (Sport sport : sports) {
            System.out.println(sport);
        }

        int codeSport = 0;
        System.out.println("Enter sport code:");
        do {
            if (sc.hasNextInt()) {
                codeSport = sc.nextInt();
                if (codeSport < 1 || codeSport > max) {
                    System.out.println("Invalid code. Please enter a number greater than 0.");
                }
            } else {
                System.out.println("Invalid code. Please enter a number greater than 0.");
                sc.next();
            }
        } while (codeSport < 1 || codeSport > max);

        return codeSport;
    }

    public String SportForm(Scanner sc) {
        String name;
        int code = 0;
        System.out.println("Enter sport name:");
        do {
            name = sc.next();
            if (name.length() < 3) {
                System.out.println("Invalid name. Please enter a name with at least 3 characters.");
            }
        } while (name.length() < 3);
        sc.nextLine();

        return name;
    }

    public void AthleteList(List<Athlete> athletes) {
        for (Athlete athlete : athletes) {
            System.out.println(athlete);
        }
    }

    public void SportList(List<Sport> sports) {
        for (Sport sport : sports) {
            System.out.println(sport);
        }

    }

    public String AskName() {
        String name;
        System.out.println("Enter athlete name:");
        do {
            name = sc.next();
            if (name.length() < 3) {
                System.out.println("Invalid name. Please enter a name with at least 3 characters.");
            }
        } while (name.length() < 3);
        sc.nextLine();

        return name;

    }


}
