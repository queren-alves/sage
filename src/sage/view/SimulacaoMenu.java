package sage.view;

import java.util.Scanner;

import sage.util.Init;

public class SimulacaoMenu {

	public static void simulationMenu(Scanner sc) throws InterruptedException {
		boolean simu = true;
        while(simu) {
        	
		System.out.println("\t╭─────────────────────────────────────────────────╮"
						+"\n\t│                    SIMULAÇÃO                    │"
						+"\n\t├─────────────────────────────────────────────────┤"
						+"\n\t│     ╭─────────────────────────────────────╮     │"
						+"\n\t│     │ Digite quantos ciclos quer simular. │     │"
						+"\n\t│     ╰─────────────────────────────────────╯     │"
						+"\n\t│                ╭───────────────╮                │"
						+"\n\t│                │   0. Voltar   │                │"
						+"\n\t│                ╰───────────────╯                │"
						+"\n\t╰─────────────────────────────────────────────────╯"); 

		    int ciclos = Menu.readOpt(sc);
            Init.initSimulation(ciclos);
            
            switch(ciclos) {
                case 0:
                    simu = false;
                    break;
            } 
        }
	}
}
