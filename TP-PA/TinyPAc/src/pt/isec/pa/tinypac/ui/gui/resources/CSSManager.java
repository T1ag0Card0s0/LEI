package pt.isec.pa.tinypac.ui.gui.resources;

import javafx.scene.Parent;

/**
 * CSSManager (estilos do projeto)
 * <p>
 *     Esta classe serve para aplicar os estilos a um certo painel a partir de um ficheiro css
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class CSSManager {
    private CSSManager() { }

    /**
     * Aplica o css a um determinado parent
     * @param parent parent a aplicar os estilos do ficheiro css
     * @param filename nome do ficheiro css a aplicar ao parrent
     */
    public static void applyCSS(Parent parent, String filename) {
        var url = CSSManager.class.getResource("css/"+filename);
        if (url == null)
            return;
        String fileCSS = url.toExternalForm();
        parent.getStylesheets().add(fileCSS);
    }
}
