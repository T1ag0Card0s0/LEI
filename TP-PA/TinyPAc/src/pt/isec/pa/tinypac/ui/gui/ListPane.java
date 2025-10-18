package pt.isec.pa.tinypac.ui.gui;

import javafx.scene.control.ListView;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.data.log.ModelLog;

/**
 * ListPane (lista de informações do jogo).
 * <p>
 *     Aqui é criado uma lista onde vai ser colocado os estados em que o jogo está atualmente.
 * </p>
 *@author Tiago Cardoso 2021138999
 *@version guiVersion
 */
public class ListPane extends ListView<String> {
    /**
     * Gestor do jogo.
     */
    private final GameManager gameManager;

    /**
     * Construtor do ListPane.
     * @param gameManager gestor do jogo
     */
    public ListPane(GameManager gameManager){
        this.gameManager=gameManager;
        createViews();
        registerHadlers();
        update();
    }

    public void createViews(){}

    /**
     * Regista a ação de atualização da lista.
     */
    public void registerHadlers(){
        gameManager.addPropertyChangeListener( evt ->update());
    }

    /**
     * Limpa e atualiza a lista.
     */
    public void update(){
        this.getItems().clear();
        this.getItems().addAll(ModelLog.getInstance().getLog());
    }

}
