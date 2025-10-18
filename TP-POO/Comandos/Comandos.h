//
// Created by trafa on 13/12/2022.
//
#include <iostream>
#include <sstream>
#include "../Reserva.h"
#ifndef TRABALHO_1__COMANDOS_H
#define TRABALHO_1__COMANDOS_H
using namespace std;

class Comandos {
public:
    Comandos(string comando);
    virtual string executa(Reserva &r)=0;
protected:
     string comando;
};

#endif //TRABALHO_1__COMANDOS_H
