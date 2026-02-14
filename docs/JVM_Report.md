# JVM Report

## 1. Class Loader

The JVM uses a three-tier class loading mechanism:

- Bootstrap Class Loader – Loads core Java classes (rt.jar).
- Extension Class Loader – Loads extension libraries.
- Application Class Loader – Loads application-level classes.

MediTrack classes are loaded by the Application Class Loader.

---

## 2. Runtime Data Areas

### Heap
Stores all objects and instance variables.
Doctor, Patient, Appointment, Bill objects are stored in heap memory.

### Stack
Each thread has its own stack.
Method calls and local variables are stored here.

### Method Area
Stores class metadata, static variables, and method definitions.

### PC Register
Holds address of currently executing instruction.

---

## 3. Execution Engine

The Execution Engine executes bytecode using:

- Interpreter – Executes bytecode line by line.
- JIT Compiler – Converts frequently used bytecode into native machine code for better performance.

---

## 4. JIT vs Interpreter

Interpreter:
- Slower
- Executes instruction-by-instruction

JIT Compiler:
- Faster
- Converts bytecode to native code

---

## 5. Write Once, Run Anywhere

Java source code → compiled into bytecode → executed by JVM.

Since JVM exists for multiple OS:
- Windows
- Linux
- macOS

Same bytecode runs everywhere.
