/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

enum Suit{
  SPADE,DIAMOND,HEART,CLUB
}

class Card{
  private Suit suit;
  private int value;
  public Card(Suit suit, int value){
    this.suit = suit;
    this.value = value;
  }
  public Suit getSuit(){
    return this.suit;
  }
  public int getValue(){
    return this.value;
  }
  public void print(){
    System.out.println(this.getSuit()+" "+ this.getValue());
  }
}

class Hand{
  private List<Card> cards;
  private int scores;
  public Hand(){
    cards = new ArrayList<>();

  }
  public int getScore(){
    return this.scores;
  }
  public void addCard(Card card){
    // based on SRP, the process of getting a card from deck should be implemented in the controller class
    cards.add(card);
    // update score
    if (card.getValue() == 1){
      if(scores + card.getValue() > 21 ){
        scores += 1;
      }else{
        scores += 11;
      }
    }else{
      scores += card.getValue();
    }
  }
  public int getScores(){
    return this.scores;
  }
  public List<Card> getCards(){
    return this.cards;
  }
  public void print(){
    for(int i = 0; i < cards.size(); i++){
      System.out.println(cards.get(i).getSuit() + "" + cards.get(i).getValue());
    }
  }
}
class Deck{
  private List<Card> cards;
  Random rand = new Random();
  public Deck(){
    cards = new ArrayList<>();
    for (Suit suit: Suit.values()){
      for (int i = 1; i <=13;i++){
        Card card = new Card(suit,Math.min(10, i));
      }
    }
  }
  public void print(){
    for (Card card : cards){
      card.print();
    }
  }
  public Card draw(){
    return cards.remove(cards.size()-1);
  }
  public void shuffle(){
    for(int i = cards.size()-1; i > 0; i--){
      int j = rand.nextInt(i+1);
      Card temp = cards.get(i);
      cards.set(i,cards.get(j));
      cards.set(j, temp);
    }
  }
}
abstract class Player{
  private Hand hand;
  public Player(Hand hand){
    this.hand = hand;
  }
  public Hand getHand(){
    return this.hand;
  }
  public void clearHand(){
    hand = new Hand();
  }
  public void addCard(Card card){
    this.hand.addCard(card);
  }
  abstract boolean makeMove();
}
class UserPlayer extends Player{
  private int balance;
  Scanner scanner = new Scanner (System.in);

	public UserPlayer(Hand hand,int balance) {
		super(hand);
    this.balance = balance;
		
	}
  public int getBalance(){
    return this.balance;
  }
  public void placeBet(int bet){
    balance -= bet;
  }
  public void receiveWinning(int winning){
    balance += winning;
  }
  @Override
  public boolean makeMove(){
    // decide whether this player can and want to make a move
    // and then recieve input from user and print output
    if(super.getHand().getScores() >= 21){
      return false;
    }
    System.out.println("Draw card? [y/n]");
    String ans = scanner.nextLine();
    return ans.equals("y");

  }

}
class Dealer extends Player{
  // play on a standard strategy: draw when score <= 16, stop when score >= 17
  private int targetScore = 17;
  public Dealer(Hand hand) {
      super(hand);
      
  }

  @Override
  public boolean makeMove(){
    if(super.getHand().getScores() >= targetScore){
      return false;
    }
    return true;
  }
}
class GameController{
  // coordinate the whole process and control the flow 
  private Dealer dealer;
  private UserPlayer userPlayer;
  private Deck deck;
  Scanner input = new Scanner(System.in);
  // 从外部传入（DI）
  public GameController(Player dealer, Player user, Deck deck){
    this.dealer = dealer;
    this.userPlayer = user;
    this.deck = deck;
  }
  private int getBetUser(){
    System.out.print("enger a bet amount: ");
    return input.nextInt();
  }

  private void dealInitialCards(){
    // send two cards for each player
    for(int i = 0; i < 2; i++){
      dealer.addCard(deck.draw());
      userPlayer.addCard(deck.draw());
    }
    System.out.println("Player hand:");
    userPlayer.getHand().print();
    Card dealerCard = dealer.getHand().getCards().get(0);
    System.out.println("Dealer's first card:");
    dealerCard.print();
}
  private void resetRound(){
    userPlayer.clearHand();
    dealer.clearHand();
    System.out.println("Player balance: " + userPlayer.getBalance());
  }

  public void playOneRound(){
    deck.shuffle();

    if (userPlayer.getBalance() <= 0) {
        System.out.println("userPlayer has no more money =)");
        return;
    }

    int userBet = getBetUser();
    userPlayer.placeBet(userBet);
    dealInitialCards();

    while (userPlayer.makeMove()) {
        userPlayer.addCard(deck.draw());
    }

    if (userPlayer.getHand().getScore() > 21) {
        System.out.println("userPlayer loses");
        resetRound();
        return;
    }

   
    while (dealer.makeMove()) {
        dealer.addCard(deck.draw());
    }

    int dealerScore = dealer.getHand().getScore();
    int userPlayerScore = userPlayer.getHand().getScore();
    // determine winner
    if (dealerScore > 21 || userPlayerScore > dealerScore) {
        System.out.println("userPlayer wins");
        userPlayer.receiveWinning(userBet * 2);
    } else if (dealerScore > userPlayerScore) {
        System.out.println("userPlayer loses");
    } else {
        System.out.println("Draw");
        userPlayer.receiveWinning(userBet);
    }

    resetRound();
  }

}







class Solution {
  public static void main(String[] args) {
    Player user = new UserPlayer(new Hand(), 5000);
    Player dealer = new Dealer(new Hand());
    Deck deck = new Deck();
    GameController game = new GameController(dealer, user, deck);
    while ( user.getBalance() > 0){
      game.playOneRound();
    }
    
  }
}
