import java.awt.*;
import java.awt.event.InputEvent;

public class App {
    private static Robot robot;
    private static PointCalculator pc = new PointCalculator(new Point(1877, 783), new Point(1427, 1036));

    private static Point battleStart = pc.getRelative(RelativePosition.BATTLE_START);
    private static Point controlPoint = pc.getRelative(RelativePosition.GAME_ON);
    private static Point advertPoint = pc.getRelative(RelativePosition.WATCH_ADD);
    private static Point dragonSelect = pc.getRelative(RelativePosition.SELECT_DRAGON);

    static Color addvertColor;
    static Color dragonColor;
    static Color battleColor;
    static Color gameColor;
    public static void main(String[] args) throws AWTException, InterruptedException {
        robot = new Robot();

        addvertColor = robot.getPixelColor(advertPoint.getX(),advertPoint.getY());
        dragonColor = robot.getPixelColor(dragonSelect.getX(),dragonSelect.getY());

        battleColor = robot.getPixelColor(battleStart.getX(), battleStart.getY());
        gameColor = robot.getPixelColor(controlPoint.getX(), controlPoint.getY());

        ArmyController armyController = new ArmyController();
        armyController.startThreads();

        while (true) {
           waveBattle(armyController);
//            farmItems();
        }
    }
    public static void farmItems() throws InterruptedException {
        Point dragon = pc.getRelative(RelativePosition.BLACK_DRAGON);
        Point salvage = pc.getRelative(RelativePosition.MAT_ITEM);

        if(dragonColor.equals(robot.getPixelColor(dragonSelect.getX(), dragonSelect.getY()))){
            // chose dragon mode
            robot.mouseMove(dragonSelect.getX(),dragonSelect.getY());
            click();
            Thread.sleep(500);
            //chose dragon
            robot.mouseMove(dragon.getX(),dragon.getY());
            click();
            //confirm
            click();
            //battle
            Thread.sleep(2000);
            Point archer = pc.getRelative(RelativePosition.ARCHER);
            robot.mouseMove(archer.getX(),archer.getY());
            click();
            Thread.sleep(500);
            Point bishop = pc.getRelative(RelativePosition.BISHOP);
            robot.mouseMove(bishop.getX(),bishop.getY());
            click();
            Thread.sleep(500);
            Point archer2 = pc.getRelative(RelativePosition.ARCHER2);
            robot.mouseMove(archer2.getX(),archer2.getY());
            click();
            Thread.sleep(11000);
            // salvage items
            robot.mouseMove(salvage.getX(),salvage.getY());
            click();
            Thread.sleep(500);
            click();

            Thread.sleep(1000);

        }

    }

    public static void waveBattle(ArmyController armyController) throws InterruptedException {
        watchADD();
        if(gameColor.equals(robot.getPixelColor(controlPoint.getX(), controlPoint.getY()))){

            waitForBattle(battleColor);
            ArmyController.action(battleStart.getX(), battleStart.getY());
            armyController.run();
        }else{
            ArmyController.setFight(false);
            System.out.println("game stopped");
        }


    }


    public static void watchADD() throws InterruptedException {
        if(addvertColor.equals(robot.getPixelColor(advertPoint.getX(),advertPoint.getY()))){
            ArmyController.action(advertPoint.getX(),advertPoint.getY());

            Thread.sleep(32000);

            int startX = pc.getRelative(RelativePosition.CLOSE_ADD_MAX).getX();
            int startY = pc.getRelative(RelativePosition.CLOSE_ADD_MAX).getY();

            int finishX = pc.getRightTop().getX();
            int finishY = pc.getRightTop().getY();

            for (int x = startX; x < finishX-5; x+=10) {
                for (int y = startY; y > finishY+5; y-=10) {
                    robot.mouseMove(x,y);
                    click();
                }
            }
            System.out.println("watched add");
        }

    }

    public static PointCalculator getPointReferences(){
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
