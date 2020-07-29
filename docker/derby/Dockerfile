FROM alpine:latest

MAINTAINER Sharkozp <sharkozp@gmail.com>

ARG DERBY_VERSION=10.15.2.0
ENV DERBY_VERSION=${DERBY_VERSION}
ENV TMP_PATH=/tmp/src
ENV WORK_PATH=/opt

RUN apk add openjdk11

RUN mkdir -p $TMP_PATH $WORK_PATH && cd $TMP_PATH \
    && wget -P $TMP_PATH http://apache.mirror.iphh.net/db/derby/db-derby-${DERBY_VERSION}/db-derby-${DERBY_VERSION}-bin.tar.gz \
    && tar zxf db-derby-${DERBY_VERSION}-bin.tar.gz && rm db-derby-${DERBY_VERSION}-bin.tar.gz && mv db-derby-${DERBY_VERSION}-bin $WORK_PATH/derby

# EXPOSE 1527 1527