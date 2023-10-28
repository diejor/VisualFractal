package ui;

import fractals.tools.PrintableFractal;

import javax.swing.*;
import java.awt.*;

public class FractalPanel extends JPanel {
    private FractalGraphics fractalDisplay;

    public FractalPanel(Dimension size) {
        setLayout(new GridBagLayout());
        this.layFractalDisplay(size);
    }

    public void layFractalDisplay(Dimension size) {
        this.fractalDisplay = new FractalGraphics(size);
        GridBagConstraints constrains = new GridBagConstraints();
        constrains.weightx = 1;
        constrains.weighty = 1;
        constrains.fill = GridBagConstraints.BOTH;
        this.add(fractalDisplay, constrains);
    }


    public FractalGraphics getFractalDisplay() {
        return this.fractalDisplay;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
