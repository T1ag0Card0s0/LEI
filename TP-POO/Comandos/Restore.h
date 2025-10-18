//
// Created by trafa on 14/12/2022.
//
#include "Comandos.h"
#ifndef TRABALHO_1__RESTORE_H
#define TRABALHO_1__RESTORE_H



class Restore:public Comandos{
public:
    Restore(string comando,vector<Reserva*>&estados): Comandos(comando),estados(estados){}
    string executa(Reserva&r)override;
    void executaRestore(Reserva&r);
private:
    string nome;
    vector<Reserva*>&estados;
};


#endif //TRABALHO_1__RESTORE_H
