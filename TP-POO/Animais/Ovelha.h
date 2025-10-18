//
// Created by trafa on 14/12/2022.
//
#include "Animal.h"
#ifndef TRABALHO_1__OVELHA_H
#define TRABALHO_1__OVELHA_H


class Ovelha: public Animal{
public:
    Ovelha(int x,int y, int id,Reserva &r,int saudePai=-1): Animal("Ovelha","o", 4 + rand()%4,x,y,id,r,saudePai),tempoDeReproducao(0){}
    string getAsString() const override;
    Animal* duplica()override;
    void setRedor() override;
    string  move()override;
private:
    int tempoDeReproducao;
};



#endif //TRABALHO_1__OVELHA_H
