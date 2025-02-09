class StealthSuit extends Suit {
    public StealthSuit(String code, int durability) {
        super(code, durability);
    }

    @Override
    public boolean isDurabilityValid() {
        return durability >= 50;
    }

    @Override
    public String getSuitType() {
        return "STEALTH";
    }
}
