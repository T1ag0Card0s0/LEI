//
// Created by trafa on 16/12/2022.
//

#include "Corpo.h"
Alimento* Corpo::duplica() {
    return new Corpo(*this);
}
void Corpo::move() {
    valorNutritivo--;
    if(toxicidade<=2*valorNutritivoInicial)
        toxicidade++;
}