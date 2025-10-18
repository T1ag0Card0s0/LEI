//
// Created by trafa on 14/12/2022.
//
#include "Comandos.h"
#ifndef TRABALHO_1__VISANIM_H
#define TRABALHO_1__VISANIM_H



class Visanim:public Comandos{
public:
    Visanim(string comando,int wr,int hr,int xr,int yr): Comandos(comando),wr(wr),hr(hr),xr(xr),yr(yr){}
    string executa(Reserva&r)override;
private:
    int wr, hr,xr,yr;
};


#endif //TRABALHO_1__VISANIM_H
