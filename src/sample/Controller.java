package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Controller {
    @FXML private TextArea txtArea;
    @FXML private VBox logOutput;
    @FXML private Button resumeBtn;
    @FXML private Text timer;
    private Pomodoro pomo;

    public void handleStart() {
        if(pomo == null) {
            pomo = new Pomodoro(timer, 1500);
            pomo.start();
        } else {
            pomo = null;
            timer.setText("00:00");
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
}
