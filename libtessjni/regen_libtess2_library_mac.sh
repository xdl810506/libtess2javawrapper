#!/bin/bash

gcc -dynamiclib -o ./mac/libtess2.jnilib -I ${JAVA_HOME}/include/ ./src/main/jni/libtess2.c ./src/main/libtess2/Source/tess.c ./src/main/libtess2/Source/sweep.c ./src/main/libtess2/Source/priorityq.c ./src/main/libtess2/Source/mesh.c ./src/main/libtess2/Source/geom.c ./src/main/libtess2/Source/dict.c ./src/main/libtess2/Source/bucketalloc.c