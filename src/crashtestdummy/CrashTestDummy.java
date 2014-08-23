package crashtestdummy;

import java.awt.Toolkit;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * https://github.com/TechnicPack/TechnicLauncher/issues/464 reproduced stand-alone.
 *
 * @author Michael Vorburger <mike@vorburger.ch>
 */
public class CrashTestDummy {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        SplashScreen splash = new SplashScreen(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("splash.png")));
        splash.setVisible(true);

    	String s = UIManager.getSystemLookAndFeelClassName();
    	System.out.println("UIManager.getSystemLookAndFeelClassName() = " + s);
        UIManager.setLookAndFeel(s);
        System.out.println("after non-crashed UIManager.setLookAndFeel()");
	}

}
