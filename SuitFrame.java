import javax.swing.*;
import java.awt.*;
import java.util.Map;

class SuitFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardsPanel;   // Container for input and result panels
    private SuitInputPanel inputPanel;
    private SuitResultPanel resultPanel;
    private JPanel summaryPanel; 
    private JLabel summaryLabel; 
    private SuitController controller;

    public SuitFrame(SuitRepository repository) {
        setTitle("Superhero Suit Checker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLayout(new BorderLayout());

        // Create the card panel with CardLayout.
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        // Create the input and result panels.
        inputPanel = new SuitInputPanel();
        resultPanel = new SuitResultPanel();
        cardsPanel.add(inputPanel, "input");
        cardsPanel.add(resultPanel, "result");

        // Create the summary panel.
        summaryPanel = new JPanel();
        summaryLabel = new JLabel();
        summaryPanel.add(summaryLabel);

        // Add panels to the frame.
        add(cardsPanel, BorderLayout.CENTER);
        add(summaryPanel, BorderLayout.SOUTH);

        // Create the controller and pass this frame.
        controller = new SuitController(repository, this);

        // Provide the controller to the panels.
        inputPanel.setController(controller);
        resultPanel.setController(controller);

        updateRepairSummary(); // Update the repair summary.s

        // Show the input panel.
        cardLayout.show(cardsPanel, "input");
    }

    public void showResultPanel(Suit suit) {
        resultPanel.updateSuitInfo(suit);
        cardLayout.show(cardsPanel, "result");
    }

    public void showInputPanel() {
        inputPanel.clearInput();
        cardLayout.show(cardsPanel, "input");
    }

    public void updateRepairSummary() {
        Map<String, Integer> counts = controller.getRepairCount();
        String summaryText = String.format("Repair Summary - POWER: %d, STEALTH: %d, INCOGNITO: %d",
                counts.get("POWER"), counts.get("STEALTH"), counts.get("INCOGNITO"));
        summaryLabel.setText(summaryText);
    }
}