package view;

import javax.swing.*;
import java.awt.*;

public class JPanelGradient extends JPanel
{
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        Color color1 = new Color (0, 65, 106);
        Color color2 = new Color(255,224,0);

        GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}