//
// Created by trafa on 16/12/2022.
//

#include "FabricaComandos.h"
FabricaComandos::FabricaComandos(string comando,int wr,int hr):comando(comando),wr(wr),hr(hr) {}

Comandos *FabricaComandos::CriaComando(int &xr,int &yr,vector<Reserva*>&estados,Window &zonaR,Window &zonaO) {
    Comandos *b= nullptr;
    string com;
    istringstream linha(comando);
    linha>>com;
    if(com=="animal"){
        b=new CAnimal(comando);
    }else if(com=="kill"||com=="killid"){
        b=new Kill(comando);
    }else if(com=="food"){
        b=new Food(comando);
    }else if(com=="feed"||com=="feedid"){
        b=new Feed(comando);
    }else if(com=="nofood"){
        b=new NoFood(comando);
    }else if(com=="empty"){
        b=new Empty(comando);
    }else if(com=="see"){
        b=new See(comando);
    }else if(com=="info"){
        b=new Info(comando);
    }else if(com=="n"){
        b=new N(comando,xr,yr,wr,hr,zonaR,zonaO);
    }else if(com=="anim"){
        b=new Anim(comando);
    }else if(com=="visanim"){
        b=new Visanim(comando,wr,hr,xr,yr);
    }else if(com=="store"){
        b=new Store(comando,estados);
    }else if(com=="restore"){
        b=new Restore(comando,estados);
    }else if(com=="load"){
        b=new Load(comando,wr,hr,xr,yr,estados,zonaR,zonaO);
    }else if(com=="slide"){
        b=new Slide(comando,xr,yr);
    }
    return b;
}