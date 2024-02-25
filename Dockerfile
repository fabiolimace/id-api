FROM tomee:9.1.2-webprofile

COPY ./target/id-api.war /usr/local/tomee/webapps/

