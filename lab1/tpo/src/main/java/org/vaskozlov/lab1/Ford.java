package org.vaskozlov.lab1;

public class Ford {
    private Vial vialInHand;

    public Ford() {
    }

    public void takeVial(Vial vial) {
        this.vialInHand = vial;
    }

    public Vial getVialInHand() {
        return vialInHand;
    }

    public boolean offerFishToEar(Arthur arthur) {
        return vialInHand != null && !vialInHand.isEmpty();
    }
}