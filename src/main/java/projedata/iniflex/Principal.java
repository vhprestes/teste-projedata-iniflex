package projedata.iniflex;

import java.io.File;
import java.io.IOException;
import java.util.List;
import projedata.iniflex.entity.Funcionario;
import projedata.iniflex.filereader.LeitorCSV;

public class Principal {

  public static void main(String[] args) {
    // Lê os dados do arquivo CSV e adiciona os objetos à lista de funcionários
    List<Funcionario> funcionarios = null;

    try {
      funcionarios = LeitorCSV.lerArquivo(
          "src/main/java/projedata/iniflex/filereader/funcionarios.csv");
    } catch (IOException e) {
      System.out.println("Erro ao ler o arquivo: " + e.getMessage());
      return;
    }

    // Imprime a lista de funcionários
    for (Funcionario f : funcionarios) {
      System.out.println(f);
    }

    // Remove o funcionário João da lista
    for (int i = 0; i < funcionarios.size(); i++) {
      if (funcionarios.get(i).getNome().equals("João")) {
        funcionarios.remove(i);

      }
      System.out.println("Após remover João, temos:");
      imprimirFuncionarios(funcionarios);

    }
//    imprimirFuncionarios(funcionarios);
  }

  // função que imprime a lista de funcionários
  public static void imprimirFuncionarios(List<Funcionario> funcionarios) {
    System.out.println("\nLista de funcionários atualizada:");
    for (Funcionario f : funcionarios) {
      System.out.println(f.getNome() + " | " + f.getNascimento() + " | " + f.getSalario() + " | "
          + f.getFuncao());
    }
  }
}