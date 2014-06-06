package com.tencent.cloudsdk;

// compiled from: SourceFile
class ac implements be {
    final /* synthetic */ ay a;
    private final /* synthetic */ boolean b;

    ac(ay r1_ay, boolean r2z) {
        this.a = r1_ay;
        this.b = r2z;
    }

    public void a(long r2j) {
        ay.a(this.a, r2j);
    }

    public void a(String r4_String, int r5i, long r6j, long r8j) {
        er.a(ay.a(), new StringBuilder(">>>\u5f00\u59cb\u7f51\u7edc\u67e5\u8be2:[SERVER IP:").append(r4_String).append("][port:").append(r5i).append("][Thread id:").append(r8j).append("][startTime:").append(r6j).append("]").toString());
    }

    public void a(String r4_String, int r5i, long r6j, String r8_String, int r9i, long r10j) {
        ay.a(this.a, r9i, r4_String, r6j);
        ay.a(this.a);
        er.a(ay.a(), new StringBuilder(">>>\u7f51\u7edc\u67e5\u8be2\u9519\u8bef:[SERVER IP:").append(r4_String).append("][port:").append(r5i).append("][Thread id:").append(r10j).append("][ERR:").append(r8_String).append("]").toString());
    }

    public void a(String r8_String, int r9i, long r10j, byte[] r12_byteA, long r13j) {
        er.a(ay.a(), new StringBuilder(">>>\u7f51\u7edc\u67e5\u8be2\u5b8c\u6210:[SERVER IP:").append(r8_String).append("][port:").append(r9i).append("][Thread id:").append(r13j).append("][\u8017\u65f6:").append(System.currentTimeMillis() - r10j).append("]").toString());
        ay.a(this.a, r12_byteA, r8_String, r9i, r10j, this.b);
    }

    public void b(long r2j) {
        ay.b(this.a, r2j);
    }

    public void c(long r2j) {
        ay.c(this.a, r2j);
    }
}