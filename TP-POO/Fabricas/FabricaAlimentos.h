//
// Created by trafa on 16/12/2022.
//
#include "../Alimentos/Alimento.h"
#include "../Alimentos/AlimentoMisterio.h"
#include "../Alimentos/Bife.h"
#include "../Alimentos/Cenoura.h"
#include "../Alimentos/Corpo.h"
#include "../Alimentos/Relva.h"
#ifndef TRABALHO_1__FABRICAALIMENTOS_H
#define TRABALHO_1__FABRICAALIMENTOS_H


class FabricaAlimentos {
public:
    FabricaAlimentos(string nomeAlimento,int x,int y, int id,int vn,int tx);
    Alimento *CriaAlimento(Reserva &r);
private:
    string nomeAlimento;
    int x,y,id,vn,tx;
};


#endif //TRABALHO_1__FABRICAALIMENTOS_H
