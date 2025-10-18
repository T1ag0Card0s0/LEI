
#include <string>
#include "Reserva.h"
#include "Simulador.h"


using namespace std;
using namespace term;
int main() {
    unsigned seed = time(0);
    srand(seed);
    int nl = 16 + rand()%484;
    int nc = 16 + rand()%484;
    Reserva r( nl, nc,"main");
    Simulador c(r,120,20);
    c.iniciaSimulacao();

    return 0;
}
