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

package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.IOExceptionList;
import org.apache.commons.io.IOIndexedException;

public class FilterCollectionWriter extends Writer {
    protected final Collection<Writer> EMPTY_WRITERS = Collections.emptyList();
    
    protected final Collection<Writer> writers;
    
    protected FilterCollectionWriter(Collection<Writer> writers) {
        this.writers = (writers == null) ? this.EMPTY_WRITERS : writers;
    }
    
    protected FilterCollectionWriter(Writer... writers) {
        this.writers = (writers == null) ? this.EMPTY_WRITERS : Arrays.<Writer>asList(writers);
    }
    
    public Writer append(char c) throws IOException {
        List<Exception> causeList = new ArrayList<>();
        int i = 0;
        for (Writer w : this.writers) {
            if (w != null)
                try {
                    w.append(c);
                } catch (IOException e) {
                    causeList.add(new IOIndexedException(i, e));
                }  
            i++;
        } 
        if (!causeList.isEmpty())
            throw new IOExceptionList(causeList); 
        return this;
    }
    
    public Writer append(CharSequence csq) throws IOException {
        List<Exception> causeList = new ArrayList<>();
        int i = 0;
        for (Writer w : this.writers) {
            if (w != null)
                try {
                    w.append(csq);
                } catch (IOException e) {
                    causeList.add(new IOIndexedException(i, e));
                }  
            i++;
        } 
        if (!causeList.isEmpty())
            throw new IOExceptionList(causeList); 
        return this;
    }
    
    public Writer append(CharSequence csq, int start, int end) throws IOException {
        List<Exception> causeList = new ArrayList<>();
        int i = 0;
        for (Writer w : this.writers) {
            if (w != null)
                try {
                    w.append(csq, start, end);
                } catch (IOException e) {
                    causeList.add(new IOIndexedException(i, e));
                }  
            i++;
        } 
        if (!causeList.isEmpty())
            throw new IOExceptionList(causeList); 
        return this;
    }
    
    public void close() throws IOException {
        List<Exception> causeList = new ArrayList<>();
        int i = 0;
        for (Writer w : this.writers) {
            if (w != null)
                try {
                    w.close();
                } catch (IOException e) {
                    causeList.add(new IOIndexedException(i, e));
                }  
            i++;
        } 
        if (!causeList.isEmpty())
            throw new IOExceptionList(causeList); 
    }
    
    public void flush() throws IOException {
        List<Exception> causeList = new ArrayList<>();
        int i = 0;
        for (Writer w : this.writers) {
            if (w != null)
                try {
                    w.flush();
                } catch (IOException e) {
                    causeList.add(new IOIndexedException(i, e));
                }  
            i++;
        } 
        if (!causeList.isEmpty())
            throw new IOExceptionList(causeList); 
    }
    
    public void write(char[] cbuf, int off, int len) throws IOException {
        List<Exception> causeList = new ArrayList<>();
        int i = 0;
        for (Writer w : this.writers) {
            if (w != null)
                try {
                    w.write(cbuf, off, len);
                } catch (IOException e) {
                    causeList.add(new IOIndexedException(i, e));
                }  
            i++;
        } 
        if (!causeList.isEmpty())
            throw new IOExceptionList(causeList); 
    }
    
    public void write(char[] cbuf) throws IOException {
        List<Exception> causeList = new ArrayList<>();
        int i = 0;
        for (Writer w : this.writers) {
            if (w != null)
                try {
                    w.write(cbuf);
                } catch (IOException e) {
                    causeList.add(new IOIndexedException(i, e));
                }  
            i++;
        } 
        if (!causeList.isEmpty())
            throw new IOExceptionList(causeList); 
    }
    
    public void write(int c) throws IOException {
        List<Exception> causeList = new ArrayList<>();
        int i = 0;
        for (Writer w : this.writers) {
            if (w != null)
                try {
                    w.write(c);
                } catch (IOException e) {
                    causeList.add(new IOIndexedException(i, e));
                }  
            i++;
        } 
        if (!causeList.isEmpty())
            throw new IOExceptionList(causeList); 
    }
    
    public void write(String str) throws IOException {
        List<Exception> causeList = new ArrayList<>();
        int i = 0;
        for (Writer w : this.writers) {
            if (w != null)
                try {
                    w.write(str);
                } catch (IOException e) {
                    causeList.add(new IOIndexedException(i, e));
                }  
            i++;
        } 
        if (!causeList.isEmpty())
            throw new IOExceptionList(causeList); 
    }
    
    public void write(String str, int off, int len) throws IOException {
        List<Exception> causeList = new ArrayList<>();
        int i = 0;
        for (Writer w : this.writers) {
            if (w != null)
                try {
                    w.write(str, off, len);
                } catch (IOException e) {
                    causeList.add(new IOIndexedException(i, e));
                }  
            i++;
        } 
        if (!causeList.isEmpty())
            throw new IOExceptionList(causeList); 
    }
}
