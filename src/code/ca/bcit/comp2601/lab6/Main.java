package ca.bcit.comp2601.lab6;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class.
 *
 * @author Erica Baartman
 * @author Grace Jung
 * @author Irene Nagli
 * @author Nate Morrison
 * @version 1.3
 */
public class Main
{
    private static final String FILENAME = "src/resources/diary.txt";

    private static final int ADD_OPTION    = 1;
    private static final int VIEW_OPTION   = 2;
    private static final int SEARCH_OPTION = 3;
    private static final int EXIT_OPTION   = 4;


    /**
     * Main driver
     *
     * @param args arguments
     * @throws IOException if invalid
     */
    public static void main(final String[] args)
            throws IOException
    {
        final Diary   diary;
        final Scanner scanner;

        boolean proceed;

        diary   = new Diary(FILENAME);
        scanner = new Scanner(System.in);

        proceed = true;

        do
        {
            System.out.println("Diary Menu");
            System.out.println("---------------------------");
            System.out.println(ADD_OPTION + ". Add new diary entry");
            System.out.println(VIEW_OPTION + ". View all diary entries");
            System.out.println(SEARCH_OPTION + ". Search diary by date");
            System.out.println(EXIT_OPTION + ". Exit");

            System.out.print("Select an option: ");

            if (scanner.hasNextInt())
            {
                final int choice;

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice)
                {
                    case ADD_OPTION:
                        diary.addEntry();
                        break;

                    case VIEW_OPTION:
                        diary.viewAllEntries();
                        break;

                    case SEARCH_OPTION:
                        diary.searchEntriesByDate();
                        break;

                    case EXIT_OPTION:
                        System.out.println("Goodbye!");
                        proceed = false;
                        break;

                    default:
                        System.out.println("Please choose a number between 1 and 4.");
                        break;
                }
            }
            else
            {
                System.out.println("Invalid input. Please enter a number.\n");
                scanner.nextLine();
            }
        }
        while (proceed);

        scanner.close();
    }
}
