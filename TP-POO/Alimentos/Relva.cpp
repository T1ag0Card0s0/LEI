//
// Created by trafa on 14/12/2022.
//

#include "Relva.h"
#include "../Reserva.h"
Alimento* Relva::duplica() {
    return new Relva(*this);
}
void Relva::move() {
    tempoDeVida++;
    if(tempoDeVida==VAlimento){
        died=true;
        return;
    }
    if(tempoDeVida==(VAlimento*75)/100){
        int xreproducao = x - 8 + rand() % 12,yreproducao = y - 8 + rand() % 12;
        if(rand()%2==0){
            xreproducao = x + 4 + rand() % 4;
        }
        if(rand()%2==0){
            yreproducao = y + 4 + rand() % 4;
        }
        r.DeuAVolta(xreproducao,yreproducao);
        if(r.findTipo(xreproducao,yreproducao)=="_|")
            r.insereAlimento(new Relva(xreproducao,yreproducao,r.setNewId(),r));
    }

}