package pathagon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import framework.AdversarySearchProblem;
import framework.AdversarySearchState;
import utilities.*;

public class ProblemPathagon implements AdversarySearchProblem<StatePathagon> {

	public ProblemPathagon(){}


	public StatePathagon initialState() {
		Token[][] newBoard = new Token[7][7];
		for(int i=0; i<newBoard.length; i++){
			Token newToken = new Token(0);
			Arrays.fill(newBoard, newToken);
		}
		//start the cpu
		// Si comienza la cpu deberia ser false (nodo min) o no?
		StatePathagon init = new StatePathagon(true,14,14,2,newBoard,null);

		return init;
	}

	public int minValue(){

		return -10000;

	}

	public int maxValue(){

		return 10000;

	}

	// Podriamos controlar despues de insertar si encerramos una ficha del oponente o no
	public StatePathagon insertToken(StatePathagon state, int column, int row){

		Token[][] auxBoard = new Token[7][7];
		for (int i=0; i<auxBoard.length; i++){
			for (int j=0; j<auxBoard.length; j++){
				auxBoard[i][j] = state.getBoard()[i][j];
			}
		}
		StatePathagon res = null;
		int currentTurn = state.getTurn();
		int turnTokens;
		if( currentTurn==1){
			turnTokens = state.getTokensUser();
		}else{
			turnTokens = state.getTokensCPU();
		}

		if ((auxBoard[row][column].getId()==0) && (turnTokens>0)){
			Token newToken = new Token(currentTurn);
			newToken.setCoordenateX(column);
			newToken.setCoordenateY(row);
			auxBoard[row][column] = newToken;
			if (currentTurn == 1){
				if(state.getTokensCPU()>0)
					res = new StatePathagon(!state.isMax(),turnTokens-1,state.getTokensCPU(),2,auxBoard,"Insert");
				else
					res = new StatePathagon(state.isMax(),turnTokens-1, state.getTokensCPU(),1,auxBoard,"Insert");
			}else{
				if(state.getTokensUser()>0)
					res = new StatePathagon(!state.isMax(),state.getTokensUser(),turnTokens-1,1,auxBoard,"Insert");
				else
					res = new StatePathagon(state.isMax(),state.getTokensUser(),turnTokens-1,2,auxBoard,"Insert");
			}
		}else{
			System.out.println("Casillero ocupado");
		}

		return res;

	}

	//retorna true si el casillero esta ocupado
	public boolean occupied(int i, int j,Token[][] board){
		if(board[i][j].getId()!=0)
			return true;
		return false;
	}

	//este metodo devuelve true si el player me encerro una pieza
	public boolean locked(StatePathagon state){
		return false;
	}

	// No recorria todo el tablero primero porque no se incrementaban los indices
	// y ademas con un solo while no se puede recorrer una matriz.
	// Cambiar de turno y de max  a min o viceverza lo hace insert.
	public List getSuccessors(StatePathagon state) {
		/* //casteo
		StatePathagon st = (StatePathagon) state;*/
		//create list of states
		List<StatePathagon> successors = new LinkedList<StatePathagon>();
		int turn = state.getTurn();
		StatePathagon newstate;
		if((turn==1 && state.getTokensUser()>0) || (turn==2 && state.getTokensCPU()>0)){
			for(int i=0; i<state.getBoard().length; i++){
				for(int j=0; j<state.getBoard().length; j++){
					if(!occupied(i,j,state.getBoard())){
						newstate = insertToken(state,i,j);
						successors.add(newstate);
					}
				}
			}
		}
		return successors;
	}


	public boolean end(StatePathagon state) {
		boolean result = false;
		int aux = 0;
		if(state.getTokensUser() == 0 && state.getTokensCPU() == 0)
			return true;
		if (state.getTurn()==1){
			for(int i=0; i<state.getBoard().length; i++){
				if(state.getBoard()[0][i].getId()==1){
					Token init = state.getBoard()[0][i];
					aux = dfs_modified(init,state);
					result = (result || (aux==7));
				}
			}
		}
		else{
			for(int j=0; j<state.getBoard().length; j++){
				if(state.getBoard()[j][0].getId()==1){
					Token init = state.getBoard()[j][0];
					aux = dfs_modified(init,state);
					result = (result || (aux==7));
				}
			}
		}
		return result;
	}

	// Se asume que el usuario juega de abajo hacia arriba y cpu juega de izquierda a
	// derecha.
	public int value(StatePathagon state) {
		int aux = 0;
		int result = 0;
		if (state.getTurn()==1){
			for(int i=0; i<state.getBoard().length; i++){
				for(int j=0; j<state.getBoard().length; j++){
					if(state.getBoard()[i][j].getId()==1){
						Token init = state.getBoard()[i][j];
						aux = dfs_modified(init,state);
					}
					if(result < aux)
						result = aux;
				}
			}
		}
		else{
			for(int i=0; i<state.getBoard().length; i++){
				for(int j=0; j<state.getBoard().length; j++){
					if(state.getBoard()[i][j].getId() == 2){
						Token init = state.getBoard()[i][j];
						aux = dfs_modified(init,state);
					}
					if(result < aux)
						result = aux;
				}
			}
		}
		return result;
	}

	public LinkedList<Token> adjacent(int i, int j, Token[][] board){
		LinkedList<Token> adjacentList = new LinkedList<Token>();
		if (board[i][j].getId() == board[i][j-1].getId()){
			adjacentList.add(board[i][j-1]);
		}
		if (board[i][j].getId() == board[i][j+1].getId()){
			adjacentList.add(board[i][j+1]);
		}
		if (board[i][j].getId() == board[i+1][j].getId()){
			adjacentList.add(board[i+1][j]);
		}
		if (board[i][j].getId() == board[i-1][j].getId()){
			adjacentList.add(board[i-1][j]);
		}
		return adjacentList;
	}

	public Token getUnvisitedAdj(LinkedList<Token> adj){
		int i=0;
		while(i<adj.size()){
			if(adj.get(i).getMark()!= false)
				i++;
		}
		return adj.get(i);
	}

	public int dfs_modified(Token init, StatePathagon state){
		int dist = 0;
		Stack<Token> s = new Stack<Token>();
		s.push(init);
		init.setMark(true);
		while(!s.empty()){
			Token u = s.peek();
			LinkedList<Token> adj = adjacent(u.getCoordenateX(), u.getCoordenateY(), state.getBoard());
			Token w = getUnvisitedAdj(adj);
			if(w!=null){
				if(state.getTurn()==1)
					dist = Math.max(dist, Math.abs(w.getCoordenateX()-init.getCoordenateX()));
				else
					dist = Math.max(dist, Math.abs(w.getCoordenateY()-init.getCoordenateX()));
				w.setMark(true);
				s.push(w);
			}
			else{
				s.pop();
			}
		}
		return dist;
	}


}
