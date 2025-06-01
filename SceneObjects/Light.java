package SceneObjects;

import BaseClasses.*;

public class Light {

    private Vector3 position;
    private Color color;
    
    public Light(Vector3 position, Color color){
        this.position = position;
        this.color = color;
    }

    public Color illuminate(Material material, Vector3 point, double brightness){
        // Scale brightness by diffuse factor and apply it to material color
        Color materialColor = material.getColorAt(point);
        Color lightColor = this.color.scale(brightness);
        return materialColor.multiply(lightColor);
    }

    public Vector3 getPosition(){
        return this.position;
    }
    public Color getColor(){
        return this.color;
    }
}
