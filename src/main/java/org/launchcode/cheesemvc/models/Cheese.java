package org.launchcode.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity     // Declare a model class an entity if you want ORM engine to store it into a DB
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3,max=20, message = "Cheese name must be 3 to 20 characters long")
    private String name;
    @NotNull
    @Size(min = 1, message="Description must not be left empty")
    private String description;

    @ManyToOne
    private Category cat;

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category getCat() {
        return cat;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

    public Cheese() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
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
