package ninja.janibek.kaztracker.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by janibek on 7/19/15.
 * Version 1.0
 */
public class Tracking {

    private final String trackerId;
    private final String timestamp;
    private final String version;
    private final String statusCode;
    private final String regDate;
    private final int sender;
    private final String senderAddress;
    private final String recipientName;
    private final String recipientAddress;
    private final String recipientNameReal;
    private final String recipientPostcode;
    private final String dlvDdate;
    private final String dlbDepName;
    private final String dlbPostIndex;
    private final String depName;
    private final String dlvPostIndex;
    private final int postType;
    private final String dispute;
    private final String xStatus;
    private final String xPostTypeName;
    private final String xDirection;
    private final String xNumPlace;
    private final String xNumStlg;
    private final String xExtraInfo;

    private Tracking(String trackerId, String timestamp, String version, String statusCode,
                     String regDate, int sender, String senderAddress, String recipientName,
                     String recipientAddress, String recipientNameReal, String recipientPostcode,
                     String dlvDdate, String dlbDepName, String dlbPostIndex, String depName,
                     String dlvPostIndex, int postType, String dispute, String xStatus,
                     String xPostTypeName, String xDirection, String xNumPlace, String xNumStlg, String xExtraInfo) {
        this.trackerId = trackerId;
        this.timestamp = timestamp;
        this.version = version;
        this.statusCode = statusCode;
        this.regDate = regDate;
        this.sender = sender;
        this.senderAddress = senderAddress;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.recipientNameReal = recipientNameReal;
        this.recipientPostcode = recipientPostcode;
        this.dlvDdate = dlvDdate;
        this.dlbDepName = dlbDepName;
        this.dlbPostIndex = dlbPostIndex;
        this.depName = depName;
        this.dlvPostIndex = dlvPostIndex;
        this.postType = postType;
        this.dispute = dispute;
        this.xStatus = xStatus;
        this.xPostTypeName = xPostTypeName;
        this.xDirection = xDirection;
        this.xNumPlace = xNumPlace;
        this.xNumStlg = xNumStlg;
        this.xExtraInfo = xExtraInfo;
    }

    @NonNull
    public String getTrackerId() {
        return trackerId;
    }

    @NonNull
    public String getTimestamp() {
        return timestamp;
    }

    @Nullable
    public String getVersion() {
        return version;
    }

    @Nullable
    public String getStatusCode() {
        return statusCode;
    }

    @Nullable
    public String getRegDate() {
        return regDate;
    }

    @Nullable
    public int getSender() {
        return sender;
    }

    @Nullable
    public String getSenderAddress() {
        return senderAddress;
    }

    @Nullable
    public String getRecipientName() {
        return recipientName;
    }

    @Nullable
    public String getRecipientAddress() {
        return recipientAddress;
    }

    @Nullable
    public String getRecipientNameReal() {
        return recipientNameReal;
    }

    @Nullable
    public String getRecipientPostcode() {
        return recipientPostcode;
    }

    @Nullable
    public String getDlvDdate() {
        return dlvDdate;
    }

    @Nullable
    public String getDlbDepName() {
        return dlbDepName;
    }

    @Nullable
    public String getDlbPostIndex() {
        return dlbPostIndex;
    }

    @Nullable
    public String getDepName() {
        return depName;
    }

    @Nullable
    public String getDlvPostIndex() {
        return dlvPostIndex;
    }

    @Nullable
    public int getPostType() {
        return postType;
    }

    @Nullable
    public String getDispute() {
        return dispute;
    }

    @Nullable
    public String getxStatus() {
        return xStatus;
    }

    @Nullable
    public String getxPostTypeName() {
        return xPostTypeName;
    }

    @Nullable
    public String getxDirection() {
        return xDirection;
    }

    @Nullable
    public String getxNumPlace() {
        return xNumPlace;
    }

    @Nullable
    public String getxNumStlg() {
        return xNumStlg;
    }

    @Nullable
    public String getxExtraInfo() {
        return xExtraInfo;
    }

    public static class Builder {
        private String trackerId;
        private String timestamp;
        private String version;
        private String statusCode;
        private String regDate;
        private int sender;
        private String senderAddress;
        private String recipientName;
        private String recipientAddress;
        private String recipientNameReal;
        private String recipientPostcode;
        private String dlvDdate;
        private String dlbDepName;
        private String dlbPostIndex;
        private String depName;
        private String dlvPostIndex;
        private int postType;
        private String dispute;
        private String xStatus;
        private String xPostTypeName;
        private String xDirection;
        private String xNumPlace;
        private String xNumStlg;
        private String xExtraInfo;

        public Builder(String trackerId, String timestamp) {
            this.trackerId = trackerId;
            this.timestamp = timestamp;
        }

        public Builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder setStatusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder setRegDate(String regDate) {
            this.regDate = regDate;
            return this;
        }

        public Builder setSender(int sender) {
            this.sender = sender;
            return this;
        }

        public Builder setSenderAddress(String senderAddress) {
            this.senderAddress = senderAddress;
            return this;
        }

        public Builder setRecipientName(String recipientName) {
            this.recipientName = recipientName;
            return this;
        }

        public Builder setRecipientAddress(String recipientAddress) {
            this.recipientAddress = recipientAddress;
            return this;
        }

        public Builder setRecipientNameReal(String recipientNameReal) {
            this.recipientNameReal = recipientNameReal;
            return this;
        }

        public Builder setRecipientPostcode(String recipientPostcode) {
            this.recipientPostcode = recipientPostcode;
            return this;
        }

        public Builder setDlvDdate(String dlvDdate) {
            this.dlvDdate = dlvDdate;
            return this;
        }

        public Builder setDlbDepName(String dlbDepName) {
            this.dlbDepName = dlbDepName;
            return this;
        }

        public Builder setDlbPostIndex(String dlbPostIndex) {
            this.dlbPostIndex = dlbPostIndex;
            return this;
        }

        public Builder setDepName(String depName) {
            this.depName = depName;
            return this;
        }

        public Builder setDlvPostIndex(String dlvPostIndex) {
            this.dlvPostIndex = dlvPostIndex;
            return this;
        }

        public Builder setPostType(int postType) {
            this.postType = postType;
            return this;
        }

        public Builder setDispute(String dispute) {
            this.dispute = dispute;
            return this;
        }

        public Builder setxStatus(String xStatus) {
            this.xStatus = xStatus;
            return this;
        }

        public Builder setxPostTypeName(String xPostTypeName) {
            this.xPostTypeName = xPostTypeName;
            return this;
        }

        public Builder setxDirection(String xDirection) {
            this.xDirection = xDirection;
            return this;
        }

        public Builder setxNumPlace(String xNumPlace) {
            this.xNumPlace = xNumPlace;
            return this;
        }

        public Builder setxNumStlg(String xNumStlg) {
            this.xNumStlg = xNumStlg;
            return this;
        }

        public Builder setxExtraInfo(String xExtraInfo) {
            this.xExtraInfo = xExtraInfo;
            return this;
        }

        public Tracking build() {
            return new Tracking(trackerId, timestamp, version, statusCode, regDate, sender, senderAddress,
                    recipientName, recipientAddress, recipientNameReal, recipientPostcode, dlvDdate, dlbDepName,
                    dlbPostIndex, depName, dlvPostIndex, postType, dispute, xStatus, xPostTypeName, xDirection,
                    xNumPlace, xNumStlg, xExtraInfo);
        }
    }
}