// TDL threads with flag , threads synchronized, threads unsychnronized

public class TestThreads {
    private static boolean stop = true;
    public static boolean getFlag(){
        return stop;
    }
    public static void main(String[] args) throws InterruptedException {
        Printer printer = new Printer();

        FatherThread ft = new FatherThread(printer, "father");
        ChildThread ct1 = new ChildThread(printer, "child 1");

        ChildThread ct3 = new ChildThread(printer, "child 2");
        ChildThread ct4 = new ChildThread(printer, "child 4");
        ChildThread ct5 = new ChildThread(printer, "child 5");
        ChildThread ct6 = new ChildThread(printer, "child 6");
        ChildThread ct7 = new ChildThread(printer, "child 7");


        ft.start();
        ct1.start();
        ct3.start();
        ct4.start();
        ct5.start();
        ct6.start();
        ct7.start();

        Thread.sleep(10000);
        stop = false;
        System.out.println(printer.counter);
    }
}

class Printer {
    int counter;

    public synchronized void  print(String i) throws InterruptedException {
        Thread.sleep(500);
        System.out.println(" " + i);
    }
}

class FatherThread extends Thread {
    Printer printer;
    String s;
    ChildThread ct2;

    FatherThread(Printer p, String s) {

        printer = p;
        this.s = s;
        ct2 = new ChildThread(printer, "Child 2");
    }

    public void run() {
        while (TestThreads.getFlag()) {
            try {

                this.sleep(5000);
                System.out.println("5 seconds up");
                printer.print(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class ChildThread extends Thread {
    Printer printer;
    String i;

    ChildThread(Printer p, String i) {
        printer = p;
        this.i = i;
    }

    public void run() {
        while (true) {
            try {
                this.sleep(250);
                printer.print(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}

