package com.ztx.world.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.shiro.io.SerializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerializeUtil {

	private static Logger log = LoggerFactory.getLogger(SerializeUtil.class);

	public static boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}

	/**
	 * 序列化
	 * @param object
	 * @return
	 * @throws SerializationException
	 */
	public static byte[] serialize(Object object) throws SerializationException {
		byte[] result = null;

		if (object == null) {
			return new byte[0];
		}
		try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream)) {

			if (!(object instanceof Serializable)) {
				throw new IllegalArgumentException(
						SerializeUtil.class.getSimpleName() + " requires a Serializable payload "
								+ "but received an object of type [" + object.getClass().getName() + "]");
			}

			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			result = byteStream.toByteArray();
		} catch (Exception ex) {
			log.error("Failed to serialize.", ex);
		}
		return result;
	}

	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 * @throws SerializationException
	 */
	public static Object deserialize(byte[] bytes) throws SerializationException {

		Object result = null;

		if (isEmpty(bytes)) {
			return null;
		}

		try (ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
				ObjectInputStream objectInputStream = new ObjectInputStream(byteStream)) {
			result = objectInputStream.readObject();
		} catch (Exception e) {
			log.error("Failed to deserialize.", e);
		}
		return result;
	}

}
