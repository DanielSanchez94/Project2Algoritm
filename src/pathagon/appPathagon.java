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
		ProblemPathagon problem = new ProblemPathagon();
		StatePathagon newState = problem.initialState();
		//newState.setTokensUser(0);
		//newState.setTokensCPU(0);
		//newState.setTurn(2);
		System.out.println("TURNO DE: "+newState.getTurn());
		Token token1 = new Token(1,0,0);
		Token token2 = new Token(2,0,1);
		Token token3 = new Token(1,0,2);
		Token token4 = new Token(1,0,3);
		Token token5 = new Token(1,0,4);
		Token token6 = new Token(1,0,5);
		Token token7 = new Token(1,0,6);
		Token token8 = new Token(2,2,4);
		Token token9 = new Token(2,3,2);
		Token[][] newBoard = newState.getBoard();
		newBoard[0][0] = token1;
		newBoard[0][1] = token2;
		//newBoard[0][2] = token3;
		//newBoard[0][3] = token4;
		//newBoard[0][4] = token5;
		//newBoard[0][5] = token6;
		//newBoard[0][6] = token7;
		//newBoard[2][4] = token8;
		//newBoard[3][2] = token9;
		newState.setBoard(newBoard);
		//ADJACENT WORK
		//List<Token> adj = problem.adjacent(token2.getCoordenateX(), token2.getCoordenateY(), newState.getBoard());
		//Token tokenaux = problem.getUnvisitedAdj(adj);
		//System.out.println(tokenaux.getCoordenateX()+" , "+tokenaux.getCoordenateY());
		//DFS_MODIFIED WORK
		//System.out.println("DFS: "+problem.dfs_modified(token1,newState));
		showGame(newState);
		//VALUE WORK
		//System.out.println("VALUE: "+problem.value(newState));
		//END WORK
		//System.out.println("END: "+problem.end(newState));
		//INSERT AND LOCKED WORK
		/*Scanner scan = new Scanner(System.in);
		System.out.println("TURNO: "+newState.getTurn()+" Fichas jug: "+newState.getTokensUser()+" FICHAS CPU: "+newState.getTokensCPU());
		System.out.println("INGRESE LA COORDENADA X: \n");
		int x = scan.nextInt();
		System.out.println("INGRESE LA COORDENADA Y: \n");
		int y = scan.nextInt();
		newState = problem.insertToken(newState,x,y);
		showGame(newState);
		System.out.println("TURNO: "+newState.getTurn()+" Fichas jug: "+newState.getTokensUser()+" FICHAS CPU: "+newState.getTokensCPU());
		newState.setTurn(2);
		System.out.println("INGRESE LA COORDENADA X: \n");
		int x2 = scan.nextInt();
		System.out.println("INGRESE LA COORDENADA Y: \n");
		int y2 = scan.nextInt();
		newState = problem.insertToken(newState,x2,y2);
		showGame(newState);
		System.out.println("TURNO: "+newState.getTurn()+" Fichas jug: "+newState.getTokensUser()+" FICHAS CPU: "+newState.getTokensCPU());
		*/





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
