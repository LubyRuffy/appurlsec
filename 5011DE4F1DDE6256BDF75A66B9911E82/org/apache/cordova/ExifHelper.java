package org.apache.cordova;

import android.media.ExifInterface;
import java.io.IOException;

public class ExifHelper {
    private String aperture;
    private String datetime;
    private String exposureTime;
    private String flash;
    private String focalLength;
    private String gpsAltitude;
    private String gpsAltitudeRef;
    private String gpsDateStamp;
    private String gpsLatitude;
    private String gpsLatitudeRef;
    private String gpsLongitude;
    private String gpsLongitudeRef;
    private String gpsProcessingMethod;
    private String gpsTimestamp;
    private ExifInterface inFile;
    private String iso;
    private String make;
    private String model;
    private String orientation;
    private ExifInterface outFile;
    private String whiteBalance;

    public ExifHelper() {
        this.aperture = null;
        this.datetime = null;
        this.exposureTime = null;
        this.flash = null;
        this.focalLength = null;
        this.gpsAltitude = null;
        this.gpsAltitudeRef = null;
        this.gpsDateStamp = null;
        this.gpsLatitude = null;
        this.gpsLatitudeRef = null;
        this.gpsLongitude = null;
        this.gpsLongitudeRef = null;
        this.gpsProcessingMethod = null;
        this.gpsTimestamp = null;
        this.iso = null;
        this.make = null;
        this.model = null;
        this.orientation = null;
        this.whiteBalance = null;
        this.inFile = null;
        this.outFile = null;
    }

    public void createInFile(String r2_String) throws IOException {
        this.inFile = new ExifInterface(r2_String);
    }

    public void createOutFile(String r2_String) throws IOException {
        this.outFile = new ExifInterface(r2_String);
    }

    public int getOrientation() {
        int r1i = Integer.parseInt(this.orientation);
        if (r1i == 1) {
            return 0;
        }
        if (r1i == 6) {
            return 90;
        }
        if (r1i == 3) {
            return 180;
        }
        if (r1i == 8) {
            return 270;
        }
        return 0;
    }

    public void readExifData() {
        this.aperture = this.inFile.getAttribute("FNumber");
        this.datetime = this.inFile.getAttribute("DateTime");
        this.exposureTime = this.inFile.getAttribute("ExposureTime");
        this.flash = this.inFile.getAttribute("Flash");
        this.focalLength = this.inFile.getAttribute("FocalLength");
        this.gpsAltitude = this.inFile.getAttribute("GPSAltitude");
        this.gpsAltitudeRef = this.inFile.getAttribute("GPSAltitudeRef");
        this.gpsDateStamp = this.inFile.getAttribute("GPSDateStamp");
        this.gpsLatitude = this.inFile.getAttribute("GPSLatitude");
        this.gpsLatitudeRef = this.inFile.getAttribute("GPSLatitudeRef");
        this.gpsLongitude = this.inFile.getAttribute("GPSLongitude");
        this.gpsLongitudeRef = this.inFile.getAttribute("GPSLongitudeRef");
        this.gpsProcessingMethod = this.inFile.getAttribute("GPSProcessingMethod");
        this.gpsTimestamp = this.inFile.getAttribute("GPSTimeStamp");
        this.iso = this.inFile.getAttribute("ISOSpeedRatings");
        this.make = this.inFile.getAttribute("Make");
        this.model = this.inFile.getAttribute("Model");
        this.orientation = this.inFile.getAttribute("Orientation");
        this.whiteBalance = this.inFile.getAttribute("WhiteBalance");
    }

    public void resetOrientation() {
        this.orientation = "1";
    }

    public void writeExifData() throws IOException {
        if (this.outFile == null) {
        } else {
            if (this.aperture != null) {
                this.outFile.setAttribute("FNumber", this.aperture);
            }
            if (this.datetime != null) {
                this.outFile.setAttribute("DateTime", this.datetime);
            }
            if (this.exposureTime != null) {
                this.outFile.setAttribute("ExposureTime", this.exposureTime);
            }
            if (this.flash != null) {
                this.outFile.setAttribute("Flash", this.flash);
            }
            if (this.focalLength != null) {
                this.outFile.setAttribute("FocalLength", this.focalLength);
            }
            if (this.gpsAltitude != null) {
                this.outFile.setAttribute("GPSAltitude", this.gpsAltitude);
            }
            if (this.gpsAltitudeRef != null) {
                this.outFile.setAttribute("GPSAltitudeRef", this.gpsAltitudeRef);
            }
            if (this.gpsDateStamp != null) {
                this.outFile.setAttribute("GPSDateStamp", this.gpsDateStamp);
            }
            if (this.gpsLatitude != null) {
                this.outFile.setAttribute("GPSLatitude", this.gpsLatitude);
            }
            if (this.gpsLatitudeRef != null) {
                this.outFile.setAttribute("GPSLatitudeRef", this.gpsLatitudeRef);
            }
            if (this.gpsLongitude != null) {
                this.outFile.setAttribute("GPSLongitude", this.gpsLongitude);
            }
            if (this.gpsLongitudeRef != null) {
                this.outFile.setAttribute("GPSLongitudeRef", this.gpsLongitudeRef);
            }
            if (this.gpsProcessingMethod != null) {
                this.outFile.setAttribute("GPSProcessingMethod", this.gpsProcessingMethod);
            }
            if (this.gpsTimestamp != null) {
                this.outFile.setAttribute("GPSTimeStamp", this.gpsTimestamp);
            }
            if (this.iso != null) {
                this.outFile.setAttribute("ISOSpeedRatings", this.iso);
            }
            if (this.make != null) {
                this.outFile.setAttribute("Make", this.make);
            }
            if (this.model != null) {
                this.outFile.setAttribute("Model", this.model);
            }
            if (this.orientation != null) {
                this.outFile.setAttribute("Orientation", this.orientation);
            }
            if (this.whiteBalance != null) {
                this.outFile.setAttribute("WhiteBalance", this.whiteBalance);
            }
            this.outFile.saveAttributes();
        }
    }
}