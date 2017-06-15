package pathagon;

import java.util.Arrays;

import framework.AdversarySearchState;

public class StatePathagon implements AdversarySearchState {
	private int tokensUser;// Player available tokens
	private int tokensCPU; //CPU available tokens
	private boolean max; //true==isMaxNode false==isMinNode
	private int turn; // turn== 1 is turn of player // turn==2 is turn of CPU
	private int[][] board; //Representations of the board

	public StatePathagon(boolean maxX, int tokensX, int tokensY, int turnX, int[][] boardX){

		max = maxX;
		tokensUser = tokensX;
		tokensCPU = tokensY;
		turn = turnX;
		board = boardX;

	}

	public int getTokensUser() {
		return tokensUser;
	}

	public void setTokensUser(int tokensUser) {
		this.tokensUser = tokensUser;
	}

	public int getTokensCPU() {
		return tokensCPU;
	}

	public void setTokensCPU(int tokensCPU) {
		this.tokensCPU = tokensCPU;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public void setMax(boolean max) {
		this.max = max;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int[][] board(){

		return board;

	}

	public boolean isMax(){

		return max;

	}

	public int getTurn(){

		return turn;

	}

	public int tokensUser(){

		return tokensUser;

	}

	public int tokensCPU(){

		return tokensCPU;

	}

	public boolean equals(StatePathagon other){

		boolean res = true;
		if (this.max != other.max){ res = false;}
		if (this.turn != other.turn){ res = false;}
		if (this.tokensUser != other.tokensUser){ res = false;}
		if (this.tokensCPU != other.tokensCPU){ res = false;}
		for(int i=0; i<this.board.length; i++){
			if(!Arrays.equals(this.board[i],other.board[i])){ res = false;}
		}

		return res;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object ruleApplied() {
		// TODO Auto-generated method stub
		return null;
	}
}
