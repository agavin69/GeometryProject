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
public abstract class Geom2D extends Geom {
protected double thickness = 1.0;

@Override
public double computeVolume() {
    
return thickness * computeArea();
}

}
