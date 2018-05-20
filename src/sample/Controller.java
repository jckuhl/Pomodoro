package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Controller {
    @FXML private TextArea txtArea;
    @FXML private VBox logOutput;
    @FXML private Button resumeBtn;
    @FXML private Button startBtn;
    @FXML private Text timer;
    @FXML private GridPane gridpane;
    @FXML private Text title;
    private Pomodoro pomo;
    public enum Color {
        BLUE, BLACK, GREEN
    }

    public Text getTimer() {
        return this.timer;
    }

    public void handleStart() {
        if(pomo == null) {
            pomo = new Pomodoro(this, 1500, 300);
            pomo.start();
            startBtn.setText("Stop");
        } else {
            pomo.stop();
            pomo = null;
            timer.setText("00:00");
            startBtn.setText("Start");
        }
    }

    public void handleResume() {
        if(pomo != null) {
            pomo.togglePause();
            if(pomo.getPaused()) {
                resumeBtn.setText("Resume");
            } else {
                resumeBtn.setText("Pause");
            }
        }
    }

    public void handleClose() {
        System.exit(0);
    }

    public void handleAdd() {
        if (!txtArea.getText().isEmpty()) {
            Text log = new Text(txtArea.getText().trim());
            logOutput.getChildren().add(log);
        }
    }

    public void updateTimerDisplay(Color color) {
        gridpane.setStyle(String.format("-fx-background-color: %s;", color));
        switch(color) {
            case BLACK:
                title.setText("FOCUS!");
                break;
            case BLUE:
                title.setText("Take a Break");
                break;
            case GREEN:
                title.setText("Pomodoro");
                break;
        }
    }
}
