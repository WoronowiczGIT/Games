public class Sequence extends Thread {

    private Point where;
    private int interval;

    public Sequence(Point where, int interval) {
        this.where = where;
        this.interval = interval;

    }

    @Override
    public void run() {

        while (true) {

            try {
                if (ArmyController.getFight()) {

                    ArmyController.action(where.getX(), where.getY());
                    this.sleep(interval);
                }



            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

}
