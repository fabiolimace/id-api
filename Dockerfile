FROM tomee:9.1.2-webprofile

# IDs per request
ENV ID_API_LIMIT 1000

COPY ./target/id-api.war /usr/local/tomee/webapps/

