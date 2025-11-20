package sage.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import sage.model.RelatorioDiario;
import sage.service.RelatorioService;

public class RelatorioMenu {
	
	private static final RelatorioService relatorioService = new RelatorioService();

	public static void reportMenu(Scanner sc) {
		boolean report = true;
        while(report) {
        	
		System.out.println("\t╭─────────────────────────────────────────────────╮"
						+"\n\t│                   RELATÓRIOS                    │"
						+"\n\t├─────────────────────────────────────────────────┤"
						+"\n\t│      ╭────────────────╮  ╭────────────────╮     │"
						+"\n\t│      │ 1. Dia Atual   │  │2. Escolher Dia │     │"
						+"\n\t│      ╰────────────────╯  ╰────────────────╯     │"
						+"\n\t│      ╭────────────────╮  ╭────────────────╮     │"
						+"\n\t│      │ 3. Geral       │  │ 0. Voltar      │     │"
						+"\n\t│      ╰────────────────╯  ╰────────────────╯     │"
						+"\n\t╰─────────────────────────────────────────────────╯");
		
			System.out.print("Escolha uma opção: ");
			int opt = Menu.readOpt(sc);
			switch(opt) {
				case 1:
					showRelatorioDiaAtual();
					break;
				case 2:
					showRelatorio(sc);
					break;
				case 3:
					showRelatorioGeral(sc);
					break;
				case 0:
					report = false;
					break;
				default:
					System.out.println("\t╭────────────────────────────────────╮"
									+"\n\t│ Opção inválida! Escolha novamente. │"
									+"\n\t╰────────────────────────────────────╯");
			}
        }
	}
	
	public static void showRelatorioDiaAtual() {
		LocalDate date = LocalDate.now();
		relatorioHeader(date);
		relatorioService.gerarRelatorioDiario(date);
		List<RelatorioDiario> relatoriosDiarios = relatorioService.gerarRelatorioDiario(date);
		if(relatoriosDiarios.isEmpty()) {
			System.out.println(""
					  +"\t╭──────────────────────────────────╮"
					+"\n\t│ Não há registros para essa data. │"
					+"\n\t╰──────────────────────────────────╯");
			return;
		}
		showReport(relatoriosDiarios);
	}
	
	public static void showRelatorio(Scanner sc) {
		System.out.printf("Insira a data a ser pesquisada (dd/MM/yyyy): ");
		String dateString = sc.next();
		LocalDate date;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			formatter = formatter.withLocale(Locale.getDefault());
			date = LocalDate.parse(dateString, formatter);
		}catch (Exception e) {
			System.out.println(""
					  +"\t╭──────────────────────────────╮"
					+"\n\t│ A data informada é inválida. │"
					+"\n\t╰──────────────────────────────╯");
			return;
		}
		
		relatorioHeader(date);
		List<RelatorioDiario> relatoriosDiarios = relatorioService.gerarRelatorioDiario(date);
		if(relatoriosDiarios.isEmpty()) {
			System.out.println(""
					  +"\t╭──────────────────────────────────╮"
					+"\n\t│ Não há registros para essa data. │"
					+"\n\t╰──────────────────────────────────╯");
			return;
		}
		
		showReport(relatoriosDiarios);

	}
	
	public static void showRelatorioGeral(Scanner sc) {
		System.out.printf("Insira a data a ser pesquisada (dd/MM/yyyy): ");
		String dateString = sc.next();
		LocalDate date;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			formatter = formatter.withLocale(Locale.getDefault());
			date = LocalDate.parse(dateString, formatter);
		}catch (Exception e) {
			System.out.println(""
					  +"\t╭──────────────────────────────╮"
					+"\n\t│ A data informada é inválida. │"
					+"\n\t╰──────────────────────────────╯");
			return;
		}
		
		relatorioHeader(date);
		List<RelatorioDiario> relatoriosDiarios = relatorioService.gerarRelatorioDiario(date);
		if(relatoriosDiarios.isEmpty()) {
			System.out.println(""
					  +"\t╭──────────────────────────────────╮"
					+"\n\t│ Não há registros para essa data. │"
					+"\n\t╰──────────────────────────────────╯");
			return;
		}
		
		showTotalReport(relatoriosDiarios);

	}
	
	public static void showReport(List<RelatorioDiario> relatoriosDiarios) {
		relatoriosDiarios.forEach(r -> {
			System.out.printf("\tAmbiente %s ficou ligado por %d minutos e consumiu %.2f kW de energia elétrica.\n"
							 +"\tCom a geração solar de %.2f kW resultou em %.2f kW no consumo final deste ambiente.\n"
							 +"\tO custo total foi de R$ %.2f. Já com a geração solar, teve um custo de R$ %.2f\n"
							 +"\tuma economia de R$ %.2f.\n",
				r.getAmbiente().getNome(), 
				r.getMinutos(),
				r.getConsumo(),
				r.getGeracao(),
				r.getConsumoFinal(),
				r.getCustoSemGeracao(),
				r.getCustoComGeracao(),
				r.economia());
			System.out.println("\t\t\t──────────────────────────────────────────────────");
		});
	}
	
	public static void showTotalReport(List<RelatorioDiario> relatoriosDiarios) {
	    double consumoTotal = 0;
	    double geracaoTotal = 0;
	    double custoSemGeracaoTotal = 0;
	    double custoComGeracaoTotal = 0;

	    for (RelatorioDiario r : relatoriosDiarios) {
	        consumoTotal += r.getConsumo();
	        geracaoTotal += r.getGeracao();
	        custoSemGeracaoTotal += r.getCustoSemGeracao();
	        custoComGeracaoTotal += r.getCustoComGeracao();
	    }

	    double economiaTotal = custoSemGeracaoTotal - custoComGeracaoTotal;
	    
	    System.out.println("\t──────────────────────────────────────────────────");
	    System.out.printf( "\tConsumo total (todos os ambientes): %.2f kW\n"
		        		  +"\tGeração solar total aplicada: %.2f kW\n"
		        		  +"\tCusto estimado total (sem geração): R$ %.2f\n"
		        		  +"\tCusto total com geração solar: R$ %.2f\n"
		        		  +"\tEconomia total estimada: R$ %.2f\n"
		        		  +"\t──────────────────────────────────────────────────\n",
				 consumoTotal,
				 geracaoTotal,
				 custoSemGeracaoTotal,
				 custoComGeracaoTotal,
				 economiaTotal);
	}
	
	public static void relatorioHeader(LocalDate date) {
		String time = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.printf("\t╭───────────────────────────────╮"
						+"\n\t│  RELATÓRIO DO DIA %s  │"
						+"\n\t╰───────────────────────────────╯\n",time);
	}
	
}
