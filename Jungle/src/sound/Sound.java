package sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    private String originalPath = "Tracks/normal.wav";

    public Sound(){
        File sound = new File(originalPath);

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(ais); // Clip opens AudioInputStream
            audioClip.start(); // Start playing audio
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changeSong(String soundPath){
        File sound = new File(soundPath);

        try {
            Clip audioClip = AudioSystem.getClip();
            audioClip.close();
            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
            Clip newAudioClip = AudioSystem.getClip();
            audioClip.open(ais); // Clip opens AudioInputStream
            audioClip.start(); // Start playing audio

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }


}
