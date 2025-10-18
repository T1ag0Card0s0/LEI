//
// Created by trafa on 15/11/2022.
//

#include "Animal.h"
#include "../Reserva.h"
Animal::Animal(string especie,string tipo,int peso,int x,int y,int identificador,Reserva &r,int saudePai):
especie(especie),tipo(tipo),peso(peso),x(x),y(y),fome(0),died(false),r(r),freeze(false),tempoParalizado(0),identificador(identificador),
tempoDeVida(0){
    if(saudePai<=0)
        saude= getConstantes("S"+especie);
    else
        saude=saudePai;
    VAnimal= getConstantes("V"+especie);
}
int Animal::getConstantes(string e){
    ifstream f;
    f.open("constantes.txt");
    if(!f){
        return 0;
    }
    while(!f.eof()){
        string constante;
        string letra;
        int value;
        f >> constante >> value;
        if(constante==e){
            return value;
        }
    }
    return 0;
}
string Animal::getHist(){
    ostringstream os;
    for(auto it:hist){
        os << " Toxicidade: " << it.toxicidade << " Nutricao: " << it.valorNutritivo << " Nome: " << it.nomeAlimento << endl;
    }
    return os.str();
}
bool Animal::IsDead() {
    return died;
}
int Animal::getX() const{
    return x;
}
int Animal::getY() const{
    return y;
}
string Animal::getTipo()const{
    return tipo;
}
int Animal::getpeso() const {
    return peso;
}

int Animal::getId() const {
    return identificador;
}
void Animal::comer(int ptNutricao, int ptToxicidade,string tipodecomida,int idAlimento) {
    if(idAlimento>=0&&tipodecomida!="animal")
        r.eliminaAlimento(idAlimento);
    saude=saude - ptToxicidade;
    saude = saude + ptNutricao;
    hist.push_back(HistoricoAlimento(tipodecomida,ptNutricao,ptToxicidade));
}
void Animal::RandomSentido() {
    if(rand()%2==0){//escolhe aleatoriamente um sentido
        //horizontal
        if(rand()%2==0){
            x=x-raioMovimento;
        }else{
            x=x+raioMovimento;
        }
    }else{
        //vertical
        if(rand()%2==0){
            y=y-raioMovimento;
        }else{
            y=y+raioMovimento;
        }
    }
    r.DeuAVolta(x,y);
}
void Animal::Segue(int x1,int y1){
    if (x1 > x) {
        x = x + raioMovimento;
    } else if (x1 < x) {
        x = x - raioMovimento;
    }else if (y1 > y) {
        y = y + raioMovimento;
    } else if (y1 < y) {
        y = y - raioMovimento;
    }
    r.DeuAVolta(x,y);
}
void Animal::Foge(int x1,int y1){
    if(x1>x){
        x = x - raioMovimento;
    }else if(x1<x){
        x = x +raioMovimento;
    }else if(y1 >y){
        y = y - raioMovimento;
    }else if(y1<y){
        y = y + raioMovimento;
    }
    r.DeuAVolta(x,y);
}
