import java.util.Scanner;
import java.util.Stack;

public class Ejercicio1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese una expresión matemática:");
        String expresion = scanner.nextLine();

        try {
            double resultado = evaluarExpresion(expresion);
            System.out.println("Resultado: " + resultado);
        } catch (Exception e) {
            System.err.println("No pude procesar la expresión: " + e.getMessage());
        }
    }

    public static double evaluarExpresion(String expresion) {
        Stack<Double> numeros = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);

            if (Character.isDigit(caracter)) {
                StringBuilder numero = new StringBuilder();
                while (i < expresion.length() && (Character.isDigit(expresion.charAt(i)) || expresion.charAt(i) == '.')) {
                    numero.append(expresion.charAt(i));
                    i++;
                }
                i--; // Retroceder un paso para no saltarse el siguiente carácter

                numeros.push(Double.parseDouble(numero.toString()));
            } else if (caracter == '(') {
                operadores.push(caracter);
            } else if (caracter == ')') {
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    realizarOperacion(numeros, operadores);
                }
                operadores.pop(); // Eliminar el paréntesis abierto
            } else if (esOperador(caracter)) {
                while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(caracter)) {
                    realizarOperacion(numeros, operadores);
                }
                operadores.push(caracter);
            }
        }

        while (!operadores.isEmpty()) {
            realizarOperacion(numeros, operadores);
        }

        return numeros.pop();
    }

    public static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static int precedencia(char operador) {
        if (operador == '+' || operador == '-') {
            return 1;
        } else if (operador == '*' || operador == '/') {
            return 2;
        }
        return 0;
    }

    public static void realizarOperacion(Stack<Double> numeros, Stack<Character> operadores) {
        char operador = operadores.pop();
        double segundoNumero = numeros.pop();
        double primerNumero = numeros.pop();

        switch (operador) {
            case '+':
                numeros.push(primerNumero + segundoNumero);
                break;
            case '-':
                numeros.push(primerNumero - segundoNumero);
                break;
            case '*':
                numeros.push(primerNumero * segundoNumero);
                break;
            case '/':
                if (segundoNumero == 0) {
                    throw new ArithmeticException("División por cero");
                }
                numeros.push(primerNumero / segundoNumero);
                break;
        }
    }
}