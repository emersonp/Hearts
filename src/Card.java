/**
 * Copyright Parker Harris Emerson, 2014. Created on 21/03/14.
 */
public class Card {
    private Rank rank;
    private Suit suit;
    private int owner;
    private CardLocation location;

    public void setSuit( Suit suit ) {
        this.suit = suit;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public void setRank( Rank rank ) {
        this.rank = rank;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setOwner( int Player ) {
        this.owner = Player;
    }

    public int getOwner() {
        return this.owner;
    }

    //String location, suitLetter, suitWord, rankLetter, rankWord;

    public String toString() {
        return( this.rank.getName() + this.suit.getLetter() + " at " + this.location );
    }

    public String shortPrint() {
        return( this.rank.getLetter() + this.suit.getLetter() + " " );
    }

    public void setLoc( CardLocation location ) {
        this.location = location;
    }

    public CardLocation getLoc() {
        return this.location;
    }
}
