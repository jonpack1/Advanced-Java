
/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 02 20, 2024
 * PROJECT NAME: PersonMain.java
 * DESCRIPTION: PersonMain
 * worked with Carlos, Nassir, Trace, Kierra, Luke
 */
import java.util.Calendar;
import java.util.GregorianCalendar;


public class OCCCDate {
    // Instance variables
    private int dayOfMonth;
    private int monthOfYear;
    private int year;

    // Instance variables for formatting options
    private boolean dateFormat;
    private boolean dateStyle;
    private boolean dateDayName;
    private GregorianCalendar gc;

    // Constants for formatting options
    public static final boolean FORMAT_US = true;
    public static final boolean FORMAT_EURO = false;
    public static final boolean STYLE_NUMBERS = true;
    public static final boolean STYLE_NAMES = false;
    public static final boolean SHOW_DAY_NAME = true;
    public static final boolean HIDE_DAY_NAME = false;

    // Default constructor
    public OCCCDate() {
        gc = new GregorianCalendar();
        this.dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
        this.monthOfYear = gc.get(Calendar.MONTH) + 1;
        this.year = gc.get(Calendar.YEAR);

        dateFormat = FORMAT_US;
        dateStyle = STYLE_NUMBERS;
        dateDayName = SHOW_DAY_NAME;
    }

    // Parameterized constructor
    public OCCCDate(int day, int month, int year) {
        // Adjust zero and negative values
        if (day <= 0) {
            day += 31; // Adjust to previous month's last day
            month--; // Move to previous month
        }
        if (month <= 0) {
            month += 12; // Adjust to December of previous year
            year--; // Move to previous year
        }

        GregorianCalendar gc = new GregorianCalendar();
        gc.set(year, month - 1, day); //might need to be plus 1
        this.dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
        this.monthOfYear = gc.get(Calendar.MONTH) + 1;
        this.year = gc.get(Calendar.YEAR);
    }
    public OCCCDate(OCCCDate d) {
        gc = new GregorianCalendar();
        this.dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
        this.monthOfYear = gc.get(Calendar.MONTH) + 1;
        this.year = gc.get(Calendar.YEAR);

        dateFormat = FORMAT_US;
        dateStyle = STYLE_NUMBERS;
        dateDayName = SHOW_DAY_NAME;
    }


    // Getter methods
    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getMonthOfYear() {
        return monthOfYear;
    }

    public int getYear() {
        return year;
    }

    public String getDayName() {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return days[gc.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public String getMonthName() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return months[gc.get(Calendar.MONTH)];
    }

    // Method to set formatting options
    public void setDateFormat(boolean dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setStyleFormat(boolean dateStyle) {
        this.dateStyle = dateStyle;
    }

    public void setDateName(boolean dateName) {
        this.dateDayName = dateName;
    }

    // Method to calculate difference in years between this date and now
    public int getDifferenceInYear() {
        GregorianCalendar currentGC = new GregorianCalendar();
        int currentYear = currentGC.get(Calendar.YEAR);
        return currentYear - year;
    }

    // Method to calculate difference in years between this date and another date
    public int getDifferenceInYears(OCCCDate d) {
        return d.getYear() - this.year;
    }

    // Method to compare two OCCCDate objects
    public boolean equals(OCCCDate dob) {
        return this.dayOfMonth == dob.getDayOfMonth() &&
                this.monthOfYear == dob.getMonthOfYear() &&
                this.year == dob.getYear();
    }

    // Method to return string representation in specified format
    public String toString(boolean format, boolean style, boolean showDayName) {
        StringBuilder result = new StringBuilder();

        // Include day name if specified
        if (showDayName) {
            String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            int dayOfWeek = new GregorianCalendar(year, monthOfYear - 1, dayOfMonth).get(Calendar.DAY_OF_WEEK);
            result.append(dayNames[dayOfWeek - 1]).append(", ");
        }

        // Include date representation based on format and style
        if (format == FORMAT_US) {
            if (style == STYLE_NUMBERS) {
                result.append(String.format("%02d", monthOfYear)).append("/");
                result.append(String.format("%02d", dayOfMonth)).append("/");
                result.append(year);
            } else {
                String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                result.append(monthNames[monthOfYear - 1]).append(" ");
                result.append(dayOfMonth).append(", ");
                result.append(year);
            }
        } else {
            if (style == STYLE_NUMBERS) {
                result.append(String.format("%02d", dayOfMonth)).append("/");
                result.append(String.format("%02d", monthOfYear)).append("/");
                result.append(year);
            } else {
                String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                result.append(dayOfMonth).append(" ");
                result.append(monthNames[monthOfYear - 1]).append(", ");
                result.append(year);
            }
        }

        return result.toString();
    }
}


