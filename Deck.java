package wargame;

import java.util.Arrays;

public class Deck {
	private static final int NUMBER_OF_SHUFFLES = 10000;
	public static final int NUMBER_OF_CARDS = 52;
	private Card[] cards;
	private int currentCard;
	private int currentFreeIndex;

	public Deck() {
		this.cards = new Card[NUMBER_OF_CARDS];

		generateCards();

		shuffleCards();
	}

	public Deck(Card[] cards) {
		if (cards != null) {
			this.cards = cards;
			this.currentCard = this.cards.length - 1;
		}
	}

	public Deck(int capacity) {
		if (capacity > 0) {
			this.cards = new Card[capacity];
		} else {
			this.cards = new Card[NUMBER_OF_CARDS];
		}
		this.currentFreeIndex = 0;
	}

	public Deck getEvenHalf() {
		Card[] cards = new Card[NUMBER_OF_CARDS / 2];
		int index = 0;

		for (int cardIndex = 0; cardIndex < NUMBER_OF_CARDS; cardIndex += 2) {
			cards[index++] = this.cards[cardIndex];
		}

		return new Deck(cards);
	}

	public Deck getOddHalf() {
		Card[] cards = new Card[NUMBER_OF_CARDS / 2];
		int index = 0;

		for (int cardIndex = 1; cardIndex < NUMBER_OF_CARDS; cardIndex += 2) {
			cards[index++] = this.cards[cardIndex];
		}

		return new Deck(cards);
	}

	private void shuffleCards() {
		// shuffle
		for (int numberOfShuffles = 1; numberOfShuffles <= NUMBER_OF_SHUFFLES; numberOfShuffles++) {
			int randomCard1 = (int) (Math.random() * NUMBER_OF_CARDS);
			int randomCard2 = (int) (Math.random() * NUMBER_OF_CARDS);

			Card temp = this.cards[randomCard1];
			this.cards[randomCard1] = this.cards[randomCard2];
			this.cards[randomCard2] = temp;
		}
	}

	private void generateCards() {
		// generate cards
		final String[] suits = { "spades", "clubs", "diamonds", "hearts" };
		final String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

		int currentIndex = 0;

		// for-each
		for (String suit : suits) {
			for (int rankIndex = 0; rankIndex < ranks.length; rankIndex++) {
				Card card = new Card(ranks[rankIndex], suit, rankIndex);
				this.cards[currentIndex++] = card;
			}
		}
	}

	@Override
	public String toString() {
		return "Deck [cards=" + Arrays.toString(cards) + "]";
	}

	public boolean isDeckEmpty() {
		return this.currentCard < 0;
	}

	public Card getTopCard() {
		if (currentCard >= 0) {
			Card card = this.cards[this.currentCard];
			this.cards[this.currentCard] = null;
			currentCard--;
			return card;
		}
		return null;
	}

	public void addCard(Card card) {
		if (currentFreeIndex < this.cards.length) {
			this.cards[this.currentFreeIndex++] = card;
		}
	}
	
	public int getAddedCards() {
		return this.currentFreeIndex;
	}
}
