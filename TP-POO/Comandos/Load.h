//
// Created by trafa on 14/12/2022.
//
#include "Comandos.h"
#ifndef TRABALHO_1__LOAD_H
#define TRABALHO_1__LOAD_H

class Load:public Comandos{
public:
    Load(string comando,int wr,int hr,int xr,int yr,vector<Reserva*>&estados,Window &zonaR,Window &zonaO): Comandos(comando),
    wr(wr),hr(hr),xr(xr),yr(yr),estados(estados),zonaR(zonaR),zonaO(zonaO){}
    string executa(Reserva&r)override;
private:
    string nome;
    int wr,hr,xr,yr;
    vector<Reserva*>&estados;
    Window &zonaR,&zonaO;
};



#endif //TRABALHO_1__LOAD_H
