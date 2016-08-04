#!/bin/bash
mvn clean install -am && mvn exec:java -Dexec.mainClass="com.rhc.drools.example.Runner"
