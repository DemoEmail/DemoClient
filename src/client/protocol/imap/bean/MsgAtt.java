package client.protocol.imap.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import client.core.Core;
import client.protocol.imap.ImapEvent;

public class MsgAtt extends ImapEvent{
	private long                    mUid;
	private long                    mSn;
	//----------------------------------------------------------[MsgAttStatic]
	private Envelope                mEnvelope;
	private String                  mInternalDate; //INERNALDATE
	private String                  mRfc822Header;
	private String          	    mRfc822Text;
	private int            		    mRfc822Size;
	private BodyStructure           mBs;
	//----------------------------------------------------------[MsgAttDynamic]
	private HashMap<String, String> mMsgAttDynamic; // TODO: we don't use it now
    private HashSet<String>         mFlags = null;
    	
    public void setFlags(HashSet<String> flags) {
    	mFlags = flags;
    }
    
    public boolean isExistedFlag(String flag) {
    	return mFlags == null ? false : mFlags.contains(flag);
    }
    
	private String getFlagsString() {
		if(mFlags == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		java.util.Iterator<String> iter = mFlags.iterator();
		while(iter.hasNext()) {
			sb.append(iter.next()).append(' ');
		}
		return sb.toString().trim();
	}
	
	public void setUid(long uid) {
		mUid =  uid;
	}
	
	public long getUid() {
		return mUid;
	}
	
	/**
	 * Get message sequence number 
	 * @return
	 */
	public long getSn() {
		return mSn;
	}
	
	/**
	 * Set message sequence number
	 * @param sn
	 */
	public void setSn(long sn) {
		mSn = sn;
	}
	
	public void setRfc822Header(String rfc822Header) {
		mRfc822Header = rfc822Header;
	}
	
	public String getRfc822Header() {
		return mRfc822Header;
	}
	                                 
	
	public void setMsgAttStatic() {
		
	}
	
	/**
	 * add a body BodyStructure instance
	 * @param bs
	 */
	public void setBodyStructure(BodyStructure bs) {
		mBs = bs;
	}
	
	/**
	 * Get all BodyStructure instances
	 * @return
	 */
	public BodyStructure getBodyStructures() {
		return mBs;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("\n(UID %s SN %s (FLAGS %s))", getUid(), getSn(), getFlagsString())).append('\n');
		sb.append(getBodyStructures()).append('\n');
		return sb.toString();
	}
}
