/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 03 04, 2024
 * PROJECT NAME: Rectangle.java
 * DESCRIPTION: Rectangle
 * worked with Carlos, Nassir, Luke, Kierra, Trace
 */


public class Rectangle extends Shape {
    public double height,width;

    public Rectangle(double x,double y ,double width,double height) {
        super();
        setX(x);
        setY(y);
        this.height=height;
        this.width=width;
    }

    public double getArea(){
        return height*width;
    }

    public double getPerimeter(){
        return 2*(height+width);
    }

    public void drawShape(){
        System.out.println("x = "+x+"\ny = "+y+"\nHeight = "+height+"\nWidth = "+width+"\nColor = "+c+"\nFill = "+fill+"\n");
    }

}
