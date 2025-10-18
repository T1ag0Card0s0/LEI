//
// Created by trafa on 15/11/2022.
//

#ifndef TRABALHO_1__ANIMAL_H
#define TRABALHO_1__ANIMAL_H
#include <iostream>
#include <sstream>
#include <list>
#include <vector>
#include <fstream>
#include "../HistoricoAlimento.h"
using namespace std;
class Reserva;
class Animal {
public:
    Animal(string especie,string tipo,int peso,int x,int y,int identificador,Reserva &r,int saudePai=-1);
    virtual string getAsString() const=0;
    int getId()const;
    int getX()const;
    int getY()const ;
    string getTipo()const;
    int getpeso()const;
    virtual Animal* duplica()=0;
    void comer(int ptNutricao,int ptToxicidade,string tipodecomida,int idAlimento);
    string getHist();
    virtual void setRedor()=0;
    virtual string move()=0;
    void RandomSentido();
    void Segue(int x1,int y1);
    void Foge(int x1,int y1);
    bool IsDead();
    int getConstantes(string e);

protected:
    int x,y,raioMovimento;
    string especie,tipo;
    int identificador,tempoDeVida;
    int peso;
    int saude;
    int VAnimal;
    int fome;
    int tempoParalizado;
    bool died;
    bool freeze;
    Reserva &r;
    vector<string>redor;
private:
    vector <HistoricoAlimento> hist;
};



#endif //TRABALHO_1__ANIMAL_H
