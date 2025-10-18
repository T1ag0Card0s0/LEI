//
// Created by trafa on 14/12/2022.
//
#include "Alimento.h"
#ifndef TRABALHO_1__RELVA_H
#define TRABALHO_1__RELVA_H


class Relva: public Alimento{
public:
    Relva(int x,int y, int id,Reserva &r): Alimento("Relva","r",3,0,x,y,id,{"erva","verdura"},r){}
    Alimento* duplica()override;
    void move()override;
private:
};


#endif //TRABALHO_1__RELVA_H
