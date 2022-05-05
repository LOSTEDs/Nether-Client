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

package org.newdawn.slick.openal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class OggDecoder {
    private int convsize = 16384;
    
    private byte[] convbuffer = new byte[this.convsize];
    
    public OggData getData(InputStream input) throws IOException {
        if (input == null)
            throw new IOException("Failed to read OGG, source does not exist?"); 
        ByteArrayOutputStream dataout = new ByteArrayOutputStream();
        OggInputStream oggInput = new OggInputStream(input);
        boolean done = false;
        while (!oggInput.atEnd())
            dataout.write(oggInput.read()); 
        OggData ogg = new OggData();
        ogg.channels = oggInput.getChannels();
        ogg.rate = oggInput.getRate();
        byte[] data = dataout.toByteArray();
        ogg.data = ByteBuffer.allocateDirect(data.length);
        ogg.data.put(data);
        ogg.data.rewind();
        return ogg;
    }
}
