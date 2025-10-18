package pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts;

import pt.isec.pa.tinypac.model.data.maze.MazeInfo;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.Ghost;

/**
 * Pinky (fantasma pinky)
 * <p>
 *     Aqui é criado o fantasma pinky e é implementado o seu movimento
 * </p>
 * @author  Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class Pinky extends Ghost {
    /**
     * Construtor do fantasma Pinky
     * @param y coordenada y
     * @param x coordenada x
     * @param mazeInfo infromação do labirinto
     */
    public Pinky(int y, int x, MazeInfo mazeInfo) {
        super('p',y,x,mazeInfo);
    }

    /**
     * Movimento do Pinky
     */
    @Override
    public void myMove() {
        randomMove();
        super.myMove();
    }
}
