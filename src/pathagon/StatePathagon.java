package pathagon;

import java.util.Arrays;

import framework.AdversarySearchState;

public class StatePathagon implements AdversarySearchState {
	private int tokensUser;// Player available tokens
	private int tokensCPU; //CPU available tokens
	private boolean max; //true==isMaxNode false==isMinNode
	private int turn; // turn== 1 is turn of player // turn==2 is turn of CPU
	private int[][] board; //Representations of the board
	private String ruleApplied;

	public StatePathagon(boolean maxX, int tokensX, int tokensY, int turnX, int[][] boardX, String mov){
		max = maxX;
		tokensUser = tokensX;
		tokensCPU = tokensY;
		turn = turnX;
		board = boardX;
		ruleApplied = mov;
	}

	public int[][] board(){
		return board;
	}
	//Set method
	public void board(int[][] boardX) {
		this.board = boardX;
	}

	public boolean isMax(){
		return max;
	}

	//Set method
	public void max(boolean maxX) {
		this.max = maxX;
	}

	public int turn(){
		return turn;
	}

	//Set method
	public void turn(int turnX) {
		this.turn = turnX;
	}

	public int tokensUser(){
		return tokensUser;
	}

	//Set method
	public void tokensUser(int tokensX) {
		this.tokensUser = tokensX;
	}

	public int tokensCPU(){
		return tokensCPU;
	}

	//Set method
	public void tokensCPU(int tokensX) {
		this.tokensCPU = tokensX;
	}

	public void printBoard(){
		System.out.println("Tablero \n");
		for (int i=0; i<this.board.length; i++){
			for (int j=0; j<this.board.length; j++){
				System.out.print(" | "+this.board[i][j]);
			}
			System.out.print(" | \n");
		}
	}

	@Override
	public boolean equals(AdversarySearchState other) {
		StatePathagon state = (StatePathagon) other;
		boolean res = true;
		if (this.max != state.max)
			res = false;
		if (this.turn != state.turn)
			res = false;
		if (this.tokensUser != state.tokensUser)
			res = false;
		if (this.tokensCPU != state.tokensCPU)
			res = false;
		for(int i=0; i<this.board.length; i++){
			if(!Arrays.equals(this.board[i],state.board[i]))
				res = false;
		}
		return res;
	}

	@Override
	public Object ruleApplied() {
		return ruleApplied;
	}
}
