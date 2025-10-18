//
// Created by trafa on 14/12/2022.
//
#include "Animal.h"
#ifndef TRABALHO_1__COELHO_H
#define TRABALHO_1__COELHO_H


class Coelho: public Animal{
public:
    Coelho(int x,int y, int id,Reserva &r): Animal("Coelho","c", 1 + rand()%3,x,y,id,r),tempoDeReproducao(0){}
    string getAsString() const override;
    Animal* duplica()override;
    void setRedor() override;
    string  move()override;
private:
    int tempoDeReproducao;
};



#endif //TRABALHO_1__COELHO_H
