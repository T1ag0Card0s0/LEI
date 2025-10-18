package pt.isec.pa.tinypac.model.fsm;

import java.io.Serializable;

/**
 * IGameState (interface de estados)
 * <p>
 *     Aqui sao criados a assinatura dos metodos que se pretende que os estados implementem.
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public interface IGameState extends Serializable {
    /**
     * Metodo que retorna um valor do tipo EGameState
     * @return EGameState
     */
    EGameState getGameState();

    /**
     * Metodo para mudar de direção a implementar na classe que vai realizar esta interface
     */
    void Up();
    /**
     * Metodo para mudar de direção a implementar na classe que vai realizar esta interface
     */
    void Down();
    /**
     * Metodo para mudar de direção a implementar na classe que vai realizar esta interface
     */
    void Left();
    /**
     * Metodo para mudar de direção a implementar na classe que vai realizar esta interface
     */
    void Right();
    /**
     * Metodo para pausar a implementar na classe que vai realizar esta interface
     */
    boolean pause();
    /**
     * Metodo para resumir a implementar na classe que vai realizar esta interface
     */
    boolean resume();
    /**
     * Metodo para correr um instante do jogo a implementar na classe que vai realizar esta interface
     */
    boolean evolve(double time);
}
