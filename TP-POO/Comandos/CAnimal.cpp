//
// Created by trafa on 14/12/2022.
//

#include "CAnimal.h"
string CAnimal::executa(Reserva&r){
    int verf=-1;
    string com;
    ostringstream o;
    istringstream linha(comando);
    linha>>com>> tipo >> l >> c;  // se passar a lin e a col
    if(validaTipoAnimal()&&linha.fail() && (c!=-1)){
        o << "Comando Valido!!" << endl;
        r.insereAnimal(tipo, l, c);
    }else if(validaTipoAnimal()&&linha.fail() && (l==-1 && c ==-1) ){
        o << "Comando Valido!!" << endl;
        r.insereAnimal(tipo, l, c);
    }else if(l!=-1&&c!=-1 && validaPosicao(r)){
        linha >> verf;
        if(verf != -1){
            o << R"( criar animal:
      ->animal <especie:c/o/l/g/m> <linha> <coluna> (coelho/ovelha/lobo/cang./mist.)
      ->animal <especie: c/o/l/g/m> (fica numa posicao aleatoria))";
        }else{
            o << "Comando Valido!!" << endl;
            r.insereAnimal(tipo, l, c);
        }
    }else{
        o << R"( criar animal:
      ->animal <especie:c/o/l/g/m> <linha> <coluna> (coelho/ovelha/lobo/cang./mist.)
      ->animal <especie: c/o/l/g/m> (fica numa posicao aleatoria))";
    }
    return o.str();
}

bool CAnimal::validaTipoAnimal(){
    return (tipo == "c" || tipo == "o" || tipo == "l" || tipo =="g" || tipo =="m");
}
bool CAnimal::validaPosicao(Reserva &r){
    return  l<=r.getColunas()  && c <=r.getLinhas() && l>=0 && c>=0;
}