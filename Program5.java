//********************************************************************
//
// Developer:     Mauricio Rivas
//
// Program #:     Five
//
// File Name:     Program5
//
// Course:        COSC 4302 Operating Systems
//
// Due Date:      5/12/2025
//
// Instructor:    Prof. Fred Kumi
//
// Java Version:  11
//
// Description:    Main test class for Program 5 that launches
//                the memory allocation program using user commands.
//                All logic is delegated to non-static methods in
//                Program5TestRunner.
//
//********************************************************************

public class Program5 {
	
	//***************************************************************
    //
    //  Method:      main
    //
    //  Description: Entry point of the program. It creates an instance
    //               of Program5TestRunner and calls its startProgram method.
    //               No processing is done in this method directly.
    //
    //  Parameters:  String[] args - command-line arguments (not used)
    //
    //  Returns:     None
    //
    //***************************************************************
	
    public static void main(String[] args) {
        Program5TestRunner runner = new Program5TestRunner();
        runner.startProgram();
        DeveloperInfo();
    }
    //***************************************************************
    //
    // Method:      DeveloperInfo
    //
    // Description: Displays metadata about the developer and program.
    //
    // Parameters:  None
    //
    // Returns:     None
    //
    //***************************************************************
    public static void DeveloperInfo() {
        System.out.println("Name:     Mauricio Rivas");
        System.out.println("Course:   COSC 4302 - Operating Systems");
        System.out.println("Program:  Five");
        System.out.println("Due Date: 5/12/2025\n");
    }
}