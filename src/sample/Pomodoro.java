package sample;

import javafx.scene.text.Text;

import java.util.Timer;

public class Pomodoro {
    private Boolean paused = false;
    private int currentTime;

    public Boolean getPaused() {
        return paused;
    }

    public void displayTimer(Text timer) {
        timer.setText(this.toString());
    }

    @Override
    public String toString() {
        // TODO: Turn timer into a string
        return "25:00";
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
