package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import model.entities.CalculoSalario;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sx = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);
		List<CalculoSalario> list = new ArrayList<>();
		CalculoSalario calc = new CalculoSalario();
		
		try {

		String opcao = calc.mostrarMenu();
		
		while (!opcao.equals("3")) {
				
				
		switch (opcao) {
				
			case "1":
				System.out.print("Digite o nome do funcionário: ");
				String funcionario = sx.nextLine();
				System.out.print("Digite o salário bruto do colaborador: ");
				double salarioBruto = sc.nextDouble();
				
				list.add(new CalculoSalario(funcionario, salarioBruto));
				System.out.println();
				opcao = calc.mostrarMenu();
				break;
			
			case "2":	
				System.out.println("----------------------");
				System.out.println("***Menu Funcionário***");
				System.out.println("----------------------");
				System.out.println();
				for(int i = 0; i<list.size();i++) {
				System.out.println("Índice: " + i + " Nome funcionário: " + list.get(i).getFuncionario());
				}
				System.out.println();
				System.out.print("Digite o índice do funcionário para imprimir o Holerith: ");
				int j = sc.nextInt();			
				
				for ( int i = 0; i < list.size(); i++) {
					if(i == j) {
					System.out.println(list.get(i));
					}
				}					
				System.out.println();
				opcao = calc.mostrarMenu();
				break;
		
			default:
				System.out.println("Opção inválida");
				System.out.println();
				opcao = calc.mostrarMenu(); 
			}	

		}	
	}
		
		catch (DomainException e) {
			System.out.println("Erro no cadastro: "+ e.getMessage());
		}
		
		catch (RuntimeException e) {
			System.out.println("unexpected error");
		}	
		
		System.out.println();
		System.out.print("Fim do programa!");
		sx.close();
		sc.close();
	}
	
}
