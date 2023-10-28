package ui;

import javax.swing.*;
import java.awt.*;

public class GuiContainer extends JPanel {
    final JFrame parent;
    public GuiContainer(JFrame parent) {
        super();
        this.parent = parent;
        setPreferredSize(new Dimension(100, 100));
        setBounds(
                100, 100, 100, 100
        );
        setBackground(new Color(162, 70, 160, 50));
    }
}
