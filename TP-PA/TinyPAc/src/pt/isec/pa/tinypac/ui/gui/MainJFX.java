package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.gameengine.GameEngine;
import pt.isec.pa.tinypac.model.GameManager;

/**
 * MainJFX (main da interface gráfica)
 * <p>
 *     Aqui são criados ecrãs para interface gráfica do jogo.
 * </p>
 *@author Tiago Cardoso 2021138999
 *@version guiVersion
 */

public class MainJFX extends Application {
    /**
     * Gestor do jogo
     */
    private GameManager gameManager;
    /**
     * Motor de jogo.
     */
    private GameEngine gameEngine;

    /**
     * Inicialização do gestor de jogo e do motor de jogo.
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        super.init();
        gameManager=new GameManager();
        gameEngine = new GameEngine();
    }

    /**
     * Cria uma janela
     * @param stage stage onde vai correr o jogo
     * @param title titulo do stage
     * @param x posição x do ecrã
     * @param y posição y do ecrã
     */
    private void newStage(Stage stage,String title,double x,double y){
        RootPane root = new RootPane(gameManager);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        stage.setScene(new Scene(root,600,600));
        stage.setTitle(title);
        stage.setUserData(gameManager);
        stage.setX(x);
        stage.setY(y);
        stage.setMinWidth(600);
        stage.setMinHeight(700);
        stage.show();
    }

    /**
     * Criação de uma janela para listagem
     * @param x posição x da janela
     * @param y posição y da janela
     */
    public void createListStage(double x,double y){
        Stage stage=new Stage();
        ListPane listPane = new ListPane(gameManager);
        Scene scene = new Scene(listPane,300,400);
        stage.setScene(scene);
        stage.setTitle("Drawing list");
        stage.setX(x);
        stage.setY(y);
        stage.show();
    }

    /**
     * Chamada aos metodos newStage e createListStage para criar janelas. Registo do avanço do jogo e o seu intervalo de tempo.
     * @param stage
     */
    @Override
    public void start(Stage stage){
        newStage(stage,"TinyPac",100,100);
        newStage(new Stage(),"TinyPac#1",stage.getX(),stage.getY()+50);
        newStage(new Stage(),"TinyPac#2",stage.getX(),stage.getY()+100);
        createListStage(stage.getX()+stage.getWidth(),stage.getY());
        gameEngine.registerClient((g,t)-> Platform.runLater(()-> gameManager.evolve(t/1_000_000_000.0)));
        gameEngine.start(200);

    }

    /**
     * Termina o motor de jogo
     */
    @Override
    public void stop(){
        gameEngine.stop();
        gameEngine.waitForTheEnd();
    }
}
