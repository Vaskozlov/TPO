package org.vaskozlov.lab1;

public class Arthur {
    private boolean eyesActive = true;

    public Arthur() {}

    public void lookAt(Vial vial) {
        if (!eyesActive) {
            throw new IllegalStateException("Артур не может смотреть");
        }
    }

    public void blink() {
    }

    public boolean areEyesActive() { return eyesActive; }
}