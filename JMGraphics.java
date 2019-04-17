/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;
import graphics3D.CustomCam;
import java.awt.Canvas;
import java.util.logging.Level;
import java.util.logging.Logger;
import geometryproject.*;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.shape.Dome;




public class JMGraphics extends SimpleApplication {
    
public final Canvas cnvs;
private CustomCam customCam = null;
private final AmbientLight ambient = new AmbientLight();
private final DirectionalLight light1 = new DirectionalLight();
private Geom shape;



public JMGraphics() {
Logger.getLogger("").setLevel(Level.SEVERE);
AppSettings newSetting = new AppSettings(true);
newSetting.setFrameRate(30);
setSettings(newSetting);
createCanvas(); // create canvas!
startCanvas();
JmeCanvasContext ctx = (JmeCanvasContext) getContext();
ctx.setSystemListener(this);
cnvs = ctx.getCanvas();
setPauseOnLostFocus(false);
} // end of Constructor

public JMGraphics(Geom shape) {
Logger.getLogger("").setLevel(Level.SEVERE);
AppSettings newSetting = new AppSettings(true);
newSetting.setFrameRate(30);
setSettings(newSetting);
createCanvas(); // create canvas!
startCanvas();
JmeCanvasContext ctx = (JmeCanvasContext) getContext();
ctx.setSystemListener(this);
cnvs = ctx.getCanvas();
setPauseOnLostFocus(false);
this.shape = shape;
} 

@Override
public void simpleInitApp() {
customCam = new CustomCam(cam);

if (customCam != null) {
flyCam.setEnabled(false);
customCam.registerWithInput(inputManager);
customCam.setZoomSpeed(10f);
customCam.setRotationSpeed(10f);
customCam.setMoveSpeed(3f);
customCam.setDistance(10.0f);
}

setDisplayStatView(false); // to hide the statistics
setDisplayFps(false);
ambient.setColor(new ColorRGBA(0.7f, 0.7f, 0.8f, 1.0f));
rootNode.addLight(ambient);
// Set up the directional light
light1.setColor(ColorRGBA.White);
rootNode.addLight(light1);
createSceneGraph();
}

/* Use the main event loop to trigger repeating actions. */
private final Quaternion camQ = new Quaternion();
@Override
public void simpleUpdate(float tpf) {
light1.setDirection(
new Vector3f(
cam.getDirection().x,
cam.getDirection().y,
cam.getDirection().z));
camQ.lookAt(cam.getLocation(), cam.getUp());
} // simpleUpdate()



private void addBox(){
        geometryproject.Cube cube = (geometryproject.Cube) shape;
        int[] dims = new int[3];
        int[] center = new int[3];
        for (int i = 0; i < 3; i++) {
            dims[i] = (int) cube.getDimensions()[i];
        }
       
        com.jme3.scene.shape.Box box1 = new com.jme3.scene.shape.Box(dims[0],dims[1],dims[2]);
        Geometry g = new Geometry("Box", box1);
        g.setLocalTranslation(new Vector3f(center[0], center[1], center[2]));
        Material mat1 = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat1.setBoolean("UseMaterialColors", true);
        mat1.setColor("Ambient", ColorRGBA.Red);
        g.setMaterial(mat1);
        
        Node pivot = new Node("pivot");
        rootNode.attachChild(pivot); // put this node in the scene

        /** Attach the two boxes to the *pivot* node. (And transitively to the root node.) */
        pivot.attachChild(g);
        /** Rotate the pivot node: Note that both boxes have rotated! */
        pivot.rotate(.4f,.4f,0f);
        
        pivot.setLocalScale(0.3f);
        com.jme3.math.Vector3f lookAt = new com.jme3.math.Vector3f(1, 1, 1);
        customCam.setLookAt(lookAt);
    }
    

private void addSphere(){
        geometryproject.Sphere sphere = (geometryproject.Sphere) shape;
        int dim = (int)sphere.getRadius();
        int[] center = new int[3];
        for(int i = 0; i < 3; i++){
            
            
        }
        com.jme3.scene.shape.Sphere sph1 = new com.jme3.scene.shape.Sphere(30, 30, dim);
        Geometry g = new Geometry("Sphere", sph1);
        g.setLocalTranslation(new Vector3f(center[0], center[1], center[2]));
        Material mat1 = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat1.setBoolean("UseMaterialColors", true);
        mat1.setColor("Ambient", ColorRGBA.Red);
        g.setMaterial(mat1);
        
        Node pivot = new Node("pivot");
        rootNode.attachChild(pivot); // put this node in the scene

        /** Attach the two boxes to the *pivot* node. (And transitively to the root node.) */
        pivot.attachChild(g);
        /** Rotate the pivot node: Note that both boxes have rotated! */
        pivot.rotate(.4f,.4f,0f);
        
        pivot.setLocalScale(0.3f);
        com.jme3.math.Vector3f lookAt = new com.jme3.math.Vector3f(1, 1, 1);
        customCam.setLookAt(lookAt);
    }
private void addCylinder(){
        geometryproject.Cylinder cylinder = (geometryproject.Cylinder) shape;
        int dim = (int)cylinder.getRadius();
        int dimen = (int)cylinder.getHeight();
        int[] center = new int[3];
        for(int i = 0; i < 3; i++){
            
            
        }
        com.jme3.scene.shape.Cylinder cyl1 = new com.jme3.scene.shape.Cylinder(30,30,dim, dimen,true);
        Geometry g = new Geometry("Cylinder", cyl1);
        g.setLocalTranslation(new Vector3f(center[0], center[1], center[2]));
        Material mat1 = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat1.setBoolean("UseMaterialColors", true);
        mat1.setColor("Ambient", ColorRGBA.Red);
        g.setMaterial(mat1);
        
        Node pivot = new Node("pivot");
        rootNode.attachChild(pivot); // put this node in the scene

        /** Attach the two boxes to the *pivot* node. (And transitively to the root node.) */
        pivot.attachChild(g);
        /** Rotate the pivot node: Note that both boxes have rotated! */
        pivot.rotate(.4f,.4f,0f);
        
        pivot.setLocalScale(0.3f);
        com.jme3.math.Vector3f lookAt = new com.jme3.math.Vector3f(1, 1, 1);
        customCam.setLookAt(lookAt);
    }

private void addCone(){
        geometryproject.Cone cone = (geometryproject.Cone) shape;
        int dim = (int)cone.getRadius();
        int dimen = (int)cone.getHeight();
        int[] center = new int[3];
        for(int i = 0; i < 3; i++){
            
            
        }
        com.jme3.scene.shape.Dome co1 = new com.jme3.scene.shape.Dome(Vector3f.ZERO,30, dim, dimen);
        Geometry g = new Geometry("Cone", co1);
        g.setLocalTranslation(new Vector3f(center[0], center[1], center[2]));
        Material mat1 = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat1.setBoolean("UseMaterialColors", true);
        mat1.setColor("Ambient", ColorRGBA.Red);
        g.setMaterial(mat1);
        
        Node pivot = new Node("pivot");
        rootNode.attachChild(pivot); // put this node in the scene

        /** Attach the two boxes to the *pivot* node. (And transitively to the root node.) */
        pivot.attachChild(g);
        /** Rotate the pivot node: Note that both boxes have rotated! */
        pivot.rotate(.4f,.4f,0f);
        
        pivot.setLocalScale(0.3f);
        com.jme3.math.Vector3f lookAt = new com.jme3.math.Vector3f(1, 1, 1);
        customCam.setLookAt(lookAt);
    }


private void createSceneGraph() {
/** create a blue box at coordinates (1,-1,1) */
        String type = shape.getShape();
        
        switch(type){
            case "Cube":
                addBox();
                break;
            case "Sphere":
                addSphere();
                break;
            case "Cylinder":
                addCylinder();
                break;
            case "Cone":
                addCone();
                break;
                
            default:
                break;

}



}

}

