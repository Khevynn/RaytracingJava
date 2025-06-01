package BaseClasses;

public class Material {
    public Color getColorAt(Vector3 point){
        throw new RuntimeException("getColorAt must be overriden");
    }
}
