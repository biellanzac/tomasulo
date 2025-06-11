package src;

import src.models.Sistema;
import src.models.TomasuloSimulator;

public class Main {
    public static void main(String[] args) {
        // Initialize the system
        Sistema.initialize();

        // Start the execution of the Tomasulo algorithm
        TomasuloSimulator.exe();
        
    }
}
