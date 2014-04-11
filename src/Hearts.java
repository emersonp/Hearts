import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Copyright Parker Harris Emerson, 2014. Created on 13/03/14.
 */

public class Hearts {
    private static ArrayList<Card> deckOfCards = new ArrayList<Card>(52);

    private static int totalTurnPoints = 0;
    private static int totalGamePoints = 0;
    private static int turnOrder = 0;
    private static int trickWinner = 0;

    private static int[] roundPoints = {0,0,0,0};
    private static int[] gamePoints = {0,0,0,0};

    private static boolean startOfTrick = true;
    private static boolean roundOver = false;
    private static boolean gameOver = false;
    private static boolean cardLegal = false;
    private static boolean deuceClubsDealt = false;
    private static boolean firstTrick = false;

    private static Suit cardToPlaySuit;
    private static Suit trickSuit;
    private static Rank cardToPlayRank;

    public static void main( String[] args ) {
        while (!gameOver) {
            arrangeDeckAndGame();
            playGame();
            endRound();
        }
    }

    public static void arrangeDeckAndGame() {
        createDeck();
        shuffleDeck();
        dealDeck();
        sortHand();
    }

    public static void playGame() {
        while (!roundOver) {
            if (startOfTrick)
                startTrick();
            displayRoundScore();
            displayTable();
            displayHand();

            do {
                getCardToPlay();
                out.println("Card Played: " + cardToPlayRank.getName() + " of " + cardToPlaySuit.getName() + " (" + cardToPlayRank.getLetter() + cardToPlaySuit.getLetter() + ")" );
                cardLegal = checkCardPlayedIsLegal();
            } while (!cardLegal);

            playCardToTable();

            turnOrder++;
            for ( int i = 1; i <= 20; i++)
                out.println();

            if ( turnOrder > 4 )
                turnOrder = 1;

            boolean endOfTrick = checkEndTrick();
            if ( endOfTrick )
                endTrick();

            roundOver = checkRoundOver();
        }
    }

    public static void createDeck() {
        out.println( "Creating deck of cards..." );
        for (int i = 1; i <= 4; i++ ) {
            for (int j = 2; j <= 14; j++ ) {
                Card singleCard = new Card();
                switch(i) {
                    case 1: singleCard.setSuit(Suit.CLUBS); break;
                    case 2: singleCard.setSuit(Suit.DIAMONDS); break;
                    case 3: singleCard.setSuit(Suit.HEARTS); break;
                    case 4: singleCard.setSuit(Suit.SPADES); break;
                }
                switch(j) {
                    case 2: singleCard.setRank(Rank.TWO); break;
                    case 3: singleCard.setRank(Rank.THREE); break;
                    case 4: singleCard.setRank(Rank.FOUR); break;
                    case 5: singleCard.setRank(Rank.FIVE); break;
                    case 6: singleCard.setRank(Rank.SIX); break;
                    case 7: singleCard.setRank(Rank.SEVEN); break;
                    case 8: singleCard.setRank(Rank.EIGHT); break;
                    case 9: singleCard.setRank(Rank.NINE); break;
                    case 10: singleCard.setRank(Rank.TEN); break;
                    case 11: singleCard.setRank(Rank.JACK); break;
                    case 12: singleCard.setRank(Rank.QUEEN); break;
                    case 13: singleCard.setRank(Rank.KING); break;
                    case 14: singleCard.setRank(Rank.ACE); break;
                }
                singleCard.setLoc(CardLocation.DECK);
                deckOfCards.add(singleCard);
                /* for( int m = 0; m < deckOfCards.size(); m++ ) {
                    out.println( deckOfCards.get(m).shortPrint() );
                } */
            }
        }
    }

