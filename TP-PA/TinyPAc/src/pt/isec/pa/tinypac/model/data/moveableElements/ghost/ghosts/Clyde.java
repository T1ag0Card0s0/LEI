package pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts;

import pt.isec.pa.tinypac.model.data.maze.MazeInfo;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.Ghost;

/**
 * Clyde (fantasma clyde)
 * <p>
 *     Aqui é criado o fantasma clyde e é implementado o seu movimento
 * </p>
 * @author  Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class Clyde extends Ghost {
    /**
     * Construtor clyde
     * @param y coordenada y
     * @param x coordenada x
     * @param mazeInfo informação do labirinto
     */
    public Clyde(int y, int x, MazeInfo mazeInfo) {
        super('c',y,x,mazeInfo);
    }

    /**
     * Valida se o pacman está no campo de visão
     * @return verdade se estiver no campo de visao, falso se não estiver no campo de visão
     */
    public boolean pacManInFieldOfVision(){
        int i = 0;
        for(boolean b: getNeighboors()){
            if(!b){
                int x=getX(),y=getY();
                while (getMazeElementSymbol(y,x)!='x'){
                    switch (i){
                        case 0-> y--;
                        case 1-> x++;
                        case 2-> y++;
                        case 3-> x--;
                    }
                    if(x==getXPacMan()&&getYPacMan()==y){
                        setNextDirection(i);
                        return true;
                    }
                }
            }
            i++;
        }
        return false;
    }

    /**
     * Movimento do clyde
     */
    @Override
    public void myMove() {
        if(!pacManInFieldOfVision())
            randomMove();
        super.myMove();
    }
}
