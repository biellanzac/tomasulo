package models;

import java.util.*;

public class Addc {

    public static int[] startClockAdd = new int[Sistema.addNumber];
    public static int[] add1 = new int[Sistema.addNumber];
    public static int[] add2 = new int[Sistema.addNumber];
    public static String[] opAdd = new String[Sistema.addNumber];
    public static int[] busyAdd = new int[Sistema.addNumber];
    public static int[] startAdd = new int[Sistema.addNumber];
    public static String[] destAdd = new String[Sistema.addNumber];

    public static void exe(int number, Object op, int v1, int v2, String dest) {
        // Quando o adder recebe nova instrução
        if (op instanceof String && (op.equals("ADD") || op.equals("SUB"))) {
            opAdd[number] = (String) op;
            add1[number] = v1;
            add2[number] = v2;
            destAdd[number] = dest;
            startClockAdd[number] = Sistema.clock;
            startAdd[number] = 1;
            busyAdd[number] = 1;
            // System.out.println("start " + op + " on adder " + number);

        }
        // Quando o adder termina a operação (simulando fim do tempo)
        else if (op == null &&
                Sistema.clock == startClockAdd[number] + Sistema.addTime &&
                startClockAdd[number] != 0) {

            if ("ADD".equals(opAdd[number])) {
                Sistema.resultQueue.add(Arrays.asList(destAdd[number], String.valueOf(add1[number] + add2[number])));
                // System.out.println("ADD finalizada no adder " + number);
            } else if ("SUB".equals(opAdd[number])) {
                Sistema.resultQueue.add(Arrays.asList(destAdd[number], String.valueOf(add1[number] - add2[number])));
                // System.out.println("SUB finalizada no adder " + number);
            }

            busyAdd[number] = 0;
            startAdd[number] = 0;
        }
    }
}
