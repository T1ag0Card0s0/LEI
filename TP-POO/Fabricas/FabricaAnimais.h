//
// Created by trafa on 16/12/2022.
//
#include "../Animais/Animal.h"
#include "../Animais/Canguro.h"
#include "../Animais/Coelho.h"
#include "../Animais/Lobo.h"
#include "../Animais/Misterio.h"
#include "../Animais/Ovelha.h"

#ifndef TRABALHO_1__FABRICAANIMAIS_H
#define TRABALHO_1__FABRICAANIMAIS_H


class FabricaAnimais {
public:
    FabricaAnimais(string especie,int x,int y, int id);
    Animal *CriaAnimal(Reserva &r,int idPai=-1);
private:
    int x,y, id;
    string especie;
};


#endif //TRABALHO_1__FABRICAANIMAIS_H
