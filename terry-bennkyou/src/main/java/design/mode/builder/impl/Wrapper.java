package design.mode.builder.impl;

import design.mode.builder.Packing;

public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}
