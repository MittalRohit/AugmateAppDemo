#!/bin/sh 
libsvm-2.9/svm-scale -l -1 -u 1 -s range1 train.1 > train.1.scale$1
libsvm-2.9/svm-train -c 512 -t 0 -q train.1.scale$1