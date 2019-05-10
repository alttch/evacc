DIR_EVAHI=/opt/evaHI

DIR_ME=$(shell pwd)

all:
	rm -rf ${DIR_ME}/evacc_hi
	cd ${DIR_EVAHI} && ./prepare -f ${DIR_ME}/evacc.ini \
			-D ${DIR_ME}/evacc_hi \
			--icon ${DIR_ME}/res/ic_launcher_512.png \
			--icon-round ${DIR_ME}/res/ic_launcher_512_round.png

clean:
	rm -rf evacc_hi
