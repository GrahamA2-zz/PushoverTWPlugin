#  Thingworx Pushover Plugin

Send notifications from [Thingworx](http://www.thingworx.com) to [Pushover](https://pushover.net/)

This project comes in two version for Thingworx 4 and Thingworx 5 - I don;t currently have access to the TW6 jars to test that version.

To build a Thingworx plugin you will need to place the thingworx.jar into the lib folder 

    /TW4Plugin/lib...	thingworx-all-4.2.0.jar
    /TW5Plugin/lib...	thingworx-common-5.4.0.b445.jar

These .jars can't be included in this project as I don't have the authors permision.

To build the plugins run: `gradle thingworxDist`  