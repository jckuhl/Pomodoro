package sample;

import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class Pomodoro {
    final private int MAX_TIME = 1500000;
    private Boolean paused = false;
    private Timer timer;
    private int counter;
    private TimerTask count;

    public Pomodoro() {
        timer = new Timer();
        counter = 0;
        count = new TimerTask() {
            @Override
            public void run() {
                counter++;
                System.out.println(counter);
                System.out.println(toString());
            }
        };

        timer.schedule(count, 1000);
    }


    public Boolean getPaused() {
        return paused;
    }

    public void displayTimer(Text timer) {
        timer.setText(this.toString());
    }

    @Override
    public String toString() {
        // TODO: Turn timer into a string
        int seconds = (MAX_TIME / 1000) - counter;
        int minutes = (int) Math.floor(seconds / 60);
        int secondsRemaining = seconds - (minutes * 60);
        return String.format("%d:%d", minutes, secondsRemaining);
    }

    public void togglePause() {
        if(paused) {
            // TODO: Start timer again
            paused = false;
        } else {
            // TODO: Implement pause
            paused = true;
        }
    }

}
