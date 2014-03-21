/**
 * Copyright Parker Harris Emerson, 2014. Created on 21/03/14.
 */
public class Card {
    int rank, suit, owner;
    String location, suitLetter, suitWord, rankLetter, rankWord;

    public String toString() {
        return( this.rankLetter + this.suitLetter + " at " + this.location );
    }

    public void setLoc( String location ) {
        this.location = location;
    }
}
