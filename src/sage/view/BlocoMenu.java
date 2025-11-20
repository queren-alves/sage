package sage.view;

import java.util.Optional;
import java.util.Scanner;

import sage.controller.BlocoController;
import sage.model.Bloco;

public class BlocoMenu {
	
	public static void blockMenu(Scanner sc) {
		boolean block = true;
        while(block) {
        	
		System.out.println("\t╭─────────────────────────────────────────────────╮"
						+"\n\t│                      BLOCOS                     │"
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
            		block = false;
            		break;
            	default:
            		System.out.println("\t╭────────────────────────────────────╮"
                					+"\n\t│ Opção inválida! Escolha novamente. │"
                					+"\n\t╰────────────────────────────────────╯");
        	}
        }
	}
	
	public static void showCadastro(Scanner sc) {
		System.out.println("\t╭──────────────────────────╮"
						+"\n\t│  CADASTRO DE NOVO BLOCO  │"
						+"\n\t╰──────────────────────────╯");
		System.out.printf("Insira o ID do Bloco: ");
		int id = sc.nextInt();
		System.out.printf("Insira o nome do Bloco: ");
		String nome = sc.next();
		if(BlocoController.persist(new Bloco(id, nome)))
			System.out.println("\t╭───────────────────────────────╮"
							+"\n\t│ Bloco cadastrado com sucesso! │"
							+"\n\t╰───────────────────────────────╯");
		else
			System.out.println("\t╭────────────────────────────╮"
							+"\n\t│ Erro ao cadastrar o Bloco. │"
							+"\n\t╰────────────────────────────╯");
	}
	
	public static void showEditar(Scanner sc) {
		System.out.println("\t╭────────────────╮"
						+"\n\t│  EDITAR BLOCO  │"
						+"\n\t╰────────────────╯");
		System.out.printf("Insira o ID do Bloco: ");
		int id = sc.nextInt();
		Optional<Bloco> blocoOptional = BlocoController.findById(id);
		if(blocoOptional.isEmpty()) {
			System.out.println("\t╭──────────────────────╮"
							+"\n\t│ Bloco não existente. │"
							+"\n\t╰──────────────────────╯");
			return;
		}
		
		System.out.printf("Insira nome do Bloco (%s): ",blocoOptional.get().getNome());
		String nome = sc.next();
		BlocoController.save(new Bloco(id, nome));
		System.out.println("\t╭─────────────────────────────╮"
						+"\n\t│ Bloco alterado com sucesso! │"
						+"\n\t╰─────────────────────────────╯");
	}

	public static void showExcluir(Scanner sc) {
		System.out.println("\t╭─────────────────╮"
						+"\n\t│  EXCLUIR BLOCO  │"
						+"\n\t╰─────────────────╯");
		System.out.printf("Insira o ID do Bloco: ");
		int id = sc.nextInt();
		
		Optional<Bloco> bloco = BlocoController.findById(id);
		
		if(!bloco.get().getAmbientes().isEmpty()) {
			System.out.println("\t╭─────────────────────────────────────────────╮"
							+"\n\t│  Erro ao excluir, o Bloco possui Ambientes. │"
							+"\n\t╰─────────────────────────────────────────────╯");
			return;
		}
		
		if(BlocoController.removeById(id))
			System.out.println("\t╭─────────────────────────────╮"
							+"\n\t│ Bloco excluído com sucesso! │"
							+"\n\t╰─────────────────────────────╯");
		else
			System.out.println("\t╭──────────────────────────╮"
							+"\n\t│ Erro ao excluir o Bloco. │"
							+"\n\t╰──────────────────────────╯");
	}
	
}
