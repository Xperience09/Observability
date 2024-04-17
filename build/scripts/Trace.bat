@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  Trace startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and TRACE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\Trace-0.0.1-SNAPSHOT-plain.jar;%APP_HOME%\lib\spring-boot-starter-web-3.2.3.jar;%APP_HOME%\lib\spring-boot-starter-security-3.2.3.jar;%APP_HOME%\lib\springdoc-openapi-starter-webmvc-ui-2.3.0.jar;%APP_HOME%\lib\opentelemetry-api-1.31.0.jar;%APP_HOME%\lib\bucket4j-core-8.10.1.jar;%APP_HOME%\lib\bucket4j-hazelcast-7.0.0.jar;%APP_HOME%\lib\cache-api-1.0.0.jar;%APP_HOME%\lib\hazelcast-5.3.5.jar;%APP_HOME%\lib\cucumber-core-7.16.1.jar;%APP_HOME%\lib\opentelemetry-context-1.31.0.jar;%APP_HOME%\lib\spring-boot-starter-json-3.2.3.jar;%APP_HOME%\lib\spring-boot-starter-3.2.3.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-3.2.3.jar;%APP_HOME%\lib\springdoc-openapi-starter-webmvc-api-2.3.0.jar;%APP_HOME%\lib\spring-webmvc-6.1.4.jar;%APP_HOME%\lib\spring-security-web-6.2.2.jar;%APP_HOME%\lib\spring-web-6.1.4.jar;%APP_HOME%\lib\spring-security-config-6.2.2.jar;%APP_HOME%\lib\springdoc-openapi-starter-common-2.3.0.jar;%APP_HOME%\lib\spring-boot-autoconfigure-3.2.3.jar;%APP_HOME%\lib\spring-boot-3.2.3.jar;%APP_HOME%\lib\spring-security-core-6.2.2.jar;%APP_HOME%\lib\spring-context-6.1.4.jar;%APP_HOME%\lib\spring-aop-6.1.4.jar;%APP_HOME%\lib\swagger-ui-5.10.3.jar;%APP_HOME%\lib\bucket4j-core-7.0.0.jar;%APP_HOME%\lib\cucumber-gherkin-messages-7.16.1.jar;%APP_HOME%\lib\cucumber-gherkin-7.16.1.jar;%APP_HOME%\lib\html-formatter-21.3.0.jar;%APP_HOME%\lib\junit-xml-formatter-0.3.0.jar;%APP_HOME%\lib\gherkin-28.0.0.jar;%APP_HOME%\lib\messages-24.1.0.jar;%APP_HOME%\lib\tag-expressions-6.1.0.jar;%APP_HOME%\lib\cucumber-expressions-17.1.0.jar;%APP_HOME%\lib\datatable-7.16.1.jar;%APP_HOME%\lib\cucumber-plugin-7.16.1.jar;%APP_HOME%\lib\docstring-7.16.1.jar;%APP_HOME%\lib\ci-environment-10.0.1.jar;%APP_HOME%\lib\apiguardian-api-1.1.2.jar;%APP_HOME%\lib\spring-boot-starter-logging-3.2.3.jar;%APP_HOME%\lib\jakarta.annotation-api-2.1.1.jar;%APP_HOME%\lib\spring-beans-6.1.4.jar;%APP_HOME%\lib\spring-expression-6.1.4.jar;%APP_HOME%\lib\spring-core-6.1.4.jar;%APP_HOME%\lib\swagger-core-jakarta-2.2.19.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.15.4.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.15.4.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.15.4.jar;%APP_HOME%\lib\jackson-databind-2.15.4.jar;%APP_HOME%\lib\swagger-models-jakarta-2.2.19.jar;%APP_HOME%\lib\jackson-annotations-2.15.4.jar;%APP_HOME%\lib\jackson-core-2.15.4.jar;%APP_HOME%\lib\jackson-dataformat-yaml-2.15.4.jar;%APP_HOME%\lib\snakeyaml-2.2.jar;%APP_HOME%\lib\tomcat-embed-websocket-10.1.19.jar;%APP_HOME%\lib\tomcat-embed-core-10.1.19.jar;%APP_HOME%\lib\tomcat-embed-el-10.1.19.jar;%APP_HOME%\lib\micrometer-observation-1.12.3.jar;%APP_HOME%\lib\logback-classic-1.4.14.jar;%APP_HOME%\lib\log4j-to-slf4j-2.21.1.jar;%APP_HOME%\lib\jul-to-slf4j-2.0.12.jar;%APP_HOME%\lib\spring-jcl-6.1.4.jar;%APP_HOME%\lib\micrometer-commons-1.12.3.jar;%APP_HOME%\lib\spring-security-crypto-6.2.2.jar;%APP_HOME%\lib\logback-core-1.4.14.jar;%APP_HOME%\lib\slf4j-api-2.0.12.jar;%APP_HOME%\lib\log4j-api-2.21.1.jar;%APP_HOME%\lib\commons-lang3-3.13.0.jar;%APP_HOME%\lib\swagger-annotations-jakarta-2.2.19.jar;%APP_HOME%\lib\jakarta.xml.bind-api-4.0.1.jar;%APP_HOME%\lib\jakarta.validation-api-3.0.2.jar;%APP_HOME%\lib\jakarta.activation-api-2.1.2.jar


@rem Execute Trace
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %TRACE_OPTS%  -classpath "%CLASSPATH%"  %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable TRACE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%TRACE_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
