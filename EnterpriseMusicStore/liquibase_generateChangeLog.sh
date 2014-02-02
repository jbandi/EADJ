export CP=$M2_REPO/org/liquibase/liquibase-core/3.1.1/*
CP=${CP}:$M2_REPO/com/h2database/h2/1.3.170/*

java  -classpath $CP liquibase.integration.commandline.Main --driver=org.h2.Driver --url=jdbc:h2:tcp://localhost/~/musicstore --username=sa --password= --diffTypes=tables,views,columns,indexes,foreignkeys,primarykeys,uniqueconstraints,data generateChangeLog
