DIR_EVAHI=/opt/evaHI

DIR_ME=$(shell pwd)

all: clean build

build:
	cd ${DIR_EVAHI} && ./prepare -f ${DIR_ME}/evacc.ini \
			-q -D ${DIR_ME}/evacc_hi \
			--icon ${DIR_ME}/res/ic_launcher_512.png \
			--icon-round ${DIR_ME}/res/ic_launcher_512_round.png

clean:
	rm -rf ${DIR_ME}/evacc_hi
