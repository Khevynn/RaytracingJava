package BaseClasses;

public class Color extends Material {
    private double r = 0;
    private double g = 0;
    private double b = 0;

    public Color(double r, double g, double b){
        this.r = r;  // Store directly in 0-255 range
        this.g = g;
        this.b = b;
    }

    @Override
    public Color getColorAt(Vector3 point){
        return this;
    }

    public static final Color Black = new Color(0, 0, 0);
    public static final Color White = new Color(255, 255, 255);
    public static final Color Yellow = new Color(255, 255, 0);
    public static final Color Green = new Color(0, 255, 0);
    public static final Color Purple = new Color(128, 0, 128);
    public static final Color Pink = new Color(255, 20, 203);
    public static final Color Blue = new Color(0, 0, 255);
    public static final Color Red = new Color(255, 0, 0);

    public Color add(Color other){
        return new Color(
            Math.min(255, this.r + other.r),
            Math.min(255, this.g + other.g),
            Math.min(255, this.b + other.b)
        );
    }
    public Color subtract(Color other){
        return new Color(
            Math.max(0, this.r - other.r),
            Math.max(0, this.g - other.g),
            Math.max(0, this.b - other.b)
        );
    }
    public Color multiply(Color other){
        return new Color(
            (this.r * other.r) / 255,
            (this.g * other.g) / 255,
            (this.b * other.b) / 255
        );
    }

    public Color scale(double scalar){
        return new Color(
            Math.min(255, Math.max(0, this.r * scalar)),
            Math.min(255, Math.max(0, this.g * scalar)),
            Math.min(255, Math.max(0, this.b * scalar))
        );
    }

    public double getR(){
        return r;
    }
    public double getG(){
        return g;
    }
    public double getB(){
        return b;
    }
    public double[] getRGBA(){
        return new double[] {r, g, b, 255};
    }
}
