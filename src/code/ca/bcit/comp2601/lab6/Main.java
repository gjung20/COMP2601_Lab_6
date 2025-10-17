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

        System.out.println("Diary Menu");
        System.out.println("---------------------------");
        System.out.println(ADD_OPTION + ". Add new diary entry");
        System.out.println(VIEW_OPTION + ". View all diary entries");
        System.out.println(SEARCH_OPTION + ". Search diary by date");
        System.out.println(EXIT_OPTION + ". Exit");

        do
        {
            final int choice;

            if (scanner.hasNextInt())
            {
                System.out.print("Select an option: ");

                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == ADD_OPTION)
                {
                    diary.addEntry();
                }
                else if (choice == VIEW_OPTION)
                {
                    diary.viewAllEntries();
                }
                else if (choice == SEARCH_OPTION)
                {
                    diary.searchEntriesByDate();
                }
                else if (choice == EXIT_OPTION)
                {
                    System.out.println("Byebye!");
                    proceed = false;
                }
                else
                {
                    System.out.println("Please choose out of: " +
                                       ADD_OPTION + VIEW_OPTION + SEARCH_OPTION + EXIT_OPTION);
                }

            }

            else
            {
                System.out.println("Please enter a valid choice (int)");
                scanner.nextLine();
            }
        }
        while (proceed);
    }
}
