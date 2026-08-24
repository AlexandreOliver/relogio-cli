package br.com.desafio;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
*
* Objetivo: Criar um Relogio com precição de milissegundos, estilo "Palito" que conta na tela.
*
* Exemplo:

* '─' = \u2500
* '│' = \u2502

0:   ───     ───       ───     ───       ───     ───       ───     ───     ───
1: │     │ │     │ ● │     │ │     │ ● │     │ │     │ ● │     │ │     │ │     │
2:   ───     ───       ───     ───       ───     ───       ───     ───     ───
3: │     │ │     │ ● │     │ │     │ ● │     │ │     │ ● │     │ │     │ │     │
4:   ───     ───       ───     ───       ───     ───       ───     ───     ───

 >>> HH:MM:SS:MMM

*
*
*
* */
public class Main {

  public static StringBuilder bufferForPrint = new StringBuilder();
  public static List<List<Marcador>> visor = new ArrayList<>();
  public static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

  public static List<Integer> markHorizontalIndexes = List.of(0, 3, 6);
  public static List<List<Integer>> markVerticalIndexes = List.of(List.of(1, 2), List.of(4, 5));

  public static void main(String[] args) throws IOException, InterruptedException {
    new PrintStream(System.out, true, StandardCharsets.UTF_8);

    clearConsole();

    while (System.in.available() == 0) {

      clocker();
      print();

      visor.clear();

      Thread.sleep(100);
    }

  }

  public static void clocker() {
    LocalTime time = LocalTime.now();
    String parsedTime = time.format(formatterDate);

    for (char value: parsedTime.toCharArray()) {
      if (value == ':') continue;

      visor.add(Digitos.getDigit(value));
    }
  }

  public static void print() throws IOException, InterruptedException {

    // Move o cursor para o inicio
    System.out.print("\033[H");
    System.out.flush();

    var stepHorizontal = 0;
    var stepVertical = 0;

    // Fatia cada digit em 5, montando uma string de cada fatia e imprimindo
    for (int lineIdx = 0; lineIdx < 5; lineIdx++) {
      bufferForPrint.setLength(0);

      /**
       * Cada segment é a posiçao de um digit: List<Marcador>
       *
       * 0 e 1 são: HH
       * 2 e 3 são: MM
       * 4 e 5 são: ss
       * 6, 7 e 8 são: SSS
       */
      for (int segment = 0; segment < visor.size(); segment++) {

        List<Marcador> digit = visor.get(segment);

        if (lineIdx % 2 == 0) {
          bufferForPrint.append("  ");

          var mark = digit.get(markHorizontalIndexes.get(stepHorizontal)).getMark();

          bufferForPrint.append(mark);
          bufferForPrint.append(mark);
          bufferForPrint.append(mark);

          bufferForPrint.append("  ");

        } else {

          bufferForPrint.append(digit.get(markVerticalIndexes.get(stepVertical).get(0)).getMark());
          bufferForPrint.append("     ");
          bufferForPrint.append(digit.get(markVerticalIndexes.get(stepVertical).get(1)).getMark());

        }

        if (segment % 2 != 0 && segment < 6) {

          if (lineIdx % 2 != 0) {
            bufferForPrint.append(" ● ");
          } else {
            bufferForPrint.append("   ");
          }

        } else {
          bufferForPrint.append(' ');
        }


      }

      if (lineIdx % 2 == 0) {
        stepHorizontal++;
      } else {
        stepVertical++;
      }

      System.out.println(bufferForPrint);

      System.out.flush();

    }
  }

  public static void clearConsole() throws IOException, InterruptedException {
    if (System.getProperty("os.name").contains("Windows")) {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } else {
      Runtime.getRuntime().exec("clear");
    }
  }
}