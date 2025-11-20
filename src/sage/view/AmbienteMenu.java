package sage.view;

import java.util.Optional;
import java.util.Scanner;

import sage.controller.AmbienteController;
import sage.controller.BlocoController;
import sage.model.Ambiente;
import sage.model.Bloco;

public class AmbienteMenu {
	
	public static void roomMenu(Scanner sc) {
		boolean room = true;
        while(room) {
        	
		System.out.println("\t╭─────────────────────────────────────────────────╮"
					 	+"\n\t│                    AMBIENTES                    │" 
						+"\n\t├─────────────────────────────────────────────────┤"
						+"\n\t│         ╭─────────────╮  ╭────────────╮         │"
						+"\n\t│         │1. Cadastrar │  │2. Editar   │         │"
						+"\n\t│         ╰─────────────╯  ╰────────────╯         │"
						+"\n\t│         ╭─────────────╮  ╭────────────╮         │"
						+"\n\t│         │3. Excluir   │  │0. Voltar   │         │"
						+"\n\t│         ╰─────────────╯  ╰────────────╯         │"
						+"\n\t╰─────────────────────────────────────────────────╯");
		
			System.out.print("Escolha uma opção: ");
			int opt = Menu.readOpt(sc);

        	switch(opt) {
            	case 1:
            		showCadastro(sc);
            		break;
            	case 2:
            		showEditar(sc);
            		break;
            	case 3:
            		showExcluir(sc);
            		break;
            	case 0:
            		room = false;
            		break;
            	default:
            		System.out.println("\t╭────────────────────────────────────╮"
                					+"\n\t│ Opção inválida! Escolha novamente. │"
                					+"\n\t╰────────────────────────────────────╯");
        	}
        }
		
	}

	public static void showCadastro(Scanner sc) {
		System.out.println("\t╭─────────────────────────────╮"
						+"\n\t│  CADASTRO DE NOVO AMBIENTE  │"
						+"\n\t╰─────────────────────────────╯");
		System.out.printf("Insira o ID do Ambiente: ");
		int id = sc.nextInt();
		System.out.printf("Insira o nome do Ambiente: ");
		String nome = sc.next();
		System.out.printf("Insira o tempo de ociosidade do Ambiente (MINUTOS): ");
		int tempoOciosidade = sc.nextInt();
		System.out.printf("Insira o ID do Bloco: ");
		int blocoId = sc.nextInt();
		
		Optional<Bloco> bloco = BlocoController.findById(blocoId);
		
		if(bloco.isEmpty()) {
			System.out.println("\t╭──────────────────────╮"
							+"\n\t│ Bloco não existente. │"
							+"\n\t╰──────────────────────╯");
			return;
		}
		
		if(AmbienteController.persist(new Ambiente(id, nome, tempoOciosidade, bloco.get())))
			System.out.println("\t╭──────────────────────────────────╮"
							+"\n\t│ Ambiente cadastrado com sucesso! │"
							+"\n\t╰──────────────────────────────────╯");
		else
			System.out.println("\t╭───────────────────────────────╮"
							+"\n\t│ Erro ao cadastrar o Ambiente. │"
							+"\n\t╰───────────────────────────────╯");
		
	}

	public static void showEditar(Scanner sc) {
		System.out.println("\t╭───────────────────╮"
						+"\n\t│  EDITAR AMBIENTE  │"
						+"\n\t╰───────────────────╯");
		System.out.printf("Insira o ID do Ambiente: ");
		int id = sc.nextInt();
		Optional<Ambiente> ambienteOptional = AmbienteController.findById(id);
		if(ambienteOptional.isEmpty()) {
			System.out.println("\t╭─────────────────────────╮"
							+"\n\t│ Ambiente não existente. │"
							+"\n\t╰─────────────────────────╯");
			return;
		}
			
		Ambiente ambiente = ambienteOptional.get();
		System.out.printf("Insira o nome do Ambiente (%s): ",ambiente.getNome());
		String nome = sc.next();	
		System.out.printf("Insira o novo tempo de ociosidade do Ambiente (MINUTOS): ");
		int tempoOciosidade = sc.nextInt();
		
		AmbienteController.save(new Ambiente(id, nome, tempoOciosidade));
		System.out.println("\t╭────────────────────────────────╮"
						+"\n\t│ Ambiente alterado com sucesso! │"
						+"\n\t╰────────────────────────────────╯");
	}

	public static void showExcluir(Scanner sc) {
		System.out.println("\t╭────────────────────╮"
						+"\n\t│  EXCLUIR AMBIENTE  │"
						+"\n\t╰────────────────────╯");
		System.out.printf("Insira o ID do Ambiente: ");
		int id = sc.nextInt();
		
		Optional<Ambiente> ambiente = AmbienteController.findById(id);
		
		if(!ambiente.get().getDispositivos().isEmpty() || !ambiente.get().getSensores().isEmpty()) {
			System.out.println("\t╭───────────────────────────────────────────────────────────────╮"
							+"\n\t│  Erro ao excluir, o Ambiente possui Dispositivos ou Sensores. │"
							+"\n\t╰───────────────────────────────────────────────────────────────╯");
			return;
		}
		
		if(AmbienteController.removeById(id))
			System.out.println("\t╭────────────────────────────────╮"
							+"\n\t│ Ambiente excluído com sucesso! │"
							+"\n\t╰────────────────────────────────╯");
		else
			System.out.println("\t╭─────────────────────────────╮"
							+"\n\t│ Erro ao excluir o Ambiente. │"
							+"\n\t╰─────────────────────────────╯");
	}
}
