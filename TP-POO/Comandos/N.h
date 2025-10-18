//
// Created by trafa on 14/12/2022.
//
#include "Comandos.h"
#ifndef TRABALHO_1__N_H
#define TRABALHO_1__N_H



class N:public Comandos{
public:
    N(string comando,int &xr,int &yr,int wr,int hr,Window &zonaR,Window &zonaO): Comandos(comando),xr(xr),yr(yr),wr(wr),hr(hr),zonaR(zonaR),zonaO(zonaO){}
    string executa(Reserva&r)override;
private:
    int &xr,&yr,wr,hr;
    Window &zonaR,&zonaO;
};

#endif //TRABALHO_1__N_H
