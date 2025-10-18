//
// Created by trafa on 14/12/2022.
//

#include "Store.h"


string Store::executa(Reserva &r) {
    int verf=-1;
    ostringstream o;
    string com;
    istringstream linha(comando);
    linha >>com>>nome>> verf; // aux = nome de uma reserva

   if(verf!=-1){
        o<< "Erro nos argumentos!!" << endl;
    }else {
        if(!com.empty()){
            o<< "Comando Valido !!" << endl;
            executaStore(r);
        }else{
            o<< "Falta de argumentos" << endl;
        }
    }
    return o.str();
}
void Store::executaStore(Reserva&r){
    auto it1 = find_if(estados.begin(),estados.end(),[&]( Reserva *a){
        return a->getNome()==nome;
    });
    if(it1==estados.end()) {
        Reserva *r1 = new Reserva(r.getLinhas(), r.getColunas(), nome);
        r.setNome(nome);
        *r1 = r;
        estados.push_back(r1);
    }
}
