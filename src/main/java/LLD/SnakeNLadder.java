package main.java.LLD;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SnakeNLadder {
    Queue<Player> players;
    Board board;
    Dice dice;
    GameStatus status;

    public void startGame(){

    }

    public void makeMove(Player p){

    }

    public void addPlayers(Queue<Player> players)
    {
        this.players = players;
    }
}

class Board{
    int n; // dimension
    Map<Integer, SpecialMove> specialMoves;

    public boolean isSpecialMove(int start){
        return specialMoves.containsKey(start);
    }
    public SpecialMove getSpecialMove(int start){
        return specialMoves.getOrDefault(start, null);
    }

    public void addSpecialMove(SpecialMove specialMove){
        specialMoves.put(specialMove.start, specialMove);
    }

    public void printBoard(){

    }
}

class Dice{
    final int upper = 6;
    final int lower = 1;
    public int roll(){
        return lower + (int) (Math.random() * (upper - lower));
    }
}

class Player{
    int pos;
    String name;
}

abstract class SpecialMove{
    int start;
    int end;

    abstract String getId();
}

class Snake extends SpecialMove
{

    @Override
    String getId() {
        return "S_"+this.end;
    }
}

class Ladder extends SpecialMove{

    @Override
    String getId() {
        return "L_"+this.end;
    }
}

enum GameStatus{
    NOT_STARTED,
    STARTED,
    RUNNING,
    FINISHED
}