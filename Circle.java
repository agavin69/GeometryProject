/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometryproject;

/**
 *
 * @author antho
 */
import java.io.BufferedReader;
import java.io.IOException;

public class Circle extends Geom2D {

    private double radius;

    public Circle() {
        this.radius = 1.0;
        name = "Circle";
    }
     
    public Circle(String shapeName, double radius, double random) {
        name =shapeName;
        this.radius = radius;
        Shape = "Circle";
        random=0;
    }
    public double [] getDimensions(){
        double [] dimen={0,0,radius,radius};
        return dimen;
    }
    
    
    @Override
    public double computeArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public String toString() {
        return "Circle:\n" 
        + "Radius = " + radius + "\n"
        + super.toString();
    }

    @Override
    public void read(BufferedReader br) throws IOException {
        String line = br.readLine();
        String numbers[] = line.split(" ");
        radius = Double.parseDouble(numbers[0]);
        center[0] = Double.parseDouble(numbers[1]);
        center[1] = Double.parseDouble(numbers[2]);
        center[2] = Double.parseDouble(numbers[3]);
    }
    
    @Override
    public String getParameters() {
        String value = radius + ",";
        value = value + center[0] + "," + center[1] + "," + center[2];
        return value;
    }

}
