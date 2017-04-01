package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

/**
 * Created by Maks on 01.04.2017.
 */
public interface MealDao {
    List<Meal> listMeals();
}
