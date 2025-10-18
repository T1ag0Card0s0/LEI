//
// Created by trafa on 14/12/2022.
//

#include "NoFood.h"


string NoFood::executa(Reserva &r) {
    int verf=-1;
    ostringstream o("Erro nos argumentos");
    string com;
    istringstream linha(comando);
    linha>>com>> l >> c>>verf;
    if(verf==-1&&validaPosicao(r)){
        r.eliminaAlimento(l,c);
        o << "Comando Valido!" << endl;
    }else if(verf==-1&&l>0){
        o << "Comando Valido!" << endl;
        r.eliminaAlimento(l,c);
    }else{
        o<<R"(remover alimento:
        ->nofood <linha> <coluna>
        ->nofood <ID> (so parametro - assume que e o ID))";
    }
    return o.str();
}
bool NoFood::validaPosicao(Reserva &r){
    return  l<r.getColunas()  && c <r.getLinhas() && l>=0 && c>=0;
}