package hu.bme.mit.inf.piclient;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import hu.bme.mit.inf.piclient.ui.RailwayWindow;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
import hu.bme.mit.inf.mqtt.common.data.Section;
import hu.bme.mit.inf.piclient.data.Relations;

/**
 * The application of the railway track visualization.
 *
 * @author zsoltmazlo, benedekh
 */
public class Application {

    public static final Color pageBackground = new Color(0, 0, 54);
    public static final Color inputBackground = new Color(0, 0, 54);
    public static final Color titleForeground = new Color(0xFF, 0xFF, 0xFF);
    public static final Color labelForeground = new Color(0xFF, 0xFF, 0xFF);
    public static final Color sliderForeground = new Color(0xFF, 0xFF, 0xFF);

    public static final Color buttonBackground = new Color(0, 0, 54);

    public static final Font titleFont = new java.awt.Font("BankGothic Lt BT", 0,
            18);
    public static final Font labelFont = new java.awt.Font("Ubuntu Light", 0, 14);
    public static final Font buttonFont = new java.awt.Font("BankGothic Lt BT",
            0, 14);

    // white color; 1 px not rounded.
    public static final Border buttonBorder = javax.swing.BorderFactory.createLineBorder(
            new java.awt.Color(0xFF, 0xFF, 0xFF), 1, false);

    public static final Insets buttonInsets = new Insets(6, 12, 6, 12);

    public static final String SettingsWindow_playButton = "/hu/bme/mit/inf/piclient/ui/ic_action_play_white.png";
    public static final String SettingsWindow_pauseButton = "/hu/bme/mit/inf/piclient/ui/ic_action_pause_white.png";

    public static final Pattern findStrokePattern = Pattern.compile(
            "stroke:#(?<sectionColor>[0-9A-Fa-f]{6});");
    public static final Pattern findFillPattern = Pattern.compile(
            "fill:#(?<sectionColor>[0-9A-Fa-f]{6});");

    public static ImageIcon getIcon(String fileName, Class<?> loader) {
        return new ImageIcon(loader.getResource(fileName));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // start GUI
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                RailwayWindow gui = new RailwayWindow();
                gui.setVisible(true);
                gui.startTurnoutRefresherThread();
            }
        });

    }
    public static final ConcurrentMap<String, Section> sections = Relations.getSections();

}
