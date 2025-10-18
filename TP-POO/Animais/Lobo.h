//
// Created by trafa on 14/12/2022.
//
#include "Animal.h"
#ifndef TRABALHO_1__LOBO_H
#define TRABALHO_1__LOBO_H


class Lobo:public Animal {
public:
    Lobo(int x,int y, int id,Reserva &r): Animal("Lobo","l",15,x,y,id,r),tempoDeReproducao(5+rand()%(VAnimal-5)){}
    string getAsString() const override;
    Animal* duplica()override;
    void setRedor() override;
    string  move()override;
private:
    int tempoDeReproducao;
};



#endif //TRABALHO_1__LOBO_H
