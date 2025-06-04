//********************************************************************
//
// Developer:     Mauricio Rivas
//
// Program #:     Five
//
// File Name:     Program5TestRunner
//
// Course:        COSC 4302 Operating Systems
//
// Due Date:      5/12/2025
//
// Instructor:    Prof. Fred Kumi
//
// Java Version:  11
//
// Description:   Controls the execution of the memory allocation
//                simulation. Handles user input and delegates tasks
//                to the MemoryManager class.
//
//********************************************************************

import java.util.Scanner;

public class Program5TestRunner {

    private final Scanner scanner;
    private MemoryManager manager;

    //***************************************************************
    //
    // Constructor:  Program5TestRunner
    //
    // Description:  Initializes the scanner used for user input.
    //
    // Parameters:   None
    //
    // Returns:      N/A
    //
    //***************************************************************
    public Program5TestRunner() {
        scanner = new Scanner(System.in);
    }

    //***************************************************************
    //
    // Method:      startProgram
    //
    // Description: Starts the program, initializes memory, and handles
    //              user command loop.
    //
    // Parameters:  None
    //
    // Returns:     None
    //
    //***************************************************************
    public void startProgram() {
        

        try {
            initializeMemory();

            String command;
            do {
                System.out.print("allocator> ");
                String[] input = scanner.nextLine().trim().split("\\s+");
                command = (input.length > 0) ? input[0].toUpperCase() : "";
                processCommand(command, input);
            } while (!command.equals("X"));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //***************************************************************
    //
    // Method:      initializeMemory
    //
    // Description: Prompts the user to enter memory size and creates
    //              the memory manager with that size.
    //
    // Parameters:  None
    //
    // Returns:     None
    //
    //***************************************************************
    private void initializeMemory() {
        System.out.print("Enter the initial amount of memory (in MB): ");
        String input = scanner.nextLine().replace("MB", "").trim();
        int mb = Integer.parseInt(input);
        int size = mb * 1024 * 1024;
        manager = new MemoryManager(size);
    }

    //***************************************************************
    //
    // Method:      processCommand
    //
    // Description: Routes a user command to the correct memory operation.
    //
    // Parameters:  command - the main command string
    //              input   - array of command arguments
    //
    // Returns:     None
    //
    //***************************************************************
    private void processCommand(String command, String[] input) {
        try {
            if (command.equals("RQ")) {
                handleRequest(input);
            } else if (command.equals("RL")) {
                handleRelease(input);
            } else if (command.equals("C")) {
                manager.compact();
            } else if (command.equals("STAT")) {
                manager.stat();
            } else if (!command.equals("X")) {
                System.out.println("Unknown command");
            } else {
                System.out.println("Exiting program...");
            }
        } catch (Exception e) {
            System.out.println("Command error: " + e.getMessage());
        }
    }

    //***************************************************************
    //
    // Method:      handleRequest
    //
    // Description: Parses and validates a memory allocation request
    //              and delegates to the memory manager.
    //
    // Parameters:  input - user command arguments
    //
    // Returns:     None
    //
    //***************************************************************
    private void handleRequest(String[] input) {
        if (input.length != 4) {
            System.out.println("Usage: RQ <process> <size> <F|B|W>");
            return;
        }

        String process = input[1];
        int size = Integer.parseInt(input[2]);
        char strategy = input[3].toUpperCase().charAt(0);
        manager.request(process, size, strategy);
    }

    //***************************************************************
    //
    // Method:      handleRelease
    //
    // Description: Parses and processes a memory release request.
    //
    // Parameters:  input - user command arguments
    //
    // Returns:     None
    //
    //***************************************************************
    private void handleRelease(String[] input) {
        if (input.length != 2) {
            System.out.println("Usage: RL <process>");
            return;
        }

        manager.release(input[1]);
    }
}