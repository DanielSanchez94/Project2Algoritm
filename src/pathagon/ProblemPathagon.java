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
			for(int j=0; j<newBoard.length; j++){
				Token newToken = new Token(0);
				newBoard[i][j] = newToken;
			}
		}
		StatePathagon init = new StatePathagon(false,14,14,1,newBoard,null);

		return init;
	}

	public int minValue(){

		return -10000;

	}

	public int maxValue(){

		return 10000;

	}

	// Podriamos controlar despues de insertar si encerramos una ficha del oponente o no
	public StatePathagon insertToken(StatePathagon state, int row, int column){
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
		if ((!occupied(row,column,state.getBoard())) && (turnTokens>0)){



			Token newToken = new Token(currentTurn);
			newToken.setCoordenateX(column);
			newToken.setCoordenateY(row);
			auxBoard[row][column] = newToken;
			Token aux = locked(auxBoard, currentTurn, row, column);

			if(aux != null){
				auxBoard[aux.getCoordenateY()][aux.getCoordenateX()] = new Token(0);
				if (currentTurn == 1){
					if(state.getTokensCPU()>0)
						res = new StatePathagon(!state.isMax(),turnTokens-1,state.getTokensCPU(),2,auxBoard,"Insert and delete");
					else
						res = new StatePathagon(state.isMax(),turnTokens-1, state.getTokensCPU(),1,auxBoard,"Insert and delete");
				}else{
					if(state.getTokensUser()>0)
						res = new StatePathagon(!state.isMax(),state.getTokensUser(),turnTokens-1,1,auxBoard,"Insert and delete");
					else
						res = new StatePathagon(state.isMax(),state.getTokensUser(),turnTokens-1,2,auxBoard,"Insert and delete");
				}
			}
			else{
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
			}




		}else{
			if(occupied(row,column,auxBoard)){
				System.out.println("Casillero ocupado");
			}
		}
		return res;

	}

	//retorna true si el casillero esta ocupado
	public boolean occupied(int i, int j,Token[][] board){
		if(board[i][j].getId()!=0)
			return true;
		return false;
	}

	//devuelve true si inserto una ficha y me encierro solo
	public boolean Tabclosed(Token[][] board, int i, int j){

			 if((j>0)&&(j<6)&&(i>0)&&(i<6)){
					 if((board[i-1][j].getId()==2)&&(board[i+1][j].getId()==1)||
							(board[i][j-1].getId()==2)&&(board[i][j+1].getId()==1)){
							 return true;
					 }
			 }

			 if((i==0)||(i==6)){
					 if((board[i][j-1].getId()==2)&&(board[i][j+1].getId()==1)){
							 return true;
					 }
			 }

			 if((j==0)||(j==6)){
					 if((board[i-1][j].getId()==2)&&(board[i+1][j].getId()==1)){
							 return true;
					 }
			 }
	 return false;
	 }


  public Token locked(Token[][] board, int turn, int i, int j){
		Token tokenLock = null;
		int p;
		int q;
		if(turn == 1){
			p=1;
			q=2;
		}
		else{
			p=2;
			q=1;
		}
		if(Tabclosed(board,i,j)){
		  tokenLock = new Token(turn,i,j);
		}
		if(j>1){
		  if((board[i][j-1].getId()==q)&&(board[i][j-2].getId()==p)){
			  tokenLock = new Token(turn,i,j-1);
			}
		}
		if(j<5){
			if((board[i][j+1].getId()==q)&&(board[i][j+2].getId()==p)){
				tokenLock = new Token(turn,i,j+1);
			}
		}
		if(i>1){
			if((board[i-1][j].getId()==q)&&(board[i-2][j].getId()==p)){
				tokenLock = new Token(turn,i-1,j);
			}
		}
		if(i<5){
			if((board[i+1][j].getId()==q)&&(board[i+2][j].getId()==p)){
				tokenLock = new Token(turn,i+1,j);
			}
		}
		return tokenLock;
	 }

	public List<StatePathagon> getSuccessors(StatePathagon state) {
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
		int aux=0;
		if(state.getTokensUser() == 0 && state.getTokensCPU() == 0)
			return true;

		if (state.getTurn()==1){
			for(int i=0; i<state.getBoard().length; i++){
				if(state.getBoard()[0][i].getId()==1){
					Token init = state.getBoard()[0][i];
					//System.out.println("ID"+init.getId()+" Fila: "+init.getCoordenateY()+" colum "+init.getCoordenateX());
					aux = dfs_modified(init,state);
				}
			}
		}
		else{
			for(int j=0; j<state.getBoard().length; j++){
				if(state.getBoard()[0][j].getId()==2){
					Token init = state.getBoard()[0][j];
					aux = dfs_modified(init,state);
				}
			}
		}
		return aux==7;
	}

	// Se asume que el usuario juega de abajo hacia arriba y cpu juega de izquierda a
	// derecha.
	public int value(StatePathagon state) {
		int aux = 0;
		int result = 0;
		if(state.getTurn()==1){
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
		return 7 - result;
	}

	public List<Token> adjacent(int j, int i, Token[][] board){
		List<Token> adjacentList = new LinkedList<Token>();
		if(i>0 && i<6 && j>0 && j<6){
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
		}
		if(i==0 && j==0){
			if (board[i][j].getId() == board[i][j+1].getId()){
				adjacentList.add(board[i][j+1]);
			}
			if (board[i][j].getId() == board[i+1][j].getId()){
				adjacentList.add(board[i+1][j]);
			}
		}
		if(i==6 && j==0){
			if (board[i][j].getId() == board[i-1][j].getId()){
				adjacentList.add(board[i-1][j]);
			}
			if (board[i][j].getId() == board[i][j+1].getId()){
				adjacentList.add(board[i][j+1]);
			}
		}
		if(i==0 && j==6){
			if (board[i][j].getId() == board[i+1][j].getId()){
				adjacentList.add(board[i+1][j]);
			}
			if (board[i][j].getId() == board[i][j-1].getId()){
				adjacentList.add(board[i][j-1]);
			}
		}
		if(i==6 && j==6){
			if (board[i][j].getId() == board[i-1][j].getId()){
				adjacentList.add(board[i-1][j]);
			}
			if (board[i][j].getId() == board[i][j-1].getId()){
				adjacentList.add(board[i][j-1]);
			}
		}
		if(i==0 && j>0 && j<6){
			if (board[i][j].getId() == board[i+1][j].getId()){
				adjacentList.add(board[i+1][j]);
			}
			if (board[i][j].getId() == board[i][j-1].getId()){
				adjacentList.add(board[i][j-1]);
			}
			if (board[i][j].getId() == board[i][j+1].getId()){
				adjacentList.add(board[i][j+1]);
			}
		}
		if(i==6 && j>0 && j<6){
			if (board[i][j].getId() == board[i-1][j].getId()){
				adjacentList.add(board[i-1][j]);
			}
			if (board[i][j].getId() == board[i][j-1].getId()){
				adjacentList.add(board[i][j-1]);
			}
			if (board[i][j].getId() == board[i][j+1].getId()){
				adjacentList.add(board[i][j+1]);
			}
		}
		if(i>0 && i<6 && j==0){
			if (board[i][j].getId() == board[i+1][j].getId()){
				adjacentList.add(board[i+1][j]);
			}
			if (board[i][j].getId() == board[i-1][j].getId()){
				adjacentList.add(board[i-1][j]);
			}
			if (board[i][j].getId() == board[i][j+1].getId()){
				adjacentList.add(board[i][j+1]);
			}
		}
		if(i>0 && i<6 && j==6){
			if (board[i][j].getId() == board[i+1][j].getId()){
				adjacentList.add(board[i+1][j]);
			}
			if (board[i][j].getId() == board[i-1][j].getId()){
				adjacentList.add(board[i-1][j]);
			}
			if (board[i][j].getId() == board[i][j-1].getId()){
				adjacentList.add(board[i][j-1]);
			}
		}
		return adjacentList;
	}

	public Token getUnvisitedAdj(List<Token> adj){
		int i=0;
		while(i<adj.size()){
			if(adj.get(i).getMark() == true)
				i++;
			else
				break;
		}
		if(i<adj.size())
			return adj.get(i);
		else
			return null;
	}

	public int dfs_modified(Token init, StatePathagon state){
		state.unmark();
		int dist = 0;
		Stack<Token> s = new Stack<Token>();
		s.push(init);
		init.setMark(true);
		while(!s.empty()){
			Token u = s.peek();
			List<Token> adj = adjacent(u.getCoordenateX(), u.getCoordenateY(), state.getBoard());
			Token w = getUnvisitedAdj(adj);
			if(w!=null){
				if(state.getTurn()==1)
					dist = Math.max(dist, Math.abs(w.getCoordenateY()-init.getCoordenateY()));
				else
					dist = Math.max(dist, Math.abs(w.getCoordenateX()-init.getCoordenateX()));
				w.setMark(true);
				s.push(w);
			}
			else{
				s.pop();
			}
		}
		return dist+1;
	}


}
