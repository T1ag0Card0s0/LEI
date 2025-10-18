package pt.isec.pa.tinypac.ui.gui.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

/**
 * ExitAlert (confirmação de saida)
 * <p>
 *     Aqui é criado um alert para confirmar a saida do utilizador.
 * </p>
 *@author Tiago Cardoso 2021138999
 *@version guiVersion
 *
 */
public class ExitAlert {
    private ExitAlert() {}

    /**
     * Metodo estatico que cria o Alert e o mostra. Nele é questionado ao utilizador se quer realmente sair ou não.
     * @param owner janela onde é pretendido mostrar o alert.
     */
    public static void show(Window owner) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Saída");
        alert.setHeaderText(null);
        alert.setContentText("Deseja sair?");

        alert.initOwner(owner);

        ButtonType buttonYes = new ButtonType("Sim");
        ButtonType buttonNo = new ButtonType("Não");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonYes) {
                Platform.exit();
            } else {
                alert.close();
            }
        });
    }
}
