public enum Rank {
    ACE(14, "Ace", "A"), TWO(2, "Two", "2" ), THREE(3, "Three", "3"), FOUR(4, "Four", "4"), FIVE(5, "Five", "5"), SIX(6, "Six", "6"), SEVEN(7, "Seven", "7"), EIGHT(8, "Eight", "8"), NINE(9, "Nine", "9"), TEN(10, "Ten", "10"), JACK(11, "Jack", "J"), QUEEN(12, "Queen", "Q"), KING(13, "King", "K");

    private final int rankValue;
    private final String name;
    private final String rankLetter;

    Rank(int rankValue, String name, String rankLetter) {
        this.rankValue = rankValue;
        this.name = name;
        this.rankLetter = rankLetter;
    }

    public int getRankValue() {
        return rankValue;
    }

    public String getLetter() { return rankLetter;}

    public String getName() {
        return name;
    }
}