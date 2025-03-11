package cleancode.minesweeper.tobe.minesweeper.gamelevel;

public class Advanced implements GameLevel {
    @Override
    public int getRowSize() {
        return 320;
    }

    @Override
    public int getColSize() {
        return 324;
    }

    @Override
    public int getLandMineCount() {
        return 9;
    }
}
