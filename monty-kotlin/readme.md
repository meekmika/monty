# Code Test: Monty Hall Problem
## Kotlin
08-Mar-2019

----
## Usage

This application expects an integer to be passed as a program argument, which represents the number of simulations to run.

You can execute the application by

* opening the file in IntelliJ IDEA and providing an integer as a program argument
  
   `Run > Edit Configurations...`
    
   *or*

* using the command line compiler.

---
### Using the command line compiler

1. Install Kotlin

   e.g with SDKMAN! `$ sdk install kotlin`

   or with Homebew `$ brew install kotlin`

2. Compile the app

   `$ kotlinc Monty.kt -include-runtime -d Monty.jar`

3. Run the application with a single integer argument

   `$ java -jar Monty.jar 10000`
