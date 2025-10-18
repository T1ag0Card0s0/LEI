//
// Created by trafa on 14/12/2022.
//
#include "Comandos.h"
#ifndef TRABALHO_1__EMPTY_H
#define TRABALHO_1__EMPTY_H



class Empty:public Comandos{
public:
    Empty(string comando): Comandos(comando){}
    string executa(Reserva&r)override;
private:
    int l,c;
};


#endif //TRABALHO_1__EMPTY_H
