package model.elementen;

public class TankAElement implements Element {

    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char teken() {
        return 'A';
    }

    public String getFileName() {
        return "tanka";
    }
}
