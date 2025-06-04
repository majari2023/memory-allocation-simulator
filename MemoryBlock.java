//********************************************************************
//
// Developer:     Mauricio Rivas
//
// Program #:     Five
//
// File Name:     MemoryBlock
//
// Course:        COSC 4302 Operating Systems
//
// Due Date:      5/12/2025
//
// Instructor:    Prof. Fred Kumi
//
// Java Version:  11
//
// Description:   Represents a block of memory with a starting and
//                ending address and the process assigned to it.
//
//********************************************************************

public class MemoryBlock {
    int start, end;
    String process;

    //***************************************************************
    //
    // Constructor:  MemoryBlock
    //
    // Description:  Initializes a memory block with start and end
    //               addresses and the associated process name.
    //
    // Parameters:   start    - starting memory address
    //               end      - ending memory address
    //               process  - name of the process assigned to the block
    //
    // Returns:      N/A
    //
    //***************************************************************
    
    public MemoryBlock(int start, int end, String process) {
        this.start = start;
        this.end = end;
        this.process = process;
    }

    //***************************************************************
    //
    // Method:      size
    //
    // Description: Calculates and returns the size of the memory block.
    //
    // Parameters:  None
    //
    // Returns:     int - number of bytes in the memory block
    //
    //***************************************************************
    
    public int size() {
        return end - start + 1;
    }
}