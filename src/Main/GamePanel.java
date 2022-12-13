package Main;

import Entity.Player;
import GameUI.TitleScreen;
import Tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //screen settings
    final int originalTileSize = 16;
    final int tileScaling = 3;

    public GameState gameState = GameState.TITLE_STATE;

    public final int tileSize = originalTileSize * tileScaling;
    public final int maxScreenColumn = 17, maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeight = tileSize * maxScreenRow;

    //FPS
    final int fps = 60;

    KeyInputHandler keyH = new KeyInputHandler();
    Player player1 = new Player(this, keyH);
    TileManager tileManager = new TileManager(this, keyH, player1);
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    TitleScreen titleScreen = new TitleScreen(this, keyH);

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground((Color.black));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null)
        {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1)
            {
                //UPDATE
                update();
                //DRAW SCREEN
                repaint();

                delta--;
                drawCount++;
            }
        }
    }

    public void update()
    {
        player1.update();
        tileManager.update();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if (gameState == GameState.TITLE_STATE)
        {
            titleScreen.draw(g2);
        }

        else
        {
            tileManager.draw(g2);

            player1.draw(g2);

        }

        g2.dispose();
    }

}
