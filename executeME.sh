#!/bin/bash
mkdir bin
javac -d bin/ src/vic/*.java
java -cp bin/ vic.ReservationManagerConsole
