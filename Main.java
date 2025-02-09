import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Ensure that GUI creation runs on the Event Dispatch Thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SuitRepository repository = new SuitRepository();
                SuitFrame frame = new SuitFrame(repository);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}