cmake_minimum_required(VERSION 3.22)

project(trabalho_1_)

set(CMAKE_CXX_STANDARD 14)

add_executable(trabalho_1_ main.cpp Simulador.h Simulador.cpp Reserva.cpp Reserva.h Animais/Animal.h Animais/Animal.cpp Alimentos/Alimento.cpp Alimentos/Alimento.h HistoricoAlimento.h HistoricoAlimento.cpp Biblioteca/Terminal.cpp Biblioteca/Terminal.h Biblioteca/curses.h Comandos/Comandos.cpp Comandos/Comandos.h Comandos/CAnimal.cpp Comandos/CAnimal.h Comandos/Kill.cpp Comandos/Kill.h Comandos/Food.cpp Comandos/Food.h Comandos/Feed.cpp Comandos/Feed.h Comandos/NoFood.cpp Comandos/NoFood.h Comandos/Empty.cpp Comandos/Empty.h Comandos/See.cpp Comandos/See.h Comandos/Info.cpp Comandos/Info.h Comandos/N.cpp Comandos/N.h Comandos/Anim.cpp Comandos/Anim.h Comandos/Visanim.cpp Comandos/Visanim.h Comandos/Store.cpp Comandos/Store.h Comandos/Restore.cpp Comandos/Restore.h Comandos/Load.cpp Comandos/Load.h Comandos/Slide.cpp Comandos/Slide.h Animais/Ovelha.cpp Animais/Ovelha.h Animais/Lobo.cpp Animais/Lobo.h Animais/Canguro.cpp Animais/Canguro.h Animais/Misterio.cpp Animais/Misterio.h Animais/Coelho.cpp Animais/Coelho.h Alimentos/Relva.cpp Alimentos/Relva.h Fabricas/FabricaComandos.cpp Fabricas/FabricaComandos.h Fabricas/FabricaAnimais.cpp Fabricas/FabricaAnimais.h Alimentos/Cenoura.cpp Alimentos/Cenoura.h Alimentos/Corpo.cpp Alimentos/Corpo.h Alimentos/Bife.cpp Alimentos/Bife.h Alimentos/AlimentoMisterio.cpp Alimentos/AlimentoMisterio.h Fabricas/FabricaAlimentos.cpp Fabricas/FabricaAlimentos.h)

target_link_libraries(${PROJECT_NAME} c:/PDCurses-3.9/wincon/pdcurses.a)
