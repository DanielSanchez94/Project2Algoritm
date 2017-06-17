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
		//start the cpu
		// Si comienza la cpu deberia ser false (nodo min) o no?
		StatePathagon init = new StatePathagon(true,14,14,1,newBoard,null);

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
		/*Token[][] auxBoard = new Token[7][7];
		for (int i=0; i<auxBoard.length; i++){
			for (int j=0; j<auxBoard.length; j++){
				auxBoard[i][j] = state.getBoard()[i][j];
			}
		}*/
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
			state.getBoard()[row][column] = newToken;
			//si no queda ninguna ficha encerrada, es el turno de player y le quedan fichas al jugador 
			if(locked(state,row,column).getCoordenateX()==-1){
				if (currentTurn == 1){
					if(state.getTokensCPU()>0)
						res = new StatePathagon(!state.isMax(),turnTokens-1,state.getTokensCPU(),2,state.getBoard(),"Insert");
					else
						res = new StatePathagon(state.isMax(),turnTokens-1, state.getTokensCPU(),1,state.getBoard(),"Insert");
				}else{
					if(state.getTokensUser()>0)
						res = new StatePathagon(!state.isMax(),state.getTokensUser(),turnTokens-1,1,state.getBoard(),"Insert");
					else
						res = new StatePathagon(state.isMax(),state.getTokensUser(),turnTokens-1,2,state.getBoard(),"Insert");
				}
			}else{
				//si encierra a alguna ficha
				int a=locked(state,row,column).getCoordenateX();
				int b=locked(state,row,column).getCoordenateY();
				state.getBoard()[a][b].setId(0);//saco la ficha y
				turnTokens++;//agrego una ficha al oponenete del current
				if (currentTurn == 1){
					if(state.getTokensCPU()>0)
						res = new StatePathagon(!state.isMax(),turnTokens-1,state.getTokensCPU(),2,state.getBoard(),"Insert");
					else
						res = new StatePathagon(state.isMax(),turnTokens-1, state.getTokensCPU(),1,state.getBoard(),"Insert");
				}else
					if(state.getTokensUser()>0)
						res = new StatePathagon(!state.isMax(),state.getTokensUser(),turnTokens-1,1,state.getBoard(),"Insert");
					else
						res = new StatePathagon(state.isMax(),state.getTokensUser(),turnTokens-1,2,state.getBoard(),"Insert");
				
			}
		}else{
			if(occupied(row,column,state.getBoard())){
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

//este metodo devuelve los indices del par encerrado en caso de que encierre a alguno
	public Token locked(StatePathagon state, int i, int j){
		Token tocklock = new Token(1);
		//si es alguna de las esquinas, entonces no puede ser encerrado
		if((i==0&&j==0)||(i==0&&j==6)||(i==6&&j==0)||(i==6&&j==6)){
			tocklock.setCoordenateX(-1);
			tocklock.setCoordenateY(-1);
		}else{
			if(state.getTurn()==1){
				tocklock.setId(1);
				int p=2;
				int q=1;
			}
			else{
				tocklock.setId(2);
				int p=2;
				int q=1;
				if((j>1)&&(j<5)&&(i>1)&&(i<5)){
					if((state.getBoard()[i+1][j].getId()==q)&&(state.getBoard()[i+2][j].getId()==p)){
						tocklock.setCoordenateX(i+1);
						tocklock.setCoordenateY(j);
					}
					
					if((state.getBoard()[i-1][j].getId()==q)&&(state.getBoard()[i-2][j].getId()==p)){
						tocklock.setCoordenateX(i-1);
						tocklock.setCoordenateY(j);	
					}
					
					if((state.getBoard()[i][j+1].getId()==q)&&(state.getBoard()[i][j+2].getId()==p)){
						tocklock.setCoordenateX(i);
						tocklock.setCoordenateY(j+1);
					}
					
					if((state.getBoard()[i][j-1].getId()==q)&&(state.getBoard()[i][j-2].getId()==p)){
						tocklock.setCoordenateX(i);
						tocklock.setCoordenateY(j-1);	
					}
					
				if(j==0){
					if((state.getBoard()[i][j+1].getId()==q)&&(state.getBoard()[i][j+2].getId()==p)){
						tocklock.setCoordenateX(i);
						tocklock.setCoordenateY(j+1);
					}else{
						if((i<5)&&(state.getBoard()[i+1][j].getId()==q)&&(state.getBoard()[i+2][j].getId()==p)){
							tocklock.setCoordenateX(i+1);
							tocklock.setCoordenateY(j);
						}
						
						if((i>1)&&(state.getBoard()[i-1][j].getId()==q)&&(state.getBoard()[i-2][j].getId()==p)){
							tocklock.setCoordenateX(i-1);
							tocklock.setCoordenateY(j);
						}
					}
				}
				
				if(j==6){
					if((state.getBoard()[i][j-1].getId()==q)&&(state.getBoard()[i][j-2].getId()==p)){
						tocklock.setCoordenateX(i);
						tocklock.setCoordenateY(j-1);
						
					}else
						if((i<5)&&(state.getBoard()[i+1][j].getId()==q)&&(state.getBoard()[i+2][j].getId()==p)){
							tocklock.setCoordenateX(i+1);
							tocklock.setCoordenateY(j);
						}
					
						if((i>1)&&(state.getBoard()[i-1][j].getId()==q)&&(state.getBoard()[i-2][j].getId()==p)){
							tocklock.setCoordenateX(i-1);
							tocklock.setCoordenateY(j);
						}
					}
				}
				
				if(i==0){
					if((state.getBoard()[i+1][j].getId()==q)&&(state.getBoard()[i+2][j].getId()==p)){
						tocklock.setCoordenateX(i+1);
						tocklock.setCoordenateY(j);
						
					}else{
						if((j<5)&&(state.getBoard()[i][j+1].getId()==q)&&(state.getBoard()[i][j+2].getId()==p)){
							tocklock.setCoordenateX(i);
							tocklock.setCoordenateY(j+1);
						}
						
						if((j>1)&&(state.getBoard()[i][j-1].getId()==q)&&(state.getBoard()[i][j-2].getId()==p)){
							tocklock.setCoordenateX(i);
							tocklock.setCoordenateY(j-1);
						}
					}
				}
				
				if(i==6){
					if((state.getBoard()[i-1][j].getId()==q)&&(state.getBoard()[i-2][j].getId()==p)){
						tocklock.setCoordenateX(i-1);
						tocklock.setCoordenateY(j);
						
					}else{
						if((j<5)&&(state.getBoard()[i][j+1].getId()==q)&&(state.getBoard()[i][j+2].getId()==p)){
							tocklock.setCoordenateX(i);
							tocklock.setCoordenateY(j+1);
						}
						
						if((j>1)&&(state.getBoard()[i][j-1].getId()==q)&&(state.getBoard()[i][j-2].getId()==p)){
							tocklock.setCoordenateX(i);
							tocklock.setCoordenateY(j-1);
						}
					}
				}
				
				if(j==1){
					if((state.getBoard()[i][j+1].getId()==q)&&(state.getBoard()[i][j+2].getId()==p)){
						tocklock.setCoordenateX(i);
						tocklock.setCoordenateY(j+1);
					}
				}
				
				if(j==5){
					if((state.getBoard()[i][j-1].getId()==q)&&(state.getBoard()[i][j-2].getId()==p)){
						tocklock.setCoordenateX(i);
						tocklock.setCoordenateY(j-1);
					}
				}
				
				if(i==1){
					if((state.getBoard()[i+1][j].getId()==q)&&(state.getBoard()[i+2][j].getId()==p)){
						tocklock.setCoordenateX(i+1);
						tocklock.setCoordenateY(j);
					}
				}
				if(i==5){
					if((state.getBoard()[i-1][j].getId()==q)&&(state.getBoard()[i-2][j].getId()==p)){
						tocklock.setCoordenateX(i-1);
						tocklock.setCoordenateY(j);
					}
				}
				if(i==5){
					if((state.getBoard()[i-1][j].getId()==q)&&(state.getBoard()[i-2][j].getId()==p)){
						tocklock.setCoordenateX(i-1);
						tocklock.setCoordenateY(j);
					}
				}
				
				//comprueba si se encierra solo
				if((j>0)&&(j<6)&&(i>0)&&(i<6)){
					if((state.getBoard()[i-1][j].getId()==q)&&(state.getBoard()[i+1][j].getId()==q)||
					   (state.getBoard()[i][j-1].getId()==q)&&(state.getBoard()[i][j+1].getId()==q)){
						tocklock.setCoordenateX(i);
						tocklock.setCoordenateY(j);
					}
				}if((i==0)||(i==6)){
					if((state.getBoard()[i][j-1].getId()==q)&&(state.getBoard()[i][j+1].getId()==q)){
						tocklock.setCoordenateX(i);
						tocklock.setCoordenateY(j);
					}
				if((j==0)||(j==6)){
					if((state.getBoard()[i-1][j].getId()==q)&&(state.getBoard()[i+1][j].getId()==q)){
						tocklock.setCoordenateX(i);
						tocklock.setCoordenateY(j);
					}
				}
				}
			}
		}
		return tocklock;
	}
	
	public List<StatePathagon> getSuccessors(StatePathagon state) {
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
		int auxUser = 0;
		int auxCPU = 0;
		int resultUser = 0;
		int resultCPU = 0;

		for(int i=0; i<state.getBoard().length; i++){
			for(int j=0; j<state.getBoard().length; j++){
				if(state.getBoard()[i][j].getId()==1){
					Token init = state.getBoard()[i][j];
					auxUser = dfs_modified(init,state);
				}
				if(resultUser < auxUser)
					resultUser = auxUser;
			}
		}


		for(int i=0; i<state.getBoard().length; i++){
			for(int j=0; j<state.getBoard().length; j++){
				if(state.getBoard()[i][j].getId() == 2){
					Token init = state.getBoard()[i][j];
					auxCPU = dfs_modified(init,state);
				}
				if(resultCPU < auxCPU)
					resultCPU = auxCPU;
			}
		}

		return resultCPU-resultUser;
	}

	public List<Token> adjacent(int i, int j, Token[][] board){
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
		if(adj.size()==0){
			return null;
		}
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
			List<Token> adj = adjacent(u.getCoordenateX(), u.getCoordenateY(), state.getBoard());
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
