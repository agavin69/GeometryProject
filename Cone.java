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


public class Cone extends Geom3D {

    private double radius;
    private double height;

    public Cone() {
        radius = 1.0;
        height = 1.0;
        name = "Cone";
    }
    
    public Cone(String shapeName, double radius, double height) {
        name = shapeName;
        this.radius = radius;
        this.height = height;
        Shape = "Cone";
    }
    
    
    public double getRadius(){
        return radius;
    }
    public double getHeight(){
        return height;
    }
    

     public double [] getDimensions(){
        double [] dimen={0,0,radius,height};
        return dimen;
    }
    
    
    @Override
    public double computeArea() {
        length = Math.sqrt(radius * radius + height * height);
		return Math.PI  * radius * (radius + length);
    }

    @Override
    public double computeVolume() {
        return (1.0/3) * Math.PI  * radius * radius * height;
    }

    @Override
    public String toString() {
        return "Cone:\n" 
        + "Radius = " + radius + "\n"
        + "Height = " + height + "\n"
        + super.toString();
    }

    @Override
    public void read(BufferedReader br) throws IOException {
        String line = br.readLine();
        String numbers[] = line.split(" ");
        radius = Double.parseDouble(numbers[0]);
        height = Double.parseDouble(numbers[1]);
        center[0] = Double.parseDouble(numbers[2]);
        center[1] = Double.parseDouble(numbers[3]);
        center[2] = Double.parseDouble(numbers[4]);
    }
    
    @Override
    public String getParameters() {
        String value = radius + "," + height + ",";
        value = value + center[0] + "," + center[1] + "," + center[2];
        return value;
    }
    
}
