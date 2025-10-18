//
// Created by trafa on 14/12/2022.
//

#include "Visanim.h"
string Visanim::executa(Reserva &r) {
    string aux, teste;
    ostringstream o;
    istringstream linha(comando);
    linha >> aux >> teste;
    if(linha.fail()){ // neste caso se falhar é porque esta correto :)
        cout << "Comando Valido!!" << endl;
        o << r.mostraAnimaisVisiveis(wr, hr, xr, yr);
    }else{
        o << "Erro excesso de argumentos! >> visanim" << endl;
    }


    return o.str();
}