package com.audio.utils;

import java.nio.ByteOrder;

/**
 * Created by wangzhe on 2016/10/18.
 * byte工具类
 */

public class BytesTransUtil {
    private static BytesTransUtil instance = null;

    private BytesTransUtil() {

    }

    public static BytesTransUtil getInstance() {
        if (instance == null) {
            instance = new BytesTransUtil();
        }
        return instance;
    }

    public boolean testCPU() {
        return ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    public byte[] getBytes(short s, boolean bBigEnding) {
        byte[] buf = new byte[2];
        if (bBigEnding)
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 0x00ff);
                s >>= 8;
            }
        else
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 0x00ff);
                s >>= 8;
            }
        return buf;
    }

    public byte[] getBytes(int s, boolean bBigEnding) {
        byte[] buf = new byte[4];
        if (bBigEnding) {
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 0x000000ff);
                s >>= 8;
            }
        } else {
            System.out.println(1);
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 0x000000ff);
                s >>= 8;
            }
        }
        return buf;
    }

    public byte[] getBytes(long s, boolean bBigEnding) {
        byte[] buf = new byte[8];
        if (bBigEnding)
            for (int i = buf.length - 1; i >= 0; i--) {
                buf[i] = (byte) (s & 0x00000000000000ff);
                s >>= 8;
            }
        else
            for (int i = 0; i < buf.length; i++) {
                buf[i] = (byte) (s & 0x00000000000000ff);
                s >>= 8;
            }
        return buf;
    }

    public short getShort(byte[] buf, boolean bBigEnding) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 2) {
            throw new IllegalArgumentException("byte array size > 2 !");
        }
        short r = 0;
        if (bBigEnding) {
            for (byte aBuf : buf) {
                r <<= 8;
                r |= (aBuf & 0x00ff);
            }
        } else {
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x00ff);
            }
        }

        return r;
    }

    public int getInt(byte[] buf, boolean bBigEnding) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 4) {
            throw new IllegalArgumentException("byte array size > 4 !");
        }
        int r = 0;
        if (bBigEnding) {
            for (byte aBuf : buf) {
                r <<= 8;
                r |= (aBuf & 0x000000ff);
            }
        } else {
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x000000ff);
            }
        }
        return r;
    }

    public long getLong(byte[] buf, boolean bBigEnding) {
        if (buf == null) {
            throw new IllegalArgumentException("byte array is null!");
        }
        if (buf.length > 8) {
            throw new IllegalArgumentException("byte array size > 8 !");
        }
        long r = 0;
        if (bBigEnding) {
            for (byte aBuf : buf) {
                r <<= 8;
                r |= (aBuf & 0x00000000000000ff);
            }
        } else {
            for (int i = buf.length - 1; i >= 0; i--) {
                r <<= 8;
                r |= (buf[i] & 0x00000000000000ff);
            }
        }
        return r;
    }

    public byte[] getBytes(int i) {
        return getBytes(i, this.testCPU());
    }

    public byte[] getBytes(short s) {
        return getBytes(s, this.testCPU());
    }

    public byte[] getBytes(long l) {
        return getBytes(l, this.testCPU());
    }

    public int getInt(byte[] buf) {
        return getInt(buf, this.testCPU());
    }

    public short getShort(byte[] buf) {
        return getShort(buf, this.testCPU());
    }

    public long getLong(byte[] buf) {
        return getLong(buf, this.testCPU());
    }

    public short[] Bytes2Shorts(byte[] buf) {
        byte bLength = 2;
        short[] s = new short[buf.length / bLength];
        for (int iLoop = 0; iLoop < s.length; iLoop++) {
            byte[] temp = new byte[bLength];
            System.arraycopy(buf, iLoop * bLength, temp, 0, bLength);
            s[iLoop] = getShort(temp);
        }
        return s;
    }

    public byte[] Shorts2Bytes(short[] s) {
        byte bLength = 2;
        byte[] buf = new byte[s.length * bLength];
        for (int iLoop = 0; iLoop < s.length; iLoop++) {
            byte[] temp = getBytes(s[iLoop]);
            System.arraycopy(temp, 0, buf, iLoop * bLength, bLength);
        }
        return buf;
    }

    public int[] Bytes2Ints(byte[] buf) {
        byte bLength = 4;
        int[] s = new int[buf.length / bLength];
        for (int iLoop = 0; iLoop < s.length; iLoop++) {
            byte[] temp = new byte[bLength];
            System.arraycopy(buf, iLoop * bLength, temp, 0, bLength);
            s[iLoop] = getInt(temp);
        }
        return s;
    }

    public byte[] Ints2Bytes(int[] s) {
        byte bLength = 4;
        byte[] buf = new byte[s.length * bLength];
        for (int iLoop = 0; iLoop < s.length; iLoop++) {
            byte[] temp = getBytes(s[iLoop]);
            System.arraycopy(temp, 0, buf, iLoop * bLength, bLength);
        }
        return buf;
    }

    public long[] Bytes2Longs(byte[] buf) {
        byte bLength = 8;
        long[] s = new long[buf.length / bLength];
        for (int iLoop = 0; iLoop < s.length; iLoop++) {
            byte[] temp = new byte[bLength];
            System.arraycopy(buf, iLoop * bLength, temp, 0, bLength);
            s[iLoop] = getLong(temp);
        }
        return s;
    }

    public byte[] Longs2Bytes(long[] s) {
        byte bLength = 8;
        byte[] buf = new byte[s.length * bLength];
        for (int iLoop = 0; iLoop < s.length; iLoop++) {
            byte[] temp = getBytes(s[iLoop]);
            System.arraycopy(temp, 0, buf, iLoop * bLength, bLength);
        }
        return buf;
    }

}