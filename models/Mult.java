package models;
import java.util.*;
public class Mult {

    public static int[] startClockMul = new int[Sistema.mulNumber];
    public static int[] mul1 = new int[Sistema.mulNumber];
    public static int[] mul2 = new int[Sistema.mulNumber];
    public static String[] opMul = new String[Sistema.mulNumber];
    public static int[] busyMul = new int[Sistema.mulNumber];
    public static int[] startMul = new int[Sistema.mulNumber];
    public static String[] destMul = new String[Sistema.mulNumber];

    public static void exe(int number, Object op, int v1, int v2, String dest) {
        // Início da operação
        if (op instanceof String && (op.equals("MUL") || op.equals("DIV"))) {
            opMul[number] = (String) op;
            mul1[number] = v1;
            mul2[number] = v2;
            destMul[number] = dest;
            startClockMul[number] = Sistema.clock;
            startMul[number] = 1;
            busyMul[number] = 1;
            // System.out.println("start " + op + " on multiplier " + number);

        } 
        // Finalização da operação após o tempo de execução
        else if (op == null &&
                Sistema.clock == startClockMul[number] + Sistema.mulTime &&
                startClockMul[number] != 0) {

            if ("MUL".equals(opMul[number])) {
                Sistema.resultQueue.add(Arrays.asList(destMul[number], String.valueOf(mul1[number] * mul2[number])));
            } else if ("DIV".equals(opMul[number])) {
                if (mul2[number] != 0) {
                    Sistema.resultQueue.add(Arrays.asList(destMul[number], String.valueOf((double) mul1[number] / mul2[number])));
                } else {
                    Sistema.resultQueue.add(Arrays.asList(destMul[number], "DIV_BY_ZERO"));
                }
            }

            busyMul[number] = 0;
            startMul[number] = 0;
        }
    }
}
