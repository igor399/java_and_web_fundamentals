package by.epam.lab.beans;

import by.epam.lab.services.*;

public class Service implements Item {
    private final String name;
    private final Byn totalCost;
    private int numUsers;

    public Service() {
        name = null;
        totalCost = null;
    }

    public Service(String name, Byn totalCost, int numUsers) {
        this.name = name;
        this.totalCost = totalCost;
        this.numUsers = numUsers;
    }

    public String getName() {
        return name;
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
        return name + GlobalConstants.SEMICOLON + totalCost + GlobalConstants.SEMICOLON + numUsers;
    }
}
