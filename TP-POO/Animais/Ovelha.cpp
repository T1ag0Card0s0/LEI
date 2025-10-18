//
// Created by trafa on 14/12/2022.
//

#include "Ovelha.h"
#include "../Reserva.h"
string Ovelha::getAsString() const{
    ostringstream os;
    os << "Especie: " << especie << " Peso:"<< peso <<" Saude: "<<saude<<" Fome: "<< fome<<" Duracao de Vida: "<<tempoDeVida<<" Id:" << identificador <<" Coordenadas: ("<<x<<","<<y<<")";
    return os.str();
}

Animal* Ovelha::duplica() {
    return new Ovelha(*this);
}
void Ovelha::setRedor() {
    redor.clear();
    stringstream X(r.getRedor(x-3,x+3,y-3,y+3,identificador,"erva"));
    string T;
    while (getline(X, T, '\n')) {
        redor.push_back(T);
    }
}
string Ovelha::move(){
    ostringstream o;
    string nome;
    int idRedor, vn, tx, x1, y1;
    tempoDeVida++;
    tempoDeReproducao++;
    fome++;
    setRedor();
    raioMovimento=1;
    if(freeze){
        tempoParalizado++;
        if(tempoParalizado>=LIMITE_ITERACOES){
            tempoParalizado=0;
            freeze= false;
        }else{
            o<<"Ovelha "<< identificador<<" esta paralizado";
            return o.str() ;
        }
    }
    if(fome>20){
        saude-=2;
        raioMovimento=1+rand()%2;
    }else if(fome>15){
        saude--;
        raioMovimento=1+rand()%2;
    }
    if(tempoDeVida==VAnimal||saude<=0){//morre
        r.insereAlimento(new Corpo(x,y,peso,0,r.setNewId(),r));
        died = true;
        return "";
    }
    if(tempoDeReproducao==15){//reproduz-se
        int xreproducao = x - 12 + rand() % 24,yreproducao = y - 12 + rand() % 24;
        r.DeuAVolta(xreproducao,yreproducao);
        r.insereFilhote(new Ovelha(xreproducao,yreproducao,r.setNewId(),r,saude));
        tempoDeReproducao=0;
        o<<" ovelha: "<<identificador<<" repoduziu-se";
    }
    for(string data:redor) {
        stringstream aux(data);
        aux >> idRedor >> nome >> vn >> tx >> x1 >> y1;
        if (nome == "animal" && vn >= 15) {
            Foge(x1, y1);
            break;
        } else if (nome == "animal" && vn < 15) {
            RandomSentido();
            break;
        } else if (nome != "animal") {
            Segue(x1, y1);
            if (x1 == x && y1 == y) {
                comer(vn, tx, nome, idRedor);
                if (nome == "AlimentoMisterio") {
                    freeze = true;
                }
            }
            break;
        }
    }
    if(redor.size()==0)
        RandomSentido();
    return o.str();
}