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

public class Square extends Geom2D {

    private double length;

    public Square() {
        this.length = 1.0;
        name = "Square";
    }
    
    public Square(String shapeName, double length, double random) {
        name = shapeName;
        this.length = length;
        Shape = "Square";
        random=0;
        
    }
 public double [] getDimensions(){
        double [] dimen={0,0,length,0};
        return dimen;
    }
    
    
    
    
    @Override
    public double computeArea() {
        return length * length;
    }

    @Override
    public String toString() {
        return "Square:\n" 
        + "Side length = " + length + "\n"
        + super.toString();
    }

    @Override
    public void read(BufferedReader br) throws IOException {
        String line = br.readLine();
        String numbers[] = line.split(" ");
        length = Double.parseDouble(numbers[0]);
        center[0] = Double.parseDouble(numbers[1]);
        center[1] = Double.parseDouble(numbers[2]);
        center[2] = Double.parseDouble(numbers[3]);
    }
    
    @Override
    public String getParameters() {
        String value = length + ",";
        value = value + center[0] + "," + center[1] + "," + center[2];
        return value;
    }
    
}

