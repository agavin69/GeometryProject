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

public class Cube extends Geom3D {

    protected double length;

    public Cube() {
        this.length = 1.0;
        name = "Cube";
    }
    
    public Cube(String shapeName, double length, double random) {
        name = shapeName;
        this.length = length;
        Shape = "Cube";
        random=0;
    }
    
     public double [] getDimensions(){
double[] dims = new double[3];
        dims[0] = length;
        dims[1] = length;
        dims[2] = length;

        return dims;
    }
  public double getLength(){
        return length;
    }
    
    @Override
    public double computeArea() {
        return 6 * length * length;
    }

    @Override
    public double computeVolume() {
        return length * length * length;
    }

    @Override
    public String toString() {
        return "Cube:\n" 
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
