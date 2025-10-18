package pt.isec.pa.tinypac.model.fsm.states;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.data.log.ModelLog;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;
import pt.isec.pa.tinypac.model.fsm.GameStateAdapter;

/**
 * Vulnerable (estado vulneravel)
 * <p>
 *     Aqui é implementada a logica pretendida no estado vulneravel
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class Vulnerable extends GameStateAdapter {
    /**
     * Construtor do estado vulneravel
     * @param context contexto do jogo
     * @param game dados do jogo
     */
    public Vulnerable(GameContext context, Game game) {
        super(context, game);
        ModelLog.getInstance().add(getGameState().toString());
    }

    /**
     * Coloca a direção do pacman para baixo
     */
    @Override
    public void Down() {game.setNextDirection(2);}
    /**
     * Coloca a direção do pacman para cima
     */
    @Override
    public void Up() {game.setNextDirection(0);}
    /**
     * Coloca a direção do pacman para a esquerda
     */
    @Override
    public void Left() {game.setNextDirection(3);}
    /**
     * Coloca a direção do pacman para a direita
     */
    @Override
    public void Right() {game.setNextDirection(1);}

    /**
     * Aqui é feita uma iteração do jogo
     * @param time tempo em segundos da execução
     * @return verdade pois é feita uma execução
     */
    @Override
    public boolean evolve(double time) {
        if(game.levelEnded()){
            changeState(EGameState.INITIAL_STATE);
        }else if(!game.pacmanHasPower()){
            changeState(EGameState.GAME_STARTED);
        }
        return true;
    }

    /**
     * Aqui é pausado o jogo mudando de estado para GAME_PAUSED
     * @return verdade pois muda sempre de estado
     */
    @Override
    public boolean pause() {
        changeState(EGameState.GAME_PAUSED);
        return true;
    }

    /**
     * Retorna o estado atual
     * @return VULNERABLE
     */
    @Override
    public EGameState getGameState(){return EGameState.VULNERABLE;}
}
