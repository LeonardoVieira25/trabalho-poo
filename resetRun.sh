# Leonardo Vieira Silva - 202235038
# Pablo Henrique Silva de Faria - 202235012


#!/bin/bash

rm -rf target
mkdir -p target

javac ./Main.java -d ./target 
cp -r ./src/assets ./target/src
cd target
java Main