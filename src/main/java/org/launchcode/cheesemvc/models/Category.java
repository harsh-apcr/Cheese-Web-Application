package org.launchcode.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3,max=15, message = "Number of characters must be between 3 and 15 for name field")
    private String name;

    @NotNull
    @Size(min=1, message = "Description field must not be left empty")
    private String description;

    @OneToMany
    @JoinColumn(name = "cat_id")   // declare the relationship and
    // JoinColumn annotation adds a column in cheese table with name = "category_id" (as a foreign key)
    // with value = ID of the category that it corresponds to
    // spring maintains this list for us behind the scenes
    private List<Cheese> cheeses = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
