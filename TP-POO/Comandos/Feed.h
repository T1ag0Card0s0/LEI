//
// Created by trafa on 14/12/2022.
//
#include "Comandos.h"
#ifndef TRABALHO_1__FEED_H
#define TRABALHO_1__FEED_H


class Feed:public Comandos{
public:
    Feed(string comando): Comandos(comando){}
    string executa(Reserva&r)override;
    string getErro();
private:
    int l,c;
};

#endif //TRABALHO_1__FEED_H
