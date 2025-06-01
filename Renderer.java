import BaseClasses.Color;
import SceneObjects.Scene;

@FunctionalInterface
interface RenderCallback {
    void paintPixel(double x, double y, Color color);
}

public class Renderer {
    private int canvasWidth;
    private int canvasHeight;

    public Renderer(int canvasWidth, int canvasHeight){
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    public void render(Scene scene, RenderCallback callback){
        for(int py = 0; py < this.canvasHeight; ++py){
            for(int px = 0; px < this.canvasWidth; ++px){
                // Convert pixel coordinates to normalized coordinates (-1 to 1)
                double x = (2.0 * px / (double)this.canvasWidth) - 1.0;
                double y = (2.0 * py / (double)this.canvasHeight) - 1.0;
                Color color = scene.trace(x, y);

                if(color.getR() == 1 && color.getG() == 1 && color.getB() == 1){
                    System.out.println("Totally White");
                }
                
                callback.paintPixel(px, py, color);  // Pass actual pixel coordinates
            }
        }
    }
}
