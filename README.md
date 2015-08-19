# TP-Stats-Java-Library

### What is this?
This is a Java library that's essentially a shortcut to opening a connection to the socket which contains stats data. This library allows you to easily create Profile objects representing a user's profile and obtain stats from said profile.

### Usage
Clone the repository into a folder, add the .jar file to your build path, and run ProfileTest.java

### Documentation
Documentation is hosted on the [gh-pages branch](http://blaziken311.github.io/TP-Stats-Java-Library/). I haven't written all the try/catches, I'll just trust you to handle all the exceptions. :P

### Details
The Profile object (which represents a Tagpro user Profile) you must pass in an HttpRequest corresponding to that user's profile.

The HttpRequest constructor takes one parameter, the user's unique hash. So an example of building a profile would be:

```
Profile me = new Profile( HttpRequest( "5372be1edde54f6e091ebc42" ) );
```

You can then call the getter methods detailed in the documentation to get all the stats. For example.

```
int[] allArr = me.getAll();
```

### Q: Will this be updated often?
A: No.
