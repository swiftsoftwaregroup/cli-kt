# cli-kt

Template for Command Line Interface (CLI) tool in Kotlin

## Development

### Setup for macOS

#### Xcode Command Line Tools

Install Command Line Tools (CLT) for Xcode:

```bash
xcode-select --install
```

#### Homebrew

Install [Homebrew](https://brew.sh/):

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

#### SDKMAN!

Install:

```bash
curl -s "https://get.sdkman.io" | bash
source ~/.sdkman/bin/sdkman-init.sh
```

Test:

```bash
sdk list java
```

Configure:

```bash
# open config file
sdk config

# set: 
# sdkman_auto_answer=true

# save the file
```

### Work on macOS

Configure project:

```bash
source configure.sh
```

Open the project in Visual Studio Code:

```bash
code .
```

### Build

```bash
./gradlew build
```

###  Run

```bash
echo "John" > name.txt

./gradlew run --args="greet name.txt"
./gradlew run --args="greet --language es name.txt"
./gradlew run --args="greet -l bg name.txt"
```

### Build JAR

```bash
./gradlew shadowJar
```

### Run JAR

```bash
echo "John" > name.txt

java -jar build/libs/cli-kt.jar greet name.txt
java -jar build/libs/cli-kt.jar greet --language es name.txt
java -jar build/libs/cli-kt.jar greet -l bg name.txt
```

