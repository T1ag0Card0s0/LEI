//
// Created by trafa on 16/12/2022.
//
#include "Alimento.h"
#ifndef TRABALHO_1__BIFE_H
#define TRABALHO_1__BIFE_H


class Bife: public Alimento{
public:
    Bife(int x,int y, int id,Reserva &r): Alimento("Bife","b",10,2,x,y,id,{"carne","ketchup"},r){};
    Alimento* duplica()override;
    void move()override;
private:

};


#endif //TRABALHO_1__BIFE_H
