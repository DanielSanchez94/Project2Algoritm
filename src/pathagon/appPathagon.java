package pathagon;

import utilities.*;
import framework.AdversarySearchEngine;
import java.util.Scanner;

import java.util.List;

public class appPathagon {

	private static void showGame(StatePathagon state){
		System.out.println("ES EL TURNO DEL JUGADOR: "+state.getTurn()+ "\n");
		state.printBoard();
	}

	private static StatePathagon play(ProblemPathagon problem, StatePathagon state, MinMaxAlphaBetaEngine<ProblemPathagon,StatePathagon> engine){
		StatePathagon res = null;
		Scanner scan = new Scanner(System.in);
		int x,y;
		if(state.getTurn()==1){
			System.out.println("DEBE INGRESAR LAS COORDENADAS DONDE INSERTAR LA FICHA");
			System.out.println("INGRESE LA COORDENADA X: \n");
			x = scan.nextInt();
			System.out.println("INGRESE LA COORDENADA Y: \n");
			y = scan.nextInt();
			res = problem.insertToken(state,x,y);
		}
		else{
			res = engine.computeSuccessor(state);
		}
		return res;
	}

	public static void main(String[] args) {
		//-------------TEST---------------
		/*ProblemPathagon problem = new ProblemPathagon();
		StatePathagon newState = problem.initialState();
		newState.setTurn(2);
		System.out.println("TURNO DE: "+newState.getTurn());
		Token token1 = new Token(2,0,0);
		Token token2 = new Token(2,0,1);
		Token token3 = new Token(2,0,2);
		Token token4 = new Token(2,1,1);
		Token token5 = new Token(2,1,2);
		Token token6 = new Token(2,3,2);
		Token token7 = new Token(2,2,3);
		Token token8 = new Token(2,2,4);
		Token[][] newBoard = newState.getBoard();
		newBoard[0][0] = token1;
		newBoard[0][1] = token2;
		newBoard[0][2] = token3;
		newBoard[1][1] = token4;
		newBoard[1][2] = token5;
		newBoard[3][2] = token6;
		newBoard[2][3] = token7;
		newBoard[2][4] = token8;
		newState.setBoard(newBoard);
		//ADJACENT WORK
		//List<Token> adj = problem.adjacent(token2.getCoordenateX(), token2.getCoordenateY(), newState.getBoard());
		//Token tokenaux = problem.getUnvisitedAdj(adj);
		//System.out.println(tokenaux.getCoordenateX()+" , "+tokenaux.getCoordenateY());
		//DFS_MODIFIED WORK
		System.out.println("DFS: "+problem.dfs_modified(token1,newState));
		showGame(newState); */







		/*ProblemPathagon problem = new ProblemPathagon();
		StatePathagon currentState = problem.initialState();
		int depth = 4;
		MinMaxAlphaBetaEngine engine = new MinMaxAlphaBetaEngine(problem,depth);
		while(!problem.end(currentState)){
			showGame(currentState);
			currentState = play(problem,currentState,engine);
		}
		showGame(currentState);
		//Aca deberia evaluar el ultimo estado y ver quien gano
		if(problem.value(currentState)>0){
			System.out.println("GANO LA MAQUINA");
		}
		else{
			System.out.println("GANO EL USUARIO");
		}*/
	}

}
