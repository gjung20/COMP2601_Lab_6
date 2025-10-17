package ca.bcit.comp2601.lab6;

/**
 * Represents a Diary Entry object.
 *
 * @author Erica Baartman
 * @author Grace Jung
 * @author Irene Nagli
 * @author Nate Morrison
 * @version 1.3
 */
public class DiaryEntry
{
    private static final String PROHIBITED_STRING = "bcit";

    private static final int PARTS_IN_DATE = 3;
    private static final int YEAR_INDEX    = 0;
    private static final int MONTH_INDEX   = 1;
    private static final int DAY_INDEX     = 2;

    private static final int MIN_YEAR     = 1;
    private static final int CURRENT_YEAR = 2025;

    private static final int JANUARY  = 1;
    private static final int DECEMBER = 12;

    private static final int MIN_DAY_OF_MONTH = 1;
    private static final int MAX_DAY_OF_MONTH = 31;

    private final String date;
    private final String content;


    /**
     * Constructs A Diary Entry object.
     *
     * @param date    the diary entry's date
     * @param content the diary entry's content
     * @throws DiaryEntryException when date and content is invalid
     */
    public DiaryEntry(final String date,
                      final String content)
            throws DiaryEntryException
    {
        validateDate(date);
        validateContent(content);

        this.date    = date;
        this.content = content;
    }


    /**
     * Constructs a Diary Entry object with date as null.
     *
     * @param content the diary entry's content
     * @throws DiaryEntryException when content is invalid
     */
    public DiaryEntry(final String content)
            throws DiaryEntryException
    {
        this(null,
             content);
    }


    /**
     * Validates the date.
     * Date must match YYYY-MM-DD format
     *
     * @param date the date to validate
     * @throws DiaryEntryException when date is invalid
     */
    public static void validateDate(final String date)
            throws DiaryEntryException
    {
        if(date == null)
        {
            return;
        }

        final String[] parts;
        parts = date.split("-");

        if (parts.length != PARTS_IN_DATE)
        {
            throw new DiaryEntryException("Date must be in YYYY-MM-DD format");
        }

        final String yearStr;
        final String monthStr;
        final String dayStr;

        final int year;
        final int month;
        final int day;

        yearStr  = parts[YEAR_INDEX];
        monthStr = parts[MONTH_INDEX];
        dayStr   = parts[DAY_INDEX];

        try
        {
            year  = Integer.parseInt(yearStr);
            month = Integer.parseInt(monthStr);
            day   = Integer.parseInt(dayStr);
        }
        catch (final NumberFormatException e)
        {
            throw new DiaryEntryException("Date must contain numeric values only");
        }


        if (year < MIN_YEAR || year > CURRENT_YEAR)
        {
            throw new DiaryEntryException("Year must be between " +
                                          MIN_YEAR +
                                          " and " +
                                          CURRENT_YEAR);
        }


        if (month < JANUARY || month > DECEMBER)
        {
            throw new DiaryEntryException("Month must be between " +
                                          JANUARY +
                                          " and " +
                                          DECEMBER);
        }


        if (day < MIN_DAY_OF_MONTH || day > MAX_DAY_OF_MONTH)
        {
            throw new DiaryEntryException("Day must be between " +
                                          MIN_DAY_OF_MONTH +
                                          " and " +
                                          MAX_DAY_OF_MONTH);
        }
    }


    /**
     * Validates the content.
     * Content must be non-null, non-blank, and must not contain PROHIBITED_STRING (e.g. "bcit")
     * (case-insensitive)
     *
     * @param content the content to validate
     * @throws DiaryEntryException when content is invalid
     */
    private static void validateContent(final String content)
            throws DiaryEntryException
    {
        if (content == null || content.isBlank())
        {
            throw new DiaryEntryException("Content cannot be null or blank");
        }


        if (content.toLowerCase()
                   .contains(PROHIBITED_STRING))
        {
            throw new DiaryEntryException("Content cannot contain string: \"" +
                                          PROHIBITED_STRING +
                                          "\" (case-insensitive)");
        }
    }


    /**
     * Returns content.
     *
     * @return the diary entry's content.
     */
    public String getContent()
    {
        return content;
    }


    /**
     * Returns date.
     *
     * @return the diary entry's date.
     */
    public String getDate()
    {
        return date;
    }
}
