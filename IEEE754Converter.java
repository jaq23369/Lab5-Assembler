import java.util.Scanner;

public class IEEE754Converter {

    public static float binaryToDecimal(String binaryString) {
        if (binaryString.length() != 32) {
            throw new IllegalArgumentException("La cadena debe tener 32 caracteres.");
        }

        int sign = binaryString.charAt(0) == '0' ? 1 : -1;
        int exponent = Integer.parseInt(binaryString.substring(1, 9), 2) - 127;
        String mantissaBinary = binaryString.substring(9);
        float mantissa = 1; // Iniciar en 1 debido al bit implícito

        for (int i = 0; i < mantissaBinary.length(); i++) {
            if (mantissaBinary.charAt(i) == '1') {
                mantissa += Math.pow(2, -(i + 1));
            }
        }

        return sign * mantissa * (float)Math.pow(2, exponent);
    }

    public static String decimalToBinary(float decimalNumber) {
        int bits = Float.floatToIntBits(decimalNumber);
        return String.format("%32s", Integer.toBinaryString(bits)).replace(' ', '0');
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elige una opción:");
        System.out.println("a. Binario en formato IEEE 754 de precisión simple a decimal.");
        System.out.println("b. Decimal a binario en formato IEEE 754 de precisión simple.");
        String option = scanner.nextLine();

        switch (option) {
            case "a":
                System.out.println("Ingresa el número binario de 32 bits:");
                String binaryString = scanner.nextLine();
                try {
                    float decimal = binaryToDecimal(binaryString);
                    System.out.println("El número decimal es: " + decimal);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "b":
                System.out.println("Ingresa el número decimal:");
                float decimalNumber = scanner.nextFloat();
                String binary = decimalToBinary(decimalNumber);
                System.out.println("La representación binaria en formato IEEE 754 es: " + binary);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }

        scanner.close();
    }
}
