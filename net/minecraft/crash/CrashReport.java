/*
Decompiled By LOSTED
https://github.com/LOSTEDs
LOSTED#8754
https://www.youtube.com/watch?v=xg2M21todDU&t=55s
"...Minecraft client created by professional developers exclusively for me..." - SuchSpeed
Here is a better way to say this, "...Minecraft client skidded by some random script kittens exclusively for me"
Please SuchSpeed, don't sue me... I just dumped the source...
For Educational Purposes Only...
*/

package net.minecraft.crash;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.src.CrashReportCpu;
import net.minecraft.src.CrashReporter;
import net.minecraft.src.Reflector;
import net.minecraft.util.ReportedException;
import net.minecraft.world.gen.layer.IntCache;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrashReport {
    private static final Logger logger = LogManager.getLogger();
    
    private final String description;
    
    private final Throwable cause;
    
    private final CrashReportCategory theReportCategory = new CrashReportCategory(this, "System Details");
    
    private final List<CrashReportCategory> crashReportSections = Lists.newArrayList();
    
    private File crashReportFile;
    
    private boolean field_85059_f = true;
    
    private StackTraceElement[] stacktrace = new StackTraceElement[0];
    
    private static final String __OBFID = "CL_00000990";
    
    private boolean reported = false;
    
    public CrashReport(String descriptionIn, Throwable causeThrowable) {
        this.description = descriptionIn;
        this.cause = causeThrowable;
        populateEnvironment();
    }
    
    private void populateEnvironment() {
        this.theReportCategory.addCrashSectionCallable("Minecraft Version", new Callable<String>() {
                    private static final String __OBFID = "CL_00001197";
                    
                    public String call() {
                        return "1.8.8";
                    }
                });
        this.theReportCategory.addCrashSectionCallable("Operating System", new Callable<String>() {
                    private static final String __OBFID = "CL_00001222";
                    
                    public String call() {
                        return String.valueOf(System.getProperty("os.name")) + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version");
                    }
                });
        this.theReportCategory.addCrashSectionCallable("CPU", (Callable<String>)new CrashReportCpu());
        this.theReportCategory.addCrashSectionCallable("Java Version", new Callable<String>() {
                    public String call() {
                        return String.valueOf(System.getProperty("java.version")) + ", " + System.getProperty("java.vendor");
                    }
                });
        this.theReportCategory.addCrashSectionCallable("Java VM Version", new Callable<String>() {
                    private static final String __OBFID = "CL_00001275";
                    
                    public String call() {
                        return String.valueOf(System.getProperty("java.vm.name")) + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor");
                    }
                });
        this.theReportCategory.addCrashSectionCallable("Memory", new Callable<String>() {
                    private static final String __OBFID = "CL_00001302";
                    
                    public String call() {
                        Runtime runtime = Runtime.getRuntime();
                        long i = runtime.maxMemory();
                        long j = runtime.totalMemory();
                        long k = runtime.freeMemory();
                        long l = i / 1024L / 1024L;
                        long i1 = j / 1024L / 1024L;
                        long j1 = k / 1024L / 1024L;
                        return String.valueOf(k) + " bytes (" + j1 + " MB) / " + j + " bytes (" + i1 + " MB) up to " + i + " bytes (" + l + " MB)";
                    }
                });
        this.theReportCategory.addCrashSectionCallable("JVM Flags", new Callable<String>() {
                    private static final String __OBFID = "CL_00001329";
                    
                    public String call() {
                        RuntimeMXBean runtimemxbean = ManagementFactory.getRuntimeMXBean();
                        List<String> list = runtimemxbean.getInputArguments();
                        int i = 0;
                        StringBuilder stringbuilder = new StringBuilder();
                        for (String s : list) {
                            if (s.startsWith("-X")) {
                                if (i++ > 0)
                                    stringbuilder.append(" "); 
                                stringbuilder.append(s);
                            } 
                        } 
                        return String.format("%d total; %s", new Object[] { Integer.valueOf(i), stringbuilder.toString() });
                    }
                });
        this.theReportCategory.addCrashSectionCallable("IntCache", new Callable<String>() {
                    private static final String __OBFID = "CL_00001355";
                    
                    public String call() throws Exception {
                        return IntCache.getCacheSizes();
                    }
                });
        if (Reflector.FMLCommonHandler_enhanceCrashReport.exists()) {
            Object object = Reflector.call(Reflector.FMLCommonHandler_instance, new Object[0]);
            Reflector.callString(object, Reflector.FMLCommonHandler_enhanceCrashReport, new Object[] { this, this.theReportCategory });
        } 
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public Throwable getCrashCause() {
        return this.cause;
    }
    
    public void getSectionsInStringBuilder(StringBuilder builder) {
        if ((this.stacktrace == null || this.stacktrace.length <= 0) && this.crashReportSections.size() > 0)
            this.stacktrace = (StackTraceElement[])ArrayUtils.subarray((Object[])((CrashReportCategory)this.crashReportSections.get(0)).getStackTrace(), 0, 1); 
        if (this.stacktrace != null && this.stacktrace.length > 0) {
            builder.append("-- Head --\n");
            builder.append("Stacktrace:\n");
            byte b;
            int i;
            StackTraceElement[] arrayOfStackTraceElement;
            for (i = (arrayOfStackTraceElement = this.stacktrace).length, b = 0; b < i; ) {
                StackTraceElement stacktraceelement = arrayOfStackTraceElement[b];
                builder.append("\t").append("at ").append(stacktraceelement.toString());
                builder.append("\n");
                b++;
            } 
            builder.append("\n");
        } 
        for (CrashReportCategory crashreportcategory : this.crashReportSections) {
            crashreportcategory.appendToStringBuilder(builder);
            builder.append("\n\n");
        } 
        this.theReportCategory.appendToStringBuilder(builder);
    }
    
    public String getCauseStackTraceOrString() {
        StringWriter stringwriter = null;
        PrintWriter printwriter = null;
        Object object = this.cause;
        if (((Throwable)object).getMessage() == null) {
            if (object instanceof NullPointerException) {
                object = new NullPointerException(this.description);
            } else if (object instanceof StackOverflowError) {
                object = new StackOverflowError(this.description);
            } else if (object instanceof OutOfMemoryError) {
                object = new OutOfMemoryError(this.description);
            } 
            ((Throwable)object).setStackTrace(this.cause.getStackTrace());
        } 
        String s = ((Throwable)object).toString();
        try {
            stringwriter = new StringWriter();
            printwriter = new PrintWriter(stringwriter);
            ((Throwable)object).printStackTrace(printwriter);
            s = stringwriter.toString();
        } finally {
            IOUtils.closeQuietly(stringwriter);
            IOUtils.closeQuietly(printwriter);
        } 
        return s;
    }
    
    public String getCompleteReport() {
        if (!this.reported) {
            this.reported = true;
            CrashReporter.onCrashReport(this, this.theReportCategory);
        } 
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("---- Minecraft Crash Report ----\n");
        Reflector.call(Reflector.BlamingTransformer_onCrash, new Object[] { stringbuilder });
        Reflector.call(Reflector.CoreModManager_onCrash, new Object[] { stringbuilder });
        stringbuilder.append("// ");
        stringbuilder.append(getWittyComment());
        stringbuilder.append("\n\n");
        stringbuilder.append("Time: ");
        stringbuilder.append((new SimpleDateFormat()).format(new Date()));
        stringbuilder.append("\n");
        stringbuilder.append("Description: ");
        stringbuilder.append(this.description);
        stringbuilder.append("\n\n");
        stringbuilder.append(getCauseStackTraceOrString());
        stringbuilder.append("\n\nA detailed walkthrough of the error, its code path and all known details is as follows:\n");
        for (int i = 0; i < 87; i++)
            stringbuilder.append("-"); 
        stringbuilder.append("\n\n");
        getSectionsInStringBuilder(stringbuilder);
        return stringbuilder.toString();
    }
    
    public File getFile() {
        return this.crashReportFile;
    }
    
    public boolean saveToFile(File toFile) {
        if (this.crashReportFile != null)
            return false; 
        if (toFile.getParentFile() != null)
            toFile.getParentFile().mkdirs(); 
        try {
            FileWriter filewriter = new FileWriter(toFile);
            filewriter.write(getCompleteReport());
            filewriter.close();
            this.crashReportFile = toFile;
            return true;
        } catch (Throwable throwable) {
            logger.error("Could not save crash report to " + toFile, throwable);
            return false;
        } 
    }
    
    public CrashReportCategory getCategory() {
        return this.theReportCategory;
    }
    
    public CrashReportCategory makeCategory(String name) {
        return makeCategoryDepth(name, 1);
    }
    
    public CrashReportCategory makeCategoryDepth(String categoryName, int stacktraceLength) {
        CrashReportCategory crashreportcategory = new CrashReportCategory(this, categoryName);
        if (this.field_85059_f) {
            int i = crashreportcategory.getPrunedStackTrace(stacktraceLength);
            StackTraceElement[] astacktraceelement = this.cause.getStackTrace();
            StackTraceElement stacktraceelement = null;
            StackTraceElement stacktraceelement1 = null;
            int j = astacktraceelement.length - i;
            if (j < 0)
                System.out.println("Negative index in crash report handler (" + astacktraceelement.length + "/" + i + ")"); 
            if (astacktraceelement != null && j >= 0 && j < astacktraceelement.length) {
                stacktraceelement = astacktraceelement[j];
                if (astacktraceelement.length + 1 - i < astacktraceelement.length)
                    stacktraceelement1 = astacktraceelement[astacktraceelement.length + 1 - i]; 
            } 
            this.field_85059_f = crashreportcategory.firstTwoElementsOfStackTraceMatch(stacktraceelement, stacktraceelement1);
            if (i > 0 && !this.crashReportSections.isEmpty()) {
                CrashReportCategory crashreportcategory1 = this.crashReportSections.get(this.crashReportSections.size() - 1);
                crashreportcategory1.trimStackTraceEntriesFromBottom(i);
            } else if (astacktraceelement != null && astacktraceelement.length >= i && j >= 0 && j < astacktraceelement.length) {
                this.stacktrace = new StackTraceElement[j];
                System.arraycopy(astacktraceelement, 0, this.stacktrace, 0, this.stacktrace.length);
            } else {
                this.field_85059_f = false;
            } 
        } 
        this.crashReportSections.add(crashreportcategory);
        return crashreportcategory;
    }
    
    private static String getWittyComment() {
        String[] astring = { 
                "Who set us up the TNT?", "Everything's going to plan. No, really, that was supposed to happen.", "Uh... Did I do that?", "Oops.", "Why did you do that?", "I feel sad now :(", "My bad.", "I'm sorry, Dave.", "I let you down. Sorry :(", "On the bright side, I bought you a teddy bear!", 
                "Daisy, daisy...", "Oh - I know what I did wrong!", "Hey, that tickles! Hehehe!", "I blame Dinnerbone.", "You should try our sister game, Minceraft!", "Don't be sad. I'll do better next time, I promise!", "Don't be sad, have a hug! <3", "I just don't know what went wrong :(", "Shall we play a game?", "Quite honestly, I wouldn't worry myself about that.", 
                "I bet Cylons wouldn't have this problem.", "Sorry :(", "Surprise! Haha. Well, this is awkward.", "Would you like a cupcake?", "Hi. I'm Minecraft, and I'm a crashaholic.", "Ooh. Shiny.", "This doesn't make any sense!", "Why is it breaking :(", "Don't do that.", "Ouch. That hurt :(", 
                "You're mean.", "This is a token for 1 free hug. Redeem at your nearest Mojangsta: [~~HUG~~]", "There are four lights!", "But it works on my machine." };
        try {
            return astring[(int)(System.nanoTime() % astring.length)];
        } catch (Throwable var2) {
            return "Witty comment unavailable :(";
        } 
    }
    
    public static CrashReport makeCrashReport(Throwable causeIn, String descriptionIn) {
        CrashReport crashreport;
        if (causeIn instanceof ReportedException) {
            crashreport = ((ReportedException)causeIn).getCrashReport();
        } else {
            crashreport = new CrashReport(descriptionIn, causeIn);
        } 
        return crashreport;
    }
}
