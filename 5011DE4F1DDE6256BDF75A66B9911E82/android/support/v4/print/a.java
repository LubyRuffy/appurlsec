package android.support.v4.print;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument.Page;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.print.PrintDocumentInfo;
import android.print.PrintDocumentInfo.Builder;
import android.print.pdf.PrintedPdfDocument;
import java.io.FileOutputStream;
import java.io.IOException;

// compiled from: PrintHelperKitkat.java
class a extends PrintDocumentAdapter {
    final /* synthetic */ String a;
    final /* synthetic */ Bitmap b;
    final /* synthetic */ int c;
    final /* synthetic */ PrintHelperKitkat d;
    private PrintAttributes e;

    a(PrintHelperKitkat r1_PrintHelperKitkat, String r2_String, Bitmap r3_Bitmap, int r4i) {
        this.d = r1_PrintHelperKitkat;
        this.a = r2_String;
        this.b = r3_Bitmap;
        this.c = r4i;
    }

    public void onLayout(PrintAttributes r4_PrintAttributes, PrintAttributes r5_PrintAttributes, CancellationSignal r6_CancellationSignal, LayoutResultCallback r7_LayoutResultCallback, Bundle r8_Bundle) {
        boolean r0z = true;
        this.e = r5_PrintAttributes;
        PrintDocumentInfo r1_PrintDocumentInfo = new Builder(this.a).setContentType(1).setPageCount(1).build();
        if (r5_PrintAttributes.equals(r4_PrintAttributes)) {
            r0z = false;
            r7_LayoutResultCallback.onLayoutFinished(r1_PrintDocumentInfo, r0z);
        } else {
            r7_LayoutResultCallback.onLayoutFinished(r1_PrintDocumentInfo, r0z);
        }
    }

    public void onWrite(PageRange[] r9_PageRangeA, ParcelFileDescriptor r10_ParcelFileDescriptor, CancellationSignal r11_CancellationSignal, WriteResultCallback r12_WriteResultCallback) {
        PrintedPdfDocument r1_PrintedPdfDocument = new PrintedPdfDocument(this.d.a, this.e);
        try {
            Page r2_Page = r1_PrintedPdfDocument.startPage(1);
            RectF r3_RectF = new RectF(r2_Page.getInfo().getContentRect());
            Matrix r4_Matrix = new Matrix();
            float r0f = r3_RectF.width() / ((float) this.b.getWidth());
            r0f = this.c == 2 ? Math.max(r0f, r3_RectF.height() / ((float) this.b.getHeight())) : Math.min(r0f, r3_RectF.height() / ((float) this.b.getHeight()));
            r4_Matrix.postScale(r0f, r0f);
            r4_Matrix.postTranslate((r3_RectF.width() - (((float) this.b.getWidth()) * r0f)) / 2.0f, (r3_RectF.height() - (r0f * ((float) this.b.getHeight()))) / 2.0f);
            r2_Page.getCanvas().drawBitmap(this.b, r4_Matrix, null);
            r1_PrintedPdfDocument.finishPage(r2_Page);
            r1_PrintedPdfDocument.writeTo(new FileOutputStream(r10_ParcelFileDescriptor.getFileDescriptor()));
            PageRange[] r0_PageRangeA = new PageRange[1];
            r0_PageRangeA[0] = PageRange.ALL_PAGES;
            r12_WriteResultCallback.onWriteFinished(r0_PageRangeA);
            if (r1_PrintedPdfDocument != null) {
                r1_PrintedPdfDocument.close();
            }
            if (r10_ParcelFileDescriptor != null) {
                try {
                    r10_ParcelFileDescriptor.close();
                } catch (IOException e) {
                }
            }
        } catch (Throwable th) {
            if (r1_PrintedPdfDocument != null) {
                r1_PrintedPdfDocument.close();
            }
            if (r10_ParcelFileDescriptor != null) {
                r10_ParcelFileDescriptor.close();
            }
        }
    }
}