package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

public class MealTestData {

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();
    public static final int MEAL_ID = BaseEntity.START_SEQ + 2;

    public static final Meal meal1 = new Meal(MEAL_ID, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Meal meal2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);
}
