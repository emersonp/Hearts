public enum Suit {
    CLUBS("C", "Clubs", 1), DIAMONDS("D", "Diamonds", 2), HEARTS("H", "Hearts", 3), SPADES("S", "Spades", 4);

    private final String letter;
    private final String name;
    private final int suitValue;

    Suit(String letter, String name, int suitValue) {
        this.letter = letter;
        this.name = name;
        this.suitValue = suitValue;
    }

    public String getLetter() {
        return letter;
    }

    public String getName() {
        return name;
    }

    public int getSuitValue() { return suitValue; }
}