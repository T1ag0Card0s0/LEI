//
// Created by trafa on 16/12/2022.
//
#include "Alimento.h"
#ifndef TRABALHO_1__CENOURA_H
#define TRABALHO_1__CENOURA_H


class Cenoura: public Alimento{
public:
    Cenoura(int x,int y, int id,Reserva &r): Alimento("Cenoura","t",4,0,x,y,id,{"verdura"},r),tempoDeAumentarTx(0){}
    Alimento* duplica()override;
    void move()override;
private:
    int tempoDeAumentarTx;

};


#endif //TRABALHO_1__CENOURA_H
