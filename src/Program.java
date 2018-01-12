
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Random;


class Student {
    String Imie;
    String Nazwisko;
    ArrayList<Integer> Oceny;

    public Student(String imie, String nazwisko){
        Imie = imie;
        Nazwisko = nazwisko;
        Oceny = new ArrayList<Integer>();
    }

    public static ArrayList<Student> Zaladuj_z_Pliku(String filepath) throws IOException{
        ArrayList<Student> studenci = new ArrayList<Student>();

        byte[] dane = Files.readAllBytes(Paths.get(filepath));

        String listaStudentow = new String(dane, StandardCharsets.UTF_8);

        // podziel po znaku nowej linii na poszczególnych studentów
        String[] studenciZListy = listaStudentow.split("\n");

        for(String s : studenciZListy){
            String[] daneStudenta = s.split("\\|");

            //index 0 - imie
            //index 1 - nazwisko
            //index 2 - oceny rozdzielone przecinkiem

            Student st = new Student(daneStudenta[0], daneStudenta[1]);

            String[] tablicaZnakowOcen = daneStudenta[2].trim().split(",");

            for (int i = 0; i < tablicaZnakowOcen.length; i++){
                int ocena = Integer.parseInt(tablicaZnakowOcen[i]);
                st.Oceny.add(ocena);
            }

            studenci.add(st);

        }

        return studenci;
    }
}

class Dziennik {
    ArrayList<Student> Studenci;

    public Dziennik(){
        Studenci = new ArrayList<Student>();
    }
}

public class Program {

    public static void main(String[] args) throws IOException {

        Random random = new Random();

        Dziennik dziennik = new Dziennik();

        dziennik.Studenci = Student.Zaladuj_z_Pliku(args[0]);

        for(Student student_z_listy : dziennik.Studenci) {
            wypisz_dane(student_z_listy);
        }
    }

    private static void wypisz_dane(Student student) {
        int dlugosc = student.Oceny.size();
        double max = 0;
        double min = student.Oceny.get(0);
        double suma = 0;

        for (double ocena : student.Oceny){
            if (ocena > max)
                max = ocena;
            if (ocena < min)
                min = ocena;

            suma += ocena;
        }



        double srednia = suma / dlugosc;


        System.out.println(student.Imie + " " + student.Nazwisko + " oceny: ");
        for (double ocena : student.Oceny){
            System.out.println(ocena);
        }

        System.out.format("Średnia ocen " + "%.2f%n", srednia);
        System.out.println("Najlepsza ocena " + max);
        System.out.println("Najgorsza ocena " + min);
        System.out.println("");
    }
}



