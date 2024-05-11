package org.example;

public class Resident {
    private String name;
    private int reputation;
    private double balance;

    protected void getInfo() {
        System.out.printf("Имя: %s, Репутация: %d, Баланс: %f\n", name, reputation, balance);
    }

    protected double getBalance() {
        return balance;
    }

    protected String getName() {
        return name;
    }

    protected void plusBalance(double amount) {
        balance += amount;
    }

    protected void plusReputation(int amount) {
        reputation += amount;
    }

    public Resident(String name, int reputation){
        this.name = name;
        this.reputation = reputation;
        balance = 0;
    }

    public void performAction(int reputation, double award) {
        plusReputation(reputation);
        plusBalance(award);
        getInfo();
    }
}
