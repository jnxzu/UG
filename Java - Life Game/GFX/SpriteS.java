package GFX;

import java.awt.image.BufferedImage;

public class SpriteS {
    private BufferedImage sprites;

    public SpriteS(BufferedImage sprites){
        this.setSprites(sprites);
    }

    public BufferedImage crop(int x, int y, int width, int height){
        return this.getSprites().getSubimage(x,y,width,height);
    }

    public BufferedImage getSprites() {
        return sprites;
    }

    public void setSprites(BufferedImage sprites) {
        this.sprites = sprites;
    }
}

