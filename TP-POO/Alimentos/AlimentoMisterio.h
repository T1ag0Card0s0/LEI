//
// Created by trafa on 16/12/2022.
//
#include "Alimento.h"
#ifndef TRABALHO_1__ALIMENTOMISTERIO_H
#define TRABALHO_1__ALIMENTOMISTERIO_H
#define LIMITE_ITERACOES 5

class AlimentoMisterio: public Alimento{
public:
    AlimentoMisterio(int x,int y, int id,Reserva &r): Alimento("AlimentoMisterio","a",0,0,x,y,id,{"carne","ketchup","verdura","erva"},r){}
    Alimento* duplica()override;
    void move()override;
private:
};


#endif //TRABALHO_1__ALIMENTOMISTERIO_H
