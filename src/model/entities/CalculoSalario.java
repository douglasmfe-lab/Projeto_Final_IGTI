package model.entities;

import java.util.Scanner;

import model.exceptions.DomainException;

public class CalculoSalario {
	
	private String funcionario;
	private Double salarioBruto;
	
	public CalculoSalario() {
	}

	public CalculoSalario(String funcionario, Double salarioBruto) {
		
		if(funcionario.isEmpty()) {
			throw new DomainException("O funcionário deve ter um nome!");
		}
				
		if(salarioBruto <= 0.0) {
			throw new DomainException("O valor do salário deve ser maior que zero!");
		}
				
		this.funcionario = funcionario;
		this.salarioBruto = salarioBruto;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(Double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}
	
	
	public double calculoInss() {
		double sum = 0.0;
		
		if(salarioBruto <= 0.0) {
			throw new DomainException("O valor do salário deve ser maior que zero!");
		}
						
		if (salarioBruto <= 1045.00) {
			sum = (salarioBruto * 0.075) + 0.01;
		}
		else if ((salarioBruto >= 1045.01) && (salarioBruto <= 2089.60)) {
			sum = (salarioBruto - 1045.01) * 0.09 + 78.38;
		}
		else if ((salarioBruto >= 2089.61) && (salarioBruto <= 3134.40)) {
			sum = (salarioBruto - 2089.61) * 0.12 + 78.38 + 94.01;	
		}
		else if ((salarioBruto >= 3134.41) && (salarioBruto <= 6101.06)) {
			sum = (salarioBruto - 3134.41) * 0.14 + 78.38 + 94.01 + 125.38;
						
		}
		else {
			sum = 713.10;
			
		}
					
		return sum;
	}
	
	
	public double calculoIrpf() {
		
		if(salarioBruto <= 0.0) {
			throw new DomainException("O valor do salário deve ser maior que zero!");
		}
		
		double baseCalculoIRPF = salarioBruto - calculoInss();
		double sum = 0.0;
		
		
		if (baseCalculoIRPF <= 1903.98) {
			return 0.0;
		}
		
		else if ((baseCalculoIRPF >= 1903.99) && (baseCalculoIRPF <= 2826.65)) {
			sum = (baseCalculoIRPF * 0.075) - 142.80;
		}
		
		else if ((baseCalculoIRPF >= 2826.66) && (baseCalculoIRPF <= 3751.05)) {
			sum = (baseCalculoIRPF * 0.15) - 354.80;
		} 
		
		else if ((baseCalculoIRPF >= 3751.06) && (baseCalculoIRPF <= 4664.68)) {
			sum = (baseCalculoIRPF * 0.225) - 636.13;
		}
		
		else {
			sum = (baseCalculoIRPF * 0.275) - 869.36;
		}
		
		return sum;
			
	}
	
	public double salarioLiquido() {
		
		if(salarioBruto <= 0.0) {
			throw new DomainException("O valor do salário deve ser maior que zero!");
		}
		
		return (this.salarioBruto) - calculoInss() - calculoIrpf();
	}
	
	
public String mostrarMenu() {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		sb.append("----------------------------------------\n");
		sb.append("*****Hollerith*****\n");
		sb.append("----------------------------------------\n");
		sb.append("1 - Cadastrar funcionário\n");
		sb.append("2 - Imprimir Contracheque\n");
		sb.append("3 - Sair \n");
		
		System.out.println(sb.toString());
		System.out.print("Digite a opção desejada: ");
		return sc.nextLine();
}
	

		@Override
	public String toString() {
		return  "\nNome do funcionário = "
				+ funcionario
				+ "\n"
				+ "Salário Bruto = R$ "
				+ salarioBruto
				+ "\n"
				+ "Desconto INSS = R$ "
				+ String.format("%.2f%n", calculoInss())
				+ "Desconto IRPF = R$ "
				+ String.format("%.2f%n", calculoIrpf())
				+ "Salário Liquido = R$ "
				+ String.format("%.2f%n", salarioLiquido());
	}	
			
}
