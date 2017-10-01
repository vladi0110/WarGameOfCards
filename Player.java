package wargame;

public class Player {
	private Deck deck;
	private Deck wonDeck;
	private String name;

	public Player(Deck deck, String name) {
		this.deck = deck;
		this.name = name;
		this.wonDeck = new Deck(Deck.NUMBER_OF_CARDS); // empty deck
	}

	public Card playCard() {
		return deck.getTopCard();
	}

	public String getName() {
		return name;
	}

	public boolean hasPlayerFinished() {
		return this.deck.isDeckEmpty();
	}

	public void gatherCard(Card card) {
		if (card != null) {
			this.wonDeck.addCard(card);
		}
	}
	
	public int takenCards() {
		return wonDeck.getAddedCards();
	}

}
