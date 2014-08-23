CrashTestDummy
==============

Illustrates a JVM crash I've found (Swing on X11).

The problem can be worked around by launching with 
-Dswing.systemlaf=javax.swing.plaf.nimbus.NimbusLookAndFeel
or
-Dswing.systemlaf=javax.swing.plaf.metal.MetalLookAndFeel

Repeatedly running CrashTestDummy (main) eventually causes:

    UIManager.getSystemLookAndFeelClassName() = com.sun.java.swing.plaf.gtk.GTKLookAndFeel
    The program 'java' received an X Window System error.
    This probably reflects a bug in the program.
    The error was 'RenderBadPicture (invalid Picture parameter)'.
      (Details: serial 251 error_code 143 request_code 139 minor_code 6)
      (Note to programmers: normally, X errors are reported asynchronously;
       that is, you will receive the error a while after causing it.
       To debug your program, run it with the --sync command line
       option to change this behavior. You can then get a meaningful
       backtrace from your debugger if you break on the gdk_x_error() function.)

The error doesn't consistently happen at every start-up, but "often enough" (3/4),
so is likely some sort of timing issue; step-by-step debugging through
UIManager.setLookAndFeel(); also does not (easily) cause it to happen.

on Ubuntu Linux 14.04 (64-bit) - at least on mine (unclear if it depends on GTK version?).

Its reproducible using on OpenJDK 1.8.0_11 & openjdk version "1.8.0-jdk8-b132" as well as Oracle Java 1.8.0_20-b26, but (for me) not on older versions.
While I have seen similar reports (based on Googling around) on older Java versions,
many of those appear to be related to Eclipse and Mozilla XUL (often SeaMonkey),
both of which are not in the picture in this case.  This makes me believe that
this is a new regression in Java 8 (as it works, for me, using either
OpenJDK 1.7.0_65 or Oracle Java 1.7.0_67 it's non-reproducible).

Originally https://github.com/TechnicPack/TechnicLauncher/issues/464;
similar issues found on bug trackers of other classic Swing/AWT apps.

If just purely for my learning I'd like to understand, how does one go about debugging something like this? I haven't found much info on the web. How you you launch "java" "with the --sync command line option" ? Or would I need to add something (what?) somewhere in JDK source? While I'm much more familiar with Java (non-UI, actually..) than with C & native interface, I have managed to build my own JDK, see http://blog2.vorburger.ch/2014/06/build-your-own-jdk-at-home.html, so if it's a simple extra parameter to X11 initialization somewhere, I could possibly manage... Maybe I'll try to blog a tutorial and depending on complexity could try to contribute a fix to OpenJDK. -- If you've actually read up to here - maybe you can help? 

___
http://bugreport.java.com/

2014-08-23: "Thank you for reporting this issue. We are evaluating this report and have stored this with an Review ID: JI-9014491. In the event this report is determined as a defect or enhancement request, it will be referenced with a new Bug ID and will be listed on Bugs.java.com."

JVM crash: program 'java' received an X Window System error RenderBadPicture ...

Linux feather 3.13.0-34-generic #60-Ubuntu SMP Wed Aug 13 15:45:27 UTC 2014 x86_64 x86_64 x86_64 GNU/Linux

java version "1.8.0_20"
Java(TM) SE Runtime Environment (build 1.8.0_20-b26)
Java HotSpot(TM) 64-Bit Server VM (build 25.20-b23, mixed mode)

JVM crash on Linux, terminated by X11, when invoking UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");

run my https://github.com/vorburger/CrashTestDummy - I've created a simple full example reliable reproducing it

I'm willing to help further debug this if required (provided I'm given due credit somewhere)

JVM should "never* crash ;)

    The program 'java' received an X Window System error.
    This probably reflects a bug in the program.
    The error was 'RenderBadPicture (invalid Picture parameter)'.
      (Details: serial 251 error_code 143 request_code 139 minor_code 6)
      (Note to programmers: normally, X errors are reported asynchronously;
       that is, you will receive the error a while after causing it.
       To debug your program, run it with the --sync command line
       option to change this behavior. You can then get a meaningful
       backtrace from your debugger if you break on the gdk_x_error() function.)

see https://github.com/vorburger/CrashTestDummy - I've created a simple full example reliable reproducing it