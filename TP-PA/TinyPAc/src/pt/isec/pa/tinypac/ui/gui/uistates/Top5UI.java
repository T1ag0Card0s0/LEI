package pt.isec.pa.tinypac.ui.gui.uistates;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.tinypac.model.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageManager;

import java.util.List;
/**
 * Top5UI (ecrã da lista top5 pontuações)
 * <p>
 *     Esta classe representa o painel onde é mostrada as cinco melhores pontuações e o correspondente nome de utilizador.
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class Top5UI extends BorderPane {
    /**
     * Gestor de jogo
     */
    private final GameManager gameManager;
    /**
     * Tabela para mostrar as pontuações
     */
    private GridPane gridPane;
    private Button btnBack;

    /**
     * Construtor do Top5UI
     * @param gameManager gestor do jogo
     */
    public Top5UI(GameManager gameManager) {
        this.gameManager = gameManager;

        createViews();
        registerHandlers();
        update();
    }
    /**
     * Cria todos o aspetos do ecrã e aplica os estilos
     */
    public void createViews() {
        ImageView imgView = new ImageView(ImageManager.getImage("title.png"));
        Label lblTitle = new Label("TOP 5");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setStyle("-fx-text-fill:rgb(252, 201, 46);-fx-font-size: 40px; -fx-font-weight: bold;");
        Label nome = new Label("Nome:");
        Label pontuacao = new Label("Pontuação:");
        nome.setPadding(new Insets(10));
        pontuacao.setPadding(new Insets(10));

        gridPane = new GridPane();
        gridPane.setMaxWidth(200);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color: #f2f2f2; -fx-background-radius: 20;");

        btnBack = new Button("Voltar");
        btnBack.getStyleClass().add("btnInitScreen");
        VBox vBoxCenter = new VBox(10);
        vBoxCenter.getChildren().addAll(imgView, lblTitle, gridPane, btnBack); // Adiciona o botão ao VBox
        vBoxCenter.setAlignment(Pos.CENTER);
        this.setCenter(vBoxCenter);
    }

    /**
     * Aqui regista todas as possiveis interações neste painel.
     */
    public void registerHandlers() {
        gameManager.addPropertyChangeListener(evt -> update());
        btnBack.setOnAction(evt -> gameManager.closeTop5());

    }

    /**
     * Serve para fazer transições entre ecrãs tornando falso ou verdadeiro a sua visibilidade.
     * Aqui também é atualizada a lista top5.
     */
    public void update() {
        if (!gameManager.top5IsLoaded() || gameManager.FSM_Is_Created()) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
        gridPane.getChildren().clear();
        Label lblNomeTitle = new Label("Nome:");
        Label lblPontuacaoTitle = new Label("Pontuação:");
        lblNomeTitle.setPadding(new Insets(10));
        lblPontuacaoTitle.setPadding(new Insets(10));
        gridPane.add(lblNomeTitle, 0, 0);
        gridPane.add(lblPontuacaoTitle, 1, 0);
        List<String> top5 = gameManager.getTop5();
        for (int i = 0; i < top5.size(); i++) {
            String[] parts = top5.get(i).split(":");
            String nome = parts[0].trim();
            String pontuacao = parts[1].trim();
            Label lblNome = new Label(nome);
            Label lblPontuacao = new Label(pontuacao);
            GridPane.setHalignment(lblNome, Pos.CENTER.getHpos());GridPane.setValignment(lblNome, Pos.CENTER.getVpos());
            GridPane.setHalignment(lblPontuacao, Pos.CENTER.getHpos());GridPane.setValignment(lblPontuacao, Pos.CENTER.getVpos());
            gridPane.add(lblNome, 0, i+1 );
            gridPane.add(lblPontuacao, 1, i +1);
        }

        gridPane.setAlignment(Pos.CENTER);
    }
}
