package model.elementen;

public class TankAPlusElement  implements Element {
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
        return '+';
    }

    public String getFileName() {
        return "tankaplus";
    }
}
