import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class SuitResultPanel extends JPanel {
    private SuitController controller;
    private JLabel suitInfoLabel;
    private JButton repairButton;
    private JButton backButton;
    private Suit currentSuit;

    public SuitResultPanel() {
        setLayout(new BorderLayout(10, 10));

        suitInfoLabel = new JLabel("Suit details will appear here");
        suitInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        suitInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(suitInfoLabel, BorderLayout.CENTER);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        repairButton = new JButton("Repair Suit");
        backButton = new JButton("Back");
        buttonPanel.add(repairButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action for the repair button: repair the current suit.
        repairButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (controller != null && currentSuit != null) {
                    controller.repairSuit(currentSuit);
                    updateSuitInfo(currentSuit);
                }
            }
        });

        // Action for the back button: return to the input panel.
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SuitFrame frame = (SuitFrame) SwingUtilities.getWindowAncestor(SuitResultPanel.this);
                frame.showInputPanel();
            }
        });
    }

    public void setController(SuitController controller) {
        this.controller = controller;
    }

    // Update the display with the suit details.
    public void updateSuitInfo(Suit suit) {
        this.currentSuit = suit;
        String info = suit.toString();
        if (suit.isDurabilityValid()) {
            info += " <br><b>Durability check PASSED.</b>";
            repairButton.setEnabled(false);
        } else {
            info += " <br><b>Durability check FAILED.</b>";
            repairButton.setEnabled(true);
        }
        suitInfoLabel.setText("<html>" + info + "</html>");
    }
}
