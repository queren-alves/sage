package sage.view;

import java.util.Scanner;

import sage.controller.AmbienteController;
import sage.controller.BlocoController;
import sage.controller.DispositivoController;
import sage.controller.PainelSolarController;
import sage.controller.SensorController;
import sage.controller.UsuarioController;

public class VisualizarMenu {
	
	public static void viewMenu(Scanner sc) {
		boolean view = true;
        while(view) {
        	System.out.println("\t╭─────────────────────────────────────────────────╮"
							+"\n\t│               VISUALIZAR CADASTROS              │"
							+"\n\t├─────────────────────────────────────────────────┤"
							+"\n\t│       ╭─────────────╮  ╭────────────────╮       │"
							+"\n\t│       │1. Ambiente  │  │2. Dispositivo  │       │"
							+"\n\t│       ╰─────────────╯  ╰────────────────╯       │"
							+"\n\t│       ╭─────────────╮  ╭────────────────╮       │"
							+"\n\t│       │3. Sensor    │  │4. Bloco        │       │"
							+"\n\t│       ╰─────────────╯  ╰────────────────╯       │"
							+"\n\t│       ╭─────────────╮  ╭────────────────╮       │"
							+"\n\t│       │5. Usuário   │  │6. Painel Solar │       │"
							+"\n\t│       ╰─────────────╯  ╰────────────────╯       │"
							+"\n\t│                 ╭─────────────╮                 │"
							+"\n\t│                 │0. Voltar    │                 │"
							+"\n\t│                 ╰─────────────╯                 │"
							+"\n\t╰─────────────────────────────────────────────────╯");
	
        	System.out.print("Escolha uma opção: ");
        	int opt = Menu.readOpt(sc);

        	switch(opt) {
    			case 1:
    				showAmbientes();
    				break;
    			case 2:
    				showDispositivos();
    				break;
    			case 3:
    				showSensores();
    				break;
    			case 4:
    				showBlocos();
    				break;
    			case 5:
    				showUsuarios();
    				break;
    			case 6:
    				showPaineisSolares();
    				break;
    			case 0:
    				view = false;
    				break;
    			default:
    				System.out.println("\t╭────────────────────────────────────╮"
            						+"\n\t│ Opção inválida! Escolha novamente. │"
            						+"\n\t╰────────────────────────────────────╯");
        	}
        }
	}

	public static void showUsuarios() {
		
		System.out.println("\t╭─────────────────────╮"
						+"\n\t│ VISUALIZAR USUÁRIOS │"
						+"\n\t╰─────────────────────╯");
		
		System.out.printf("\t┌───────┬───────┬───────────┐\n"
					  	 +"\t│NOME\t│TIPO\t│USERNAME   │\n"
					  	 +"\t└───────┴───────┴───────────┘\n");
		UsuarioController
			.findAll()
			.forEach(u -> System.out.printf("\t %s\t %s\t %s\n",u.getNome(),u.isAdmin() ? "ADMIN" : "COMUM",u.getUsername()));
			System.out.println("");
	}
	
	public static void showDispositivos() {
		System.out.println("\t╭─────────────────────────╮"
						+"\n\t│ VISUALIZAR DISPOSITIVOS │"
						+"\n\t╰─────────────────────────╯");

		if(DispositivoController.findAll().isEmpty())
			System.out.println("\t╭──────────────────────────────────╮"
							+"\n\t│ Não há dispositivos cadastrados. │"
							+"\n\t╰──────────────────────────────────╯");
		else
			System.out.printf("\t┌───────┬───────┬───────────────┬───────────────┬──────────────────┐\n"
							 +"\t│ID\t│NOME\t│AMBIENTE\t│CONSUMO\t│TIPO\t           │\n"
							 +"\t└───────┴───────┴───────────────┴───────────────┴──────────────────┘\n");
			DispositivoController
					.findAll()
					.forEach(d -> System.out.printf("\t %d\t %s\t %d\t\t %.2fW\t %s\n"
					,d.getId(),d.getNome(),d.getAmbiente().getId(),d.getConsumo(),d.getTipo()));
					System.out.println("");
	}
	
