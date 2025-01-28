test:
	mvn -B package --file pom.xml
	
sonar:
	start "C:\Users\fabri\Documents\Projet\sonarqube-10.5.0.89998\sonarqube-10.5.0.89998\bin\windows-x86-64\StartSonar.bat"
	mvn clean org.jacoco:jacoco-maven-plugin:0.8.8:prepare-agent verify org.jacoco:jacoco-maven-plugin:0.8.8:report
	mvn verify sonar:sonar -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
	mvn clean
	
run:
	mvn spring-boot:run