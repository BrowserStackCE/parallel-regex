# Run tests in parallel to test API limits
This repo is to run multiple tests in parallel across different devices using Dynamic device allocation

## Pre-Requisites
* Upload the app to BrowserStack and get the `bs:<url>` using the upload API in the below link
```sh
  curl -u "BROWSERSTACK_USERNAME:BROWSERSTACK_ACCESS_KEY" \
  -X POST "https://api-cloud.browserstack.com/app-automate/upload" \
  -F "file=@/path/to/app/file/application-debug.apk"  
```

## Setup
* Clone the repo
* Install dependencies `mvn clean install`
* You can setup environment variables for all sample repos (see Notes) or update `browserstak.yml`
* You can export the environment variables for the Username and Access Key of your BrowserStack account
```sh
export BROWSERSTACK_USERNAME=<browserstack-username> &&
export BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
```
* Update the app id in the browserstack.yml file
```
app: bs://<url>
```
* To run the test, execute command `mvn clean test`
## Notes
Presently the repo is configured to trigger 40 parallels. If you would like to change the number, you will have to update in three places
* To trigger 80 tests in parallel, you can update the parallelsPerPlatform: 80 in the yml file . This can be updated to any number within your parallel license limit.
* Update the dataProviderThreadCount to 80 in pom.xml
```
<property>
    <name>dataproviderthreadcount</name>
    <value>80</value>
</property>
```
* Update the thread-count to 80 in the config/appium.testng xml file
```
<suite name="AppiumTest" parallel="methods" thread-count="80">
```