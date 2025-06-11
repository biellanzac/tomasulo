package src.models;

import java.util.Arrays;

public class UnidadeReserva {

    // ADD reservation station
    public static String[] opAdd = new String[Sistema.addNumber];
    public static Object[] v1Add = new Object[Sistema.addNumber];
    public static Object[] v2Add = new Object[Sistema.addNumber];
    public static String[] destAdd = new String[Sistema.addNumber];

    // MUL reservation station
    public static String[] opMul = new String[Sistema.mulNumber];
    public static Object[] v1Mul = new Object[Sistema.mulNumber];
    public static Object[] v2Mul = new Object[Sistema.mulNumber];
    public static String[] destMul = new String[Sistema.mulNumber];

    static {
        for (int i = 0; i < Sistema.addNumber; i++) {
            opAdd[i] = "null";
            v1Add[i] = "null";
            v2Add[i] = "null";
            destAdd[i] = "null";
        }
        for (int i = 0; i < Sistema.mulNumber; i++) {
            opMul[i] = "null";
            v1Mul[i] = "null";
            v2Mul[i] = "null";
            destMul[i] = "null";
        }
    }

    public static void addExe(int number, String[] instruction) {
        if (Addc.busyAdd[number] == 1 && Addc.startAdd[number] == 0) {
            if (v1Add[number] instanceof Integer && v2Add[number] instanceof Integer) {
                Addc.exe(number, opAdd[number], (Integer) v1Add[number], (Integer) v2Add[number], destAdd[number]);
            }
        }

        if (Addc.busyAdd[number] == 0 && (instruction[0].equals("ADD") || instruction[0].equals("SUB"))
                && Sistema.instructionIssued == 0) {
            Addc.busyAdd[number] = 1;
            opAdd[number] = instruction[0];
            destAdd[number] = instruction[1];
            Sistema.busyReg[Integer.parseInt(destAdd[number].substring(1))] = 1;
            v1Add[number] = instruction[2];
            v2Add[number] = instruction[3];

            if (!v1Add[number].toString().startsWith("R")) {
                v1Add[number] = Integer.parseInt(v1Add[number].toString());
            } else {
                int regNumber = Integer.parseInt(v1Add[number].toString().substring(1));
                if (Sistema.busyReg[regNumber] == 0 && Sistema.emptyReg[regNumber] == 0) {
                    v1Add[number] = Sistema.register[regNumber];
                }
            }

            if (!v2Add[number].toString().startsWith("R")) {
                v2Add[number] = Integer.parseInt(v2Add[number].toString());
            } else {
                int regNumber = Integer.parseInt(v2Add[number].toString().substring(1));
                if (Sistema.busyReg[regNumber] == 0 && Sistema.emptyReg[regNumber] == 0) {
                    v2Add[number] = Sistema.register[regNumber];
                }
            }

            Sistema.instructionIssued = 1;
            Sistema.stall.put("add", 0);
            System.out.println(
                    "Instruction " + Arrays.toString(instruction) + " issued to reservation station " + number);
        }

        if (number == Sistema.addNumber - 1 && Sistema.instructionIssued == 0 &&
                (instruction[0].equals("ADD") || instruction[0].equals("SUB"))) {
            Sistema.stall.put("add", 1);
        }
    }

    public static void mulExe(int number, String[] instruction) {
        if (Mult.busyMul[number] == 1 && Mult.startMul[number] == 0) {
            if (v1Mul[number] instanceof Integer && v2Mul[number] instanceof Integer) {
                Mult.exe(number, opMul[number], (Integer) v1Mul[number], (Integer) v2Mul[number], destMul[number]);
            }
        }

        if (Mult.busyMul[number] == 0 && (instruction[0].equals("MUL") || instruction[0].equals("DIV"))
                && Sistema.instructionIssued == 0) {
            Mult.busyMul[number] = 1;
            opMul[number] = instruction[0];
            destMul[number] = instruction[1];
            Sistema.busyReg[Integer.parseInt(destMul[number].substring(1))] = 1;
            v1Mul[number] = instruction[2];
            v2Mul[number] = instruction[3];

            if (!v1Mul[number].toString().startsWith("R")) {
                v1Mul[number] = Integer.parseInt(v1Mul[number].toString());
            } else {
                int regNumber = Integer.parseInt(v1Mul[number].toString().substring(1));
                if (Sistema.busyReg[regNumber] == 0 && Sistema.emptyReg[regNumber] == 0) {
                    v1Mul[number] = Sistema.register[regNumber];
                }
            }

            if (!v2Mul[number].toString().startsWith("R")) {
                v2Mul[number] = Integer.parseInt(v2Mul[number].toString());
            } else {
                int regNumber = Integer.parseInt(v2Mul[number].toString().substring(1));
                if (Sistema.busyReg[regNumber] == 0 && Sistema.emptyReg[regNumber] == 0) {
                    v2Mul[number] = Sistema.register[regNumber];
                }
            }

            Sistema.instructionIssued = 1;
            Sistema.stall.put("mul", 0);
            System.out.println(
                    "Instruction " + Arrays.toString(instruction) + " issued to reservation station " + number);
        }

        if (number == Sistema.mulNumber - 1 && Sistema.instructionIssued == 0 &&
                (instruction[0].equals("MUL") || instruction[0].equals("DIV"))) {
            Sistema.stall.put("mul", 1);
        }
    }
}
