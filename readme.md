#  Thingworx Pushover Plugin

Send notifications from [Thingworx](http://www.thingworx.com) to [Pushover](https://pushover.net/) 

Built using [pushover4j](https://github.com/sps/pushover4j)

This project comes in two version for Thingworx 4 and Thingworx 5 - I don’t currently have access to the TW6 jars to test that version.

Hopfully a version of this is avalaible from the [Thingworx Marketplace](http://marketplace.thingworx.com/)

To build a Thingworx plugin you will need to place the thingworx.jar into the lib folder 

    /TW4Plugin/lib...	thingworx-all-4.2.0.jar
    /TW5Plugin/lib...	thingworx-common-5.4.0.b445.jar

These .jars can't be included in this project as I don't have the authors permission.

This project uses Gradle (http://gradle.org/)

To build the plugins run: `gradle thingworxDist`  

#  Installation
Build the plugin by running the command `gradle thingworxDist` from either the TW5Plugin or TW4Plugin folder

The plugin will be created in \PushoverTWPlugin\TWxPlugin\build\distributions
Open Thingworx and import the plugin, the plugin creates a new Thing Template
Create a new Thing using the PushoverConnector as a Base Thing Template
Go to the configuration page and enter your Applications API token (this is not the same as your User Key).  If you don't have an Application registered then you will need to register one at https://pushover.net/apps/build
To send a message go to Services and selected the service Enter your User Key or Group Key


There are two services avalaible:

PushMessageShort justs requires a user (you API key: https://pushover.net/) and a message
PushMessageLong allows all the API parameters to be used; all parameters bar user and message are optional.  Timestamp can be ommited or use 0 to use current time  

For example you can call the service like this:

    var userKey = me.getUserPushoverKey({ userName: user.userName });
    var akURL = 'http://' + me.serverURL  + '/Thingworx/Things/ThingXXX/Services/NamedService?param=ACB&method=post';
    var priority = 1;
    
    var params = {
        message: eventData.message,
        title: 'Alert ' + eventName,
        priority: priority,
        sound: 'siren ',
        user: userKey,
        url_title: 'Acknowledge Alert ',
        url: akURL
    };
    
