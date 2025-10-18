//
// Created by trafa on 14/12/2022.
//
#include "Comandos.h"
#ifndef TRABALHO_1__SLIDE_H
#define TRABALHO_1__SLIDE_H



class Slide:public Comandos{
public:
    Slide(string comando,int &xr,int &yr): Comandos(comando),xr(xr),yr(yr){}
    string executa(Reserva&r)override;
private:
    char direcao;
    int value,&xr,&yr;
};


#endif //TRABALHO_1__SLIDE_H
