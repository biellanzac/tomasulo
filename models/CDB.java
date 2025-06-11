package models;

import java.util.List;

public class CDB {

    public static void exe() {
        // Enquanto houver resultados na fila de resultados (resultQueue)
        while (!Sistema.resultQueue.isEmpty()) {
            // Pega o primeiro resultado da fila (que é uma List<String>)
            List<String> result = Sistema.resultQueue.get(0);

            // Verifica se o resultado tem pelo menos 2 elementos (registrador e valor)
            if (result.size() >= 2) {
                String register = result.get(0);
                String value = result.get(1);

                // Verifica se o registrador está no formato correto (ex: "R0")
                if (register.startsWith("R")) {
                    try {
                        int registerNumber = Integer.parseInt(register.substring(1)); // Remove o "R" e converte
                        int intValue = Integer.parseInt(value);

                        // Armazena o resultado no registrador correspondente
                        Sistema.register[registerNumber] = intValue;
                        Sistema.busyReg[registerNumber] = 0;
                        Sistema.emptyReg[registerNumber] = 0;

                        // Verifica se a Fila de Reserva do somador precisa desse resultado
                        for (int j = 0; j < Sistema.addNumber; j++) {
                            if (UnidadeReserva.v1Add[j] != null &&
                                    UnidadeReserva.v1Add[j].toString().equals(register)) {
                                UnidadeReserva.v1Add[j] = intValue;
                            }
                            if (UnidadeReserva.v2Add[j] != null &&
                                    UnidadeReserva.v2Add[j].toString().equals(register)) {
                                UnidadeReserva.v2Add[j] = intValue;
                            }
                        }

                        // Verifica se a Fila de Reserva do multiplicador precisa desse resultado
                        for (int j = 0; j < Sistema.mulNumber; j++) {
                            if (UnidadeReserva.v1Mul[j] != null &&
                                    UnidadeReserva.v1Mul[j].toString().equals(register)) {
                                UnidadeReserva.v1Mul[j] = intValue;
                            }
                            if (UnidadeReserva.v2Mul[j] != null &&
                                    UnidadeReserva.v2Mul[j].toString().equals(register)) {
                                UnidadeReserva.v2Mul[j] = intValue;
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao converter número do registrador ou valor: " + e.getMessage());
                    }
                }
            }

            // Remove o resultado processado da fila
            Sistema.resultQueue.remove(0);
        }
    }
}