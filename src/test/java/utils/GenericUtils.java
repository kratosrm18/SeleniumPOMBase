package utils;

import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public final class GenericUtils {

    public static void clickOnWebElementOfList(List<WebElement> list, String elementTxt) {
        for (WebElement webElement : list) {
            if (webElement.getText().equalsIgnoreCase(elementTxt)) {
                webElement.click();
                break;
            }
        }
    }

    public static boolean isWebElementOnList(List<WebElement> list, String elementTxt) {

        for (WebElement webElement : list) {
            if (webElement.getText().equalsIgnoreCase(elementTxt)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isPropertyOnWebElementAttribute(WebElement webElement, String attribute, String propertyToVerify) {
        List<String> propertyLst = List.of(webElement.getAttribute(attribute).split(" "));

        for (String property : propertyLst) {
            if (property.equalsIgnoreCase(propertyToVerify)) {
                return true;
            }
        }

        return false;
    }

    public static void selectWebElementByAttribute(List<WebElement> itemElement, String attribute, String comparator) {
        for (WebElement currentElement : itemElement) {
            if (currentElement.getAttribute(attribute).equals(comparator)) {
                currentElement.click();
                break;
            }
        }
    }

    public static List<String> convertDateToArray(String date) {
        List<String> dateSplit;
        Pattern dateRegex = Pattern.compile("[/[^\\d ]/g]");
        dateSplit = new ArrayList<String>(Arrays.asList((date.replaceAll(String.valueOf(dateRegex), " ")).trim().split(" ")));
        dateSplit.removeAll(Arrays.asList("", null));
        return dateSplit;
    }

    public static String checkAndExtractLeadingZerosFromDate(String element) {
        String finalElement = element;
        if (element.length() > 1) {
            finalElement = element.charAt(0) == '0' ? element.substring(1) : element;
        }
        return finalElement;
    }

    public static String formatTheDate(String date) {
        List<String> dateSplit = convertDateToArray(date);
        return checkAndExtractLeadingZerosFromDate(dateSplit.get(0)) + "/" + checkAndExtractLeadingZerosFromDate(dateSplit.get(1)) + "/" + dateSplit.get(2);
    }

    public static String formatDateInTable(String date) {
        List<String> dateSplit = convertDateToArray(date);
        return dateSplit.get(2) + "-" + dateSplit.get(0) + "-" + dateSplit.get(1);
    }

    public static String getTodayDateWithFormat(String pattern) {
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime todayDate = LocalDateTime.now();
        return formatterDate.format(todayDate);
    }

    public static String formatDate(String dateToFormat, String inFormat, String outFormat) throws Exception{
        SimpleDateFormat dateIn = new SimpleDateFormat(inFormat);
        SimpleDateFormat dateOut = new SimpleDateFormat(outFormat);

        return dateOut.format(dateIn.parse(dateToFormat));
    }

    public static int getDaysBetweenASetOfDates(String startDate, String endDate) {
        int daysRequested = 0;
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Calendar calendarStartDate = Calendar.getInstance();
        Calendar calendarEndDate = Calendar.getInstance();

        try {
            calendarStartDate.setTime(simpleDateFormat.parse(formatDateInTable(startDate)));
            calendarEndDate.setTime(simpleDateFormat.parse(formatDateInTable(endDate)));

        } catch (ParseException e) {
            throw new RuntimeException("Failed to format Dates: " + e);
        }

        while (!calendarStartDate.after(calendarEndDate)) {
            int dayNumber = calendarStartDate.get(Calendar.DAY_OF_WEEK);

            if ((dayNumber != Calendar.SATURDAY) && (dayNumber != Calendar.SUNDAY)) {
                daysRequested++;
            }

            calendarStartDate.add(Calendar.DATE, 1);
        }

        return daysRequested;
    }

    public static int getIndexOfElementOnList(List<WebElement> list ,String question) {
        boolean isFound = false;
        int index = 0;

        try {
            while (!isFound) {
                WebElement element = list.get(index);

                if (element.getText().contains(question)) {
                    isFound = true;
                } else {
                    index++;
                }
            }
        } catch (Exception e) {
            System.out.println("IndexOutOfBoundsException => " + e.getMessage());
        }

        return index;
    }

}
