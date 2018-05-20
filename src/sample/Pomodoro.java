package sample;

import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class Pomodoro {
    private int maxSeconds;
    private int focusTime;
    private int breakTime;
    private Boolean paused = false;
    private Timer timer;
    private int counter;
    private TimerTask count;
    public enum State {
            OFF, FOCUSED, BREAK
    }
    private State status;
    private Controller controller;

    public Pomodoro(Controller controller, int focusTime, int breakTime) {
        this.maxSeconds = focusTime;
        this.focusTime = focusTime;
        this.breakTime = breakTime;
        this.controller = controller;
        this.status = State.OFF;
    }

    public void start() {
        timer = new Timer();
        String soundFile = "./resources/sounds/boxing_bell.wav";
        status = State.FOCUSED;
        counter = 0;
        count = new TimerTask() {
            @Override
            public void run() {
                if(!paused) {
                    counter++;
                    displayTimer(controller.getTimer());
                }
                if(counter == maxSeconds) {
                    if (status == State.FOCUSED) {
                        status = State.BREAK;
                        counter = 0;
                        maxSeconds = breakTime;
                        Sound sound = new Sound(soundFile);
                        sound.playSound();
                        handleUpdateStatus(status);
                    } else {
                        status = State.FOCUSED;
                        counter = 0;
                        maxSeconds = focusTime;
                        Sound sound = new Sound(soundFile);
                        sound.playSound();
                        handleUpdateStatus(status);
                    }
                }
            }
        };
        controller.updateTimerDisplay(Controller.Color.BLACK);
        timer.scheduleAtFixedRate(count, 1000,1000);
    }

    private void handleUpdateStatus(State state) {
        switch(state) {
            case FOCUSED:
                controller.updateTimerDisplay(Controller.Color.BLACK);
                break;
            case BREAK:
                controller.updateTimerDisplay(Controller.Color.BLUE);
                break;
            case OFF:
                controller.updateTimerDisplay(Controller.Color.GREEN);
                break;
        }
    }

    public void setStatus(State state) {
        status = state;
    }

    public State getStatus() {
        return status;
    }

    public void stop() {
        timer.cancel();
    }

    public Boolean getPaused() {
        return paused;
    }

    public void displayTimer(Text timer) {
        timer.setText(this.toString());
    }

    @Override
    public String toString() {
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
        paused = !paused;
    }

}
