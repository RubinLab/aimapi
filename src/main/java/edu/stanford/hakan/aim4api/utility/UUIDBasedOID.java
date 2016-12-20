/* Copyright (c) 2001-2016, David A. Clunie DBA Pixelmed Publishing. All rights reserved. */

package edu.stanford.hakan.aim4api.utility;

import java.math.BigInteger;
	
/**
 * <p>A class for creating and convertin UUID based OIDs.</p>
 *
 * <p>See <a href="http://www.itu.int/ITU-T/studygroups/com17/oid/X.667-E.pdf">ITU X.667 Information technology - Open Systems Interconnection - Procedures for the operation of OSI Registration Authorities: Generation and registration of Universally Unique Identifiers (UUIDs) and their use as ASN.1 Object Identifier components</a>.</p>
 *
 * @author	dclunie
 */

/**
 * Removed the unused parts
 * uuid use changed to Robert Kieffer's UUID as java.util.UUID is not supported by GWT client side.
 * UUID to decimal representation conversion is done using directly BigInteger class's BigInteger(String,radix) constructor
 * instead of going from unsigned long as parseUnsignedLong is also not supported by GWT client side
 * @author emelalkim
 *
 */

public class UUIDBasedOID {

	private static final String identString = "@(#) $Header: /userland/cvs/pixelmed/imgbook/com/pixelmed/utils/UUIDBasedOID.java,v 1.8 2016/05/26 19:11:52 dclunie Exp $";

	protected static final String OID_PREFIX = "2.25";	// {joint-iso-itu-t uuid(25) <uuid-single-integer-value>}
	protected static final String OID_PREFIX_REMOVAL_REGEX = "^"+OID_PREFIX+".";
	
	protected String uuid;
	protected String oid;

	/**
	 * <p>Construct a new OID with a new random UUID.</p>
	 */
	public UUIDBasedOID() {
		uuid = UUID4GWT.uuid();
		oid = createOIDFromUUIDCanonicalHexString(uuid.toString());
	}
	
	
	/**
	 * <p>Get the string representation of the OID.</p>
	 *
	 * @return	the string representation of the OID
	 */
	public String getOID() { return oid; }
	
	/**
	 * <p>Get the UUID of the OID.</p>
	 *
	 * @return	the UUID
	 */
	public String getUUID() { return uuid; }
	
	/**
	 * <p>Create an OID from the canonical hex string form of a UUID.</p>
	 *
	 * @param		hexString					canonical hex string form of a UUID 
	 * @return									the OID
	 * @throws	IllegalArgumentException	if name does not conform to the string representation
	 */
	public static String createOIDFromUUIDCanonicalHexString(String hexString) throws IllegalArgumentException {
		byte[] bytes=hexString.replace("-", "").getBytes();
		BigInteger decimalValue = new BigInteger(new String(bytes),16);
		System.out.println("iod "+OID_PREFIX+"."+decimalValue.toString());
		
		return OID_PREFIX+"."+decimalValue.toString();
	}
	

}


