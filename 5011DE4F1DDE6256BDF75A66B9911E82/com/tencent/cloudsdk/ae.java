package com.tencent.cloudsdk;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

// compiled from: SourceFile
public class ae implements Iterable {
    private ConcurrentLinkedQueue a;
    private AtomicInteger b;

    public ae() {
        this.a = null;
        this.b = null;
        this.a = new ConcurrentLinkedQueue();
        this.b = new AtomicInteger(0);
    }

    public int a() {
        return this.b.get();
    }

    public int a(String r3_String) {
        int r0i = r3_String.length();
        this.a.add(r3_String);
        return this.b.addAndGet(r0i);
    }

    public void a(Writer r11_Writer, char[] r12_charA) throws IOException {
        if (r11_Writer == null || r12_charA == null || r12_charA.length == 0) {
        } else {
            int r4i = r12_charA.length;
            Iterator r8_Iterator = iterator();
            int r1i = 0;
            int r3i = r4i;
            while (r8_Iterator.hasNext()) {
                String r0_String = (String) r8_Iterator.next();
                int r6i = r0_String.length();
                int r7i = 0;
                while (r6i > 0) {
                    int r5i;
                    r5i = r3i > r6i ? r6i : r3i;
                    r0_String.getChars(r7i, r7i + r5i, r12_charA, r1i);
                    r3i -= r5i;
                    r1i += r5i;
                    r6i -= r5i;
                    r5i += r7i;
                    if (r3i == 0) {
                        r11_Writer.write(r12_charA, 0, r4i);
                        r1i = 0;
                        r3i = r4i;
                        r7i = r5i;
                    } else {
                        r7i = r5i;
                    }
                }
            }
            if (r1i > 0) {
                r11_Writer.write(r12_charA, 0, r1i);
            }
            r11_Writer.flush();
        }
    }

    public void b() {
        this.a.clear();
        this.b.set(0);
    }

    public Iterator iterator() {
        return this.a.iterator();
    }
}