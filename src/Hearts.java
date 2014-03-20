import org.omg.DynamicAny._DynArrayStub;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Copyright Parker Harris Emerson, 2014. Created on 13/03/14.
 */
class Card {
    int rank, suit, owner;
    String location, suitLetter, suitWord, rankLetter, rankWord;

    public String toString() {
        return( this.rankLetter + this.suitLetter + " at " + this.location );
    }

    public void setLoc( String location ) {
        this.location = location;
    }
}

public class Hearts {
    private static int totalTurnPoints = 0;

    private static boolean gameOver = false;
    private static int turnOrder = 0;
    private static boolean startOfTrick = true;
    private static String cardToPlaySuit = "";
    private static int cardToPlayRank = 0;
    private static boolean cardLegal = false;
    private static boolean deuceClubsDealt = false;
    private static String trickSuit = "";
    private static boolean firstTrick = false;
    private static int[] turnPoints = {0,0,0,0};
    private static int[] gamePoints = {0,0,0,0};
    private static ArrayList<Card> deckOfCards = new ArrayList<Card>(52);
    private static int trickWinner = 0;

    public static void main( String[] args ) {
        createDeck();
        shuffleDeck();
        dealDeck();
        sortHand();
        playGame();

        //for (int i = 0; i < deckOfCards.size(); i++ )
        //    out.println(deckOfCards.get(i));
    }

    public static void createDeck() {
        for (int i = 1; i <= 4; i++ ) {
            for (int j = 2; j <= 14; j++ ) {
                Card singleCard = new Card();
                singleCard = setSuit( singleCard, i);
                singleCard = setRank(singleCard, j);
                singleCard.location = "Deck";
                deckOfCards.add(singleCard);
            }
        }
    }

    public static Card setSuit( Card singleCard, int i ) {
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
    }

    public static Card setRank( Card singleCard, int j ) {
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
    }

    public static void shuffleDeck() {
        Random r = new Random();
        for (int i = 0; i < deckOfCards.size(); i++) {
            int randomSpot = r.nextInt(51);
            deckOfCards = swapCardsInDeck(deckOfCards, i, randomSpot);
        }
    }

    public static ArrayList swapCardsInDeck( ArrayList<Card> deckOfCards, int from, int dest ) {
        Card tempCard = new Card();
        tempCard = deckOfCards.get(from);
        deckOfCards.set( from, deckOfCards.get(dest) );
        deckOfCards.set( dest, tempCard);

        return deckOfCards;
    }

    public static void dealDeck() {
        for (int i =0; i < deckOfCards.size(); i++ ) {
            int handDestination = (i+1) % 4;
            switch(handDestination) {
                case 1: deckOfCards.get(i).location = "Hand";
                        deckOfCards.get(i).owner = 1;
                        break;
                case 2: deckOfCards.get(i).location = "Hand";
                        deckOfCards.get(i).owner = 2;
                        break;
                case 3: deckOfCards.get(i).location = "Hand";
                        deckOfCards.get(i).owner = 3;
                        break;
                case 0: deckOfCards.get(i).location = "Hand";
                        deckOfCards.get(i).owner = 4;
                        break;
            }
        }
    }

    public static void playGame() {
        //int turns = 1;
        while (!gameOver) {
            if (startOfTrick)
                startTrick();
            displayScore();
            displayTable();
            displayHand();

            do {
            getCardToPlay();
            out.println("Card Played: " + cardToPlayRank + cardToPlaySuit);
            cardLegal = checkCardPlayedIsLegal();
            } while (!cardLegal);

            playCardToTable();

            turnOrder++;
            if ( turnOrder > 4 )
                turnOrder = 1;

            boolean endOfTrick = checkEndTrick();
            if ( endOfTrick )
                endTrick();

            //turns++;

            gameOver = checkRoundOver();
        }
    }

    public static boolean checkRoundOver(){
        boolean roundOverTest = false;
        if ( totalTurnPoints >= 26 ) {
            int indexPoints = 0;
            roundOverTest = true;
            for (int i = 0; i < turnPoints.length; i++ ) {
                if ( turnPoints[i] == 26 ) {
                    indexPoints = i;
                    for ( int k = 0; k < turnPoints.length; k++ ) {
                        if ( k != indexPoints )
                            gamePoints[k] += 26;
                    }
                }
                else
                    gamePoints[i] += turnPoints[i];
            }
            for ( int i : turnPoints )
                turnPoints[i] = 0;
        }


        if (roundOverTest)
            displayScore();

        return roundOverTest;
    }

