package com.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        /*
         * Las apis para el manejo de fecha y hora generan objetos inmutables y el metodo mas parecido a 
         * un setter que te da la sensacion de estar modificando un objeto es el metodo with.
         * 
         * Los objetos no se crean con constructores, es decir invocando el operador new si no con 
         * metodos estaticos factory
         * 
         * Como se declara una variable de fecha y hora
         */

        LocalDate today = LocalDate.now();

        System.out.println("Hoy es: " + today);

        LocalDateTime fechaYhora = LocalDateTime.now();

        System.out.println("Fecha y hora actual: " + fechaYhora);

        // Si se quiere construir un objeto localdate con una fecha concreta es posible
        LocalDate birthday = LocalDate.of(1991, Month.APRIL, 02);

        /*
         * Para ejemplificar l uso del metodo with vamos a comprobar si una fecha cae en los ultimos 5 dias del mes
         */
        LocalDate fechaEnCuestion = LocalDate.of(2024, Month.OCTOBER, 27);

        LocalDate fec5DiasAntes = fechaEnCuestion.with(TemporalAdjusters.lastDayOfMonth()).minusDays(5);

        if (fechaEnCuestion.isAfter(fec5DiasAntes)) {
            System.out.println("Si, la fecha en cuestion esta en los ultimos 5 dias");
        } else {
            System.out.println("No, la fecha en cuestion no esta en los ultimos 5 dias");
        }

        // Mostrar el dia de la semana en el idioma de la configuracion regional del equipo
        DayOfWeek dayOfWeek = today.getDayOfWeek();

        System.out.println("el dia de hoy es " + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()));
        // Mostrar el dia en otro idioma 
        System.out.println("el dia de hoy es " + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.of("en", "usa")));

        System.out.println("el dia de hoy es " + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.of("no", "NO")));

        /*
         * calcular la edad a partir de una fecha de nacimiento
         */
        long edad = ChronoUnit.YEARS.between(birthday, today);
        System.out.println("Tengo " + edad + " años");

        Period p = Period.between(birthday, today);
        long p2 = ChronoUnit.DAYS.between(birthday, today);
        System.out.println("You are " + p.getYears() + " years, " + p.getMonths()
                + " months, and " + p.getDays()
                + " days old. (" + p2 + " days total)");

        
        
        LocalDate nextBDay = birthday.withYear(today.getYear());

        //If your birthday has occurred this year already, add 1 to the year.
        if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
            nextBDay = nextBDay.plusYears(1);
        }

        Period p3 = Period.between(today, nextBDay);
        long p4 = ChronoUnit.DAYS.between(today, nextBDay);
        System.out.println("There are " + p3.getMonths() + " months, and "
                + p3.getDays() + " days until your next birthday. ("
                + p4 + " total)");
    }
}
