package sage.view;

import java.util.Optional;
import java.util.Scanner;

import sage.controller.PainelSolarController;
import sage.model.PainelSolar;

public class PainelSolarMenu {
	
	public static void painelMenu(Scanner sc) {
		boolean painel = true;
        while(painel) {
        	System.out.println("\t╭─────────────────────────────────────────────────╮"
				 			+"\n\t│                   PAINEL SOLAR                  │" 
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
    				painel = false;
    				break;
    			default:
    				System.out.println("\t╭────────────────────────────────────╮"
            						+"\n\t│ Opção inválida! Escolha novamente. │"
            						+"\n\t╰────────────────────────────────────╯");
        	}
        }
	}
	
	public static void showCadastro(Scanner sc) {
		System.out.println("\t╭─────────────────────────────────╮"
						+"\n\t│  CADASTRO DE NOVO PAINEL SOLAR  │"
						+"\n\t╰─────────────────────────────────╯");
		System.out.printf("Insira o ID do Painel Solar: ");
		int id = sc.nextInt();
		System.out.printf("Insira o nome do Painel Solar: ");
		String nome = sc.next();
		System.out.printf("Insira a geração do Painel Solar: ");
		double geracao = sc.nextDouble();
		if(PainelSolarController.persist(new PainelSolar(id, nome, geracao)))
			System.out.println("\t╭──────────────────────────────────────╮"
							+"\n\t│ Painel Solar cadastrado com sucesso! │"
							+"\n\t╰──────────────────────────────────────╯");
		else
			System.out.println("\t╭───────────────────────────────────╮"
							+"\n\t│ Erro ao cadastrar o Painel Solar. │"
							+"\n\t╰───────────────────────────────────╯");
	}
	
	public static void showEditar(Scanner sc) {
		System.out.println("\t╭───────────────────────╮"
						+"\n\t│  EDITAR PAINEL SOLAR  │"
						+"\n\t╰───────────────────────╯");
		System.out.printf("Insira o ID do Painel Solar: ");
		int id = sc.nextInt();
		Optional<PainelSolar> painelOptional = PainelSolarController.findById(id);
		if(painelOptional.isEmpty()) {
			System.out.println("\t╭─────────────────────────────╮"
							+"\n\t│ Painel Solar não existente. │"
							+"\n\t╰─────────────────────────────╯");
			return;
		}
		
		System.out.printf("Insira nome do Painel Solar (%s): ",painelOptional.get().getNome());
		String nome = sc.next();
		System.out.printf("Insira a geração do Painel Solar (%.2f): ",painelOptional.get().getGeracao());
		double geracao = sc.nextDouble();
		PainelSolarController.save(new PainelSolar(id, nome, geracao));
		System.out.println("\t╭────────────────────────────────────╮"
						+"\n\t│ Painel Solar alterado com sucesso! │"
						+"\n\t╰────────────────────────────────────╯");
	}

	public static void showExcluir(Scanner sc) {
		System.out.println("\t╭────────────────────────╮"
						+"\n\t│  EXCLUIR PAINEL SOLAR  │"
						+"\n\t╰────────────────────────╯");
		System.out.printf("Insira o ID do Painel Solar: ");
		int id = sc.nextInt();
		
		if(PainelSolarController.removeById(id))
			System.out.println("\t╭────────────────────────────────────╮"
							+"\n\t│ Painel Solar excluído com sucesso! │"
							+"\n\t╰────────────────────────────────────╯");
		else
			System.out.println("\t╭─────────────────────────────────╮"
							+"\n\t│ Erro ao excluir o Painel Solar. │"
							+"\n\t╰─────────────────────────────────╯");
	}
	
}
