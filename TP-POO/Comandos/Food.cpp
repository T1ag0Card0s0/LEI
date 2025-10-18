//
// Created by trafa on 14/12/2022.
//

#include "Food.h"

string Food::executa(Reserva &r){
    int verf=-1;
    string com;
    ostringstream o;
    istringstream linha(comando);
    linha>> com >> tipo >> l >> c;  // se passar a lin e a col
    if(validaTipoAlimento()&&linha.fail() && (c!=-1)){
        o << "Comando Valido!!" << endl;
        r.insereAlimento(tipo, l, c);
    }else if(validaTipoAlimento()&&linha.fail() && (l==-1 && c ==-1) ){
        o << "Comando Valido!!" << endl;
        r.insereAlimento(tipo, l, c);
    }else if(l!=-1&&c!=-1 && validaPosicao(r)){
        linha >> verf;
        if(verf != -1){
            o << R"( criar alimento:
      ->food <tipo:r/t/b/a/p> <linha> <coluna>
      ->food <tipo: r/t/b/a/p> (fica numa posicao aleatoria))";
        }else{
            o << "Comando Valido!!" << endl;
            r.insereAlimento(tipo, 0,0,l, c);
        }
    }else{
        o << R"(criar alimento:
      ->food <tipo:r/t/b/a/p> <linha> <coluna>
      ->food <tipo: r/t/b/a/p> (fica numa posicao aleatoria))";
    }
    return o.str();
}
bool Food::validaTipoAlimento() {
    return (tipo == "r" || tipo == "t" || tipo == "b" || tipo =="a" || tipo == "p");
}
bool Food::validaPosicao(Reserva &r){
    return  l<r.getColunas()  && c <r.getLinhas() && l>=0 && c>=0;
}