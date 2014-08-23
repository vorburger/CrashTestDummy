CrashTestDummy
==============

Illustrates a JVM crash I've found (Swing on X11).

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

on (at least some) Ubuntu Linux 14.04 at least.  The error doesn't consistently happen at every start-up, but "often enough" - could be some sort of timing issue?

Originally https://github.com/TechnicPack/TechnicLauncher/issues/464;
similar issues found on bug trackers of other classic Swing/AWT apps.

Probably (?) unrelated to many similar bug reports on Eclipse (no SWT here)
and not related to Mozilla XUL which frequently appears in connection to above when researching this.  

If just purely for my learning I'd like to understand, how does one go about debugging something like this? I haven't found much info on the web. How you you launch "java" "with the --sync command line option" ? Or would I need to add something (what?) somewhere in JDK source? While I'm much more familiar with Java (non-UI, actually..) than with C & native interface, I have managed to build my own JDK, see http://blog2.vorburger.ch/2014/06/build-your-own-jdk-at-home.html, so if it's a simple extra parameter to X11 initialization somewhere, I could possibly manage... Maybe I'll try to blog a tutorial and depending on complexity could try to contribute a fix to OpenJDK. -- If you've actually read up to here - maybe you can help? 