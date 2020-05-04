package sound;

import utils.SystemTypeChecker;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    private static String DirPath = getPathWithSystem();
    private static String DefaultFile = "normal.wav";
    private static Clip clip;
    
    public static void PlayDefault() {
    	Play(DefaultFile);
    }
    
    public static void Play(String fileName) {
    	 String originalPath = DirPath + fileName;
         File sound = new File(originalPath);

         try {
             AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
             Clip audioClip = AudioSystem.getClip();
             if(audioClip.isOpen()) {
            	 audioClip.close();
             }
             audioClip.open(ais); // Clip opens AudioInputStream
             audioClip.start(); // Start playing audio
             audioClip.loop(Clip.LOOP_CONTINUOUSLY);
             clip = audioClip;
             
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
    }
    
    public static void Stop() {
    	clip.stop();
    }

    private static String getPathWithSystem() {
        if(SystemTypeChecker.getType() == SystemTypeChecker.SystemType.Mac) {
            return "Tracks/";
        }
        return "./Tracks/";
    }

}
