import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Ensure that GUI creation runs on the Event Dispatch Thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create the repository (the CSV "database").
                SuitRepository repository = new SuitRepository();
                // Create and show the main frame.
                SuitFrame frame = new SuitFrame(repository);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}