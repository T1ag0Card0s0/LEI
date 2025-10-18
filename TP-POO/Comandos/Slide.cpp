//
// Created by trafa on 14/12/2022.
//

#include "Slide.h"

string Slide::executa(Reserva &r) {
    ostringstream o;
    int verf=-1;
    string  com;
    istringstream linha(comando);
    linha >> com>>direcao >> value;
    if((direcao != 'u' && direcao != 'd' && direcao != 'r' && direcao != 'l')||linha.fail() || value < 0){
        o << "slide <d/u/r/l > <value> (baixo, cima, direita, esquerda)\n" << endl;
    }else{
        if(direcao == 'u'&& yr - value>=0){
            yr-=value;
            o << "Comando Valido" << endl;

        }else if(direcao == 'd'&& yr + value < r.getLinhas()) {
           yr += value;
            o << "Comando Valido" << endl;

        }else if(direcao=='r'&& xr + value < r.getColunas()) {
            xr += value;
           o<< "Comando Valido" << endl;
        }else if(direcao=='l'&& xr - value>=0){
            xr-=value;
           o << "Comando Valido" << endl;
        }
    }
    return o.str();

}
