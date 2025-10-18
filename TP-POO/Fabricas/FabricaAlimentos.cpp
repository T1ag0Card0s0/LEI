//
// Created by trafa on 16/12/2022.
//

#include "FabricaAlimentos.h"
FabricaAlimentos::FabricaAlimentos(string nomeAlimento, int x, int y, int id,int vn,int tx):nomeAlimento(nomeAlimento),x(x),y(y), id(id),vn(vn),tx(tx){}

Alimento *FabricaAlimentos::CriaAlimento(Reserva&r) {
    Alimento *a= nullptr;
    if(nomeAlimento=="r"){
        a=new Relva(x,y, id,r);
    }else if(nomeAlimento == "t"){
        a=new Cenoura(x,y, id,r);
    }else if(nomeAlimento=="b"){
        a=new Bife(x,y, id,r);
    }else if(nomeAlimento=="a"){
        a=new AlimentoMisterio(x,y, id,r);
    }
    return a;
}