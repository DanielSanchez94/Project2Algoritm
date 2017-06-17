package pathagon;

import utilities.*;
import framework.AdversarySearchEngine;
import java.util.Scanner;

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
		System.out.println("lala");
		ProblemPathagon problem = new ProblemPathagon();
		StatePathagon currentState = problem.initialState();
		int depth = 4;
		MinMaxAlphaBetaEngine engine = new MinMaxAlphaBetaEngine(problem,depth);
		while(!problem.end(currentState)){
			showGame(currentState);
			currentState = play(problem,currentState,engine);
		}
		showGame(currentState);
		//Aca deberia evaluar el ultimo estado y ver quien gano
		if(problem.value(currentState)<0){
			System.out.println("GANO EL USUARIO");
		}
		else{
			System.out.println("GANO LA MAQUINA");
		}
	}

}
