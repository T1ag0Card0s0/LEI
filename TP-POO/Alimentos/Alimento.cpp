//
// Created by trafa on 15/11/2022.
//

#include "Alimento.h"
Alimento::Alimento(string nome,string tipo, int vn, int tx,int x,int y,int identificador,initializer_list<string> cheiros,Reserva &r):
        nome(nome),tipo(tipo),valorNutritivo(vn), toxicidade(tx), x(x),y(y),cheiros(cheiros),r(r),died(false),identificador(identificador),tempoDeVida(0){
    VAlimento=getConstantes("V"+nome);
};
int Alimento::getConstantes(string e){
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
string Alimento::getAsString() const {
    ostringstream os;
    os << "Nome: " << nome << " Valor Nutritivo: " << valorNutritivo << " Toxicidade: " << toxicidade << " Id:" << getId() <<
       " Coordenadas: ("<<x<<","<<y<<")";
    os<<"cheiros: ";
    for(string a: cheiros){
        os<< a+" ";
    }
    return os.str();
}
bool Alimento::isDead() {
    return died;
}
int Alimento::getX()const{
    return x;
}
int Alimento::getY()const{
    return y;
}
int Alimento::getId() const {
    return identificador;
}
string Alimento::getTipo()const{
    return tipo;
}
string Alimento::getIntData(){
    ostringstream o;
    o<<getId()<<" "<<nome<<" "<<valorNutritivo<<" "<<toxicidade<<" "<<x<<" "<<y;
    return o.str();
}
bool Alimento::smellLike(string cheiro)const{
    for(string c: cheiros){
        if(cheiro==c)
            return true;
    }
    return false;
}
