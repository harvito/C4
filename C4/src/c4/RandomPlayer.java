package c4;

import java.util.concurrent.ThreadLocalRandom;

public class RandomPlayer extends Player {

	public RandomPlayer(char colour) {
		super(colour);
	}

	@Override
	void play(Board board) {
		int col = ThreadLocalRandom.current().nextInt(1, 8);
		try {
			board.insert(colour, col);
			System.out.println("Computer '"+colour+"' played on column "+col);
		} catch (Exception e) {
			this.play(board); //try again
		}
		
	}

}
