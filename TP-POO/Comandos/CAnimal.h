//
// Created by trafa on 14/12/2022.
//
#include "Comandos.h"
#ifndef TRABALHO_1__CANIMAL_H
#define TRABALHO_1__CANIMAL_H


class CAnimal:public Comandos{
public:
    CAnimal(string comando): Comandos(comando),l(-1),c(-1){}
    string executa(Reserva &r)override;
    bool validaTipoAnimal();
    bool validaPosicao(Reserva &r);
private:
    string tipo;
    int l,c;
};

#endif //TRABALHO_1__CANIMAL_H
