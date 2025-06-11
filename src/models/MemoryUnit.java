package src.models;

import java.util.*;

public class MemoryUnit {

    public static int startClock = 0;
    public static String dest = "0";
    public static String data = "0";
    public static int start = 0;
    public static String address = "0";
    public static int busy = 0;
    public static String inst = "0";

    public static void exe(String[] instruction) {
        if ((instruction[0].equals("LD") || instruction[0].equals("STR")) && start == 1) {
            // Memória ocupada — causa stall
            Sistema.stall.put("mem", 1);
        }

        else if ((instruction[0].equals("LD") || instruction[0].equals("STR")) && start == 0) {
            // Início da operação de memória
            startClock = Sistema.clock;
            inst = instruction[0];
            dest = instruction[1];
            Sistema.busyReg[Integer.parseInt(dest.substring(1))] = 1;
            address = instruction[2];
            data = instruction[3];
            start = 1;
            Sistema.stall.put("mem", 0);

            // System.out.println("start " + inst + " " + dest + " " + address);
        }

        else if (Sistema.clock == startClock + Sistema.loadTime && start == 1) {
            if (inst.equals("LD")) {
                // LOAD: envia valor da memória para o barramento
                int value = Sistema.mem[Integer.parseInt(address)];
                Sistema.resultQueue.add(Arrays.asList(dest, String.valueOf(value)));
                // System.out.println("send " + value + " to " + dest);
            }

            if (inst.equals("STR")) {
                if (!dest.equals("0")) {
                    // STR com registrador: escreve o valor do registrador na memória
                    int regIndex = Integer.parseInt(dest.substring(1));
                    Sistema.mem[Integer.parseInt(address)] = Sistema.register[regIndex];
                    Sistema.busyReg[regIndex] = 0;
                    // System.out.println("store " + dest + " at memory address " + address);
                } else {
                    // STR imediato: escreve valor direto
                    Sistema.mem[Integer.parseInt(address)] = Integer.parseInt(data);
                    // System.out.println("store " + data + " at memory address " + address);
                }
            }

            start = 0;
        }
    }
}
