//
// Created by trafa on 14/12/2022.
//
#include "Comandos.h"
#ifndef TRABALHO_1__FOOD_H
#define TRABALHO_1__FOOD_H

class Food:public Comandos{
public:
    Food(string comando): Comandos(comando),l(-1),c(-1){}
    string executa(Reserva&r)override;
    bool validaTipoAlimento();
    bool validaPosicao(Reserva&r);

private:
    string tipo;
    int l,c;
};

#endif //TRABALHO_1__FOOD_H
