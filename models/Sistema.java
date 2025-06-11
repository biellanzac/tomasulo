package models;
import java.util.*;

public class Sistema {

    // Recursos da máquina
    public static int addNumber = 3;
    public static int mulNumber = 2;

    // Estado do sistema
    public static int clock;
    public static int maxTime;
    public static int sleepDuration;

    public static int[] register = new int[5];
    public static int[] busyReg = new int[5];
    public static int[] emptyReg = new int[5];

    public static int[] mem = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

    public static List<String> instQueue = new ArrayList<>();

    public static int loadTime;
    public static List<List<String>> resultQueue = new ArrayList<>();

    public static int addTime;
    public static int mulTime;
    public static int instructionIssued;

    public static Map<String, Integer> stall = new HashMap<>();

    public static void initialize() {
        clock = 1;
        maxTime = 30;
        sleepDuration = 1;

        // Inicializando os registradores
        Arrays.fill(register, 0);
        Arrays.fill(busyReg, 0);
        Arrays.fill(emptyReg, 1);

        // Inicializando a fila de instruções
        instQueue.clear();
        instQueue.add("ADD R0 1 2");
        instQueue.add("ADD R1 R0 7");
        instQueue.add("ADD R2 2 2");
        instQueue.add("SUB R3 R2 1");
        instQueue.add("ADD R4 5 16");
        instQueue.add("ADD R4 R1 R3");
        instQueue.add("MUL R0 5 6");
        instQueue.add("LD R1 1 0");
        instQueue.add("ADD R2 R0 R1");
        instQueue.add("STR R2 0 0");

        // Inicializando fila de resultados
        resultQueue.clear();
        for (int i = 0; i < 10; i++) {
            resultQueue.add(new ArrayList<>());
        }

        // Tempos das operações
        loadTime = 2;
        addTime = 2;
        mulTime = 10;

        // Contador de instruções emitidas
        instructionIssued = 0;

        // Inicializando tipos de stall
        stall.clear();
        stall.put("general", 0);
        stall.put("issue", 0);
        stall.put("mem", 0);
        stall.put("add", 0);
        stall.put("mul", 0);
    }

    public static void main(String[] args) {
        initialize();
        System.out.println("Sistema inicializado com sucesso.");
    }
}