    public static void sortHand() {
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            for (int j = 0; j < deckOfCards.size(); j++ ) {
                if (deckOfCards.get(i).owner < deckOfCards.get(j).owner ) {
                    swapCardsInDeck( deckOfCards, i, j );
                }
            }
        }
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            for (int j = 0; j < deckOfCards.size(); j++ ) {
                if ( deckOfCards.get(i).owner == deckOfCards.get(j).owner && deckOfCards.get(i).suit < deckOfCards.get(j).suit ) {
                    swapCardsInDeck( deckOfCards, i, j );
                }
            }
        }
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            for (int j = 0; j < deckOfCards.size(); j++ ) {
                if ( deckOfCards.get(i).owner == deckOfCards.get(j).owner && deckOfCards.get(i).suit == deckOfCards.get(j).suit && deckOfCards.get(i).rank < deckOfCards.get(j).rank ) {
                    swapCardsInDeck( deckOfCards, i, j );
                }
            }
        }
    }

    public static void displayHand() {
        out.print( "PLAYER " + turnOrder + " HAND:\n" );
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            if (deckOfCards.get(i).location.equals("Hand") && deckOfCards.get(i).owner == turnOrder) {
                out.print( deckOfCards.get(i).rankLetter + deckOfCards.get(i).suitLetter + " " );
            }
        }
        out.println();
        }

    public static void displayTable() {
        out.print( "\nTABLE:\n" );
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            if (deckOfCards.get(i).location.equals("Table")) {
                out.print( deckOfCards.get(i).rankLetter + deckOfCards.get(i).suitLetter + " " );
            }
        }
        out.println();
    }

    public static void getCardToPlay() {
        Scanner keyboard = new Scanner(System.in);
        out.print( "What card would you like to play?: ");
        String cardToPlayInput = keyboard.next();
        if (Character.isDigit(cardToPlayInput.charAt(0))) {
            String cardToPlayRankString = cardToPlayInput.replaceAll("[a-zA-Z]", "");
            cardToPlayRank = Integer.parseInt(cardToPlayRankString);
            cardToPlaySuit = Character.toString(cardToPlayInput.charAt(cardToPlayInput.length() - 1));
        }
        else {
            String cardToPlayRankString = Character.toString(cardToPlayInput.charAt(0));
            switch (cardToPlayRankString) {
                case "A":   cardToPlayRank = 14;
                            break;
                case "K":   cardToPlayRank = 13;
                            break;
                case "Q":   cardToPlayRank = 12;
                            break;
                case "J":   cardToPlayRank = 11;
            }
            cardToPlaySuit = Character.toString(cardToPlayInput.charAt(cardToPlayInput.length() - 1));
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
        if (!deuceClubsDealt && cardToPlayRank == 2 && cardToPlaySuit.equals("C") ) {
            legalStartTrick = true;
            deuceClubsDealt = true;
            trickSuit = "C";
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

        for (int i = 0; i < deckOfCards.size(); i++ )
            if (deckOfCards.get(i).owner == turnOrder && deckOfCards.get(i).location.equals("Hand") && deckOfCards.get(i).suitLetter.equals(trickSuit))
                hasTrickSuit = true;

        if ( !hasTrickSuit || cardToPlaySuit.equals(trickSuit) )
            legalFollowTrick = true;

        if ( !hasTrickSuit && firstTrick && (cardToPlaySuit.equals("H") || ( cardToPlaySuit.equals("S") && cardToPlayRank == 12)) )
            legalFollowTrick = false;
        return legalFollowTrick;
    }

    public static boolean checkCardInHand() {
        boolean cardInHand = false;
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            if (deckOfCards.get(i).rank ==cardToPlayRank && deckOfCards.get(i).suitLetter.equals(cardToPlaySuit) )
                cardInHand = true;
        }
        if (!cardInHand)
            out.println( "Please choose a card in your hand." );
        return cardInHand;
    }

    public static void playCardToTable() {
        for (int i = 0; i < deckOfCards.size(); i++ )
            if ( cardToPlayRank == deckOfCards.get(i).rank && cardToPlaySuit.equals(deckOfCards.get(i).suitLetter))
                deckOfCards.get(i).setLoc("Table");
    }

    public static void startTrick() {
        if ( trickWinner == 0 ) {
            int index = 0;
            for (int i = 0; i < deckOfCards.size(); i++ ) {
                if ( deckOfCards.get(i).rank < deckOfCards.get(index).rank && deckOfCards.get(i).location.equals("Hand") )
                    index = i;
                else if ( deckOfCards.get(i).rank == deckOfCards.get(index).rank )
                    if ( deckOfCards.get(i).suit < deckOfCards.get(index).suit )
                        index = i;
            }

            turnOrder = deckOfCards.get(index).owner;
        }
        else if ( trickWinner > 0 && trickWinner < 5 ) {
            turnOrder = trickWinner;
            trickWinner = 0;
        }
        else
            out.println( "TRICK START DETERMINATION ERROR " );

        //if ( deckOfCards.get(index).rank == 2 && deckOfCards.get(index).suit == 1 )
            //deuceClubsDealt = true;
    }

    public static boolean checkEndTrick() {
        int cardsOnTable = 0;
        for (int i = 0; i < deckOfCards.size(); i++ )
            if ( deckOfCards.get(i).location.equals("Table"))
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
            if ( deckOfCards.get(i).location.equals("Table") )
                if ( deckOfCards.get(i).suitLetter.equals(trickSuit) && deckOfCards.get(i).rank >= deckOfCards.get(index).rank )
                    index = i;

        trickWinner = deckOfCards.get(index).owner;

        out.print( "\nPlayer " + trickWinner + " won the trick, taking the cards: ");
        for ( Card forTemp : deckOfCards ) {
            if ( forTemp.location.equals("Table") )
                out.print( forTemp.rankLetter + forTemp.suitLetter + " " );
        }
        out.println();

        return trickWinner;
    }

    public static void scoreTrickPoints( int trickWinner ) {
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            if ( deckOfCards.get(i).location.equals("Table") ) {
                if ( deckOfCards.get(i).suitLetter.equals("H") )
                    turnPoints[trickWinner-1]++;
                else if ( deckOfCards.get(i).suitLetter.equals("S") && deckOfCards.get(i).rank == 12 )
                    turnPoints[trickWinner-1] += 13;
                }
            }
        }

    public static void clearTable() {
        for (int i = 0; i < deckOfCards.size(); i++ ) {
            if ( deckOfCards.get(i).location.equals("Table") )
                deckOfCards.get(i).location = "Discard";
        }
    }

    public static void displayScore() {
        out.print( "\nSCORE: Player One[" + turnPoints[0] + "] / ");
        out.print( "Two[" + turnPoints[1] + "] / ");
        out.print( "Three[" + turnPoints[2] + "] / ");
        out.print( "Four[" + turnPoints[3] + "] / ");
        out.print( "TRICK SUIT: " + trickSuit + " / " );
    }
}
