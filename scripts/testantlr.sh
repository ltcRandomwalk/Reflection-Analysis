java -Xmx16g -jar build/tai-e-all-0.5.2-SNAPSHOT.jar \
    -acp java-benchmarks/dacapo-2006/antlr.jar \
    -cp java-benchmarks/dacapo-2006/antlr-deps.jar \
    -java 6 \
    -m Harness \
    -a pta="only-app:false;distinguish-string-constants:all;reflection-inference:solar;dump-yaml:true;plugins:[pascal.taie.analysis.pta.plugin.ResultProcessor]"