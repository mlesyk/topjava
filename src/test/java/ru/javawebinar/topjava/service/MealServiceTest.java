package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.MealTestData.MATCHER;

/**
 * Created by Maks on 17.04.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL_ID, ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(meal2), service.getAll(ADMIN_ID));
    }


    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(MEAL_ID, 1);
    }


    @Test
    public void testSave() throws Exception {
        Meal testMealCreated = new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 15, 0), "ланч", 5110);
        service.save(testMealCreated, ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(meal2, testMealCreated, meal1), service.getAll(ADMIN_ID));
    }

    @Test
    public void testGet() throws Exception {
        Meal testMeal = service.get(MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(meal1, testMeal);
    }


    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(MEAL_ID, ADMIN_ID + 1);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal testMealUpdated = new Meal(meal1.getId()
                , meal1.getDateTime()
                , meal1.getDescription()
                , meal1.getCalories() + 200);
        service.update(testMealUpdated, ADMIN_ID);
        MATCHER.assertEquals(testMealUpdated, service.get(MEAL_ID, ADMIN_ID));
    }

    @Test( expected = NotFoundException.class )
    public void testUpdateNotFound() throws Exception {
        service.update(meal1, ADMIN_ID + 1);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(meal2, meal1), service.getAll(ADMIN_ID));
    }

    @Test
    public void testDeleteAll() throws Exception {
        service.deleteAll(ADMIN_ID);
        Assert.assertEquals(0, service.getAll(ADMIN_ID).size());
    }
}