
import javax.swing.*;
import java.awt.*;
import java.util.Random;



public class stackGui extends JFrame {

    private stack stack = new stack();
    private JTextField input = new JTextField(10);
    private JPanel stackPanel = new JPanel();
    private JLabel status = new JLabel("Stack is empty");
    private JLabel sizeLabel = new JLabel("Size: 0 / 10");

    public stackGui() {
        setTitle("Stack Visualizer");
        setSize(450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter value:"));
        topPanel.add(input);
        topPanel.add(createButton("PUSH", this::push));

        add(topPanel, BorderLayout.NORTH);

        stackPanel.setLayout(new BoxLayout(stackPanel, BoxLayout.Y_AXIS));
        stackPanel.setBorder(
                BorderFactory.createTitledBorder("Stack - TOP"));
        stackPanel.setBackground(Color.WHITE);

        add(new JScrollPane(stackPanel), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));

        buttonPanel.add(createButton("POP", this::pop));
        buttonPanel.add(createButton("PEEK", this::peek));
        buttonPanel.add(createButton("CLEAR", this::clear));
        buttonPanel.add(createButton("CHECK STACK", this::checkStack));
        buttonPanel.add(createButton("RANDOM", this::random));
        buttonPanel.add(createButton("SIZE", this::showSize));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(sizeLabel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(status, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        refreshStack();
    }

    private JButton createButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.addActionListener(e -> action.run());
        return button;
    }

    private void push() {
        try {
            int value = Integer.parseInt(input.getText().trim());

            if (stack.isFull()) {
                status.setText("STACK OVERFLOW: Stack is full");
            } else {
                stack.push(value);
                status.setText("Pushed: " + value);
                input.setText("");
            }

            refreshStack();

        } catch (NumberFormatException e) {
            status.setText("Please enter a valid integer");
        }
    }

    private void pop() {
        if (stack.isEmpty()) {
            status.setText("STACK UNDERFLOW: Stack is empty");
        } else {
            int value = stack.pop();
            status.setText("Popped: " + value);
        }

        refreshStack();
    }

    private void peek() {
        if (stack.isEmpty()) {
            status.setText("Stack is empty. Nothing to peek.");
        } else {
            status.setText("Top element: " + stack.peek());
        }
    }

    private void clear() {
        stack.clear();
        status.setText("Stack cleared");
        refreshStack();
    }

    private void checkStack() {
        if (stack.isEmpty()) {
            status.setText("The stack is empty");
        } else {
            status.setText("The stack is not empty");
        }
    }

    private void random() {
        stack.clear();

        Random random = new Random();
        int count = random.nextInt(10) + 1;

        for (int i = 0; i < count; i++) {
            int value = random.nextInt(100) + 1;
            stack.push(value);
        }

        status.setText("Generated " + count + " random elements");
        refreshStack();
    }

    private void showSize() {
        status.setText("Current stack size: " + stack.size());
    }

    private void refreshStack() {
        stackPanel.removeAll();

        for (int i = stack.size() - 1; i >= 0; i--) {
            JLabel block = new JLabel(
                    "          " + stack.getElement(i) + "          ",
                    SwingConstants.CENTER);

            block.setOpaque(true);

            if (i == stack.size() - 1) {
                block.setBackground(new Color(70, 130, 220));
                block.setForeground(Color.WHITE);
            } else {
                block.setBackground(new Color(190, 215, 245));
                block.setForeground(Color.BLACK);
            }

            block.setFont(new Font("Arial", Font.BOLD, 18));
            block.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            block.setAlignmentX(Component.CENTER_ALIGNMENT);
            block.setMaximumSize(new Dimension(250, 40));

            stackPanel.add(block);
            stackPanel.add(Box.createVerticalStrut(3));
        }

        sizeLabel.setText("Size: " + stack.size() + " / 10");

        stackPanel.revalidate();
        stackPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new stackGui().setVisible(true);
        });
    }
}

