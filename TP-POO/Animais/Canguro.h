//
// Created by trafa on 14/12/2022.
//
#include "Animal.h"
#ifndef TRABALHO_1__CANGURO_H
#define TRABALHO_1__CANGURO_H


class Canguro: public Animal{
public:
    Canguro(int x, int y, int id,int idPai,Reserva &r): Animal("Canguro","g",10,x,y,id,r),tempoDeReproducao(0),idPai(idPai),perigo(false),tempoBolsa(0),naBolsa(false){}
    string getAsString() const override;
    Animal* duplica()override;
    void setRedor() override;
    string move()override;
private:
    int tempoDeReproducao,idPai,tempoBolsa;
    bool perigo,naBolsa;
};

#endif //TRABALHO_1__CANGURO_H
