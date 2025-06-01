package BaseClasses;

import SceneObjects.Light;

public class Finish {
    static Finish Default = new Finish();
    public double ambient = 0.1;
    public double diffuse = 0.7;
    public double shiny = 0;
    public double reflection = 0;

    public Color addHighlight(Vector3 reflex, Light light, Vector3 lightVector){
        if(this.shiny == 0){
            return Color.Black;
        }

        double intensity = reflex.dot(lightVector.unit());
        if(intensity <= 0){
            return Color.Black;
        }

        double exponent = 32 * this.shiny * this.shiny;
        intensity = Math.pow(intensity, exponent);
        
        // Scale highlight by shiny factor and reduce overall intensity
        return light.getColor().scale(this.shiny * intensity);
    }

    
}
