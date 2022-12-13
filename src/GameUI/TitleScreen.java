package GameUI;

import Main.GamePanel;
import Main.GameState;
import Main.KeyInputHandler;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class TitleScreen {
    static GamePanel gp;
    KeyInputHandler keyH;

    public int buttonSelectPointer = 0;

    public TitleScreen(GamePanel gp, KeyInputHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;
    }

    public void update()
    {
        if (keyH.upPressed == true)
        {
            buttonSelectPointer = 0;
        }
        if(keyH.downPressed == true)
        {
            buttonSelectPointer = 1;
        }
        if(keyH.enterPressed == true)
        {
            gp.gameState = GameState.GAMEPLAY_STATE;
        }
    }

    public void draw(Graphics2D g2)
    {
        update();
        g2.setFont(g2.getFont().deriveFont(68f));
        String text = "Bomberman Game";
        int x = 16*8;
        int y = 16*8;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //Buttons
        String playText = "Play";
        y = 16 * 16;
        g2.drawString(playText, x, y);

        String exitText = "Exit";
        int yexit = y + (16*6);
        g2.drawString(exitText, x, yexit);

        //Button Select
        if(buttonSelectPointer == 0)
            g2.drawString(">", x-40, y);
        if(buttonSelectPointer == 1)
            g2.drawString(">", x-40, yexit);
    }
}