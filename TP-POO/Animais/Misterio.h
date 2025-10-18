//
// Created by trafa on 14/12/2022.
//
#include "Animal.h"
#ifndef TRABALHO_1__MISTERIO_H
#define TRABALHO_1__MISTERIO_H


class Misterio: public Animal{
public:
Misterio(int x,int y, int id,Reserva &r): Animal("Misterio","m",0,x,y,id,r){}
    string getAsString() const override;
    Animal* duplica()override;
    void setRedor() override;
    string  move()override;
private:
};



#endif //TRABALHO_1__MISTERIO_H
