package sage.view;

import java.util.Optional;
import java.util.Scanner;

import sage.controller.AmbienteController;
import sage.controller.SensorController;
import sage.model.Ambiente;
import sage.model.Sensor;

public class SensorMenu {
	
	public static void sensorMenu(Scanner sc) {
		boolean sensor = true;
        while(sensor) {
        	
		System.out.println("\t╭─────────────────────────────────────────────────╮"
						+"\n\t│               SENSOR DE PRESENÇA                │"
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
        			SensorMenu.showCadastro(sc);
        			break;
        		case 2:
        			SensorMenu.showEditar(sc);
        			break;
        		case 3:
        			SensorMenu.showExcluir(sc);
        			break;
        		case 0:
        			sensor = false;
        			break;
        		default:
        			System.out.println("\t╭────────────────────────────────────╮"
                					+"\n\t│ Opção inválida! Escolha novamente. │"
                					+"\n\t╰────────────────────────────────────╯");
        	}
        }
	}

	public static void showCadastro(Scanner sc) {
		System.out.println("\t╭───────────────────────────╮"
						+"\n\t│  CADASTRO DE NOVO SENSOR  │"
						+"\n\t╰───────────────────────────╯");
		System.out.printf("Insira o ID do Sensor: ");
		int id = sc.nextInt();
		System.out.printf("Insira o nome do Sensor: ");
		String nome = sc.next();
		System.out.printf("Insira o fabricante do Sensor: ");
		String fabricante = sc.next();
		System.out.printf("Insira o ID do Ambiente: ");
		int idAmbiente = sc.nextInt();
		
		Optional<Ambiente> ambiente = AmbienteController.findById(idAmbiente);
		if(ambiente.isEmpty()) {
			System.out.println("\t╭─────────────────────────╮"
							+"\n\t│ Ambiente não existente. │"
							+"\n\t╰─────────────────────────╯");
			return;
		}
		
		if(SensorController.persist(new Sensor(id, nome, fabricante, ambiente.get())))
			System.out.println("\t╭────────────────────────────────╮"
							+"\n\t│ Sensor cadastrado com sucesso! │"
							+"\n\t╰────────────────────────────────╯");
		else
			System.out.println("\t╭─────────────────────────────╮"
							+"\n\t│ Erro ao cadastrar o Sensor. │"
							+"\n\t╰─────────────────────────────╯");
	}
	
	public static void showEditar(Scanner sc) {
		System.out.println("\t╭─────────────────╮"
						+"\n\t│  EDITAR SENSOR  │"
						+"\n\t╰─────────────────╯");
		System.out.printf("Insira o ID do Sensor: ");
		int id = sc.nextInt();
		Optional<Sensor> sensorOptional = SensorController.findById(id);
		if(sensorOptional.isEmpty()) {
			System.out.println("\t╭───────────────────────╮"
							+"\n\t│ Sensor não existente. │"
							+"\n\t╰───────────────────────╯");
			return;
		}
		Sensor sensor = sensorOptional.get();
		System.out.printf("Insira o nome do Sensor (%s): ",sensor.getNome());
		String nome = sc.next();
		System.out.printf("Insira o fabricante do Sensor (%s): ",sensor.getFabricante());
		String fabricante = sc.next();
		SensorController.save(new Sensor(id, nome, fabricante));
		System.out.println("\t╭──────────────────────────────╮"
						+"\n\t│ Sensor alterado com sucesso! │"
						+"\n\t╰──────────────────────────────╯");
	}

	public static void showExcluir(Scanner sc) {
		System.out.println("\t╭──────────────────╮"
						+"\n\t│  EXCLUIR SENSOR  │"
						+"\n\t╰──────────────────╯");
		System.out.printf("Insira o ID do Sensor: ");
		int id = sc.nextInt();

		if(SensorController.removeById(id))
			System.out.println("\t╭──────────────────────────────╮"
							+"\n\t│ Sensor excluído com sucesso! │"
							+"\n\t╰──────────────────────────────╯");
		else
			System.out.println("\t╭───────────────────────────╮"
							+"\n\t│ Erro ao excluir o Sensor. │"
							+"\n\t╰───────────────────────────╯");
	}
	
}
