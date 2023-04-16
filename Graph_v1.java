import java.io.PrintStream;
import java.io.*;
import java.util.*;

/**
 * Beschreiben Sie hier die Klasse Graph.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Graph_v1
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int[][] amatrix;
    private String[] label;
    private int n; // Anzahl Knoten = Größe Matrix

    public static void main() {
        Graph_v1 g = new Graph_v1();
        g.setDemo();
        g.print();
        g.readCSV("abcd.csv");
        g.print();
        g.printCSV("out.csv");
    }

    /**
     * Konstruktor für Objekte der Klasse Graph
     */
    public Graph_v1()
    {
    }

    /**
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */

    public void setDemo() {
        n=4;
        amatrix = new int[4][4];
        label = "A B C D".split(" ");
        amatrix[0][1]=1; // AB
        amatrix[1][2]=1; // BC
        amatrix[1][3]=1; // BD
        amatrix[2][0]=1; // CA
        amatrix[3][0]=1; // DA
    }

    public void print() {
        printAdjMatrix();
    }

    public void printAdjMatrix() {
        for (int i=0; i<n; i++){
            System.out.print("    "+label[i]);
        }
        System.out.println();
        for (int i=0; i<n; i++){
            System.out.print(label[i]+"   ");
            for (int j=0; j<n; j++){
                System.out.print(amatrix[i][j]+"    ");
            }
            System.out.println();
        }
    }

    public void printCSV(String filename) {
        //PrintStream ps = System.out;
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(filename));
            for (int i=0; i<n; i++){
                ps.print(label[i]+","); // Zeilenanfang, 1. Element
                for (int j=0; j<n-1; j++){
                    ps.print(amatrix[i][j]+",");// alle Werte als CSV
                }
                ps.println(amatrix[i][n-1]); // Zeilenende
            }
            ps.close();
        } catch (IOException e) {System.err.println(e);
        }
    }

    public void readCSV(String filename) {

        List <String[]> lines = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                lines.add(values);
            }
        } catch (IOException ioe) {}

        n=lines.size();
        label = new String[n];
        for (int i=0; i<n; i++) {
            label[i]=lines.get(i)[0]; // Ersters Element jedes Values-Arrays
        }
        amatrix = new int [n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; i<n; i++) {
                amatrix[i][j] = Integer.parseInt(lines.get(i)[j+1]); 
            }
        }
    }

    public void printAdjListe() {
        for (int i=0; i<n; i++){
            System.out.print(label[i]+": ");
            for (int j=0; j<n; j++){
                if (amatrix[i][j]!=0) {
                    System.out.print(label[j]+"("+amatrix[i][j]+") ");
                }
            }
            System.out.println();
        }
    }

}
