import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Play {

    //init number of Aces
    public static int numOfAces = 0;

    public static void main(String[] args) {

        ArrayList<String> cards = new ArrayList<String>();
        CreateDeck(cards);

        //Game example for first deal. Must rewrite.
        //after each deal cards must be removed from the ArrayList
        int dealerCard1 = randomCard(cards);
        int dealerCard2 = randomCard(cards);

        //test
        //int dealerCount = getCount("A") + getCount("K") + getCount("7") + getCount("A");
        int dealerCount = getCount(cardConverter(dealerCard1)) +
                getCount(cardConverter(dealerCard2));
        System.out.println("Dealer have: " + cardConverter(dealerCard1) + " " + "X");
//		System.out.println(dealerCard1 + " " + dealerCard2);


        /*
        * Decrease total dealer count by 10, if there is an Ace and sum is > 21
        * decrease number of aces also
        * */
        while (dealerCount > 21 && numOfAces > 0) {
            dealerCount -= 10;
            numOfAces--;
        }

        /*
        * make number of aces = 0 after you finish with the dealer,
        * before counting the player hand
        * */
        if(numOfAces > 0) {
            numOfAces = 0;
        }

        //test
        //System.out.println(dealerCount);

        cards.remove(cards.indexOf(dealerCard1 + ""));
        cards.remove(cards.indexOf(dealerCard2 + ""));

        int playerCard1 = randomCard(cards);
        int playerCard2 = randomCard(cards);
        int playerCount = getCount(cardConverter(playerCard1)) + getCount(cardConverter(playerCard2));
        System.out.println("You have: " + cardConverter(playerCard1) + " " + cardConverter(playerCard2));
//		System.out.println(playerCard1 + " " + playerCard2);
        cards.remove(cards.indexOf(playerCard1 + ""));
        cards.remove(cards.indexOf(playerCard2 + ""));
        //End of game example

        //Game example for the next deals. Must rewrite.
        System.out.println("Deal or stay? [d/s]");
        Scanner sc = new Scanner(System.in);
        String command = sc.next();

        if(command.equals("d")){

            int playerCard3 = randomCard(cards);
            playerCount += getCount(cardConverter(playerCard3));

            /*
            * decrease total player count by 10, if there is an Ace and sum is > 21
            * decrease number of aces also
            * */
            while (playerCount > 21 && numOfAces > 0) {
                playerCount -= 10;
                numOfAces--;
            }

            /*
            * make number of aces = 0 after you finish with the player,
            * uncomment if counting the dealer after that
            * */
            //if(numOfAces > 0) {
            //    numOfAces = 0;
            //}

            //test
            //System.out.println(playerCount);

            if(playerCount > 21){
                System.out.println("You lost!");
            }
            else if(playerCount < 21){
                System.out.println("You have: " + cardConverter(playerCard1) +
                        " " + cardConverter(playerCard2) +
                        " " + cardConverter(playerCard3));
                System.out.println("Deal or stay? [d/s]");
                String newcommand = sc.next();
            }

        }
        else if(command.equals("s")){
            if(dealerCount > playerCount){
                System.out.println("Dealer have: " + cardConverter(dealerCard1) + " " + cardConverter(dealerCard2));
                System.out.println("You lost!");
            }
            else if(dealerCount < playerCount){
                System.out.println("Dealer have: " + cardConverter(dealerCard1) + " " + cardConverter(dealerCard2));
                System.out.println("You Win!");
            }
        }
        //End of game example
    }


    public static void CreateDeck(ArrayList<String> cards){

        cards.removeAll(cards);

        for (int i = 1; i < 7; i++) {				//6 decks of cards
            for (int j = 1; j <= 13; j++) {			//13 cards in each deck
                for (int k = 1; k <= 4; k++) {		//4 colors for each type of card
                    cards.add(i + "" + j + "" + k);
                }
            }
        }
    }

    public static int randomCard(ArrayList<String> cards)
    {
        Random randomGenerator = new Random();
        int value = randomGenerator.nextInt(cards.size());
        int randomCard = Integer.parseInt(cards.get(value));
        return randomCard;
    }

    public static String cardConverter(int card)
    {
        String finalCard  = "";
        String typeOfCard = "";
        char typeOfSymbol = ' ';

        if(card < 1000)
        {
            int cardType = (card / 10) % 10;
            switch(cardType)
            {
                case 1: typeOfCard = "A"; break;
                case 2: typeOfCard = "2"; break;
                case 3: typeOfCard = "3"; break;
                case 4: typeOfCard = "4"; break;
                case 5: typeOfCard = "5"; break;
                case 6: typeOfCard = "6"; break;
                case 7: typeOfCard = "7"; break;
                case 8: typeOfCard = "8"; break;
                case 9: typeOfCard = "9"; break;

            }
        }
        else if(card > 1000)
        {
            int cardType = (card / 10) % 100;
            switch(cardType)
            {
                case 10: typeOfCard = "10"; break;
                case 11: typeOfCard = "J"; break;
                case 12: typeOfCard = "Q"; break;
                case 13: typeOfCard = "K"; break;

            }
        }

        int symbolType = card % 10;
        switch(symbolType)
        {
            case 1: typeOfSymbol = '♠'; break;
            case 2: typeOfSymbol = '♥'; break;
            case 3: typeOfSymbol = '♣'; break;
            case 4: typeOfSymbol = '♦'; break;
        }

        finalCard = typeOfCard + typeOfSymbol;
        return finalCard;
    }

    public static int getCount(String card){ //Count method. Must rewrite for the Ace - 1 or 11.

        int count = 0;

        if (card.contains("A"))
        {
            count = 11;
            //increase number of Aces
            numOfAces++;
        }
        else if(card.contains("2"))
        {
            count = 2;
        }
        else if(card.contains("3"))
        {
            count = 3;
        }
        else if(card.contains("4"))
        {
            count = 4;
        }
        else if(card.contains("5"))
        {
            count = 5;
        }
        else if(card.contains("6"))
        {
            count = 6;
        }
        else if(card.contains("7"))
        {
            count = 7;
        }
        else if(card.contains("8"))
        {
            count = 8;
        }
        else if(card.contains("9"))
        {
            count = 9;
        }
        else if(card.contains("10") || card.contains("J") || card.contains("Q") || card.contains("K"))
        {
            count = 10;
        }
        return count;
    }


}