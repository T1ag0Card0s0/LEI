//
// Created by trafa on 14/12/2022.
//
#include "Feed.h"

string Feed::executa(Reserva &r) {
    int pn=-1, pt=-1, verf =-1; //pontos nutritivos e pontos de toxicidade
    string com;
    ostringstream o;
    istringstream linha(comando);
    linha>> com;
    if(com=="feedid"){
        linha>>l>>pn>>pt>>verf;
        if(l>0&&pn>=0&&pt>=0&&verf==-1){
            o<<"Comando Valido"<<endl;
            r.feed(pn,pt,l);
        }else
            o<<getErro();
    }else{
        linha>>l>>c>>pn>>pt>>verf;
        if((l>=0&&l<r.getLinhas()&&c>=0&&c<r.getColunas())&&pn>=0&&pt>=0&&verf==-1){
            o<<"Comando Valido"<<endl;
            r.feed(pn,pt,l,c);
        }else
            o<<getErro();
    }
    return o.str();
}

string Feed::getErro() {
    ostringstream o;

    o << R"( alimentar animal:
      ->feed <linha> <coluna> <pNutritivos> <pToxicidade>
      ->feed <id> <pNutritivos> <pToxicidade>)";

    return o.str();
}
