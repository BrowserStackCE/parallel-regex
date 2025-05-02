# Run tests in parallel to test API limits
This repo is to run multiple tests in parallel across different devices using Dynamic device allocation

## Pre-Requisite

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
