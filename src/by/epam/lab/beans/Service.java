package by.epam.lab.beans;

import by.epam.lab.services.RoundMethod;
import static by.epam.lab.services.GlobalConstants.*;

public class Service implements Priceable {
    private String name;
    private Byn totalCost;
    private int numUsers;

    public Service(String name, Byn totalCost, int numUsers) {
        this.name = name;
        this.totalCost = totalCost;
        this.numUsers = numUsers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byn getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Byn totalCost) {
        this.totalCost = totalCost;
    }

    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    @Override
    public Byn getPrice() {
        return totalCost.multiply(1.0 / numUsers, RoundMethod.CEIL, 0);
    }

    @Override
    public String toString() {
        return name + SEMICOLON + totalCost + SEMICOLON + numUsers;
    }
}
