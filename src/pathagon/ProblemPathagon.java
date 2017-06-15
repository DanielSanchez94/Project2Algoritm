package pathagon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import framework.AdversarySearchProblem;
import framework.AdversarySearchState;

public class ProblemPathagon implements AdversarySearchProblem {

	public ProblemPathagon(){}

	@Override
	public AdversarySearchState initialState() {
		int[][] newBoard = new int[7][7];
		for(int i=0; i<newBoard.length; i++){
			Arrays.fill(newBoard, 0);
		}
		//start the cpu
		StatePathagon init = new StatePathagon(true,14,14,2,newBoard);
		
		return init;
	}

	public int minValue(){

		return -10000;

	}

	public int maxValue(){

		return 10000;

	}

	public StatePathagon insertToken(StatePathagon state, int column, int row){
		
		int[][] auxBoard = new int[7][7];
		for (int i=0; i<auxBoard.length; i++){
			for (int j=0; j<auxBoard.length; j++){
				auxBoard[i][j] = state.board()[i][j];
			}
		}
		StatePathagon res = null;
		int currentTurn = state.getTurn();
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
					res = new StatePathagon(!state.isMax(),turnTokens-1,state.tokensCPU(),2,auxBoard);
				else
					res = new StatePathagon(state.isMax(),turnTokens-1, state.tokensCPU(),1,auxBoard);
			}else{
				if(state.tokensUser()>0)
					res = new StatePathagon(!state.isMax(),state.tokensUser(),turnTokens-1,1,auxBoard);
				else
					res = new StatePathagon(state.isMax(),state.tokensUser(),turnTokens-1,2,auxBoard);
			}
		}else{
			System.out.println("Casillero ocupado");
		}

		return res;

	}

	//retorna true si el casillero esta ocupado
	public boolean occupied(int i, int j,int[][] table){
		if(table[i][j]!=0){
			return true;
		}
		return false;
	}
	
	//este metodo devuelve true si el player me encerro una pieza
	public boolean locked(AdversarySearchState state){		
		return true;
	}
	
	@Override
	public List getSuccessors(AdversarySearchState state) {
		//casteo
		StatePathagon st = (StatePathagon) state;
		//create list of states
		List<StatePathagon> successors = new LinkedList<StatePathagon>();
		//create newTokensCPU and newTokenUser
		int newTokensCPU = st.getTokensCPU();
		int newTokensUser = st.getTokensUser();
		//I update max status in true 
		boolean newMax = true;
		//changes turn
		int newTurn= 2;
		int i=0;int j =0;
		//mientras no recorra todo el tablero,y que tenga fichas, que cicle
		while((i<st.board().length && (j<st.board().length))){
			if(!occupied(i,j,st.board())&&(newTokensCPU!=0)){
				StatePathagon newstate = new StatePathagon(newMax, newTokensUser, newTokensCPU, newTurn, st.board());
				//si el casillero esta libre, entonces debo cargar el arreglo retornado con esa posibilidad
				newstate.board()[i][j]=2;
				successors.add(newstate);
			}
		}
		return successors;
			
	}

	@Override
	public boolean end(AdversarySearchState state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int value(AdversarySearchState state) {
		// TODO Auto-generated method stub
		return 0;
	}


}