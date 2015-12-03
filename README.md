# JAVA-Full-Code

// The Circle class definition
public class Circle {    // Save as "Circle.java"
   // Public constants
   public static final double DEFAULT_RADIUS = 8.8;
   public static final String DEFAULT_COLOR  = "red";

   // Private variables
   private double radius;
   private String color;

   // Constructors
   public Circle() {                   // 1st Constructor
      radius = DEFAULT_RADIUS;
      color  = DEFAULT_COLOR;
   }
   public Circle(double radius) {      // 2nd Constructor
      this.radius = radius;
      color = DEFAULT_COLOR;
   }
   public Circle(double radius, String color) { // 3rd Constructor
      this.radius = radius;
      this.color = color;
   }

   // Public getter and setter for private variables
   public double getRadius() {
      return radius;
   }
   public void setRadius(double radius) {
      this.radius = radius;
   }
   public String getColor() {
      return color;
   }
   public void setColor(String color) {
      this.color = color;
   }

   // toString() to provide a short description of this instance
   public String toString() {
      return "Circle with radius = " + radius + " and color of " + color;
   }

   // Public methods
   public double getArea() {
      return radius*radius*Math.PI;
   }
}


