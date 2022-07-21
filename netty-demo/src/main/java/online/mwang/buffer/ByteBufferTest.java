package online.mwang.buffer;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author mwangli
 * @date 2022/7/18 15:54
 */
@Slf4j
public class ByteBufferTest {

    public static void main(String[] args) {
        try (FileChannel fileChannel = new FileInputStream("E:\\IdeaProjects\\study-demo\\netty-demo\\src\\main\\resources\\data.txt").getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(4);
            int l;
            while ((l = fileChannel.read(byteBuffer)) != -1) {
                log.info("读取到字节长度:{}", l);
                // 切换至读模式，移动position、limit的索引位置
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    byte b = byteBuffer.get();
                    System.out.println((char) b);
                }
                // 切换至写模式，compact会将未读取的数据保留
                byteBuffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
