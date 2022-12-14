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
//    ArrayList<Integer> explodedAreaMidCol;
//    ArrayList<Integer> explodedAreaMidRow;

    public int bombCollumn;
    public int bombRow;
    public int bombRadUpRow, bombRadUpCol, bombRadRightRow, bombRadRightCol,
            bombRadLeftRow, bombRadLeftCol, bombRadDownRow, bombRadDownCol;
    public ArrayList<Explosions> explosions = new ArrayList<>();
    public int arrayIndex;

    int[] indexing = new int [5];
    public TileManager(GamePanel gp, KeyInputHandler keyH, Player player01)
    {
        this.gp = gp;
        this.keyH = keyH;
        this.player01 = player01;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenColumn][gp.maxScreenRow];
//        explodedAreaMidRow = new ArrayList<>();
//        explodedAreaMidCol = new ArrayList<>();
        getTileImage();
        LoadMap();
        isExploding = false;
        arrayIndex = -1;
        i=0;
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
        //System.out.println("array size: "+ explosions.size());
        //System.out.println("array index: " + arrayIndex);
        //System.out.println(explosions.size());
        //System.out.println(keyH.spacePressedCount);
        //System.out.println(indexing[0]);
    }

    public void draw(Graphics2D g2)
    {
        ArrayList<Integer> explodedAreaMidCol = new ArrayList<>();
        ArrayList<Integer> explodedAreaMidRow = new ArrayList<>();
        ArrayList<Integer> explodedAreaUpCol = new ArrayList<>();
        ArrayList<Integer> explodedAreaUpRow = new ArrayList<>();
        ArrayList<Integer> explodedAreaLeftCol = new ArrayList<>();
        ArrayList<Integer> explodedAreaLeftRow = new ArrayList<>();
        ArrayList<Integer> explodedAreaRightCol = new ArrayList<>();
        ArrayList<Integer> explodedAreaRightRow = new ArrayList<>();
        ArrayList<Integer> explodedAreaDownCol = new ArrayList<>();
        ArrayList<Integer> explodedAreaDownRow = new ArrayList<>();
        boolean forActivator = false;
        int col = 0, row = 0, x = 0, y = 0;

        indexing[0] = 0; indexing[1] = 0; indexing[2] = 0; indexing[3] = 0; indexing[4] = 0;
        while(col<gp.maxScreenColumn && row<gp.maxScreenRow)
        {
            int tileNum = mapTileNum[col][row];

            if(!explosions.isEmpty())
            {
                if (col == explosions.get(indexing[0]).getBombRadUpCol() &&
                        row == explosions.get(indexing[0]).getBombRadUpRow()) {
                    System.out.println(indexing[0]);
                    explodedAreaUpCol.add(col);
                    explodedAreaUpRow.add(col);
                    tileNum = explosions.get(indexing[0]).getExplosionIndexUp();
                    forActivator = true;
                }
                if (col == explosions.get(indexing[1]).getBombRadLeftCol() &&
                        row == explosions.get(indexing[1]).getBombRadLeftRow()) {
                    explodedAreaLeftCol.add(col);
                    explodedAreaLeftRow.add(col);
                    tileNum = explosions.get(indexing[1]).getExplosionIndexLeft();
                    forActivator = true;
                }
                //mid
                if (col == explosions.get(indexing[2]).getBombCollumn() &&
                        row == explosions.get(indexing[2]).getBombRow()) {
                    tileNum = explosions.get(indexing[2]).getExplosionIndexMid();
                    explodedAreaMidCol.add(col);
                    explodedAreaMidRow.add(row);
                    //System.out.println("size of are arr: " + explodedAreaMidCol.size());
                    forActivator = true;
                }

                if (col == explosions.get(indexing[3]).getBombRadRightCol() &&
                        row == explosions.get(indexing[3]).getBombRadRightRow()) {
                    explodedAreaRightCol.add(col);
                    explodedAreaRightRow.add(col);
                    tileNum = explosions.get(indexing[3]).getExplosionIndexRight();
                    forActivator = true;
                }
                if (col == explosions.get(indexing[4]).getBombRadDownCol() &&
                        row == explosions.get(indexing[4]).getBombRadDownRow()) {
                    explodedAreaDownCol.add(col);
                    explodedAreaDownRow.add(col);
                    tileNum = explosions.get(indexing[4]).getExplosionIndexDown();
                    forActivator = true;
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
