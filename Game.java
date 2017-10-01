package wargame;

public class Game {
	private static final int CARDS_TO_DRAW_IN_CASE_OF_WAR = 3;

	public static void main(String[] args) throws InterruptedException {
		Deck deck = new Deck();
		
		Player ivan = new Player(deck.getEvenHalf(), "Ivan Ivanov");
		Player petar = new Player(deck.getOddHalf(), "Petar Petrov");
		
		while (!ivan.hasPlayerFinished()) {
			Card firstPlayerCard = ivan.playCard();
			Card secondPlayerCard = petar.playCard();
			
			System.out.print("Ivan : " + firstPlayerCard);
			System.out.print("Petar : " + secondPlayerCard);
			
			if (firstPlayerCard.compareTo(secondPlayerCard) > 0) {
				ivan.gatherCard(firstPlayerCard);
				ivan.gatherCard(secondPlayerCard);
				System.out.println("Ivan takes the cards");
			}
			
			if (firstPlayerCard.compareTo(secondPlayerCard) < 0) {
				petar.gatherCard(firstPlayerCard);
				petar.gatherCard(secondPlayerCard);
				System.out.println("Petar takes the cards");
			}
			
			if (firstPlayerCard.compareTo(secondPlayerCard) == 0) {
				System.out.println("Now we have a war");
				Card[] drawnCards = new Card[Deck.NUMBER_OF_CARDS];
				drawnCards[0] = firstPlayerCard;
				drawnCards[1] = secondPlayerCard;
				int index = 2;
				
				while ((firstPlayerCard.compareTo(secondPlayerCard) == 0) && (!ivan.hasPlayerFinished())) {
					for (int count=1; count <= CARDS_TO_DRAW_IN_CASE_OF_WAR && (!ivan.hasPlayerFinished()); count++) {
						Card cardOfIvan = ivan.playCard();
						Card cardOfPetar = petar.playCard();
						
						drawnCards[index++] = cardOfIvan;
						drawnCards[index++] = cardOfPetar;
						
						System.out.println("Now " + ivan.getName() + " gives : " + cardOfIvan);
						System.out.println("Now "+ petar.getName() + " gives : " + cardOfPetar);
						 
						Thread.sleep(1000);
					}
					firstPlayerCard = drawnCards[index-2];
					secondPlayerCard = drawnCards[index-1];
				}
				
				if (firstPlayerCard.compareTo(secondPlayerCard) > 0) {
					System.out.println("After the war " + ivan.getName()+" takes the cards.");
					for (Card card : drawnCards) {
						ivan.gatherCard(card);
					}
				}
				
				if (firstPlayerCard.compareTo(secondPlayerCard) < 0) {
					System.out.println("After the war " + petar.getName()+ " takes the cards.");
					for (Card card : drawnCards) {
						petar.gatherCard(card);
					}
				}
			}
			
			// wait 2 sec
			Thread.sleep(200);
		}
		
		System.out.println("Final score - Ivan: " + ivan.takenCards() + " Petar: " + petar.takenCards());
		System.out.println("Winner is : " + ((ivan.takenCards() > petar.takenCards()) ? "Ivan" : "Petar"));
	}
}

