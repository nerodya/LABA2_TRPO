import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class App {
    private JFrame frame;
    private JPanel bottomPanel;
 //   private Button ButtonСircle;
    private Button ButtonAdd;
    private Button ButtonColor;
    private Button ButtonAnimation;
    private Button ButtonDel;
    private DrawerPanel drawerPanel;
    private final JComboBox shapesList;

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

        shapesList = new JComboBox(new String[]{"Круг", "Прямоугольник", "Овал", "Квадрат"});
        shapesList.setSelectedIndex(0);
        bottomPanel.add(shapesList);

        ButtonAdd = new Button("Добавить");
        ButtonAdd.addActionListener(e -> addShape());
        bottomPanel.add(ButtonAdd);

        ButtonColor = new Button("Поменять у объектов цвет");
        ButtonColor.addActionListener(e -> drawerPanel.onFullRepleceColor());
        bottomPanel.add(ButtonColor);

        ButtonAnimation = new Button("Анимация");
        ButtonAnimation.addActionListener(e -> onAppAnimation());
        bottomPanel.add(ButtonAnimation);

        ButtonDel = new Button("Удалить последнюю фигуру");
        ButtonDel.addActionListener(e -> drawerPanel.onDel());
        bottomPanel.add(ButtonDel);

    }

    private void addShape() {

        String str = shapesList.getSelectedItem().toString();

        switch (str) {
            case ("Круг") -> drawerPanel.onAddСircle();
            case ("Прямоугольник") -> drawerPanel.onAddRect();
            case ("Овал") -> drawerPanel.onAddOval();
            case ("Квадрат") -> drawerPanel.onAddSquare();
        }
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