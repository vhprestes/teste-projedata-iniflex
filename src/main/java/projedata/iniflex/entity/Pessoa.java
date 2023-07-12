
package projedata.iniflex.entity;

import java.time.LocalDate;

public class Pessoa {

  private String nome;
  private String nascimento;


//  nascimento é string apenas para fim de visualização
  public Pessoa(String nome, String nascimento) {
    this.nome = nome;
    this.nascimento = nascimento;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getNascimento() {
    return nascimento;
  }

  public void setNascimento(String nascimento) {
    this.nascimento = nascimento;
  }
}

//Exemplo para criar funcionário:
//Pessoa pessoa1 = new Pessoa("Aluno", LocalDate.of(YYYY, MM, DD));