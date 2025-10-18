package pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts;

import pt.isec.pa.tinypac.model.data.maze.MazeInfo;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.Ghost;

/**
 * Blinky (fantasma blinky)
 * <p>
 *     Aqui é criado o fantasma blinky e é implementado o seu movimento
 * </p>
 * @author  Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class Blinky extends Ghost {
    /**
     * Construtor do blinky
     * @param y coordenada y
     * @param x coordenada x
     * @param mazeInfo informação do tabuleiro onde se localiza
     */
    public Blinky(int y, int x, MazeInfo mazeInfo) {
        super('b',y,x,mazeInfo);
    }

    /**
     * Movimento do fantasma blinky
     */
    @Override
    public void myMove() {
        randomMove();
        super.myMove();
    }


}
