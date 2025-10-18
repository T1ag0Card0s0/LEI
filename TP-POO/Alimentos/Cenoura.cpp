//
// Created by trafa on 16/12/2022.
//

#include "Cenoura.h"
Alimento* Cenoura::duplica() {
    return new Cenoura(*this);
}
void Cenoura::move() {
    tempoDeAumentarTx++;
    if(tempoDeAumentarTx==10&&toxicidade<3){
        toxicidade++;
    }
}