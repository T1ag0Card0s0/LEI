//
// Created by trafa on 14/11/2022.
//
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include "Alimentos/Alimento.h"
#include "Biblioteca/Terminal.h"
#include "Fabricas/FabricaAnimais.h"
#include "Fabricas/FabricaAlimentos.h"
#ifndef TRABALHO_1__RESERVA_H
#define TRABALHO_1__RESERVA_H
using namespace std;
using namespace term;
class Reserva {
public:
    Reserva(const int NL,const int NC,string nome);
    Reserva(Reserva &o);
    ~Reserva();
    void insereAnimal(string especie,int x=-1,int y=-1);
    void insereFilhote(Animal* a);
    void insereAlimento(string nomeAlimento, int vn,int tx,int x = -1,int y = -1);
    void insereAlimento(Alimento *a);
    string mostraInfoAnimais();
    string mostraAnimaisVisiveis(int wr, int hr,int xr,int yr);
    string findTipo(int x,int y)const;
    void mostraReservaVisivel(int xr, int yr,int wr,int hr,Window &zonaR) const;
    void eliminaAnimal(int x, int y=-1);
    void eliminaAlimento(int x,int y=-1);
    void foiComido(int idd);
    string getRedor(int xmin,int xmax,int ymin,int ymax,int idd,string cheiro)const;
    const string getNome()const;
    string getInfoById(int num);
    string getInfoByPosition(int x,int y);
    const int getLinhas()const;
    const int getColunas()const;
    int setNewId();
    void setNome(string n);
    string feed(int ptn,int ptt,int x,int y=-1);
    string n();
    void DeuAVolta(int &i,int &j)const;
    Reserva &operator=(const Reserva&b);
private:
    int id = 0;
    string nome;
    const int NL;
    const int NC;
    vector<Animal*> animais;
    vector<Animal*>filhotes;
    vector<Animal*>mortos;
    vector<Alimento*> alimento;
    vector<Alimento*> novoAlimento;
    vector<Alimento*> alimentoMorto;
};
#endif //TRABALHO_1__RESERVA_H
