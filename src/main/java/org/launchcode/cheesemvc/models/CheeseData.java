package org.launchcode.cheesemvc.models;

import java.util.LinkedList;
import java.util.List;

public class CheeseDAO {
    public static List<Cheese> cheeses = new LinkedList<>();

    // getAll
    public static List<Cheese> getAll() {
        return cheeses;
    }
    // add

    public static void add(Cheese newCheese) {
        cheeses.add(newCheese);
    }
    // remove
    public static void remove(int id) {
        Cheese toRemove = getById(id);
        if (toRemove != null) cheeses.remove(toRemove);
    }


    // getById
    public static Cheese getById(int id) {
        for(Cheese cheese : cheeses) {
            if (cheese.getCheeseId() == id) {
                return cheese;
            }
        }
        return null;
    }
}
