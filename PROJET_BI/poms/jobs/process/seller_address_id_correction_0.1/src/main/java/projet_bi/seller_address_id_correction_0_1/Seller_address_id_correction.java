// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Big Data
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package projet_bi.seller_address_id_correction_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: Seller_address_id_correction Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class Seller_address_id_correction implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Seller_address_id_correction";
	private final String projectName = "PROJET_BI";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Seller_address_id_correction.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Seller_address_id_correction.this,
									new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputJSON_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputJSON_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class clean_customerStruct implements routines.system.IPersistableRow<clean_customerStruct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Seller_address_id_correction = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String seller_id;

		public String getSeller_id() {
			return this.seller_id;
		}

		public Integer address_id;

		public Integer getAddress_id() {
			return this.address_id;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.seller_id == null) ? 0 : this.seller_id.hashCode());

				result = prime * result + ((this.address_id == null) ? 0 : this.address_id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final clean_customerStruct other = (clean_customerStruct) obj;

			if (this.seller_id == null) {
				if (other.seller_id != null)
					return false;

			} else if (!this.seller_id.equals(other.seller_id))

				return false;

			if (this.address_id == null) {
				if (other.address_id != null)
					return false;

			} else if (!this.address_id.equals(other.address_id))

				return false;

			return true;
		}

		public void copyDataTo(clean_customerStruct other) {

			other.seller_id = this.seller_id;
			other.address_id = this.address_id;

		}

		public void copyKeysDataTo(clean_customerStruct other) {

			other.seller_id = this.seller_id;
			other.address_id = this.address_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Seller_address_id_correction.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Seller_address_id_correction.length == 0) {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Seller_address_id_correction.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Seller_address_id_correction.length == 0) {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Seller_address_id_correction) {

				try {

					int length = 0;

					this.seller_id = readString(dis);

					this.address_id = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Seller_address_id_correction) {

				try {

					int length = 0;

					this.seller_id = readString(dis);

					this.address_id = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.seller_id, dos);

				// Integer

				writeInteger(this.address_id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.seller_id, dos);

				// Integer

				writeInteger(this.address_id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("seller_id=" + seller_id);
			sb.append(",address_id=" + String.valueOf(address_id));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(clean_customerStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.seller_id, other.seller_id);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.address_id, other.address_id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Seller_address_id_correction = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[0];

		public Integer address_id;

		public Integer getAddress_id() {
			return this.address_id;
		}

		public String state;

		public String getState() {
			return this.state;
		}

		public String city;

		public String getCity() {
			return this.city;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Seller_address_id_correction.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Seller_address_id_correction.length == 0) {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Seller_address_id_correction.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Seller_address_id_correction.length == 0) {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Seller_address_id_correction) {

				try {

					int length = 0;

					this.address_id = readInteger(dis);

					this.state = readString(dis);

					this.city = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Seller_address_id_correction) {

				try {

					int length = 0;

					this.address_id = readInteger(dis);

					this.state = readString(dis);

					this.city = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.address_id, dos);

				// String

				writeString(this.state, dos);

				// String

				writeString(this.city, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.address_id, dos);

				// String

				writeString(this.state, dos);

				// String

				writeString(this.city, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("address_id=" + String.valueOf(address_id));
			sb.append(",state=" + state);
			sb.append(",city=" + city);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputDelimited_1Struct
			implements routines.system.IPersistableRow<after_tFileInputDelimited_1Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Seller_address_id_correction = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer address_id;

		public Integer getAddress_id() {
			return this.address_id;
		}

		public String state;

		public String getState() {
			return this.state;
		}

		public String city;

		public String getCity() {
			return this.city;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.address_id == null) ? 0 : this.address_id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final after_tFileInputDelimited_1Struct other = (after_tFileInputDelimited_1Struct) obj;

			if (this.address_id == null) {
				if (other.address_id != null)
					return false;

			} else if (!this.address_id.equals(other.address_id))

				return false;

			return true;
		}

		public void copyDataTo(after_tFileInputDelimited_1Struct other) {

			other.address_id = this.address_id;
			other.state = this.state;
			other.city = this.city;

		}

		public void copyKeysDataTo(after_tFileInputDelimited_1Struct other) {

			other.address_id = this.address_id;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Seller_address_id_correction.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Seller_address_id_correction.length == 0) {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Seller_address_id_correction.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Seller_address_id_correction.length == 0) {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Seller_address_id_correction) {

				try {

					int length = 0;

					this.address_id = readInteger(dis);

					this.state = readString(dis);

					this.city = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Seller_address_id_correction) {

				try {

					int length = 0;

					this.address_id = readInteger(dis);

					this.state = readString(dis);

					this.city = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.address_id, dos);

				// String

				writeString(this.state, dos);

				// String

				writeString(this.city, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.address_id, dos);

				// String

				writeString(this.state, dos);

				// String

				writeString(this.city, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("address_id=" + String.valueOf(address_id));
			sb.append(",state=" + state);
			sb.append(",city=" + city);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputDelimited_1Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.address_id, other.address_id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tFileInputJSON_1Process(globalMap);

				row2Struct row2 = new row2Struct();
				clean_customerStruct clean_customer = new clean_customerStruct();

				/**
				 * [tFileOutputJSON_1 begin ] start
				 */

				ok_Hash.put("tFileOutputJSON_1", false);
				start_Hash.put("tFileOutputJSON_1", System.currentTimeMillis());

				currentComponent = "tFileOutputJSON_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "clean_customer");
				}

				int tos_count_tFileOutputJSON_1 = 0;

				int nb_line_tFileOutputJSON_1 = 0;
				java.io.File file_tFileOutputJSON_1 = new java.io.File(
						"C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/dimensionCorrectedSeller.json");
				java.io.File dir_tFileOutputJSON_1 = file_tFileOutputJSON_1.getParentFile();
				java.io.PrintWriter outtFileOutputJSON_1 = new java.io.PrintWriter(
						new java.io.BufferedWriter(new java.io.FileWriter(
								"C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/dimensionCorrectedSeller.json")));
				outtFileOutputJSON_1.append("{\"" + "data" + "\":[");
				boolean isFirst_tFileOutputJSON_1 = true;

				/**
				 * [tFileOutputJSON_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row1Struct> tHash_Lookup_row1 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row1Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row1Struct>) globalMap
						.get("tHash_Lookup_row1"));

				tHash_Lookup_row1.initGet();

				row1Struct row1HashKey = new row1Struct();
				row1Struct row1Default = new row1Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
					String c_city;
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				clean_customerStruct clean_customer_tmp = new clean_customerStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/addressDimension.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/addressDimension.csv", "UTF-8",
								";", "\n", false, 1, 0, limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row2 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row2 = new row2Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row2.address_id = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"address_id", "row2", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row2.address_id = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 1;

							row2.state = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 2;

							row2.city = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row2 = null;

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row2"
						if (row2 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row2"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row1"
							///////////////////////////////////////////////

							boolean forceLooprow1 = false;

							row1Struct row1ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								tHash_Lookup_row1.lookup(row1HashKey);

								if (!tHash_Lookup_row1.hasNext()) { // G_TM_M_090

									forceLooprow1 = true;

								} // G_TM_M_090

							} // G_TM_M_020

							else { // G 20 - G 21
								forceLooprow1 = true;
							} // G 21

							row1Struct row1 = null;

							while ((tHash_Lookup_row1 != null && tHash_Lookup_row1.hasNext()) || forceLooprow1) { // G_TM_M_043

								// CALL close loop of lookup 'row1'

								row1Struct fromLookup_row1 = null;
								row1 = row1Default;

								if (!forceLooprow1) { // G 46

									fromLookup_row1 = tHash_Lookup_row1.next();

									if (fromLookup_row1 != null) {
										row1 = fromLookup_row1;
									}

								} // G 46

								forceLooprow1 = false;

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_1__Struct Var = Var__tMap_1;
									Var.c_city = row1.seller_city.replaceAll("[àáâãäå]", "a").replaceAll("[èéêë]", "e")
											.replaceAll("[ìíîï]", "i").replaceAll("[òóôõö]", "o")
											.replaceAll("[ùúûü]", "u").replaceAll("[ñ]", "n").replaceAll("[ç]", "c")
											.replaceAll("[š]", "s").replaceAll("[ž]", "z");// ###############################
									// ###############################
									// # Output tables

									clean_customer = null;

// # Output table : 'clean_customer'
// # Filter conditions 
									if (

									row1.seller_state.concat(Var.c_city).equals(row2.state.concat(row2.city))

									) {
										clean_customer_tmp.seller_id = row1.seller_id;
										clean_customer_tmp.address_id = row2.address_id;
										clean_customer = clean_customer_tmp;
									} // closing filter/reject
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_1 = false;

								tos_count_tMap_1++;

								/**
								 * [tMap_1 main ] stop
								 */

								/**
								 * [tMap_1 process_data_begin ] start
								 */

								currentComponent = "tMap_1";

								/**
								 * [tMap_1 process_data_begin ] stop
								 */
// Start of branch "clean_customer"
								if (clean_customer != null) {

									/**
									 * [tFileOutputJSON_1 main ] start
									 */

									currentComponent = "tFileOutputJSON_1";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "clean_customer"

										);
									}

									org.json.simple.JSONObject jsonRowtFileOutputJSON_1 = new org.json.simple.JSONObject();
									if (clean_customer.seller_id != null) {

										jsonRowtFileOutputJSON_1.put("seller_id", clean_customer.seller_id);

									} else {
										jsonRowtFileOutputJSON_1.put("seller_id", null);
									}

									if (clean_customer.address_id != null) {

										jsonRowtFileOutputJSON_1.put("address_id", clean_customer.address_id);

									} else {
										jsonRowtFileOutputJSON_1.put("address_id", null);
									}

									if (!isFirst_tFileOutputJSON_1) {
										outtFileOutputJSON_1.append(",");
									}
									isFirst_tFileOutputJSON_1 = false;
									outtFileOutputJSON_1.append(jsonRowtFileOutputJSON_1.toJSONString());
									nb_line_tFileOutputJSON_1++;

									tos_count_tFileOutputJSON_1++;

									/**
									 * [tFileOutputJSON_1 main ] stop
									 */

									/**
									 * [tFileOutputJSON_1 process_data_begin ] start
									 */

									currentComponent = "tFileOutputJSON_1";

									/**
									 * [tFileOutputJSON_1 process_data_begin ] stop
									 */

									/**
									 * [tFileOutputJSON_1 process_data_end ] start
									 */

									currentComponent = "tFileOutputJSON_1";

									/**
									 * [tFileOutputJSON_1 process_data_end ] stop
									 */

								} // End of branch "clean_customer"

							} // close loop of lookup 'row1' // G_TM_M_043

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row2"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

					}
				} finally {
					if (!((Object) ("C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/addressDimension.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row1 != null) {
					tHash_Lookup_row1.endGet();
				}
				globalMap.remove("tHash_Lookup_row1");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tFileOutputJSON_1 end ] start
				 */

				currentComponent = "tFileOutputJSON_1";

				outtFileOutputJSON_1.print("]}");
				outtFileOutputJSON_1.close();
				globalMap.put("tFileOutputJSON_1_NB_LINE", nb_line_tFileOutputJSON_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "clean_customer");
				}

				ok_Hash.put("tFileOutputJSON_1", true);
				end_Hash.put("tFileOutputJSON_1", System.currentTimeMillis());

				/**
				 * [tFileOutputJSON_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row1");

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tFileOutputJSON_1 finally ] start
				 */

				currentComponent = "tFileOutputJSON_1";

				/**
				 * [tFileOutputJSON_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Seller_address_id_correction = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[0];

		public String seller_id;

		public String getSeller_id() {
			return this.seller_id;
		}

		public Integer seller_zip_code_prefix;

		public Integer getSeller_zip_code_prefix() {
			return this.seller_zip_code_prefix;
		}

		public String seller_state;

		public String getSeller_state() {
			return this.seller_state;
		}

		public String seller_city;

		public String getSeller_city() {
			return this.seller_city;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Seller_address_id_correction.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Seller_address_id_correction.length == 0) {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Seller_address_id_correction.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Seller_address_id_correction.length == 0) {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Seller_address_id_correction = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Seller_address_id_correction, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Seller_address_id_correction) {

				try {

					int length = 0;

					this.seller_id = readString(dis);

					this.seller_zip_code_prefix = readInteger(dis);

					this.seller_state = readString(dis);

					this.seller_city = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Seller_address_id_correction) {

				try {

					int length = 0;

					this.seller_id = readString(dis);

					this.seller_zip_code_prefix = readInteger(dis);

					this.seller_state = readString(dis);

					this.seller_city = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.seller_id, dos);

				// Integer

				writeInteger(this.seller_zip_code_prefix, dos);

				// String

				writeString(this.seller_state, dos);

				// String

				writeString(this.seller_city, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.seller_id, dos);

				// Integer

				writeInteger(this.seller_zip_code_prefix, dos);

				// String

				writeString(this.seller_state, dos);

				// String

				writeString(this.seller_city, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("seller_id=" + seller_id);
			sb.append(",seller_zip_code_prefix=" + String.valueOf(seller_zip_code_prefix));
			sb.append(",seller_state=" + seller_state);
			sb.append(",seller_city=" + seller_city);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputJSON_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputJSON_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();

				/**
				 * [tAdvancedHash_row1 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row1", false);
				start_Hash.put("tAdvancedHash_row1", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tAdvancedHash_row1 = 0;

				// connection name:row1
				// source node:tFileInputJSON_1 - inputs:(after_tFileInputDelimited_1)
				// outputs:(row1,row1) | target node:tAdvancedHash_row1 - inputs:(row1)
				// outputs:()
				// linked node: tMap_1 - inputs:(row2,row1) outputs:(clean_customer)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row1 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_ROWS;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row1Struct> tHash_Lookup_row1 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row1Struct>getLookup(matchingModeEnum_row1);

				globalMap.put("tHash_Lookup_row1", tHash_Lookup_row1);

				/**
				 * [tAdvancedHash_row1 begin ] stop
				 */

				/**
				 * [tFileInputJSON_1 begin ] start
				 */

				ok_Hash.put("tFileInputJSON_1", false);
				start_Hash.put("tFileInputJSON_1", System.currentTimeMillis());

				currentComponent = "tFileInputJSON_1";

				int tos_count_tFileInputJSON_1 = 0;

				class JsonPathCache_tFileInputJSON_1 {
					final java.util.Map<String, com.jayway.jsonpath.JsonPath> jsonPathString2compiledJsonPath = new java.util.HashMap<String, com.jayway.jsonpath.JsonPath>();

					public com.jayway.jsonpath.JsonPath getCompiledJsonPath(String jsonPath) {
						if (jsonPathString2compiledJsonPath.containsKey(jsonPath)) {
							return jsonPathString2compiledJsonPath.get(jsonPath);
						} else {
							com.jayway.jsonpath.JsonPath compiledLoopPath = com.jayway.jsonpath.JsonPath
									.compile(jsonPath);
							jsonPathString2compiledJsonPath.put(jsonPath, compiledLoopPath);
							return compiledLoopPath;
						}
					}
				}

				int nb_line_tFileInputJSON_1 = 0;

				JsonPathCache_tFileInputJSON_1 jsonPathCache_tFileInputJSON_1 = new JsonPathCache_tFileInputJSON_1();

				String loopPath_tFileInputJSON_1 = "$[*]";
				java.util.List<Object> resultset_tFileInputJSON_1 = new java.util.ArrayList<Object>();

				java.io.InputStream is_tFileInputJSON_1 = null;
				com.jayway.jsonpath.ParseContext parseContext_tFileInputJSON_1 = com.jayway.jsonpath.JsonPath
						.using(com.jayway.jsonpath.Configuration.defaultConfiguration());
				Object filenameOrStream_tFileInputJSON_1 = null;
				try {
					filenameOrStream_tFileInputJSON_1 = "C:/Users/adelj/Desktop/dataset/olist_sellers_dataset.json";
				} catch (java.lang.Exception e_tFileInputJSON_1) {
					globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());

					globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
					System.err.println(e_tFileInputJSON_1.getMessage());
				}

				com.jayway.jsonpath.ReadContext document_tFileInputJSON_1 = null;
				try {
					if (filenameOrStream_tFileInputJSON_1 instanceof java.io.InputStream) {
						is_tFileInputJSON_1 = (java.io.InputStream) filenameOrStream_tFileInputJSON_1;
					} else {

						is_tFileInputJSON_1 = new java.io.FileInputStream((String) filenameOrStream_tFileInputJSON_1);

					}

					document_tFileInputJSON_1 = parseContext_tFileInputJSON_1.parse(is_tFileInputJSON_1, "UTF-8");
					com.jayway.jsonpath.JsonPath compiledLoopPath_tFileInputJSON_1 = jsonPathCache_tFileInputJSON_1
							.getCompiledJsonPath(loopPath_tFileInputJSON_1);
					Object result_tFileInputJSON_1 = document_tFileInputJSON_1.read(compiledLoopPath_tFileInputJSON_1,
							net.minidev.json.JSONObject.class);
					if (result_tFileInputJSON_1 instanceof net.minidev.json.JSONArray) {
						resultset_tFileInputJSON_1 = (net.minidev.json.JSONArray) result_tFileInputJSON_1;
					} else {
						resultset_tFileInputJSON_1.add(result_tFileInputJSON_1);
					}
				} catch (java.lang.Exception e_tFileInputJSON_1) {
					globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
					globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
					System.err.println(e_tFileInputJSON_1.getMessage());
				} finally {
					if (is_tFileInputJSON_1 != null) {
						is_tFileInputJSON_1.close();
					}
				}

				String jsonPath_tFileInputJSON_1 = null;
				com.jayway.jsonpath.JsonPath compiledJsonPath_tFileInputJSON_1 = null;

				Object value_tFileInputJSON_1 = null;
				Object root_tFileInputJSON_1 = null;
				for (Object row_tFileInputJSON_1 : resultset_tFileInputJSON_1) {
					nb_line_tFileInputJSON_1++;
					row1 = null;
					row1 = null;
					boolean whetherReject_tFileInputJSON_1 = false;
					row1 = new row1Struct();

					try {
						jsonPath_tFileInputJSON_1 = "seller_id";
						compiledJsonPath_tFileInputJSON_1 = jsonPathCache_tFileInputJSON_1
								.getCompiledJsonPath(jsonPath_tFileInputJSON_1);

						try {

							if (jsonPath_tFileInputJSON_1.startsWith("$")) {
								if (root_tFileInputJSON_1 == null) {
									root_tFileInputJSON_1 = document_tFileInputJSON_1
											.read(jsonPathCache_tFileInputJSON_1.getCompiledJsonPath("$"));
								}
								value_tFileInputJSON_1 = compiledJsonPath_tFileInputJSON_1.read(root_tFileInputJSON_1);
							} else {
								value_tFileInputJSON_1 = compiledJsonPath_tFileInputJSON_1.read(row_tFileInputJSON_1);
							}
							row1.seller_id = value_tFileInputJSON_1 == null ?

									null : value_tFileInputJSON_1.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_1) {
							globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
							row1.seller_id =

									null;
						}
						jsonPath_tFileInputJSON_1 = "seller_zip_code_prefix";
						compiledJsonPath_tFileInputJSON_1 = jsonPathCache_tFileInputJSON_1
								.getCompiledJsonPath(jsonPath_tFileInputJSON_1);

						try {

							if (jsonPath_tFileInputJSON_1.startsWith("$")) {
								if (root_tFileInputJSON_1 == null) {
									root_tFileInputJSON_1 = document_tFileInputJSON_1
											.read(jsonPathCache_tFileInputJSON_1.getCompiledJsonPath("$"));
								}
								value_tFileInputJSON_1 = compiledJsonPath_tFileInputJSON_1.read(root_tFileInputJSON_1);
							} else {
								value_tFileInputJSON_1 = compiledJsonPath_tFileInputJSON_1.read(row_tFileInputJSON_1);
							}
							if (value_tFileInputJSON_1 != null && !value_tFileInputJSON_1.toString().isEmpty()) {
								row1.seller_zip_code_prefix = ParserUtils
										.parseTo_Integer(value_tFileInputJSON_1.toString());
							} else {
								row1.seller_zip_code_prefix =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_1) {
							globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
							row1.seller_zip_code_prefix =

									null;
						}
						jsonPath_tFileInputJSON_1 = "seller_state";
						compiledJsonPath_tFileInputJSON_1 = jsonPathCache_tFileInputJSON_1
								.getCompiledJsonPath(jsonPath_tFileInputJSON_1);

						try {

							if (jsonPath_tFileInputJSON_1.startsWith("$")) {
								if (root_tFileInputJSON_1 == null) {
									root_tFileInputJSON_1 = document_tFileInputJSON_1
											.read(jsonPathCache_tFileInputJSON_1.getCompiledJsonPath("$"));
								}
								value_tFileInputJSON_1 = compiledJsonPath_tFileInputJSON_1.read(root_tFileInputJSON_1);
							} else {
								value_tFileInputJSON_1 = compiledJsonPath_tFileInputJSON_1.read(row_tFileInputJSON_1);
							}
							row1.seller_state = value_tFileInputJSON_1 == null ?

									null : value_tFileInputJSON_1.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_1) {
							globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
							row1.seller_state =

									null;
						}
						jsonPath_tFileInputJSON_1 = "seller_city";
						compiledJsonPath_tFileInputJSON_1 = jsonPathCache_tFileInputJSON_1
								.getCompiledJsonPath(jsonPath_tFileInputJSON_1);

						try {

							if (jsonPath_tFileInputJSON_1.startsWith("$")) {
								if (root_tFileInputJSON_1 == null) {
									root_tFileInputJSON_1 = document_tFileInputJSON_1
											.read(jsonPathCache_tFileInputJSON_1.getCompiledJsonPath("$"));
								}
								value_tFileInputJSON_1 = compiledJsonPath_tFileInputJSON_1.read(root_tFileInputJSON_1);
							} else {
								value_tFileInputJSON_1 = compiledJsonPath_tFileInputJSON_1.read(row_tFileInputJSON_1);
							}
							row1.seller_city = value_tFileInputJSON_1 == null ?

									null : value_tFileInputJSON_1.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_1) {
							globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
							row1.seller_city =

									null;
						}
					} catch (java.lang.Exception e_tFileInputJSON_1) {
						globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
						whetherReject_tFileInputJSON_1 = true;
						System.err.println(e_tFileInputJSON_1.getMessage());
						row1 = null;
						globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
					}
//}

					/**
					 * [tFileInputJSON_1 begin ] stop
					 */

					/**
					 * [tFileInputJSON_1 main ] start
					 */

					currentComponent = "tFileInputJSON_1";

					tos_count_tFileInputJSON_1++;

					/**
					 * [tFileInputJSON_1 main ] stop
					 */

					/**
					 * [tFileInputJSON_1 process_data_begin ] start
					 */

					currentComponent = "tFileInputJSON_1";

					/**
					 * [tFileInputJSON_1 process_data_begin ] stop
					 */
// Start of branch "row1"
					if (row1 != null) {

						/**
						 * [tAdvancedHash_row1 main ] start
						 */

						currentComponent = "tAdvancedHash_row1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row1"

							);
						}

						row1Struct row1_HashRow = new row1Struct();

						row1_HashRow.seller_id = row1.seller_id;

						row1_HashRow.seller_zip_code_prefix = row1.seller_zip_code_prefix;

						row1_HashRow.seller_state = row1.seller_state;

						row1_HashRow.seller_city = row1.seller_city;

						tHash_Lookup_row1.put(row1_HashRow);

						tos_count_tAdvancedHash_row1++;

						/**
						 * [tAdvancedHash_row1 main ] stop
						 */

						/**
						 * [tAdvancedHash_row1 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row1";

						/**
						 * [tAdvancedHash_row1 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row1 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row1";

						/**
						 * [tAdvancedHash_row1 process_data_end ] stop
						 */

					} // End of branch "row1"

					/**
					 * [tFileInputJSON_1 process_data_end ] start
					 */

					currentComponent = "tFileInputJSON_1";

					/**
					 * [tFileInputJSON_1 process_data_end ] stop
					 */

					/**
					 * [tFileInputJSON_1 end ] start
					 */

					currentComponent = "tFileInputJSON_1";

				}
				globalMap.put("tFileInputJSON_1_NB_LINE", nb_line_tFileInputJSON_1);

				ok_Hash.put("tFileInputJSON_1", true);
				end_Hash.put("tFileInputJSON_1", System.currentTimeMillis());

				/**
				 * [tFileInputJSON_1 end ] stop
				 */

				/**
				 * [tAdvancedHash_row1 end ] start
				 */

				currentComponent = "tAdvancedHash_row1";

				tHash_Lookup_row1.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tAdvancedHash_row1", true);
				end_Hash.put("tAdvancedHash_row1", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputJSON_1 finally ] start
				 */

				currentComponent = "tFileInputJSON_1";

				/**
				 * [tFileInputJSON_1 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row1 finally ] start
				 */

				currentComponent = "tAdvancedHash_row1";

				/**
				 * [tAdvancedHash_row1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputJSON_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final Seller_address_id_correction Seller_address_id_correctionClass = new Seller_address_id_correction();

		int exitCode = Seller_address_id_correctionClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = Seller_address_id_correction.class.getClassLoader().getResourceAsStream(
					"projet_bi/seller_address_id_correction_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Seller_address_id_correction.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory)
					+ " bytes memory increase when running : Seller_address_id_correction");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 87413 characters generated by Talend Open Studio for Big Data on the 18
 * janvier 2024, 5:11:43 PM CET
 ************************************************************************************************/