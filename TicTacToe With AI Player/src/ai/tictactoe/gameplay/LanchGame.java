package ai.tictactoe.gameplay;

import java.util.Random;
import java.util.Scanner;
//Create a board for game 3 x 3 box

class TicTacToe
{
	static char[][] board;
	public TicTacToe() {
		board = new char[3][3];	
		initBoard();
	}
	void initBoard() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j] = ' ';
			}
		}
	}
	static void displayBoard() {
		System.out.println("-------------------");
		for(int i= 0;i<board.length;i++) {
			System.out.print("|  ");
			for(int j=0;j<board[i].length;j++) {
				System.out.print(board[i][j] + "  |  ");	
			}
				System.out.println();
				System.out.println("-------------------");
			}
		}
	
//how to play(Tell row and column number and which mark like 'X' or '0')
	
	static void placeMark(int row,int col,char mark) {
		if(row >= 0 && row <= 2 && col >= 0 && col <= 2) {
			board[row][col] = mark;
		}else {
			System.out.println("Ivaild Position");
		}
	}
	
//check win condition(column,row,diagonally)
//check col win
	
	static boolean checkColumnWin() {
		for(int j = 0; j <= 2 ;j++) {
			if(board[0][j] != ' ' && 
					board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		return false;
	}
	
//check row win
	
	static boolean checkRowWin() {
		for(int i =0 ; i <= 2;i++) {
			if(board[i][0] != ' ' && 
					board[i][0] == board[i][1] && board[i][1] == board[i][2]){
				return true;
			}			
		}
		return false;
	}
	
//check diagonally win
	
	static boolean checkDiagonalWin() {
		if(board[0][0] !=' ' && board[0][0] == board[1][1] && board[0][0] == board[2][2]
				|| board[0][2] !=' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
	
	return false;
	}
//check draw like if(( both players not won the game will draw))
	static boolean checkDraw() {
		for(int i = 0;i <= 2 ;i++) {
			for(int j=0;j<=2;j++) {
				if(board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
}
//play with players like user input
class HumanPlayer extends Player{
	public HumanPlayer(String name, char mark) {
		this.name=name;
		this.mark=mark;
	}
//makeMove action(does part) or method
	void makeMove() {
		Scanner scan = new Scanner(System.in);
		int row;
		int col;
		do {
			System.out.println("pls, Enter row and column number: ");
			row=scan.nextInt();
			col=scan.nextInt();
		}while(!isValidMove(row, col)) ;
		
			TicTacToe.placeMark(row, col, mark);
	}
}
class AiPlayer extends Player{
	
	public AiPlayer(String name, char mark) {
		this.name=name;
		this.mark=mark;
	}

//makeMove action(does part) or method
	void makeMove() {
		Scanner scan = new Scanner(System.in);
		int row;
		int col;
		do {
			System.out.println("pls, Enter row and column number: ");
			Random r = new Random();
			row=r.nextInt(3);
			col=r.nextInt(3);
		}while(!isValidMove(row, col)) ;
		
			TicTacToe.placeMark(row, col, mark);
	}
}
abstract class Player{
	String name;
	char mark;
	abstract void makeMove();
//check if move is valid or not
		boolean isValidMove(int row , int col) {
// check within boundary or not
			if(row >= 0 && row <= 2 
					&& col >= 0 && col <= 2) {
//check box(entering place of row,col) is empty or not
				if(TicTacToe.board[row][col] == ' ') {
					return true;
					}	
			}
			return false;
		}

	
	
}

public class LanchGame {

	public static void main(String[] args) {
		TicTacToe t= new TicTacToe();
		HumanPlayer p1 = new HumanPlayer("player",'X');
		AiPlayer p2 = new AiPlayer("AI Player ",'O');
		Player cp;
		cp = p1;
		while(true) {
			System.out.println(cp.name + " turn ");
			cp.makeMove();
			TicTacToe.displayBoard();
			if(TicTacToe.checkColumnWin()||TicTacToe.checkRowWin()||TicTacToe.checkDiagonalWin()) {
				System.out.println(cp.name + " has won the Game");
				break;
			}
			else if(TicTacToe.checkDraw()) {
				System.out.println("draw or game over (both are not won the game)");
				break;	
			}
			else {
				if(cp == p1) {
					
					cp = p2;
				}
				else {
					cp = p1;
				}
			}
		}
		
		
		// TODO Auto-generated method stub

	}

}
