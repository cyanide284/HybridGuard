#!/bin/bash


#while IFS=",$IFS" read appName script iframe
#	do
#		if [ "$appName" == "dvt.glacier_7" ]; then
#			echo $appName
#		fi
#	done < 3rdPartyIncludes.csv
x="string.apk"

#y=${x}

#echo ${x%???}

#z="eduthek.info.messerlocations_20005.apk"
z="apks/TEST.apk"
w="${z:5}"
#echo $w

java -jar apktool.jar d -o Apps/${w%????} -f $z
#mv ${z%????} Apps



#mv ${z%????} Apps
