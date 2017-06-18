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
			System.out.println("INGRESE LA COORDENADA X: ");
			x = scan.nextInt();
			System.out.println("INGRESE LA COORDENADA Y: ");
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
		//Aca deberia evaluar el ultimo estado y ver quien gano
		if(problem.value(currentState)>0){
			System.out.println("GANO LA MAQUINA");
		}
		else{
			System.out.println("GANO EL USUARIO");
		}
	}

}