	public static void showBlocos() {
		
		System.out.println("\t╭───────────────────╮"
						+"\n\t│ VISUALIZAR BLOCOS │"
						+"\n\t╰───────────────────╯");
		
		if(BlocoController.findAll().isEmpty())
			System.out.println("\t╭────────────────────────────╮"
							+"\n\t│ Não há blocos cadastrados. │"
							+"\n\t╰────────────────────────────╯");
		else
			System.out.printf("\t┌───────┬───────┐\n"
							 +"\t│ID\t│NOME\t│\n"
							 +"\t└───────┴───────┘\n");
			BlocoController
				.findAll()
				.forEach(b -> System.out.printf("\t %d\t %s\t\n",b.getId(),b.getNome()));
				System.out.println("");
	}
	
	public static void showAmbientes() {
		
		System.out.println("\t╭──────────────────────╮"
						+"\n\t│ VISUALIZAR AMBIENTES │"
						+"\n\t╰──────────────────────╯");
		
		if(AmbienteController.findAll().isEmpty())
			System.out.println("\t╭───────────────────────────────╮"
							+"\n\t│ Não há ambientes cadastrados. │"
							+"\n\t╰───────────────────────────────╯");
		else
			System.out.printf("\t┌───────┬───────┬───────┬───────────────────┐\n"
							 +"\t│ID\t│NOME\t│BLOCO\t│TEMPO DE OCIOSIDADE│\n"
							 +"\t└───────┴───────┴───────┴───────────────────┘\n");
			AmbienteController
				.findAll()
				.forEach(a -> System.out.printf("\t %d\t %s\t %d\t %d Minutos\n",a.getId(),a.getNome(),a.getBloco().getId(),a.getTempoOciosidade()));
				System.out.println("");
	}

	public static void showSensores() {
		
		System.out.println("\t╭─────────────────────╮"
						+"\n\t│ VISUALIZAR SENSORES │"
						+"\n\t╰─────────────────────╯");

		if(SensorController.findAll().isEmpty())
			System.out.println("\t╭──────────────────────────────╮"
							+"\n\t│ Não há sensores cadastrados. │"
							+"\n\t╰──────────────────────────────╯");
		else
			System.out.printf("\t┌───────┬───────┬──────────┬───────────┐\n"
							 +"\t│ID\t│NOME\t│AMBIENTE  │FABRICANTE │\n"
							 +"\t└───────┴───────┴──────────┴───────────┘\n");
			SensorController
				.findAll()
				.forEach(s -> System.out.printf("\t %d\t %s\t %d\t    %s\n",s.getId(),s.getNome(),s.getAmbiente().getId(),s.getFabricante()));
				System.out.println("");
		
	}
	
	public static void showPaineisSolares() {
		System.out.println("\t╭────────────────────────────╮"
						+"\n\t│ VISUALIZAR PAINEIS SOLARES │"
						+"\n\t╰────────────────────────────╯");

		if(PainelSolarController.findAll().isEmpty())
			System.out.println("\t╭─────────────────────────────────────╮"
							+"\n\t│ Não há paineis solares cadastrados. │"
							+"\n\t╰─────────────────────────────────────╯");
		else
			System.out.printf("\t┌───────┬───────┬───────────────┐\n"
					 		 +"\t│ID\t│NOME\t│GERAÇÃO\t│\n"
					 		 +"\t└───────┴───────┴───────────────┘\n");
			PainelSolarController
			.findAll()
			.forEach(p -> System.out.printf("\t %d\t %s\t %.2fW\t\n"
			,p.getId(),p.getNome(),p.getGeracao()));
			System.out.println("");
}
	
}