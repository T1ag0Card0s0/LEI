package pt.isec.pa.tinypac.model.data.moveableElements;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.maze.MazeInfo;

/**
 * MoveableElement (elementos moveis)
 * <p>
 *     Aqui está implementada toda a logica comum aos elementos moveis.
 * </p>
 *  @author Tiago Cardoso 2021138999
 *  @version guiVersion
 */
public class MoveableElement extends Element{
    private final MazeInfo mazeInfo;
    private int currentDirection, nextDirection;
    private int lastX, lastY;
    private final int initX, initY;
    private final boolean []neighboors;//TOP,RIGHT,BOTTOM,LEFT Check if there are walls around
    private Element underElement;

    /**
     * Construtor MoveableElement
     * @param symbol simbolo do elemento
     * @param y coordenada y
     * @param x coordenada x
     * @param mazeInfo informação do labirinto
     */
    public MoveableElement(char symbol, int y, int x, MazeInfo mazeInfo) {
        super(symbol,y,x);
        this.neighboors = new boolean[4];
        this.currentDirection=-1;
        this.nextDirection=currentDirection;
        if(mazeInfo.get(y,x) instanceof MoveableElement moveableElement){
            underElement = moveableElement.getUnderElement();
        }else{
            underElement =  (Element) mazeInfo.get(y,x);
        }
        mazeInfo.set(y,x,this);
        this.lastX=x;
        this.lastY=y;
        this.initX = x;
        this.initY = y;
        this.mazeInfo=mazeInfo;
        checkNeighboors();

    }

    /**
     * Verifica se os vizinhos sao paredes ou nao.
     */
    public void checkNeighboors(){
        neighboors[0] = getMazeElementSymbol(getY()-1,getX()) == 'x';
        neighboors[1] = getMazeElementSymbol(getY(),getX() + 1) == 'x';
        neighboors[2] = getMazeElementSymbol(getY()+1,getX()) == 'x';
        neighboors[3] = getMazeElementSymbol(getY(),getX()-1) == 'x';
    }

    /**
     * Verifica os vizinhos com uma restrição a mais para além da parede.
     * @param constraint symbolo do elemento a evitar.
     */
    public void checkNeighboorsWithExtraConstraint(char constraint) {
        char top=getMazeElementSymbol(getX(),getY()-1),right=getMazeElementSymbol(getX() + 1,getY())
                ,bottom=getMazeElementSymbol(getX(),getY()+1),left=getMazeElementSymbol(getX()-1,getY());
        neighboors[0] = top=='x'||top==constraint;
        neighboors[1] = right=='x'||right==constraint;
        neighboors[2] = bottom=='x'||bottom==constraint;
        neighboors[3] = left=='x'||left==constraint;

    }

    /**
     * Muda a direção atual para a proxima direção introduzida.
     */
    public void changeDirection(){
        if(nextDirection==-1)return;
        if(!neighboors[nextDirection]){
            currentDirection=nextDirection;
        }
    }

    /**
     * Movimento dos elementos dado uma direção.
     * @return verdade se moveram, falso se nao moveram.
     */
    public boolean move(){
        checkNeighboors();
        changeDirection();
        if(currentDirection==-1) return false;
        if(neighboors[currentDirection]){ currentDirection=-1;return false;}

        int x = getX(),y = getY();
        this.lastX=getX();this.lastY=getY();
        switch (currentDirection) {
            case 0 -> y--;//UP
            case 1 -> x++;//RIGHT
            case 2 -> y++;//BOTTOM
            case 3 -> x--;//LEFT
        }
        if(mazeInfo.get(y,x) instanceof MoveableElement moveableElement){
            underElement = moveableElement.getUnderElement();
        }else{
            underElement = (Element) mazeInfo.get(y,x);
        }
        setX(x);setY(y);
        return true;
    }

    /**
     * Retorna a direção atual
     * @return currentDirection
     */
    public int getCurrentDirection() {return currentDirection;}

    /**
     * Retorna um array booleano que indica se os vizinhos sao transponiveis.
     * @return boolean[] neighboors
     */
    public boolean[] getNeighboors() {return neighboors;}

    /**
     * Retorna a proxima direção.
     * @return proxima direção;
     */
    public int getNextDirection() {
        return nextDirection;
    }

    /**
     * Retorna o elemento que está por debaixo.
     * @return underElement
     */
    public Element getUnderElement(){return underElement;}

    /**
     * Retorna a coordenada x atual do pacman
     * @return int  mazeInfo.getMoveableElement('P').getX();
     */
    public int getXPacMan(){return mazeInfo.getMoveableElement('P').getX();}

    /**
     * Retorna a coordenada y atual do pacman
     * @return int mazeInfo.getMoveableElement('P').getY();
     */
    public int getYPacMan(){return mazeInfo.getMoveableElement('P').getY();}

    /**
     * Retorna a coordenada x inicial em que este elemento foi criado
     * @return int initX
     */
    public int getInitX(){return initX;}

    /**
     * Retorna a coordenada y inicial em que este elemento foi criado
     * @return int initY
     */
    public int getInitY(){return initY;}

    /**
     * Retorna o simbolo de um elemento no labirinto numa dada coordenada
     * @param y coordenada y
     * @param x coordenada x
     * @return char mazeInfo.get(y,x).getSymbol();
     */
    protected char getMazeElementSymbol(int y,int x){
        if(mazeInfo.get(y,x)==null) return ' ';
        return  mazeInfo.get(y,x).getSymbol();
    }

    /**
     * Coloca uma proposta para uma proxima direção que irá ser tomada assim que for possivel
     * @param nextDirection proxima posição
     */
    public void setNextDirection(int nextDirection) {this.nextDirection = nextDirection;}

    /**
     * Guarda uma referencia do elemento que está a ser passado por cima.
     * @param underElement referencia do elemento que esta a ser sobreposto
     */
    public void setUnderElement(Element underElement) {this.underElement = underElement;}


}
