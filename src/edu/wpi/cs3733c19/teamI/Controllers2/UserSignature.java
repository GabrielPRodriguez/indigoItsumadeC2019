package edu.wpi.cs3733c19.teamI.Controllers2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComponent;

public class UserSignature extends JComponent
{
    private Image image;
    private Graphics2D graphics;
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public UserSignature()
    {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent event)
            {
                x2 = event.getX();
                y2 = event.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent event)
            {
                x1 = event.getX();
                y1 = event.getY();

                if(graphics != null)
                {
                    graphics.drawLine(x2, y2, x1, y1);
                    repaint();
                    x2 = x1;
                    y2 = y1;
                }
            }
        });
    }

    protected void paintComponent(Graphics x)
    {
        if(image == null)
        {
            image = createImage(getSize().width, getSize().height);
            graphics = (Graphics2D) image.getGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }

        x.drawImage(image, 0, 0, null);
    }

    public void clear()
    {
        graphics.setPaint(Color.white);
        graphics.fillRect(0,0, getSize().width, getSize().height);
        graphics.setPaint(Color.black);
        repaint();
    }

    public void black()
    {
        graphics.setPaint(Color.black);
    }

}
