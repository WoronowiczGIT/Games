import java.awt.*;

public class ArmyController {
    private static boolean fightON = false;
    private static Robot robot;

    private PointCalculator pc = App.getPointReferences();

    private Point archer = pc.getRelative(RelativePosition.ARCHER);
    private Point archer2 = pc.getRelative(RelativePosition.ARCHER2);
    private Point bishop = pc.getRelative(RelativePosition.BISHOP);

    Sequence archerCtrl;
    Sequence archerCtrl2;
    Sequence bishopCtrl;


    public ArmyController() throws AWTException {
        robot = new Robot();
        archerCtrl = new Sequence(archer, RelativePosition.ARCHER.interval);
        archerCtrl2 = new Sequence(archer2, RelativePosition.ARCHER2.interval);
        bishopCtrl = new Sequence(bishop, RelativePosition.BISHOP.interval);
    }

    public void startThreads() {
        archerCtrl.start();
        archerCtrl2.start();
        bishopCtrl.start();
    }

    public void run() throws InterruptedException {
        Thread.sleep(3000);
        fightON = true;
        Thread.sleep(40000);
        fightON = false;
    }

    public static synchronized boolean getFight() {
        return fightON;
    }

    public static synchronized void setFight(boolean fight) {
        fightON = fight;
    }

    public Color getColor(int x, int y) {
        return robot.getPixelColor(x, y);
    }

    public static synchronized void action(int x, int y) throws InterruptedException {
        int oldX = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int oldY = (int) MouseInfo.getPointerInfo().getLocation().getY();

        robot.mouseMove(x, y);
        App.click();
        robot.mouseMove(oldX, oldY);
        Thread.currentThread().sleep(1500);
    }
}
