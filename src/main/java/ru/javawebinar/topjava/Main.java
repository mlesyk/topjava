package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        Meal meal1 = new Meal(10002, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
        Meal meal2 = new Meal(10002, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

        System.out.println(Timestamp.valueOf(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0)));
        System.out.println(Timestamp.valueOf(LocalDateTime.of(2015, Month.JUNE, 1, 21, 0)));

    }
}
