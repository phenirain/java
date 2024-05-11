package org.example;

public class Ork extends Resident {

    public Ork(String name, int reputation) {
        super(name, reputation);
    }

    @Override
    public void performAction(int reputation, double award) {
        System.out.printf("Орк - %s защитил замок\n", getName());
        plusReputation(reputation);
        plusBalance(award);
        getInfo();
    }
}