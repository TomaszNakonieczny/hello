
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

}

class Dziennik {
    ArrayList<Student> Studenci;

    public Dziennik(){
        Studenci = new ArrayList<Student>();
    }
}

public class Program {

    public static void main(String[] args) {

        Random random = new Random();

        Dziennik dziennik = new Dziennik();

        dziennik.Studenci.add(new Student("Tomasz", "Testowy"));
        dziennik.Studenci.add(new Student("Maciek", "Malicki"));
        dziennik.Studenci.add(new Student("Liwia", "Igowska"));

        for(Student student_z_listy : dziennik.Studenci) {
            // losuje od 5 do 15 ocen
            int ilosc_ocen = random.nextInt(10) + 5;

            for (int i = 0; i < ilosc_ocen; i++) {
                //losowanie ocen
                int ocena = random.nextInt(5) + 1;
                student_z_listy.Oceny.add(ocena);
            }
        }

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



