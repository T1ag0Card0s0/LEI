package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.model.data.log.ModelLog;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

/**
 * GameOverUI (ecrã de fim de jogo)
 * <p>
 *     Este ecrã torna-se visivel assim que o jogador fique sem vidas.
 *     Aqui apenas é mostrada a pontuação e é pedido o seu nome de utilizador.
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class GameOverUI extends BorderPane {
    /**
     * Gestor do jogo
     */
    private final GameManager gameManager;
    private Label lblTitle,lblPts;
    private TextField nameInput;
    private Button btnOk;

    /**
     * Construtor do painel de fim de jogo
     * @param gameManager gestor do jogo
     */
    public GameOverUI(GameManager gameManager){
        this.gameManager = gameManager;
        createViews();
        registerHandlers();
        update();
    }
    /**
     * Cria todos o aspetos do ecrã e aplica os estilos
     */
    public void createViews(){
        ImageView gameOver = new ImageView(ImageManager.getImage("game-over.png"));
        lblPts = new Label("Pontuação: 0");
        lblTitle = new Label("Introduza um username");
        lblTitle.setId("game_overTitle");
        lblPts.setId("game_overTitle");
        nameInput = new TextField();
        nameInput.setId("game_overInput");
        btnOk = new Button("Submeter");
        btnOk.getStyleClass().add("btnInitScreen");
        VBox vBoxLabels = new VBox(lblPts,lblTitle);
        vBoxLabels.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(gameOver,vBoxLabels,nameInput,btnOk);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);
        this.setTop(vBox);
    }
    /**
     * Regista todas as interações possiveis presentes no ecrã de fim de jogo
     */
    public void registerHandlers(){
        gameManager.addPropertyChangeListener(evt -> update());
        btnOk.setOnAction(event->{
            if(nameInput.getText().length()>0) {
                ModelLog.getInstance().reset();
                gameManager.addTop5(nameInput.getText());
                gameManager.saveTop5();
                gameManager.deleteFileGame();
                gameManager.exit();
            }else{
                lblTitle.setStyle("-fx-font-size: 25px;-fx-text-fill: red");
            }
        });
    }

    /**
     * Serve para fazer transições entre ecrãs tornando falso ou verdadeiro a sua visibilidade.
     * Aqui tambem é atualizada a informação da quantidade de pontos com que o jogoador terminou o jogo.
     */
    public void update(){
        if (!gameManager.FSM_Is_Created()) {
            this.setVisible(false);
            return;
        }
        if(!gameManager.lostGame()){
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
        lblPts.setText("Pontuação: "+gameManager.getPoints());
    }
}
