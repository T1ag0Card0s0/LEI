package pt.isec.pa.tinypac.model.fsm;

import pt.isec.pa.tinypac.model.data.game.Game;
import pt.isec.pa.tinypac.model.fsm.states.GamePaused;
import pt.isec.pa.tinypac.model.fsm.states.InitialState;
import pt.isec.pa.tinypac.model.fsm.states.Vulnerable;
import pt.isec.pa.tinypac.model.fsm.states.GameStarted;

/**
 * EGameState (enumeração de estados)
 * <p>
 *     Aqui são enumerados os estados possiveis do jogo
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public enum EGameState {

    INITIAL_STATE,GAME_STARTED,GAME_PAUSED,VULNERABLE;

    /**
     * toString para transmitir informação do estado atual
     * @return  String "nome do estado"
     */
    @Override
    public String toString() {
        return(
                switch (this){
                    case INITIAL_STATE -> "Game Initialized";
                    case GAME_STARTED -> "Game Started";
                    case GAME_PAUSED -> "Game Paused";
                    case VULNERABLE -> "Vulnerable";
                }
        );
    }

    /**
     * Cria uma nova instancia de estado
     * @param context contexto do jogo
     * @param game dados do jogo
     * @return instancia do estado correspondente ao valor da enumeração
     */
    public IGameState createState(GameContext context, Game game){
        return(
                switch (this){
                    case INITIAL_STATE -> new InitialState(context,game);
                    case GAME_STARTED -> new GameStarted(context,game);
                    case GAME_PAUSED -> new  GamePaused(context,game);
                    case VULNERABLE -> new Vulnerable(context,game);
                }
        );
    }
}