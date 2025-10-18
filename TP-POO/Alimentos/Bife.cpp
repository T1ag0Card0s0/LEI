//
// Created by trafa on 16/12/2022.
//

#include "Bife.h"
Alimento* Bife::duplica() {
    return new Bife(*this);
}
void Bife::move() {
    if(tempoDeVida>=30){
        died=true;
        return;
    }
    if(valorNutritivo>0){
        valorNutritivo--;
    }
}