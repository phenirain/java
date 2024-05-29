package org.example;

import java.io.IOException;

public class Cooker extends Resident {
    public Cooker(String name, int reputation) {
        super(name, reputation);
    }

    @Override
    public void performAction(int reputation, double award) throws IOException {
        System.out.printf("Повар - %s выполнил работу\n", getName());
        plusReputation(reputation);
        plusBalance(award);
        getInfo();
    }
}