package SceneObjects;

import BaseClasses.Color;
import BaseClasses.Ray;
import BaseClasses.Vector3;

public class Camera {
    private Vector3 location;
    private Vector3 look_at;

    private Vector3 direction;
    private Vector3 right;
    private Vector3 up;

    private double width = 32;
    private double height = 18;
    
    public Camera(Vector3 location, Vector3 look_at){
        this.location = location;
        this.look_at = look_at;

        this.direction = Vector3.from(this.location).to(this.look_at);
        this.right = Vector3.UNIT_Y.cross(this.direction).unit().multiply(width/2);
        this.up = this.right.cross(this.direction).unit().multiply(height/2);
    }

    public Color trace(Scene scene, double x, double y){
        Vector3 xComponent = this.right.multiply(x);
        Vector3 yComponent = this.up.multiply(y);
        
        Vector3 rayDirection = this.direction.add(xComponent).add(yComponent).normalize(); //TODO: remove normalize
        
        Ray ray = new Ray(this.location, rayDirection);
        return ray.trace(scene, 0);
    }

}
