# Create the "compiled" directory if it doesn't exist
mkdir -p compiled

# Compile the .java files and place the .class files in the "target" directory
javac -d target src/*.java

# Create a manifest file with the main class specified
echo "Main-Class: Main" > manifest.txt

# Create a .jar file with the compiled .class files and the manifest file
jar cvfm compiled/myjar.jar manifest.txt -C target/ .

# Remove the manifest file
rm manifest.txt

mkdir compiled/src/assets/

cp -r src/assets/* compiled/src/assets/
