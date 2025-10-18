//
// Created by trafa on 14/11/2022.
//

#include "Reserva.h"
#include <sstream>
Reserva::Reserva(int NL, int NC,string nome):NL(NL),NC(NC),nome(nome) {
}
Reserva::Reserva(Reserva &o):NC(o.NC),NL(o.NL),nome(o.nome),id(o.id),alimento(o.alimento) {
}
Reserva::~Reserva(){
    for(auto a:animais){
        delete a;
    }
    for(auto a:alimento){
        delete a;
    }
}
void Reserva::DeuAVolta(int &i,int &j)const{
    if(i<0){
        i=NC+i;
    }else if(i>=NC){
        i=abs(i-NC);
    }
    if(j<0){
        j=NL+j;
    }else if(j>=NL){
        j=abs(j-NL);
    }
}
void Reserva::eliminaAlimento(int x,int y){
    if(y!=-1) {
        alimento.erase(remove_if(alimento.begin(), alimento.end(), [&](const Alimento *a) {
            if (a->getX() == x && a->getY() == y) {
                delete a;
                return true;
            }
            return false;
        }), alimento.end());
        return;
    }
    alimento.erase(remove_if(alimento.begin(),alimento.end(),[&](const Alimento *a){
        if (a->getId()==x) {
            delete a;
            return true;
        }
        return false;
    }),alimento.end());
}
void Reserva::foiComido(int idd) {
   auto it = find_if(animais.begin(),animais.end(),[&](Animal *a){
        return a->getId()==idd;
    });
   if(*it!=nullptr)
       mortos.push_back(*it);
}
void Reserva::eliminaAnimal(int x, int y) {
    if(y>=0&&x>=0) {
        animais.erase(remove_if(animais.begin(), animais.end(), [&](const Animal *a) {
            if (a->getX() == x && a->getY() == y) {
                delete a;
                return true;
            }
            return false;
        }), animais.end());
        return;
    }
        animais.erase(remove_if(animais.begin(),animais.end(),[&](Animal *a){
            if(a->getId()==x) {
                delete a;
                return true;
            }
            return false;
        }),animais.end());
}


void Reserva::insereAnimal(string especie,int x,int y) {
    if(x==-1||y==-1) {
        x = rand() % getColunas();
        y= rand()% getLinhas();
    }
    FabricaAnimais f(especie, x, y, setNewId());
    animais.push_back(f.CriaAnimal(*this));
}
void Reserva::insereFilhote(Animal* a) {
    filhotes.push_back(a);
}
string Reserva::mostraInfoAnimais() {
    ostringstream o;
    for(auto it : animais){
        o << it->getAsString() << endl<<endl;
    }
    return o.str();
}
string Reserva::getInfoByPosition(int x,int y){
    ostringstream o;
    for(auto it: animais){
        if(it->getX() == x&&it->getY()==y){
            o<< it->getAsString()<<endl;
        }
    }
    for (auto it : alimento){
        if(it->getX() == x&&it->getY()==y){
            o<<it->getAsString()<<endl;
        }
    }
    return o.str();
}
string Reserva::getRedor(int xmin,int xmax,int ymin,int ymax,int idd,string cheiro)const{
    ostringstream o;
    DeuAVolta(xmin,ymin);
    DeuAVolta(xmax,ymax);
    for(auto a: alimento){
        if((a->getX()<=xmax && a->getX()>=xmin) && (a->getY()<=ymax && a->getY()>=ymin) && a->smellLike(cheiro)){
            o<<a->getIntData()<<endl;
        }
    }
    for(auto a: animais){
        if((a->getX()<=xmax && a->getX()>=xmin) && (a->getY()<=ymax && a->getY()>=ymin)&&a->getId()!=idd){
            o<<a->getId()<<" animal "<<a->getpeso()<<" "<<0<<" "<<a->getX()<<" "<<a->getY()<<endl;
        }
    }
    return o.str();
}

string Reserva::getInfoById(int num){
    for (auto it : alimento){
        if(it->getId() == num){
            return it->getAsString();
        }
    }
    for(auto it: animais){
        if(it->getId() == num){
            return it->getAsString();
        }
    }
    return "Id Invalido";

}

void Reserva::insereAlimento(string tipo, int vn,int tx,int x, int y) {
    string  nomeAlimento;
    if(x==-1||y==-1) {
        x = rand() % getColunas();
        y= rand()% getLinhas();
    }
    FabricaAlimentos f(tipo,x,y, setNewId(),vn,tx);
    Alimento *a=f.CriaAlimento(*this);
    alimento.push_back(a);
}
void Reserva::insereAlimento(Alimento *a) {
    novoAlimento.push_back(a);
}

