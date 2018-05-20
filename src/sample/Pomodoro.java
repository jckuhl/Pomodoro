package sample;

import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class Pomodoro {
    private int maxSeconds;
    private Boolean paused = false;
    private Timer timer;
    private int counter;
    private TimerTask count;
    private Text timeDisplay;

    public Pomodoro(Text timeDisplay, int maxSeconds) {
        this.maxSeconds = maxSeconds;
        this.timeDisplay = timeDisplay;
    }

    public void start() {
        timer = new Timer();
        counter = 0;
        count = new TimerTask() {
            @Override
            public void run() {
                counter++;
                System.out.println(Pomodoro.this.toString());
                displayTimer(Pomodoro.this.timeDisplay);
                if(counter == maxSeconds) {
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(count, 1000,1000);
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
        int seconds = maxSeconds - counter;
        int minutes = (int) Math.floor(seconds / 60);
        int secondsRemaining = seconds - (minutes * 60);
        String minStr = String.valueOf(minutes);
        if(minStr.length() == 1) {
            minStr = "0" + minStr;
        }
        String secStr = String.valueOf(secondsRemaining);
        if(secStr.length() == 1) {
            secStr = "0" + secStr;
        }
        return String.format("%s:%s", minStr, secStr);
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
