package models;

public class Display {
    public static void exe() {
        System.out.println("Clock: " + Sistema.clock);
        System.out.println("Instruction Queue: " + Sistema.instQueue);
        System.out.println("Reservation Station: ");

        // ADD reservation station
        for (int i = 0; i < Sistema.addNumber; i++) {
            System.out.println("ADD" + i + ": " + UnidadeReserva.opAdd[i] + " " + UnidadeReserva.v1Add[i] + " "
                    + UnidadeReserva.v2Add[i] + " " + UnidadeReserva.destAdd[i]);
        }

        // MUL reservation station
        for (int i = 0; i < Sistema.mulNumber; i++) {
            System.out.println("MUL" + i + ": " + UnidadeReserva.opMul[i] + " " + UnidadeReserva.v1Mul[i] + " "
                    + UnidadeReserva.v2Mul[i] + " " + UnidadeReserva.destMul[i]);
        }

        // Register
        System.out.println("Register: ");
        for (int i = 0; i < Sistema.register.length; i++) {
            System.out.println(
                    "R" + i + ": " + Sistema.register[i] + " " + Sistema.busyReg[i] + " " + Sistema.emptyReg[i]);
        }

        // Memory
        System.out.println("Memory: ");
        for (int i = 0; i < Sistema.mem.length; i++) {
            System.out.println("M" + i + ": " + Sistema.mem[i]);
        }

        System.out.println();
    }
}