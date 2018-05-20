package sample;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    private File file;
    private AudioInputStream audioIn;
    private Clip clip;

    public Sound(String filename) {
        file = new File(filename);
        try {
            audioIn = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
        } catch(UnsupportedAudioFileException uafe) {
            uafe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } catch(LineUnavailableException lue) {
            lue.printStackTrace();
        }
    }

    public void playSound() {
        if(clip.isRunning()  || clip.isOpen()) {
            clip.close();
        }
        try {
            clip.open(audioIn);
        } catch (LineUnavailableException lue) {
            lue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        clip.start();
        clip.addLineListener(event -> {
            if(event.getType() == LineEvent.Type.STOP) {
                clip.close();
            }
        });
    }

}
