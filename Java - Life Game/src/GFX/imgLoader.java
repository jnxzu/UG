package GFX;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class imgLoader {
    public BufferedImage loadimg(String path){
        try {
            return ImageIO.read(imgLoader.class.getResource(path));
        } catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
