//
// Created by trafa on 14/12/2022.
//
#include "Comandos.h"
#ifndef TRABALHO_1__NOFOOD_H
#define TRABALHO_1__NOFOOD_H


class NoFood:public Comandos{
public:
    NoFood(string comando): Comandos(comando),l(-1),c(-1){}
    string executa(Reserva&r)override;
    bool validaPosicao(Reserva &r);
private:
    int l,c;
};


#endif //TRABALHO_1__NOFOOD_H
