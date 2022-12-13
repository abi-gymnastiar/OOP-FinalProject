package Tile;

public class Explosions {
    private int bombCollumn;
    private int bombRow;
    private int bombRadUpRow;
    private int bombRadUpCol;
    private int bombRadRightRow;
    private int bombRadRightCol;
    private int bombRadLeftRow;
    private int bombRadLeftCol;
    private int bombRadDownRow;
    private int bombRadDownCol;
    private int explosionCounter;
    private int explosionIndexMid;
    private int explosionIndexLeft;
    private int explosionIndexRight;
    private int explosionIndexUp;
    private int explosionIndexDown;
    public boolean isExploding;
    Explosions(int bombCollumn, int bombRow, int bombRadUpRow, int bombRadUpCol, int bombRadRightRow,
               int bombRadRightCol, int bombRadLeftRow, int bombRadLeftCol, int bombRadDownRow,
               int bombRadDownCol)
    {
        this.setBombCollumn(bombCollumn);
        this.setBombRow(bombRow);
        this.setBombRadUpRow(bombRadUpRow);
        this.setBombRadUpCol(bombRadUpCol);
        this.setBombRadRightRow(bombRadRightRow);
        this.setBombRadRightCol(bombRadRightCol);
        this.setBombRadLeftRow(bombRadLeftRow);
        this.setBombRadLeftCol(bombRadLeftCol);
        this.setBombRadDownRow(bombRadDownRow);
        this.setBombRadDownCol(bombRadDownCol);
        setIndexDefault();
    }

    public void setIndexDefault()
    {
        this.setExplosionIndexUp(7);
        this.setExplosionIndexMid(4);
        this.setExplosionIndexDown(8);
        this.setExplosionIndexLeft(6);
        this.setExplosionIndexRight(5);
    }
    public void setIndexToZero()
    {
        this.setExplosionIndexMid(0);
        this.setExplosionIndexDown(0);
        this.setExplosionIndexLeft(0);
        this.setExplosionIndexRight(0);
        this.setExplosionIndexUp(0);
    }

    public int getBombCollumn() {
        return bombCollumn;
    }

    public void setBombCollumn(int bombCollumn) {
        this.bombCollumn = bombCollumn;
    }

    public int getBombRow() {
        return bombRow;
    }

    public void setBombRow(int bombRow) {
        this.bombRow = bombRow;
    }

    public int getBombRadUpRow() {
        return bombRadUpRow;
    }

    public void setBombRadUpRow(int bombRadUpRow) {
        this.bombRadUpRow = bombRadUpRow;
    }

    public int getBombRadUpCol() {
        return bombRadUpCol;
    }

    public void setBombRadUpCol(int bombRadUpCol) {
        this.bombRadUpCol = bombRadUpCol;
    }

    public int getBombRadRightRow() {
        return bombRadRightRow;
    }

    public void setBombRadRightRow(int bombRadRightRow) {
        this.bombRadRightRow = bombRadRightRow;
    }

    public int getBombRadRightCol() {
        return bombRadRightCol;
    }

    public void setBombRadRightCol(int bombRadRightCol) {
        this.bombRadRightCol = bombRadRightCol;
    }

    public int getBombRadLeftRow() {
        return bombRadLeftRow;
    }

    public void setBombRadLeftRow(int bombRadLeftRow) {
        this.bombRadLeftRow = bombRadLeftRow;
    }

    public int getBombRadLeftCol() {
        return bombRadLeftCol;
    }

    public void setBombRadLeftCol(int bombRadLeftCol) {
        this.bombRadLeftCol = bombRadLeftCol;
    }

    public int getBombRadDownRow() {
        return bombRadDownRow;
    }

    public void setBombRadDownRow(int bombRadDownRow) {
        this.bombRadDownRow = bombRadDownRow;
    }

    public int getBombRadDownCol() {
        return bombRadDownCol;
    }

    public void setBombRadDownCol(int bombRadDownCol) {
        this.bombRadDownCol = bombRadDownCol;
    }

    public int getExplosionCounter() {
        return explosionCounter;
    }

    public void setExplosionCounter(int explosionCounter) {
        this.explosionCounter = explosionCounter;
    }

    public int getExplosionIndexMid() {
        return explosionIndexMid;
    }

    public void setExplosionIndexMid(int explosionIndexMid) {
        this.explosionIndexMid = explosionIndexMid;
    }

    public int getExplosionIndexLeft() {
        return explosionIndexLeft;
    }

    public void setExplosionIndexLeft(int explosionIndexLeft) {
        this.explosionIndexLeft = explosionIndexLeft;
    }

    public int getExplosionIndexRight() {
        return explosionIndexRight;
    }

    public void setExplosionIndexRight(int explosionIndexRight) {
        this.explosionIndexRight = explosionIndexRight;
    }

    public int getExplosionIndexUp() {
        return explosionIndexUp;
    }

    public void setExplosionIndexUp(int explosionIndexUp) {
        this.explosionIndexUp = explosionIndexUp;
    }

    public int getExplosionIndexDown() {
        return explosionIndexDown;
    }

    public void setExplosionIndexDown(int explosionIndexDown) {
        this.explosionIndexDown = explosionIndexDown;
    }
}