    // COMMENTED OUT BECAUSE OF NEW USE OF ENUM
    /*public static Card setSuit( Card singleCard, int i ) {
        switch(i) {
            case 1: singleCard.suit = 1;
                    singleCard.suitLetter = "C";
                    singleCard.suitWord = "Club";
                    break;
            case 2: singleCard.suit = 2;
                    singleCard.suitLetter = "D";
                    singleCard.suitWord = "Diamond";
                    break;
            case 3: singleCard.suit = 3;
                    singleCard.suitLetter = "H";
                    singleCard.suitWord = "Heart";
                    break;
            case 4: singleCard.suit = 4;
                    singleCard.suitLetter = "S";
                    singleCard.suitWord = "Spade";
                    break;
        }
        return singleCard;
    }*/

    // COMMENTED OUT BECAUSE OF NEW USE OF ENUM
    /* public static Card setRank( Card singleCard, int j ) {
        switch(j) {
            case 2: singleCard.rank = 2;
                singleCard.rankLetter = "2";
                singleCard.rankWord = "Two";
                break;
            case 3: singleCard.rank = 3;
                singleCard.rankLetter = "3";
                singleCard.rankWord = "Three";
                break;
            case 4: singleCard.rank = 4;
                singleCard.rankLetter = "4";
                singleCard.rankWord = "Four";
                break;
            case 5: singleCard.rank = 5;
                singleCard.rankLetter = "5";
                singleCard.rankWord = "Five";
                break;
            case 6: singleCard.rank = 6;
                singleCard.rankLetter = "6";
                singleCard.rankWord = "Six";
                break;
            case 7: singleCard.rank = 7;
                singleCard.rankLetter = "7";
                singleCard.rankWord = "Seven";
                break;
            case 8: singleCard.rank = 8;
                singleCard.rankLetter = "8";
                singleCard.rankWord = "Eight";
                break;
            case 9: singleCard.rank = 9;
                singleCard.rankLetter = "9";
                singleCard.rankWord = "Nine";
                break;
            case 10: singleCard.rank = 10;
                singleCard.rankLetter = "10";
                singleCard.rankWord = "Ten";
                break;
            case 11: singleCard.rank = 11;
                singleCard.rankLetter = "J";
                singleCard.rankWord = "Jack";
                break;
            case 12: singleCard.rank = 12;
                singleCard.rankLetter = "Q";
                singleCard.rankWord = "Queen";
                break;
            case 13: singleCard.rank = 13;
                singleCard.rankLetter = "K";
                singleCard.rankWord = "King";
                break;
            case 14: singleCard.rank = 14;
                singleCard.rankLetter = "A";
                singleCard.rankWord = "Ace";
                break;
        }
        return singleCard;
    } */

    public static void shuffleDeck() {
        out.println( "Shuffling cards..." );
        Random r = new Random();
        for (int i = 0; i < deckOfCards.size(); i++) {
            int randomSpot = r.nextInt(51);
            swapCardsInDeck(deckOfCards, i, randomSpot);
        }
    }

    public static void swapCardsInDeck( ArrayList<Card> deckOfCards, int from, int dest ) {
        Card tempCard = new Card();
        tempCard = deckOfCards.get(from);
        deckOfCards.set( from, deckOfCards.get(dest) );
        deckOfCards.set( dest, tempCard);
    }

    public static void dealDeck() {
        out.println( "Dealing cards..." );
        for (int i =0; i < deckOfCards.size(); i++ ) {
            int handDestination = (i+1) % 4;
            deckOfCards.get(i).setLoc(CardLocation.HAND);
            switch(handDestination) {
                case 1: deckOfCards.get(i).setOwner(1);
                        break;
                case 2: deckOfCards.get(i).setOwner(2);
                        break;
                case 3: deckOfCards.get(i).setOwner(3);
                        break;
                case 0: deckOfCards.get(i).setOwner(4);
                        break;
            }
        }
    }

