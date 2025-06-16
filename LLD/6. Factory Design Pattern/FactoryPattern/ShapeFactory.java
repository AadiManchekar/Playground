import Shape.Circle;
import Shape.Rectangle;
import Shape.Shape;

public class ShapeFactory {
    Shape getShape(String input) {
        switch (input) {
            case "CIRCLE":
                return new Circle();
            case "RECTANGLE":
                return new Rectangle();

            default:
                return null;
        }
    }
}
