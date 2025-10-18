

#include "Simulador.h"
#include "Biblioteca/Terminal.h"
#include "Fabricas/FabricaComandos.h"
#include "Biblioteca/curses.h"
using namespace term;
Simulador::Simulador(Reserva r,int wr,int hr):wr(wr),hr(hr),r(r){}
Simulador::~Simulador() {
    for_each(estados.begin(),estados.end(),[&](const Reserva *a){
        delete a;
    });
}

void Simulador::ficheiroConstantes(){
    ifstream f;
    f.open("constantes.txt");
    if(!f){
        return;
    }
    int i = 1;
    while(!f.eof()){
        string constante;
        string letra;
        int value;
        f >> constante >> value;
        i++;
    }
}


int Simulador::validaComandos(string &comando,int& xr, int &yr,string &output,Window &zonaR,Window &zonaO){
    if (comando == "exit"){
        return 0;
    }
    FabricaComandos f(comando,wr,hr);
    Comandos *c=f.CriaComando(xr,yr,estados,zonaR,zonaO);
    if(c!= nullptr) {
        output = c->executa(r);
        delete c;
        return 1;
    }else{
        helper(output);
        return 2;
    }
}
void Simulador::helper(string &output) {
    stringstream o;
    o<<R"(Comandos possiveis:
 criar animal:
  ->animal <especie:c/o/l/g/m> <linha> <coluna> (coelho/ovelha/lobo/cang./mist.)
  ->animal <especie: c/o/l/g/m> (fica numa posicao aleatoria)
 matar animal:
  ->kill <linha> <coluna> (identifica por posicao)
  ->killid <id> (identifica por ID)
 colocar alimento:
  ->food <tipo: r/t/b/a> <linha> <coluna> (relva / cenoura / bife /misterio)
  ->food <tipo: r/t/b/a> (fica numa posicao aleatoria)
 alimetar diretamente os animais:
  ->feed <linha> <coluna> <pontos nutritivos> <pontos de toxicidade>
    Alimenta os animais que estiverem nessa posicao
  ->feedid <ID> <pontos nutritivos> <pontos de toxicidade>
    Alimenta o animal com esse ID
 remover alimento:
  ->nofood <linha> <coluna>
  ->nofood <ID> (so parametro - assume que e o ID)
 eliminar o que quer que esteja numa posicao:
  ->empty <linha> <coluna>
 ver o que se encontra numa posicao:
  ->see <linha> <coluna>
 ver informacao acerca de uma elemento do simulador (animal ou alimento):
  ->info <ID>
 passar para o instante seguinte da simulacao:
  ->n <N>
   avanca N instantes, mas 1 de cada vez, executando as açoes de cada um dos
   instantes.
  ->n <N> <P>
    Mesmo que o anterior mas faz uma pausa de P segundos entre cada instante para
    dar tempo para ver.
 listar ID dos animais na reserva:
  ->anim
    Apresenta a lista de animais: ID, especie, saude
 listar ID dos animais na area visivel da reserva:
  ->visanim
    Apresenta a lista de animais: ID, especie, saude
 Armazenar o estado da reserva em memoria:
  ->store <nome>
 Reativar um estado da reserva previamente armazenado em memoria:
  ->restore <nome>
 Carregar e executar comandos a partir de um ficheiro de texto:
  ->load <nome-do-ficheiro>
 Deslocar a area de visualizacao (analogia: mover uma janela deslizante):
  ->slide <direcao: up/down/right/left> <linhas/colunas>
 Encerrar o simulador:
  ->exit
            )";
    output= o.str();
}

void Simulador::mostraOutputVisivel(string &output,Window& zonaO,int y){
    stringstream X(output);
    string T;
    int i=0,j=1;
    while (getline(X, T, '\n')) {
        if(i>=y&&i<y+7){
            zonaO<< move_to(0,j)<<T;
            j++;
        }
        i++;
    }
}
int Simulador::introduzComandos(int &xr,int &yr){
    int aux;
    int yo=0,ho=10,wo=85;
    string output="";
    bool arrows=true;
    Terminal &t = Terminal::instance();
    resize_term(30,120);
    for(int i=1; i<20; i++) {
        t.init_color(i, i, 0);
    }
    string comando="";
    Window zonaC=Window(0,20,35,10);
    Window zonaO=Window(35,20,wo,ho);
    Window zonaR=Window(0,0,wr,hr);
    zonaR << set_color(0) << move_to(0, 0);
    r.mostraReservaVisivel(xr,yr,wr,hr,zonaR);
    zonaC<<move_to(0,2)<<"Dimensoes:";
    zonaC<< move_to(0,3)<<"->Reserva:("<<r.getColunas()-1<<","<<r.getLinhas()-1<<")";
    zonaC<< move_to(0,4)<<"->Area visivel:("<<wr/2-2<<","<<hr-2<<")";
    zonaC<< move_to(0,5)<<"Nome: "<<r.getNome();
    do{
        zonaR << set_color(0) << move_to(0, 0);
        zonaO<< move_to(0,0)<<"Output:";
        mostraOutputVisivel(output,zonaO,yo);
        if(arrows) {
            r.mostraReservaVisivel(xr, yr,wr,hr, zonaR);
        }
        zonaC<< set_color(0) <<move_to(0,1)<<"Canto sup. esq.:("<<xr<<","<<yr<<") ";
        zonaC<< set_color(0)<< move_to(0,0)<<">>";
        comando.resize(100);
        zonaC >> comando;
        arrows= true;//apenas para atualizar a reserva quando se clica nas setas
        if (comando == "KEY_RESIZE") {
            zonaC.clear();
            continue;
        }
        if (comando == "KEY_UP") {
            if( yr-1>=0 ) yr--;
            continue;
        }
        if (comando == "KEY_DOWN") {
            if(yr+hr<=r.getLinhas()+1) yr++;
            continue;
        }
        if (comando== "KEY_LEFT") {
            if( xr-1>=0) xr--;
            continue;
        }
        if (comando == "KEY_RIGHT") {
            if( xr+1<r.getColunas() ) xr++;
            continue;
        }
        zonaO.clear();
        if(comando=="CTL_UP"){
            arrows= false;
          if(yo>0) yo--;
            continue;
        }
        if(comando=="CTL_DOWN"){
            arrows= false;
            yo++;
            continue;
        }
        aux= validaComandos(comando,xr,yr,output,zonaR,zonaO);
        zonaC<< set_color(0)<< move_to(0,0)<<"                    ";
        zonaC<< move_to(0,5)<<"Nome:                 ";
        zonaC<< move_to(0,5)<<"Nome: "<<r.getNome();
    } while (aux!=0);
    return 0;
}


void Simulador::iniciaSimulacao(){
    int xr = 0, yr = 0;
    ficheiroConstantes();
    introduzComandos(xr,yr);
}
