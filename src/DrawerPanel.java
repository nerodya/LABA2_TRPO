import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class DrawerPanel extends JPanel {

    ArrayList<Shape> shapes = new ArrayList<>();
    public boolean animating = false;

    public void onAnimation() {
            for (Shape x : shapes){
                x.setY(x.getY() + x.getSpeedY());
                x.setX(x.getX() + x.getSpeedX());
                if (x.getY() <= 0 || x.getY() + x.getHeight() >= 550) {
                    x.setSpeedY(-x.getSpeedY());
                    onRepleceColor(x);
                }
                if (x.getX() <= 0 || x.getX() + x.getWidth() >= 890) {
                    x.setSpeedX(-x.getSpeedX());
                    onRepleceColor(x);
                }
            }
            repaint();
    }

    public void onAddСircle() {
        Circle circle = new Circle();
        circle.randomFill();
        shapes.add(circle);
        repaint();
    }
    public void onAddRect() {
        Rect rect = new Rect();
        rect.randomFill();
        shapes.add(rect);
        repaint();
    }
    public void onAddSquare() {
        Square square = new Square();
        square.randomFill();
        shapes.add(square);
        repaint();
    }
    public void onAddOval() {
        Oval Oval = new Oval();
        Oval.randomFill();
        shapes.add(Oval);
        repaint();
    }
    public void onDel() {
//        shapes.clear();
//        repaint();

        if (shapes.size() != 0) {
            shapes.remove(shapes.size() - 1);
            repaint();
        } else
            new Exception().printStackTrace();
    }

    public void onRepleceColor(Shape x) {
            x.setColor();
        repaint();
    }

    public void onFullRepleceColor(){
        for (Shape x : shapes)
            x.setColor();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        setDoubleBuffered(true);
        for (Shape shape : shapes) {
            shape.draw(g);
        }
    }
}
interface Present {
    void draw(Graphics g);
}

abstract class Shape implements Present {
    private int x, y;
    private int width, height;
    private int speedY = 2;
    private int speedX = 2;
    private Color color = Color.RED;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return height;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getSpeedY() {
        return speedY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getWidth() {
        return width;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setColor() {
        this.color = this.randomColor();
    }

    public int randint(int min, int max) {
        Random rnd = new Random();
        return min + rnd.nextInt(max - min);
    }

    public Color randomColor() {
        Color[] colors = new Color[]{new Color(0, 255, 0),
                            new Color(105, 227, 227),
                            new Color(0, 169, 255),
                            new Color(106, 0, 255),
                            new Color(200, 50, 255),
                            new Color(255, 60, 200),
                            new Color(255, 80, 80),
                            new Color(131, 159, 2),
                            new Color(98, 3, 0),
                            new Color(86, 152, 99),
                            new Color(255, 204, 0)};
        return colors[randint(0, colors.length)];
    }

    public void randomFill() {
        this.x = randint(40, 820);
        this.y = randint(40, 470);
        this.width = randint(20, 80);
        this.height = randint(20, 80);
        this.color = randomColor();
    }
}

class Oval extends Shape{
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}

class Circle extends Shape {
    @Override
    public void randomFill(){
        super.randomFill();
        this.setWidth(this.getHeight());
    }
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}

class Square extends Shape {
    @Override
    public void randomFill(){
        super.randomFill();
        this.setWidth(this.getHeight());
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}

class Rect extends Shape {
    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}