package Board;

public class Square {

    private boolean _hidden;
    private boolean _hasMine;
    private boolean _flagged;
    private int _adjacentMines;

    public Square() {
        _hidden = false;
        _hasMine = false;
        _flagged = false;
        _adjacentMines = 0;
    }

    public Square(boolean hasMine) {
        _hidden = false;
        _hasMine = hasMine;
        _flagged = false;
        _adjacentMines = 0;
    }

    public boolean get_hasMine() {
        return _hasMine;
    }

    public void set_hasMine(boolean hasMine) {
        _hasMine = hasMine;
    }

    public int get_adjacentMines() {
        return _adjacentMines;
    }

    public void set_adjacentMines(int adjacentMines) {
        _adjacentMines = adjacentMines;
    }

}
