package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.log.ModelLog;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

/**
 * GamePaused (estado jogo pausado)
 * <p>
 *     Aqui são implementados os métodos correspondentes a estado jogo pausado
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class GamePaused extends GameStateAdapter {
    /**
     * Construtor do estado GamePaused
     * @param context contexto do jogo
     * @param game dados do jogo
     */
    public GamePaused(GameContext context, Game game){
        super(context,game);
        ModelLog.getInstance().add(getGameState().toString());
    }

    /**
     * Resume o jogo voltando para o ultimo estado
     * @return true pois é pretendido que neste estado seja possivel resumir voltando ao ultimo estado
     */
    @Override
    public boolean resume() {
        changeState(context.getLastState());
        return true;
    }

    /**
     * Evolve vazio pois neste estado o jogo nao avança
     * @param time tempo em segundos da execução
     * @return falso, pois nao queremos que ocorra nada aqui
     */
    @Override
    public boolean evolve(double time) {
        return false;
    }

    /**
     * Retorna o estado atual
     * @return GAME_PAUSED
     */
    @Override
    public EGameState getGameState(){return EGameState.GAME_PAUSED;}
}
