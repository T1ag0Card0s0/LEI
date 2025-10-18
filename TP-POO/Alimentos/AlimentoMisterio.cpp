//
// Created by trafa on 16/12/2022.
//
#include "../Reserva.h"
#include "AlimentoMisterio.h"
Alimento* AlimentoMisterio::duplica() {
    return new AlimentoMisterio(*this);
}

void AlimentoMisterio::move() {
    tempoDeVida++;
    if(tempoDeVida==20){
        tempoDeVida=0;
        int xreproducao = x - 4 + rand() % 8,yreproducao = y - 4 + rand() % 8;
        r.DeuAVolta(xreproducao,yreproducao);
        r.insereAlimento(new AlimentoMisterio(xreproducao,yreproducao,r.setNewId(),r));
    }
}