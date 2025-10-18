//
// Created by trafa on 14/12/2022.
//
#include "../Fabricas/FabricaComandos.h"
#include "Load.h"

string Load::executa(Reserva &r) {
    string com,aux;
    int verf=-1;
    ostringstream o;
    istringstream linha(comando);
    linha >> com>>aux >> verf; // aux = nome de uma reserva

    if(verf!=-1){
        o << "Erro nos argumentos!!" << endl;
    }else {
        if(!aux.empty()){
            ifstream f;
            f.open(aux);

            if(!f){
                o << "Erro ao abrir ficheiro!"<< endl;
                return o.str();
            }

            while(getline(f, comando)){
               FabricaComandos fa(comando,wr,hr);
                Comandos *c=fa.CriaComando(xr,yr,estados,zonaR,zonaO);
                if(c!= nullptr) {
                    o<< c->executa(r);
                    delete c;
                }
            }
        }else {
            o<< "Falta de argumentos" << endl;
        }
    }
    return o.str();
}
