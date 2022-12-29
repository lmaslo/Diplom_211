package order.models;

import java.util.ArrayList;
import java.util.List;

import static utils.RandomString.getRandomString;

public class Order {
    private List<String> ingredients;

    public Order() {
    }

    public Order(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }


    public Order createOrderGeneration() {
        Order createOrderRequest = new Order();
        createOrderRequest.ingredients = new ArrayList<>();
        createOrderRequest.ingredients.add(0, "61c0c5a71d1f82001bdaaa6d");
        createOrderRequest.ingredients.add(1, "61c0c5a71d1f82001bdaaa6f");
        return createOrderRequest;
    }

    public Order createIncorrectOrderGeneration() {
        Order createOrderRequest = new Order();
        createOrderRequest.ingredients = new ArrayList<>();
        createOrderRequest.ingredients.add(0, getRandomString(10));
        createOrderRequest.ingredients.add(1, getRandomString(10));
        return createOrderRequest;
    }
}
