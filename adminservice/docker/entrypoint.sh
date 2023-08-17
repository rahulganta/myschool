#!/usr/bin/env sh

echo "Running myschool-web-service"

echo "Configured in YML : Xmx value is $XMX";
echo "Configured in YML : Xms value is $XMS";
echo "Configured in YML : MAX_METASPACE_SIZE is $MAX_METASPACE_SIZE";
echo "Configured in YML : GC_CONFIGURATION Config of $GC_CONFIGURATION";
echo "Configured in YML : JAVA_OPTS Config of $JAVA_OPTS";

if [ ! -n "$XMX" ]; then
    XMX=1024m
    echo "Updating Xmx with $XMX"
fi

if [ ! -n "$XMS" ]; then
    XMS=512m
    echo "Updating Xms with $XMS"
fi

if [ ! -n "$MAX_METASPACE_SIZE" ]; then
    MAX_METASPACE_SIZE=300m
    echo "Updating MAX_METASPACE_SIZE with $MAX_METASPACE_SIZE"
fi

if [ ! -n "$GC_CONFIGURATION" ]; then
    GC_CONFIGURATION="-server -XX:+UseG1GC -XX:MaxMetaspaceSize=$MAX_METASPACE_SIZE -XX:MetaspaceSize=128m -XX:CompressedClassSpaceSize=128m -XX:+ScavengeBeforeFullGC"
    echo "Updating GC_CONFIGURATION with $GC_CONFIGURATION"
fi

if [ "$DEBUG_MICRO" = "true" ]; then
    echo "Java OPTS before is $JAVA_OPTS";
    export JAVA_OPTS="$JAVA_OPTS  -agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n "
    echo "Java OPTS after is $JAVA_OPTS";
fi

#JAVACOMMAND="java $JAVA_OPTS -Xlog:gc -jar -Xms$XMS -Xmx$XMX $GC_CONFIGURATION /myschool/myschool-web-service.jar"
JAVACOMMAND="java -jar /myschool/myschool-web-service.jar"
echo "Command called to start service is : java $JAVACOMMAND"
$JAVACOMMAND