package Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class RandomMines {
  private static File file = new File("src/Model/output.txt");
  private static StringBuilder sb = new StringBuilder();
  private static final int HUNDRED = 100;
  private Random random = new Random();
  private int minePercent = 0; // out of HUNDRED
  private int height = 0;
  private int width = 0;
  private int mines = 0;
  private static FileWriter write;

  static {
    try {
      write = new FileWriter(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void generateNewInput(final int hei, final int wid, final int percentMines) { // generates a new minesweeper map
    this.height = hei;
    this.width = wid;
    this.minePercent = percentMines;
    sb.append(this.height + " " + this.width);
    mines = 0;
    for (int i = 0; i < height * width; i++) { // height*width = total area
      if (i % width == 0) { // if i is a multiple of width
        sb.append('\n'); // creates a new line at the end of the row
      }
      if (random.nextInt(HUNDRED) < minePercent) {
        sb.append('*');
        mines++;
      } else {
        sb.append('.');
      }
    }
    sb.append("\n"); // creates a new line at the end of the field
  }

  public static void main(String[] args) {
    RandomMines r = new RandomMines();
    Scanner scan = new Scanner(System.in);
    int hei = 1; // doesnt matter after while check
    int wid = 1;
    while (hei > 0) { // allows for more than one input map
      System.out.println("Enter height of field (0 to escape):");
      hei = Integer.valueOf(scan.next());
      if (hei == 0) {
        sb.append("0 0");
        try {
          write.write(sb.toString());
          write.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      }
      System.out.println("Enter width of field :");
      wid = Integer.valueOf(scan.next());
      System.out.println("Enter percent of mines out of 100 :");
      int per = Integer.valueOf(scan.next());
      r.generateNewInput(hei, wid, per);
      System.out.println(((float) r.mines / (r.height * r.width) * HUNDRED) + " actual percent of mines");
    }
    sb.append("0 0");
    try {
      write.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
