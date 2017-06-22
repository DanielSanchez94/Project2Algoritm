package pathagon;

import utilities.*;
import framework.AdversarySearchEngine;
import java.util.Scanner;

import java.util.List;

/**
 * Title:        appPathagon
 * @author Armas Lucas, Sanchez Daniel
 * @version 0.1
 */
public class AppPathagon {

	/**
	 * Method used to print some aspects of the game
	 * @param StatePathagon state [description]
	 * @pre.    state!=null
   * @post.   some properties of the game are displayed on the screen
	 */
	private static void showGame(StatePathagon state){
		state.printBoard();
		System.out.println("ES EL TURNO DEL JUGADOR: "+state.getTurn()+ "\n");
		System.out.println("USER TIENE: "+state.getTokensUser()+" FICHAS");
		System.out.println("CPU TIENE: "+state.getTokensCPU()+" FICHAS \n");
	}

	/**
	 * Method used to compute the user or CPU game
	 * @param  ProblemPathagon                                      problem       current problem
	 * @param  StatePathagon                                        state         current state
	 * @param  MinMaxAlphaBetaEngine<ProblemPathagon,StatePathagon> engine
	 * @return             game state
	 * @pre.     state!=null and problem!=null and engine!=null
   * @post.    a new state with some modification
	 */
	private static StatePathagon play(ProblemPathagon problem, StatePathagon state, MinMaxAlphaBetaEngine<ProblemPathagon,StatePathagon> engine){
		StatePathagon res = null;
		Scanner scan = new Scanner(System.in);
		int x = 0;
		int y = 0;
		boolean correctPosition = false;
		if(state.getTurn()==1){
			System.out.println("INGRESE LAS COORDENADAS DONDE DESEA INSERTAR LA FICHA: \n");
			while(!correctPosition){
				x = scan.nextInt();
				y = scan.nextInt();
				if(problem.occupied(x,y,state.getBoard())){
					System.out.println("CASILLERO OCUPADO, INGRESE OTRA POSICION \n");
				}
				else{
					correctPosition = true;
				}
			}
			if(x<0 || x>6 || y<0 || y>6){
				throw new IllegalArgumentException("In class appPathagon, method play: Incorrect input of coordinates");
			}
			else{
				res = problem.insertToken(state,x,y);
			}
		}
		else{
			res = engine.computeSuccessor(state);
		}
		return res;
	}

	public static void main(String[] args) {
		ProblemPathagon problem = new ProblemPathagon();
		StatePathagon currentState = problem.initialState();
		int depth = 5;
		MinMaxAlphaBetaEngine engine = new MinMaxAlphaBetaEngine(problem,depth);
		while(!problem.end(currentState)){
			showGame(currentState);
			currentState = play(problem,currentState,engine);
			System.out.println("\n  MOVIMIENTO APLICADO: "+ currentState.ruleApplied());
		}
		showGame(currentState);
		if(problem.value(currentState)==0){
			if(currentState.getTurn()==1)
				System.out.println("RESULTADO DEL JUEGO: EL USUARIO GANA");
			else
				System.out.println("RESULTADO DEL JUEGO: LA MAQUINA GANA");
		}
		else{
			System.out.println("RESULTADO DEL JUEGO: EMPATE");
		}
	}

}
