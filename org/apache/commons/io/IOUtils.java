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

package org.apache.commons.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.output.AppendableWriter;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.io.output.StringBuilderWriter;

public class IOUtils {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    
    public static final char DIR_SEPARATOR = File.separatorChar;
    
    public static final char DIR_SEPARATOR_UNIX = '/';
    
    public static final char DIR_SEPARATOR_WINDOWS = '\\';
    
    public static final int EOF = -1;
    
    @Deprecated
    public static final String LINE_SEPARATOR = System.lineSeparator();
    
    public static final String LINE_SEPARATOR_UNIX = "\n";
    
    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";
    
    private static final byte[] SKIP_BYTE_BUFFER = new byte[8192];
    
    private static char[] SKIP_CHAR_BUFFER;
    
    public static BufferedInputStream buffer(InputStream inputStream) {
        Objects.requireNonNull(inputStream, "inputStream");
        return (inputStream instanceof BufferedInputStream) ? (BufferedInputStream)inputStream : new BufferedInputStream(inputStream);
    }
    
    public static BufferedInputStream buffer(InputStream inputStream, int size) {
        Objects.requireNonNull(inputStream, "inputStream");
        return (inputStream instanceof BufferedInputStream) ? (BufferedInputStream)inputStream : new BufferedInputStream(inputStream, size);
    }
    
    public static BufferedOutputStream buffer(OutputStream outputStream) {
        Objects.requireNonNull(outputStream, "outputStream");
        return (outputStream instanceof BufferedOutputStream) ? (BufferedOutputStream)outputStream : new BufferedOutputStream(outputStream);
    }
    
    public static BufferedOutputStream buffer(OutputStream outputStream, int size) {
        Objects.requireNonNull(outputStream, "outputStream");
        return (outputStream instanceof BufferedOutputStream) ? (BufferedOutputStream)outputStream : new BufferedOutputStream(outputStream, size);
    }
    
    public static BufferedReader buffer(Reader reader) {
        return (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader);
    }
    
    public static BufferedReader buffer(Reader reader, int size) {
        return (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader, size);
    }
    
    public static BufferedWriter buffer(Writer writer) {
        return (writer instanceof BufferedWriter) ? (BufferedWriter)writer : new BufferedWriter(writer);
    }
    
    public static BufferedWriter buffer(Writer writer, int size) {
        return (writer instanceof BufferedWriter) ? (BufferedWriter)writer : new BufferedWriter(writer, size);
    }
    
    public static void close(Closeable closeable) throws IOException {
        if (closeable != null)
            closeable.close(); 
    }
    
    public static void close(Closeable... closeables) throws IOException {
        if (closeables != null)
            for (Closeable closeable : closeables)
                close(closeable);  
    }
    
    public static void close(Closeable closeable, IOConsumer<IOException> consumer) throws IOException {
        if (closeable != null)
            try {
                closeable.close();
            } catch (IOException e) {
                if (consumer != null)
                    consumer.accept(e); 
            }  
    }
    
    public static void close(URLConnection conn) {
        if (conn instanceof HttpURLConnection)
            ((HttpURLConnection)conn).disconnect(); 
    }
    
    @Deprecated
    public static void closeQuietly(Closeable closeable) {
        closeQuietly(closeable, (Consumer<IOException>)null);
    }
    
    @Deprecated
    public static void closeQuietly(Closeable... closeables) {
        if (closeables == null)
            return; 
        for (Closeable closeable : closeables)
            closeQuietly(closeable); 
    }
    
    public static void closeQuietly(Closeable closeable, Consumer<IOException> consumer) {
        if (closeable != null)
            try {
                closeable.close();
            } catch (IOException e) {
                if (consumer != null)
                    consumer.accept(e); 
            }  
    }
    
    @Deprecated
    public static void closeQuietly(InputStream input) {
        closeQuietly(input);
    }
    
    @Deprecated
    public static void closeQuietly(OutputStream output) {
        closeQuietly(output);
    }
    
    @Deprecated
    public static void closeQuietly(Reader input) {
        closeQuietly(input);
    }
    
    @Deprecated
    public static void closeQuietly(Selector selector) {
        closeQuietly(selector);
    }
    
