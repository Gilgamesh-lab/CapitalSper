test:
	mvn -B package --file pom.xml
	
sonar:
	mvn clean org.jacoco:jacoco-maven-plugin:0.8.8:prepare-agent verify org.jacoco:jacoco-maven-plugin:0.8.8:report
	mvn verify sonar:sonar -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml