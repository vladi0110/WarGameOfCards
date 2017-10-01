package wargame;

public class Card {
	private final String kind;
	private final String suit;
	private final int power;
	
	
	public Card(String kind, String suit, int power) {
		this.kind = kind;
		this.suit = suit;
		this.power = power;
	}
	
	
	// < 0 - this card < another
	// == 0 = this card == another
	// > 0 - this card > another
	int compareTo(Card anotherCard) {
		if (anotherCard == null) {
			System.out.println("Card cannot be null");
			return 1;
		}
		return this.power - anotherCard.power;
	}


	@Override
	public String toString() {
		return "Card [kind=" + kind + ", suit=" + suit + "] \n";
	}
}