    @Deprecated
    public static void closeQuietly(ServerSocket serverSocket) {
        closeQuietly(serverSocket);
    }
    
    @Deprecated
    public static void closeQuietly(Socket socket) {
        closeQuietly(socket);
    }
    
    @Deprecated
    public static void closeQuietly(Writer output) {
        closeQuietly(output);
    }
    
    public static long consume(InputStream input) throws IOException {
        return copyLarge(input, (OutputStream)NullOutputStream.NULL_OUTPUT_STREAM, SKIP_BYTE_BUFFER);
    }
    
    public static boolean contentEquals(InputStream input1, InputStream input2) throws IOException {
        if (input1 == input2)
            return true; 
        if ((((input1 == null) ? 1 : 0) ^ ((input2 == null) ? 1 : 0)) != 0)
            return false; 
        BufferedInputStream bufferedInput1 = buffer(input1);
        BufferedInputStream bufferedInput2 = buffer(input2);
        int ch = bufferedInput1.read();
        while (-1 != ch) {
            int ch2 = bufferedInput2.read();
            if (ch != ch2)
                return false; 
            ch = bufferedInput1.read();
        } 
        return (bufferedInput2.read() == -1);
    }
    
    public static boolean contentEquals(Reader input1, Reader input2) throws IOException {
        if (input1 == input2)
            return true; 
        if ((((input1 == null) ? 1 : 0) ^ ((input2 == null) ? 1 : 0)) != 0)
            return false; 
        BufferedReader bufferedInput1 = toBufferedReader(input1);
        BufferedReader bufferedInput2 = toBufferedReader(input2);
        int ch = bufferedInput1.read();
        while (-1 != ch) {
            int ch2 = bufferedInput2.read();
            if (ch != ch2)
                return false; 
            ch = bufferedInput1.read();
        } 
        return (bufferedInput2.read() == -1);
    }
    
    public static boolean contentEqualsIgnoreEOL(Reader input1, Reader input2) throws IOException {
        if (input1 == input2)
            return true; 
        if ((((input1 == null) ? 1 : 0) ^ ((input2 == null) ? 1 : 0)) != 0)
            return false; 
        BufferedReader br1 = toBufferedReader(input1);
        BufferedReader br2 = toBufferedReader(input2);
        String line1 = br1.readLine();
        String line2 = br2.readLine();
        while (line1 != null && line1.equals(line2)) {
            line1 = br1.readLine();
            line2 = br2.readLine();
        } 
        return Objects.equals(line1, line2);
    }
    
    public static int copy(InputStream input, OutputStream output) throws IOException {
        long count = copyLarge(input, output);
        if (count > 2147483647L)
            return -1; 
        return (int)count;
    }
    
