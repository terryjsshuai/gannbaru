package design.mode.bridge;

import design.mode.bridge.impl.Circle;
import design.mode.bridge.impl.GreenCircle;
import design.mode.bridge.impl.RedCircle;

public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}