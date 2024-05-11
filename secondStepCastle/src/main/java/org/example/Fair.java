package org.example;

public class Fair extends Resident {
    public Fair(String name, int reputation) {
        super(name, reputation);
    }

    @Override
    public void performAction(int reputation, double award) {
        System.out.printf("Фея - %s убралась в замке\n", getName());
        plusReputation(reputation);
        plusBalance(award);
        getInfo();
    }
}
