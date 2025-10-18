package pt.isec.pa.tinypac.model;

import pt.isec.pa.tinypac.model.data.top5.Top5Players;
import pt.isec.pa.tinypac.model.fsm.EGameState;
import pt.isec.pa.tinypac.model.fsm.GameContext;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GameManager (gestor de jogo)
 * <p>
 *     Aqui faz-se toda a gestão e manipulação do jogo
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class GameManager {
    public static final String FILE_GAME = "files/game.dat";
    private static final String FILE_TOP5 = "files/top5.dat";
    private GameContext fsm;
    private Top5Players top5Players;
    PropertyChangeSupport pcs;

    /**
     * Construtor do GameManager
     */
    public GameManager(){
        this.fsm = null;
        this.top5Players=null;
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Adiciona uma propriedade ao property change listener
     * @param listener propriedade
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Chama metodo Up do contexto
     */
    public void Up(){fsm.Up();}
    /**
     * Chama metodo Down do contexto
     */
    public void Down(){fsm.Down();}
    /**
     * Chama metodo Left do contexto
     */
    public void Left(){fsm.Left();}
    /**
     * Chama metodo Right do contexto
     */
    public void Right(){fsm.Right();}

    /**
     * Chama metodo pause do contexto e assinala o property change listener caso o jogo seja pausado
     * @return verdade se pausar falso se nao pausar
     */
    public boolean pause(){
        if(fsm.pause()) {
            pcs.firePropertyChange(null, null, null);
            return true;
        }
        return false;
    }

    /**
     * Resume o jogo e assianala o property change listener
     */
    public void resume(){fsm.resume();pcs.firePropertyChange(null,null,null);}

    /**
     * Coloca o contexto a null e assinala o property change listener
     */
    public void exit(){
        fsm=null;pcs.firePropertyChange(null,null,null);
    }

    /**
     * Cria um contexto novo do jogo e assinala o property change listener
     */
    public void start(){fsm = new GameContext(); pcs.firePropertyChange(null,null,null);}

    /**
     * Retorna se perdeu o jogo ou nao.
     * @return verdade se perdeu o jogo falso se perdeu o jogo ou se o contexto ainda nao estiver criado.
     */
    public boolean lostGame(){
        if(fsm == null)return false;
        return fsm.lostGame();
    }

    /**
     * Verifica se um elemento está vulnerável ou nao.
     * @param c simbolo do elemento a verificar vulnerabilidade
     * @return  fsm.elementVulnerable(c), fsm corresponde ao contexto.
     */
    public boolean elementVulnerable(char c){return fsm.elementVulnerable(c);}

    /**
     * Obtém o estado do contexto do jogo.
     * @return EGameState fsm.getState(), fsm corresponde ao contexto
     */
    public EGameState getState(){if(fsm==null)return null; return fsm.getState();}

    /**
     * Retorna o numero de pontos acomulados ate ao momento.
     * @return 0 se o contexto nao existir, caso exista chama o metodo getPoints do contexto.
     */
    public  int getPoints(){
        if(fsm == null)return 0;
        return fsm.getPoints();
    }

    /**
     * Numero de vidas do jogador.
     * @return int  fsm.getLives()
     */
    public int getLives(){return fsm.getLives();}

    /**
     * Nivel em que o jogador está a jogar.
     * @return int fsm.getLevel()
     */
    public int getLevel(){return fsm.getLevel();}

    /**
     * Obtém o conjunto de caracteres que representam o tabuleiro.
     * @return char [][] fsm.getBoard()
     */
    public char [][]getBoard(){return fsm.getBoard();}

    /**
     *Obtém a direção atual do pacman.
     * @return String fsm.getDirection()
     */
    public String getDirection(){return fsm.getDirection();}

    /**
     * Retorna se o contexto existe ou nao.
     * @return boolean  fsm!=null
     */
    public boolean FSM_Is_Created(){return fsm!=null;}

    /**
     * Chama o metodo evolve do contexto e retorna o seu valor, e  aciona o property change.
     * @param time tempo de execução da iteração.
     * @return  fsm.evolve(time)
     */
    public boolean evolve(double time){
        if(fsm==null)return false;
        pcs.firePropertyChange(null,null,null);
        return fsm.evolve(time);
    }

    /**
     * Salva o contexto do jogo.
     * @return true se foi guardado com sucesso, falso se nao foi guardado com sucesso.
     */
    public boolean save(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_GAME))){
            oos.writeObject(fsm);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Carrega o contexto do jogo a partir de um ficheiro .dat e assinala o property change
     * @return verdade se foi carregado com sucesso, falso em caso de insucesso.
     */
    public boolean load(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_GAME))){
            fsm = (GameContext) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        pcs.firePropertyChange(null,null,null);
        return true;
    }

    /**
     * Elimina o ficheiro .dat que guarda o contexto do jogo.
     * @return verdadeiro em caso de sucesso, falso em caso de insucesso.
     */
    public boolean deleteFileGame(){
        File file = new File(FILE_GAME);
        return file.delete();
    }

    /**
     * Adiciona um nome e uma pontuação à lista top5 jogadores
     * @param name nome do jogador a adcionar
     */
    public void addTop5(String name){
        if(top5Players==null)loadTop5();
        top5Players.addPlayer(name,getPoints());
    }

    /**
     * Obtém lista top5.
     * @return lista de strings com informação dos 5 melhores jogadores
     */
    public List<String> getTop5(){return new ArrayList<>(top5Players.getTop5());}

    /**
     * Devolve se a lista dos 5 melhores foi carregada ou nao.
     * @return top5Players!=null
     */
    public boolean top5IsLoaded(){return top5Players!=null;}

    /**
     * Carrega a lista dos 5 melhores jogadores.
     * @return verdade se foi carregadaa a lista com sucesso, falso em caso de insucesso.
     */
    public boolean loadTop5(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_TOP5))){
            top5Players = (Top5Players) ois.readObject();
        }catch (Exception e){
            top5Players=new Top5Players();
            return false;
        }
        pcs.firePropertyChange(null,null,null);
        return true;
    }

    /**
     * Guarda a lista dos 5 melhores jogadores num ficheiro .dat e assinala o property change
     * @return verdade se foi guardado com sucesso, falso em caso de insucesso.
     */
    public boolean saveTop5(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_TOP5))){
            oos.writeObject(top5Players);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        pcs.firePropertyChange(null,null,null);
        return true;
    }

    /**
     * Coloca valor null no top5 e assinala o property change.
     */
    public void closeTop5(){top5Players=null;        pcs.firePropertyChange(null,null,null);}

    /**
     * Verifica se existe ou não um jogo guardado.
     * @return verdade se existe jogo guardado, falso se não existe jogo guardado
     */
    public boolean existSavedGame() {
        File arquivo = new File(FILE_GAME);
        return arquivo.exists();
    }
}
