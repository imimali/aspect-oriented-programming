package contest.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Nov 24, 2011
 * Time: 1:26:48 AM
 */
public class GUIUtils {

    public static JPanel putInPanel(Component component) {
        JPanel pan=new JPanel();
        pan.add(component);
        return pan;
    }
}
