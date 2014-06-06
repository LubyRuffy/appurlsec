package com.tencent.mm.sdk.platformtools;

import com.tencent.mm.sdk.contact.RContactStorage;
import com.tencent.tauth.Constants;
import java.nio.ByteBuffer;
import qsbk.app.utils.Base64;
import qsbk.app.utils.audit.RequestListener;
import qsbk.app.widget.listview.XListViewFooter;

public class LVBuffer {
    public static final int LENGTH_ALLOC_PER_NEW = 4096;
    public static final int MAX_STRING_LENGTH = 2048;
    private ByteBuffer a;
    private boolean b;

    private int a(int r5i) {
        if (this.a.limit() - this.a.position() > r5i) {
            return 0;
        }
        ByteBuffer r0_ByteBuffer = ByteBuffer.allocate(this.a.limit() + 4096);
        r0_ByteBuffer.put(this.a.array(), 0, this.a.position());
        this.a = r0_ByteBuffer;
        return 0;
    }

    public byte[] buildFinish() {
        if (this.b) {
            a(1);
            this.a.put((byte) 125);
            Object r0_Object = new Object[this.a.position()];
            System.arraycopy(this.a.array(), 0, r0_Object, 0, r0_Object.length);
            return r0_Object;
        } else {
            throw new Exception("Buffer For Parse");
        }
    }

    public boolean checkGetFinish() {
        return this.a.limit() - this.a.position() <= 1;
    }

    public int getInt() {
        if (!(this.b)) {
            return this.a.getInt();
        }
        throw new Exception("Buffer For Build");
    }

    public long getLong() {
        if (!(this.b)) {
            return this.a.getLong();
        }
        throw new Exception("Buffer For Build");
    }

    public String getString() {
        if (this.b) {
            throw new Exception("Buffer For Build");
        } else {
            short r0s = this.a.getShort();
            if (r0s > (short) 2048) {
                this.a = null;
                throw new Exception("Buffer String Length Error");
            } else {
                if (r0s == (short) 0) {
                    return RContactStorage.PRIMARY_KEY;
                }
                byte[] r1_byteA = new byte[r0s];
                this.a.get(r1_byteA, 0, r0s);
                return new String(r1_byteA);
            }
        }
    }

    public int initBuild() {
        this.a = ByteBuffer.allocate(LENGTH_ALLOC_PER_NEW);
        this.a.put((byte) 123);
        this.b = true;
        return 0;
    }

    public int initParse(byte[] r5_byteA) {
        int r0i;
        if (r5_byteA == null || r5_byteA.length == 0) {
            r0i = -1;
        } else if (r5_byteA[0] != 123) {
            r0i = RequestListener.DEFAULT_LOADED_SIZE;
        } else if (r5_byteA[r5_byteA.length - 1] != 125) {
            r0i = Constants.ERROR_URL;
        } else {
            r0i = 0;
        }
        if (r0i != 0) {
            this.a = null;
            throw new Exception(new StringBuilder("Parse Buffer Check Failed :").append(r0i).toString());
        } else {
            this.a = ByteBuffer.wrap(r5_byteA);
            this.a.position(1);
            this.b = false;
            return 0;
        }
    }

    public int putInt(int r3i) {
        if (this.b) {
            a(XListViewFooter.STATE_NODATA);
            this.a.putInt(r3i);
            return 0;
        } else {
            throw new Exception("Buffer For Parse");
        }
    }

    public int putLong(long r3j) {
        if (this.b) {
            a(Base64.DONT_BREAK_LINES);
            this.a.putLong(r3j);
            return 0;
        } else {
            throw new Exception("Buffer For Parse");
        }
    }

    public int putString(String r5_String) {
        if (this.b) {
            byte[] r0_byteA = null;
            if (r5_String != null) {
                r0_byteA = r5_String.getBytes();
            }
            if (r0_byteA == null) {
                r0_byteA = new byte[0];
            }
            if (r0_byteA.length > 2048) {
                throw new Exception("Buffer String Length Error");
            } else {
                a(r0_byteA.length + 2);
                this.a.putShort((short) r0_byteA.length);
                if (r0_byteA.length > 0) {
                    this.a.put(r0_byteA);
                }
                return 0;
            }
        } else {
            throw new Exception("Buffer For Parse");
        }
    }
}