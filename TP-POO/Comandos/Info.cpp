//
// Created by trafa on 14/12/2022.
//

#include "Info.h"

string Info::executa(Reserva &r) {
    string com;
    int verf=-1;
    ostringstream o;
    istringstream linha(comando);
    linha>>com>> id;
    if(!linha.fail()||id>=0){
        linha>>verf;
        if (verf == -1) {
            o << r.getInfoById(id);
        }else{
            o<<R"( ver informacao acerca de uma elemento do simulador (animal ou alimento):
  ->info <ID>)";
        }
    }else{
        o<<R"( ver informacao acerca de uma elemento do simulador (animal ou alimento):
  ->info <ID>)";
    }
    return o.str();
}
