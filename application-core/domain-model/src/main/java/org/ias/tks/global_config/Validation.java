package org.ias.tks.global_config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public Validation() {
    }

    public static boolean validateData(String parameter, ValidationParameter validationParameter) {
        String regex;
        Pattern pattern;
        Matcher matcher;

        switch(validationParameter) {
            case FIRSTNAME:
                regex = "^[a-zA-Z]+(\\p{javaWhitespace}[a-zA-Z]+)??$";
                pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(parameter);
                return !matcher.matches();

            case LASTNAME:
                regex = "^[a-zA-Z]+(-[a-zA-Z]+)??$";
                pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(parameter);

                return !matcher.matches();

            case CITY:
                regex = "^[a-zA-Z]+$";
                pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(parameter);

                return !matcher.matches();

            // to najprawdopodobniej nie działa
            case POSTAL_CODE:
                regex = "^[0-9]+-[0-9]+$";
                pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(parameter);

                return !matcher.matches();

            case EMAIL:
                regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
                pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(parameter);

                return !matcher.matches();

            case LOGIN:
                regex = "^([a-zA-Z])+([\\w]{2,})+$";
                pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(parameter);

                return !matcher.matches();

            case COSTUME_NAME:
                regex = "^[a-zA-Z]+(\\p{javaWhitespace}[a-zA-Z]+)?+$";
                pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(parameter);
                return !matcher.matches();

            case PASSWORD:
                regex = "^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{0,19}$";
                pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(parameter);

                return !matcher.matches();


            case PRICE:
                if(Double.parseDouble(parameter) <= 0 || Double.parseDouble(parameter) >= 200) {
                    return false;
                }
            default:
                return true;
        }
    }

}