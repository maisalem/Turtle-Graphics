/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) 
    {
       /*Turtle newTurtle=turtle;
       newTurtle.forward(sideLength);
       newTurtle.turn(90);
       newTurtle.forward(sideLength);
       newTurtle.turn(90);
       newTurtle.forward(sideLength);
       newTurtle.turn(90);
       newTurtle.forward(sideLength);
       newTurtle.turn(90);
       newTurtle.draw();
       */
       for(int x = 0; x < 4; x++){
           turtle.forward(sideLength);
           turtle.turn(90.0);
       }
        //throw new RuntimeException("implement me!");
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
       
    	/*sum of angles in a regular polygon is (sides-2)*180
    	therefore in regular polygon where all angles are equal, one angle will be: (sides-2)*180.0/sides  
    	*/
        return (sides-2)*180.0/sides;
       
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        return (int)(Math.round(360/(180-angle)));
        //throw new RuntimeException("implement me!");
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        for(int x = sides; x > 0; x--){
            turtle.forward(sideLength);
            turtle.turn(180.0 - calculateRegularPolygonAngle(sides));
        }
        //throw new RuntimeException("implement me!");
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        double rise = targetY - currentY;
        double run = targetX - currentX;
        double angle = Math.atan(run/rise)*360/(2*Math.PI) - currentHeading;
        return angle < 0.0 ? 360.0 + angle: angle;
        //throw new RuntimeException("implement me!");
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> headings = new ArrayList<Double>();
        double relHeading = 0.0;
        for(int x = 0; x < xCoords.size()-1; x++){
            relHeading = calculateHeadingToPoint(relHeading, xCoords.get(x), yCoords.get(x), xCoords.get(x+1), yCoords.get(x+1));
            headings.add(relHeading);
        }
        return headings;
        //throw new RuntimeException("implement me!");
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        //throw new RuntimeException("implement me!");
        int sides = 5;
        int sideLength = 100;
        for(int x = sides; x > 0; x--){
            for(int y = 30; y>0; y-=10){
                turtle.color(PenColor.BLUE);
                drawRegularPolygon(turtle, 5, y);
            }
            turtle.color(PenColor.GREEN);
            drawRegularPolygon(turtle, 5, 50);
            turtle.color(PenColor.RED);
            turtle.forward(sideLength);
            turtle.turn(180.0 - calculateRegularPolygonAngle(sides));
        }
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

        //drawSquare(turtle, 40);
        //drawRegularPolygon(turtle, 5, 50);
        drawPersonalArt(turtle);
        // draw the window
        turtle.draw();
    }

}
