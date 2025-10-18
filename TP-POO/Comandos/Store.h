//
// Created by trafa on 14/12/2022.
//
#include "Comandos.h"
#ifndef TRABALHO_1__STORE_H
#define TRABALHO_1__STORE_H


class Store:public Comandos{
public:
    Store(string comando,vector<Reserva*>&estados): Comandos(comando),estados(estados){}
    string executa(Reserva&r)override;
    void executaStore(Reserva&r);
private:
    string nome;
    vector<Reserva*>&estados;
};


#endif //TRABALHO_1__STORE_H
