package core;


import sound.Sound;
import ui.WindowController;

public class JungleApp {

    public static void main(String[] args) {
  	
        WindowController controller = new WindowController();      
        controller.centerWindow();
        Sound.PlayDefault();
        controller.run();

    }

}