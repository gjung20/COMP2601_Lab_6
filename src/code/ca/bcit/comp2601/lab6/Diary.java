package ca.bcit.comp2601.lab6;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Diary object.
 *
 * @author Erica Baartman
 * @author Grace Jung
 * @author Irene Nagli
 * @author Nate Morrison
 * @version 1.0
 */
public class Diary
{
    private static final int DATE_INDEX = 0;
    private static final int CONTENT_INDEX = 1;

    private final Path path;


    /**
     * Constructs a Diary object.
     *
     * @param path Path to the diary.
     * @throws NoSuchFileException if the path does not reference an existing file.
     */
    public Diary(final String path)
            throws NoSuchFileException
    {
        validatePath(path);

        this.path = Paths.get(path);
    }


    /**
     * Add an entry to the diary file.
     *
     * @throws IOException if an I/O error occurs writing to or creating the file
     */
    public void addEntry()
            throws IOException
    {
//        final DiaryEntry diaryEntry;

        final String dateString;
        final String  content;
        final Scanner scanner;

        scanner = new Scanner(System.in);


        System.out.print("Enter date (YYYY-MM-DD): ");

        dateString = scanner.next();
        scanner.nextLine();


        System.out.print("Enter content: ");

        content = scanner.nextLine();


        final String diaryEntry;

        diaryEntry = String.format("%s|%s%s", dateString, content, System.lineSeparator());


        try
        {
            DiaryEntry.validateDate(dateString);
        }
        catch (final DiaryEntryException e)
        {
            System.out.println(e);

            return;
        }


        Files.write(path, diaryEntry.getBytes(), StandardOpenOption.APPEND);

        System.out.println("\nDiary entry added successfully.\n");

    }


    /**
     * Prompts the user for a date to search for, and prints all entries with the corresponding
     * date, if any.
     *
     * @throws IOException if an I/O error occurs reading from the file or
     *                     a malformed or unmappable byte sequence is read
     */
    public void searchEntriesByDate()
            throws IOException
    {
        final Scanner scanner;
        String dateString;


        scanner = new Scanner(System.in);


        while (true)
        {
            boolean proceed;

            proceed = false;

            System.out.println("Enter date (YYYY-MM-DD): ");

            dateString = scanner.next();
            scanner.nextLine();

            try
            {
                DiaryEntry.validateDate(dateString);

                proceed = true;
            }
            catch (final DiaryEntryException e)
            {
                System.out.println("Sorry, that date is not valid. Please enter a valid one.");

                proceed = false;
            }


            if (proceed)
            {
                break;
            }
        }


        final List<String> entries;

        entries = Files.readAllLines(path);

        for (final String line : entries)
        {
            if (line.startsWith(dateString))
            {
                System.out.println(line);
            }
        }
    }


    /**
     * Prints the contents of the diary file.
     *
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     *                     This class is the general class of exceptions produced by failed or
     *                     interrupted I/O operations.
     */
    public void viewAllEntries()
            throws IOException
    {
        for (final String line : Files.readAllLines(path))
        {
            final String[] content;


            content = line.split("\\|");

            System.out.println("Date: " + content[DATE_INDEX]);
            System.out.println("Content: " + content[CONTENT_INDEX]);
        }
    }


    /**
     * Validates the file path.
     *
     * @param pathString the file path to validate.
     * @throws NoSuchFileException when file path is invalid
     */
    private static void validatePath(final String pathString)
            throws NoSuchFileException
    {
        if (pathString == null)
        {
            throw new NullPointerException("Invalid path: null");
        }


        final Path path;


        path = Paths.get(pathString);


        if (!Files.exists(path))
        {
            throw new NoSuchFileException("Invalid path: \"" +
                                          pathString + "\" does not exist");
        }


        if (!Files.isRegularFile(path))
        {
            throw new IllegalArgumentException("The path \"" + pathString +
                                               "\" is not a file.");
        }
    }
}
