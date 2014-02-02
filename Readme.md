##Prerequisites
* Install Maven
* Install JBoss EAP 6.2
 
##EnterpriseMusicstore
* Execute: mvn clean install -DskipTests -Pjsfunit
* Copy: EnterpriseMusicStore-ear/target/EnterpriseMusicStore-ear.ear -> $JBOSS_HOME/standalone/deployments
* Start the H2 Database with the Script: start_h2_database.sh
* Start JBoss: $JBOSS_HOME/bin/standalone.sh
* Navigate to: http://localhost:8080/musicstore/faces/index.xhtml
* Navigate to: http://localhost:8080/musicstore/jsfunit/index.jsfunit
* Execute: cd EnterpriseMusicStore-integration-tests; mvn integration-test

##bookstore-openejb
* Execute: mvn clean test

##bookstore-selenium
* Google Chrome must be installed
* bookstore must run under http://localhost:8080/books
* Execute: mvn clean test