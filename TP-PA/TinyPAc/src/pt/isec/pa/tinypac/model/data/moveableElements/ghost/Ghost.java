package pt.isec.pa.tinypac.model.data.moveableElements.ghost;

import pt.isec.pa.tinypac.model.data.maze.MazeInfo;
import pt.isec.pa.tinypac.model.data.moveableElements.MoveableElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Ghost (base dos fantasmas)
 * <p>
 *     Aqui é feita toda a logica que seja comum a todos os fantasmas.
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class Ghost extends MoveableElement {
    private boolean locked;
    private boolean vulnerable;
    private final int []caveDoor;
    private List<int []> positions;
    private int index;
    /**
     * Construtor dos fantasmas
     * @param symbol simbolo que representa o fantasmas
     * @param y coordenada y
     * @param x coordenda x
     * @param mazeInfo informação do labirinto
     */
    public Ghost(char symbol, int y, int x,MazeInfo mazeInfo) {
        super(symbol,y,x,mazeInfo);
        this.locked=true;
        this.vulnerable=false;
        this.caveDoor=mazeInfo.getCaveDoors();
        this.index=0;
        this.positions = new ArrayList<>();
    }
       /**
     * Coloca a proxima direção a tomar dos fantasmas na direção que os leva para as coordenadas introduzidas
     * @param x coordenada x
     * @param y coordenada y
     */
    public void TravelTo(int x,int y){
        boolean YAxis = false,XAxis = false;
        if(x==getX())YAxis = true;
        else if(y==getY())XAxis = true;
        else {
            if(Math.abs(x-getX())>Math.abs(y-getY()))XAxis = true;
            else if(Math.abs(x-getX())<Math.abs(y-getY()))YAxis = true;
        }

        if(XAxis)
            if(getX()>x){
              setNextDirection(3);
            }
            else if(getX()<x){
                setNextDirection(1);
            }
        if(YAxis)
            if (getY() > y){
              setNextDirection(0);
            }
            else if (getY() < y){
              setNextDirection(2);

            }
        if(getNeighboors()[getNextDirection()]){
            randomMove();
        }
    }

    /**
     * Coloca a proxima direção dos fantasmas aleatoria.
     * Este movimento é feito principalmente pelo blinky e quando estao dentro da caverna.
     */
    public void randomMove(){
        if(getCurrentDirection()>=0) return;
        Random rnd = new Random();
        int rndDirecion;
        do {
            rndDirecion = rnd.nextInt(4);
        } while (getNeighboors()[rndDirecion]);
        setNextDirection(rndDirecion);
    }
    /**
     * Movimento vulneravel dos fantasmas, voltam atras nas posições que ja percorreram
     */
    public void vulnerableMove(){
        if(getUnderElement()!=null)
            if(getUnderElement().getSymbol()=='y'){
                vulnerable=false;
                return;
            }
        TravelTo(positions.get(index)[0],positions.get(index)[1]);
        if(index>0)
            index--;
    }

    /**
     * Movimento dos fantasmas
     */
    public void myMove() {
        if(getUnderElement()!=null)
            if(getUnderElement().getSymbol()=='y')
                TravelTo(caveDoor[1],caveDoor[0]);
    }

    /**
     * Retorna se o fantasma está vulnerável ou não
     * @return verdade se estiver vulneravel falso se não estiver vulnerável
     */
    public boolean getVulnerable(){return vulnerable;}

    /**
     * Retorna se está preso na caverna ou não
     * @return verdade se estiver preso, falso se não estiver preso
     */
    public boolean getLocked(){return locked;}

    /**
     * Atribui um valor ao valor vulnerable do fantasma
     * @param value verdade para ficar vulneravel, falso para deixar de ficar vulneravel.
     */
    public void setVulnerable(boolean value){this.vulnerable=value;}

    /**
     * Atrubui um valor ao valor locked do fantasma.
     * @param value verdade para estar preso, falso para nao estar preso.
     */
    public void setLocked(boolean value){this.locked=value;}

    /**
     * Decisao do tipo de movimento a tomar.
     * @return verdade se mover, falso se nao mover
     */
    @Override
    public boolean move() {
        positions.add(new int[]{getX(), getY()});
        if (vulnerable) {
            vulnerableMove();
        }else if (locked){
            positions=new ArrayList<>();
            randomMove();
        }
        else{index=positions.size(); myMove();}
        return super.move();
    }
}
