package design.mode.builder;

import design.mode.builder.impl.ChickenBurger;
import design.mode.builder.impl.Coke;
import design.mode.builder.impl.Pepsi;
import design.mode.builder.impl.VegBurger;

public class MealBuilder {

    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}