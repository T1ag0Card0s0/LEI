package pt.isec.pa.tinypac.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.CSSManager;
import pt.isec.pa.tinypac.ui.gui.uistates.BeginUI;
import pt.isec.pa.tinypac.ui.gui.uistates.GameOverUI;
import pt.isec.pa.tinypac.ui.gui.uistates.GameUI;
import pt.isec.pa.tinypac.ui.gui.uistates.Top5UI;

/**
 * RootPane (painel raiz)
 * <p>
 *     Painel raiz onde são criados todos os paineis que constituem a aplicação.
 * </p>
 *@author Tiago Cardoso 2021138999
 *@version guiVersion
 */

public class RootPane extends BorderPane {
    /**
     * Gestor do jogo.
     */
    private final GameManager gameManager;

    /**
     * Construtor do RootPane.
     * @param gameManager gestor do jogo.
     */
    public RootPane(GameManager gameManager){
        this.gameManager=gameManager;
        createViews();
    }

    /**
     * Aqui são criados os paineis que constituem a aplicação e adicionados a um stackpane para gestao de paineis.
     * Coloca-se o fundo a preto.
     */
    private void createViews() {
        CSSManager.applyCSS(this,"styles.css");
        StackPane stackPane = new StackPane(
                new BeginUI(gameManager),
                new GameUI(gameManager),
                new Top5UI(gameManager),
                new GameOverUI(gameManager)
        );
        stackPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setCenter(stackPane);
    }

}
