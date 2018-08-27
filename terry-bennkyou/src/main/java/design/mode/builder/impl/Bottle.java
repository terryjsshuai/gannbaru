package design.mode.builder.impl;

import design.mode.builder.Packing;

public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}