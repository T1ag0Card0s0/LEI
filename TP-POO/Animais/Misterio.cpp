//
// Created by trafa on 14/12/2022.
//

#include "Misterio.h"
#include "../Reserva.h"
string Misterio::getAsString() const{
    ostringstream os;
    os << "Especie: " << especie << " Peso:"<< peso <<" Saude: "<<saude<<" Duracao de Vida: "<<tempoDeVida<<" Id:" << identificador <<" Coordenadas: ("<<x<<","<<y<<")";
    return os.str();
}
Animal* Misterio::duplica() {
    return new Misterio(*this);
}
void Misterio::setRedor() {
    redor.clear();
    stringstream X(r.getRedor(x-1,x+1,y-1,y+1,identificador,"verdura"));
    string T;
    while (getline(X, T, '\n')) {
        redor.push_back(T);
    }
}
string  Misterio::move(){
    ostringstream o;
    string nome;
    int idRedor, vn, tx, x1, y1;
    tempoDeVida++;
    setRedor();
    raioMovimento=1;
    if(freeze){
        tempoParalizado++;
        if(tempoParalizado>=LIMITE_ITERACOES){
            tempoParalizado=0;
            freeze= false;
        }else{
            o<<"Animal Misterio "<< identificador<<" esta paralizado";
            return o.str() ;
        }
    }
    if(tempoDeVida==VAnimal){
        died= true;
        return "";
    }
    for(string data:redor) {
        stringstream aux(data);
        aux >> idRedor >> nome >> vn >> tx >> x1 >> y1;
        if (nome != "animal") {
            Segue(x1, y1);
            if (x1 == x && y1 == y) {
                comer(vn, tx, nome, idRedor);
                if (nome == "AlimentoMisterio") {
                    freeze = true;
                }
                int xreproducao = x - 2 + rand() % 4, yreproducao = y - 2 + rand() % 4;
                r.DeuAVolta(xreproducao, yreproducao);
                r.insereFilhote(new Misterio(xreproducao, yreproducao, r.setNewId(), r));
                o << " animal misterio: " << identificador << " reproduziu-se";
            }
            return o.str();
        }
    }
    RandomSentido();
    return o.str();
}