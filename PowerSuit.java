class PowerSuit extends Suit {
    public PowerSuit(String code, int durability) {
        super(code, durability);
    }

    @Override
    public boolean isDurabilityValid() {
        return durability >= 70;
    }

    @Override
    public String getSuitType() {
        return "POWER";
    }
}
