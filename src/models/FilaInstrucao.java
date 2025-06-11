package src.models;

import java.util.*;

public class FilaInstrucao {

    private static int issueStall = 0;

    public static String[] exe() {
        if (Sistema.stall.get("issue") == 0 && Sistema.stall.get("mem") == 0 &&
                Sistema.stall.get("add") == 0 && Sistema.stall.get("mul") == 0 &&
                Sistema.clock != 1 && !Sistema.instQueue.isEmpty()) {
            Sistema.instQueue.remove(0);
            Sistema.stall.put("general", 0);
        }

        if (Sistema.instQueue.isEmpty()) {
            return new String[] { "0", "0", "0", "0" };
        }

        String[] instruction = Sistema.instQueue.get(0).split(" ");
        String operand = instruction[0];
        String dest = instruction[1];
        String vj = instruction[2];
        String vk = instruction[3];

        if (Sistema.busyReg[Integer.parseInt(dest.substring(1))] == 1) {
            Sistema.stall.put("issue", 1);
            return new String[] { "0", "0", "0", "0" };
        } else {
            Sistema.stall.put("issue", 0);
        }

        if (Sistema.stall.get("issue") == 1 || Sistema.stall.get("mem") == 1 ||
                Sistema.stall.get("add") == 1 || Sistema.stall.get("mul") == 1) {
            Sistema.stall.put("general", 1);
        } else {
            Sistema.instructionIssued = 0;
        }

        return new String[] { operand, dest, vj, vk };
    }
}
