package pathagon;

import java.util.Arrays;

import framework.AdversarySearchState;
import utilities.*;

/**
 * Title:        StatePathagon
 * @author Armas Lucas, Sanchez Daniel
 * @version 0.1
 */
public class StatePathagon implements AdversarySearchState {
	private int tokensUser;// Player available tokens
	private int tokensCPU; //CPU available tokens
	private boolean max; //true==isMaxNode false==isMinNode
	private int turn; // turn== 1 is turn of player // turn==2 is turn of CPU
	private Token[][] board; //Representations of the board
	private String ruleApplied;

	/**
	 * Class builder
	 * @param  boolean   maxX          If true is a max node but is a min node
	 * @param  int       tokensX       Number of tokens available to the user
	 * @param  int       tokensY       [description]
	 * @param  int       turnX         Turn to play, 1 for the user and 2 for the cpu
	 * @param  Token[][] boardX        Game board
	 * @param  String    mov           Last rule applied
	 */
	public StatePathagon(boolean maxX, int tokensX, int tokensY, int turnX, Token[][] boardX, String mov){
		max = maxX;
		tokensUser = tokensX;
		tokensCPU = tokensY;
		turn = turnX;
		board = boardX;
		ruleApplied = mov;
	}

	/**
	 * Return the game board
	 * @return Token[][] board
	 */
	public Token[][] getBoard(){
		return board;
	}

	/**
	 * Set a board to the state
	 * @param Token[][] boardX Game board
	 */
	public void setBoard(Token[][] boardX) {
		this.board = boardX;
	}

	/**
	 * Indicates whether a node is min or max
	 * @return boolean, true if node is max and flase if node is min
	 */
	public boolean isMax(){
		return max;
	}

	/**
	 * Set a node to max or min
	 * @param boolean maxX [description]
	 */
	public void setMax(boolean maxX) {
		this.max = maxX;
	}

	/**
	 * Indicates who is the turn to play
	 * @return int, 1 if it is the user's turn and 2 if it is the CPU's turn
	 */
	public int getTurn(){
		return turn;
	}

	/**
	 * Set the turn to play
	 * @param int turnX next turn to play
	 */
	public void setTurn(int turnX) {
		this.turn = turnX;
	}

	/**
	 * Indicates how many available tokens the user
	 * @return int between 0 and 14
	 */
	public int getTokensUser(){
		return tokensUser;
	}

	/**
	 * Set the number of available tokens of the user
	 * @param int tokensX int between 0 and 14
	 */
	public void setTokensUser(int tokensX) {
		this.tokensUser = tokensX;
	}

	/**
	 * Indicates how many available tokens the CPU
	 * @return int between 0 and 14
	 */
	public int getTokensCPU(){
		return tokensCPU;
	}

	/**
	 * Set the number of available tokens of the CPU
	 * @param int tokensX int between 0 and 14
	 */
	public void setTokensCPU(int tokensX) {
		this.tokensCPU = tokensX;
	}

	/**
	 * Print the game board
	 */
	public void printBoard(){
		System.out.println("\n             TABLERO");
		System.out.println("    | 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		System.out.println("    -----------------------------");
		for (int i=0; i<this.board.length; i++){
			System.out.print(" "+i+" ");
			for (int j=0; j<this.board.length; j++){
				System.out.print(" | "+this.board[i][j].getId());
			}
			System.out.print(" | \n");
		}
		System.out.println("\n");
	}

	/**
	 * Unmark all tokens of the game board
	 */
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
