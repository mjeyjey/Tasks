interface Shape{
  double calculatedArea();
  double calculatedPerimeter();
}

abstract class AbstractShape implements Shape{
    String color;
    double length;
    double width;
    public AbstractShape(String color, double length, double width){
        this.color = color;
        this.length = length;
        this.width = width;
    }
    @Override
    public double calculatedArea() {
        // return Math.PI * radius * radius;;
        return 0;
    }
    @Override
    public double calculatedPerimeter() {
        // return 2 * Math.PI * radius;
        return 0;
    }
}
class Circle extends AbstractShape {
    double radius;
    public Circle(String color, double length, double width, double radius) {
        super(color,0,0);
        this.radius = radius;
    }
    @Override
    public double calculatedArea() {
        return Math.PI * radius * radius;
    }
    @Override
    public double calculatedPerimeter() {
        return 2 * Math.PI * radius;
    }
}
class Rectangle extends AbstractShape {
    public Rectangle(String color, double length, double width) {
        super(color, length, width);
    }
    @Override
      public double calculatedArea() {
        // return Math.PI * radius * radius;
        return length * width;
    }
    @Override
    public double calculatedPerimeter() {
        // return 2 * Math.PI * radius;
        return 2 * (length + width);
    }
}

public class Task15 {
    public static void main(String[] args) {
        Circle c = new Circle(null, 0, 0, 5);
        Rectangle r = new Rectangle(null, 4, 6);

        System.out.println("Circle: ");
        System.out.println("Area: "+ c.calculatedArea());
        System.out.println("Perimeter: "+ c.calculatedPerimeter());
        System.out.println("\nRectangle: ");
        System.out.println("Area: "+ r.calculatedArea());
        System.out.println("Perimeter: "+ r.calculatedPerimeter());
    }
}