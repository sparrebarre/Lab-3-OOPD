package src;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<WorkShop<?>> shops = new ArrayList<>();

    // Initializes the panel
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    public void update() { repaint(); }

    public boolean addCar(Car car) { return cars.add(car); }

    public <T extends Car> boolean addShop(WorkShop<T> shop) { return shops.add(shop); }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Print an error message in case file is not found with a try/catch block
        try {
            for (Car car : cars) {
                Point pos = car.getPosition();
                g.drawImage(ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream(car.getImagePath()))), (int) pos.getX(), (int) pos.getY(), null); // see javadoc for more info on the parameters
            }
            for (WorkShop<?> shop : shops ) {
                Point pos = shop.getPosition();
                g.drawImage(ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream(shop.getImagePath()))), (int) pos.getX(), (int) pos.getY(), null);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
