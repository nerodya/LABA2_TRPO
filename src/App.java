import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class App {

    private JFrame frame;
    private JPanel bottomPanel;
    private Button ButtonСircle;
    private Button ButtonRect;
    private Button ButtonColor;
    private Button ButtonAnimation;
    private Button ButtonDel;
    private DrawerPanel drawerPanel;

    public static void main(String[] args) {
        App app = new App();
        app.Start();
    }

    public App() {
        frame = new JFrame("LAB...");

        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 6));
        bottomPanel.setSize(900, 50);

        drawerPanel = new DrawerPanel();
        drawerPanel.setSize(900, 550);

        mainContainer.add(drawerPanel, BorderLayout.CENTER);
        mainContainer.add(bottomPanel, BorderLayout.SOUTH);

        ButtonСircle = new Button("Добавить круг");
        ButtonСircle.addActionListener(e -> drawerPanel.onAddСircle());
        bottomPanel.add(ButtonСircle);

        ButtonСircle = new Button("Добавить круг");
        ButtonСircle.addActionListener(e -> drawerPanel.onAddСircle());
        bottomPanel.add(ButtonСircle);

        ButtonRect = new Button("Добавить прямоугольник");
        ButtonRect.addActionListener(e -> drawerPanel.onAddRect());
        bottomPanel.add(ButtonRect);

        ButtonColor = new Button("Поменять у объектов цвет");
        ButtonColor.addActionListener(e -> drawerPanel.onRepleceColor());
        bottomPanel.add(ButtonColor);

        ButtonAnimation = new Button("Анимация");
        ButtonAnimation.addActionListener(e -> onAppAnimation());
        bottomPanel.add(ButtonAnimation);

        ButtonDel = new Button("Удалить последнюю фигуру");
        ButtonDel.addActionListener(e -> drawerPanel.onDel());
        bottomPanel.add(ButtonDel);

    }

    public void Start() {
        frame.setVisible(true);
        startTimer();
    }

    public void onAppAnimation(){
        drawerPanel.animating = !drawerPanel.animating;
        ButtonAnimation.setLabel(drawerPanel.animating ? "Стоп" : "Анимация");
    }

    private void startTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(drawerPanel.animating) {
                    drawerPanel.onAnimation();
                }
            }
        };

        java.util.Timer timer = new Timer();
        timer.schedule(task, 0, 10);
    }
}