//
// Created by trafa on 14/12/2022.
//

#include "Kill.h"
string Kill::executa(Reserva &r) {
    string com;
    ostringstream o;
    int verf=-1;
    istringstream linha(comando);
    linha>>com>> l >> c;
    r.eliminaAnimal(l,c);
    if(validaPosicao(r)){
        linha>>verf;
        if(verf==-1){
            r.eliminaAnimal(l,c);
            o<<"Comando Valido!!";
        }else{
            o<< R"(matar animal:
                    ->kill <linha> <coluna> (identifica por posicao)
            ->killid <id> (identifica por ID))";
        }
    }
    return o.str();
}
bool Kill::validaPosicao(Reserva &r){
    return  ((l<=r.getColunas() && l>=0)||l==-1)  && (c <=r.getLinhas() && c>=0);
}
