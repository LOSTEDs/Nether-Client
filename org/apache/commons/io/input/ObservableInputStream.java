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

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ObservableInputStream extends ProxyInputStream {
    public static abstract class Observer {
        public void data(int pByte) throws IOException {}
        
        public void data(byte[] pBuffer, int pOffset, int pLength) throws IOException {}
        
        public void finished() throws IOException {}
        
        public void closed() throws IOException {}
        
        public void error(IOException pException) throws IOException {
            throw pException;
        }
    }
    
    private final List<Observer> observers = new ArrayList<>();
    
    public ObservableInputStream(InputStream pProxy) {
        super(pProxy);
    }
    
    public void add(Observer pObserver) {
        this.observers.add(pObserver);
    }
    
    public void remove(Observer pObserver) {
        this.observers.remove(pObserver);
    }
    
    public void removeAllObservers() {
        this.observers.clear();
    }
    
    public int read() throws IOException {
        int result = 0;
        IOException ioe = null;
        try {
            result = super.read();
        } catch (IOException pException) {
            ioe = pException;
        } 
        if (ioe != null) {
            noteError(ioe);
        } else if (result == -1) {
            noteFinished();
        } else {
            noteDataByte(result);
        } 
        return result;
    }
    
    public int read(byte[] pBuffer) throws IOException {
        int result = 0;
        IOException ioe = null;
        try {
            result = super.read(pBuffer);
        } catch (IOException pException) {
            ioe = pException;
        } 
        if (ioe != null) {
            noteError(ioe);
        } else if (result == -1) {
            noteFinished();
        } else if (result > 0) {
            noteDataBytes(pBuffer, 0, result);
        } 
        return result;
    }
    
    public int read(byte[] pBuffer, int pOffset, int pLength) throws IOException {
        int result = 0;
        IOException ioe = null;
        try {
            result = super.read(pBuffer, pOffset, pLength);
        } catch (IOException pException) {
            ioe = pException;
        } 
        if (ioe != null) {
            noteError(ioe);
        } else if (result == -1) {
            noteFinished();
        } else if (result > 0) {
            noteDataBytes(pBuffer, pOffset, result);
        } 
        return result;
    }
    
    protected void noteDataBytes(byte[] pBuffer, int pOffset, int pLength) throws IOException {
        for (Observer observer : getObservers())
            observer.data(pBuffer, pOffset, pLength); 
    }
    
    protected void noteFinished() throws IOException {
        for (Observer observer : getObservers())
            observer.finished(); 
    }
    
    protected void noteDataByte(int pDataByte) throws IOException {
        for (Observer observer : getObservers())
            observer.data(pDataByte); 
    }
    
    protected void noteError(IOException pException) throws IOException {
        for (Observer observer : getObservers())
            observer.error(pException); 
    }
    
    protected void noteClosed() throws IOException {
        for (Observer observer : getObservers())
            observer.closed(); 
    }
    
    protected List<Observer> getObservers() {
        return this.observers;
    }
    
    public void close() throws IOException {
        IOException ioe = null;
        try {
            super.close();
        } catch (IOException e) {
            ioe = e;
        } 
        if (ioe == null) {
            noteClosed();
        } else {
            noteError(ioe);
        } 
    }
    
    public void consume() throws IOException {
        int res;
        byte[] buffer = new byte[8192];
        do {
            res = read(buffer);
        } while (res != -1);
    }
}
