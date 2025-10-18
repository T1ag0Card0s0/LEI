package pt.isec.pa.tinypac.ui.gui.resources;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.HashMap;

/**
 * ImageManager (imagens presentes no projeto)
 * <p>
 *     Esta classe guarda e gere as imagens que se pretendem ser inseridas no projeto
 * </p>
 *@author Tiago Cardoso 2021138999
 *@version guiVersion
 */
public class ImageManager {
    private ImageManager() { }

    /**
     * Referencia para a lista de imagens associada ao seu nome de ficheiro
     */
    private static final HashMap<String, Image> images = new HashMap<>();

    /**
     * Obtem uma imagem consoante o seu nome de ficheiro que esteja num recurso interno
     * @param filename nome do ficheiro correspondente à imagem pretendida
     * @return Image imagem pedida
     */
    public static Image getImage(String filename) {
        Image image = images.get(filename);
        if (image == null)
            try (InputStream is = ImageManager.class.getResourceAsStream("images/"+filename)) {
                image = new Image(is);
                images.put(filename,image);
            } catch (Exception e) {
                return null;
            }
        return image;
    }

    /**
     *Obtem uma imagem consoante o seu nome de ficheiro que esteja num recurso externo
     *@param filename nome do ficheiro correspondente à imagem pretendida
     *@return Image imagem pedida
     */
    public static Image getExternalImage(String filename) {
        Image image = images.get(filename);
        if (image == null)
            try {
                image = new Image(filename);
                images.put(filename,image);
            } catch (Exception e) {
                return null;
            }
        return image;
    }
}