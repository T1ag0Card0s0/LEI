    package pt.isec.pa.tinypac.ui.gui.util;

    import javafx.application.Platform;
    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.layout.*;
    import javafx.stage.Popup;
    import javafx.stage.PopupWindow;
    import javafx.stage.Window;
    import pt.isec.pa.tinypac.model.GameManager;
    import pt.isec.pa.tinypac.model.fsm.EGameState;
    import pt.isec.pa.tinypac.ui.gui.resources.CSSManager;

    /**
     * PausePopup (popup de pausa de jogo)
     * <p>
     *     Aqui é criado um popup que mostra duas opções:
     *     <ul>
     *         <li>
     *             Resumir
     *         </li>
     *         <li>
     *             Salvar e Sair
     *         </li>
     *     </ul>
     * <p>
     *@author Tiago Cardoso 2021138999
     *@version guiVersion
     */
    public class PausePopup {
        private PausePopup() {}

        /**
         * Metodo estatico que mostra um popup onde é possivel resumir o jogo, ou salvar e sair. Na segunda opção é chamado
         * o metodo show da classe InsertNamePopup.
         * @param owner janela onde se pretende mostrar o popup.
         * @param gameManager gestor do jogo para resumir o jogo ou sair dele.
         */
        public static void show(Window owner, GameManager gameManager) {
            Popup popup = new Popup();
            popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_LEFT);
            popup.setAutoHide(true);
            BorderPane toastPane = new BorderPane();
            CSSManager.applyCSS(toastPane,"styles.css");
            toastPane.getStyleClass().add("popup");

            Label titleLabel = new Label("Jogo pausado");
            titleLabel.getStyleClass().add("popup_label");
            BorderPane.setAlignment(titleLabel, Pos.CENTER); // Centralizar o título
            toastPane.setTop(titleLabel);

            Button resumeButton = new Button("Resumir");
            Button saveAndExitButton = new Button("Salvar e Sair");
            resumeButton.getStyleClass().add("btnPopUp");
            saveAndExitButton.getStyleClass().add("btnPopUp");
            HBox buttonPane = new HBox(10, resumeButton, saveAndExitButton);
            buttonPane.setPadding(new Insets(10));
            buttonPane.setAlignment(Pos.CENTER);
            toastPane.setCenter(buttonPane);
            saveAndExitButton.setOnAction(event -> {
                popup.hide();
                InsertNamePopup.show(owner,gameManager,titleLabel.getText());
            });

            resumeButton.setOnAction(event->{
                gameManager.resume();
            });
            gameManager.addPropertyChangeListener(evt -> {
                if(gameManager.getState()!= EGameState.GAME_PAUSED||gameManager.getState()==null) {
                    popup.hide();
                }
            });
            popup.getContent().add(toastPane);
            popup.show(owner);
            Platform.runLater(() -> {
                popup.setX(owner.getX() + (owner.getWidth() - toastPane.getWidth()) / 2);
                popup.setY(owner.getY() + (owner.getHeight() - toastPane.getHeight()) / 2);
            });
        }
    }

