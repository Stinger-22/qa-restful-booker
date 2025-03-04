# Testing restful-booker website

## Features
- [x] Used Maven wrapper
- [x] ~~- Used JUnit 5 test framework~~ Migrated to TestNG
- [x] Used SLF4J with Log4J for logging with TestNG
- [x] Used REST-Assured interactions with API and API testing
- [ ] Used Cucumber
- [ ] Used Playwright for E2E testing
- [x] Set up Jenkins pipeline

## How to launch tests
### Manually
Follow https://github.com/mwinteringham/restful-booker instructions for setting up the service under test.
Launch tests from this repository using any preferred way (Maven, IDE).
### With Jenkins
Follow README.md in `./ci` folder. 