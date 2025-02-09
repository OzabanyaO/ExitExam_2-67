import java.util.*;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

class SuitController {
    private SuitRepository repository;
    private SuitFrame frame;
    private Map<String, Integer> repairCount; // Map to track the number of repairs per suit type.

    public SuitController(SuitRepository repository, SuitFrame frame) {
        this.repository = repository;
        this.frame = frame;
        repairCount = new HashMap<>();
        repairCount.put("POWER", 0);
        repairCount.put("STEALTH", 0);
        repairCount.put("INCOGNITO", 0);
    }

    public void checkSuit(String code) {
        // Validate the code format: 6 digits and first digit not 0
        if (code == null || !Pattern.matches("^[1-9]\\d{5}$", code)) {
            JOptionPane.showMessageDialog(frame, 
                    "Invalid suit code format. Please enter a 6-digit code with the first digit not 0.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Retrieve the suit from the repository
        Suit suit = repository.getSuitByCode(code);
        if (suit == null) {
            JOptionPane.showMessageDialog(frame, 
                    "No suit found with the code " + code + ".",
                    "Not Found", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show suit details on the result panel
        frame.showResultPanel(suit);
    }

    public void repairSuit(Suit suit) {
        int newDurability = suit.getDurability() + 25;
        if (newDurability > 100) {
            newDurability = 100;
        }
        suit.setDurability(newDurability);

        // Update the repair count for this suit type.
        String type = suit.getSuitType();
        repairCount.put(type, repairCount.get(type) + 1);

        JOptionPane.showMessageDialog(frame, 
                "Suit repaired successfully. New durability: " + suit.getDurability(),
                "Repair Successful", JOptionPane.INFORMATION_MESSAGE);

        // Update the repair summary displayed on the screen.
        frame.updateRepairSummary();
    }

    public Map<String, Integer> getRepairCount() {
        return repairCount;
    }
}