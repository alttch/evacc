DIR_ME=$(shell pwd)
DIR_EVAHI=/opt/evaHI

all:
	rm -rf ${DIR_ME}/evacc_hi
	cd ${DIR_EVAHI} && ./prepare -f ${DIR_ME}/evacc.ini -D ${DIR_ME}/evacc_hi -q
	cp -rf res/* ${DIR_ME}/evacc_hi/app/src/main/res/
