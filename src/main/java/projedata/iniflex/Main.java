package projedata.iniflex;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import projedata.iniflex.entity.Pessoa;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

  public static void main(String[] args) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDate dataDeNascimento = LocalDate.parse("30/01/1960", formatter);
    String dataFormatada = dataDeNascimento.format(formatter);
    Pessoa pessoa1 = new Pessoa("Ana", dataFormatada);

    System.out.println(pessoa1.getNascimento());

  }
}