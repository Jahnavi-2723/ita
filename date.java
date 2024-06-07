/*Define a class called "Date" which has 3 properties: "private int month", "private int day", "private int year
In addition, you should write the following methods:
(a) provide a getter and a setter for the data field "private int year" (no need to write getters and
setters for the other two data fields);
(b) write a method called "isLeapYear" which checks if the year contained in the data field
"private int year" is a leap year. A leap year is
(1) a year that is divisible by 4, i.e., "year % 4 =0^ prime prime
*/
import java.util.Scanner;

public class Date {
    private int month;
    private int day;
    private int year;

    // Constructor
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public boolean isLeapYear() {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Date myDate = new Date(0, 0, 0); 
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        myDate.setYear(year);
        if (myDate.isLeapYear()) {
            System.out.println(year + " is a leap year.");
        } else {
            System.out.println(year + " is not a leap year.");
        }
        scanner.close();
    }
}
