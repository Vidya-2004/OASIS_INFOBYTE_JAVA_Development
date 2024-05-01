import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineExaminationSystem extends JFrame {
    private String username;
    private String password;
    private int timeRemaining;
    private Timer timer;

    private String[] questions = {
        "What is the capital of France?",
            "Which programming language is Java based on?",
            "What is the largest mammal in the world?"
    };

    private String[][] options = {
            {"Paris", "Berlin", "London", "Rome"},
            {"C++", "Python", "JavaScript", "C#"},
            {"Elephant", "Blue Whale", "Giraffe", "Hippopotamus"}
    };

    private int[] correctAnswers = {0, 2, 1};
    private int currentQuestionIndex;

    private JButton loginButton;
    private JButton updateProfileButton;
    private JButton startExamButton;
    private JButton submitButton;
    private JButton logoutButton;
    private JComboBox<String> answerComboBox;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField timerField;

    public OnlineExaminationSystem() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Online Examination System");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel loginPanel = createLoginPanel();
        JPanel examPanel = createExamPanel();
        JPanel controlPanel = createControlPanel();

        add(loginPanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> handleLogin());
        updateProfileButton.addActionListener(e -> updateProfile());
        startExamButton.addActionListener(e -> startExam());
        submitButton.addActionListener(e -> submitAnswer());
        logoutButton.addActionListener(e -> logout());

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimer();
            }
        });
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JTextField();

        loginButton = new JButton("Login");
        updateProfileButton = new JButton("Update Profile");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(updateProfileButton);

        return panel;
    }

    private JPanel createExamPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel questionPanel = new JPanel();
        JLabel questionLabel = new JLabel();
        answerComboBox = new JComboBox<>();

        for (int i = 0; i < options[0].length; i++) {
            answerComboBox.addItem(options[0][i]);
        }

        questionPanel.add(questionLabel);
        questionPanel.add(answerComboBox);

        JPanel timerPanel = new JPanel();
        JLabel timerLabel = new JLabel("Time Remaining:");
        timerField = new JTextField(5);
        timerField.setEditable(false);

        timerPanel.add(timerLabel);
        timerPanel.add(timerField);

        submitButton = new JButton("Submit");

        panel.add(questionPanel, BorderLayout.CENTER);
        panel.add(timerPanel, BorderLayout.NORTH);
        panel.add(submitButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        startExamButton = new JButton("Start Exam");
        logoutButton = new JButton("Logout");

        panel.add(startExamButton);
        panel.add(logoutButton);

        return panel;
    }

    private void handleLogin() {
        username = usernameField.getText();
        password = passwordField.getText();

        // Implement login authentication logic here

        JOptionPane.showMessageDialog(this, "Login Successful! Welcome, " + username + "!");
        remove(getContentPane().getComponent(0)); // Remove login panel
        add(createExamPanel(), BorderLayout.CENTER); // Add exam panel
        revalidate();
        repaint();
    }

    private void updateProfile() {
        // Implement profile update logic here
        JOptionPane.showMessageDialog(this, "Profile Updated!");
    }

    private void startExam() {
        currentQuestionIndex = 0;
        displayQuestion();
        timer.start();
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.length) {
            JLabel questionLabel = (JLabel) ((JPanel) getContentPane().getComponent(1)).getComponent(0);
            questionLabel.setText(questions[currentQuestionIndex]);

            JComboBox<String> answerComboBox = (JComboBox<String>) ((JPanel) getContentPane().getComponent(1)).getComponent(1);
            answerComboBox.removeAllItems();
            for (int i = 0; i < options[currentQuestionIndex].length; i++) {
                answerComboBox.addItem(options[currentQuestionIndex][i]);
            }
        } else {
            finishExam();
        }
    }

    private void updateTimer() {
        timeRemaining--;
        timerField.setText(String.valueOf(timeRemaining));

        if (timeRemaining <= 0) {
            finishExam();
        }
    }

    private void submitAnswer() {
        int selectedAnswerIndex = answerComboBox.getSelectedIndex();
        if (selectedAnswerIndex == correctAnswers[currentQuestionIndex]) {
            // Increment score or perform desired action on correct answer
            JOptionPane.showMessageDialog(this, "Correct Answer!");
        } else {
            // Perform desired action on incorrect answer
            JOptionPane.showMessageDialog(this, "Incorrect Answer!");
        }

        currentQuestionIndex++;
        displayQuestion();
    }

    private void finishExam() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Exam Finished!");
        remove(getContentPane().getComponent(1)); // Remove exam panel
        add(createControlPanel(), BorderLayout.CENTER); // Add control panel
        revalidate();
        repaint();
    }

    private void logout() {
        // Implement logout logic here
        JOptionPane.showMessageDialog(this, "Logout Successful!");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OnlineExaminationSystem().setVisible(true);
        });
    }
}
