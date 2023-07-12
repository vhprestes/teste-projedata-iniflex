package projedata.iniflex;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import projedata.iniflex.entity.Pessoa;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

  public static void main(String[] args) {

    LocalDate nascimentoFuncionario = LocalDate.of(1960, 1, 19);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    Pessoa pessoa1 = new Pessoa("Ana", formatter.format(nascimentoFuncionario));

    System.out.println(pessoa1.getNascimento());

  }
}