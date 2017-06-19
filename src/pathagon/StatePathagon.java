package pathagon;

import java.util.Arrays;

import framework.AdversarySearchState;
import utilities.*;

public class StatePathagon implements AdversarySearchState {
	private int tokensUser;// Player available tokens
	private int tokensCPU; //CPU available tokens
	private boolean max; //true==isMaxNode false==isMinNode
	private int turn; // turn== 1 is turn of player // turn==2 is turn of CPU
	private Token[][] board; //Representations of the board
	private String ruleApplied;

	public StatePathagon(boolean maxX, int tokensX, int tokensY, int turnX, Token[][] boardX, String mov){
		max = maxX;
		tokensUser = tokensX;
		tokensCPU = tokensY;
		turn = turnX;
		board = boardX;
		ruleApplied = mov;
	}

	public Token[][] getBoard(){
		return board;
	}
	//Set method
	public void setBoard(Token[][] boardX) {
		this.board = boardX;
	}

	public boolean isMax(){
		return max;
	}

	//Set method
	public void setMax(boolean maxX) {
		this.max = maxX;
	}

	public int getTurn(){
		return turn;
	}

	//Set method
	public void setTurn(int turnX) {
		this.turn = turnX;
	}

	public int getTokensUser(){
		return tokensUser;
	}

	//Set method
	public void setTokensUser(int tokensX) {
		this.tokensUser = tokensX;
	}

	public int getTokensCPU(){
		return tokensCPU;
	}

	//Set method
	public void setTokensCPU(int tokensX) {
		this.tokensCPU = tokensX;
	}

	public void printBoard(){
		System.out.println("TABLERO \n");
		for (int i=0; i<this.board.length; i++){
			for (int j=0; j<this.board.length; j++){
				System.out.print(" | "+this.board[i][j].getId());
			}
			System.out.print(" | \n");
		}
		System.out.println("\n");
	}

	public void unmark(){
		for (int i=0; i<this.board.length; i++){
			for (int j=0; j<this.board.length; j++){
				this.board[i][j].setMark(false);
			}
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
