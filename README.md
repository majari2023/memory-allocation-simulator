# memory-allocation-simulator
Java program that simulates contiguous memory allocation using First Fit, Best Fit, and Worst Fit strategies.
This Java project simulates contiguous memory allocation using First Fit, Best Fit, and Worst Fit strategies. It handles allocation, deallocation, memory compaction, and provides a status view of memory blocks.

## Features
- Allocate memory to processes using First Fit (F), Best Fit (B), or Worst Fit (W)
- Release memory
- Compact memory
- View current memory block status

## Files
- `MemoryBlock.java` — Represents a block of memory.
- `MemoryManager.java` — Core class that manages allocation and deallocation.
- `Program5.java` — Entry point that routes commands (no processing in `main()`).
- `Program5TestRunner.java` — Unit test runner for validation.

- Example Commands
RQ P1 300000 F
RQ P2 200000 B
STAT
RL P1
C
X
