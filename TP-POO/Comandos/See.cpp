//
// Created by trafa on 14/12/2022.
//

#include "See.h"

string See::executa(Reserva &r) {
    int verf=-1;
    string com;
    ostringstream o("Erro nos argumentos");
    istringstream linha(comando);
    linha>> com>>l >> c;
    if(!linha.fail()&&l<=r.getColunas()  && c <=r.getLinhas() && l>=0 && c>=0){
        linha>>verf;
        if(verf==-1) {
            o.clear();
            o<<r.getInfoByPosition(l,c);
        }
    }else{
        o<< R"(ver posicao:
        ->see <linha> <coluna> (identifica por posicao))";
    }
    return o.str();
}