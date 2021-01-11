package com.example.learn.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author zyred
 * @since v 0.1
 **/
public class DateTimeTest {

    public static int inMonthCurrentLength(String time) {
        LocalDate parse = LocalDate.parse(time);
        int dayOfMonth = parse.lengthOfMonth();

        LocalDate now = LocalDate.now();
        // 如果是当月的，那么最大天数获取截至到今天
        if (Objects.equals(parse.getMonthValue(), now.getMonthValue())) {
            dayOfMonth = now.getDayOfMonth();
        }
        return dayOfMonth;
    }

    public static int inYearCurrentLength(String time) {
        LocalDate parse = LocalDate.parse(time);
        int length = 12;

        LocalDate now = LocalDate.now();
        // 如果是本年的，那么最大天数获取截至到当月
        if (Objects.equals(parse.getYear(), now.getYear())) {
            length = now.getMonthValue();
        }
        return length;
    }

    private static String getMonthRunning(int i, String formatter, String time){
        return LocalDateTime.of(LocalDate.parse(time), LocalTime.MIN)
                .minusMonths(i).format(DateTimeFormatter.ofPattern(formatter));
    }

    public static LinkedList<String> getMonthRunning(String timer, String formatter) {
//        validateTime(timer);
//        timer = checkIfNowYear(timer);
        int len = inYearCurrentLength(timer);
        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            linkedList.add(getMonthRunning(i, formatter, timer));
        }
        return linkedList;
    }

    public static void main(String[] args) {
//        System.out.println(inMonthCurrentLength("2020-10-01"));
//        LocalDate localDate = LocalDate.now();
////        Date yearEnd = Date.from().atStartOfDay(ZoneId.systemDefault()).toInstant());
//        LocalDate with = localDate.with(TemporalAdjusters.lastDayOfYear());
//        String format = LocalDateTime.of(LocalDate.parse(with.toString()), LocalTime.MIN)
//                .minusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM"));
//
//        System.out.println(format);
//        int length = inYearCurrentLength("2020-11-01");
//        System.out.println(length);
        getMonthRunning("2020-11-01", "yyyy-MM");
    }
}
