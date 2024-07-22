/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 03 04, 2024
 * PROJECT NAME: Circle.java
 * DESCRIPTION: Circle
 * worked with Carlos, Nassir, Luke, Kierra, Trace
 */






import java.awt.Color;

public class TestShape {

    public static void main(String args[]){

        // welcome message
        System.out.println("Welcome to Shape Test!");
        System.out.println();

        // Ellipse
        Shape shape1=new Ellipse(7,9,5,2);
        shape1.setColor(Color.BLUE);
        shape1.setFill(Shape.SHAPE_SET_FILL);
        shape1.drawShape();
        System.out.println("The perimeter of this shape is " + shape1.getPerimeter());
        System.out.println("The area of this shape is " + shape1.getArea());
        System.out.println();

        // Circle
        Shape shape2=new Circle(5,7,2);
        shape2.setColor(Color.RED);
        shape2.setFill(Shape.SHAPE_SET_OUTLINE);
        shape2.drawShape();
        System.out.println("The perimeter of this shape is " + shape2.getPerimeter());
        System.out.println("The area of this shape is " + shape2.getArea());
        System.out.println();

        // Rectangle
        Shape shape3=new Rectangle(1, 2, 3, 4);
        shape3.setColor(Shape.SHAPE_DEFAULT_COLOR);
        shape3.drawShape();
        System.out.println("The perimeter of this shape is " + shape3.getPerimeter());
        System.out.println("The area of this shape is " + shape3.getArea());
        System.out.println();

        // Square
        Shape shape4=new Square(15,12,15);
        shape4.drawShape();
        System.out.println("The perimeter of this shape is " + shape4.getPerimeter());
        System.out.println("The area of this shape is " + shape4.getArea());
        System.out.println();

//   Additional shape - fill in the XXXXX and enable by removing //
        Shape shape5=new Hexagon(11, 12, 14);
        shape5.drawShape();
        System.out.println("The perimeter of this shape is " + shape5.getPerimeter());
        System.out.println("The area of this shape is " + shape5.getArea());
        System.out.println();
    }
}