//
// Created by trafa on 16/12/2022.
//

#include "FabricaAnimais.h"
FabricaAnimais::FabricaAnimais(string especie,int x,int y, int id):especie(especie),x(x),y(y),id(id){}

Animal *FabricaAnimais::CriaAnimal(Reserva &r,int idPai) {
    if(especie == "c"){
        return new Coelho(x,y, id,r);
    }else if(especie == "l"){
        return new Lobo(x,y, id,r);
    }else if(especie == "o"){
        return new Ovelha(x,y, id,r);
    }else if(especie == "g"){
        return new Canguro(x,y, id,idPai,r);
    }else if(especie == "m"){
        return new Misterio(x,y, id,r);
    }
    return nullptr;
}