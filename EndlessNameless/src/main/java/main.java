import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

/**
 * Created by david on 11.2.17.
 */


public class main /*implements Runnable*/ {
    /*public void run() {

    }*/

    static int interval;
    static Timer timer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cislo; //Začátek komentáře když se validace podělá
        do {
            System.out.print("Zadejte počet sekund : ");
            while (!sc.hasNextInt()) {
                System.out.println("Vstup není kladné číslo");
                sc.next();
            }
            cislo = sc.nextInt();
        } while (cislo <= 0);
        System.out.println("Zadáno " +cislo); //Konec komentáře když se validace podělá

        /*System.out.print("Zadejte počet sekund : ");*/ //Odkomentovat když se validace podělá
        String secs = sc.nextLine();
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = Integer.parseInt(secs);
        System.out.println(secs);
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                System.out.println(setInterval());
            }
        }, delay, period);
    }

    private static final int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }
}

class IsKeyPressed {
    private static boolean wPressed = false;
    public static boolean isWPressed() {
        synchronized (IsKeyPressed.class) {
            return wPressed;
        }
    }

    public static void main(String[]args) {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (IsKeyPressed.class) {
                    switch (ke.getID()) {
                        case KeyEvent.KEY_PRESSED:
                            if (ke.getKeyCode() == KeyEvent.VK_W) {
                                wPressed = true;
                            }
                            break;

                        case KeyEvent.KEY_RELEASED:
                            if (ke.getKeyCode() == KeyEvent.VK_W) {
                                wPressed = false;
                            }
                            break;
                    }
                    return false;
                }
            }
        });
    }
}
