//
// Created by trafa on 14/12/2022.
//

#include "Coelho.h"
#include "../Reserva.h"
#include "../Fabricas/FabricaAnimais.h"
string Coelho::getAsString() const{
    ostringstream os;
    os << "Especie: " << especie << " Peso:"<< peso <<" Saude: "<<saude<<" Fome: "<< fome<<" Duracao de Vida: "<<tempoDeVida<<" Id:" << identificador <<" Coordenadas: ("<<x<<","<<y<<")";
    return os.str();
}
Animal* Coelho::duplica() {
    return new Coelho(*this);
}
void Coelho::setRedor() {
    redor.clear();
    stringstream X(r.getRedor(x-4,x+4,y-4,y+4,identificador,"verdura"));
    string T;
    while (getline(X, T, '\n')) {
        redor.push_back(T);
    }
}
string Coelho::move(){
    ostringstream o;
    string nome;
    int idRedor, vn, tx, x1, y1;
    tempoDeVida++;
    tempoDeReproducao++;
    fome++;
    setRedor();
    raioMovimento=1+rand()%2;
    if(freeze){
        tempoParalizado++;
        if(tempoParalizado>=LIMITE_ITERACOES){
            tempoParalizado=0;
            freeze= false;
        }else{
            o<<"coelho "<< identificador<<" esta paralizado";
            return o.str() ;
        }
    }
    if(fome>20){
        saude-=2;
        raioMovimento=1+rand()%4;
    }else if(fome>10){
        saude--;
        raioMovimento=1+rand()%3;
    }
    if(tempoDeVida==VAnimal||saude<=0){
        died = true;
        return "";
    }
    if(tempoDeReproducao==8){
       if(rand()%2==0) {
            int xreproducao = x - 10 + rand() % 20,yreproducao = y - 10 + rand() % 20;
            r.DeuAVolta(xreproducao,yreproducao);
            r.insereFilhote(new Coelho(xreproducao,yreproducao,r.setNewId(),r));
           o<<" coelho: "<<identificador<<" reproduziu-se";
       }
        tempoDeReproducao=0;
    }
    for(string data: redor) {
        stringstream aux(data);
        aux >> idRedor >> nome >> vn >> tx >> x1 >> y1;
        if(nome=="animal"&&vn>=10){
            Foge(x1,y1);
            break;
        }else if(nome == "animal"&&vn<10){
            RandomSentido();
            break;
        }else if(nome != "animal"){
            Segue(x1, y1);
            if(x1==x&&y1==y){
                comer(vn,tx,nome,idRedor);
                if(nome=="AlimentoMisterio"){
                    freeze= true;
                }
            }
            break;
        }
    }
    if(redor.size()==0)
        RandomSentido();
    return o.str();
}