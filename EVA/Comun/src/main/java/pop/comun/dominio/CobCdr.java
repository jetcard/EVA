/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pop.comun.dominio;

import java.util.Date;

/**
 *
 * @author Jyoverar
 */
public class CobCdr extends CobSeguimientoDet{
    
    private Date calldate;
    private String clid;
    private String  src;
    private String dst;
    private String dcontext;
    private String channel;
    private String dstchannel;
    private String lastapp;
    private String lastdata;
    private int duration;
    private int billsec;
    private String disposition;
    private int amaflags;
    private String accountcode;
    private String uniqueid;
    private String userfield;
    private String recordingfile;
    private String cnum;
    private String cnam;
    private String outbound_cnum;
    private String outbound_cnam;
    private String dst_cnam;
    private String did;
    private String listTchn;
    private String listTchn2;
    private String calldateB;

    public Date getCalldate() {
        return calldate;
    }

    public String getClid() {
        return clid;
    }

    public String getSrc() {
        return src;
    }

    public String getDst() {
        return dst;
    }

    public String getDcontext() {
        return dcontext;
    }

    public String getChannel() {
        return channel;
    }

    public String getDstchannel() {
        return dstchannel;
    }

    public String getLastapp() {
        return lastapp;
    }

    public String getLastdata() {
        return lastdata;
    }

    public int getDuration() {
        return duration;
    }

    public int getBillsec() {
        return billsec;
    }

    public String getDisposition() {
        return disposition;
    }

    public int getAmaflags() {
        return amaflags;
    }

    public String getAccountcode() {
        return accountcode;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public String getUserfield() {
        return userfield;
    }

    public String getRecordingfile() {
        return recordingfile;
    }

    public String getCnum() {
        return cnum;
    }

    public String getCnam() {
        return cnam;
    }

    public String getOutbound_cnum() {
        return outbound_cnum;
    }

    public String getOutbound_cnam() {
        return outbound_cnam;
    }

    public String getDst_cnam() {
        return dst_cnam;
    }

    public String getDid() {
        return did;
    }

    public void setCalldate(Date calldate) {
        this.calldate = calldate;
    }

    public void setClid(String clid) {
        this.clid = clid;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public void setDcontext(String dcontext) {
        this.dcontext = dcontext;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setDstchannel(String dstchannel) {
        this.dstchannel = dstchannel;
    }

    public void setLastapp(String lastapp) {
        this.lastapp = lastapp;
    }

    public void setLastdata(String lastdata) {
        this.lastdata = lastdata;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setBillsec(int billsec) {
        this.billsec = billsec;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public void setAmaflags(int amaflags) {
        this.amaflags = amaflags;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public void setUserfield(String userfield) {
        this.userfield = userfield;
    }

    public void setRecordingfile(String recordingfile) {
        this.recordingfile = recordingfile;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }

    public void setCnam(String cnam) {
        this.cnam = cnam;
    }

    public void setOutbound_cnum(String outbound_cnum) {
        this.outbound_cnum = outbound_cnum;
    }

    public void setOutbound_cnam(String outbound_cnam) {
        this.outbound_cnam = outbound_cnam;
    }

    public void setDst_cnam(String dst_cnam) {
        this.dst_cnam = dst_cnam;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getListTchn() {
        return listTchn;
    }

    public void setListTchn(String listTchn) {
        this.listTchn = listTchn;
    }

    public String getCalldateB() {
        return calldateB;
    }

    public void setCalldateB(String calldateB) {
        this.calldateB = calldateB;
    }

    /**
     * @return the listTchn2
     */
    public String getListTchn2() {
        return listTchn2;
    }

    /**
     * @param listTchn2 the listTchn2 to set
     */
    public void setListTchn2(String listTchn2) {
        this.listTchn2 = listTchn2;
    }
}
