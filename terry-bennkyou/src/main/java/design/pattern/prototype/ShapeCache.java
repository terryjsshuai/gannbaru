package design.pattern.prototype;

import design.pattern.prototype.impl.Circle;
import design.pattern.prototype.impl.Rectangle;
import design.pattern.prototype.impl.Square;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShapeCache {

    private static Map<String, Shape> shapeMap = new ConcurrentHashMap<>();

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    // 对每种形状都运行数据库查询，并创建该形状
    // shapeMap.put(shapeKey, shape);
    // 例如，我们要添加三种形状
    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);

        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(), square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeMap.put(rectangle.getId(), rectangle);
    }
}
