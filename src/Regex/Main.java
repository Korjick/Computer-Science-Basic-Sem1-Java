package Regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String txt = in.next();

        System.out.println(Pattern.matches("^(([a-zA-Zа-яА-Я]{1})|([a-zA-Zа-яА-Я]{1}[a-zA-Zа-яА-Я]{1})|([a-zA-Zа-яА-Я]{1}[0-9]{1})|" +
                "([0-9]{1}[a-zA-Zа-яА-Я]{1})|([a-zA-Z0-9а-яА-Я][а-яА-Яa-zA-Z0-9-_]{1,61}[а-яА-Яa-zA-Z0-9]))\\.([а-яА-Яa-zA-Z]{2,6}|" +
                "[а-яА-Яa-zA-Z0-9-]{2,30}\\.[а-яА-Яa-zA-Z]{2,3})$", txt));

        txt = in.next();
        Pattern emailEnd = Pattern.compile("(?:@)((?:[a-z0-9](?:[a-z0-9-]*[a-z0-9]?)\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        Matcher matcher = emailEnd.matcher(txt);
        if (matcher.find()) {
            String res = matcher.group();
            String[] resSplit = res.split("\\.");
            System.out.println(resSplit[0].substring(1) + " - " + resSplit[resSplit.length - 1]);
        }
    }
}
