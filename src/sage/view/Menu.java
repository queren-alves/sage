package sage.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import sage.controller.SimuladorController;
import sage.model.Ambiente;
import sage.model.Usuario;

public class Menu {

	public static void header() {
		LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        System.out.println("\t╭─────────────────────────────────────────────────╮"
        				+"\n\t│             ─────── SAGE ───────                │"
        				+"\n\t│ Sistema de Automação e Gerenciamento Energético │"
        				+"\n\t│        Data/Hora: "+ time + "           │"
        				+"\n\t╰─────────────────────────────────────────────────╯");
	}
	
	public static void firstOption() throws InterruptedException {
		Menu.header();
		Scanner sc = new Scanner(System.in);
		boolean first = true;
		while(first) {
			
			System.out.println("\t╭─────────────────────────────────────────────────╮"
							+"\n\t│                    BEM VINDO(A)                 │"
							+"\n\t├─────────────────────────────────────────────────┤"
							+"\n\t│         ╭─────────────╮  ╭────────────╮         │"
							+"\n\t│         │1. Login     │  │0. Sair     │         │"
							+"\n\t│         ╰─────────────╯  ╰────────────╯         │"
							+"\n\t╰─────────────────────────────────────────────────╯");
			
			System.out.print("Escolha uma opção: ");
			int opt = readOpt(sc);

            switch(opt) {
                case 1:
                    UsuarioMenu.loginUser(sc);
                    break;
                case 0:
                	SimuladorController.parar();
                	Ambiente.shutdownScheduler();
                    first = false;
                    break;
                default:
                    System.out.println("\t╭────────────────────────────────────╮"
                    				+"\n\t│ Opção inválida! Escolha novamente. │"
                    				+"\n\t╰────────────────────────────────────╯");
            } 
        }
		
		 System.out.println("\t╭──────────────────────────────╮"
 						 +"\n\t│ Sistema encerrado. Até logo! │"
 						 +"\n\t╰──────────────────────────────╯");
		sc.close();
	}
	
	public static void adminMenu(Scanner sc, Usuario u) throws InterruptedException {
		Menu.header();
		boolean menu = true;
        while(menu) {
        	
		System.out.println("\t╭─────────────────────────────────────────────────╮"
						+"\n\t│                MENU ADMINISTRADOR               │"
						+"\n\t├─────────────────────────────────────────────────┤"
						+"\n\t│       ╭───────────────╮ ╭───────────────╮       │"
						+"\n\t│       │1. Ambiente    │ │2. Dispositivo │       │"
						+"\n\t│       ╰───────────────╯ ╰───────────────╯       │"
						+"\n\t│       ╭───────────────╮ ╭───────────────╮       │"
						+"\n\t│       │3. Sensor      │ │4. Bloco       │       │"
						+"\n\t│       ╰───────────────╯ ╰───────────────╯       │"
						+"\n\t│       ╭───────────────╮ ╭───────────────╮       │"
						+"\n\t│       │5. Usuário     │ │6. Painel Solar│       │"
						+"\n\t│       ╰───────────────╯ ╰───────────────╯       │"
						+"\n\t│       ╭───────────────╮ ╭───────────────╮       │"
						+"\n\t│       │7. Visualizar  │ │8. Simular     │       │"
						+"\n\t│       ╰───────────────╯ ╰───────────────╯       │"
						+"\n\t│       ╭───────────────╮ ╭───────────────╮       │"
						+"\n\t│       │9. Relatório   │ │0. Logout      │       │"
						+"\n\t│       ╰───────────────╯ ╰───────────────╯       │"
						+"\n\t╰─────────────────────────────────────────────────╯"); 

            System.out.print("Escolha uma opção: ");
            int opt = readOpt(sc);

            switch(opt) {
                case 1:
                    AmbienteMenu.roomMenu(sc);
                    break;
                case 2:
                    DispositivoMenu.deviceMenu(sc);
                    break;
                case 3:
                    SensorMenu.sensorMenu(sc);
                    break;
                case 4:
                    BlocoMenu.blockMenu(sc);
                    break;
                case 5:
                    UsuarioMenu.userMenu(sc);
                    break;
                case 6:
                	PainelSolarMenu.painelMenu(sc);
                    break;
                case 7:
                	VisualizarMenu.viewMenu(sc);
                    break;
                case 8:
                	SimulacaoMenu.simulationMenu(sc);
                	break;
                case 9:
                	RelatorioMenu.reportMenu(sc);
                	break;
                case 0:
                	System.out.println("\t╭───────────────────────╮"
                					+"\n\t│ Usuário desconectado. │"
                					+"\n\t╰───────────────────────╯");
                    menu = false;
                    break;
                default:
                    System.out.println("\t╭────────────────────────────────────╮"
                    				+"\n\t│ Opção inválida! Escolha novamente. │"
                    				+"\n\t╰────────────────────────────────────╯");
            }
        }
	}
	
	public static void comumMenu(Scanner sc, Usuario u) {
		boolean user = true;
        while(user) {
	
		System.out.println("\t╭─────────────────────────────────────────────────╮"
						+"\n\t│                   MENU USUÁRIO                  │"
						+"\n\t├─────────────────────────────────────────────────┤"
						+"\n\t│       ╭───────────────╮ ╭───────────────╮       │"
						+"\n\t│       │1. Visualizar  │ │2. Relatórios  │       │"
						+"\n\t│       ╰───────────────╯ ╰───────────────╯       │"
						+"\n\t│                ╭───────────────╮                │"
						+"\n\t│                │0. Logout      │                │"
						+"\n\t│                ╰───────────────╯                │"
						+"\n\t╰─────────────────────────────────────────────────╯"); 
		
			System.out.print("Escolha uma opção: ");
			int opt = readOpt(sc);

			switch(opt) {
        		case 1:
        			VisualizarMenu.viewMenu(sc);
        			break;
        		case 2:
        			RelatorioMenu.reportMenu(sc);
        			break;
        		case 0:
        			user = false;
        			break;
        		default:
        			System.out.println("\t╭────────────────────────────────────╮"
            						+"\n\t│ Opção inválida! Escolha novamente. │"
            						+"\n\t╰────────────────────────────────────╯");
			}
       }
    }
	
	public static void loading(String txt) throws InterruptedException {
		System.out.print(txt);
        for (int i = 0; i < 4; i++) {
            Thread.sleep(400);
            System.out.print(".");
        }
        System.out.println("\n\t╭────────────────────────────────╮"
        				  +"\n\t│ Sistema carregado com sucesso! │"
        				  +"\n\t╰────────────────────────────────╯");
	}
	
	public static int readOpt(Scanner sc) {
        while (!sc.hasNextInt()) {
        	 System.out.println("\t╭──────────────────────────╮"
             				 +"\n\t│ Digite um número válido! │"
             				 +"\n\t╰──────────────────────────╯");
            sc.next();
            System.out.print("Escolha novamente: ");
        }
        return sc.nextInt();
    }
	
}