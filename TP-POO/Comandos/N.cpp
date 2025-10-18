//
// Created by trafa on 14/12/2022.
//

#include "N.h"
string N::executa(Reserva &r) {
    istringstream linha(comando);
    ostringstream o,aux;
    string co;
    int verf=-1;
    int x=-1, y=-1;
    linha >>co>> x >> y >> verf;
    if(verf!=-1){
        o<< "Erro nos argumentos!!" << endl;
    }else if(y != -1){
        if(x >0 && y > 0){
            string com;
            for(int i = 0;i<x;i++){
                zonaO.clear();
                zonaR << set_color(0) << move_to(0, 0);
                r.mostraReservaVisivel(xr,yr,wr,hr,zonaR);
                zonaO<< set_color(0)<< move_to(0,0);
                zonaO<<r.n();
                napms(y*1000);
            }
        }else{
            o<< "Erro nos argumentos!!" << endl;
        }
    }else if(x != -1){
        if(x > 0 && y==-1){
            for(int i = 0;i<x;i++){
                zonaO.clear();
                zonaR << set_color(0) << move_to(0, 0);
                r.mostraReservaVisivel(xr,yr,wr,hr,zonaR);
                zonaO<< set_color(0)<< move_to(0,0);
                zonaO<<r.n();
                napms(1000);
            }
            zonaO.clear();
        }else{
            o << "Erro nos argumentos!!" << endl;
        }
    }else if(x == -1){
        o <<r.n();
        return o.str();
    }
    return "";
}