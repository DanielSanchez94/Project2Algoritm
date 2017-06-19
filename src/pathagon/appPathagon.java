package pathagon;

import utilities.*;
import framework.AdversarySearchEngine;
import java.util.Scanner;

import java.util.List;

public class appPathagon {

	private static void showGame(StatePathagon state){
		state.printBoard();
		System.out.println("ES EL TURNO DEL JUGADOR: "+state.getTurn()+ "\n");
		System.out.println("USER TIENE: "+state.getTokensUser()+" FICHAS");
		System.out.println("CPU TIENE: "+state.getTokensCPU()+" FICHAS \n");

	}

	private static StatePathagon play(ProblemPathagon problem, StatePathagon state, MinMaxAlphaBetaEngine<ProblemPathagon,StatePathagon> engine){
		StatePathagon res = null;
		Scanner scan = new Scanner(System.in);
		int x,y;
		if(state.getTurn()==1){
			System.out.println("DEBE INGRESAR LAS COORDENADAS DONDE INSERTAR LA FICHA");
			System.out.println("INGRESE LAS COORDENADAS : ");
			x = scan.nextInt();
			y = scan.nextInt();
			res = problem.insertToken(state,x,y);
		}
		else{
			res = engine.computeSuccessor(state);
		}
		return res;
	}

	public static void main(String[] args) {
		ProblemPathagon problem = new ProblemPathagon();
		StatePathagon currentState = problem.initialState();
		int depth = 4;
		MinMaxAlphaBetaEngine engine = new MinMaxAlphaBetaEngine(problem,depth);
		while(!problem.end(currentState)){
			showGame(currentState);
			currentState = play(problem,currentState,engine);
		}
		showGame(currentState);
		if(problem.value(currentState)>0){
			System.out.println("GANO LA MAQUINA");
		}
		else{
			System.out.println("GANO EL USUARIO");
		}
		/*Token token1 = new Token(1,0,6);
		Token token2 = new Token(1,1,6);
		Token token3 = new Token(1,2,6);
		Token token4 = new Token(1,3,6);
		Token token5 = new Token(1,4,6);
		Token token6 = new Token(1,5,6);
		Token token7 = new Token(1,6,6);

		currentState.getBoard()[0][6] = token1;
		currentState.getBoard()[1][6] = token2;
		currentState.getBoard()[2][6] = token3;
		currentState.getBoard()[3][6] = token4;
		currentState.getBoard()[4][6] = token5;
		currentState.getBoard()[5][6] = token6;
		currentState.getBoard()[6][6] = token7;
		System.out.println("DFS...");
		System.out.println(problem.dfs_modified(token1,currentState));
		System.out.println("END...");
		System.out.println(problem.end(currentState));
		System.out.println("VALUE...");
		System.out.println(problem.value(currentState));*/

	}

}
