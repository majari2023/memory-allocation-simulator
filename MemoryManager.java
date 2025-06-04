//********************************************************************
//
// Developer:     Mauricio Rivas
//
// Program #:     Five
//
// File Name:     MemoryManager
//
// Course:        COSC 4302 Operating Systems
//
// Due Date:      5/12/2025
//
// Instructor:    Prof. Fred Kumi
//
// Java Version:  11
//
// Description:   Manages memory allocation and deallocation using
//                various strategies (First Fit, Best Fit, Worst Fit).
//                Also supports memory compaction and status reporting.
//
//********************************************************************

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MemoryManager {
    private List<MemoryBlock> memory = new ArrayList<>();

    //***************************************************************
    //
    // Constructor:  MemoryManager
    //
    // Description:  Initializes the memory with one large unused block.
    //
    // Parameters:   sizeInBytes - total memory size in bytes
    //
    //***************************************************************
    
    public MemoryManager(int sizeInBytes) {
        memory.add(new MemoryBlock(0, sizeInBytes - 1, "Unused"));
    }
    
    //***************************************************************
    //
    // Method:      request
    //
    // Description: Allocates memory for a process using the given strategy.
    //
    // Parameters:  process - name of the process requesting memory
    //              size    - amount of memory requested (in bytes)
    //              strategy - allocation strategy ('F', 'B', or 'W')
    //
    //***************************************************************
    
    public void request(String process, int size, char strategy) {
        Comparator<MemoryBlock> comparator = switch (strategy) {
            case 'F' -> Comparator.comparingInt(b -> b.start);
            case 'B' -> Comparator.comparingInt(MemoryBlock::size);
            case 'W' -> Comparator.comparingInt(MemoryBlock::size).reversed();
            default -> null;
        };

        memory.stream()
               .filter(b -> b.process.equals("Unused") && b.size() >= size)
               .sorted(comparator)
               .findFirst()
               .ifPresentOrElse(block -> {
                   int newStart = block.start + size;
                   int oldEnd = block.end;
                   block.end = block.start + size - 1;
                   block.process = process;
                   if (newStart <= oldEnd) {
                       memory.add(memory.indexOf(block) + 1, new MemoryBlock(newStart, oldEnd, "Unused"));
                   }
               },
               () -> System.out.println("Error: Not enough memory"));
    }

    //***************************************************************
    //
    // Method:      release
    //
    // Description: Releases memory assigned to a process and merges
    //              adjacent unused blocks.
    //
    // Parameters:  process - name of the process to release
    //
    //***************************************************************
    
    public void release(String process) {
        for (int i = 0; i < memory.size(); i++) {
            MemoryBlock b = memory.get(i);
            if (b.process.equals(process)) {
                b.process = "Unused";
                if (i > 0 && memory.get(i - 1).process.equals("Unused")) {
                    MemoryBlock prev = memory.get(i - 1);
                    prev.end = b.end;
                    memory.remove(i);
                    i--;
                }
                if (i < memory.size() - 1 && memory.get(i + 1).process.equals("Unused")) {
                    MemoryBlock next = memory.get(i + 1);
                    b.end = next.end;
                    memory.remove(i + 1);
                }
                return;
            }
        }
        System.out.println("Error: Process not found");
    }

    //***************************************************************
    //
    // Method:      compact
    //
    // Description: Compacts all used memory blocks to the front and
    //              creates a single large unused block at the end.
    //
    // Parameters:  None
    //
    //***************************************************************
    
    public void compact() {
        int pointer = 0;
        List<MemoryBlock> newMemory = new ArrayList<>();
        for (MemoryBlock b : memory) {
            if (!b.process.equals("Unused")) {
                int newEnd = pointer + b.size() - 1;
                newMemory.add(new MemoryBlock(pointer, newEnd, b.process));
                pointer = newEnd + 1;
            }
        }
        int totalSize = getTotalSize();
        if (pointer < totalSize) {
            newMemory.add(new MemoryBlock(pointer, totalSize - 1, "Unused"));
        }
        memory = newMemory;
    }

    //***************************************************************
    //
    // Method:      stat
    //
    // Description: Prints a report of current memory usage showing
    //              the address ranges and the process assigned.
    //
    // Parameters:  None
    //
    //***************************************************************
    
    public void stat() {
        for (MemoryBlock b : memory) {
            System.out.printf("Addresses [%d:%d] %s\n", b.start, b.end, b.process);
        }
    }

    //***************************************************************
    //
    // Method:      getTotalSize
    //
    // Description: Returns the total size of memory currently being
    //              managed.
    //
    // Parameters:  None
    //
    // Returns:     int - total memory size in bytes
    //
    //***************************************************************
    
    private int getTotalSize() {
        return memory.stream().mapToInt(MemoryBlock::size).sum();
    }
}