package online.mwang.buffer;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @author mwangli
 * @date 2022/7/19 11:45
 */
public class ByteBufferSplitTest {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        byteBuffer.put("hello word\nI am fine\nHo".getBytes(StandardCharsets.UTF_8));
        split(byteBuffer);
        byteBuffer.put("w are you\n".getBytes(StandardCharsets.UTF_8));
        split(byteBuffer);
    }

    private static void split(ByteBuffer byteBuffer) {
        byteBuffer.flip();
        for (int i = 0; i < byteBuffer.limit(); i++) {
            byte b = byteBuffer.get(i);
            if (byteBuffer.get(i) == '\n') {
                int l = i + 1 - byteBuffer.position();
                ByteBuffer target = ByteBuffer.allocate(l);
                for (int j = 0; j < l; j++) {
                    target.put(byteBuffer.get());
                }
                target.flip();
                System.out.print(StandardCharsets.UTF_8.decode(target));
            }
        }
        byteBuffer.compact();
    }
}
