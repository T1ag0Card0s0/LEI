//
// Created by trafa on 14/12/2022.
//

#include "Canguro.h"
#include "../Reserva.h"
string Canguro::getAsString() const{
    ostringstream os;
    os << "Especie: " << especie << " Peso:"<< peso <<" Saude: "<<saude<<" Duracao de Vida: "<<tempoDeVida<<" Id:" << identificador ;
    if(idPai>=0){
        os<<" Id pai: "<<idPai;
    }
    os<<" Coordenadas: ("<<x<<","<<y<<")";
    return os.str();
}
Animal* Canguro::duplica() {
    return new Canguro(*this);
}
void Canguro::setRedor() {
    redor.clear();
    stringstream X(r.getRedor(x-7,x+7,y-7,y+7,identificador,"verdura"));
    string T;
    while (getline(X, T, '\n')) {
        redor.push_back(T);
    }
}
string Canguro::move(){
    ostringstream o;
    string nome;
    int idRedor, vn, tx, x1, y1,xpai=-1,ypai=-1;
    tempoDeVida++;
    tempoDeReproducao++;
    setRedor();
    raioMovimento=1;
    if(tempoDeVida==20){
        peso=20;
    }
    if(tempoDeVida>10){
        naBolsa=false;
        perigo=false;
        idPai=-1;
    }
    if(tempoDeVida==VAnimal){//morre
        r.insereAlimento(new Corpo(x,y,15,5,r.setNewId(),r));
        died= true;
        return o.str();
    }
    if(tempoDeReproducao==30){//reproduz-se
        int xreproducao = x - 3 + rand() % 6,yreproducao = y - 3 + rand() % 6;
        r.DeuAVolta(xreproducao,yreproducao);
        r.insereFilhote(new Canguro(xreproducao,yreproducao,r.setNewId(),identificador,r));
        tempoDeReproducao=0;
        o<<" canguro: "<<identificador<<" reproduziu-se";
    }
    for(string data: redor){
        stringstream aux(data);
        aux >> idRedor >> nome >> vn >> tx >> x1 >> y1;
        if(tempoDeVida<10){
            if (idRedor == idPai){
                xpai = x1;
                ypai = y1;
                if(naBolsa){
                    o<<" canguro: "<<identificador<<" esta na bolsa";
                    tempoBolsa++;
                    x=xpai;
                    y=ypai;
                    if(tempoBolsa==5){
                        tempoBolsa=0;
                        naBolsa=false;
                        perigo=false;
                        o<<" canuro: "<<identificador<<" saiu da bolsa";
                    }
                    return o.str();
                }
            }else if(nome=="animal"){
                perigo = true;
                break;
            }
        }else{
            RandomSentido();
            break;
        }
    }
    if(perigo&&!naBolsa){//se estiver em perigo e fora da bolsa
        raioMovimento=2;
        Segue(xpai, ypai);
        if (xpai == x && ypai == y) {
            naBolsa= true;
            return "";
        }
    }
    if(!perigo&&idPai>=0){//se nao estiver em perigo e nao for crescido e tiver pai (id do pai é sempre maior ou igual a zero)
        if((x<xpai+4&&x>xpai-4)&&(y<ypai+4&&y>ypai-4)&&(xpai!=x&&ypai!=y)) {//distancia de 4 unidades do pai
            Segue(xpai, ypai);
        }
    }
    if(redor.size()==0)//nada ao redor
        RandomSentido();
    return o.str();
}