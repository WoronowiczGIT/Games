import java.awt.*;

public class ArmyController {
    private static boolean fightON = false;
    private static Robot robot;

    private PointCalculator pc = App.getPointReferences();

    private Point archer = pc.getRelative(RelativePosition.ARCHER);
    private Point archer2 = pc.getRelative(RelativePosition.ARCHER2);
    private Point bishop = pc.getRelative(RelativePosition.BISHOP);
    private Point smith = pc.getRelative(RelativePosition.SMITH);
    private Point witch1 = pc.getRelative(RelativePosition.WITCH1);
    private Point witch2 = pc.getRelative(RelativePosition.WITCH2);

    Sequence archerCtrl;
    Sequence archerCtrl2;
    Sequence bishopCtrl;
    Sequence smithCtrl;
    Sequence witch1Ctrl;
    Sequence witch2Ctrl;


    public ArmyController() throws AWTException {
        robot = new Robot();
        archerCtrl = new Sequence(archer, RelativePosition.ARCHER.interval);
        archerCtrl2 = new Sequence(archer2, RelativePosition.ARCHER2.interval);
        bishopCtrl = new Sequence(bishop, RelativePosition.BISHOP.interval);
        smithCtrl = new Sequence(smith, RelativePosition.SMITH.interval);
        witch1Ctrl = new Sequence(witch1, RelativePosition.WITCH1.interval);
        witch2Ctrl = new Sequence(witch2, RelativePosition.WITCH2.interval);
    }

    public void startThreads() {
        archerCtrl.start();
        archerCtrl2.start();
        bishopCtrl.start();
        smithCtrl.start();
        witch1Ctrl.start();
        witch2Ctrl.start();
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

    public static synchronized void action(int x, int y) throws InterruptedException {
        int oldX = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int oldY = (int) MouseInfo.getPointerInfo().getLocation().getY();
        Thread.sleep(50);
        robot.mouseMove(x, y);
        App.click();
        robot.mouseMove(oldX, oldY);
        Thread.currentThread().sleep(500);
    }
}
