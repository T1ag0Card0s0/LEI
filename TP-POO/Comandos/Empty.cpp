//
// Created by trafa on 14/12/2022.
//

#include "Empty.h"


string Empty::executa(Reserva &r) {
    int verf=-1;
    string com;
    ostringstream o("Erro nos argumentos");
    istringstream linha(comando);
    linha>>com>> l >> c;
    if(!linha.fail()&&l<=r.getColunas()&& c <= r.getLinhas() && l >= 0 && c >= 0){
        linha>>verf;
        if(verf==-1){
            o.clear();
            o << "Comando Valido !!" << endl;
            r.eliminaAnimal(l, c);
            r.eliminaAlimento(l, c);
        }else{
            o<<R"( eliminar o que quer que esteja numa posicao:
  ->empty <linha> <coluna>)";
        }
    }else
        o<<R"( eliminar o que quer que esteja numa posicao:
  ->empty <linha> <coluna>)";
    return o.str();
}
