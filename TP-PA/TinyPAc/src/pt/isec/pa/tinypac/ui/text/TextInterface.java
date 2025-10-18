package pt.isec.pa.tinypac.ui.text;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import pt.isec.pa.tinypac.model.fsm.GameContext;

import java.io.IOException;

public class TextInterface {
    GameContext fsm;
    private TextGraphics textGraphics;
    private Terminal terminal;
    public TextInterface(GameContext game) {
        this.fsm=game;
        this.terminal=null;
        this.textGraphics=null;
    }
    public void createWindow() {
        try {
            TerminalSize size = new TerminalSize(50, 50);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(size);
            terminalFactory.setTerminalEmulatorTitle("pt.isec.pa.tinypac.TinyPAc");
            terminal = terminalFactory.createTerminal();
            terminal.enterPrivateMode();
            terminal.clearScreen();
            terminal.setCursorVisible(false);
            textGraphics = terminal.newTextGraphics();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public TextColor getColor(char c){
        return (
                switch (c){
                    case 'x'-> TextColor.ANSI.BLUE;
                    case 'W'->TextColor.ANSI.WHITE;
                    case '.', 'O' -> TextColor.ANSI.YELLOW;
                    case 'F', 'Y' ->TextColor.ANSI.MAGENTA;
                    case 'P'->new TextColor.RGB(255,255,0);
                    case 'p'-> new TextColor.RGB(222,161,133);
                    case 'b'-> TextColor.ANSI.RED;
                    case 'c'->new TextColor.RGB(255,140,0);
                    case 'i'->new TextColor.RGB(0,255,255);
                    default -> TextColor.ANSI.BLACK;
                }
        );
    }
    public void readKeyBoard(){
        boolean finish = false;
        try {
            while (!finish) {
                KeyStroke keyStroke = terminal.pollInput();
                if (keyStroke == null) continue;
                if(keyStroke.getKeyType()!= KeyType.Character)
                    switch (keyStroke.getKeyType()) {
                        case ArrowDown -> fsm.Down();
                        case ArrowLeft -> fsm.Left();
                        case ArrowUp -> fsm.Up();
                        case ArrowRight -> fsm.Right();
                        case Escape -> finish = true;
                }else {
                    if (keyStroke.getCharacter() == ' ') {
                        fsm.pause();
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void show() {
        try {
            int i = 10, j = 5;
            for (char[] arr : fsm.getBoard()) {
                for (char c : arr) {
                    if(fsm.elementVulnerable(c))
                        textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
                    else textGraphics.setBackgroundColor(getColor(c));
                    textGraphics.putString(i, j, c + "");
                    i++;
                }
                j++;
                i=10;
            }
            textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
            textGraphics.putString(0,fsm.getBoard().length+i,fsm.getState().toString());
            textGraphics.putString(0,fsm.getBoard().length+i+1,"Points:"+fsm.getPoints());
            textGraphics.putString(0,fsm.getBoard().length+i+2,"Lives:"+fsm.getLives());

            terminal.flush();
        }catch (IOException e){e.printStackTrace();}

    }
}
