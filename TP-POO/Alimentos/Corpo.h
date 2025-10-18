//
// Created by trafa on 16/12/2022.
//
#include "Alimento.h"
#ifndef TRABALHO_1__CORPO_H
#define TRABALHO_1__CORPO_H


class Corpo: public Alimento{
public:
    Corpo(int x,int y,int vn,int tx, int id,Reserva &r): Alimento("Corpo","p",vn,tx,x,y,id,{"carne"},r),valorNutritivoInicial(vn){}
    Alimento* duplica()override;
    void move()override;
private:
    int valorNutritivoInicial;
};


#endif //TRABALHO_1__CORPO_H
