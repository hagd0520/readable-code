package cleancode.minesweeper.tobe.minesweeper.gamelevel;

public class Advanced implements GameLevel {
    @Override
    public int getRowSize() {
        return 32;
    }

    @Override
    public int getColSize() {
        return 32;
    }

    @Override
    public int getLandMineCount() {
        return 99;
    }
}
