#!/bin/bash

rm -rf target
mkdir -p target

javac ./Main.java -d ./target 
cp -r ./src/assets ./target/src
cd target
java Main