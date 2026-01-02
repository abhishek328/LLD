package parkingLot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateTimeParser {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d MMMM h:mm a yyyy", Locale.ENGLISH);
    
    /**
     * Parses a date-time string in the format "21 May 7:30 AM 2025"
     * and returns a LocalDateTime object.
     * 
     * @param dateTimeString The date-time string to parse
     * @return LocalDateTime object parsed from the string
     * @throws DateTimeParseException if the string cannot be parsed
     */
    public static LocalDateTime parse(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.trim().isEmpty()) {
            throw new IllegalArgumentException("Date-time string cannot be null or empty");
        }
        
        try {
            return LocalDateTime.parse(dateTimeString, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(
                "Unable to parse date-time string: " + dateTimeString + 
                ". Expected format: 'd MMMM h:mm a yyyy' (e.g., '21 May 7:30 AM 2025')",
                dateTimeString,
                e.getErrorIndex()
            );
        }
    }
}

