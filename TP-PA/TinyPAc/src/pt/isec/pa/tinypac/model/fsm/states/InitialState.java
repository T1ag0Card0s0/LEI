package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.log.ModelLog;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

/**
 * InitialState (Estado inicial do jogo)
 * <p>
 *     Aqui é implementada a logica que se pretende no inicio do jogo.
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class InitialState extends GameStateAdapter {
    /**
     * Construtor do InitialState
     * @param context contexto do jogo
     * @param game dados do jogo
     */
    public InitialState(GameContext context, Game game){
        super(context, game);
        ModelLog.getInstance().add(getGameState().toString());
    }

    /**
     * Muda para o estado GAME_STARTED e coloca a direção do pacman para baixo
     */
    @Override
    public void Down() {
        game.setNextDirection(2);
        changeState(EGameState.GAME_STARTED);
    }

    /**
     * Muda para o estado GAME_STARTED e coloca a direção do pacman para cima
     */
    @Override
    public void Up() {
        game.setNextDirection(0);
        changeState(EGameState.GAME_STARTED);
    }

    /**
     * Muda para o estado GAME_STARTED e coloca a direção do pacman para a esquerda
     */
    @Override
    public void Left() {
        game.setNextDirection(3);
        changeState(EGameState.GAME_STARTED);
    }

    /**
     * Muda para o estado GAME_STARTED e coloca a direção do pacman para a direita
     */
    @Override
    public void Right() {
        game.setNextDirection(1);
        changeState(EGameState.GAME_STARTED);
    }

    /**
     * Um avança do jogo vazio, pois nao quero que aconteça nada aqui
     * @param time tempo em segundos do decorrer do jogo
     * @return falso, pois nao acontece nada
     */
    @Override
    public boolean evolve(double time) {
        return false;
    }

    /**
     * Retorna o valor do estado atual
     * @return INITIAL_STATE
     */
    @Override
    public EGameState getGameState(){return EGameState.INITIAL_STATE;}
}

