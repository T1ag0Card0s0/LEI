package pt.isec.pa.tinypac.model.data.element;

import pt.isec.pa.tinypac.model.data.maze.IMazeElement;


/**
 * Element (elemento do labirinto)
 * <p>
 *     Esta classe implementa o IMazeElement e o seu metodo getSymbol.
 * </p>
 *@author Tiago Cardoso 2021138999
 *@version guiVersion
 */
public class Element implements IMazeElement,Cloneable {
    private char symbol;
    private int x,y;

    /**
     * Construtor do elemento do labirinto.<br>
     * Posição do elemento. Não é pretendido guardar estes parametros, mas já é tarde para alterar o codigo,
     * portanto deixei assim mesmo, mas alteraria caso tivesse tempo para tal.
     * @param symbol simbolo que representa o elemento
     * @param y posição y
     * @param x posição x
     */
    public Element(char symbol, int y, int x ){
        this.symbol=symbol;
        this.x=x;
        this.y=y;
    }

    /**
     * Obtém a coordenada x do elemento
     * @return int x
     */
    public int getX() {
        return x;
    }
    /**
     * Obtém a coordenada y do elemento
     * @return int y
     */
    public int getY() {
        return y;
    }

    /**
     * Atribui o simbolo ao elemento
     * @param symbol simbolo novo do elemento
     */
    public void setSymbol(char symbol){this.symbol=symbol;}

    /**
     * Atribui uma nova coordenada x
     * @param x nova cooredenada
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Atribui uma nova coordenada y
     * @param y nova coordenada y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Atribui com uma chamada de funcao novas coordenadas x e y
     * @param x nova coordenada x
     * @param y nova coordenada y
     */
    public void setXY(int x,int y){
        setX(x);setY(y);
    }

    /**
     * Retorna o simbolo do elemento
     * @return char symbol
     */
    @Override
    public char getSymbol() {
        return symbol;
    }

    /**
     * Retorna o clone do elemento
     * @return Element
     * @throws CloneNotSupportedException exceção de nao suportar o clone do elemento
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
