class IncognitoSuit extends Suit {
    public IncognitoSuit(String code, int durability) {
        super(code, durability);
    }

    @Override
    public boolean isDurabilityValid() {
        int lastDigit = durability % 10;
        return (lastDigit != 3 && lastDigit != 7);
    }

    @Override
    public String getSuitType() {
        return "INCOGNITO";
    }
}
