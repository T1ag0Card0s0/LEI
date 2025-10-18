//
// Created by trafa on 14/12/2022.
//

#include "Lobo.h"
#include "../Reserva.h"
string Lobo::getAsString() const{
    ostringstream os;
    os << "Especie: " << especie << " Peso:"<< peso <<" Saude: "<<saude<<" Fome: "<< fome<<" Duracao de Vida: "<<tempoDeVida<<" Id:" << identificador <<" Coordenadas: ("<<x<<","<<y<<")";
    return os.str();
}

Animal* Lobo::duplica() {
    return new Lobo(*this);
}
void Lobo::setRedor() {
    redor.clear();
    stringstream X(r.getRedor(x-5,x+5,y-5,y+5,identificador,"carne"));
    string T;
    while (getline(X, T, '\n')) {
        redor.push_back(T);
    }
}
string  Lobo::move(){
    setRedor();
    ostringstream o;
    int idRedor,vn,tx,x1,y1,maisPesado=0,xPesado,yPesado,txPesado,idPesado;
    bool animalRedor= false;
    string nome;
    tempoDeVida++;
    fome+=2;
    raioMovimento=1;
    if(freeze){
        tempoParalizado++;
        if(tempoParalizado>=LIMITE_ITERACOES){
            tempoParalizado=0;
            freeze= false;
        }else{
            o<<"Lobo "<< identificador<<" esta paralizado";
            return o.str() ;
        }
    }
    if(fome>25){
        saude-=2;
        raioMovimento=2;
    }else if(fome>10){
        saude--;
        raioMovimento=2;
    }
    if(tempoDeVida==tempoDeReproducao){//reproduz-se
        int xreproducao = (x - 15) + rand() % 30,yreproducao = (y - 15) + rand() % 30;
        r.DeuAVolta(xreproducao,yreproducao);
        r.insereFilhote(new Lobo(xreproducao,yreproducao,r.setNewId(),r));
        o<<" lobo: "<<identificador<<" reproduziu-se";
    }
    if(saude<=0){//morre
        r.insereAlimento(new Corpo(x,y,10,0,r.setNewId(),r));
        died= true;
        return "";
    }
    if(redor.size()>0) {
        for (string data: redor) {
            istringstream aux(data);
            aux >> idRedor >> nome >> vn >> tx >> x1 >> y1;
            if (nome == "animal" && vn > maisPesado) {
                animalRedor = true;
                maisPesado = vn;
                xPesado = x1;
                yPesado = y1;
                txPesado = tx;
                idPesado = idRedor;
            }
        }
        if (!animalRedor) {//alimento
            istringstream aux(redor.at(rand() % redor.size()));
            aux >> idRedor >> nome >> vn >> tx >> x1 >> y1;
            Segue(x1, y1);
            if (x1 == x && y1 == y) {
                comer(vn, tx, nome, idRedor);
                if (nome == "AlimentoMisterio") {
                    freeze = true;
                }
            }
        } else {//animal
            raioMovimento=2;
            if (fome > 15) {
                raioMovimento = 3;
            }
            Segue(xPesado, yPesado);
            if (xPesado == x && yPesado == y &&maisPesado < peso) {// na mesma posicao que o lobo e pesar menos que o lobo
                comer(maisPesado, txPesado, "animal", idPesado);
                r.foiComido(idPesado);
            } else if (xPesado == x && yPesado == y &&maisPesado > peso) {// na mesma posicao que o lobo e pesar mais que o lobo
                if (rand() % 2 == 0) {//lobo vence a luta
                    o << " lobo: " << identificador << " gahou a luta";
                    comer(maisPesado, txPesado, "animal", idPesado);
                    r.foiComido(idPesado);
                } else {//lobo perde a luta
                    o << " lobo: " << identificador << " perdeu uma luta";
                    r.insereAlimento(new Corpo(x, y, 10, 0, r.setNewId(), r));
                    died = true;
                    return o.str();
                }
            }
        }
    }
    if(redor.size()==0)
        RandomSentido();
    return o.str();
}