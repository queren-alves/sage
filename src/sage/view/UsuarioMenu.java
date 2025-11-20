package sage.view;

import java.util.Optional;
import java.util.Scanner;

import sage.controller.UsuarioController;
import sage.model.Usuario;

public class UsuarioMenu {

	public static void userMenu(Scanner sc) {
		boolean user = true;
        while(user) {
        	
		System.out.println("\t╭─────────────────────────────────────────────────╮"
						+"\n\t│                     USUÁRIOS                    │"
						+"\n\t├─────────────────────────────────────────────────┤"
						+"\n\t│        ╭─────────────╮  ╭─────────────╮         │"
						+"\n\t│        │1. Cadastrar │  │2. Excluir   │         │"
						+"\n\t│        ╰─────────────╯  ╰─────────────╯         │"
						+"\n\t│        ╭─────────────╮  ╭─────────────╮         │"
						+"\n\t│        │3. Redefinir │  │0. Voltar    │         │"
						+"\n\t│        ╰─────────────╯  ╰─────────────╯         │"
						+"\n\t╰─────────────────────────────────────────────────╯");
		
			System.out.print("Escolha uma opção: ");
			int opt = Menu.readOpt(sc);

        	switch(opt) {
            	case 1:
            		registerUser(sc);
            		break;
            	case 2:
            		deleteUser(sc);
            		break;
            	case 3:
            		redefineUser(sc);
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
	
	public static void loginUser(Scanner sc) throws InterruptedException {
		System.out.println("\t╭─────────────────╮"
						+"\n\t│ ACESSAR USUÁRIO │"
						+"\n\t╰─────────────────╯");
		System.out.printf("Usuário: ");
        String username = sc.next();                                       

        System.out.printf("Senha: ");
        String senha = sc.next();
        
        Optional<Usuario> user = UsuarioController.findByUsernameAndSenha(username, senha);
        
        if(user.isPresent()) {
        	System.out.println("\n\t      Login bem-sucedido. Bem-vindo(a), "+ user.get().getNome() + "! "
					  		+"\n\t      ────────────────────────────────────────" );
        		if(user.get().isAdmin()) {
                	Menu.adminMenu(sc, user.get());
        		}else {
                    Menu.comumMenu(sc, user.get());
        		}
        		return;
        }

        System.out.println("\t╭─────────────────────────────╮"
        				+"\n\t│ Usuário ou senha inválidos. │"
        				+"\n\t╰─────────────────────────────╯");
	}
	
	public static void redefineUser(Scanner sc) {
		
		System.out.println("\t╭────────────────────────────╮"
						+"\n\t│ REDEFINIR SENHA DO USUÁRIO │"
						+"\n\t╰────────────────────────────╯");
		System.out.printf("Username: ");
		String username = sc.next();
		System.out.printf("Nova senha: ");
		String senha = sc.next();
		
		if(UsuarioController.resetPasswordByUsername(username, senha))
			System.out.println("\t╭───────────────────────────────╮"
							+"\n\t│ Senha redefinida com sucesso! │"
							+"\n\t╰───────────────────────────────╯");
		else
			System.out.println("\t╭────────────────────────────╮"
							+"\n\t│ Erro ao redefinir a Senha. │"
							+"\n\t╰────────────────────────────╯");
		
	}
	
	public static void deleteUser(Scanner sc) {
		System.out.println("\t╭─────────────────╮"
						+"\n\t│ EXCLUIR USUÁRIO │"
						+"\n\t╰─────────────────╯");
		System.out.printf("Username: ");
		String username = sc.next();
	
		if(UsuarioController.removeByUsername(username))
			System.out.println("\t╭───────────────────────────────╮"
							+"\n\t│ Usuário excluído com sucesso! │"
							+"\n\t╰───────────────────────────────╯");
		else
			System.out.println("\t╭────────────────────────────╮"
							+"\n\t│ Erro ao excluir o Usuário. │"
							+"\n\t╰────────────────────────────╯");

	}
	
	public static void registerUser(Scanner sc) {
		System.out.println("\t╭──────────────────────────╮"
						+"\n\t│ CADASTRO DE NOVO USUÁRIO │"
						+"\n\t╰──────────────────────────╯");
		System.out.printf("Nome do novo usuário: ");
		String nome = sc.next();
		System.out.printf("Username: ");
		String username = sc.next();
		System.out.printf("Tipo (admin/comum): ");
		String tipo = sc.next().toLowerCase();
		System.out.printf("Senha: ");
		String senha = sc.next();
		
		if(UsuarioController.persist(new Usuario(nome, username, tipo.equals("admin"), senha)))
			System.out.println("\t╭─────────────────────────────────╮"
							+"\n\t│ Usuário cadastrado com sucesso! │"
							+"\n\t╰─────────────────────────────────╯");
		else
			System.out.println("\t╭──────────────────────────────╮"
							+"\n\t│ Erro ao cadastrar o Usuário. │"
							+"\n\t╰──────────────────────────────╯");
	}
	
}
