package br.com.desafio;

import java.util.List;

public enum Digitos {
  ZERO('0', List.of(Marcador.HORIZONTAL, Marcador.VERTICAL, Marcador.VERTICAL, Marcador.VAZIO, Marcador.VERTICAL, Marcador.VERTICAL, Marcador.HORIZONTAL)),
  UM('1', List.of(Marcador.VAZIO, Marcador.VAZIO, Marcador.VERTICAL, Marcador.VAZIO, Marcador.VAZIO, Marcador.VERTICAL, Marcador.VAZIO)),
  DOIS('2', List.of(Marcador.HORIZONTAL, Marcador.VAZIO, Marcador.VERTICAL, Marcador.HORIZONTAL, Marcador.VERTICAL, Marcador.VAZIO, Marcador.HORIZONTAL)),
  TRES('3', List.of(Marcador.HORIZONTAL, Marcador.VAZIO, Marcador.VERTICAL, Marcador.HORIZONTAL, Marcador.VAZIO, Marcador.VERTICAL, Marcador.HORIZONTAL)),
  QUATRO('4', List.of(Marcador.VAZIO, Marcador.VERTICAL, Marcador.VERTICAL, Marcador.HORIZONTAL, Marcador.VAZIO, Marcador.VERTICAL, Marcador.VAZIO)),
  CINCO('5', List.of(Marcador.HORIZONTAL, Marcador.VERTICAL, Marcador.VAZIO, Marcador.HORIZONTAL, Marcador.VAZIO, Marcador.VERTICAL, Marcador.HORIZONTAL)),
  SEIS('6', List.of(Marcador.HORIZONTAL, Marcador.VERTICAL, Marcador.VAZIO, Marcador.HORIZONTAL, Marcador.VERTICAL, Marcador.VERTICAL, Marcador.HORIZONTAL)),
  SETE('7', List.of(Marcador.HORIZONTAL, Marcador.VAZIO, Marcador.VERTICAL, Marcador.VAZIO, Marcador.VAZIO, Marcador.VERTICAL, Marcador.VAZIO)),
  OITO('8', List.of(Marcador.HORIZONTAL, Marcador.VERTICAL, Marcador.VERTICAL, Marcador.HORIZONTAL, Marcador.VERTICAL, Marcador.VERTICAL, Marcador.HORIZONTAL)),
  NOVE('9', List.of(Marcador.HORIZONTAL, Marcador.VERTICAL, Marcador.VERTICAL, Marcador.HORIZONTAL, Marcador.VAZIO, Marcador.VERTICAL, Marcador.HORIZONTAL));

  private final char value;
  private final List<Marcador> digito;

  Digitos(char valueIntText, List<Marcador> digito) {
    this.value = valueIntText;
    this.digito = digito;
  }

  public static List<Marcador> getDigit(char value) {
    for (Digitos digitos: values()) {
      if (digitos.value == value) return digitos.digito;
    }

    return ZERO.digito;
  }
}
