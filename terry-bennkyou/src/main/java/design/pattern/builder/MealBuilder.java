package design.pattern.builder;

import design.pattern.builder.impl.ChickenBurger;
import design.pattern.builder.impl.Coke;
import design.pattern.builder.impl.Pepsi;
import design.pattern.builder.impl.VegBurger;

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