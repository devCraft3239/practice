package LLD;

import java.util.*;

/**
 The game is to be played between two people (in this program between HUMAN and COMPUTER).
 One of the player chooses ‘O’ and the other ‘X’ to mark their respective cells.
 The game starts with one of the players and the game ends when one of the players has one whole row/ column/ diagonal filled with his/her respective character (‘O’ or ‘X’).
 If no one wins, then the game is said to be drawn.
 * */
public class TicTacToe {
}


class Player{
    String id;
    String name;
    Mark mark;

    Player(String id, String name, Mark mark){
        this.id =  id;
        this.name = name;
        this.mark =  mark;
    }
}

class Board{
    int dimension;
    Mark[][] matrix;
    int[] rowSum, colSum;
    int diagSum, revDiagSum;

    Board(int dimension){
        this.dimension =  dimension;
        this.matrix =  new Mark[dimension][dimension];
    }

    void printBoard(){
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    boolean isValidPosition(int x, int y){
        return  (x >=0 && x<dimension) && (y >=0 && y<dimension) && (matrix[x][y] == null);
    }

    boolean checkWin(Mark m){  // O(n^2)
        // check for rows
        if((matrix[0][0] == m && matrix[0][1] == m && matrix[0][2] ==m) ||
                (matrix[1][0] == m && matrix[1][1] == m && matrix[1][2] ==m) ||
                (matrix[2][0] == m && matrix[2][1] == m && matrix[2][2] ==m) )
            return true;
        // check for column
        if((matrix[0][0] == m && matrix[1][0] == m && matrix[2][0] ==m) ||
                (matrix[0][1] == m && matrix[1][1] == m && matrix[2][1] ==m) ||
                (matrix[0][2] == m && matrix[1][2] == m && matrix[2][2] ==m) )
            return true;

        // check diagonal
        if((matrix[0][0] == m && matrix[1][1] == m && matrix[2][2] ==m) ||
                (matrix[2][0] == m && matrix[1][1] == m && matrix[0][2] ==m))
            return true;
        return false;
    }

    boolean checkWin2(int row, int col, Mark m){ // O(N)
        // check for row
        boolean winRow = true,winCol=true,winDiag=true,winRevDiag= true;
        for (int i = 0; i < dimension; i++) {
            // check for rows
            if(matrix[row][i] != m)
                winRow = false;

            // check for colum
            if (matrix[i][col] != m)
                winCol = false;

            // check for diagonal
            if (matrix[i][i] != m)
                winDiag =  false;

            /// check for reverse diagonal
            if(matrix[i][dimension-1-i] != m)
                winRevDiag = false;
        }
        if(winRow || winCol || winDiag || winRevDiag)
            return true;
        else
            return false;
    }

    boolean checkWin3(int row, int col, Mark m){  // O(1)
        rowSum[row] += m.value;
        colSum[col] += m.value;
        if(row == col)
            diagSum += m.value;
        if (row == dimension-1-col || col == dimension-1-row)
            revDiagSum +=  m.value;
        if(dimension == Math.abs(rowSum[row]) ||
                dimension == Math.abs(colSum[row]) ||
                dimension == Math.abs(diagSum) ||
                dimension == Math.abs(revDiagSum) )
            return true;
        return false;
    }
}

enum GameStatus{
    NOT_STARTED,
    STARTED,
    FINISHED,
    DRAWN
}

enum Mark{
    X(1),
    O(-1);
    int value;

    Mark(int value) {
        this.value = value;
    }
}

class Move{
    Player player;
    int x;
    int y;
    Mark mark;

    public Move(Player player, int x, int y, Mark mark) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.mark = mark;
    }
}

abstract class TicTacToeGame{
    Board board;
    Queue<Player> players;
    List<Move> moves;
    GameStatus gameStatus;
    Player winner;
    Scanner input;

    abstract void makeMove(Move m);
    abstract boolean isWinMove(Move m);
}

class TicTacToeGameImpl extends TicTacToeGame{
    public static final int DIMENSION = 3;

    TicTacToeGameImpl(){
        board = new Board(DIMENSION);
        players = new LinkedList<>();
        players.add(new Player("1", "X", Mark.X));
        players.add(new Player("2", "Y", Mark.O));
        moves = new ArrayList<>();
        gameStatus =  GameStatus.STARTED;
        input =  new Scanner(System.in);
    }

    void play(){
        while (gameStatus != GameStatus.FINISHED || gameStatus != GameStatus.DRAWN){
            Player p =players.poll();
            players.add(p);
            System.out.println("enter position for player:"+p);
            int x = input.nextInt();
            int y =  input.nextInt();
            makeMove(new Move(p, x, y, p.mark));
        }
    }

    @Override
    void makeMove(Move m) {
        Player player = m.player;
        int x = m.x;
        int y = m.y;
        Mark mark = m.mark;
        if(board.isValidPosition(x, y)){
            board.matrix[x][y] = mark;
            if(board.checkWin(mark)){
                gameStatus =  GameStatus.FINISHED;
                winner =  player;
            }
        }else {
            throw new IllegalArgumentException("Illegal move");
        }

    }

    @Override
    boolean isWinMove(Move m) {
        return false;
    }
}


