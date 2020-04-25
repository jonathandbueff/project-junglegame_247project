package sound;

import ui.Texture;
import utils.SystemTypeChecker;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    private static String DirPath = getPathWithSystem();

    public Sound(){
        String originalPath = DirPath + "normal.wav";
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

    private static String getPathWithSystem() {
        if(SystemTypeChecker.getType() == SystemTypeChecker.SystemType.Mac) {
            return "Tracks/";
        }
        return "./Tracks/";
    }




}
