package projedata.iniflex.filereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import projedata.iniflex.entity.Funcionario;

public class LeitorCSV {

  public static List<Funcionario> lerArquivo(String nomeArquivo) throws IOException {
    List<Funcionario> funcionarios = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
      String linha = br.readLine();
      linha = br.readLine();

      while (linha != null) {
        String[] campos = linha.split(",");
        String nome = campos[0];
        String nascimento = campos[1];
        BigDecimal salario = new BigDecimal(campos[2].replace(",", "."));
        String funcao = campos[3];

        Funcionario f = new Funcionario(nome, nascimento, salario, funcao);
        funcionarios.add(f);

        linha = br.readLine();
      }
    }

    return funcionarios;
  }


}