//
// Created by trafa on 14/12/2022.
//
#include "Comandos.h"
#ifndef TRABALHO_1__ANIM_H
#define TRABALHO_1__ANIM_H



class Anim:public Comandos{
public:
    Anim(string comando): Comandos(comando){}
    string executa(Reserva&r)override;
private:
};


#endif //TRABALHO_1__ANIM_H
