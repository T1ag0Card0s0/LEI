package pt.isec.pa.tinypac.model.data.game;

import pt.isec.pa.tinypac.model.data.element.Element;
import pt.isec.pa.tinypac.model.data.maze.MazeInfo;
import pt.isec.pa.tinypac.model.data.moveableElements.MoveableElement;
import pt.isec.pa.tinypac.model.data.moveableElements.ghost.Ghost;
import pt.isec.pa.tinypac.model.data.moveableElements.pacman.PacMan;

import java.io.*;

/**
 * Game (onde os dados sao geridos)
 * <p>
 *     Aqui é onde ocorre toda a logica aplicada ao jogo em si.
 * </p>
 * @author TiagoCardoso 2021138999
 * @version guiVersion
 */
public class Game implements Serializable {
    private int ticks;
    private MazeInfo mazeInfo;
    private int level;
    private int points;
    private int lives;
    private int eatenFood;
    private double timeRegister;
    private char pacManAteSymbol;
    private String lastLevelFile;
    /**
     * Construtor do jogo
     */
    public Game(){
        this.ticks=0;
        this.level=1;
        this.points=0;
        this.lives=3;
        this.eatenFood=0;
        this.timeRegister=0;
        initMazeInfo();
    }

    /**
     * Preenche o labirinto através de um ficheiro de texto
     */
    void initMazeInfo(){
        int width = 0, height = 0;
        try {
            File file = new File(getLevelFilePath());
            if(!file.exists()){
                file=new File(lastLevelFile);
            }else{
                lastLevelFile = getLevelFilePath();
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                width=line.length();
                height++;
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        mazeInfo = new MazeInfo(height,width);
        try {
            File file = new File(getLevelFilePath());
            if(!file.exists()){
                file=new File(lastLevelFile);
            }
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            int c;
            int x = 0, y = 0;
            while ((c = br.read()) != -1) {
                if((char)c!='\n')
                    mazeInfo.setInitElements(new Element((char) c,y,x));
                x++;
                if ((char) c == '\n') {
                    y++;
                    x = 0;
                }
            }
            br.close();
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Obtém o nome do ficheiro de texto que contém o labirinto correspondente ao nivel.
     * @return String caminho do ficheiro de texto do labirinto
     */
    private String getLevelFilePath(){
        if(level<10)return "Levels\\Level0"+level+".txt";
        return "Levels\\Level"+level+".txt";
    }

    /**
     * Obtem conjunto de caracteres do labirinto.
     * @return  char[][] tabuleiro
     */
    public char[][]getBoard(){return mazeInfo.getBoard();}

    /**
     * Logica do movimento dos elementos que se movem
     * @param time tempo de execução em segundos
     */
    public void moveElements(double time){
        for(MoveableElement element: mazeInfo.getMoveableElements()){
            if(element.getUnderElement()!=null)
                mazeInfo.set(element.getUnderElement().getY(),element.getUnderElement().getX(),element.getUnderElement());
            else
                mazeInfo.set(element.getY(),element.getX(),null);
            element.move();
            pacManAteSymbol=mazeInfo.pacManAte();
            mazeInfo.set(element.getY(),element.getX(),element);
            if(element instanceof PacMan pacMan) {
                switch (pacManAteSymbol) {
                    case 'F' -> points += level * 25;
                    case '.' ->{
                        eatenFood++;
                        points++;
                        pacMan.setUnderElement(null);
                    }
                    case 'b','c','i','p'->{
                        if(pacMan.getPower()) points += mazeInfo.getNumOfEatenGhost() * 50;
                        else{
                            lives--;
                            mazeInfo.initMoveableElements();
                        }
                    }
                    case 'O' ->{
                        eatenFood++;
                        timeRegister=time;
                        mazeInfo.setVulnerable(true);
                        pacMan.setPower(true);
                        points += 10;
                        pacMan.setUnderElement(null);
                    }
                    case 'W' -> mazeInfo.teleTransportPacMan();
                }
                if(pacMan.getPower()&&time-timeRegister>5.0){
                    mazeInfo.setVulnerable(false);
                    pacMan.setPower(false);
                }
            }
            if(element instanceof Ghost ghost){
                if(ghost.getLocked()&&time-timeRegister>5.0){
                    ghost.setLocked(false);
                }else if(time-timeRegister<5.0){
                    ghost.setLocked(true);
                }
                if(pacmanHasPower()&&pacManAteSymbol==ghost.getSymbol())
                        points += mazeInfo.getNumOfEatenGhost() * 50;
                else if(pacManAteSymbol==ghost.getSymbol()) {
                    lives--;
                    mazeInfo.initMoveableElements();
                }

            }
        }
        if(timeRegister==0)
            timeRegister=time;
        ticks++;
    }

    /**
     * Verifica se o nivel acabou, tanto por vencer como por perder.
     * @return verdade se acabou, falso se nao acabou
     */
    public boolean levelEnded(){
        if(mazeInfo.getNumOfFood()-eatenFood==0){
            if(level<20) {
                level++;
                ticks = 0; eatenFood = 0;
                initMazeInfo();
            }
            return true;
        }
        if(!mazeInfo.getPacManPower()&&
             (pacManAteSymbol=='c'||pacManAteSymbol=='p'||
                     pacManAteSymbol=='b'||pacManAteSymbol=='i')&&lives>0) {
                mazeInfo.initMoveableElements();
                return true;
        }
        return false;
    }

    /**
     * Saber se o pacman tem poderes ou nao.
     * @return verdade, tem poderes, falso, nao tem poderes
     */
    public boolean pacmanHasPower(){return mazeInfo.getPacManPower();}

    /**
     * Saber se um certo fantasma esta vulneravel através de uma pesquisa do seu simbolo
     * @param c simbolo do fantasma que pretendemos saber se esta vulneravel ou nao
     * @return verdade se estiver vulneravel, falso se nao estiver vulneravel, ou se o simbolo do parametro nao for simbolo de  nenhum fantasma
     */
    public boolean elementVulnerable(char c){

        if(mazeInfo.getMoveableElement(c) instanceof Ghost ghost){
            return ghost.getVulnerable();
        }
        return false;
    }

    /**
     * Diz se perdeu o jogo ou nao.
     * @return verdade se perdeu o jogo, falso se nao perdeu
     */
    public boolean lostGame(){return getLives()<=0;}

    /**
     * Obtém o nivel atual
     * @return int nivel
     */
    public int getLevel() {return level;}

    /**
     * Obtém os pontos que o jogador ja acumolou ate ao momento
     * @return int pontos
     */
    public int getPoints(){return points;}

    /**
     * Obtém as vidas que o jogoados ainda tem.
     * @return int vidas.
     */
    public int getLives(){return lives;}

    /**
     * Obtém a direção atual do pacman
     * @return int direção
     */
    public int getDirection(){return mazeInfo.getPacManDirection();}

    /**
     * Coloca a proxima direção do pacman a pretendida.
     * @param nextDirection proxima direção pretendida.
     */
    public void setNextDirection(int nextDirection){mazeInfo.setPacManNextDirection(nextDirection);}

}
