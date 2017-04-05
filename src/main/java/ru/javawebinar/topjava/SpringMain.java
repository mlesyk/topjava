package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));

            System.out.println(adminUserController.getAll());
            System.out.println(adminUserController.get(1));
            System.out.println(adminUserController.get(2));
            System.out.println(adminUserController.get(3));
            System.out.println(adminUserController.getByMail("email"));
            adminUserController.delete(1);
            System.out.println(adminUserController.getAll());


            MealRestController mealController = appCtx.getBean(MealRestController.class);
            System.out.println(mealController.getAll());
            System.out.println(mealController.get(1));
            System.out.println(mealController.get(2));
            System.out.println(mealController.get(3));
            mealController.delete(1);
            System.out.println(mealController.getAll());
            mealController.create(new Meal(LocalDateTime.of(2016, Month.MAY, 31, 20, 0), "Ужинок", 5102, AuthorizedUser.id()));
            System.out.println(mealController.getAll());
        }
    }
}