const int Reserva::getLinhas()const {
    return NL;
}
const int Reserva::getColunas()const {
    return NC;
}
string Reserva::findTipo(int x, int y) const {
    string str="_";
    for_each(alimento.begin(),alimento.end(),[&](const Alimento *a){
        if(a->getX()==x&&a->getY()==y){
            str = a->getTipo();
        }
    });
    for_each(animais.begin(),animais.end(),[&](const Animal *a){
        if(a->getX()==x&&a->getY()==y){
            str = a->getTipo();
        }
    });
    return str+"|";
}
int Reserva::setNewId(){
    id++;
    return id;
}

string Reserva::mostraAnimaisVisiveis(int wr, int hr,int xr,int yr) {
    ostringstream o;
    for(auto it : animais){
        if(it->getX() < xr+wr/2-2 && it->getX()>=xr && it->getY() < yr+hr-2 && it->getY()>=yr){
            o<< it->getAsString() << endl;
        }
    }
    return o.str();
}

const string Reserva::getNome()const {
    return nome;
}
void Reserva::setNome(string n) {
    nome=n;
}

Reserva& Reserva::operator=(const Reserva &b) {
    for(auto l: animais){
        delete l;
    }
    for(auto l: alimento){
        delete l;
    }
    alimento.clear();
    animais.clear();
    for(auto l: b.animais){
        animais.push_back(l->duplica());
    }
    for(auto l: b.alimento){
        Alimento *novo=l->duplica();
        alimento.push_back(novo);
    }
    id=b.id;
    return *this;
}

string Reserva::feed(int ptn,int ptt,int x,int y){
    ostringstream os ;
    string erro = "Animal nao encontrado!!";
    bool aux;
    if(y == -1){
        // entao x = id
        for(auto it : animais){
            if(x == it->getId()){
                it->comer(ptn,ptt,"user",-1);
                os << it->getHist() << endl;
                aux = true;
            }
        }
    }else{
        for(auto it: animais){
            if(x == it->getX() && y == it->getY()){
                it->comer(ptn, ptt, "user",-1);
                os << it->getHist();
                aux = true;
            }
        }
    }
    if(aux){
        return os.str();
    }else{
        return erro;
    }
}

string Reserva::n() {
    ostringstream o;
    o<<"ha "<<alimento.size()<<" alimentos"<<endl;
    for(auto a: alimento){
        a->move();
        if(a->isDead())
            alimentoMorto.push_back(a);
    }
    //atualiza alimentos introduzidos
    for(auto a: animais){
       o<< a->move();
        if(a->IsDead())
            mortos.push_back(a);
    }

    if(filhotes.size()>0){
        o<<"nasceram: "<<endl;//atualiza animais nascidos
    }
    for(auto f: filhotes){
        o<<f->getTipo()<<" "<< f->getId();
        animais.push_back(f);
    }
    for(auto an: novoAlimento){
        alimento.push_back(an);
    }
    if(mortos.size()>0){
        o<<"mortos:"<<endl;//elimina tudo o que é morto
    }
    for(auto m: mortos){
        o<<m->getTipo()<<" "<< m->getId()<<", ";
        eliminaAnimal(m->getId());
    }
    for(auto am: alimentoMorto){
        eliminaAlimento(am->getId());
    }
    filhotes.clear();
    mortos.clear();
    novoAlimento.clear();
    alimentoMorto.clear();
    return o.str();
}
void Reserva::mostraReservaVisivel(int xr, int yr,int wr,int hr,Window &zonaR) const{
    zonaR<< move_to(0,0);
    for(int j=yr;j<yr + hr-2;j++){
            for (int i = xr; i < xr + wr / 2 - 2&&i<NC; i++) {
                zonaR << "_|";
            }
        zonaR << "\n";
    }
    for_each(animais.begin(),animais.end(),[&](Animal *a){
        if((a->getX()<xr + wr / 2 - 2&&a->getX()>=xr)&&(a->getY()<=yr + hr-2&&a->getY()>=yr))
            zonaR<< move_to((a->getX()-xr)*2,a->getY()-yr)<<a->getTipo();
    });
    for_each(alimento.begin(),alimento.end(),[&](Alimento *a){
        if((a->getX()<xr + wr / 2 - 2&&a->getX()>=xr)&&(a->getY()<=yr + hr-2&&a->getY()>=yr))
            zonaR<< move_to((a->getX()-xr)*2,a->getY()-yr)<<a->getTipo();
    });
}