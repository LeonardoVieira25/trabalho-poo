
# Leonardo Vieira Silva - 202235038
# Pablo Henrique Silva de Faria - 202235012

rm -rf compiled

mkdir -p compiled

javac -d target src/*.java

echo "Main-Class: Main" > manifest.txt

jar cvfm compiled/Jogo.jar manifest.txt -C target/ .

rm manifest.txt

mkdir compiled/src

mkdir compiled/src/assets/

cp -r src/assets/* compiled/src/assets/
