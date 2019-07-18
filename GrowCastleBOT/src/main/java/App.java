import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;

public class App {
    private static Robot robot;
    private static PointCalculator pc = new PointCalculator(new Point(1839, 408), new Point(1000, 879));

    private static Point battleStart = pc.getRelative(RelativePosition.BATTLE_START);
    private static Point controlPoint = pc.getRelative(RelativePosition.CONTROL_POINT);
    private static Point advertPoint = pc.getRelative(RelativePosition.WATCH_ADD);
    private static Point dragonSelect = pc.getRelative(RelativePosition.SELECT_DRAGON);
    private static Point replayStart = pc.getRelative(RelativePosition.REPLAY);


    static Color addvertColor;
    static Color dragonColor;
    static Color battleColor;
    static Color controlColor;
    static Color replayColor;

    public static void main(String[] args) throws AWTException, InterruptedException {
        robot = new Robot();

        addvertColor = robot.getPixelColor(advertPoint.getX(), advertPoint.getY());
        dragonColor = robot.getPixelColor(dragonSelect.getX(), dragonSelect.getY());

        battleColor = robot.getPixelColor(battleStart.getX(), battleStart.getY());
        replayColor = robot.getPixelColor(replayStart.getX(), replayStart.getY());
        controlColor = robot.getPixelColor(controlPoint.getX(), controlPoint.getY());

        ArmyController armyController = new ArmyController();
        armyController.startThreads();

        while (true) {
//           waveBattle(armyController);
            farmItems();
        }
    }

    public static void farmItems() throws InterruptedException {
        Point dragon = pc.getRelative(RelativePosition.RED_DRAGON);
        Point salvage = pc.getRelative(RelativePosition.MAT_ITEM);

        if (dragonColor.equals(robot.getPixelColor(dragonSelect.getX(), dragonSelect.getY()))) {
            Random r = new Random();
            Point point;
            // chose dragon mode
            point = randomizeClick(dragonSelect, 15);
            robot.mouseMove(point.getX(), point.getY());
            click();
            Thread.sleep(500+r.nextInt(500));
            //chose dragon
            point = randomizeClick(dragon, 15);
            robot.mouseMove(point.getX(), point.getY());
            click();
            //confirm
            click();

            Thread.sleep(1500+r.nextInt(500));
            //anti-anti macro
            Color current = robot.getPixelColor(controlPoint.getX(),controlPoint.getY());
            if(!controlColor.equals(current)) return;

            //battle
            for (int i = 0; i < 3 ; i++) {

                Point archer = pc.getRelative(RelativePosition.ARCHER);
                point = randomizeClick(archer, 5);
                robot.mouseMove(point.getX(), point.getY());
                click();
                Thread.sleep(500+r.nextInt(500));

                Point bishop = pc.getRelative(RelativePosition.BISHOP);
                point = randomizeClick(bishop, 5);
                robot.mouseMove(point.getX(), point.getY());
                click();
                Thread.sleep(500+r.nextInt(500));

                Point archer2 = pc.getRelative(RelativePosition.ARCHER2);
                point = randomizeClick(archer2, 5);
                robot.mouseMove(point.getX(), point.getY());
                click();
                Thread.sleep(500+r.nextInt(500));

                Point witch1 = pc.getRelative(RelativePosition.WITCH1);
                point = randomizeClick(witch1, 5);
                robot.mouseMove(point.getX(), point.getY());
                click();
                Thread.sleep(500+r.nextInt(500));

                Point witch2 = pc.getRelative(RelativePosition.WITCH2);
                point = randomizeClick(witch2, 5);
                robot.mouseMove(point.getX(), point.getY());
                click();

                Thread.sleep(7000+r.nextInt(1000));
            }

            Thread.sleep(24000 + r.nextInt(1000));

            // salvage items
            point = randomizeClick(salvage, 5);
            robot.mouseMove(point.getX(), point.getY());
            click();
            Thread.sleep(500 + r.nextInt(400));
            click();

            Thread.sleep(r.nextInt(500) + 500);

        }


    }

    static Point randomizeClick(Point point, int pixels) {
        int boundary = 2 * pixels;
        Random r = new Random();
        int newX = point.getX() + (r.nextInt(boundary) - pixels);
        int newY = point.getY() + (r.nextInt(boundary) - pixels);
        return new Point(newX, newY);
    }

    public static void waveBattle(ArmyController armyController) throws InterruptedException {

        if (controlColor.equals(robot.getPixelColor(controlPoint.getX(), controlPoint.getY()))) {
            waitForBattle(battleColor);
            // wait for animation to finish
            Thread.sleep(1000);
            watchADD();
            ArmyController.action(battleStart.getX(), battleStart.getY());
            armyController.run();
        } else {
            ArmyController.setFight(false);
            System.out.println("game stopped");
        }
    }

    public static void replayBattle(ArmyController armyController) throws InterruptedException {
        watchADD();

        Point replayMode = pc.getRelative(RelativePosition.REPLAY_MODE1);

        if (controlColor.equals(robot.getPixelColor(controlPoint.getX(), controlPoint.getY()))) {

            waitForBattle(replayColor);
            ArmyController.action(replayStart.getX(), replayStart.getY());
            ArmyController.action(replayMode.getX(), replayMode.getY());
            armyController.run();
        } else {
            ArmyController.setFight(false);
            System.out.println("game stopped");
        }
    }


    public static void watchADD() throws InterruptedException {
        if (addvertColor.equals(robot.getPixelColor(advertPoint.getX(), advertPoint.getY()))) {
            ArmyController.action(advertPoint.getX(), advertPoint.getY());
            // add need some time to load
            Thread.sleep(1000);
            while (!controlColor.equals(robot.getPixelColor(controlPoint.getX(), controlPoint.getY()))) {
                Thread.sleep(6000);

                int startX = pc.getRelative(RelativePosition.CLOSE_ADD_MAX).getX();
                int startY = pc.getRelative(RelativePosition.CLOSE_ADD_MAX).getY();

                int finishX = pc.getRightTop().getX();
                int finishY = pc.getRightTop().getY();

                for (int x = startX; x < finishX - 5; x += 10) {
                    for (int y = startY; y > finishY + 5; y -= 10) {
                        robot.mouseMove(x, y);
                        click();
                    }
                }
                checkForBlock();

            }

            System.out.println("watched add");
        }
    }

    public static void checkForBlock() throws InterruptedException {
        Point mid = pc.getRelative(RelativePosition.MID_POINT);
        Point close = pc.getRelative(RelativePosition.CLOSE);

        Color midColor = robot.getPixelColor(mid.getX(), mid.getY());
        Color rightColor = robot.getPixelColor(mid.getX() + 50, mid.getY() - 50);
        if (midColor.equals(Color.WHITE) && rightColor.equals(Color.WHITE)) {
            ArmyController.action(close.getX(), close.getY());
            Thread.sleep(24000);
        }
    }

    public static PointCalculator getPointReferences() {
        return pc;
    }

    public static void waitForBattle(Color battleColor) throws InterruptedException {
        Color actualColor;
        do {
            actualColor = robot.getPixelColor(battleStart.getX(), battleStart.getY());
            Thread.sleep(1000);
        } while (!battleColor.equals(actualColor));
    }

    static void click() {
        robot.delay(50);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(50);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}
