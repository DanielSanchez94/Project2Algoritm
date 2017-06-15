package pathagon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import framework.AdversarySearchProblem;
import framework.AdversarySearchState;

public class ProblemPathagon implements AdversarySearchProblem<StatePathagon> {

	public ProblemPathagon(){}


	public StatePathagon initialState() {
		int[][] newBoard = new int[7][7];
		for(int i=0; i<newBoard.length; i++){
			Arrays.fill(newBoard, 0);
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

		int[][] auxBoard = new int[7][7];
		for (int i=0; i<auxBoard.length; i++){
			for (int j=0; j<auxBoard.length; j++){
				auxBoard[i][j] = state.board()[i][j];
			}
		}
		StatePathagon res = null;
		int currentTurn = state.turn();
		int turnTokens;
		if( currentTurn==1){
			turnTokens = state.tokensUser();
		}else{
			turnTokens = state.tokensCPU();
		}

		if ((auxBoard[row][column]==0) && (turnTokens>0)){
			auxBoard[row][column] = currentTurn;
			if (currentTurn == 1){
				if(state.tokensCPU()>0)
					res = new StatePathagon(!state.isMax(),turnTokens-1,state.tokensCPU(),2,auxBoard,"Insert");
				else
					res = new StatePathagon(state.isMax(),turnTokens-1, state.tokensCPU(),1,auxBoard,"Insert");
			}else{
				if(state.tokensUser()>0)
					res = new StatePathagon(!state.isMax(),state.tokensUser(),turnTokens-1,1,auxBoard,"Insert");
				else
					res = new StatePathagon(state.isMax(),state.tokensUser(),turnTokens-1,2,auxBoard,"Insert");
			}
		}else{
			System.out.println("Casillero ocupado");
		}

		return res;

	}

	//retorna true si el casillero esta ocupado
	public boolean occupied(int i, int j,int[][] board){
		if(board[i][j]!=0){
			return true;
		}
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
		int turn = state.turn();
		StatePathagon newstate;
		if((turn==1 && state.tokensUser()>0) || (turn==2 && state.tokensCPU()>0)){
			for(int i=0; i<state.board().length; i++){
				for(int j=0; j<state.board().length; j++){
					if(!occupied(i,j,state.board())){
						newstate = insertToken(state,i,j);
						successors.add(newstate);
					}
				}
			}
		}
		return successors;
	}


	public boolean end(StatePathagon state) {
		// TODO Auto-generated method stub
		return false;
	}

	// Se asume que el usuario juega de abajo hacia arriba y cpu juega de izquierda a
	// derecha.
	public int value(StatePathagon state) {
		if(state.turn == 1){
			
		}
		else{

		}
	}


}
