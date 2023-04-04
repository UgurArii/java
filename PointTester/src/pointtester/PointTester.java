package pointtester;

import java.awt.Point;


public class PointTester {

    public static void main(String[] args) {
       Point location1 = new Point(11,22);
       Point3D location2 = new Point3D(7, 6, 64);
       
       System.out.println("The 2D point is at (" + location1.x
 + ", " + location1.y + ")");
       
       System.out.println("It's being moved to (4, 13)");
 location1.move(4,13);
 
       System.out.println("The 2D point is now at (" + location1.x
 + ", " + location1.y + ")"); 
       
       System.out.println("It's being moved -10 units on both the x "
 + "and y axes");
location1.translate(-10,-10);

        System.out.println("The 2D point ends up at (" + location1.x
+ ", " + location1.y + ")\n");
        
        System.out.println("The 3D point is at (" + location2.x
 + ", " + location2.y + ", " + location2.z + ")");
        
        System.out.println("It's being moved to (10, 22, 71)");
        location2.move(10,22,71);
        
        System.out.println("The 3D point is now at (" + location2.x
+ ", " + location2.y + ", " + location2.z + ")");
        
        System.out.println("It's being moved -20 units on the x, y "
+ "and z axes");

        location2.translate(-20,-20,-20);
        System.out.println("The 3D point ends up at (" + location2.x
 + ", " + location2.y + ", " + location2.z + ")");
        
    }
    
}
