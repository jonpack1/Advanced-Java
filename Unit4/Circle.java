/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 03 04, 2024
 * PROJECT NAME: Circle.java
 * DESCRIPTION: Circle
 * worked with Carlos, Nassir, Luke, Kierra, Trace
 */
public class Circle extends Ellipse {

    public Circle(double x, double y, double r) {
        super(x, y, r, r);
    }

    public double getPerimeter(){
        return 2*Math.PI*radius1;
    }

}
