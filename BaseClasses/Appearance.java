package BaseClasses;

import SceneObjects.Scene;

public class Appearance {

    private Material material;
    private Finish finish;

    public Appearance(Material material, Finish finish){
        this.material = material;
        this.finish = finish;
    }

    public Color getAmbientColorAt(Vector3 point){
        return this.material.getColorAt(point).scale(this.finish.ambient);
    }
    public Material getMaterial(){
        return this.material;
    }
    public Finish getFinish(){
        return this.finish;
    }

    public Color reflect(Vector3 point, Vector3 reflex, Scene scene, int depth){
        if(this.finish.reflection == 0){
            return Color.Black;
        }
        Ray reflectedRay = new Ray(point, reflex);
        Color reflectedColor = reflectedRay.trace(scene, depth);
        return reflectedColor.scale(this.finish.reflection);
    }
}
