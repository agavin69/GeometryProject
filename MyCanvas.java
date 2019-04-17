/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author antho
 */
import geometryproject.Geom;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
/**
*
*  @author akumar
*/
public class MyCanvas extends Canvas {
    
  ArrayList plotObject;
 int k;
  
  public MyCanvas(ArrayList geomsList, int i){
      plotObject = geomsList;
      k=i;
  }
    
    @Override
    public void paint(Graphics g){
        
        Class className = ((Geom) plotObject.get(k)).getClass();
        String classSimpleName = className.getSimpleName();
        double [] dimen = ((Geom) plotObject.get(k)).getDimensions();
        if ("Circle".equals(classSimpleName)){
            g.fillOval((int) dimen[0]*5+150, (int)dimen[1]+25, (int) dimen[2]*15, (int) dimen[2]*15);
        } else if ("Rectangle".equals(classSimpleName)){
            g.fillRect((int) dimen[0]*15+85, (int) dimen[1]+85,(int) dimen[2]*15, (int) dimen[2]*15);
        }else if ("Square".equals(classSimpleName)){
            g.fillRect((int) dimen[0]*20+115, (int) dimen[1]+50,(int) dimen[2]*15, (int) dimen[2]*15);
        } else{
                    System.out.println("Error");
                    }
        
    }
}
    
    
    
    
    
    
    



