import java.io.*;
import java.util.*;

class SuitRepository {
    private List<Suit> suits;
    private final String csvFileName = "suits.csv";

    public SuitRepository() {
        suits = new ArrayList<>();
        File csvFile = new File(csvFileName);
        if (!csvFile.exists()) {
            createSampleCSV();
        }
        loadFromCSV();
    }

    // Create a sample CSV file with at least 50 entries
    private void createSampleCSV() {
        Random random = new Random();
        Set<String> usedCodes = new HashSet<>();
        List<Suit> sampleSuits = new ArrayList<>();

        // Add 10 entries for each suit type
        for (int i = 0; i < 10; i++) {
            sampleSuits.add(new PowerSuit(generateUniqueCode(random, usedCodes), random.nextInt(101)));
            sampleSuits.add(new StealthSuit(generateUniqueCode(random, usedCodes), random.nextInt(101)));
            sampleSuits.add(new IncognitoSuit(generateUniqueCode(random, usedCodes), random.nextInt(101)));
        }
        // Add 20 additional random entries
        String[] types = {"POWER", "STEALTH", "INCOGNITO"};
        for (int i = 0; i < 20; i++) {
            String type = types[random.nextInt(types.length)];
            if (type.equalsIgnoreCase("POWER")) {
                sampleSuits.add(new PowerSuit(generateUniqueCode(random, usedCodes), random.nextInt(101)));
            } else if (type.equalsIgnoreCase("STEALTH")) {
                sampleSuits.add(new StealthSuit(generateUniqueCode(random, usedCodes), random.nextInt(101)));
            } else if (type.equalsIgnoreCase("INCOGNITO")) {
                sampleSuits.add(new IncognitoSuit(generateUniqueCode(random, usedCodes), random.nextInt(101)));
            }
        }
    }

    // Generate a unique suit code (6 digits, first digit not 0)
    private String generateUniqueCode(Random random, Set<String> usedCodes) {
        String code;
        do {
            int num = 100000 + random.nextInt(900000); // 100000-999999
            code = String.valueOf(num);
        } while (usedCodes.contains(code));
        usedCodes.add(code);
        return code;
    }

    // Load suit data from the CSV file into the suits list
    private void loadFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                // Skip header
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    continue;
                }
                String code = parts[0].trim();
                String type = parts[1].trim();
                int durability = Integer.parseInt(parts[2].trim());
                if (type.equalsIgnoreCase("POWER")) {
                    suits.add(new PowerSuit(code, durability));
                } else if (type.equalsIgnoreCase("STEALTH")) {
                    suits.add(new StealthSuit(code, durability));
                } else if (type.equalsIgnoreCase("INCOGNITO")) {
                    suits.add(new IncognitoSuit(code, durability));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    // Retrieve a suit by its code
    public Suit getSuitByCode(String code) {
        for (Suit suit : suits) {
            if (suit.getCode().equals(code)) {
                return suit;
            }
        }
        return null;
    }

    public List<Suit> getSuits() {
        return suits;
    }
}
