package io;

import java.io.IOException;
import java.io.Reader;

public class ReusableBufferedReader extends Reader {

    private char[] buffer = null;
    private int writeIndex = 0;
    private int readIndex = 0;
    private boolean endOfReaderReached = false;
    private boolean skipLF = false;

    private static final int INVALIDATED = -2;
    private static final int UNMARKED = -1;
    private int markedChar = UNMARKED;
    private int readAheadLimit = 0; /* Valid only when markedChar > 0 */

    private Reader source = null;
    private int nChars, nextChar;


    public ReusableBufferedReader(char[] buffer) {
        this.buffer = buffer;
    }

    public ReusableBufferedReader setSource(Reader source) {
        this.source = source;
        this.writeIndex = 0;
        this.readIndex = 0;
        this.endOfReaderReached = false;
        nextChar = nChars = 0;
        return this;
    }

    @Override
    public int read() throws IOException {
        if (endOfReaderReached) {
            return -1;
        }

        if (readIndex == writeIndex) {
            if (writeIndex == buffer.length) {
                this.writeIndex = 0;
                this.readIndex = 0;
            }
            //data should be read into buffer.
            int bytesRead = readCharsIntoBuffer();
            while (bytesRead == 0) {
                //continue until you actually get some bytes !
                bytesRead = readCharsIntoBuffer();
            }

            //if no more data could be read in, return -1;
            if (bytesRead == -1) {
                return -1;
            }
        }

        return 65535 & this.buffer[readIndex++];
    }

    @Override
    public int read(char[] dest, int offset, int length) throws IOException {
        int charsRead = 0;
        int data = 0;
        while (data != -1 && charsRead < length) {
            data = read();
            if (data == -1) {
                endOfReaderReached = true;
                if (charsRead == 0) {
                    return -1;
                }
                return charsRead;
            }
            dest[offset + charsRead] = (char) (65535 & data);
            charsRead++;
        }
        return charsRead;
    }

    @Override
    public void close() throws IOException {
        this.source.close();
    }


    private int readCharsIntoBuffer() throws IOException {
        int charsRead = this.source.read(this.buffer, this.writeIndex, this.buffer.length - this.writeIndex);
        writeIndex += charsRead;
        return charsRead;
    }

    private void ensureOpen() throws IOException {
        if (source == null)
            throw new IOException("Stream closed");
    }

    private void fill() throws IOException {
        int dst;
        if (markedChar <= UNMARKED) {
            /* No mark */
            dst = 0;
        } else {
            /* Marked */
            int delta = nextChar - markedChar;
            if (delta >= readAheadLimit) {
                /* Gone past read-ahead limit: Invalidate mark */
                markedChar = INVALIDATED;
                readAheadLimit = 0;
                dst = 0;
            } else {
                if (readAheadLimit <= buffer.length) {
                    /* Shuffle in the current buffer */
                    System.arraycopy(buffer, markedChar, buffer, 0, delta);
                    markedChar = 0;
                    dst = delta;
                } else {
                    /* Reallocate buffer to accommodate read-ahead limit */
                    char[] ncb = new char[readAheadLimit];
                    System.arraycopy(buffer, markedChar, ncb, 0, delta);
                    buffer = ncb;
                    markedChar = 0;
                    dst = delta;
                }
                nextChar = nChars = delta;
            }
        }
        int n;
        do {
            n = source.read(buffer, dst, buffer.length - dst);
        } while (n == 0);
        if (n > 0) {
            nChars = dst + n;
            nextChar = dst;
        }
    }

    String readLine(boolean ignoreLF) throws IOException {
        StringBuffer s = null;
        int startChar;

        synchronized (lock) {
            ensureOpen();
            boolean omitLF = ignoreLF || skipLF;

            bufferLoop:
            for (; ; ) {

                if (nextChar >= nChars)
                    fill();
                if (nextChar >= nChars) { /* EOF */
                    if (s != null && s.length() > 0)
                        return s.toString();
                    else
                        return null;
                }
                boolean eol = false;
                char c = 0;
                int i;

                /* Skip a leftover '\n', if necessary */
                if (omitLF && (buffer[nextChar] == '\n'))
                    nextChar++;
                skipLF = false;
                omitLF = false;

                charLoop:
                for (i = nextChar; i < nChars; i++) {
                    c = buffer[i];
                    if ((c == '\n') || (c == '\r')) {
                        eol = true;
                        break charLoop;
                    }
                }

                startChar = nextChar;
                nextChar = i;

                if (eol) {
                    String str;
                    if (s == null) {
                        str = new String(buffer, startChar, i - startChar);
                    } else {
                        s.append(buffer, startChar, i - startChar);
                        str = s.toString();
                    }
                    nextChar++;
                    if (c == '\r') {
                        skipLF = true;
                    }
                    return str;
                }

                int defaultExpectedLineLength = 80;
                if (s == null)
                    s = new StringBuffer(defaultExpectedLineLength);
                s.append(buffer, startChar, i - startChar);
            }
        }
    }

    public String readLine() throws IOException {
        return readLine(false);
    }
}