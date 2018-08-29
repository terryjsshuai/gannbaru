package design.pattern.builder.impl;

import design.pattern.builder.Packing;

public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}
