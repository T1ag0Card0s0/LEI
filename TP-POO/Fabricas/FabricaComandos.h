//
// Created by trafa on 16/12/2022.
//
#include "../Comandos/Anim.h"
#include "../Comandos/CAnimal.h"
#include "../Comandos/Empty.h"
#include "../Comandos/Feed.h"
#include "../Comandos/Food.h"
#include "../Comandos/Info.h"
#include "../Comandos/Kill.h"
#include "../Comandos/Load.h"
#include "../Comandos/N.h"
#include "../Comandos/NoFood.h"
#include "../Comandos/Restore.h"
#include "../Comandos/See.h"
#include "../Comandos/Slide.h"
#include "../Comandos/Store.h"
#include "../Comandos/Visanim.h"
#include "../Comandos/Comandos.h"

#ifndef TRABALHO_1__FABRICACOMANDOS_H
#define TRABALHO_1__FABRICACOMANDOS_H


class FabricaComandos {
public:
    FabricaComandos(string comando,int wr,int hr);
    Comandos *CriaComando(int &xr,int &yr,vector<Reserva*>&estados,Window &zonaR,Window &zonaO);
private:
    string comando;
    int wr,hr;


};


#endif //TRABALHO_1__FABRICACOMANDOS_H
