//
// Created by trafa on 14/12/2022.
//
#include "Comandos.h"
#ifndef TRABALHO_1__INFO_H
#define TRABALHO_1__INFO_H



class Info:public Comandos{
public:
    Info(string comando): Comandos(comando){}
    string executa(Reserva&r)override;
private:
    int id;
};


#endif //TRABALHO_1__INFO_H
