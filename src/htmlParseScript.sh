#!/bin/bash
#htmlParseScript


for entry in "Apps"/*
do
  #echo "$entry"
  #java -classpath . testPackage/Hello $entry
  javac -classpath .:jsoup-1.11.2.jar com/xml/parser/HTMLParser.java
  java -classpath .:jsoup-1.11.2.jar com/xml/parser/HTMLParser $entry
done