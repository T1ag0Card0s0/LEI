//
// Created by trafa on 15/11/2022.
//

#ifndef TRABALHO_1__ALIMENTO_H
#define TRABALHO_1__ALIMENTO_H
#include <iostream>
#include <vector>
#include <sstream>
#include <fstream>
using namespace std;
class Reserva;
class Alimento {
public:
    Alimento(string nome,string tipo, int vn, int tx,int x,int y,int identificador,initializer_list<string> cheiros,Reserva &r);
    string getAsString() const;
    int getId()const;
    int getX()const;
    int getY()const;
    string getTipo()const;
    virtual Alimento* duplica()=0;
    bool smellLike(string cheiro)const;
    string getIntData();
    virtual void move()=0;
    bool isDead();
    int getConstantes(string e);
protected:
    int x,y;
    string nome,tipo;
    int VAlimento;
    int valorNutritivo;
    int toxicidade;
    int identificador,tempoDeVida;
    bool died;
    Reserva &r;
    vector<string> cheiros;
};



#endif //TRABALHO_1__ALIMENTO_H
