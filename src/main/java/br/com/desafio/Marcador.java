package br.com.desafio;

public enum Marcador {
  VAZIO(' '),
  HORIZONTAL('\u2500'),
  VERTICAL('\u2502');

  private final char mark;

  Marcador(char sinal) {
    this.mark = sinal;
  }

  public char getMark() {
    return mark;
  }
}
