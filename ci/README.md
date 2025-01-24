# Jenkins setup

1. Download Docker image and run it
    ```
    docker pull jenkins/jenkins:lts-jdk17
    docker run -d -v jenkins_home:/var/jenkins_home -p 8080:8080 -p 50000:50000 --restart=on-failure --dns=8.8.8.8 jenkins/jenkins:lts-jdk17
    ```
2. Create Linux based Agent with the following tools: git, jq, docker.
3. Create new Pipeline. In the Pipeline definition script select "Pipeline script from SCM", select Git and paste repository url `https://github.com/Stinger-22/qa-restful-booker.git` and in Script Path write `ci/Jenkinsfile` (preferred), or copy-paste the Jenkinsfile from the current directory.
4. Run the newly created Pipeline.