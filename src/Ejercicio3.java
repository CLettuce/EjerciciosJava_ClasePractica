import java.util.Scanner;
import java.util.Arrays;

public class Ejercicio3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de números: ");
        int n = scanner.nextInt();

        int[] arreglo = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Ingrese el número " + (i + 1) + ": ");
            arreglo[i] = scanner.nextInt();
        }

        System.out.println("Seleccione una opción:");
        System.out.println("1. Ordenar utilizando el algoritmo de Burbuja.");
        System.out.println("2. Realizar una búsqueda binaria.");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            System.out.println("\nArreglo sin ordenar:");
            imprimirArreglo(arreglo);

            ordenarBurbuja(arreglo);

            System.out.println("\nArreglo ordenado:");
            imprimirArreglo(arreglo);
        } else if (opcion == 2) {
            System.out.print("Ingrese el número que desea buscar: ");
            int numeroBuscado = scanner.nextInt();

            Arrays.sort(arreglo); // Asegurarse de que el arreglo esté ordenado
            int indice = busquedaBinaria(arreglo, numeroBuscado);

            if (indice != -1) {
                System.out.println("El número " + numeroBuscado + " se encuentra en el índice " + indice);
            } else {
                System.out.println("El número " + numeroBuscado + " no se encuentra en el arreglo.");
            }
        } else {
            System.out.println("Opción no válida.");
        }

        scanner.close();
    }

    public static void ordenarBurbuja(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Intercambiar arr[j] y arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void imprimirArreglo(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }


    public static int busquedaBinaria(int[] arr, int elemento) {
        int izquierda = 0;
        int derecha = arr.length - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arr[medio] == elemento) {
                return medio;
            }

            if (arr[medio] < elemento) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return -1; // Elemento no encontrado
    }
}
