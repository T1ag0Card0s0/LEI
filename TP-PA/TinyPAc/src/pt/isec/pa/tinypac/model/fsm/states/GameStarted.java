package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.log.ModelLog;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

/**
 * GameStarted (jogo a decorrer)
 * <p>
 *     Aqui é implementada toda a logica que se pretende ter no decorrer do jogo
 * </p>
 * @author  Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class GameStarted extends GameStateAdapter {
    /**
     * Construtor do GameStarted
     * @param context contexto do jogo
     * @param game dados do jogo
     */
    public GameStarted(GameContext context, Game game){
        super(context, game);
        ModelLog.getInstance().add(getGameState().toString());
    }

    /**
     * Muda a direção do pacman para baixo
     */
    @Override
    public void Down() {
        game.setNextDirection(2);
    }

    /**
     * Muda a direção do pacman para cima
     */
    @Override
    public void Up() {
        game.setNextDirection(0);
    }

    /**
     * Muda a direção do pacman para a esquerda
     */
    @Override
    public void Left() {
        game.setNextDirection(3);
    }

    /**
     * Muda a direção do pacman para a direita
     */
    @Override
    public void Right() {
        game.setNextDirection(1);
    }

    /**
     * Aqui é feita uma iteração do jogo
     * @param time tempo em segundos da execução
     * @return true, pois aqui acontece um evolve
     */
    @Override
    public boolean evolve(double time) {
        if(game.levelEnded()){
            changeState(EGameState.INITIAL_STATE);
        }else if(game.pacmanHasPower()){
            changeState(EGameState.VULNERABLE);
        }
        return true;
    }

    /**
     * Pausa o jogo mudando de estado
     * @return true, pois a sua chamada pausa sempre o jogo
     */
    @Override
    public boolean pause() {
        changeState(EGameState.GAME_PAUSED);
        return true;
    }

    /**
     * Retorna o estado atual.
     * @return GAME_STARTED
     */
    @Override
    public EGameState getGameState(){return EGameState.GAME_STARTED;}
}
