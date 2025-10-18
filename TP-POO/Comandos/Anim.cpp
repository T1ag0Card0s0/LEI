//
// Created by trafa on 14/12/2022.
//

#include "Anim.h"

string Anim::executa(Reserva &r) {
    string com, verf;
    ostringstream o;
    istringstream linha(comando);
    linha >> com >> verf;
    if(linha.fail()){ // neste caso se falhar é porque esta correto :)
        cout << "Comando Valido!!" << endl;
        o << r.mostraInfoAnimais();
    }else{
        o << "Erro excesso de argumentos! >> anim" << endl;
    }
    return o.str();
}