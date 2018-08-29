package design.pattern.bridge;

import design.pattern.bridge.impl.Circle;
import design.pattern.bridge.impl.GreenCircle;
import design.pattern.bridge.impl.RedCircle;

public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}