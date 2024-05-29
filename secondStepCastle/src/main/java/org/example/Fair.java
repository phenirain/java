package org.example;

import java.io.IOException;

public class Fair extends Resident {
    public Fair(String name, int reputation) {
        super(name, reputation);
    }

    @Override
    public void performAction(int reputation, double award) throws IOException {
        System.out.printf("Фея - %s убралась в замке\n", getName());
        plusReputation(reputation);
        plusBalance(award);
        getInfo();
    }
}
