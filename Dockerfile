# Use the official JDK 19 image as the base image for the build stage
FROM openjdk:19-jdk AS build

WORKDIR /work/

# Copie os arquivos de compilação para o contêiner
COPY target/*-runner /work/application

# Crie a imagem nativa
RUN chmod 775 /work/application

# Use a imagem leve do Alpine como base
FROM alpine:3.14

WORKDIR /work/

# Copie o executável nativo para a imagem final
COPY --from=builder /work/application /work/application

# Defina a variável de ambiente para o perfil Quarkus
ENV QUARKUS_PROFILE=prod

EXPOSE 8080

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
