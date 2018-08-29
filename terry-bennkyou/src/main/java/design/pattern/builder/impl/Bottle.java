package design.pattern.builder.impl;

import design.pattern.builder.Packing;

public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}