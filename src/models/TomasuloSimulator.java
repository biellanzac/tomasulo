package src.models;

public class TomasuloSimulator {


    public static void exe() {
        while (Sistema.clock < Sistema.maxTime) {
            String[] currentInstruction = FilaInstrucao.exe();
            String operand = currentInstruction[0];
            String dest = currentInstruction[1];
            String vj = currentInstruction[2];
            String vk = currentInstruction[3];

            // Execute instructions
            for (int i = 0; i < Sistema.addNumber; i++) {
                UnidadeReserva.addExe(i, currentInstruction);
                Addc.exe(i, null, 0, 0, "0");
            }

            for (int i = 0; i < Sistema.mulNumber; i++) {
                UnidadeReserva.mulExe(i, currentInstruction);
                Mult.exe(i, null, 0, 0, "0");
            }

            // Execute memory operations
            MemoryUnit.exe(currentInstruction);

            // Execute CDB
            CDB.exe();

            // Display current state
            Display.exe();

            // Increment clock
            Sistema.clock++;
        }
    }
}