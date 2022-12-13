package Entity;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public GamePanel gp;
    public int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public boolean moving = false;
    public Rectangle solidArea;
    public boolean collisionOn = false;

    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }
}