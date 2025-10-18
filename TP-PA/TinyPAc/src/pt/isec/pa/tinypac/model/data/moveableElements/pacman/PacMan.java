package pt.isec.pa.tinypac.model.data.moveableElements.pacman;

import pt.isec.pa.tinypac.model.data.maze.MazeInfo;
import pt.isec.pa.tinypac.model.data.moveableElements.MoveableElement;

/**
 * PacMan (jogador)
 * <p>
 *     Esta classe representa o pacman com os seus parametros.
 * </p>
 *@author Tiago Cardoso 2021138999
 *@version guiVersion
 */
public class PacMan extends MoveableElement {
    private boolean power;

    /**
     * Construtor do PacMan
     * @param y coordenada y
     * @param x coordenada x
     * @param mazeInfo informação do labirinto
     */
    public PacMan(int y, int x, MazeInfo mazeInfo) {
        super('P',y,x,mazeInfo);
        this.power=false;
    }

    /**
     * Retorna se o pacman tem poderes ou nao
     * @return power
     */
    public boolean getPower(){return power;}

    /**
     * Atribui um valor ao poder do pacman
     * @param value verdade para passar a ter poder falso para nao ter poder.
     */
    public void setPower(boolean value){this.power = value;}
}
