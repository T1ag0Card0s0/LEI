package pt.isec.pa.tinypac.model.data.maze;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.Ghost;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts.Blinky;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts.Clyde;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts.Inky;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.ghosts.Pinky;
import pt.isec.pa.tinypac.model.data.moveableElements.pacman.PacMan;

import java.io.Serializable;

/**
 * MazeInfo (informações importantes do Maze)
 * <p>
 *     Aqui são guardadas informações e implementados metodos para
 *     obter informações importantes para a execução do jogo.
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class MazeInfo implements Serializable {
    private final Maze maze;
    private final MoveableElement[] moveableElements;
    private Element fruit;
    private final Element[] wrap;
    private final int[] caveDoors;
    private final int [] initGhostXY;
    private final int[] initPacManXY;
    private boolean ghostsAdded;
    private int wrapindex, eatenGhost, count, numOfFood;
    private int width,height;
    /**
     * Construtor do MazeInfo
     * @param heigh altura do tabuleiro
     * @param width comprimento do tabuleiro
     */
    public MazeInfo(int heigh,int width){
        maze = new Maze(heigh,width);
        this.width=width;this.height=heigh;
        this.caveDoors=new int[2];
        this.moveableElements=new MoveableElement[5];
        this.ghostsAdded=false;
        this.wrapindex=0;
        this.wrap=new Element[2];
        this.numOfFood=0;
        this.initGhostXY=new int[2];
        this.initPacManXY=new int[2];
        this.count = 0;
        this.eatenGhost=0;
    }

    /**
     * Obtem o simbolo do elemento que o pacman passou por cima
     * @return char symbol, simbolo do elemento que o pacman "comeu"
     */
    public char pacManAte(){
        PacMan pacman = (PacMan) getMoveableElement('P');
        if(pacman==null){return ' ';}
        for (MoveableElement e : moveableElements) {
            if (e instanceof Ghost) {
                if (e.getY() == pacman.getY()&&e.getX() == pacman.getX()) {
                    if (pacman.getPower()) {
                        e.setUnderElement((Element) maze.get(initGhostXY[1], initGhostXY[0]));
                        e.setXY(initGhostXY[0], initGhostXY[1]);
                        eatenGhost++;
                    } else {
                        pacman.setUnderElement((Element) maze.get(initPacManXY[1], initPacManXY[0]));
                        pacman.setXY(initPacManXY[0], initPacManXY[1]);
                    }
                    return e.getSymbol();
                }
            }
        }
        if(pacman.getUnderElement()==null)return ' ';
        if(pacman.getUnderElement().getSymbol()=='F'){ count = 0;fruit.setSymbol(' ');}
        else if(pacman.getUnderElement().getSymbol() == '.'||pacman.getUnderElement().getSymbol()=='O')count++;
        if(count > 20){fruit.setSymbol('F');}
        else fruit.setSymbol(' ');
        return pacman.getUnderElement().getSymbol();
    }

    /**
     * Teletransporta o pacman de um wrapper para outro
     */
    public void teleTransportPacMan(){
        MoveableElement e = getMoveableElement('P');
        if(e==null)return;
        if(e.getX()==wrap[0].getX()&&e.getY()==wrap[0].getY())
            e.setXY(wrap[1].getX(),wrap[1].getY());
        else
            e.setXY(wrap[0].getX(),wrap[0].getY());
    }

    /**
     * Coloca os elementos móveis nas suas posições iniciais
     */
    public void initMoveableElements(){
        for(MoveableElement m:moveableElements){
            maze.set(m.getY(),m.getX(),null);
           // m.setUnderElement((Element) maze.get(m.getInitY(),m.getInitX()));
            m.setXY(m.getInitX(),m.getInitY());
        }
    }

    /**
     * Obtém o valor de poder do pacman
     * @return boolean poder, verdade se tiver poder, falso se nao tiver poder
     */
    public boolean getPacManPower(){
        PacMan pacMan=(PacMan) getMoveableElement('P');
        if(pacMan==null)return false;
        return pacMan.getPower();
    }

    /**
     * Retorna numero de fantasmas comidos para ser usado no calculo da pontuação
     * @return int numero de fantasmas comidos
     */
    public int getNumOfEatenGhost(){return eatenGhost;}

    /**
     * Retorna os elementos moveis.
     * @return moveableElements.
     */
    public MoveableElement []getMoveableElements(){
        return moveableElements;
    }

    /**
     * Retorna a direção atual do pacman.
     * @return int direção atual do pacman
     */
    public int getPacManDirection(){return getMoveableElement('P').getCurrentDirection();}

    /**
     * Obtem um elemento na posição x e y
     * @param y coordenada y
     * @param x coordenada x
     * @return IMazeElement elemento do labirinto
     */
    public IMazeElement get(int y,int x){return maze.get(y,x);}

    /**
     * Retorna array de coordenadas da porta de saida da caverna.
     * @return int [] coordenadas
     */
    public int []getCaveDoors(){return caveDoors;}

    /**
     * Retorna o tabuleiro do jogo.
     * @return char [][] tabuleiro
     */
    public char[][]getBoard(){return maze.getMaze().clone();}

    /**
     * Obtem o elemento movel apartir do seu simbolo
     * @param c simbolo do elemento movel.
     * @return Elemento movel
     */
    public MoveableElement getMoveableElement(char c){
        for(MoveableElement e:moveableElements)
            if(e.getSymbol() == c)
                return e;
        return null;
    }

    /**
     * Retorna o numero de comida que existe
     * @return numero de comida que existe
     */
    public int getNumOfFood() {return numOfFood;}
    public int getHeight(){return height;}
    public int getWidth(){return width;}
    /**
     * Atribui a proxima direção para o pacman tomar.
     * @param nextDirection proxima direção
     */
    public void setPacManNextDirection(int nextDirection){moveableElements[4].setNextDirection(nextDirection);}

    /**
     * Inicialização dos elementos moveis no maze
     * @param element novo elemento no maze
     */
    public void setInitElements(Element element){
        if(element==null) return;
        maze.set(element.getY(), element.getX(), element);
        switch (element.getSymbol()) {
            case 'y' -> {
                if (!ghostsAdded) {
                    moveableElements[0] = new Blinky(element.getY(),element.getX(),this);
                    moveableElements[1] = new Clyde(element.getY(),element.getX(),this);
                    moveableElements[2] = new Inky(element.getY(),element.getX(),this);
                    moveableElements[3] = new Pinky(element.getY(),element.getX(),this);
                    ghostsAdded=true;
                    initGhostXY[0]=element.getX();
                    initGhostXY[1]=element.getY();
                }
            }
            case 'W'->{
                if(wrapindex<2) {
                    wrap[wrapindex] = element;
                    wrapindex++;
                }
            }
            case 'Y'->{caveDoors[0]=element.getY();caveDoors[1]= element.getX();}
            case 'M' -> {
                initPacManXY[0]=element.getX();initPacManXY[1]=element.getY();
                if (moveableElements[4] == null)
                    moveableElements[4] = new PacMan(element.getY(), element.getX(),this);
            }
            case 'F'->{
                fruit=new Element(' ',element.getY(),element.getX());
                maze.set(fruit.getY(),fruit.getX(),fruit);
            }
            case 'o'->{element.setSymbol('.');numOfFood++;}
            case 'O'->numOfFood++;
        }
    }

    /**
     * Coloca os fantasmas vulneraveis
     * @param value verdade para ficarem vulneraveis, falso para nao ficarem vulneraveis
     */
    public void setVulnerable(boolean value){
        for(MoveableElement element:moveableElements)
            if(element instanceof Ghost ghost) ghost.setVulnerable(value);
    }

    /**
     * Coloca um elemento numa posição do maze
     * @param y coordenada y
     * @param x coordenada x
     * @param element elemento
     */
    public void set(int y,int x,IMazeElement element){
        maze.set(y,x,element);
    }
}
