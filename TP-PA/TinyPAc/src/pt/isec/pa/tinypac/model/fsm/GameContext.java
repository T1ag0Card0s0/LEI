package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.fsm.states.InitialState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * GameContext (contexto do jogo)
 * <p>
 *     Aqui é feita principalmente a chamada de metodos que permitem trocar de estado.
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class GameContext implements Serializable {
    public static final long serialVersionUID = 1L;
    private IGameState state;
    private IGameState lastState;
    private final Game game;

    /**
     * Construtor do GameContext
     */
    public GameContext(){
        game=new Game();
        state=new InitialState(this,game);
        lastState=state;
    }

    /**
     * Muda de estado e guarda o estado anterior
     * @param newState novo estado
     */
    public void changeState(IGameState newState){
        this.lastState=state;
        this.state=newState;
    }

    /**
     * Chama metodo Up do estado atual
     */
    public void Up(){state.Up();}
    /**
     * Chama metodo Down do estado atual
     */
    public void Down(){state.Down();}
    /**
     * Chama metodo Left do estado atual
     */
    public void Left(){state.Left();}
    /**
     * Chama metodo Right do estado atual
     */
    public void Right(){state.Right();}
    /**
     * Chama metodo Pause do estado atual
     */
    public boolean pause(){return state.pause();}
    /**
     * Chama metodo Resume do estado atual
     */
    public void resume(){state.resume();}

    /**
     * Chama metodo elementVulnerable do Game e retorna o valor por ele retornado
     * @param c simbolo do elemento que pretendemos saber se esta vulneravel ou nao
     * @return game.elementVulnerable(c)
     */
    public boolean elementVulnerable(char c){return game.elementVulnerable(c);}

    /**
     * Chama metodo lostGame do Game e retorna o valor por ele retornado.
     * @return game.lostGame()
     */
    public boolean lostGame(){return game.lostGame();}

    /**
     * Retorna o estado atual
     * @return state.getGameState()
     */
    public EGameState getState(){
        if(state==null)return null;
        return state.getGameState();
    }

    /**
     * Retorna o ultimo estado.
     * @return lastState.getGameState()
     */
    public EGameState getLastState() {
        if (lastState == null) return null;
        return lastState.getGameState();
    }

    /**
     * Retorna o valor de pontos que esta guardado no Game
     * @return game.getPoints();
     */
    public  int getPoints(){return game.getPoints();}

    /**
     * Retorna o numero de vidas que esta guardado no Game
     * @return game.getLives()
     */
    public int getLives(){return game.getLives();}

    /**
     * Retorna o valor de nivel atual que esta guardado no Game
     * @return game.getLevel()
     */
    public int getLevel(){return game.getLevel();}

    /**
     * Retorna conjunto de caracteres que representa o tabuleiro, que esta guardado no Game
     * @return game.getBoard()
     */
    public char [][]getBoard(){return game.getBoard();}

    /**
     * Retorna a direção atual do pacman chamando metodo no Game
     * @return game.getDirection()
     */
    public String getDirection(){
        return (
            switch (game.getDirection()){
                case 0->"Up";
                case 1->"Right";
                case 2->"Down";
                case 3-> "Left";
                default -> " ";
            });
    }

    /**
     * Chama metodo evolve do state atual.
     * @param time tempo em segundos da execução
     * @return verdade se ainda tem vidas falso se ja nao tem vidas
     */
    public boolean evolve(double time){
        if(state.evolve(time)){
            game.moveElements(time);
        }
        return game.getLives()>0;
    }
}
