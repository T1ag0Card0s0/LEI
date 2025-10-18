package pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts;

import pt.isec.pa.tinypac.model.data.maze.MazeInfo;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.Ghost;
/**
 * Inky (fantasma inky)
 * <p>
 *     Aqui é criado o fantasma inky e é implementado o seu movimento
 * </p>
 * @author  Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class Inky extends Ghost {
    /**
     * Construtor do fantasma Inky
     * @param y coordenada y
     * @param x coordenada x
     * @param mazeInfo informação do labirinto
     */
    public Inky(int y, int x, MazeInfo mazeInfo) {
        super('i', y, x, mazeInfo);
    }
    /**
     * Movimento do fantasma inky
     */
    @Override
    public void myMove() {
        randomMove();
        super.myMove();
    }
}
