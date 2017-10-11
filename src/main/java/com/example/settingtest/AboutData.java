package com.example.settingtest;

/**
 * Created by 11070562 on 2017/10/11.
 */

public class AboutData {

    String model;
    String IMEI1;
    String IMEI2;
    String MEID;
    String CMIIT;
    String AndroidVersion;
    String AndroidPatchLevel;
    String CPU;
    String CPUCurrent;
    String ROM;
    String Memory;
    String softVersion;
    String BasebandVersion;
    String CoreVersion;
    String DecompileTime;


    String useTime;
    String powerStatus;
    String SIMStatus;
    String IP;
    String MAC;
    String blueTooth;


    public String[] toStrings() {

        String string = model + "," + IMEI1 + "," + IMEI2 + "," + MEID + "," + CMIIT + "," + AndroidVersion + ","
                + AndroidPatchLevel + "," + CPU + "," + CPUCurrent + "," + Memory + "," + ROM + ","
                + softVersion + "," + BasebandVersion + "," + CoreVersion + "," + DecompileTime + ", ,"
                + useTime + "," + powerStatus + "," + SIMStatus + "," + IP + "," + MAC + "," + blueTooth + ", , , ";


        return string.split(",");
    }

    public String getSoftVersion() {
        return softVersion;
    }

    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }

    public String getROM() {
        return ROM;
    }

    public void setROM(String ROM) {
        this.ROM = ROM;
    }

    public String getCPUCurrent() {
        return CPUCurrent;
    }

    public void setCPUCurrent(String CPUCurrent) {
        this.CPUCurrent = CPUCurrent;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getPowerStatus() {
        return powerStatus;
    }

    public void setPowerStatus(String powerStatus) {
        this.powerStatus = powerStatus;
    }

    public String getSIMStatus() {
        return SIMStatus;
    }

    public void setSIMStatus(String SIMStatus) {
        this.SIMStatus = SIMStatus;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public String getBlueTooth() {
        return blueTooth;
    }

    public void setBlueTooth(String blueTooth) {
        this.blueTooth = blueTooth;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIMEI1() {
        return IMEI1;
    }

    public void setIMEI1(String IMEI1) {
        this.IMEI1 = IMEI1;
    }

    public String getIMEI2() {
        return IMEI2;
    }

    public void setIMEI2(String IMEI2) {
        this.IMEI2 = IMEI2;
    }

    public String getMEID() {
        return MEID;
    }

    public void setMEID(String MEID) {
        this.MEID = MEID;
    }

    public String getCMIIT() {
        return CMIIT;
    }

    public void setCMIIT(String CMIIT) {
        this.CMIIT = CMIIT;
    }

    public String getAndroidVersion() {
        return AndroidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        AndroidVersion = androidVersion;
    }

    public String getAndroidPatchLevel() {
        return AndroidPatchLevel;
    }

    public void setAndroidPatchLevel(String androidPatchLevel) {
        AndroidPatchLevel = androidPatchLevel;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getMemory() {
        return Memory;
    }

    public void setMemory(String memory) {
        Memory = memory;
    }


    public String getBasebandVersion() {
        return BasebandVersion;
    }

    public void setBasebandVersion(String basebandVersion) {
        BasebandVersion = basebandVersion;
    }

    public String getCoreVersion() {
        return CoreVersion;
    }

    public void setCoreVersion(String coreVersion) {
        CoreVersion = coreVersion;
    }

    public String getDecompileTime() {
        return DecompileTime;
    }

    public void setDecompileTime(String decompileTime) {
        DecompileTime = decompileTime;
    }
}
