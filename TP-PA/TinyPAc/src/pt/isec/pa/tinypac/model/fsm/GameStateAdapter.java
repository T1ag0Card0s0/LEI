package pt.isec.pa.tinypac.model.fsm;


import pt.isec.pa.tinypac.model.data.game.Game;

/**
 * GameStateAdapter (adaptador de estaddos)
 * <p>
 *     Aqui são implementados os metodos da inteface IGameState, e um metodo de troca de estado.
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class GameStateAdapter implements IGameState{
    protected Game game;
    protected GameContext context;

    /**
     * Troca de estado.
     * @param eState novo estado.
     */
    protected void changeState(EGameState eState){
        context.changeState(eState.createState(context,game));
    }

    /**
     * Construtor do GameStateAdapter
     * @param context contexto do jogo
     * @param game dados do jogo
     */
    public GameStateAdapter(GameContext context,Game game){
        this.game=game;
        this.context=context;
    }

    /**
     * Retorna null pois é pretendido que cada estado implemente este metdo.
     * @return null
     */
    @Override
    public EGameState getGameState() {
        return null;
    }

    /**
     * Metodo Up vazio, pois cada estado deve implementa-lo ou nao.
     */
    @Override
    public void Up() {

    }

    /**
     * Metodo Down vazio, pois cada estado deve implementa-lo ou nao.
     */
    @Override
    public void Down() {

    }
    /**
     * Metodo Left vazio, pois cada estado deve implementa-lo ou nao.
     */
    @Override
    public void Left() {

    }
    /**
     * Metodo Right vazio, pois cada estado deve implementa-lo ou nao.
     */
    @Override
    public void Right() {

    }

    /**
     * Retorna falso pois cada estado deve implementa-lo ou não mudando o seu valor de retorno.
     * @return false
     */
    @Override
    public boolean pause() {
        return false;
    }
    /**
     * Retorna falso pois cada estado deve implementa-lo ou não mudando o seu valor de retorno.
     * @return false
     */
    @Override
    public boolean resume() {
        return false;
    }

    /**
     *  Retorna falso pois cada estado deve implementa-lo ou não mudando o seu valor de retorno.
     * @param time tempo em segundos da execução
     * @return falso
     */
    @Override
    public boolean evolve(double time) {
        return false;
    }
}
