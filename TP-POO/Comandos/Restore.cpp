//
// Created by trafa on 14/12/2022.
//

#include "Restore.h"

string Restore::executa(Reserva &r) {
    string com;
    int verf=-1;
    ostringstream o;
    istringstream linha(comando);
    linha >> com >> nome>>verf; // aux = nome de uma reserva
    if(verf!=-1){
        o << "Erro nos argumentos!!" << endl;
    }else {
        if(!nome.empty()){
            executaRestore(r);
        }else
            o << "Falta de argumentos" << endl;
    }
    return o.str();
}
void Restore::executaRestore(Reserva &r){
    estados.erase(remove_if(estados.begin(), estados.end(), [&]( Reserva *a) {
        if (a->getNome() == r.getNome()) {
            delete a;
            return true;
        }
        return false;
    }), estados.end());
    auto it = find_if(estados.begin(), estados.end(), [&]( Reserva *a) {
        return a->getNome() == nome;
    });
    if (it != estados.end()) {
        r = **it;
        r.setNome(nome);
    }
}
