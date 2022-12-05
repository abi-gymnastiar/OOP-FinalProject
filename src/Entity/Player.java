package Entity;

import Main.GamePanel;
import Main.KeyInputHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyInputHandler keyH;

    public Player(GamePanel gp, KeyInputHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle(8, 4, 29, 40);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues()
    {
        x = gp.tileSize;
        y = gp.tileSize;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage()
    {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/player_up_01.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/player_up_02.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/player_down_01.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/player_down_02.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/player_left_01.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/player_left_02.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/player_right_01.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Assets/Player/player_right_02.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update()
    {

        if(keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            //checking tile collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            //if collision = false, player can move
            if (collisionOn == false)
            {
                switch(direction)
                {
                    case"up":
                        y -= speed;
                        break;
                    case"down":
                        y += speed;
                        break;
                    case"left":
                        x -= speed;
                        break;
                    case"right":
                        x += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 20) {
                if (spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 10;
            }
        }

    }
    public void draw(Graphics2D g2)
    {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
