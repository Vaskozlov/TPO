package org.vaskozlov.lab1;

public class Vial {
    private Fish content;

    public Vial() {}

    public void putFish(Fish fish) {
        if (fish == null) {
            throw new IllegalArgumentException("Действие запрещено");
        }

        if (content != null) {
            throw new IllegalStateException("Флакончик уже содержит рыбку");
        }

        this.content = fish;
    }

    public Fish getContent() { return content; }
    public boolean isEmpty() { return content == null; }
}