##Prerequisites
* Install Maven
* Install Glassfish
 
##EnterpriseBookstore
* Execute: mvn clean install -DskipTests
* Copy: EnterpriseMusicStore-ear/target/EnterpriseMusicStore-ear.ear -> $GLASSFISH_HOME/domains/domain1/autodeploy
* Start Galssfish: asadmin start-database; asadmin start-domain
* Navigate to: http://localhost:8080/EnterpriseMusicStore-web/faces/index.xhtml
* Navigate to: http://localhost:8080/EnterpriseMusicStore-web-jsfunit/jsfunit/index.jsfunit
* Execute: cd EnterpriseMusicStore-integration-tests; mvn integration-test

##bookstore-openejb
* Execute: mvn clean test

##bookstore-selenium
* Google Chrome must be installed
* bookstore must run under http://localhost:8080/books
* Execute: mvn clean test