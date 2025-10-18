package pt.isec.pa.tinypac.model.data.log;

import java.util.ArrayList;
import java.util.List;

/**
 * ModelLog (Informações do jogo)
 * <p>
 *     Aqui são guardadas informação do jogo em formato de texto.
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
// singleton
public class ModelLog {
    /**
     * Instancia da classe
     */
    private static ModelLog _instance=null;

    /**
     * Retorna a sua instancia.
     * @return ModelLog sua instancia.
     */
    public static ModelLog getInstance() {
        if (_instance == null)
            _instance = new ModelLog();
        return _instance;
    }

    /**
     * Lista de informação que se pretende guardar
     */
    protected ArrayList<String> log;

    /**
     * Construtor privado
     */
    private ModelLog() {
        log = new ArrayList<>();
    }

    /**
     * Limpa a lista de informações
     */
    public void reset() {
        log.clear();
    }

    /**
     * Adiciona um elemento à lista de informações
     * @param msg novo elemento que pretende-se adicionar à lista
     */
    public void add(String msg) {
        log.add(msg);
    }

    /**
     * Retorna uma copia da lista de informações guardadas ate agora
     * @return informaçoes
     */
    public List<String> getLog() {
        return new ArrayList<>(log);
    }
}
