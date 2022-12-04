package Tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];
//    int scene[][];

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
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

             tile[2] = new Tile();
             tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Assets/Tile/wall02.png"));

             tile[0] = new Tile();
             tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Assets/Tile/tile_green.png"));
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    public void LoadMap()
    {
        try{
            InputStream is = getClass().getResourceAsStream("/Assets/maps/map00.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

//            scene = new int[][]{
//                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//                    {1,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,1},
//                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//                    {1,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,1},
//                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//                    {1,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,1},
//                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//                    {1,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,1},
//                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//                    {1,0,2,0,2,0,2,0,2,0,2,0,2,0,2,0,1},
//                    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//            };

            int col = 0, row = 0;

            while(col < gp.maxScreenRow)
            {
                String line = br.readLine();

                while(col < gp.maxScreenColumn)
                {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
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

    public void draw(Graphics2D g2)
    {

        int col = 0, row = 0, x = 0, y = 0;
        while(col<gp.maxScreenColumn && row<gp.maxScreenRow)
        {
            int tileNum = mapTileNum[col][row];

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
