#!/bin/bash

flag=false
#x=0
apk=".apk"

for entry in "apks"/*
do
	#echo iteration $x
	#let "x++"
	while IFS=",$IFS" read appName script iframe
	do
		if [ "${entry:5}" == "$appName$apk" ]; then
			flag=true
			#echo $flag entry ${entry:5} equals $appName
			break
		else
			flag=false
			#echo $flag entry ${entry:5} dne $appName
		fi
	done < 3rdPartyIncludes.csv

	#echo afterWhile
	if [ $flag == false ]; then
		rm -rf $entry
		#echo not 3rd Party
	fi

done