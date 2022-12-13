package Main;

import Entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public void checkTile(Entity entity)
    {
        int entityLeftWorldX = entity.x + entity.solidArea.x;
        int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.y + entity.solidArea.y;
        int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction)
        {
            case"up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];

                if(!gp.tileManager.explosions.isEmpty()) {
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombCollumn()
                            && entityTopRow == gp.tileManager.bombRow)
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombCollumn()
                            && entityTopRow == gp.tileManager.bombRow)
                        tileNum2 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadLeftCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadLeftRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadLeftCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadLeftRow())
                        tileNum2 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadRightCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadRightRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadRightCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadRightRow())
                        tileNum2 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadUpCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadUpRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadUpCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadUpRow())
                        tileNum2 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadDownCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadDownRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadDownCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadDownRow())
                        tileNum2 = 0;
                }

                if(gp.tileManager.tile[tileNum1].collision == true ||
                        gp.tileManager.tile[tileNum2].collision == true)
                {
                    System.out.println("tilenum1 = " + tileNum1);
                    System.out.println("tilenum2 = " + tileNum2);
                    entity.collisionOn = true;
                }
                break;
            case"down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(!gp.tileManager.explosions.isEmpty()) {
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombCollumn()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombCollumn()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRow())
                        tileNum2 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadLeftCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadLeftRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadLeftCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadLeftRow())
                        tileNum2 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadRightCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadRightRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadRightCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadRightRow())
                        tileNum2 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadUpCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadUpRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadUpCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadUpRow())
                        tileNum2 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadDownCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadDownRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadDownCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadDownRow())
                        tileNum2 = 0;
                }
                if(gp.tileManager.tile[tileNum1].collision == true ||
                        gp.tileManager.tile[tileNum2].collision == true)
                {
                    System.out.println("tilenum1 = " + tileNum1);
                    System.out.println("tilenum2 = " + tileNum2);
                    entity.collisionOn = true;
                }
                break;
            case"left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(!gp.tileManager.explosions.isEmpty()) {
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombCollumn()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRow())
                        tileNum1 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombCollumn()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRow())
                        tileNum2 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadLeftCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadLeftRow())
                        tileNum1 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadLeftCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadLeftRow())
                        tileNum2 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadRightCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadRightRow())
                        tileNum1 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadRightCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadRightRow())
                        tileNum2 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadUpCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadUpRow())
                        tileNum1 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadUpCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadUpRow())
                        tileNum2 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadDownCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadDownRow())
                        tileNum1 = 0;
                    if (entityLeftCol == gp.tileManager.explosions.get(0).getBombRadDownCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadDownRow())
                        tileNum2 = 0;
                }
                if(gp.tileManager.tile[tileNum1].collision == true ||
                        gp.tileManager.tile[tileNum2].collision == true)
                {
                    System.out.println("tilenum1 = " + tileNum1);
                    System.out.println("tilenum2 = " + tileNum2);
                    entity.collisionOn = true;
                }
                break;
            case"right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(!gp.tileManager.explosions.isEmpty()) {
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombCollumn()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombCollumn()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRow())
                        tileNum2 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadLeftCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadLeftRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadLeftCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadLeftRow())
                        tileNum2 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadRightCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadRightRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadRightCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadRightRow())
                        tileNum2 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadUpCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadUpRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadUpCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadUpRow())
                        tileNum2 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadDownCol()
                            && entityTopRow == gp.tileManager.explosions.get(0).getBombRadDownRow())
                        tileNum1 = 0;
                    if (entityRightCol == gp.tileManager.explosions.get(0).getBombRadDownCol()
                            && entityBottomRow == gp.tileManager.explosions.get(0).getBombRadDownRow())
                        tileNum2 = 0;
                }
                if(gp.tileManager.tile[tileNum1].collision == true ||
                        gp.tileManager.tile[tileNum2].collision == true)
                {
                    System.out.println("tilenum1 = " + tileNum1);
                    System.out.println("tilenum2 = " + tileNum2);
                    entity.collisionOn = true;
                }
                break;
        }
    }

}
