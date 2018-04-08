#!/bin/bash
# My first script

for entry in "Apps"/*
do
  #echo "$entry"
  #java -classpath . testPackage/Hello $entry
  javac com/xml/parser/XMLParser.java
  java -classpath . com/xml/parser/XMLParser $entry
done