    public static long copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
        return copyLarge(input, output, new byte[bufferSize]);
    }
    
    @Deprecated
    public static void copy(InputStream input, Writer output) throws IOException {
        copy(input, output, Charset.defaultCharset());
    }
    
    public static void copy(InputStream input, Writer output, Charset inputCharset) throws IOException {
        InputStreamReader in = new InputStreamReader(input, Charsets.toCharset(inputCharset));
        copy(in, output);
    }
    
    public static void copy(InputStream input, Writer output, String inputCharsetName) throws IOException {
        copy(input, output, Charsets.toCharset(inputCharsetName));
    }
    
    public static long copy(Reader input, Appendable output) throws IOException {
        return copy(input, output, CharBuffer.allocate(8192));
    }
    
    public static long copy(Reader input, Appendable output, CharBuffer buffer) throws IOException {
        long count = 0L;
        int n;
        while (-1 != (n = input.read(buffer))) {
            buffer.flip();
            output.append(buffer, 0, n);
            count += n;
        } 
        return count;
    }
    
    @Deprecated
    public static void copy(Reader input, OutputStream output) throws IOException {
        copy(input, output, Charset.defaultCharset());
    }
    
    public static void copy(Reader input, OutputStream output, Charset outputCharset) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(output, Charsets.toCharset(outputCharset));
        copy(input, out);
        out.flush();
    }
    
    public static void copy(Reader input, OutputStream output, String outputCharsetName) throws IOException {
        copy(input, output, Charsets.toCharset(outputCharsetName));
    }
    
    public static int copy(Reader input, Writer output) throws IOException {
        long count = copyLarge(input, output);
        if (count > 2147483647L)
            return -1; 
        return (int)count;
    }
    
    public static long copyLarge(InputStream input, OutputStream output) throws IOException {
        return copy(input, output, 8192);
    }
    
    public static long copyLarge(InputStream input, OutputStream output, byte[] buffer) throws IOException {
        long count = 0L;
        if (input != null) {
            int n;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
                count += n;
            } 
        } 
        return count;
    }
    
    public static long copyLarge(InputStream input, OutputStream output, long inputOffset, long length) throws IOException {
        return copyLarge(input, output, inputOffset, length, new byte[8192]);
    }
    
    public static long copyLarge(InputStream input, OutputStream output, long inputOffset, long length, byte[] buffer) throws IOException {
        if (inputOffset > 0L)
            skipFully(input, inputOffset); 
        if (length == 0L)
            return 0L; 
        int bufferLength = buffer.length;
        int bytesToRead = bufferLength;
        if (length > 0L && length < bufferLength)
            bytesToRead = (int)length; 
        long totalRead = 0L;
        int read;
        while (bytesToRead > 0 && -1 != (read = input.read(buffer, 0, bytesToRead))) {
            output.write(buffer, 0, read);
            totalRead += read;
            if (length > 0L)
                bytesToRead = (int)Math.min(length - totalRead, bufferLength); 
        } 
        return totalRead;
    }
    
    public static long copyLarge(Reader input, Writer output) throws IOException {
        return copyLarge(input, output, new char[8192]);
    }
    
    public static long copyLarge(Reader input, Writer output, char[] buffer) throws IOException {
        long count = 0L;
        int n;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        } 
        return count;
    }
    
    public static long copyLarge(Reader input, Writer output, long inputOffset, long length) throws IOException {
        return copyLarge(input, output, inputOffset, length, new char[8192]);
    }
    
    public static long copyLarge(Reader input, Writer output, long inputOffset, long length, char[] buffer) throws IOException {
        if (inputOffset > 0L)
            skipFully(input, inputOffset); 
        if (length == 0L)
            return 0L; 
        int bytesToRead = buffer.length;
        if (length > 0L && length < buffer.length)
            bytesToRead = (int)length; 
        long totalRead = 0L;
        int read;
        while (bytesToRead > 0 && -1 != (read = input.read(buffer, 0, bytesToRead))) {
            output.write(buffer, 0, read);
            totalRead += read;
            if (length > 0L)
                bytesToRead = (int)Math.min(length - totalRead, buffer.length); 
        } 
        return totalRead;
    }
    
    public static int length(byte[] array) {
        return (array == null) ? 0 : array.length;
    }
    
    public static int length(char[] array) {
        return (array == null) ? 0 : array.length;
    }
    
    public static int length(CharSequence csq) {
        return (csq == null) ? 0 : csq.length();
    }
    
    public static int length(Object[] array) {
        return (array == null) ? 0 : array.length;
    }
    
    public static LineIterator lineIterator(InputStream input, Charset charset) throws IOException {
        return new LineIterator(new InputStreamReader(input, Charsets.toCharset(charset)));
    }
    
    public static LineIterator lineIterator(InputStream input, String charsetName) throws IOException {
        return lineIterator(input, Charsets.toCharset(charsetName));
    }
    
    public static LineIterator lineIterator(Reader reader) {
        return new LineIterator(reader);
    }
    
    public static int read(InputStream input, byte[] buffer) throws IOException {
        return read(input, buffer, 0, buffer.length);
    }
    
    public static int read(InputStream input, byte[] buffer, int offset, int length) throws IOException {
        if (length < 0)
            throw new IllegalArgumentException("Length must not be negative: " + length); 
        int remaining = length;
        while (remaining > 0) {
            int location = length - remaining;
            int count = input.read(buffer, offset + location, remaining);
            if (-1 == count)
                break; 
            remaining -= count;
        } 
        return length - remaining;
    }
    
    public static int read(ReadableByteChannel input, ByteBuffer buffer) throws IOException {
        int length = buffer.remaining();
        while (buffer.remaining() > 0) {
            int count = input.read(buffer);
            if (-1 == count)
                break; 
        } 
        return length - buffer.remaining();
    }
    
    public static int read(Reader input, char[] buffer) throws IOException {
        return read(input, buffer, 0, buffer.length);
    }
    
    public static int read(Reader input, char[] buffer, int offset, int length) throws IOException {
        if (length < 0)
            throw new IllegalArgumentException("Length must not be negative: " + length); 
        int remaining = length;
        while (remaining > 0) {
            int location = length - remaining;
            int count = input.read(buffer, offset + location, remaining);
            if (-1 == count)
                break; 
            remaining -= count;
        } 
        return length - remaining;
    }
    
    public static void readFully(InputStream input, byte[] buffer) throws IOException {
        readFully(input, buffer, 0, buffer.length);
    }
    
    public static void readFully(InputStream input, byte[] buffer, int offset, int length) throws IOException {
        int actual = read(input, buffer, offset, length);
        if (actual != length)
            throw new EOFException("Length to read: " + length + " actual: " + actual); 
    }
    
    public static byte[] readFully(InputStream input, int length) throws IOException {
        byte[] buffer = new byte[length];
        readFully(input, buffer, 0, buffer.length);
        return buffer;
    }
    
    public static void readFully(ReadableByteChannel input, ByteBuffer buffer) throws IOException {
        int expected = buffer.remaining();
        int actual = read(input, buffer);
        if (actual != expected)
            throw new EOFException("Length to read: " + expected + " actual: " + actual); 
    }
    
    public static void readFully(Reader input, char[] buffer) throws IOException {
        readFully(input, buffer, 0, buffer.length);
    }
    
    public static void readFully(Reader input, char[] buffer, int offset, int length) throws IOException {
        int actual = read(input, buffer, offset, length);
        if (actual != length)
            throw new EOFException("Length to read: " + length + " actual: " + actual); 
    }
    
    @Deprecated
    public static List<String> readLines(InputStream input) throws IOException {
        return readLines(input, Charset.defaultCharset());
    }
    
    public static List<String> readLines(InputStream input, Charset charset) throws IOException {
        InputStreamReader reader = new InputStreamReader(input, Charsets.toCharset(charset));
        return readLines(reader);
    }
    
    public static List<String> readLines(InputStream input, String charsetName) throws IOException {
        return readLines(input, Charsets.toCharset(charsetName));
    }
    
    public static List<String> readLines(Reader input) throws IOException {
        BufferedReader reader = toBufferedReader(input);
        List<String> list = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null)
            list.add(line); 
        return list;
    }
    
    public static byte[] resourceToByteArray(String name) throws IOException {
        return resourceToByteArray(name, null);
    }
    
    public static byte[] resourceToByteArray(String name, ClassLoader classLoader) throws IOException {
        return toByteArray(resourceToURL(name, classLoader));
    }
    
    public static String resourceToString(String name, Charset charset) throws IOException {
        return resourceToString(name, charset, null);
    }
    
    public static String resourceToString(String name, Charset charset, ClassLoader classLoader) throws IOException {
        return toString(resourceToURL(name, classLoader), charset);
    }
    
    public static URL resourceToURL(String name) throws IOException {
        return resourceToURL(name, null);
    }
    
    public static URL resourceToURL(String name, ClassLoader classLoader) throws IOException {
        URL resource = (classLoader == null) ? IOUtils.class.getResource(name) : classLoader.getResource(name);
        if (resource == null)
            throw new IOException("Resource not found: " + name); 
        return resource;
    }
    
    public static long skip(InputStream input, long toSkip) throws IOException {
        if (toSkip < 0L)
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip); 
        long remain = toSkip;
        while (remain > 0L) {
            long n = input.read(SKIP_BYTE_BUFFER, 0, (int)Math.min(remain, SKIP_BYTE_BUFFER.length));
            if (n < 0L)
                break; 
            remain -= n;
        } 
        return toSkip - remain;
    }
    
    public static long skip(ReadableByteChannel input, long toSkip) throws IOException {
        if (toSkip < 0L)
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip); 
        ByteBuffer skipByteBuffer = ByteBuffer.allocate((int)Math.min(toSkip, SKIP_BYTE_BUFFER.length));
        long remain = toSkip;
        while (remain > 0L) {
            skipByteBuffer.position(0);
            skipByteBuffer.limit((int)Math.min(remain, SKIP_BYTE_BUFFER.length));
            int n = input.read(skipByteBuffer);
            if (n == -1)
                break; 
            remain -= n;
        } 
        return toSkip - remain;
    }
    
    public static long skip(Reader input, long toSkip) throws IOException {
        if (toSkip < 0L)
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip); 
        if (SKIP_CHAR_BUFFER == null)
            SKIP_CHAR_BUFFER = new char[SKIP_BYTE_BUFFER.length]; 
        long remain = toSkip;
        while (remain > 0L) {
            long n = input.read(SKIP_CHAR_BUFFER, 0, (int)Math.min(remain, SKIP_BYTE_BUFFER.length));
            if (n < 0L)
                break; 
            remain -= n;
        } 
        return toSkip - remain;
    }
    
    public static void skipFully(InputStream input, long toSkip) throws IOException {
        if (toSkip < 0L)
            throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip); 
        long skipped = skip(input, toSkip);
        if (skipped != toSkip)
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped); 
    }
    
    public static void skipFully(ReadableByteChannel input, long toSkip) throws IOException {
        if (toSkip < 0L)
            throw new IllegalArgumentException("Bytes to skip must not be negative: " + toSkip); 
        long skipped = skip(input, toSkip);
        if (skipped != toSkip)
            throw new EOFException("Bytes to skip: " + toSkip + " actual: " + skipped); 
    }
    
    public static void skipFully(Reader input, long toSkip) throws IOException {
        long skipped = skip(input, toSkip);
        if (skipped != toSkip)
            throw new EOFException("Chars to skip: " + toSkip + " actual: " + skipped); 
    }
    
    public static InputStream toBufferedInputStream(InputStream input) throws IOException {
        return ByteArrayOutputStream.toBufferedInputStream(input);
    }
    
    public static InputStream toBufferedInputStream(InputStream input, int size) throws IOException {
        return ByteArrayOutputStream.toBufferedInputStream(input, size);
    }
    
    public static BufferedReader toBufferedReader(Reader reader) {
        return (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader);
    }
    
    public static BufferedReader toBufferedReader(Reader reader, int size) {
        return (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader, size);
    }
    
    public static byte[] toByteArray(InputStream input) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            copy(input, (OutputStream)output);
            return output.toByteArray();
        } 
    }
    
    public static byte[] toByteArray(InputStream input, int size) throws IOException {
        if (size < 0)
            throw new IllegalArgumentException("Size must be equal or greater than zero: " + size); 
        if (size == 0)
            return EMPTY_BYTE_ARRAY; 
        byte[] data = new byte[size];
        int offset = 0;
        int read;
        while (offset < size && (read = input.read(data, offset, size - offset)) != -1)
            offset += read; 
        if (offset != size)
            throw new IOException("Unexpected read size. current: " + offset + ", expected: " + size); 
        return data;
    }
    
    public static byte[] toByteArray(InputStream input, long size) throws IOException {
        if (size > 2147483647L)
            throw new IllegalArgumentException("Size cannot be greater than Integer max value: " + size); 
        return toByteArray(input, (int)size);
    }
    
    @Deprecated
    public static byte[] toByteArray(Reader input) throws IOException {
        return toByteArray(input, Charset.defaultCharset());
    }
    
    public static byte[] toByteArray(Reader input, Charset charset) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            copy(input, (OutputStream)output, charset);
            return output.toByteArray();
        } 
    }
    
    public static byte[] toByteArray(Reader input, String charsetName) throws IOException {
        return toByteArray(input, Charsets.toCharset(charsetName));
    }
    
    @Deprecated
    public static byte[] toByteArray(String input) throws IOException {
        return input.getBytes(Charset.defaultCharset());
    }
    
    public static byte[] toByteArray(URI uri) throws IOException {
        return toByteArray(uri.toURL());
    }
    
    public static byte[] toByteArray(URL url) throws IOException {
        URLConnection conn = url.openConnection();
        try {
            return toByteArray(conn);
        } finally {
            close(conn);
        } 
    }
    
    public static byte[] toByteArray(URLConnection urlConn) throws IOException {
        try (InputStream inputStream = urlConn.getInputStream()) {
            return toByteArray(inputStream);
        } 
    }
    
    @Deprecated
    public static char[] toCharArray(InputStream is) throws IOException {
        return toCharArray(is, Charset.defaultCharset());
    }
    
    public static char[] toCharArray(InputStream is, Charset charset) throws IOException {
        CharArrayWriter output = new CharArrayWriter();
        copy(is, output, charset);
        return output.toCharArray();
    }
    
    public static char[] toCharArray(InputStream is, String charsetName) throws IOException {
        return toCharArray(is, Charsets.toCharset(charsetName));
    }
    
    public static char[] toCharArray(Reader input) throws IOException {
        CharArrayWriter sw = new CharArrayWriter();
        copy(input, sw);
        return sw.toCharArray();
    }
    
    @Deprecated
    public static InputStream toInputStream(CharSequence input) {
        return toInputStream(input, Charset.defaultCharset());
    }
    
    public static InputStream toInputStream(CharSequence input, Charset charset) {
        return toInputStream(input.toString(), charset);
    }
    
    public static InputStream toInputStream(CharSequence input, String charsetName) throws IOException {
        return toInputStream(input, Charsets.toCharset(charsetName));
    }
    
    @Deprecated
    public static InputStream toInputStream(String input) {
        return toInputStream(input, Charset.defaultCharset());
    }
    
    public static InputStream toInputStream(String input, Charset charset) {
        return new ByteArrayInputStream(input.getBytes(Charsets.toCharset(charset)));
    }
    
    public static InputStream toInputStream(String input, String charsetName) throws IOException {
        byte[] bytes = input.getBytes(Charsets.toCharset(charsetName));
        return new ByteArrayInputStream(bytes);
    }
    
    @Deprecated
    public static String toString(byte[] input) throws IOException {
        return new String(input, Charset.defaultCharset());
    }
    
    public static String toString(byte[] input, String charsetName) throws IOException {
        return new String(input, Charsets.toCharset(charsetName));
    }
    
    @Deprecated
    public static String toString(InputStream input) throws IOException {
        return toString(input, Charset.defaultCharset());
    }
    
    public static String toString(InputStream input, Charset charset) throws IOException {
        try (StringBuilderWriter sw = new StringBuilderWriter()) {
            copy(input, (Writer)sw, charset);
            return sw.toString();
        } 
    }
    
    public static String toString(InputStream input, String charsetName) throws IOException {
        return toString(input, Charsets.toCharset(charsetName));
    }
    
    public static String toString(Reader input) throws IOException {
        try (StringBuilderWriter sw = new StringBuilderWriter()) {
            copy(input, (Writer)sw);
            return sw.toString();
        } 
    }
    
    @Deprecated
    public static String toString(URI uri) throws IOException {
        return toString(uri, Charset.defaultCharset());
    }
    
    public static String toString(URI uri, Charset encoding) throws IOException {
        return toString(uri.toURL(), Charsets.toCharset(encoding));
    }
    
    public static String toString(URI uri, String charsetName) throws IOException {
        return toString(uri, Charsets.toCharset(charsetName));
    }
    
    @Deprecated
    public static String toString(URL url) throws IOException {
        return toString(url, Charset.defaultCharset());
    }
    
    public static String toString(URL url, Charset encoding) throws IOException {
        try (InputStream inputStream = url.openStream()) {
            return toString(inputStream, encoding);
        } 
    }
    
    public static String toString(URL url, String charsetName) throws IOException {
        return toString(url, Charsets.toCharset(charsetName));
    }
    
    public static void write(byte[] data, OutputStream output) throws IOException {
        if (data != null)
            output.write(data); 
    }
    
    @Deprecated
    public static void write(byte[] data, Writer output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }
    
    public static void write(byte[] data, Writer output, Charset charset) throws IOException {
        if (data != null)
            output.write(new String(data, Charsets.toCharset(charset))); 
    }
    
    public static void write(byte[] data, Writer output, String charsetName) throws IOException {
        write(data, output, Charsets.toCharset(charsetName));
    }
    
    @Deprecated
    public static void write(char[] data, OutputStream output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }
    
    public static void write(char[] data, OutputStream output, Charset charset) throws IOException {
        if (data != null)
            output.write((new String(data)).getBytes(Charsets.toCharset(charset))); 
    }
    
    public static void write(char[] data, OutputStream output, String charsetName) throws IOException {
        write(data, output, Charsets.toCharset(charsetName));
    }
    
    public static void write(char[] data, Writer output) throws IOException {
        if (data != null)
            output.write(data); 
    }
    
    @Deprecated
    public static void write(CharSequence data, OutputStream output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }
    
    public static void write(CharSequence data, OutputStream output, Charset charset) throws IOException {
        if (data != null)
            write(data.toString(), output, charset); 
    }
    
    public static void write(CharSequence data, OutputStream output, String charsetName) throws IOException {
        write(data, output, Charsets.toCharset(charsetName));
    }
    
    public static void write(CharSequence data, Writer output) throws IOException {
        if (data != null)
            write(data.toString(), output); 
    }
    
    @Deprecated
    public static void write(String data, OutputStream output) throws IOException {
        write(data, output, Charset.defaultCharset());
    }
    
    public static void write(String data, OutputStream output, Charset charset) throws IOException {
        if (data != null)
            output.write(data.getBytes(Charsets.toCharset(charset))); 
    }
    
    public static void write(String data, OutputStream output, String charsetName) throws IOException {
        write(data, output, Charsets.toCharset(charsetName));
    }
    
    public static void write(String data, Writer output) throws IOException {
        if (data != null)
            output.write(data); 
    }
    
    @Deprecated
    public static void write(StringBuffer data, OutputStream output) throws IOException {
        write(data, output, (String)null);
    }
    
    @Deprecated
    public static void write(StringBuffer data, OutputStream output, String charsetName) throws IOException {
        if (data != null)
            output.write(data.toString().getBytes(Charsets.toCharset(charsetName))); 
    }
    
    @Deprecated
    public static void write(StringBuffer data, Writer output) throws IOException {
        if (data != null)
            output.write(data.toString()); 
    }
    
    public static void writeChunked(byte[] data, OutputStream output) throws IOException {
        if (data != null) {
            int bytes = data.length;
            int offset = 0;
            while (bytes > 0) {
                int chunk = Math.min(bytes, 8192);
                output.write(data, offset, chunk);
                bytes -= chunk;
                offset += chunk;
            } 
        } 
    }
    
    public static void writeChunked(char[] data, Writer output) throws IOException {
        if (data != null) {
            int bytes = data.length;
            int offset = 0;
            while (bytes > 0) {
                int chunk = Math.min(bytes, 8192);
                output.write(data, offset, chunk);
                bytes -= chunk;
                offset += chunk;
            } 
        } 
    }
    
    @Deprecated
    public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output) throws IOException {
        writeLines(lines, lineEnding, output, Charset.defaultCharset());
    }
    
    public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output, Charset charset) throws IOException {
        if (lines == null)
            return; 
        if (lineEnding == null)
            lineEnding = System.lineSeparator(); 
        Charset cs = Charsets.toCharset(charset);
        for (Object line : lines) {
            if (line != null)
                output.write(line.toString().getBytes(cs)); 
            output.write(lineEnding.getBytes(cs));
        } 
    }
    
    public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output, String charsetName) throws IOException {
        writeLines(lines, lineEnding, output, Charsets.toCharset(charsetName));
    }
    
    public static void writeLines(Collection<?> lines, String lineEnding, Writer writer) throws IOException {
        if (lines == null)
            return; 
        if (lineEnding == null)
            lineEnding = System.lineSeparator(); 
        for (Object line : lines) {
            if (line != null)
                writer.write(line.toString()); 
            writer.write(lineEnding);
        } 
    }
    
    public static Writer writer(Appendable appendable) {
        Objects.requireNonNull(appendable, "appendable");
        if (appendable instanceof Writer)
            return (Writer)appendable; 
        if (appendable instanceof StringBuilder)
            return (Writer)new StringBuilderWriter((StringBuilder)appendable); 
        return (Writer)new AppendableWriter(appendable);
    }
}
