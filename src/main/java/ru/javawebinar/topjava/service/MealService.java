package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    public Meal save(Meal meal);

    public void delete(int id, int userId) throws NotFoundException;

    public Meal get(int id, int userId) throws NotFoundException;

    public List<Meal> getAll(int userId) throws NotFoundException;

    public void update(Meal meal, int userId);
}