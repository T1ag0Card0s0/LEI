//
// Created by trafa on 29/11/2022.
//

#ifndef TRABALHO_1__SIMULADOR_H
#define TRABALHO_1__SIMULADOR_H
#include "Reserva.h"
#include "Biblioteca/Terminal.h"
#include "Biblioteca/curses.h"
#include <fstream>
#include <iostream>
#include <string>
#include <cstdlib>
using namespace std;
using namespace term;
class Simulador {
public:
    Simulador(Reserva r,int wr,int hr);
    ~Simulador();
    int introduzComandos(int &xr,int &yr);
    int validaComandos(string &comando,int& xr, int &yr,string &output,Window &zonaR,Window &zonaO);
    void ficheiroConstantes();
    void iniciaSimulacao();
    void mostraOutputVisivel(string &output,Window &zonaO,int y);
    void helper(string&output);
private:
    Reserva r;
    vector<Reserva*> estados;
    int wr,hr;
};


#endif //TRABALHO_1__SIMULADOR_H
