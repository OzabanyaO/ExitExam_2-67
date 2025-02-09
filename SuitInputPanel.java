import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class SuitInputPanel extends JPanel {
    private SuitController controller;
    private JTextField codeField;
    private JButton checkButton;

    public SuitInputPanel() {
        setLayout(new BorderLayout(10, 10));
        
        JLabel label = new JLabel("Enter Suit Code (6 digits, first digit not 0): ");
        codeField = new JTextField(10);
        checkButton = new JButton("Check Suit");

        JPanel inputPanel = new JPanel();
        inputPanel.add(label);
        inputPanel.add(codeField);
        add(inputPanel, BorderLayout.CENTER);
        add(checkButton, BorderLayout.SOUTH);

        // When the button is pressed, call the controller's checkSuit method.
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = codeField.getText().trim();
                if (controller != null) {
                    controller.checkSuit(code);
                }
            }
        });
    }

    public void setController(SuitController controller) {
        this.controller = controller;
    }

    public void clearInput() {
        codeField.setText("");
    }
}
