/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serpientesyescaleras;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Uniqua
 */
public class Serpientesyescaleras {
    // 0 = nada 1= trampa 2= usuario 3= trampa y usuario

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int posicionjugador = 0;
        boolean hasGanado = false;
        int tablerito[][] = new int[8][8];
        // 0 = nada 1= trampa 2= usuario 3= trampa y usuario
        Scanner kb = new Scanner(System.in);
        menu(kb, tablerito, hasGanado, posicionjugador);

    }

    public static void menu(Scanner kbmenu, int tablerito[][], boolean hasGanado, int posicionjugador) {

        int inicio = 0;
        while (inicio != 4) {
            System.out.println("======MENU PRINCIPAL======");
            System.out.println("1. Iniciar Juego");
            System.out.println("2. Retomar Juego");
            System.out.println("4. Salir");
            inicio = kbmenu.nextInt();
            if (inicio == 1) {
                tablero(tablerito);
                menu2 (posicionjugador, kbmenu, hasGanado, tablerito);
            } else if (inicio == 2) {
                for (int i = 7; i > -1; i--) {
                    System.out.println("-------------------------------------------------");
                    imprimir(i % 2 == 0, i, tablerito);
                }
                System.out.println("-------------------------------------------------");
                menu2 (posicionjugador, kbmenu, hasGanado, tablerito);
            }      
        }

    }

    public static void tablero(int tablerito[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablerito[i][j] = 0;
            }
        }

        trampas(tablerito);
        tablerito[0][0] += 2;
        for (int i = 7; i > -1; i--) {
            System.out.println("-------------------------------------------------");
            imprimir(i % 2 == 0, i, tablerito);
        }
        System.out.println("-------------------------------------------------");
    }

    public static void imprimir(boolean invertir, int i, int tablerito[][]) {
        if (invertir) {
            for (int j = 7; j > -1; j--) {
                int lugar = ((i) * 8 + (j + 1));
                System.out.print("|" + espaciotablero(lugar) + lugar);

            }
            System.out.println("|");
            for (int j = 7; j > -1; j--) {
                imprimosupertrampa(tablerito[i][j]);
            }
            System.out.println("|");
        } else {
            for (int j = 0; j < 8; j++) {
                int lugar = ((i) * 8 + (j + 1));
                System.out.print("|" + espaciotablero(lugar) + lugar);

            }
            System.out.println("|");
            for (int j = 0; j < 8; j++) {
                imprimosupertrampa(tablerito[i][j]);
            }
            System.out.println("|");
        }

    }

    public static String espaciotablero(int lugar) {
        if (lugar < 10) {
            return "    ";

        } else {
            return "   ";
        }
    }

    public static void trampas(int tablerito[][]) {
        Random rand = new Random();
        for (int i = 0; i < 8; i++) {
            int cantidadtrampas = rand.nextInt(3) + 1;
            for (int j = 0; j < cantidadtrampas; j++) {
                int newtrap = rand.nextInt(8);
                tablerito[i][newtrap] = 1;
            }
        }
    }

    public static void imprimosupertrampa(int casilla) {
        switch (casilla) {
            case 0:
                System.out.print("|     ");
                break;
            case 1:
                System.out.print("|    #");
                break;
            case 2:
                System.out.print("|@    ");
                break;
            case 3:
                System.out.print("|@   #");
                break;
        }
    }
    public static void menu2 (int posicionjugador, Scanner kbmenu, boolean hasGanado, int tablerito[][]){
        String evilinicio = "";
            kbmenu.nextLine();
            while (!hasGanado && !evilinicio.equals("P")) {
                System.out.println("======MENU JUEGO======");
                System.out.println("1. Tirar Dado");
                System.out.println("P. Menu principal");
                evilinicio = kbmenu.nextLine();
                if (evilinicio.equals("1") ) {
                    Random rand = new Random();
                    int dadito = rand.nextInt(6) + 1;
                    int dadoj = posicionjugador % 8;
                    int dadoi = (posicionjugador - dadoj) / 8;
                    posicionjugador += dadito;
                    System.out.println("Has sacado " + (dadito));
                    if (posicionjugador > 63) {
                        System.out.println("Has ganado");
                        hasGanado = true;
                    } else {
                        int newdadoj = posicionjugador % 8;
                        int newdadoi = (posicionjugador - newdadoj) / 8;
                        tablerito[dadoi][dadoj] -= 2;
                        tablerito[newdadoi][newdadoj] += 2;

                        for (int i = 7; i > -1; i--) {
                            System.out.println("-------------------------------------------------");
                            imprimir(i % 2 == 0, i, tablerito);
                        }

                        System.out.println("-------------------------------------------------");
                        if (tablerito[newdadoi][newdadoj] == 3) {
                            System.out.println("Has recibido una penalización");
                        }
                    }

                }

            }
    }
} 
