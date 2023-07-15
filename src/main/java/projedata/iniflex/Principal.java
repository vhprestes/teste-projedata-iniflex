package projedata.iniflex;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import projedata.iniflex.entity.Funcionario;
import projedata.iniflex.filereader.LeitorCSV;

public class Principal {

  public static void main(String[] args) {
    // Lê os dados do arquivo CSV e adiciona os objetos à lista de funcionários
    // Req 3.1
    List<Funcionario> funcionarios = null;

    try {
      funcionarios = LeitorCSV.lerArquivo(
          "src/main/java/projedata/iniflex/filereader/funcionarios.csv");
    } catch (IOException e) {
      System.out.println("Erro ao ler o arquivo: " + e.getMessage());
      return;
    }

    // Remove o funcionário João da lista
    // Req 3.2
    for (int i = 0; i < funcionarios.size(); i++) {
      if (funcionarios.get(i).getNome().equals("João")) {
        funcionarios.remove(i);

      }

    }
//     ########### COMENTAR/DESCOMENTAR ABAIXO PARA VER OU NÃO OS RESULTADOS
//    3.2
    System.out.println("Após remover João, temos:");
    imprimirFuncionarios(funcionarios);

//    Aumento de 10%
    darAumento(10, funcionarios);

//   Chama a função que imprime as listas por função(cargo) para o req 3.5 e 3.6
    Map<String, List<Funcionario>> funcionarioMap = agruparPorFuncao(funcionarios);

    for (Map.Entry<String, List<Funcionario>> entry : funcionarioMap.entrySet()) {
      System.out.print(entry.getKey() + ": ");
      for (Funcionario funcionario : entry.getValue()) {
        System.out.print(funcionario.getNome());
        if (entry.getValue().indexOf(funcionario) < entry.getValue().size() - 1) {
          System.out.print(", ");
        }
      }
      System.out.println();
    }

//  Req 3.8 Chama a função que imprime os funcionarios nascidos nos meses 10 e  12
    imprimirAniversarios(funcionarios);

//    Req 3.9
//    Imprime o funcionario mais velho
    imprimirMaisVelho(funcionarios);

// Req 3.10
//    Imprime a lista de funcionários em ordem alfabetica

    List<Funcionario> ordenados = ordenarPorNome(funcionarios);
    System.out.println("Lista ordenadas alfabeticamente: ");
    for (Funcionario f : ordenados) {
      System.out.println(
          f.getNome() + " | " + f.getNascimento() + " | "
              + f.getSalario() + " | "
              + f.getFuncao());
    }
  }

  // função que imprime a lista de funcionários
  // Req 3.3
  public static void imprimirFuncionarios(List<Funcionario> funcionarios) {
    System.out.println("\nLista de funcionários atualizada:");
    for (Funcionario f : funcionarios) {
      System.out.println(f.getNome() + " | " + f.getNascimento() + " | " + f.getSalario() + " | "
          + f.getFuncao());
    }
  }

//  Req 3.4
//  Função que aplica aumento, recebendo um parametro em % e atualizando o salario dos funcionários

  public static void darAumento(int aumento, List<Funcionario> funcionarios) {
    for (Funcionario f : funcionarios) {
      BigDecimal salario = f.getSalario();
      BigDecimal aumentoEmReais = salario.multiply(BigDecimal.valueOf(aumento))
          .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
      f.setSalario(salario.add(aumentoEmReais));
    }
    System.out.println("\nLista de funcionários com salário alterado em " + aumento + " %.");
    for (Funcionario f : funcionarios) {
      System.out.println(f.getNome() + " | " + f.getNascimento() + " | " + f.getSalario() + " | "
          + f.getFuncao());
    }
  }


  public static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
    Map<String, List<Funcionario>> funcionarioMap = new HashMap<>();

    for (Funcionario funcionario : funcionarios) {
      String funcao = funcionario.getFuncao();
      List<Funcionario> listaDeFuncionarios = funcionarioMap.get(funcao);

      if (listaDeFuncionarios == null) {
        listaDeFuncionarios = new ArrayList<>();
        funcionarioMap.put(funcao, listaDeFuncionarios);
      }
      listaDeFuncionarios.add(funcionario);
    }
    return funcionarioMap;
  }

  public static void imprimirAniversarios(List<Funcionario> funcionarios) {
    System.out.print("funcionarios que fazem aniversario no mes 10 e 12: ");
    for (Funcionario funcionario : funcionarios) {
      String aniversarioString = funcionario.getNascimento();
      LocalDate aniversario = LocalDate.parse(aniversarioString,
          DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      int mes = aniversario.getMonthValue();
      if (mes == 10 || mes == 12) {
        System.out.print(funcionario.getNome() + " ");
      }
    }
  }


  public static Funcionario imprimirMaisVelho(List<Funcionario> funcionarios) {
    Funcionario maisVelho = funcionarios.get(0);
    for (Funcionario funcionario : funcionarios) {
      String dataNascimentoString1 = funcionario.getNascimento();
      String dataNascimentoString2 = maisVelho.getNascimento();
      int ano1 = Integer.parseInt(dataNascimentoString1.substring(6, 10));
      int mes1 = Integer.parseInt(dataNascimentoString1.substring(3, 5));
      int dia1 = Integer.parseInt(dataNascimentoString1.substring(0, 2));
      int ano2 = Integer.parseInt(dataNascimentoString2.substring(6, 10));
      int mes2 = Integer.parseInt(dataNascimentoString2.substring(3, 5));
      int dia2 = Integer.parseInt(dataNascimentoString2.substring(0, 2));
      if (ano1 < ano2) {
        maisVelho = funcionario;
      } else if (ano1 == ano2) {
        if (mes1 < mes2) {
          maisVelho = funcionario;
        } else if (mes1 == mes2) {
          if (dia1 < dia2) {
            maisVelho = funcionario;
          }
        }
      }
    }

    // Calcula a idade
    LocalDate dataNascimento = LocalDate.parse(maisVelho.getNascimento(),
        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    LocalDate dataAtual = LocalDate.now();
    Period periodo = Period.between(dataNascimento, dataAtual);
    int idade = periodo.getYears();

    System.out.println(
        "\nO funcionário mais velho é " + maisVelho.getNome() + " e tem " + idade + " anos.");

    return maisVelho;
  }

  public static List<Funcionario> ordenarPorNome(List<Funcionario> funcionarios) {
    List<Funcionario> ordenados = new ArrayList<>(funcionarios);
    ordenados.sort((Funcionario f1, Funcionario f2) -> f1.getNome().compareTo(f2.getNome()));
    return ordenados;
  }
}
