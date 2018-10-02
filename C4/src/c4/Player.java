package c4;

public abstract class Player {
	char colour;
	
	public Player(char colour) {
		this.colour = colour;
	}
	
	abstract void play(Board board);
}