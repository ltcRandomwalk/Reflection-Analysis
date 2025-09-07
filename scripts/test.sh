java -Xmx16g -jar build/tai-e-all-0.5.2-SNAPSHOT.jar \
    -acp /SSD2/tcli/Tai-e/testcode/test \
    -java 6 \
    -m ReflectionDemo \
    -a pta="only-app:true;distinguish-string-constants:all;reflection-inference:string-constant;reflection-log:/SSD2/tcli/out/refl.log;dump-yaml:true;plugins:[pascal.taie.analysis.pta.plugin.ResultProcessor]"