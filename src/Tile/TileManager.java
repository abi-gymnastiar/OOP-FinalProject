package Tile;

import Main.GamePanel;
import Main.KeyInputHandler;
import Entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class TileManager {

    GamePanel gp;
    KeyInputHandler keyH;
    public Tile[] tile;
    Player player01;
    public int mapTileNum[][];

    public int bombCollumn;
    public int bombRow;
    public int bombRadUpRow, bombRadUpCol, bombRadRightRow, bombRadRightCol,
            bombRadLeftRow, bombRadLeftCol, bombRadDownRow, bombRadDownCol;

//    int scene[][];

    public TileManager(GamePanel gp, KeyInputHandler keyH, Player player01)
    {
        this.gp = gp;
        this.keyH = keyH;
        this.player01 = player01;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenColumn][gp.maxScreenRow];
        getTileImage();
        LoadMap();
    }

    public void getTileImage()
    {
         try{
             tile[1] = new Tile();
             tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Assets/Tile/wall01.png"));
             tile[1].collision = true;

             tile[2] = new Tile();
             tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Assets/Tile/wall02.png"));
             tile[2].collision = true;

             tile[3] = new Tile();
             tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Assets/Tile/green.png"));
             tile[3].collision = false;

             tile[0] = new Tile();
             tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Assets/Tile/green.png"));
             tile[0].collision = false;

             tile[4] = new Tile();
             tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Assets/Explosion/ex_mid.png"));
             tile[4].collision = false;

             tile[5] = new Tile();
             tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Assets/Explosion/rx_right.png"));
             tile[5].collision = false;

             tile[6] = new Tile();
             tile[6].image = ImageIO.read(getClass().getResourceAsStream("/Assets/Explosion/ex_left.png"));
             tile[6].collision = false;

             tile[7] = new Tile();
             tile[7].image = ImageIO.read(getClass().getResourceAsStream("/Assets/Explosion/ex_top.png"));
             tile[7].collision = false;

             tile[8] = new Tile();
             tile[8].image = ImageIO.read(getClass().getResourceAsStream("/Assets/Explosion/ex_bottom.png"));
             tile[8].collision = false;

         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    public void LoadMap()
    {
        try{
            InputStream is = getClass().getResourceAsStream("/Assets/maps/map00.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;

            while(col < gp.maxScreenRow)
            {
                String line = br.readLine();

                while(col < gp.maxScreenColumn)
                {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    //to create randomize breakable walls
                    if(num == 0 && new Random().nextInt(10)<5)
                    {
                        num = 2;
                    }

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenColumn)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){

        }
    }

    public void update()
    {
        if(player01.bombPlaced)
        {
            if (player01.bombCounter > 60*2)
            {
                bombCollumn = player01.bombX / gp.tileSize;
                bombRow = player01.bombY / gp.tileSize;

                //left radius
                bombRadLeftCol = bombCollumn - 1;
                bombRadLeftRow = bombRow;
                //right radius
                bombRadRightCol = bombCollumn + 1;
                bombRadRightRow = bombRow;
                //up radius
                bombRadUpCol = bombCollumn;
                bombRadUpRow = bombRow - 1;
                //down radius
                bombRadDownCol = bombCollumn;
                bombRadDownRow = bombRow + 1;
            }
        }
    }

    public void draw(Graphics2D g2)
    {

        int col = 0, row = 0, x = 0, y = 0;
        while(col<gp.maxScreenColumn && row<gp.maxScreenRow)
        {
            int tileNum = mapTileNum[col][row];

            if(col == bombCollumn && row == bombRow)
                g2.drawImage(tile[4].image,x,y,gp.tileSize,gp.tileSize,null);
            else if(col == bombRadLeftCol && row == bombRadLeftRow)
                g2.drawImage(tile[6].image,x,y,gp.tileSize,gp.tileSize,null);
            else if(col == bombRadRightCol && row == bombRadRightRow)
                g2.drawImage(tile[5].image,x,y,gp.tileSize,gp.tileSize,null);
            else if(col == bombRadUpCol && row == bombRadUpRow)
                g2.drawImage(tile[7].image,x,y,gp.tileSize,gp.tileSize,null);
            else if(col == bombRadDownCol && row == bombRadDownRow)
                g2.drawImage(tile[8].image,x,y,gp.tileSize,gp.tileSize,null);
            else
                g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenColumn)
            {
                col = 0;
                x = 0;
                row++;
                y+= gp.tileSize;
            }
        }
    }
}
