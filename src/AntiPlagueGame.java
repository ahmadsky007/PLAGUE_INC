import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;


public class AntiPlagueGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}


class MainMenu {
    private JFrame frame;

    public MainMenu() {
        frame = new JFrame("AntiPlague Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("AntiPlague Game", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton newGameButton = new JButton("New Game");
        JButton highScoresButton = new JButton("High Scores");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(newGameButton);
        buttonPanel.add(highScoresButton);
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.CENTER);


        newGameButton.addActionListener(e -> {
            frame.dispose();
            new DifficultySelection();
        });

        highScoresButton.addActionListener(e -> {
            frame.dispose();
            new HighScores();
        });

        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }
}


class DifficultySelection {
    private JFrame frame;

    public DifficultySelection() {
        frame = new JFrame("Select Difficulty");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel label = new JLabel("Select Difficulty Level", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");

        panel.add(label);
        panel.add(easyButton);
        panel.add(mediumButton);
        panel.add(hardButton);

        frame.add(panel);

        easyButton.addActionListener(e -> startGame("Easy"));
        mediumButton.addActionListener(e -> startGame("Medium"));
        hardButton.addActionListener(e -> startGame("Hard"));

        frame.setVisible(true);
    }

    private void startGame(String difficulty) {
        frame.dispose();
        new GameWindow(difficulty);
    }
}


class GameWindow {
    private JFrame frame;
    private JLabel pointsLabel;
    private JLabel timerLabel;
    private int points = 0;
    private int seconds = 0;
    private Timer timer;

    public GameWindow(String difficulty) {
        frame = new JFrame("AntiPlague Game - " + difficulty);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(1, 2));
        pointsLabel = new JLabel("Points: 0", JLabel.CENTER);
        timerLabel = new JLabel("Time: 0s", JLabel.CENTER);
        infoPanel.add(pointsLabel);
        infoPanel.add(timerLabel);

        frame.add(infoPanel, BorderLayout.NORTH);

        JPanel mapPanel = new JPanel(); // Placeholder for the map
        mapPanel.setBackground(Color.LIGHT_GRAY);
        frame.add(mapPanel, BorderLayout.CENTER);

        startTimer();

        frame.setVisible(true);
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                timerLabel.setText("Time: " + seconds + "s");
            }
        });
        timer.start();
    }
}


class HighScores {
    private JFrame frame;

    public HighScores() {
        frame = new JFrame("High Scores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Player1 - 1000 Points - Easy");
        model.addElement("Player2 - 800 Points - Medium");

        JList<String> scoreList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(scoreList);

        frame.add(scrollPane);

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu();
        });

        frame.add(backButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
