# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# คัดลอกไฟล์ pom และ preload dependencies ก่อน เพื่อ cache dependency
COPY pom.xml .
RUN mvn -q -B dependency:go-offline

# copy source แล้ว build
COPY src ./src
RUN mvn -q -B -DskipTests package

# ---- Runtime stage (distroless หรือ jre slim) ----
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# set timezone/locale หากต้องการ
ENV TZ=Asia/Bangkok \
    JAVA_TOOL_OPTIONS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

# สร้าง user ไม่รันเป็น root (ปลอดภัยขึ้น)
RUN useradd -ms /bin/bash springuser
USER springuser

# ค้นหาไฟล์ .jar ล่าสุดจากสเตจ build แล้วคัดลอกเข้ามา
COPY --from=build /app/target/springboot-example-images.jar app.jar

# ใช้ prod profile ตอนรันจริง; ปรับได้ตาม infra ของคุณ
# ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 9000
ENTRYPOINT ["java","-jar","/app/app.jar"]