    public static void sortHand() {
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            for (int j = 0; j < deckOfCards.size(); j++ ) {
                if (deckOfCards.get(i).getOwner() < deckOfCards.get(j).getOwner() ) {
                    swapCardsInDeck( deckOfCards, i, j );
                }
            }
        }
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            for (int j = 0; j < deckOfCards.size(); j++ ) {
                if ( deckOfCards.get(i).getOwner() == deckOfCards.get(j).getOwner() && deckOfCards.get(i).getSuit().getSuitValue() < deckOfCards.get(j).getSuit().getSuitValue() ) {
                    swapCardsInDeck( deckOfCards, i, j );
                }
            }
        }
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            for (int j = 0; j < deckOfCards.size(); j++ ) {
                if ( deckOfCards.get(i).getOwner() == deckOfCards.get(j).getOwner() && deckOfCards.get(i).getSuit().getSuitValue() == deckOfCards.get(j).getSuit().getSuitValue() && deckOfCards.get(i).getRank().getRankValue() < deckOfCards.get(j).getRank().getRankValue() ) {
                    swapCardsInDeck( deckOfCards, i, j );
                }
            }
        }
    }

    public static void startTrick() {
        if ( trickWinner == 0 ) {
            int index = 0;
            for (int i = 0; i < deckOfCards.size(); i++ ) {
                if ( deckOfCards.get(i).getRank().getRankValue() < deckOfCards.get(index).getRank().getRankValue() && deckOfCards.get(i).getLoc() == CardLocation.HAND )
                    index = i;
                else if ( deckOfCards.get(i).getRank() == deckOfCards.get(index).getRank() )
                    if ( deckOfCards.get(i).getSuit().getSuitValue() < deckOfCards.get(index).getSuit().getSuitValue() )
                        index = i;
            }

            turnOrder = deckOfCards.get(index).getOwner();
        }
        else if ( trickWinner > 0 && trickWinner < 5 ) {
            turnOrder = trickWinner;
            trickWinner = 0;
        }
        else
            out.println( "TRICK START DETERMINATION ERROR " );
    }

    public static void displayHand() {
        out.print( "PLAYER " + turnOrder + " HAND:\n" );
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            if (deckOfCards.get(i).getLoc() == CardLocation.HAND && deckOfCards.get(i).getOwner() == turnOrder) {
                out.print( deckOfCards.get(i).getRank().getLetter() + deckOfCards.get(i).getSuit().getLetter() + " " );
            }
        }
        out.println();
        }

    public static void displayTable() {
        out.print( "\nTABLE:\n" );
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            if (deckOfCards.get(i).getLoc() == CardLocation.TABLE) {
                out.print( deckOfCards.get(i).getRank().getLetter() + deckOfCards.get(i).getSuit().getLetter() + " " );
            }
        }
        out.println();
    }

    public static void displayRoundScore() {
        out.print( "\nROUND SCORE: Player One[" + roundPoints[0] + "] / ");
        out.print( "Two[" + roundPoints[1] + "] / ");
        out.print( "Three[" + roundPoints[2] + "] / ");
        out.print( "Four[" + roundPoints[3] + "] / ");
        out.print( "TRICK SUIT: " + trickSuit + " / " );
    }

    public static void getCardToPlay() {
        Scanner keyboard = new Scanner(System.in);
        out.print( "What card would you like to play? (e.g., 'AS' or '10C'): ");
        String cardToPlayInput = keyboard.next();
        if (Character.isDigit(cardToPlayInput.charAt(0))) {
            String cardToPlayRankString = cardToPlayInput.replaceAll("[a-zA-Z]", "");
            int tempCardToPlayRank = Integer.parseInt(cardToPlayRankString);
            switch(tempCardToPlayRank) {
                case 2: cardToPlayRank = Rank.TWO; break;
                case 3: cardToPlayRank = Rank.THREE; break;
                case 4: cardToPlayRank = Rank.FOUR; break;
                case 5: cardToPlayRank = Rank.FIVE; break;
                case 6: cardToPlayRank = Rank.SIX; break;
                case 7: cardToPlayRank = Rank.SEVEN; break;
                case 8: cardToPlayRank = Rank.EIGHT; break;
                case 9: cardToPlayRank = Rank.NINE; break;
                case 10: cardToPlayRank = Rank.TEN; break;
            }
            String tempStringCardToPlaySuit = Character.toString(cardToPlayInput.charAt(cardToPlayInput.length() - 1));
            switch(tempStringCardToPlaySuit) {
                case "C": cardToPlaySuit = Suit.CLUBS; break;
                case "D": cardToPlaySuit = Suit.DIAMONDS; break;
                case "H": cardToPlaySuit = Suit.HEARTS; break;
                case "S": cardToPlaySuit = Suit.SPADES; break;
            }
        }
        else {
            String cardToPlayRankString = Character.toString(cardToPlayInput.charAt(0));
            switch (cardToPlayRankString) {
                case "A":   cardToPlayRank = Rank.ACE; break;
                case "K":   cardToPlayRank = Rank.KING; break;
                case "Q":   cardToPlayRank = Rank.QUEEN; break;
                case "J":   cardToPlayRank = Rank.JACK; break;
            }
            String tempStringCardToPlaySuit = Character.toString(cardToPlayInput.charAt(cardToPlayInput.length() - 1));
            switch(tempStringCardToPlaySuit) {
                case "C": cardToPlaySuit = Suit.CLUBS; break;
                case "D": cardToPlaySuit = Suit.DIAMONDS; break;
                case "H": cardToPlaySuit = Suit.HEARTS; break;
                case "S": cardToPlaySuit = Suit.SPADES; break;
            }
        }
    }

    public static boolean checkCardPlayedIsLegal() {
        boolean cardInHand = false;
        boolean validTrickOrder = false;
        cardInHand = checkCardInHand();
        if (startOfTrick)
            validTrickOrder = checkLegalStartTrick();
        else
            validTrickOrder = checkLegalFollowTrick();

        if ( cardInHand && validTrickOrder )
            return true;
        else
            return false;
    }

    public static boolean checkLegalStartTrick() {
        boolean legalStartTrick = false;
        if (!deuceClubsDealt && cardToPlayRank == Rank.TWO && cardToPlaySuit == Suit.CLUBS ) {
            legalStartTrick = true;
            deuceClubsDealt = true;
            trickSuit = Suit.CLUBS;
            firstTrick = true;
            startOfTrick = false;
        }
        else if (deuceClubsDealt) {
            legalStartTrick = true;
            trickSuit = cardToPlaySuit;
            firstTrick = false;
            startOfTrick = false;
        }
        return legalStartTrick;
    }

    public static boolean checkLegalFollowTrick() {
        boolean legalFollowTrick = false;
        boolean hasTrickSuit = false;
        String cantFollowReason = "You must follow the Trick Suit.";

        for (int i = 0; i < deckOfCards.size(); i++ )
            if (deckOfCards.get(i).getOwner() == turnOrder && deckOfCards.get(i).getLoc() == CardLocation.HAND && deckOfCards.get(i).getSuit() ==trickSuit)
                hasTrickSuit = true;

        if ( !hasTrickSuit || cardToPlaySuit.equals(trickSuit) )
            legalFollowTrick = true;

        if ( !hasTrickSuit && firstTrick && (cardToPlaySuit == Suit.HEARTS || ( cardToPlaySuit == Suit.SPADES && cardToPlayRank == Rank.QUEEN)) ) {
            legalFollowTrick = false;
            cantFollowReason = "You can't play a Heart or the QS on the first trick.";
        }

        if (!legalFollowTrick)
            out.println( "ERROR: " + cantFollowReason);

        return legalFollowTrick;
    }

    public static boolean checkCardInHand() {
        boolean cardInHand = false;
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            if (deckOfCards.get(i).getRank() ==cardToPlayRank && deckOfCards.get(i).getSuit() == cardToPlaySuit && turnOrder == deckOfCards.get(i).getOwner() )
                cardInHand = true;
        }
        if (!cardInHand)
            out.println( "Please choose a card in your hand." );
        return cardInHand;
    }

    public static void playCardToTable() {
        for (int i = 0; i < deckOfCards.size(); i++ )
            if ( cardToPlayRank == deckOfCards.get(i).getRank() && cardToPlaySuit == deckOfCards.get(i).getSuit() )
                deckOfCards.get(i).setLoc( CardLocation.TABLE );
    }

    public static boolean checkEndTrick() {
        int cardsOnTable = 0;
        for (int i = 0; i < deckOfCards.size(); i++ )
            if ( deckOfCards.get(i).getLoc() == CardLocation.TABLE)
                cardsOnTable++;

        return (cardsOnTable == 4);
        }

    public static void endTrick() {
        trickWinner = checkTrickWinner();
        scoreTrickPoints( trickWinner );
        clearTable();
        startOfTrick = true;
    }

    public static int checkTrickWinner() {
        int index = 0;
        for (int i = 0; i < deckOfCards.size(); i++ )
            if ( deckOfCards.get(i).getLoc() == CardLocation.TABLE )
                if ( deckOfCards.get(i).getSuit() == trickSuit && deckOfCards.get(i).getRank().getRankValue() >= deckOfCards.get(index).getRank().getRankValue() )
                    index = i;

        trickWinner = deckOfCards.get(index).getOwner();

        out.print( "\nPlayer " + trickWinner + " won the trick, taking the cards: ");
        for ( Card forTemp : deckOfCards ) {
            if ( forTemp.getLoc() == CardLocation.TABLE )
                out.print( forTemp.shortPrint() );
        }
        out.println();

        return trickWinner;
    }

    public static void scoreTrickPoints( int trickWinner ) {
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            if ( deckOfCards.get(i).getLoc() == CardLocation.TABLE ) {
                if ( deckOfCards.get(i).getSuit() == Suit.HEARTS )
                    roundPoints[trickWinner-1]++;
                else if ( deckOfCards.get(i).getSuit() == Suit.SPADES && deckOfCards.get(i).getRank() == Rank.QUEEN )
                    roundPoints[trickWinner-1] += 13;
                }
            }
        }

    public static void clearTable() {
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            if ( deckOfCards.get(i).getLoc() == CardLocation.TABLE )
                deckOfCards.get(i).setLoc(CardLocation.DISCARD);
        }
    }

    public static boolean checkRoundOver(){
        boolean roundOverTest = false;
        if ( totalTurnPoints >= 26 ) {
            int indexPoints = 0;
            roundOverTest = true;
            for (int i = 0; i < roundPoints.length; i++ ) {
                if ( roundPoints[i] == 26 ) {
                    indexPoints = i;
                    for ( int k = 0; k < roundPoints.length; k++ ) {
                        if ( k != indexPoints )
                            gamePoints[k] += 26;
                    }
                }
                else
                    gamePoints[i] += roundPoints[i];
            }
            for ( int i : roundPoints)
                roundPoints[i] = 0;
        }


        if (roundOverTest)
            displayRoundScore();

        return roundOverTest;
    }

    public static void endRound() {
        addRoundScoreToTotalScore();
        displayGameScore();
        if (totalGamePoints >=100) {
            gameOver = true;
            int gameWinner = 0;
            for ( int i : gamePoints )
                if ( gamePoints[i] >= gamePoints[gameWinner] )
                    gameWinner = i;
            out.println( "Player " + ( gameWinner + 1) + " won the game!" );
        }
    }

    public static void addRoundScoreToTotalScore() {
        for ( int i : gamePoints )
            gamePoints[i] += roundPoints[i];
    }

    public static void displayGameScore() {
        out.print( "\nGAME SCORE: Player One[" + gamePoints[0] + "] / ");
        out.print( "Two[" + gamePoints[1] + "] / ");
        out.print( "Three[" + gamePoints[2] + "] / ");
        out.print( "Four[" + gamePoints[3] + "]");
    }
}
