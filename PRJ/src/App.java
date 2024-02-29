import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme;

import UserInterface.Form.SplashAntBot;
//Hola patmic
public class App {
        public static void main(String[] args) throws Exception {

          
          try {
                  UIManager.setLookAndFeel(new FlatAtomOneDarkIJTheme());
              } catch (UnsupportedLookAndFeelException e) {
              e.printStackTrace();
          } 
    
          SplashAntBot.show();
                
        }  
    }
   

