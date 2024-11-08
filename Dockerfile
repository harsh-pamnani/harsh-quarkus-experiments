FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /work/
COPY target/*-runner /work/application
RUN chmod 775 /work
EXPOSE 9999
CMD ["./application", "-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=9999"]