package org.example;

import javax.imageio.IIOException;
import java.io.IOException;

public class Resident {
    private String name;
    private int reputation;
    private double balance;

    static Log myLog;
    static{
        try
        {
            myLog = new Log("resident.log");
        } catch(
        IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    protected void getInfo() {
        System.out.printf("Имя: %s, Репутация: %d, Баланс: %f\n", name, reputation, balance);
    }

    protected double getBalance() {
        return balance;

    }

    protected String getName() {
        return name;
    }

    protected void plusBalance(double amount) throws IOException {
        balance += amount;
        myLog.logger.info("%s был баланс: %f, стал: %f".formatted(this.getClass().getCanonicalName(),
                balance - amount, balance));
    }

    protected void plusReputation(int amount) throws IOException {
        reputation += amount;
        myLog.logger.info("%s была репутация: %d, стала: %d".formatted(this.getClass().getCanonicalName(),
                reputation = amount, reputation));
    }

    public Resident(String name, int reputation){
        this.name = name;
        this.reputation = reputation;
        balance = 0;
        myLog.logger.info("Инициализация нового резидента");
    }

    public void performAction(int reputation, double award) throws IOException {
        plusReputation(reputation);
        plusBalance(award);
        getInfo();
    }
}
