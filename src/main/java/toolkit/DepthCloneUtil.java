package toolkit;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhull
 * @date 2019/1/7
 * <P>深度克隆工具</P>
 */
public class DepthCloneUtil {

    /**
     * <P>date: 2019/1/7
     * 用Protostuff深复制
     **/
    public static <T> T deepClone(T obj) {
        return obj == null ? null : deserializer(serializer(obj), (Class<T>) obj.getClass());
    }

    /**
     * <P>date: 2019/1/7
     * 用字节流深复制
     **/
    public static <T extends Serializable> T deepClone1(T obj) throws IOException, ClassNotFoundException {
        try(ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo)) {
            //将对象写到流里
            oo.writeObject(obj);

            //从流里读出来
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            return (T) oi.readObject();
        }
    }

    /**
     * <P>date: 2019/1/7
     * 序列化
     *
     * @param obj 待序列化对象
     * @param <T> 待序列化对象类型
     * @return
     */
    private static <T> byte[] serializer(T obj) {
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return ProtostuffIOUtil.toByteArray(obj, RuntimeSchema.createFrom((Class<T>) obj.getClass()), buffer);
        } finally {
            buffer.clear();
        }
    }

    /**
     * <P>date: 2019/1/7
     * 反序列化
     *
     * @param data  序列化产生的字节数组
     * @param clazz 对象的类对象
     * @return
     */
    private static <T> T deserializer(byte[] data, Class<T> clazz) {
        Schema<T> schema = RuntimeSchema.createFrom(clazz);
        T obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data, obj, schema);
        return obj;
    }



    public static void main(String[] args) {
        HashMap<String, Student> map = new HashMap<>();
        HashMap<String, Student> map2 = map;

        Map<String, Student> map1 = deepClone(map);
        System.out.println(map == map2);
        System.out.println(map.equals(map1));
        System.out.println(map == map1);

    }

    private static class Student {
        private String name;
    }
}
