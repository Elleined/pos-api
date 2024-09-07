package com.elleined.pos_api.service.utils;

import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public interface Formatter {

    static String formatDateWithoutYear(LocalDateTime dateTime) {
        String month = dateTime.getMonth().name();
        String finalMonth = month
                .substring(0, 1)
                .toUpperCase() +
                month.substring(1).toLowerCase();

        String day = String.valueOf(dateTime.getDayOfMonth());
        return String.format("%s %s", finalMonth, day);
    }

    static String formatTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return formatter.format(dateTime);
    }

    static String formatDate(LocalDate orderDate) {
        String month = orderDate.getMonth().name();
        String finalMonth = month
                .substring(0, 1)
                .toUpperCase() +
                month.substring(1).toLowerCase();

        String day = String.valueOf(orderDate.getDayOfMonth());
        String year = String.valueOf(orderDate.getYear());
        return String.format("%s %s, %s", finalMonth, day, year);
    }

    static String formatDate(LocalDateTime orderDate) {
        String month = orderDate.getMonth().name();
        String finalMonth = month
                .substring(0, 1)
                .toUpperCase() +
                month.substring(1).toLowerCase();

        String day = String.valueOf(orderDate.getDayOfMonth());
        String year = String.valueOf(orderDate.getYear());
        return String.format("%s %s, %s", finalMonth, day, year);
    }

    static double formatDouble(double target) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedTarget = decimalFormat.format(target);
        return Double.parseDouble(formattedTarget);
    }

    static String formatImgFileName(MultipartFile attachment) {
        return Objects.requireNonNull(attachment.getOriginalFilename()).replace(" ", "_");
    }
}