import java.awt.*;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static void main(String[] args) throws InterruptedException, AWTException {
        Robot r = new Robot();
        while (true){
            Thread.sleep(1000);
            System.out.println(MouseInfo.getPointerInfo().getLocation().toString());
            System.out.println(r.getPixelColor(MouseInfo.getPointerInfo().getLocation().x,MouseInfo.getPointerInfo().getLocation().y).toString());
        }
    }
}
