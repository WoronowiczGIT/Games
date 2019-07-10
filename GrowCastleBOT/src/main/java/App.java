import java.awt.*;
import java.awt.event.InputEvent;

public class App {
    private static Robot robot;
    private static PointCalculator pc = new PointCalculator(new Point(1870, 610), new Point(1118, 1028));

    private static Point battleStart = pc.getRelative(RelativePosition.BATTLE_START);

    public static void main(String[] args) throws AWTException, InterruptedException {
        robot = new Robot();

        robot.mouseMove(battleStart.getX(), battleStart.getY());
        Color battleColor = robot.getPixelColor(battleStart.getX(), battleStart.getY());

        ArmyController armyController = new ArmyController();

        while (true) {

            waitForBattle(battleColor);
            robot.mouseMove(battleStart.getX(), battleStart.getY());
            click();


            armyController.run();

        }
    }

    public static void waitForBattle(Color battleColor) throws InterruptedException {
        Color actualColor;
        do {
            Thread.sleep(1000);
            actualColor = robot.getPixelColor(battleStart.getX(), battleStart.getY());

        } while (!battleColor.equals(actualColor));


    }

    static void click() {
        robot.delay(500);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(500);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
