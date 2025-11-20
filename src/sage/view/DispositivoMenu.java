package sage.view;

import java.util.Optional;
import java.util.Scanner;

import sage.controller.AmbienteController;
import sage.controller.DispositivoController;
import sage.model.Ambiente;
import sage.model.Dispositivo;

public class DispositivoMenu {
	
	public static void deviceMenu(Scanner sc) {
		boolean device = true;
        while(device) {
        	
		System.out.println("\t╭─────────────────────────────────────────────────╮"
						+"\n\t│                   DISPOSITIVOS                  │"
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
					device = false;
					break;
				default:
					System.out.println("\t╭────────────────────────────────────╮"
									+"\n\t│ Opção inválida! Escolha novamente. │"
									+"\n\t╰────────────────────────────────────╯");
			}
        }
	}

	public static void showCadastro(Scanner sc) {
		System.out.println("\t╭────────────────────────────────╮"
						+"\n\t│  CADASTRO DE NOVO DISPOSITIVO  │"
						+"\n\t╰────────────────────────────────╯");
		System.out.printf("Insira o ID do Dispositivo: ");
		int id = sc.nextInt();
		System.out.printf("Insira o nome do Dispositivo: ");
		String nome = sc.next();
		System.out.printf("Insira o tipo do Dispositivo: ");
		String tipo = sc.next();
		System.out.printf("Insira o consumo do Dispositivo (W): ");
		double consumo = sc.nextDouble();
		System.out.printf("Insira o ID do Ambiente: ");
		int idAmbiente = sc.nextInt();
		
		Optional<Ambiente> ambiente = AmbienteController.findById(idAmbiente);
		if(ambiente.isEmpty()) {
			System.out.println("\t╭─────────────────────────╮"
							+"\n\t│ Ambiente não existente. │"
							+"\n\t╰─────────────────────────╯");
			return;
		}
		
		if(DispositivoController.persist(new Dispositivo(id, nome, tipo, consumo, ambiente.get())))
			System.out.println("\t╭─────────────────────────────────────╮"
							+"\n\t│ Dispositivo cadastrado com sucesso! │"
							+"\n\t╰─────────────────────────────────────╯");
		else
			System.out.println("\t╭──────────────────────────────────╮"
							+"\n\t│ Erro ao cadastrar o Dispositivo. │"
							+"\n\t╰──────────────────────────────────╯");
	}
	
	public static void showEditar(Scanner sc) {
		System.out.println("\t╭──────────────────────╮"
						+"\n\t│  EDITAR DISPOSITIVO  │"
						+"\n\t╰──────────────────────╯");
		System.out.printf("Insira o ID do Dispositivo: ");
		int id = sc.nextInt();
		Optional<Dispositivo> dispositivoOptional = DispositivoController.findById(id);
		if(dispositivoOptional.isEmpty()) {
			System.out.println("\t╭────────────────────────────╮"
							+"\n\t│ Dispositivo não existente. │"
							+"\n\t╰────────────────────────────╯");
			return;
		}
		Dispositivo dispositivo = dispositivoOptional.get();
		System.out.printf("Insira o nome do Dispositivo (%s): ",dispositivo.getNome());
		String nome = sc.next();
		System.out.printf("Insira o tipo do Dispositivo (%s): ",dispositivo.getTipo());
		String tipo = sc.next();
		System.out.printf("Insira o consumo do Dispositivo (%.2f): ",dispositivo.getConsumo());
		double consumo = sc.nextDouble();
		DispositivoController.save(new Dispositivo(id, nome, tipo, consumo));
		System.out.println("\t╭───────────────────────────────────╮"
						+"\n\t│ Dispositivo alterado com sucesso! │"
						+"\n\t╰───────────────────────────────────╯");
	}

	public static void showExcluir(Scanner sc) {
		System.out.println("\t╭───────────────────────╮"
						+"\n\t│  EXCLUIR DISPOSITIVO  │"
						+"\n\t╰───────────────────────╯");
		System.out.printf("Insira o ID do Dispositivo: ");
		int id = sc.nextInt();

		if(DispositivoController.removeById(id))
			System.out.println("\t╭───────────────────────────────────╮"
							+"\n\t│ Dispositivo excluído com sucesso! │"
							+"\n\t╰───────────────────────────────────╯");
		else
			System.out.println("\t╭────────────────────────────────╮"
							+"\n\t│ Erro ao excluir o Dispositivo. │"
							+"\n\t╰────────────────────────────────╯");
	}
	
}
