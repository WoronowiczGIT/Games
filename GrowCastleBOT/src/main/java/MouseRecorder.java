import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class MouseRecorder{
    private boolean recording;
    static MouseRecorder mr = new MouseRecorder();
    public void setRecording(boolean recording){
        this.recording = recording;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner s = new Scanner(System.in);
        while (true){
            s.next();
            System.out.println("clicked");
        }
    }




}
