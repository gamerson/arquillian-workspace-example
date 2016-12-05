# arquillian-workspace-example

This sample shows how to use combination of Liferay workspace project layout and Arquillian Liferay extension.  Most of the code was taken from the example shown here. https://github.com/liferay-labs/arquillian-blade-example

## Run all example integration tests

```
./gradlew initBundle
./gradlew testIntegration
```

## Run just individual project integration tests

This below assumes that you have executed `./gradlew initBundle` at least once before running any tests

```
cd modules/sample-service
../../gradlew testIntegration
```
