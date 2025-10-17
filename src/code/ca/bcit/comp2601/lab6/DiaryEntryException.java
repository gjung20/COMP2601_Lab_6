package ca.bcit.comp2601.lab6;

/**
 * Custom checked exception.
 *
 * @author Erica Baartman
 * @author Grace Jung
 * @author Irene Nagli
 * @author Nate Morrison
 *
 * @version 1.3
 */
public class DiaryEntryException extends Exception
{
    public DiaryEntryException(final String message)
    {
        super(message);
    }
}
