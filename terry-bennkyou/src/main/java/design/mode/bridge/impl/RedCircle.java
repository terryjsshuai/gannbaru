package design.mode.bridge.impl;

import design.mode.bridge.DrawAPI;

public class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        String out = "Drawing Circle[ color: red, radius: " + radius + ", x: " + x + ", " + y + "]";
        System.out.println(out);
    }
}