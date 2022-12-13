package Tile;

import Main.GamePanel;
import Main.KeyInputHandler;
import Entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class TileManager {

    boolean isExploding;
    GamePanel gp;
    KeyInputHandler keyH;
    public Tile[] tile;
    int tileNumArr[];
    Player player01;
    public int mapTileNum[][];
    int i;

    Explosions explosion;

    public int bombCollumn;
    public int bombRow;
    public int bombRadUpRow, bombRadUpCol, bombRadRightRow, bombRadRightCol,
            bombRadLeftRow, bombRadLeftCol, bombRadDownRow, bombRadDownCol;
    int explosionCounter;
    int explosionIndexMid, explosionIndexLeft,explosionIndexRight, explosionIndexUp, explosionIndexDown;
    public int arrayListBound;
    public ArrayList<Explosions> explosions = new ArrayList<>();
    public int arrayIndex;

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
        isExploding = false;
        arrayIndex = -1;
        i=0;
//        for (int i = 0; i < 5; i++) {
//
//        }
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
//        if(player01.bombPlaced)
//        {
//            if (player01.bombCounter > 60*2)
//            {
//                bombCollumn = player01.bombX / gp.tileSize;
//                bombRow=player01.bombY / gp.tileSize;
//
//                //left radius
//                bombRadLeftCol=bombCollumn - 1;
//                bombRadLeftRow=bombRow;
//                //right radius
//                bombRadRightCol=bombCollumn + 1;
//                bombRadRightRow=bombRow;
//                //up radius
//                bombRadUpCol=bombCollumn;
//                bombRadUpRow=bombRow - 1;
//                //down radius
//                bombRadDownCol=bombCollumn;
//                bombRadDownRow=bombRow + 1;
//
//                if(explosion == null) {
//                    explosion = new Explosions
//                            (bombCollumn, bombRow, bombRadUpRow, bombRadUpCol,
//                                    bombRadRightRow, bombRadRightCol, bombRadLeftRow,
//                                    bombRadLeftCol,  bombRadDownRow, bombRadDownCol);
//                }
//            }
//        }
//        if(!player01.bombPlaced)
//        {
//            if (player01.bombCounter > 60*2 + 30)
//            {
//                explosion.setIndexToZero();
//            }
//        }
        if(player01.bombPlaced && !isExploding)
        {
            isExploding = true;
            arrayIndex++;
        }
        if(player01.bombPlaced && isExploding)
        {
            if (player01.bombCounter > 60*2)
            {
                bombCollumn = player01.bombX / gp.tileSize;
                bombRow=player01.bombY / gp.tileSize;

                //left radius
                bombRadLeftCol=bombCollumn - 1;
                bombRadLeftRow=bombRow;
                //right radius
                bombRadRightCol=bombCollumn + 1;
                bombRadRightRow=bombRow;
                //up radius
                bombRadUpCol=bombCollumn;
                bombRadUpRow=bombRow - 1;
                //down radius
                bombRadDownCol=bombCollumn;
                bombRadDownRow=bombRow + 1;

                if(explosions.size() <arrayIndex+1) {
                    explosions.add(arrayIndex, new Explosions(bombCollumn, bombRow, bombRadUpRow, bombRadUpCol,
                            bombRadRightRow, bombRadRightCol, bombRadLeftRow, bombRadLeftCol,
                            bombRadDownRow, bombRadDownCol));
                }
                if (!player01.bombPlaced)
                {
                    isExploding = false;
                }
            }
        }
        if(!player01.bombPlaced)
        {
            if (player01.bombCounter > 60*2 + 30)
            {
                explosions.get(arrayIndex).setIndexToZero();
            }
            isExploding = false;
        }
        //System.out.println(explosions.size());
        //System.out.println(keyH.spacePressedCount);
    }

    public void draw(Graphics2D g2)
    {
        int col = 0, row = 0, x = 0, y = 0;
        while(col<gp.maxScreenColumn && row<gp.maxScreenRow)
        {
            int tileNum = mapTileNum[col][row];

            if(!explosions.isEmpty())
            {
                if (col == explosions.get(0).getBombCollumn() &&
                        row == explosions.get(0).getBombRow()) {
                    tileNum = explosions.get(0).getExplosionIndexMid();
                } else if (col == explosions.get(0).getBombRadLeftCol() &&
                        row == explosions.get(0).getBombRadLeftRow()) {
                    tileNum = explosions.get(0).getExplosionIndexLeft();
                } else if (col == explosions.get(0).getBombRadRightCol() &&
                        row == explosions.get(0).getBombRadRightRow()) {
                    tileNum = explosions.get(0).getExplosionIndexRight();
                } else if (col == explosions.get(0).getBombRadUpCol() &&
                        row == explosions.get(0).getBombRadUpRow()) {
                    tileNum = explosions.get(0).getExplosionIndexUp();
                } else if (col == explosions.get(0).getBombRadDownCol() &&
                        row == explosions.get(0).getBombRadDownRow()) {
                    tileNum = explosions.get(0).getExplosionIndexDown();
                }
            }
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);

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
