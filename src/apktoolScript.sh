#!/bin/bash
# My first script

for entry in "apks"/*
do
	x="${entry:5}"
	java -jar apktool.jar d -o Apps/${x%????} -f $entry
	#mv ${entry%????} Apps

  #echo "$entry"
  #java -classpath . testPackage/Hello $entry
  #javac com/xml/parser/XMLParser.java
  #java -classpath . com/xml/parser/XMLParser $entry
done
