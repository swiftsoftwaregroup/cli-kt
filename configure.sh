#!/usr/bin/env bash

# Java

source ~/.sdkman/bin/sdkman-init.sh

sdk install java 21.0.4-tem

sdk install kotlin 2.0.20

sdk install gradle 8.10

sdk env

gradle wrapper
