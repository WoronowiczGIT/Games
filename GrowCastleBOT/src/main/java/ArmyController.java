import java.awt.*;
import java.awt.event.InputEvent;

 public class ArmyController{
    private static Robot robot;

     private static PointCalculator pc = new PointCalculator(new Point(1870, 610), new Point(1118, 1028));

     private static Point watchADD = pc.getRelative(RelativePosition.WATCH_ADD);
     private static Point closeADD1 = pc.getRelative(RelativePosition.CLOSE_ADD1);

     private static Point archer = pc.getRelative(RelativePosition.ARCHER);
     private static Point archer2 = pc.getRelative(RelativePosition.ARCHER2);
     private static Point bishop = pc.getRelative(RelativePosition.BISHOP);

     Sequence archerCtrl;
     Sequence archerCtrl2;
     Sequence bishopCtrl;

    public ArmyController() throws AWTException {
        robot = new Robot();
        archerCtrl = new Sequence(archer, RelativePosition.ARCHER.interval);
        archerCtrl2 = new Sequence(archer2, RelativePosition.ARCHER2.interval);
        bishopCtrl = new Sequence(bishop, RelativePosition.BISHOP.interval);
    }

    public void run(){
       archerCtrl.start();
       archerCtrl2.start();
       bishopCtrl.start();


    }

    public  Color getColor(int x, int y){
        return robot.getPixelColor(x,y);
    }

    public static void click() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(700);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public static synchronized void action(int x, int y){
        robot.mouseMove(x,y);
        click();
    }
}
