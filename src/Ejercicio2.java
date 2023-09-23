import java.util.Random;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {
        String[] palabras = {"Prueba", "Lopez", "carl", "carlos", "car"};
        Random rand = new Random();
        String palabraSecreta = palabras[rand.nextInt(palabras.length)];
        int intentos = 6;

        char[] palabraAdivinada = new char[palabraSecreta.length()];
        for (int i = 0; i < palabraAdivinada.length; i++) {
            palabraAdivinada[i] = '_';
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al juego del ahorcado");
        while (intentos > 0) {
            System.out.println("\nPalabra a adivinar: " + String.valueOf(palabraAdivinada));
            System.out.println("Intentos restantes: " + intentos);
            System.out.print("Ingresa una letra: ");
            char letra = scanner.next().charAt(0);

            boolean acierto = false;
            for (int i = 0; i < palabraSecreta.length(); i++) {
                if (palabraSecreta.charAt(i) == letra) {
                    palabraAdivinada[i] = letra;
                    acierto = true;
                }
            }

            if (!acierto) {
                System.out.println("Letra incorrecta.");
                intentos--;
            }

            if (String.valueOf(palabraAdivinada).equals(palabraSecreta)) {
                System.out.println("\n¡Felicidades! Has adivinado la palabra: " + palabraSecreta);
                break;
            }
        }

        if (intentos == 0) {
            System.out.println("\n¡Has agotado todos tus intentos! La palabra era: " + palabraSecreta);
        }

        scanner.close();
    }
}