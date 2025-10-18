package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;
import pt.isec.pa.tinypac.ui.gui.util.ExitAlert;

/**
 * BeginUI (ecrã inicial)
 * <p>
 *     Esta classe gere todas as funcionalidades presentes no ecrã inicial
 *     Funcionalidades estas:
 *     <ul>
 *         <li>Iniciar jogo</li>
 *         <li>Ver Top 5</li>
 *         <li>Sair</li>
 *     </ul>
 *     Aqui tambem são mostrados os creditos
 * <p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class BeginUI extends BorderPane {
    /**
     * Gestor do jogo
     */
    private final GameManager gameManager;
    /**
     * Botões que estão presentes no ecrã inicial
     */
    private Button btnInitGame,btnTop5,btnExit;

    /**
     * Construtor do ecrã inicial
     * @param gameManager gestor do jogo
     */
    public BeginUI(GameManager gameManager){
        this.gameManager = gameManager;
        createViews();
        registerHandlers();
        update();
    }

    /**
     * Cria todos o aspetos do ecrã e aplica os estilos
     */
    public void createViews() {
        ImageView imageView = new ImageView(ImageManager.getImage("isec-logo.png"));
        Label label = new Label("""
        DEIS-ISEC-IPC
        Licenciatura Engenharia Informática (LEI)
        Trabalho realizado no âmbito da Unidade Curricular Programação Avançada
        Autor:
            Tiago Rafael Santos Cardoso, 2011138999
        2022/2023""");

        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-text-fill:  grey;");
        VBox vBoxInfo = new VBox(imageView, label);
        vBoxInfo.setAlignment(Pos.CENTER);
        btnInitGame = new Button("Iniciar Jogo");
        btnTop5 = new Button("Top 5");
        btnExit = new Button("Sair");
        btnInitGame.getStyleClass().add("btnInitScreen");
        btnTop5.getStyleClass().add("btnInitScreen");
        btnExit.getStyleClass().add("btnInitScreen");

        ImageView imgView = new ImageView(ImageManager.getImage("title.png"));
        VBox vBox = new VBox(imgView, btnInitGame, btnTop5, btnExit, vBoxInfo);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        this.setCenter(vBox);
    }
    /**
     * Regista todas as interações possiveis presentes no ecrã principal
     */
    public void registerHandlers(){
        gameManager.addPropertyChangeListener(evt -> update());
        btnInitGame.setOnAction( event -> {
            if(gameManager.existSavedGame()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Iniciar Jogo");
                alert.setHeaderText(null);
                alert.setContentText("Existe um jogo guardado deseja carrega-lo?");

                ButtonType buttonTypeYes = new ButtonType("Sim");
                ButtonType buttonTypeNo = new ButtonType("Não");

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                alert.showAndWait().ifPresent(response -> {
                    if (response == buttonTypeNo) {
                        gameManager.start();
                    }else if(response == buttonTypeYes){
                        gameManager.load();
                    }
                });
            }else{
                gameManager.start();
            }
        });
        btnTop5.setOnAction( event -> {
            gameManager.loadTop5();
        });
        btnExit.setOnAction( event -> {
            ExitAlert.show(getScene().getWindow());
        });

    }

    /**
     * Neste caso serve para fazer transições entre ecrãs tornando falso ou verdadeiro a sua visibilidade
     */
    public void update(){
        if(gameManager.FSM_Is_Created()||gameManager.top5IsLoaded()){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }


}
