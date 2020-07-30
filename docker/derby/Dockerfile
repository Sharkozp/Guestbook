FROM openjdk:11-jre

MAINTAINER Sharkozp "sharkozp@gmail.com"

ARG DERBY_VERSION=10.15.2.0
ENV DERBY_VERSION=${DERBY_VERSION}
ENV TMP_PATH=/tmp/src
ENV DERBY_HOME=/opt/derby
ENV DERBY_LIB=${DERBY_HOME}/lib
ENV CLASSPATH=${DERBY_LIB}/derby.jar:${DERBY_LIB}/derbynet.jar:${DERBY_LIB}/derbytools.jar:${DERBY_LIB}/derbyoptionaltools.jar:${DERBY_LIB}/derbyclient.jar

RUN mkdir -p $TMP_PATH && cd $TMP_PATH \
    && wget -P $TMP_PATH https://dist.apache.org/repos/dist/release/db/derby/db-derby-${DERBY_VERSION}/db-derby-${DERBY_VERSION}-bin.tar.gz \
    && tar zxf db-derby-${DERBY_VERSION}-bin.tar.gz && rm db-derby-${DERBY_VERSION}-bin.tar.gz && mv db-derby-${DERBY_VERSION}-bin $DERBY_HOME \
    && rm -Rf ${DERBY_HOME}/demo ${DERBY_HOME}/javadoc ${DERBY_HOME}/docs ${DERBY_HOME}/test ${DERBY_HOME}/*.html ${DERBY_HOME}/KEYS

WORKDIR /dbs
VOLUME ["/dbs"]
EXPOSE 1527

CMD ["java", "-Dderby.stream.error.field=System.out", "org.apache.derby.drda.NetworkServerControl", "start", "-h", "0.0.0.0"]