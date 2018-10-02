package c4;

public class HumanPlayer extends Player {
	
	public HumanPlayer(char colour) {
		super(colour);
	}
	
	@Override
	void play(Board board) {
		System.out.print("Player '"+colour+"' select column >>");
		try {
			int col = Integer.parseInt(C4.br.readLine());
			board.insert(colour, col);
		} catch (Exception e) {
			System.out.println(e.toString());
			this.play(board); //try again
		}
	}
}