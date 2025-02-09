abstract class Suit {
    protected String code;      // Suit code (6 digits, first digit cannot be 0)
    protected int durability;   // Durability level (0 to 100)

    public Suit(String code, int durability) {
        this.code = code;
        this.durability = durability;
    }

    public String getCode() {
        return code;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public abstract boolean isDurabilityValid();

    public abstract String getSuitType();

    @Override
    public String toString() {
        return String.format("Suit Code: %s, Type: %s, Durability: %d", code, getSuitType(), durability);
    }
}