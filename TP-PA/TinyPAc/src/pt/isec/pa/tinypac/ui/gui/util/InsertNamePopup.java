package pt.isec.pa.tinypac.ui.gui.util;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Window;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.CSSManager;

/**
 * InsertNamePopup (insere user name)
 * <p>
 *     Aqui é criado um popup que pede ao utilizador o seu username
 * </p>
 *@author Tiago Cardoso 2021138999
 *@version guiVersion
 */
public class InsertNamePopup {
    private InsertNamePopup() {}

    /**
     * Metodo estatico que cria um popup onde é pedido o nome do utilizador.
     * @param owner janela onde deve aparecer o popup
     * @param gameManager gestor do jogo
     * @param title titulo do popup
     */
    public static void show(Window owner, GameManager gameManager,String title) {
        Popup popup = new Popup();
        popup.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_LEFT);
        popup.setAutoHide(true);
        BorderPane toastPane = new BorderPane();
        CSSManager.applyCSS(toastPane, "styles.css");
        toastPane.getStyleClass().add("popup");

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("popup_label");
        BorderPane.setAlignment(titleLabel, Pos.CENTER); // Centralizar o título
        toastPane.setTop(titleLabel);

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Introduza o seu nome");
        Button okButton = new Button("OK");
        VBox namePane = new VBox(nameTextField, okButton);
        namePane.setPadding(new Insets(10));
        okButton.getStyleClass().add("btnPopUp");
        namePane.setAlignment(Pos.CENTER);
        okButton.setOnAction(event -> {
            if (nameTextField.getText().length() > 0) {
                gameManager.addTop5(nameTextField.getText());
                gameManager.saveTop5();
                gameManager.save();
                gameManager.exit();
                popup.hide();
            }
        });

        toastPane.setCenter(namePane);
        popup.getContent().add(toastPane);
        popup.show(owner);
        Platform.runLater(() -> {
            popup.setX(owner.getX() + (owner.getWidth() - toastPane.getWidth()) / 2);
            popup.setY(owner.getY() + (owner.getHeight() - toastPane.getHeight()) / 2);
            nameTextField.requestFocus();
        });
    }


}

