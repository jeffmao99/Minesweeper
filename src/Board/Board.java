package Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {

    private int _x;
    private int _y;
    private int _numMines;
    private ArrayList<ArrayList<Square>> _board;
    private ArrayList<ArrayList<Integer>> _directions;


    public Board() {
        _x = 30;
        _y = 16;
        _numMines = 0;
        _board = new ArrayList<>();
        for (int i = 0; i < _y; i++) {
            _board.add(new ArrayList<Square>());
            for (int j = 0; j < _x; j++) {
                _board.get(i).add(new Square());
            }
        }
    }

    public Board(int x, int y) {
        _x = x;
        _y = y;
        _numMines = 0;
        _board = new ArrayList<>();
        for (int i = 0; i < _y; i++) {
            _board.add(new ArrayList<Square>());
            for (int j = 0; j < _x; j++) {
                _board.get(i).add(new Square());
            }
        }
    }

    public Board(int x, int y, int mines) {
        _x = x;
        _y = y;
        _numMines = mines;
        _board = new ArrayList<>();

        //setting the directions
        _directions = new ArrayList<>();
        ArrayList<Integer> topLeft = new ArrayList<>(Arrays.asList(-1, -1));
        ArrayList<Integer> top = new ArrayList<>(Arrays.asList(0, -1));
        ArrayList<Integer> topRight = new ArrayList<>(Arrays.asList(1, -1));
        ArrayList<Integer> midLeft = new ArrayList<>(Arrays.asList(-1, 0));
        ArrayList<Integer> midRight = new ArrayList<>(Arrays.asList(1, 0));
        ArrayList<Integer> botLeft = new ArrayList<>(Arrays.asList(-1, 1));
        ArrayList<Integer> bot = new ArrayList<>(Arrays.asList(0, 1));
        ArrayList<Integer> botRight = new ArrayList<>(Arrays.asList(1, 1));
        _directions.addAll(Arrays.asList(topLeft, top, topRight, midLeft, midRight, botLeft, bot, botRight));



        //adding all the squares to the board
        for (int i = 0; i < _y; i++) {
            ArrayList<Square> row = new ArrayList<>();
            for (int j = 0; j < _x; j++) {
                row.add(new Square());
            }
            _board.add(row);
        }

        //randomizing the mine placement on the board
        Random rand = new Random();
        while (mines > 0) {
            for (int i = 0; i < _y; i++) {
                for (int j = 0; j < _x; j++) {
                    if (mines == 0) {
                        break;
                    }
                    int randInt = rand.nextInt(_x * _y);
                    if (randInt < _numMines) {
                        Square sq = _board.get(i).get(j);
                        sq.set_hasMine(true);
                        mines--;

                        //increase adjacent squares' mine count
                        for (int k = 0; k < _directions.size(); k++) {
                            int tempy = i + _directions.get(k).get(1);
                            int tempx = j + _directions.get(k).get(0);
                            if (tempy >= 0 && tempx >= 0 && tempy <_y && tempx < _x) {
                               Square tempsq = _board.get(tempy).get(tempx);
                               tempsq.set_adjacentMines(tempsq.get_adjacentMines() + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public void printBoard() {
        ArrayList<ArrayList<String>> printedBoard = new ArrayList<>();
        for (ArrayList<Square> lis : _board) {
            ArrayList<String> printedString = new ArrayList<>();
            for (Square sq : lis) {
                if (sq.get_hasMine()) {
                    printedString.add("X");
                } else {
                    printedString.add("" + sq.get_adjacentMines());
                }
            }
            printedBoard.add(printedString);
        }
        for (ArrayList<String> row : printedBoard) {
            System.out.println(row);
        }
    }

    public static void main(String[] args) {
        Board bd1 = new Board();
        Board bd2 = new Board(30, 16, 99);
        Board bd3 = new Board(10, 10, 20);
        bd3.printBoard();
    }
}
