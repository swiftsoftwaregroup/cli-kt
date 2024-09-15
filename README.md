# cli-kt

Template for Command Line Interface (CLI) tool in Kotlin

## Setup for macOS

### Xcode Command Line Tools

Install Command Line Tools (CLT) for Xcode:

```bash
xcode-select --install
```

### Homebrew

Install [Homebrew](https://brew.sh/):

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```

### SDKMAN!

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

## Work on macOS

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

### Run as JAR

Build JAR:

```bash
./gradlew shadowJar
```

Run as JAR:

```bash
echo "John" > name.txt

java -jar build/libs/cli-kt.jar greet name.txt
java -jar build/libs/cli-kt.jar greet --language es name.txt
java -jar build/libs/cli-kt.jar greet -l bg name.txt
```

### Test

Run all tests:

```bash
./gradlew test

 GreetCommand Tests

    generateGreeting function

      ✔ should generate correct greetings for different languages

    GreetCommand execution

      ✔ should greet in English by default
      ✔ should handle non-existent file
      ✔ should greet in Spanish when specified
      ✔ should greet in Bulgarian when specified

    readNameFromFile function

      ✔ should read name correctly from file

  6 passing (698ms)
```

Run specific test:

```bash
./gradlew test --tests "GreetCommandTest\$GenerateGreetingTests.testGenerateGreeting"

GreetCommand Tests

    generateGreeting function

      ✔ should generate correct greetings for different languages

  1 passing (558ms)
```

