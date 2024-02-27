package src;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    final static int DISTANCE = 100;
    Map<Point, BufferedImage> carPics = new HashMap<>();
    Map<Point, BufferedImage> workshopPics = new HashMap<>();

    void moveit(int oldX, int oldY, int x, int y) {
        Point oldPos = new Point(oldX, oldY);
        BufferedImage car = carPics.get(oldPos);
        carPics.remove(oldPos);
        carPics.put(new Point(x, y), car);
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, String...paths) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message if case file is not found with a try/catch block
        try {
            int i = 1;
            for (String path : paths) {
                this.carPics.put(new Point(0, DISTANCE * i), ImageIO.read(DrawPanel.class.getResourceAsStream(path)));
                i++;
            }
            workshopPics.put(new Point(300, 300), ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg")));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    public void update() {

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        carPics.forEach((key, value) -> {
            g.drawImage(value, key.x, key.y, null); // see javadoc for more info on the parameters
        });
        workshopPics.forEach((key, value) -> {
            g.drawImage(value, key.x, key.y, null);
        });
    }
}
