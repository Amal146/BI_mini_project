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

package projet_bi.documents_sorting_0_1;

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
 * Job: documents_sorting Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class documents_sorting implements TalendJob {

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
	private final String jobName = "documents_sorting";
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
					documents_sorting.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(documents_sorting.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFileOutputDelimited_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_7_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputJSON_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputJSON_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputJSON_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputJSON_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSortRow_5_SortOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tSortRow_5_SortIn_error(exception, errorComponent, globalMap);

	}

	public void tSortRow_5_SortIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSortRow_6_SortOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tSortRow_6_SortIn_error(exception, errorComponent, globalMap);

	}

	public void tSortRow_6_SortIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSortRow_4_SortOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tSortRow_4_SortIn_error(exception, errorComponent, globalMap);

	}

	public void tSortRow_4_SortIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSortRow_3_SortOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tSortRow_3_SortIn_error(exception, errorComponent, globalMap);

	}

	public void tSortRow_3_SortIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSortRow_7_SortOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tSortRow_7_SortIn_error(exception, errorComponent, globalMap);

	}

	public void tSortRow_7_SortIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_5_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSortRow_8_SortOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tSortRow_8_SortIn_error(exception, errorComponent, globalMap);

	}

	public void tSortRow_8_SortIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSortRow_1_SortOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tSortRow_1_SortIn_error(exception, errorComponent, globalMap);

	}

	public void tSortRow_1_SortIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSortRow_2_SortOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tSortRow_2_SortIn_error(exception, errorComponent, globalMap);

	}

	public void tSortRow_2_SortIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputJSON_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_4_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_5_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_6_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputJSON_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputJSON_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row10Struct implements routines.system.IPersistableRow<row10Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _geolocation_zip_code_prefix;

		public String get_geolocation_zip_code_prefix() {
			return this._geolocation_zip_code_prefix;
		}

		public Double _geolocation_lat;

		public Double get_geolocation_lat() {
			return this._geolocation_lat;
		}

		public Double _geolocation_lng;

		public Double get_geolocation_lng() {
			return this._geolocation_lng;
		}

		public String _geolocation_city;

		public String get_geolocation_city() {
			return this._geolocation_city;
		}

		public String _geolocation_state;

		public String get_geolocation_state() {
			return this._geolocation_state;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._geolocation_zip_code_prefix == null) ? 0
						: this._geolocation_zip_code_prefix.hashCode());

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
			final row10Struct other = (row10Struct) obj;

			if (this._geolocation_zip_code_prefix == null) {
				if (other._geolocation_zip_code_prefix != null)
					return false;

			} else if (!this._geolocation_zip_code_prefix.equals(other._geolocation_zip_code_prefix))

				return false;

			return true;
		}

		public void copyDataTo(row10Struct other) {

			other._geolocation_zip_code_prefix = this._geolocation_zip_code_prefix;
			other._geolocation_lat = this._geolocation_lat;
			other._geolocation_lng = this._geolocation_lng;
			other._geolocation_city = this._geolocation_city;
			other._geolocation_state = this._geolocation_state;

		}

		public void copyKeysDataTo(row10Struct other) {

			other._geolocation_zip_code_prefix = this._geolocation_zip_code_prefix;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._geolocation_zip_code_prefix = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this._geolocation_lat = null;
					} else {
						this._geolocation_lat = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this._geolocation_lng = null;
					} else {
						this._geolocation_lng = dis.readDouble();
					}

					this._geolocation_city = readString(dis);

					this._geolocation_state = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._geolocation_zip_code_prefix = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this._geolocation_lat = null;
					} else {
						this._geolocation_lat = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this._geolocation_lng = null;
					} else {
						this._geolocation_lng = dis.readDouble();
					}

					this._geolocation_city = readString(dis);

					this._geolocation_state = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._geolocation_zip_code_prefix, dos);

				// Double

				if (this._geolocation_lat == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this._geolocation_lat);
				}

				// Double

				if (this._geolocation_lng == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this._geolocation_lng);
				}

				// String

				writeString(this._geolocation_city, dos);

				// String

				writeString(this._geolocation_state, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._geolocation_zip_code_prefix, dos);

				// Double

				if (this._geolocation_lat == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this._geolocation_lat);
				}

				// Double

				if (this._geolocation_lng == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this._geolocation_lng);
				}

				// String

				writeString(this._geolocation_city, dos);

				// String

				writeString(this._geolocation_state, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_geolocation_zip_code_prefix=" + _geolocation_zip_code_prefix);
			sb.append(",_geolocation_lat=" + String.valueOf(_geolocation_lat));
			sb.append(",_geolocation_lng=" + String.valueOf(_geolocation_lng));
			sb.append(",_geolocation_city=" + _geolocation_city);
			sb.append(",_geolocation_state=" + _geolocation_state);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row10Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._geolocation_zip_code_prefix, other._geolocation_zip_code_prefix);
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

	public static class OnRowsEndStructtSortRow_5
			implements routines.system.IPersistableRow<OnRowsEndStructtSortRow_5> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _geolocation_zip_code_prefix;

		public String get_geolocation_zip_code_prefix() {
			return this._geolocation_zip_code_prefix;
		}

		public Double _geolocation_lat;

		public Double get_geolocation_lat() {
			return this._geolocation_lat;
		}

		public Double _geolocation_lng;

		public Double get_geolocation_lng() {
			return this._geolocation_lng;
		}

		public String _geolocation_city;

		public String get_geolocation_city() {
			return this._geolocation_city;
		}

		public String _geolocation_state;

		public String get_geolocation_state() {
			return this._geolocation_state;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._geolocation_zip_code_prefix == null) ? 0
						: this._geolocation_zip_code_prefix.hashCode());

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
			final OnRowsEndStructtSortRow_5 other = (OnRowsEndStructtSortRow_5) obj;

			if (this._geolocation_zip_code_prefix == null) {
				if (other._geolocation_zip_code_prefix != null)
					return false;

			} else if (!this._geolocation_zip_code_prefix.equals(other._geolocation_zip_code_prefix))

				return false;

			return true;
		}

		public void copyDataTo(OnRowsEndStructtSortRow_5 other) {

			other._geolocation_zip_code_prefix = this._geolocation_zip_code_prefix;
			other._geolocation_lat = this._geolocation_lat;
			other._geolocation_lng = this._geolocation_lng;
			other._geolocation_city = this._geolocation_city;
			other._geolocation_state = this._geolocation_state;

		}

		public void copyKeysDataTo(OnRowsEndStructtSortRow_5 other) {

			other._geolocation_zip_code_prefix = this._geolocation_zip_code_prefix;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._geolocation_zip_code_prefix = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this._geolocation_lat = null;
					} else {
						this._geolocation_lat = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this._geolocation_lng = null;
					} else {
						this._geolocation_lng = dis.readDouble();
					}

					this._geolocation_city = readString(dis);

					this._geolocation_state = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._geolocation_zip_code_prefix = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this._geolocation_lat = null;
					} else {
						this._geolocation_lat = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this._geolocation_lng = null;
					} else {
						this._geolocation_lng = dis.readDouble();
					}

					this._geolocation_city = readString(dis);

					this._geolocation_state = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._geolocation_zip_code_prefix, dos);

				// Double

				if (this._geolocation_lat == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this._geolocation_lat);
				}

				// Double

				if (this._geolocation_lng == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this._geolocation_lng);
				}

				// String

				writeString(this._geolocation_city, dos);

				// String

				writeString(this._geolocation_state, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._geolocation_zip_code_prefix, dos);

				// Double

				if (this._geolocation_lat == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this._geolocation_lat);
				}

				// Double

				if (this._geolocation_lng == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this._geolocation_lng);
				}

				// String

				writeString(this._geolocation_city, dos);

				// String

				writeString(this._geolocation_state, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_geolocation_zip_code_prefix=" + _geolocation_zip_code_prefix);
			sb.append(",_geolocation_lat=" + String.valueOf(_geolocation_lat));
			sb.append(",_geolocation_lng=" + String.valueOf(_geolocation_lng));
			sb.append(",_geolocation_city=" + _geolocation_city);
			sb.append(",_geolocation_state=" + _geolocation_state);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtSortRow_5 other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._geolocation_zip_code_prefix, other._geolocation_zip_code_prefix);
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

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _geolocation_zip_code_prefix;

		public String get_geolocation_zip_code_prefix() {
			return this._geolocation_zip_code_prefix;
		}

		public Double _geolocation_lat;

		public Double get_geolocation_lat() {
			return this._geolocation_lat;
		}

		public Double _geolocation_lng;

		public Double get_geolocation_lng() {
			return this._geolocation_lng;
		}

		public String _geolocation_city;

		public String get_geolocation_city() {
			return this._geolocation_city;
		}

		public String _geolocation_state;

		public String get_geolocation_state() {
			return this._geolocation_state;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._geolocation_zip_code_prefix == null) ? 0
						: this._geolocation_zip_code_prefix.hashCode());

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
			final row9Struct other = (row9Struct) obj;

			if (this._geolocation_zip_code_prefix == null) {
				if (other._geolocation_zip_code_prefix != null)
					return false;

			} else if (!this._geolocation_zip_code_prefix.equals(other._geolocation_zip_code_prefix))

				return false;

			return true;
		}

		public void copyDataTo(row9Struct other) {

			other._geolocation_zip_code_prefix = this._geolocation_zip_code_prefix;
			other._geolocation_lat = this._geolocation_lat;
			other._geolocation_lng = this._geolocation_lng;
			other._geolocation_city = this._geolocation_city;
			other._geolocation_state = this._geolocation_state;

		}

		public void copyKeysDataTo(row9Struct other) {

			other._geolocation_zip_code_prefix = this._geolocation_zip_code_prefix;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._geolocation_zip_code_prefix = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this._geolocation_lat = null;
					} else {
						this._geolocation_lat = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this._geolocation_lng = null;
					} else {
						this._geolocation_lng = dis.readDouble();
					}

					this._geolocation_city = readString(dis);

					this._geolocation_state = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._geolocation_zip_code_prefix = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this._geolocation_lat = null;
					} else {
						this._geolocation_lat = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this._geolocation_lng = null;
					} else {
						this._geolocation_lng = dis.readDouble();
					}

					this._geolocation_city = readString(dis);

					this._geolocation_state = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._geolocation_zip_code_prefix, dos);

				// Double

				if (this._geolocation_lat == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this._geolocation_lat);
				}

				// Double

				if (this._geolocation_lng == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this._geolocation_lng);
				}

				// String

				writeString(this._geolocation_city, dos);

				// String

				writeString(this._geolocation_state, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._geolocation_zip_code_prefix, dos);

				// Double

				if (this._geolocation_lat == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this._geolocation_lat);
				}

				// Double

				if (this._geolocation_lng == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this._geolocation_lng);
				}

				// String

				writeString(this._geolocation_city, dos);

				// String

				writeString(this._geolocation_state, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_geolocation_zip_code_prefix=" + _geolocation_zip_code_prefix);
			sb.append(",_geolocation_lat=" + String.valueOf(_geolocation_lat));
			sb.append(",_geolocation_lng=" + String.valueOf(_geolocation_lng));
			sb.append(",_geolocation_city=" + _geolocation_city);
			sb.append(",_geolocation_state=" + _geolocation_state);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._geolocation_zip_code_prefix, other._geolocation_zip_code_prefix);
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
		String currentVirtualComponent = null;

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

				row9Struct row9 = new row9Struct();
				row10Struct row10 = new row10Struct();

				/**
				 * [tSortRow_5_SortOut begin ] start
				 */

				ok_Hash.put("tSortRow_5_SortOut", false);
				start_Hash.put("tSortRow_5_SortOut", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_5";

				currentComponent = "tSortRow_5_SortOut";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row9");
				}

				int tos_count_tSortRow_5_SortOut = 0;

				class Comparablerow9Struct extends row9Struct implements Comparable<Comparablerow9Struct> {

					public int compareTo(Comparablerow9Struct other) {

						return 0;
					}
				}

				java.util.List<Comparablerow9Struct> list_tSortRow_5_SortOut = new java.util.ArrayList<Comparablerow9Struct>();

				/**
				 * [tSortRow_5_SortOut begin ] stop
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

					Object filename_tFileInputDelimited_1 = "C:/Users/adelj/Desktop/dataset/olist_geolocation_dataset.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/adelj/Desktop/dataset/olist_geolocation_dataset.csv", "UTF-8", ",", "\n",
								false, 1, 0, limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row9 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row9 = new row9Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							row9._geolocation_zip_code_prefix = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 1;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row9._geolocation_lat = ParserUtils.parseTo_Double(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_geolocation_lat", "row9", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row9._geolocation_lat = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 2;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row9._geolocation_lng = ParserUtils.parseTo_Double(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_geolocation_lng", "row9", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row9._geolocation_lng = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row9._geolocation_city = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							row9._geolocation_state = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row9 = null;

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
// Start of branch "row9"
						if (row9 != null) {

							/**
							 * [tSortRow_5_SortOut main ] start
							 */

							currentVirtualComponent = "tSortRow_5";

							currentComponent = "tSortRow_5_SortOut";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row9"

								);
							}

							Comparablerow9Struct arrayRowtSortRow_5_SortOut = new Comparablerow9Struct();

							arrayRowtSortRow_5_SortOut._geolocation_zip_code_prefix = row9._geolocation_zip_code_prefix;
							arrayRowtSortRow_5_SortOut._geolocation_lat = row9._geolocation_lat;
							arrayRowtSortRow_5_SortOut._geolocation_lng = row9._geolocation_lng;
							arrayRowtSortRow_5_SortOut._geolocation_city = row9._geolocation_city;
							arrayRowtSortRow_5_SortOut._geolocation_state = row9._geolocation_state;
							list_tSortRow_5_SortOut.add(arrayRowtSortRow_5_SortOut);

							tos_count_tSortRow_5_SortOut++;

							/**
							 * [tSortRow_5_SortOut main ] stop
							 */

							/**
							 * [tSortRow_5_SortOut process_data_begin ] start
							 */

							currentVirtualComponent = "tSortRow_5";

							currentComponent = "tSortRow_5_SortOut";

							/**
							 * [tSortRow_5_SortOut process_data_begin ] stop
							 */

							/**
							 * [tSortRow_5_SortOut process_data_end ] start
							 */

							currentVirtualComponent = "tSortRow_5";

							currentComponent = "tSortRow_5_SortOut";

							/**
							 * [tSortRow_5_SortOut process_data_end ] stop
							 */

						} // End of branch "row9"

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
					if (!((Object) ("C:/Users/adelj/Desktop/dataset/olist_geolocation_dataset.csv") instanceof java.io.InputStream)) {
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
				 * [tSortRow_5_SortOut end ] start
				 */

				currentVirtualComponent = "tSortRow_5";

				currentComponent = "tSortRow_5_SortOut";

				row9Struct[] array_tSortRow_5_SortOut = list_tSortRow_5_SortOut.toArray(new Comparablerow9Struct[0]);

				java.util.Arrays.sort(array_tSortRow_5_SortOut);

				globalMap.put("tSortRow_5", array_tSortRow_5_SortOut);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row9");
				}

				ok_Hash.put("tSortRow_5_SortOut", true);
				end_Hash.put("tSortRow_5_SortOut", System.currentTimeMillis());

				/**
				 * [tSortRow_5_SortOut end ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_3", false);
				start_Hash.put("tFileOutputDelimited_3", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row10");
				}

				int tos_count_tFileOutputDelimited_3 = 0;

				String fileName_tFileOutputDelimited_3 = "";
				fileName_tFileOutputDelimited_3 = (new java.io.File(
						"C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/out.csv")).getAbsolutePath().replace("\\",
								"/");
				String fullName_tFileOutputDelimited_3 = null;
				String extension_tFileOutputDelimited_3 = null;
				String directory_tFileOutputDelimited_3 = null;
				if ((fileName_tFileOutputDelimited_3.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_3.lastIndexOf(".") < fileName_tFileOutputDelimited_3
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3;
						extension_tFileOutputDelimited_3 = "";
					} else {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
								fileName_tFileOutputDelimited_3.lastIndexOf("."));
						extension_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3
								.substring(fileName_tFileOutputDelimited_3.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
							fileName_tFileOutputDelimited_3.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_3.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
								fileName_tFileOutputDelimited_3.lastIndexOf("."));
						extension_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3
								.substring(fileName_tFileOutputDelimited_3.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3;
						extension_tFileOutputDelimited_3 = "";
					}
					directory_tFileOutputDelimited_3 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_3 = true;
				java.io.File filetFileOutputDelimited_3 = new java.io.File(fileName_tFileOutputDelimited_3);
				globalMap.put("tFileOutputDelimited_3_FILE_NAME", fileName_tFileOutputDelimited_3);
				if (filetFileOutputDelimited_3.exists()) {
					throw new RuntimeException("The particular file \"" + filetFileOutputDelimited_3.getAbsoluteFile()
							+ "\" already exist. If you want to overwrite the file, please uncheck the"
							+ " \"Throw an error if the file already exist\" option in Advanced settings.");
				}
				int nb_line_tFileOutputDelimited_3 = 0;
				int splitedFileNo_tFileOutputDelimited_3 = 0;
				int currentRow_tFileOutputDelimited_3 = 0;

				final String OUT_DELIM_tFileOutputDelimited_3 = /** Start field tFileOutputDelimited_3:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_3:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_3 = /**
																		 * Start field
																		 * tFileOutputDelimited_3:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_3:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_3 != null && directory_tFileOutputDelimited_3.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_3 = new java.io.File(directory_tFileOutputDelimited_3);
					if (!dir_tFileOutputDelimited_3.exists()) {
						dir_tFileOutputDelimited_3.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_3 = null;

				java.io.File fileToDelete_tFileOutputDelimited_3 = new java.io.File(fileName_tFileOutputDelimited_3);
				if (fileToDelete_tFileOutputDelimited_3.exists()) {
					fileToDelete_tFileOutputDelimited_3.delete();
				}
				outtFileOutputDelimited_3 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_3, false), "ISO-8859-15"));

				resourceMap.put("out_tFileOutputDelimited_3", outtFileOutputDelimited_3);
				resourceMap.put("nb_line_tFileOutputDelimited_3", nb_line_tFileOutputDelimited_3);

				/**
				 * [tFileOutputDelimited_3 begin ] stop
				 */

				/**
				 * [tSortRow_5_SortIn begin ] start
				 */

				ok_Hash.put("tSortRow_5_SortIn", false);
				start_Hash.put("tSortRow_5_SortIn", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_5";

				currentComponent = "tSortRow_5_SortIn";

				int tos_count_tSortRow_5_SortIn = 0;

				row9Struct[] array_tSortRow_5_SortIn = (row9Struct[]) globalMap.remove("tSortRow_5");

				int nb_line_tSortRow_5_SortIn = 0;

				row9Struct current_tSortRow_5_SortIn = null;

				for (int i_tSortRow_5_SortIn = 0; i_tSortRow_5_SortIn < array_tSortRow_5_SortIn.length; i_tSortRow_5_SortIn++) {
					current_tSortRow_5_SortIn = array_tSortRow_5_SortIn[i_tSortRow_5_SortIn];
					row10._geolocation_zip_code_prefix = current_tSortRow_5_SortIn._geolocation_zip_code_prefix;
					row10._geolocation_lat = current_tSortRow_5_SortIn._geolocation_lat;
					row10._geolocation_lng = current_tSortRow_5_SortIn._geolocation_lng;
					row10._geolocation_city = current_tSortRow_5_SortIn._geolocation_city;
					row10._geolocation_state = current_tSortRow_5_SortIn._geolocation_state;
					// increase number of line sorted
					nb_line_tSortRow_5_SortIn++;

					/**
					 * [tSortRow_5_SortIn begin ] stop
					 */

					/**
					 * [tSortRow_5_SortIn main ] start
					 */

					currentVirtualComponent = "tSortRow_5";

					currentComponent = "tSortRow_5_SortIn";

					tos_count_tSortRow_5_SortIn++;

					/**
					 * [tSortRow_5_SortIn main ] stop
					 */

					/**
					 * [tSortRow_5_SortIn process_data_begin ] start
					 */

					currentVirtualComponent = "tSortRow_5";

					currentComponent = "tSortRow_5_SortIn";

					/**
					 * [tSortRow_5_SortIn process_data_begin ] stop
					 */

					/**
					 * [tFileOutputDelimited_3 main ] start
					 */

					currentComponent = "tFileOutputDelimited_3";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row10"

						);
					}

					StringBuilder sb_tFileOutputDelimited_3 = new StringBuilder();
					if (row10._geolocation_zip_code_prefix != null) {
						sb_tFileOutputDelimited_3.append(row10._geolocation_zip_code_prefix);
					}
					sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
					if (row10._geolocation_lat != null) {
						sb_tFileOutputDelimited_3.append(row10._geolocation_lat);
					}
					sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
					if (row10._geolocation_lng != null) {
						sb_tFileOutputDelimited_3.append(row10._geolocation_lng);
					}
					sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
					if (row10._geolocation_city != null) {
						sb_tFileOutputDelimited_3.append(row10._geolocation_city);
					}
					sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
					if (row10._geolocation_state != null) {
						sb_tFileOutputDelimited_3.append(row10._geolocation_state);
					}
					sb_tFileOutputDelimited_3.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_3);

					nb_line_tFileOutputDelimited_3++;
					resourceMap.put("nb_line_tFileOutputDelimited_3", nb_line_tFileOutputDelimited_3);

					outtFileOutputDelimited_3.write(sb_tFileOutputDelimited_3.toString());

					tos_count_tFileOutputDelimited_3++;

					/**
					 * [tFileOutputDelimited_3 main ] stop
					 */

					/**
					 * [tFileOutputDelimited_3 process_data_begin ] start
					 */

					currentComponent = "tFileOutputDelimited_3";

					/**
					 * [tFileOutputDelimited_3 process_data_begin ] stop
					 */

					/**
					 * [tFileOutputDelimited_3 process_data_end ] start
					 */

					currentComponent = "tFileOutputDelimited_3";

					/**
					 * [tFileOutputDelimited_3 process_data_end ] stop
					 */

					/**
					 * [tSortRow_5_SortIn process_data_end ] start
					 */

					currentVirtualComponent = "tSortRow_5";

					currentComponent = "tSortRow_5_SortIn";

					/**
					 * [tSortRow_5_SortIn process_data_end ] stop
					 */

					/**
					 * [tSortRow_5_SortIn end ] start
					 */

					currentVirtualComponent = "tSortRow_5";

					currentComponent = "tSortRow_5_SortIn";

				}

				globalMap.put("tSortRow_5_SortIn_NB_LINE", nb_line_tSortRow_5_SortIn);

				ok_Hash.put("tSortRow_5_SortIn", true);
				end_Hash.put("tSortRow_5_SortIn", System.currentTimeMillis());

				/**
				 * [tSortRow_5_SortIn end ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 end ] start
				 */

				currentComponent = "tFileOutputDelimited_3";

				if (outtFileOutputDelimited_3 != null) {
					outtFileOutputDelimited_3.flush();
					outtFileOutputDelimited_3.close();
				}

				globalMap.put("tFileOutputDelimited_3_NB_LINE", nb_line_tFileOutputDelimited_3);
				globalMap.put("tFileOutputDelimited_3_FILE_NAME", fileName_tFileOutputDelimited_3);

				resourceMap.put("finish_tFileOutputDelimited_3", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row10");
				}

				ok_Hash.put("tFileOutputDelimited_3", true);
				end_Hash.put("tFileOutputDelimited_3", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_3 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tSortRow_5_SortIn"
			globalMap.remove("tSortRow_5");

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tSortRow_5_SortOut finally ] start
				 */

				currentVirtualComponent = "tSortRow_5";

				currentComponent = "tSortRow_5_SortOut";

				/**
				 * [tSortRow_5_SortOut finally ] stop
				 */

				/**
				 * [tSortRow_5_SortIn finally ] start
				 */

				currentVirtualComponent = "tSortRow_5";

				currentComponent = "tSortRow_5_SortIn";

				/**
				 * [tSortRow_5_SortIn finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_3";

				if (resourceMap.get("finish_tFileOutputDelimited_3") == null) {

					java.io.Writer outtFileOutputDelimited_3 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_3");
					if (outtFileOutputDelimited_3 != null) {
						outtFileOutputDelimited_3.flush();
						outtFileOutputDelimited_3.close();
					}

				}

				/**
				 * [tFileOutputDelimited_3 finally ] stop
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

	public static class row12Struct implements routines.system.IPersistableRow<row12Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _order_id;

		public String get_order_id() {
			return this._order_id;
		}

		public Integer _order_item_id;

		public Integer get_order_item_id() {
			return this._order_item_id;
		}

		public String _product_id;

		public String get_product_id() {
			return this._product_id;
		}

		public String _seller_id;

		public String get_seller_id() {
			return this._seller_id;
		}

		public java.util.Date _shipping_limit_date;

		public java.util.Date get_shipping_limit_date() {
			return this._shipping_limit_date;
		}

		public Float _price;

		public Float get_price() {
			return this._price;
		}

		public Float _freight_value;

		public Float get_freight_value() {
			return this._freight_value;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._order_id == null) ? 0 : this._order_id.hashCode());

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
			final row12Struct other = (row12Struct) obj;

			if (this._order_id == null) {
				if (other._order_id != null)
					return false;

			} else if (!this._order_id.equals(other._order_id))

				return false;

			return true;
		}

		public void copyDataTo(row12Struct other) {

			other._order_id = this._order_id;
			other._order_item_id = this._order_item_id;
			other._product_id = this._product_id;
			other._seller_id = this._seller_id;
			other._shipping_limit_date = this._shipping_limit_date;
			other._price = this._price;
			other._freight_value = this._freight_value;

		}

		public void copyKeysDataTo(row12Struct other) {

			other._order_id = this._order_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._order_item_id = readInteger(dis);

					this._product_id = readString(dis);

					this._seller_id = readString(dis);

					this._shipping_limit_date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this._price = null;
					} else {
						this._price = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this._freight_value = null;
					} else {
						this._freight_value = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._order_item_id = readInteger(dis);

					this._product_id = readString(dis);

					this._seller_id = readString(dis);

					this._shipping_limit_date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this._price = null;
					} else {
						this._price = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this._freight_value = null;
					} else {
						this._freight_value = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// Integer

				writeInteger(this._order_item_id, dos);

				// String

				writeString(this._product_id, dos);

				// String

				writeString(this._seller_id, dos);

				// java.util.Date

				writeDate(this._shipping_limit_date, dos);

				// Float

				if (this._price == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._price);
				}

				// Float

				if (this._freight_value == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._freight_value);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// Integer

				writeInteger(this._order_item_id, dos);

				// String

				writeString(this._product_id, dos);

				// String

				writeString(this._seller_id, dos);

				// java.util.Date

				writeDate(this._shipping_limit_date, dos);

				// Float

				if (this._price == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._price);
				}

				// Float

				if (this._freight_value == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._freight_value);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_order_id=" + _order_id);
			sb.append(",_order_item_id=" + String.valueOf(_order_item_id));
			sb.append(",_product_id=" + _product_id);
			sb.append(",_seller_id=" + _seller_id);
			sb.append(",_shipping_limit_date=" + String.valueOf(_shipping_limit_date));
			sb.append(",_price=" + String.valueOf(_price));
			sb.append(",_freight_value=" + String.valueOf(_freight_value));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row12Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._order_id, other._order_id);
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

	public static class OnRowsEndStructtSortRow_6
			implements routines.system.IPersistableRow<OnRowsEndStructtSortRow_6> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _order_id;

		public String get_order_id() {
			return this._order_id;
		}

		public Integer _order_item_id;

		public Integer get_order_item_id() {
			return this._order_item_id;
		}

		public String _product_id;

		public String get_product_id() {
			return this._product_id;
		}

		public String _seller_id;

		public String get_seller_id() {
			return this._seller_id;
		}

		public java.util.Date _shipping_limit_date;

		public java.util.Date get_shipping_limit_date() {
			return this._shipping_limit_date;
		}

		public Float _price;

		public Float get_price() {
			return this._price;
		}

		public Float _freight_value;

		public Float get_freight_value() {
			return this._freight_value;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._order_id == null) ? 0 : this._order_id.hashCode());

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
			final OnRowsEndStructtSortRow_6 other = (OnRowsEndStructtSortRow_6) obj;

			if (this._order_id == null) {
				if (other._order_id != null)
					return false;

			} else if (!this._order_id.equals(other._order_id))

				return false;

			return true;
		}

		public void copyDataTo(OnRowsEndStructtSortRow_6 other) {

			other._order_id = this._order_id;
			other._order_item_id = this._order_item_id;
			other._product_id = this._product_id;
			other._seller_id = this._seller_id;
			other._shipping_limit_date = this._shipping_limit_date;
			other._price = this._price;
			other._freight_value = this._freight_value;

		}

		public void copyKeysDataTo(OnRowsEndStructtSortRow_6 other) {

			other._order_id = this._order_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._order_item_id = readInteger(dis);

					this._product_id = readString(dis);

					this._seller_id = readString(dis);

					this._shipping_limit_date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this._price = null;
					} else {
						this._price = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this._freight_value = null;
					} else {
						this._freight_value = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._order_item_id = readInteger(dis);

					this._product_id = readString(dis);

					this._seller_id = readString(dis);

					this._shipping_limit_date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this._price = null;
					} else {
						this._price = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this._freight_value = null;
					} else {
						this._freight_value = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// Integer

				writeInteger(this._order_item_id, dos);

				// String

				writeString(this._product_id, dos);

				// String

				writeString(this._seller_id, dos);

				// java.util.Date

				writeDate(this._shipping_limit_date, dos);

				// Float

				if (this._price == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._price);
				}

				// Float

				if (this._freight_value == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._freight_value);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// Integer

				writeInteger(this._order_item_id, dos);

				// String

				writeString(this._product_id, dos);

				// String

				writeString(this._seller_id, dos);

				// java.util.Date

				writeDate(this._shipping_limit_date, dos);

				// Float

				if (this._price == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._price);
				}

				// Float

				if (this._freight_value == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._freight_value);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_order_id=" + _order_id);
			sb.append(",_order_item_id=" + String.valueOf(_order_item_id));
			sb.append(",_product_id=" + _product_id);
			sb.append(",_seller_id=" + _seller_id);
			sb.append(",_shipping_limit_date=" + String.valueOf(_shipping_limit_date));
			sb.append(",_price=" + String.valueOf(_price));
			sb.append(",_freight_value=" + String.valueOf(_freight_value));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtSortRow_6 other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._order_id, other._order_id);
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

	public static class row11Struct implements routines.system.IPersistableRow<row11Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _order_id;

		public String get_order_id() {
			return this._order_id;
		}

		public Integer _order_item_id;

		public Integer get_order_item_id() {
			return this._order_item_id;
		}

		public String _product_id;

		public String get_product_id() {
			return this._product_id;
		}

		public String _seller_id;

		public String get_seller_id() {
			return this._seller_id;
		}

		public java.util.Date _shipping_limit_date;

		public java.util.Date get_shipping_limit_date() {
			return this._shipping_limit_date;
		}

		public Float _price;

		public Float get_price() {
			return this._price;
		}

		public Float _freight_value;

		public Float get_freight_value() {
			return this._freight_value;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._order_id == null) ? 0 : this._order_id.hashCode());

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
			final row11Struct other = (row11Struct) obj;

			if (this._order_id == null) {
				if (other._order_id != null)
					return false;

			} else if (!this._order_id.equals(other._order_id))

				return false;

			return true;
		}

		public void copyDataTo(row11Struct other) {

			other._order_id = this._order_id;
			other._order_item_id = this._order_item_id;
			other._product_id = this._product_id;
			other._seller_id = this._seller_id;
			other._shipping_limit_date = this._shipping_limit_date;
			other._price = this._price;
			other._freight_value = this._freight_value;

		}

		public void copyKeysDataTo(row11Struct other) {

			other._order_id = this._order_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._order_item_id = readInteger(dis);

					this._product_id = readString(dis);

					this._seller_id = readString(dis);

					this._shipping_limit_date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this._price = null;
					} else {
						this._price = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this._freight_value = null;
					} else {
						this._freight_value = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._order_item_id = readInteger(dis);

					this._product_id = readString(dis);

					this._seller_id = readString(dis);

					this._shipping_limit_date = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this._price = null;
					} else {
						this._price = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this._freight_value = null;
					} else {
						this._freight_value = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// Integer

				writeInteger(this._order_item_id, dos);

				// String

				writeString(this._product_id, dos);

				// String

				writeString(this._seller_id, dos);

				// java.util.Date

				writeDate(this._shipping_limit_date, dos);

				// Float

				if (this._price == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._price);
				}

				// Float

				if (this._freight_value == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._freight_value);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// Integer

				writeInteger(this._order_item_id, dos);

				// String

				writeString(this._product_id, dos);

				// String

				writeString(this._seller_id, dos);

				// java.util.Date

				writeDate(this._shipping_limit_date, dos);

				// Float

				if (this._price == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._price);
				}

				// Float

				if (this._freight_value == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._freight_value);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_order_id=" + _order_id);
			sb.append(",_order_item_id=" + String.valueOf(_order_item_id));
			sb.append(",_product_id=" + _product_id);
			sb.append(",_seller_id=" + _seller_id);
			sb.append(",_shipping_limit_date=" + String.valueOf(_shipping_limit_date));
			sb.append(",_price=" + String.valueOf(_price));
			sb.append(",_freight_value=" + String.valueOf(_freight_value));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row11Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._order_id, other._order_id);
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

	public void tFileInputDelimited_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

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

				row11Struct row11 = new row11Struct();
				row12Struct row12 = new row12Struct();

				/**
				 * [tSortRow_6_SortOut begin ] start
				 */

				ok_Hash.put("tSortRow_6_SortOut", false);
				start_Hash.put("tSortRow_6_SortOut", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_6";

				currentComponent = "tSortRow_6_SortOut";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row11");
				}

				int tos_count_tSortRow_6_SortOut = 0;

				class Comparablerow11Struct extends row11Struct implements Comparable<Comparablerow11Struct> {

					public int compareTo(Comparablerow11Struct other) {

						return 0;
					}
				}

				java.util.List<Comparablerow11Struct> list_tSortRow_6_SortOut = new java.util.ArrayList<Comparablerow11Struct>();

				/**
				 * [tSortRow_6_SortOut begin ] stop
				 */

				/**
				 * [tFileInputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_2", false);
				start_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_2";

				int tos_count_tFileInputDelimited_2 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_2 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_2 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_2 = null;
				int limit_tFileInputDelimited_2 = -1;
				try {

					Object filename_tFileInputDelimited_2 = "C:/Users/adelj/Desktop/dataset/olist_order_items_dataset.csv";
					if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_2 = 0, random_value_tFileInputDelimited_2 = -1;
						if (footer_value_tFileInputDelimited_2 > 0 || random_value_tFileInputDelimited_2 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_2 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/adelj/Desktop/dataset/olist_order_items_dataset.csv", "US-ASCII", ",", "\n",
								false, 1, 0, limit_tFileInputDelimited_2, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_2 != null && fid_tFileInputDelimited_2.nextRecord()) {
						rowstate_tFileInputDelimited_2.reset();

						row11 = null;

						boolean whetherReject_tFileInputDelimited_2 = false;
						row11 = new row11Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_2 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_2 = 0;

							row11._order_id = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 1;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row11._order_item_id = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_order_item_id", "row11", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row11._order_item_id = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 2;

							row11._product_id = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 3;

							row11._seller_id = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 4;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row11._shipping_limit_date = ParserUtils.parseTo_Date(temp, "dd-MM-yyyy");

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_shipping_limit_date", "row11", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row11._shipping_limit_date = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 5;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row11._price = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_price", "row11", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row11._price = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 6;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row11._freight_value = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_freight_value", "row11", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row11._freight_value = null;

							}

							if (rowstate_tFileInputDelimited_2.getException() != null) {
								throw rowstate_tFileInputDelimited_2.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_2 = true;

							System.err.println(e.getMessage());
							row11 = null;

						}

						/**
						 * [tFileInputDelimited_2 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_2 main ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						tos_count_tFileInputDelimited_2++;

						/**
						 * [tFileInputDelimited_2 main ] stop
						 */

						/**
						 * [tFileInputDelimited_2 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_begin ] stop
						 */
// Start of branch "row11"
						if (row11 != null) {

							/**
							 * [tSortRow_6_SortOut main ] start
							 */

							currentVirtualComponent = "tSortRow_6";

							currentComponent = "tSortRow_6_SortOut";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row11"

								);
							}

							Comparablerow11Struct arrayRowtSortRow_6_SortOut = new Comparablerow11Struct();

							arrayRowtSortRow_6_SortOut._order_id = row11._order_id;
							arrayRowtSortRow_6_SortOut._order_item_id = row11._order_item_id;
							arrayRowtSortRow_6_SortOut._product_id = row11._product_id;
							arrayRowtSortRow_6_SortOut._seller_id = row11._seller_id;
							arrayRowtSortRow_6_SortOut._shipping_limit_date = row11._shipping_limit_date;
							arrayRowtSortRow_6_SortOut._price = row11._price;
							arrayRowtSortRow_6_SortOut._freight_value = row11._freight_value;
							list_tSortRow_6_SortOut.add(arrayRowtSortRow_6_SortOut);

							tos_count_tSortRow_6_SortOut++;

							/**
							 * [tSortRow_6_SortOut main ] stop
							 */

							/**
							 * [tSortRow_6_SortOut process_data_begin ] start
							 */

							currentVirtualComponent = "tSortRow_6";

							currentComponent = "tSortRow_6_SortOut";

							/**
							 * [tSortRow_6_SortOut process_data_begin ] stop
							 */

							/**
							 * [tSortRow_6_SortOut process_data_end ] start
							 */

							currentVirtualComponent = "tSortRow_6";

							currentComponent = "tSortRow_6_SortOut";

							/**
							 * [tSortRow_6_SortOut process_data_end ] stop
							 */

						} // End of branch "row11"

						/**
						 * [tFileInputDelimited_2 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_2 end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

					}
				} finally {
					if (!((Object) ("C:/Users/adelj/Desktop/dataset/olist_order_items_dataset.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_2 != null) {
							fid_tFileInputDelimited_2.close();
						}
					}
					if (fid_tFileInputDelimited_2 != null) {
						globalMap.put("tFileInputDelimited_2_NB_LINE", fid_tFileInputDelimited_2.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_2", true);
				end_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_2 end ] stop
				 */

				/**
				 * [tSortRow_6_SortOut end ] start
				 */

				currentVirtualComponent = "tSortRow_6";

				currentComponent = "tSortRow_6_SortOut";

				row11Struct[] array_tSortRow_6_SortOut = list_tSortRow_6_SortOut.toArray(new Comparablerow11Struct[0]);

				java.util.Arrays.sort(array_tSortRow_6_SortOut);

				globalMap.put("tSortRow_6", array_tSortRow_6_SortOut);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row11");
				}

				ok_Hash.put("tSortRow_6_SortOut", true);
				end_Hash.put("tSortRow_6_SortOut", System.currentTimeMillis());

				/**
				 * [tSortRow_6_SortOut end ] stop
				 */

				/**
				 * [tFileOutputDelimited_4 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_4", false);
				start_Hash.put("tFileOutputDelimited_4", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row12");
				}

				int tos_count_tFileOutputDelimited_4 = 0;

				String fileName_tFileOutputDelimited_4 = "";
				fileName_tFileOutputDelimited_4 = (new java.io.File(
						"C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/out.csv")).getAbsolutePath().replace("\\",
								"/");
				String fullName_tFileOutputDelimited_4 = null;
				String extension_tFileOutputDelimited_4 = null;
				String directory_tFileOutputDelimited_4 = null;
				if ((fileName_tFileOutputDelimited_4.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_4.lastIndexOf(".") < fileName_tFileOutputDelimited_4
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4;
						extension_tFileOutputDelimited_4 = "";
					} else {
						fullName_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4.substring(0,
								fileName_tFileOutputDelimited_4.lastIndexOf("."));
						extension_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4
								.substring(fileName_tFileOutputDelimited_4.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4.substring(0,
							fileName_tFileOutputDelimited_4.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_4.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4.substring(0,
								fileName_tFileOutputDelimited_4.lastIndexOf("."));
						extension_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4
								.substring(fileName_tFileOutputDelimited_4.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4;
						extension_tFileOutputDelimited_4 = "";
					}
					directory_tFileOutputDelimited_4 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_4 = true;
				java.io.File filetFileOutputDelimited_4 = new java.io.File(fileName_tFileOutputDelimited_4);
				globalMap.put("tFileOutputDelimited_4_FILE_NAME", fileName_tFileOutputDelimited_4);
				if (filetFileOutputDelimited_4.exists()) {
					throw new RuntimeException("The particular file \"" + filetFileOutputDelimited_4.getAbsoluteFile()
							+ "\" already exist. If you want to overwrite the file, please uncheck the"
							+ " \"Throw an error if the file already exist\" option in Advanced settings.");
				}
				int nb_line_tFileOutputDelimited_4 = 0;
				int splitedFileNo_tFileOutputDelimited_4 = 0;
				int currentRow_tFileOutputDelimited_4 = 0;

				final String OUT_DELIM_tFileOutputDelimited_4 = /** Start field tFileOutputDelimited_4:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_4:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_4 = /**
																		 * Start field
																		 * tFileOutputDelimited_4:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_4:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_4 != null && directory_tFileOutputDelimited_4.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_4 = new java.io.File(directory_tFileOutputDelimited_4);
					if (!dir_tFileOutputDelimited_4.exists()) {
						dir_tFileOutputDelimited_4.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_4 = null;

				java.io.File fileToDelete_tFileOutputDelimited_4 = new java.io.File(fileName_tFileOutputDelimited_4);
				if (fileToDelete_tFileOutputDelimited_4.exists()) {
					fileToDelete_tFileOutputDelimited_4.delete();
				}
				outtFileOutputDelimited_4 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_4, false), "ISO-8859-15"));

				resourceMap.put("out_tFileOutputDelimited_4", outtFileOutputDelimited_4);
				resourceMap.put("nb_line_tFileOutputDelimited_4", nb_line_tFileOutputDelimited_4);

				/**
				 * [tFileOutputDelimited_4 begin ] stop
				 */

				/**
				 * [tSortRow_6_SortIn begin ] start
				 */

				ok_Hash.put("tSortRow_6_SortIn", false);
				start_Hash.put("tSortRow_6_SortIn", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_6";

				currentComponent = "tSortRow_6_SortIn";

				int tos_count_tSortRow_6_SortIn = 0;

				row11Struct[] array_tSortRow_6_SortIn = (row11Struct[]) globalMap.remove("tSortRow_6");

				int nb_line_tSortRow_6_SortIn = 0;

				row11Struct current_tSortRow_6_SortIn = null;

				for (int i_tSortRow_6_SortIn = 0; i_tSortRow_6_SortIn < array_tSortRow_6_SortIn.length; i_tSortRow_6_SortIn++) {
					current_tSortRow_6_SortIn = array_tSortRow_6_SortIn[i_tSortRow_6_SortIn];
					row12._order_id = current_tSortRow_6_SortIn._order_id;
					row12._order_item_id = current_tSortRow_6_SortIn._order_item_id;
					row12._product_id = current_tSortRow_6_SortIn._product_id;
					row12._seller_id = current_tSortRow_6_SortIn._seller_id;
					row12._shipping_limit_date = current_tSortRow_6_SortIn._shipping_limit_date;
					row12._price = current_tSortRow_6_SortIn._price;
					row12._freight_value = current_tSortRow_6_SortIn._freight_value;
					// increase number of line sorted
					nb_line_tSortRow_6_SortIn++;

					/**
					 * [tSortRow_6_SortIn begin ] stop
					 */

					/**
					 * [tSortRow_6_SortIn main ] start
					 */

					currentVirtualComponent = "tSortRow_6";

					currentComponent = "tSortRow_6_SortIn";

					tos_count_tSortRow_6_SortIn++;

					/**
					 * [tSortRow_6_SortIn main ] stop
					 */

					/**
					 * [tSortRow_6_SortIn process_data_begin ] start
					 */

					currentVirtualComponent = "tSortRow_6";

					currentComponent = "tSortRow_6_SortIn";

					/**
					 * [tSortRow_6_SortIn process_data_begin ] stop
					 */

					/**
					 * [tFileOutputDelimited_4 main ] start
					 */

					currentComponent = "tFileOutputDelimited_4";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row12"

						);
					}

					StringBuilder sb_tFileOutputDelimited_4 = new StringBuilder();
					if (row12._order_id != null) {
						sb_tFileOutputDelimited_4.append(row12._order_id);
					}
					sb_tFileOutputDelimited_4.append(OUT_DELIM_tFileOutputDelimited_4);
					if (row12._order_item_id != null) {
						sb_tFileOutputDelimited_4.append(row12._order_item_id);
					}
					sb_tFileOutputDelimited_4.append(OUT_DELIM_tFileOutputDelimited_4);
					if (row12._product_id != null) {
						sb_tFileOutputDelimited_4.append(row12._product_id);
					}
					sb_tFileOutputDelimited_4.append(OUT_DELIM_tFileOutputDelimited_4);
					if (row12._seller_id != null) {
						sb_tFileOutputDelimited_4.append(row12._seller_id);
					}
					sb_tFileOutputDelimited_4.append(OUT_DELIM_tFileOutputDelimited_4);
					if (row12._shipping_limit_date != null) {
						sb_tFileOutputDelimited_4
								.append(FormatterUtils.format_Date(row12._shipping_limit_date, "dd-MM-yyyy"));
					}
					sb_tFileOutputDelimited_4.append(OUT_DELIM_tFileOutputDelimited_4);
					if (row12._price != null) {
						sb_tFileOutputDelimited_4.append(row12._price);
					}
					sb_tFileOutputDelimited_4.append(OUT_DELIM_tFileOutputDelimited_4);
					if (row12._freight_value != null) {
						sb_tFileOutputDelimited_4.append(row12._freight_value);
					}
					sb_tFileOutputDelimited_4.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_4);

					nb_line_tFileOutputDelimited_4++;
					resourceMap.put("nb_line_tFileOutputDelimited_4", nb_line_tFileOutputDelimited_4);

					outtFileOutputDelimited_4.write(sb_tFileOutputDelimited_4.toString());

					tos_count_tFileOutputDelimited_4++;

					/**
					 * [tFileOutputDelimited_4 main ] stop
					 */

					/**
					 * [tFileOutputDelimited_4 process_data_begin ] start
					 */

					currentComponent = "tFileOutputDelimited_4";

					/**
					 * [tFileOutputDelimited_4 process_data_begin ] stop
					 */

					/**
					 * [tFileOutputDelimited_4 process_data_end ] start
					 */

					currentComponent = "tFileOutputDelimited_4";

					/**
					 * [tFileOutputDelimited_4 process_data_end ] stop
					 */

					/**
					 * [tSortRow_6_SortIn process_data_end ] start
					 */

					currentVirtualComponent = "tSortRow_6";

					currentComponent = "tSortRow_6_SortIn";

					/**
					 * [tSortRow_6_SortIn process_data_end ] stop
					 */

					/**
					 * [tSortRow_6_SortIn end ] start
					 */

					currentVirtualComponent = "tSortRow_6";

					currentComponent = "tSortRow_6_SortIn";

				}

				globalMap.put("tSortRow_6_SortIn_NB_LINE", nb_line_tSortRow_6_SortIn);

				ok_Hash.put("tSortRow_6_SortIn", true);
				end_Hash.put("tSortRow_6_SortIn", System.currentTimeMillis());

				/**
				 * [tSortRow_6_SortIn end ] stop
				 */

				/**
				 * [tFileOutputDelimited_4 end ] start
				 */

				currentComponent = "tFileOutputDelimited_4";

				if (outtFileOutputDelimited_4 != null) {
					outtFileOutputDelimited_4.flush();
					outtFileOutputDelimited_4.close();
				}

				globalMap.put("tFileOutputDelimited_4_NB_LINE", nb_line_tFileOutputDelimited_4);
				globalMap.put("tFileOutputDelimited_4_FILE_NAME", fileName_tFileOutputDelimited_4);

				resourceMap.put("finish_tFileOutputDelimited_4", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row12");
				}

				ok_Hash.put("tFileOutputDelimited_4", true);
				end_Hash.put("tFileOutputDelimited_4", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_4 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tSortRow_6_SortIn"
			globalMap.remove("tSortRow_6");

			try {

				/**
				 * [tFileInputDelimited_2 finally ] start
				 */

				currentComponent = "tFileInputDelimited_2";

				/**
				 * [tFileInputDelimited_2 finally ] stop
				 */

				/**
				 * [tSortRow_6_SortOut finally ] start
				 */

				currentVirtualComponent = "tSortRow_6";

				currentComponent = "tSortRow_6_SortOut";

				/**
				 * [tSortRow_6_SortOut finally ] stop
				 */

				/**
				 * [tSortRow_6_SortIn finally ] start
				 */

				currentVirtualComponent = "tSortRow_6";

				currentComponent = "tSortRow_6_SortIn";

				/**
				 * [tSortRow_6_SortIn finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_4 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_4";

				if (resourceMap.get("finish_tFileOutputDelimited_4") == null) {

					java.io.Writer outtFileOutputDelimited_4 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_4");
					if (outtFileOutputDelimited_4 != null) {
						outtFileOutputDelimited_4.flush();
						outtFileOutputDelimited_4.close();
					}

				}

				/**
				 * [tFileOutputDelimited_4 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 1);
	}

	public static class row8Struct implements routines.system.IPersistableRow<row8Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _order_id;

		public String get_order_id() {
			return this._order_id;
		}

		public Integer _payment_sequential;

		public Integer get_payment_sequential() {
			return this._payment_sequential;
		}

		public String _payment_type;

		public String get_payment_type() {
			return this._payment_type;
		}

		public Integer _payment_installments;

		public Integer get_payment_installments() {
			return this._payment_installments;
		}

		public Float _payment_value;

		public Float get_payment_value() {
			return this._payment_value;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._order_id == null) ? 0 : this._order_id.hashCode());

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
			final row8Struct other = (row8Struct) obj;

			if (this._order_id == null) {
				if (other._order_id != null)
					return false;

			} else if (!this._order_id.equals(other._order_id))

				return false;

			return true;
		}

		public void copyDataTo(row8Struct other) {

			other._order_id = this._order_id;
			other._payment_sequential = this._payment_sequential;
			other._payment_type = this._payment_type;
			other._payment_installments = this._payment_installments;
			other._payment_value = this._payment_value;

		}

		public void copyKeysDataTo(row8Struct other) {

			other._order_id = this._order_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._payment_sequential = readInteger(dis);

					this._payment_type = readString(dis);

					this._payment_installments = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this._payment_value = null;
					} else {
						this._payment_value = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._payment_sequential = readInteger(dis);

					this._payment_type = readString(dis);

					this._payment_installments = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this._payment_value = null;
					} else {
						this._payment_value = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// Integer

				writeInteger(this._payment_sequential, dos);

				// String

				writeString(this._payment_type, dos);

				// Integer

				writeInteger(this._payment_installments, dos);

				// Float

				if (this._payment_value == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._payment_value);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// Integer

				writeInteger(this._payment_sequential, dos);

				// String

				writeString(this._payment_type, dos);

				// Integer

				writeInteger(this._payment_installments, dos);

				// Float

				if (this._payment_value == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._payment_value);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_order_id=" + _order_id);
			sb.append(",_payment_sequential=" + String.valueOf(_payment_sequential));
			sb.append(",_payment_type=" + _payment_type);
			sb.append(",_payment_installments=" + String.valueOf(_payment_installments));
			sb.append(",_payment_value=" + String.valueOf(_payment_value));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._order_id, other._order_id);
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

	public static class OnRowsEndStructtSortRow_4
			implements routines.system.IPersistableRow<OnRowsEndStructtSortRow_4> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _order_id;

		public String get_order_id() {
			return this._order_id;
		}

		public Integer _payment_sequential;

		public Integer get_payment_sequential() {
			return this._payment_sequential;
		}

		public String _payment_type;

		public String get_payment_type() {
			return this._payment_type;
		}

		public Integer _payment_installments;

		public Integer get_payment_installments() {
			return this._payment_installments;
		}

		public Float _payment_value;

		public Float get_payment_value() {
			return this._payment_value;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._order_id == null) ? 0 : this._order_id.hashCode());

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
			final OnRowsEndStructtSortRow_4 other = (OnRowsEndStructtSortRow_4) obj;

			if (this._order_id == null) {
				if (other._order_id != null)
					return false;

			} else if (!this._order_id.equals(other._order_id))

				return false;

			return true;
		}

		public void copyDataTo(OnRowsEndStructtSortRow_4 other) {

			other._order_id = this._order_id;
			other._payment_sequential = this._payment_sequential;
			other._payment_type = this._payment_type;
			other._payment_installments = this._payment_installments;
			other._payment_value = this._payment_value;

		}

		public void copyKeysDataTo(OnRowsEndStructtSortRow_4 other) {

			other._order_id = this._order_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._payment_sequential = readInteger(dis);

					this._payment_type = readString(dis);

					this._payment_installments = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this._payment_value = null;
					} else {
						this._payment_value = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._payment_sequential = readInteger(dis);

					this._payment_type = readString(dis);

					this._payment_installments = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this._payment_value = null;
					} else {
						this._payment_value = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// Integer

				writeInteger(this._payment_sequential, dos);

				// String

				writeString(this._payment_type, dos);

				// Integer

				writeInteger(this._payment_installments, dos);

				// Float

				if (this._payment_value == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._payment_value);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// Integer

				writeInteger(this._payment_sequential, dos);

				// String

				writeString(this._payment_type, dos);

				// Integer

				writeInteger(this._payment_installments, dos);

				// Float

				if (this._payment_value == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._payment_value);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_order_id=" + _order_id);
			sb.append(",_payment_sequential=" + String.valueOf(_payment_sequential));
			sb.append(",_payment_type=" + _payment_type);
			sb.append(",_payment_installments=" + String.valueOf(_payment_installments));
			sb.append(",_payment_value=" + String.valueOf(_payment_value));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtSortRow_4 other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._order_id, other._order_id);
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

	public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _order_id;

		public String get_order_id() {
			return this._order_id;
		}

		public Integer _payment_sequential;

		public Integer get_payment_sequential() {
			return this._payment_sequential;
		}

		public String _payment_type;

		public String get_payment_type() {
			return this._payment_type;
		}

		public Integer _payment_installments;

		public Integer get_payment_installments() {
			return this._payment_installments;
		}

		public Float _payment_value;

		public Float get_payment_value() {
			return this._payment_value;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._order_id == null) ? 0 : this._order_id.hashCode());

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
			final row7Struct other = (row7Struct) obj;

			if (this._order_id == null) {
				if (other._order_id != null)
					return false;

			} else if (!this._order_id.equals(other._order_id))

				return false;

			return true;
		}

		public void copyDataTo(row7Struct other) {

			other._order_id = this._order_id;
			other._payment_sequential = this._payment_sequential;
			other._payment_type = this._payment_type;
			other._payment_installments = this._payment_installments;
			other._payment_value = this._payment_value;

		}

		public void copyKeysDataTo(row7Struct other) {

			other._order_id = this._order_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._payment_sequential = readInteger(dis);

					this._payment_type = readString(dis);

					this._payment_installments = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this._payment_value = null;
					} else {
						this._payment_value = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._payment_sequential = readInteger(dis);

					this._payment_type = readString(dis);

					this._payment_installments = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this._payment_value = null;
					} else {
						this._payment_value = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// Integer

				writeInteger(this._payment_sequential, dos);

				// String

				writeString(this._payment_type, dos);

				// Integer

				writeInteger(this._payment_installments, dos);

				// Float

				if (this._payment_value == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._payment_value);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// Integer

				writeInteger(this._payment_sequential, dos);

				// String

				writeString(this._payment_type, dos);

				// Integer

				writeInteger(this._payment_installments, dos);

				// Float

				if (this._payment_value == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this._payment_value);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_order_id=" + _order_id);
			sb.append(",_payment_sequential=" + String.valueOf(_payment_sequential));
			sb.append(",_payment_type=" + _payment_type);
			sb.append(",_payment_installments=" + String.valueOf(_payment_installments));
			sb.append(",_payment_value=" + String.valueOf(_payment_value));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._order_id, other._order_id);
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

	public void tFileInputDelimited_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

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

				row7Struct row7 = new row7Struct();
				row8Struct row8 = new row8Struct();

				/**
				 * [tSortRow_4_SortOut begin ] start
				 */

				ok_Hash.put("tSortRow_4_SortOut", false);
				start_Hash.put("tSortRow_4_SortOut", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_4";

				currentComponent = "tSortRow_4_SortOut";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row7");
				}

				int tos_count_tSortRow_4_SortOut = 0;

				class Comparablerow7Struct extends row7Struct implements Comparable<Comparablerow7Struct> {

					public int compareTo(Comparablerow7Struct other) {

						return 0;
					}
				}

				java.util.List<Comparablerow7Struct> list_tSortRow_4_SortOut = new java.util.ArrayList<Comparablerow7Struct>();

				/**
				 * [tSortRow_4_SortOut begin ] stop
				 */

				/**
				 * [tFileInputDelimited_3 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_3", false);
				start_Hash.put("tFileInputDelimited_3", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_3";

				int tos_count_tFileInputDelimited_3 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_3 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_3 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_3 = null;
				int limit_tFileInputDelimited_3 = -1;
				try {

					Object filename_tFileInputDelimited_3 = "C:/Users/adelj/Desktop/dataset/olist_order_payments_dataset.csv";
					if (filename_tFileInputDelimited_3 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_3 = 0, random_value_tFileInputDelimited_3 = -1;
						if (footer_value_tFileInputDelimited_3 > 0 || random_value_tFileInputDelimited_3 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_3 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/adelj/Desktop/dataset/olist_order_payments_dataset.csv", "US-ASCII", ",",
								"\n", false, 1, 0, limit_tFileInputDelimited_3, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_3 != null && fid_tFileInputDelimited_3.nextRecord()) {
						rowstate_tFileInputDelimited_3.reset();

						row7 = null;

						boolean whetherReject_tFileInputDelimited_3 = false;
						row7 = new row7Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_3 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_3 = 0;

							row7._order_id = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 1;

							temp = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									row7._payment_sequential = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
											ex_tFileInputDelimited_3.getMessage());
									rowstate_tFileInputDelimited_3.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_payment_sequential", "row7", temp, ex_tFileInputDelimited_3),
											ex_tFileInputDelimited_3));
								}

							} else {

								row7._payment_sequential = null;

							}

							columnIndexWithD_tFileInputDelimited_3 = 2;

							row7._payment_type = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 3;

							temp = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									row7._payment_installments = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
											ex_tFileInputDelimited_3.getMessage());
									rowstate_tFileInputDelimited_3.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_payment_installments", "row7", temp, ex_tFileInputDelimited_3),
											ex_tFileInputDelimited_3));
								}

							} else {

								row7._payment_installments = null;

							}

							columnIndexWithD_tFileInputDelimited_3 = 4;

							temp = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									row7._payment_value = ParserUtils.parseTo_Float(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
											ex_tFileInputDelimited_3.getMessage());
									rowstate_tFileInputDelimited_3.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_payment_value", "row7", temp, ex_tFileInputDelimited_3),
											ex_tFileInputDelimited_3));
								}

							} else {

								row7._payment_value = null;

							}

							if (rowstate_tFileInputDelimited_3.getException() != null) {
								throw rowstate_tFileInputDelimited_3.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_3 = true;

							System.err.println(e.getMessage());
							row7 = null;

						}

						/**
						 * [tFileInputDelimited_3 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_3 main ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						tos_count_tFileInputDelimited_3++;

						/**
						 * [tFileInputDelimited_3 main ] stop
						 */

						/**
						 * [tFileInputDelimited_3 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						/**
						 * [tFileInputDelimited_3 process_data_begin ] stop
						 */
// Start of branch "row7"
						if (row7 != null) {

							/**
							 * [tSortRow_4_SortOut main ] start
							 */

							currentVirtualComponent = "tSortRow_4";

							currentComponent = "tSortRow_4_SortOut";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row7"

								);
							}

							Comparablerow7Struct arrayRowtSortRow_4_SortOut = new Comparablerow7Struct();

							arrayRowtSortRow_4_SortOut._order_id = row7._order_id;
							arrayRowtSortRow_4_SortOut._payment_sequential = row7._payment_sequential;
							arrayRowtSortRow_4_SortOut._payment_type = row7._payment_type;
							arrayRowtSortRow_4_SortOut._payment_installments = row7._payment_installments;
							arrayRowtSortRow_4_SortOut._payment_value = row7._payment_value;
							list_tSortRow_4_SortOut.add(arrayRowtSortRow_4_SortOut);

							tos_count_tSortRow_4_SortOut++;

							/**
							 * [tSortRow_4_SortOut main ] stop
							 */

							/**
							 * [tSortRow_4_SortOut process_data_begin ] start
							 */

							currentVirtualComponent = "tSortRow_4";

							currentComponent = "tSortRow_4_SortOut";

							/**
							 * [tSortRow_4_SortOut process_data_begin ] stop
							 */

							/**
							 * [tSortRow_4_SortOut process_data_end ] start
							 */

							currentVirtualComponent = "tSortRow_4";

							currentComponent = "tSortRow_4_SortOut";

							/**
							 * [tSortRow_4_SortOut process_data_end ] stop
							 */

						} // End of branch "row7"

						/**
						 * [tFileInputDelimited_3 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						/**
						 * [tFileInputDelimited_3 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_3 end ] start
						 */

						currentComponent = "tFileInputDelimited_3";

					}
				} finally {
					if (!((Object) ("C:/Users/adelj/Desktop/dataset/olist_order_payments_dataset.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_3 != null) {
							fid_tFileInputDelimited_3.close();
						}
					}
					if (fid_tFileInputDelimited_3 != null) {
						globalMap.put("tFileInputDelimited_3_NB_LINE", fid_tFileInputDelimited_3.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_3", true);
				end_Hash.put("tFileInputDelimited_3", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_3 end ] stop
				 */

				/**
				 * [tSortRow_4_SortOut end ] start
				 */

				currentVirtualComponent = "tSortRow_4";

				currentComponent = "tSortRow_4_SortOut";

				row7Struct[] array_tSortRow_4_SortOut = list_tSortRow_4_SortOut.toArray(new Comparablerow7Struct[0]);

				java.util.Arrays.sort(array_tSortRow_4_SortOut);

				globalMap.put("tSortRow_4", array_tSortRow_4_SortOut);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row7");
				}

				ok_Hash.put("tSortRow_4_SortOut", true);
				end_Hash.put("tSortRow_4_SortOut", System.currentTimeMillis());

				/**
				 * [tSortRow_4_SortOut end ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_2", false);
				start_Hash.put("tFileOutputDelimited_2", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row8");
				}

				int tos_count_tFileOutputDelimited_2 = 0;

				String fileName_tFileOutputDelimited_2 = "";
				fileName_tFileOutputDelimited_2 = (new java.io.File(
						"C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/out.csv")).getAbsolutePath().replace("\\",
								"/");
				String fullName_tFileOutputDelimited_2 = null;
				String extension_tFileOutputDelimited_2 = null;
				String directory_tFileOutputDelimited_2 = null;
				if ((fileName_tFileOutputDelimited_2.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") < fileName_tFileOutputDelimited_2
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
								fileName_tFileOutputDelimited_2.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
							fileName_tFileOutputDelimited_2.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
								fileName_tFileOutputDelimited_2.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					}
					directory_tFileOutputDelimited_2 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_2 = true;
				java.io.File filetFileOutputDelimited_2 = new java.io.File(fileName_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME", fileName_tFileOutputDelimited_2);
				if (filetFileOutputDelimited_2.exists()) {
					throw new RuntimeException("The particular file \"" + filetFileOutputDelimited_2.getAbsoluteFile()
							+ "\" already exist. If you want to overwrite the file, please uncheck the"
							+ " \"Throw an error if the file already exist\" option in Advanced settings.");
				}
				int nb_line_tFileOutputDelimited_2 = 0;
				int splitedFileNo_tFileOutputDelimited_2 = 0;
				int currentRow_tFileOutputDelimited_2 = 0;

				final String OUT_DELIM_tFileOutputDelimited_2 = /** Start field tFileOutputDelimited_2:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_2:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_2 = /**
																		 * Start field
																		 * tFileOutputDelimited_2:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_2:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_2 != null && directory_tFileOutputDelimited_2.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_2 = new java.io.File(directory_tFileOutputDelimited_2);
					if (!dir_tFileOutputDelimited_2.exists()) {
						dir_tFileOutputDelimited_2.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_2 = null;

				java.io.File fileToDelete_tFileOutputDelimited_2 = new java.io.File(fileName_tFileOutputDelimited_2);
				if (fileToDelete_tFileOutputDelimited_2.exists()) {
					fileToDelete_tFileOutputDelimited_2.delete();
				}
				outtFileOutputDelimited_2 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_2, false), "ISO-8859-15"));

				resourceMap.put("out_tFileOutputDelimited_2", outtFileOutputDelimited_2);
				resourceMap.put("nb_line_tFileOutputDelimited_2", nb_line_tFileOutputDelimited_2);

				/**
				 * [tFileOutputDelimited_2 begin ] stop
				 */

				/**
				 * [tSortRow_4_SortIn begin ] start
				 */

				ok_Hash.put("tSortRow_4_SortIn", false);
				start_Hash.put("tSortRow_4_SortIn", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_4";

				currentComponent = "tSortRow_4_SortIn";

				int tos_count_tSortRow_4_SortIn = 0;

				row7Struct[] array_tSortRow_4_SortIn = (row7Struct[]) globalMap.remove("tSortRow_4");

				int nb_line_tSortRow_4_SortIn = 0;

				row7Struct current_tSortRow_4_SortIn = null;

				for (int i_tSortRow_4_SortIn = 0; i_tSortRow_4_SortIn < array_tSortRow_4_SortIn.length; i_tSortRow_4_SortIn++) {
					current_tSortRow_4_SortIn = array_tSortRow_4_SortIn[i_tSortRow_4_SortIn];
					row8._order_id = current_tSortRow_4_SortIn._order_id;
					row8._payment_sequential = current_tSortRow_4_SortIn._payment_sequential;
					row8._payment_type = current_tSortRow_4_SortIn._payment_type;
					row8._payment_installments = current_tSortRow_4_SortIn._payment_installments;
					row8._payment_value = current_tSortRow_4_SortIn._payment_value;
					// increase number of line sorted
					nb_line_tSortRow_4_SortIn++;

					/**
					 * [tSortRow_4_SortIn begin ] stop
					 */

					/**
					 * [tSortRow_4_SortIn main ] start
					 */

					currentVirtualComponent = "tSortRow_4";

					currentComponent = "tSortRow_4_SortIn";

					tos_count_tSortRow_4_SortIn++;

					/**
					 * [tSortRow_4_SortIn main ] stop
					 */

					/**
					 * [tSortRow_4_SortIn process_data_begin ] start
					 */

					currentVirtualComponent = "tSortRow_4";

					currentComponent = "tSortRow_4_SortIn";

					/**
					 * [tSortRow_4_SortIn process_data_begin ] stop
					 */

					/**
					 * [tFileOutputDelimited_2 main ] start
					 */

					currentComponent = "tFileOutputDelimited_2";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row8"

						);
					}

					StringBuilder sb_tFileOutputDelimited_2 = new StringBuilder();
					if (row8._order_id != null) {
						sb_tFileOutputDelimited_2.append(row8._order_id);
					}
					sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
					if (row8._payment_sequential != null) {
						sb_tFileOutputDelimited_2.append(row8._payment_sequential);
					}
					sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
					if (row8._payment_type != null) {
						sb_tFileOutputDelimited_2.append(row8._payment_type);
					}
					sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
					if (row8._payment_installments != null) {
						sb_tFileOutputDelimited_2.append(row8._payment_installments);
					}
					sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
					if (row8._payment_value != null) {
						sb_tFileOutputDelimited_2.append(row8._payment_value);
					}
					sb_tFileOutputDelimited_2.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_2);

					nb_line_tFileOutputDelimited_2++;
					resourceMap.put("nb_line_tFileOutputDelimited_2", nb_line_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write(sb_tFileOutputDelimited_2.toString());

					tos_count_tFileOutputDelimited_2++;

					/**
					 * [tFileOutputDelimited_2 main ] stop
					 */

					/**
					 * [tFileOutputDelimited_2 process_data_begin ] start
					 */

					currentComponent = "tFileOutputDelimited_2";

					/**
					 * [tFileOutputDelimited_2 process_data_begin ] stop
					 */

					/**
					 * [tFileOutputDelimited_2 process_data_end ] start
					 */

					currentComponent = "tFileOutputDelimited_2";

					/**
					 * [tFileOutputDelimited_2 process_data_end ] stop
					 */

					/**
					 * [tSortRow_4_SortIn process_data_end ] start
					 */

					currentVirtualComponent = "tSortRow_4";

					currentComponent = "tSortRow_4_SortIn";

					/**
					 * [tSortRow_4_SortIn process_data_end ] stop
					 */

					/**
					 * [tSortRow_4_SortIn end ] start
					 */

					currentVirtualComponent = "tSortRow_4";

					currentComponent = "tSortRow_4_SortIn";

				}

				globalMap.put("tSortRow_4_SortIn_NB_LINE", nb_line_tSortRow_4_SortIn);

				ok_Hash.put("tSortRow_4_SortIn", true);
				end_Hash.put("tSortRow_4_SortIn", System.currentTimeMillis());

				/**
				 * [tSortRow_4_SortIn end ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 end ] start
				 */

				currentComponent = "tFileOutputDelimited_2";

				if (outtFileOutputDelimited_2 != null) {
					outtFileOutputDelimited_2.flush();
					outtFileOutputDelimited_2.close();
				}

				globalMap.put("tFileOutputDelimited_2_NB_LINE", nb_line_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME", fileName_tFileOutputDelimited_2);

				resourceMap.put("finish_tFileOutputDelimited_2", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row8");
				}

				ok_Hash.put("tFileOutputDelimited_2", true);
				end_Hash.put("tFileOutputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tSortRow_4_SortIn"
			globalMap.remove("tSortRow_4");

			try {

				/**
				 * [tFileInputDelimited_3 finally ] start
				 */

				currentComponent = "tFileInputDelimited_3";

				/**
				 * [tFileInputDelimited_3 finally ] stop
				 */

				/**
				 * [tSortRow_4_SortOut finally ] start
				 */

				currentVirtualComponent = "tSortRow_4";

				currentComponent = "tSortRow_4_SortOut";

				/**
				 * [tSortRow_4_SortOut finally ] stop
				 */

				/**
				 * [tSortRow_4_SortIn finally ] start
				 */

				currentVirtualComponent = "tSortRow_4";

				currentComponent = "tSortRow_4_SortIn";

				/**
				 * [tSortRow_4_SortIn finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_2";

				if (resourceMap.get("finish_tFileOutputDelimited_2") == null) {

					java.io.Writer outtFileOutputDelimited_2 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_2");
					if (outtFileOutputDelimited_2 != null) {
						outtFileOutputDelimited_2.flush();
						outtFileOutputDelimited_2.close();
					}

				}

				/**
				 * [tFileOutputDelimited_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 1);
	}

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _review_id;

		public String get_review_id() {
			return this._review_id;
		}

		public String _order_id;

		public String get_order_id() {
			return this._order_id;
		}

		public String _review_score;

		public String get_review_score() {
			return this._review_score;
		}

		public String _review_comment_title;

		public String get_review_comment_title() {
			return this._review_comment_title;
		}

		public String _review_comment_message;

		public String get_review_comment_message() {
			return this._review_comment_message;
		}

		public String _review_creation_date;

		public String get_review_creation_date() {
			return this._review_creation_date;
		}

		public String _review_answer_timestamp;

		public String get_review_answer_timestamp() {
			return this._review_answer_timestamp;
		}

		public String Column7;

		public String getColumn7() {
			return this.Column7;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._review_id == null) ? 0 : this._review_id.hashCode());

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
			final row6Struct other = (row6Struct) obj;

			if (this._review_id == null) {
				if (other._review_id != null)
					return false;

			} else if (!this._review_id.equals(other._review_id))

				return false;

			return true;
		}

		public void copyDataTo(row6Struct other) {

			other._review_id = this._review_id;
			other._order_id = this._order_id;
			other._review_score = this._review_score;
			other._review_comment_title = this._review_comment_title;
			other._review_comment_message = this._review_comment_message;
			other._review_creation_date = this._review_creation_date;
			other._review_answer_timestamp = this._review_answer_timestamp;
			other.Column7 = this.Column7;

		}

		public void copyKeysDataTo(row6Struct other) {

			other._review_id = this._review_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._review_id = readString(dis);

					this._order_id = readString(dis);

					this._review_score = readString(dis);

					this._review_comment_title = readString(dis);

					this._review_comment_message = readString(dis);

					this._review_creation_date = readString(dis);

					this._review_answer_timestamp = readString(dis);

					this.Column7 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._review_id = readString(dis);

					this._order_id = readString(dis);

					this._review_score = readString(dis);

					this._review_comment_title = readString(dis);

					this._review_comment_message = readString(dis);

					this._review_creation_date = readString(dis);

					this._review_answer_timestamp = readString(dis);

					this.Column7 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._review_id, dos);

				// String

				writeString(this._order_id, dos);

				// String

				writeString(this._review_score, dos);

				// String

				writeString(this._review_comment_title, dos);

				// String

				writeString(this._review_comment_message, dos);

				// String

				writeString(this._review_creation_date, dos);

				// String

				writeString(this._review_answer_timestamp, dos);

				// String

				writeString(this.Column7, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._review_id, dos);

				// String

				writeString(this._order_id, dos);

				// String

				writeString(this._review_score, dos);

				// String

				writeString(this._review_comment_title, dos);

				// String

				writeString(this._review_comment_message, dos);

				// String

				writeString(this._review_creation_date, dos);

				// String

				writeString(this._review_answer_timestamp, dos);

				// String

				writeString(this.Column7, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_review_id=" + _review_id);
			sb.append(",_order_id=" + _order_id);
			sb.append(",_review_score=" + _review_score);
			sb.append(",_review_comment_title=" + _review_comment_title);
			sb.append(",_review_comment_message=" + _review_comment_message);
			sb.append(",_review_creation_date=" + _review_creation_date);
			sb.append(",_review_answer_timestamp=" + _review_answer_timestamp);
			sb.append(",Column7=" + Column7);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._review_id, other._review_id);
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

	public static class OnRowsEndStructtSortRow_3
			implements routines.system.IPersistableRow<OnRowsEndStructtSortRow_3> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _review_id;

		public String get_review_id() {
			return this._review_id;
		}

		public String _order_id;

		public String get_order_id() {
			return this._order_id;
		}

		public String _review_score;

		public String get_review_score() {
			return this._review_score;
		}

		public String _review_comment_title;

		public String get_review_comment_title() {
			return this._review_comment_title;
		}

		public String _review_comment_message;

		public String get_review_comment_message() {
			return this._review_comment_message;
		}

		public String _review_creation_date;

		public String get_review_creation_date() {
			return this._review_creation_date;
		}

		public String _review_answer_timestamp;

		public String get_review_answer_timestamp() {
			return this._review_answer_timestamp;
		}

		public String Column7;

		public String getColumn7() {
			return this.Column7;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._review_id == null) ? 0 : this._review_id.hashCode());

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
			final OnRowsEndStructtSortRow_3 other = (OnRowsEndStructtSortRow_3) obj;

			if (this._review_id == null) {
				if (other._review_id != null)
					return false;

			} else if (!this._review_id.equals(other._review_id))

				return false;

			return true;
		}

		public void copyDataTo(OnRowsEndStructtSortRow_3 other) {

			other._review_id = this._review_id;
			other._order_id = this._order_id;
			other._review_score = this._review_score;
			other._review_comment_title = this._review_comment_title;
			other._review_comment_message = this._review_comment_message;
			other._review_creation_date = this._review_creation_date;
			other._review_answer_timestamp = this._review_answer_timestamp;
			other.Column7 = this.Column7;

		}

		public void copyKeysDataTo(OnRowsEndStructtSortRow_3 other) {

			other._review_id = this._review_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._review_id = readString(dis);

					this._order_id = readString(dis);

					this._review_score = readString(dis);

					this._review_comment_title = readString(dis);

					this._review_comment_message = readString(dis);

					this._review_creation_date = readString(dis);

					this._review_answer_timestamp = readString(dis);

					this.Column7 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._review_id = readString(dis);

					this._order_id = readString(dis);

					this._review_score = readString(dis);

					this._review_comment_title = readString(dis);

					this._review_comment_message = readString(dis);

					this._review_creation_date = readString(dis);

					this._review_answer_timestamp = readString(dis);

					this.Column7 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._review_id, dos);

				// String

				writeString(this._order_id, dos);

				// String

				writeString(this._review_score, dos);

				// String

				writeString(this._review_comment_title, dos);

				// String

				writeString(this._review_comment_message, dos);

				// String

				writeString(this._review_creation_date, dos);

				// String

				writeString(this._review_answer_timestamp, dos);

				// String

				writeString(this.Column7, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._review_id, dos);

				// String

				writeString(this._order_id, dos);

				// String

				writeString(this._review_score, dos);

				// String

				writeString(this._review_comment_title, dos);

				// String

				writeString(this._review_comment_message, dos);

				// String

				writeString(this._review_creation_date, dos);

				// String

				writeString(this._review_answer_timestamp, dos);

				// String

				writeString(this.Column7, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_review_id=" + _review_id);
			sb.append(",_order_id=" + _order_id);
			sb.append(",_review_score=" + _review_score);
			sb.append(",_review_comment_title=" + _review_comment_title);
			sb.append(",_review_comment_message=" + _review_comment_message);
			sb.append(",_review_creation_date=" + _review_creation_date);
			sb.append(",_review_answer_timestamp=" + _review_answer_timestamp);
			sb.append(",Column7=" + Column7);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtSortRow_3 other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._review_id, other._review_id);
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

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _review_id;

		public String get_review_id() {
			return this._review_id;
		}

		public String _order_id;

		public String get_order_id() {
			return this._order_id;
		}

		public String _review_score;

		public String get_review_score() {
			return this._review_score;
		}

		public String _review_comment_title;

		public String get_review_comment_title() {
			return this._review_comment_title;
		}

		public String _review_comment_message;

		public String get_review_comment_message() {
			return this._review_comment_message;
		}

		public String _review_creation_date;

		public String get_review_creation_date() {
			return this._review_creation_date;
		}

		public String _review_answer_timestamp;

		public String get_review_answer_timestamp() {
			return this._review_answer_timestamp;
		}

		public String Column7;

		public String getColumn7() {
			return this.Column7;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._review_id == null) ? 0 : this._review_id.hashCode());

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
			final row5Struct other = (row5Struct) obj;

			if (this._review_id == null) {
				if (other._review_id != null)
					return false;

			} else if (!this._review_id.equals(other._review_id))

				return false;

			return true;
		}

		public void copyDataTo(row5Struct other) {

			other._review_id = this._review_id;
			other._order_id = this._order_id;
			other._review_score = this._review_score;
			other._review_comment_title = this._review_comment_title;
			other._review_comment_message = this._review_comment_message;
			other._review_creation_date = this._review_creation_date;
			other._review_answer_timestamp = this._review_answer_timestamp;
			other.Column7 = this.Column7;

		}

		public void copyKeysDataTo(row5Struct other) {

			other._review_id = this._review_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._review_id = readString(dis);

					this._order_id = readString(dis);

					this._review_score = readString(dis);

					this._review_comment_title = readString(dis);

					this._review_comment_message = readString(dis);

					this._review_creation_date = readString(dis);

					this._review_answer_timestamp = readString(dis);

					this.Column7 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._review_id = readString(dis);

					this._order_id = readString(dis);

					this._review_score = readString(dis);

					this._review_comment_title = readString(dis);

					this._review_comment_message = readString(dis);

					this._review_creation_date = readString(dis);

					this._review_answer_timestamp = readString(dis);

					this.Column7 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._review_id, dos);

				// String

				writeString(this._order_id, dos);

				// String

				writeString(this._review_score, dos);

				// String

				writeString(this._review_comment_title, dos);

				// String

				writeString(this._review_comment_message, dos);

				// String

				writeString(this._review_creation_date, dos);

				// String

				writeString(this._review_answer_timestamp, dos);

				// String

				writeString(this.Column7, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._review_id, dos);

				// String

				writeString(this._order_id, dos);

				// String

				writeString(this._review_score, dos);

				// String

				writeString(this._review_comment_title, dos);

				// String

				writeString(this._review_comment_message, dos);

				// String

				writeString(this._review_creation_date, dos);

				// String

				writeString(this._review_answer_timestamp, dos);

				// String

				writeString(this.Column7, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_review_id=" + _review_id);
			sb.append(",_order_id=" + _order_id);
			sb.append(",_review_score=" + _review_score);
			sb.append(",_review_comment_title=" + _review_comment_title);
			sb.append(",_review_comment_message=" + _review_comment_message);
			sb.append(",_review_creation_date=" + _review_creation_date);
			sb.append(",_review_answer_timestamp=" + _review_answer_timestamp);
			sb.append(",Column7=" + Column7);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._review_id, other._review_id);
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

	public void tFileInputDelimited_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

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

				row5Struct row5 = new row5Struct();
				row6Struct row6 = new row6Struct();

				/**
				 * [tSortRow_3_SortOut begin ] start
				 */

				ok_Hash.put("tSortRow_3_SortOut", false);
				start_Hash.put("tSortRow_3_SortOut", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_3";

				currentComponent = "tSortRow_3_SortOut";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tSortRow_3_SortOut = 0;

				class Comparablerow5Struct extends row5Struct implements Comparable<Comparablerow5Struct> {

					public int compareTo(Comparablerow5Struct other) {

						return 0;
					}
				}

				java.util.List<Comparablerow5Struct> list_tSortRow_3_SortOut = new java.util.ArrayList<Comparablerow5Struct>();

				/**
				 * [tSortRow_3_SortOut begin ] stop
				 */

				/**
				 * [tFileInputDelimited_4 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_4", false);
				start_Hash.put("tFileInputDelimited_4", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_4";

				int tos_count_tFileInputDelimited_4 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_4 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_4 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_4 = null;
				int limit_tFileInputDelimited_4 = -1;
				try {

					Object filename_tFileInputDelimited_4 = "C:/Users/adelj/Desktop/dataset/olist_order_reviews_dataset.csv";
					if (filename_tFileInputDelimited_4 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_4 = 0, random_value_tFileInputDelimited_4 = -1;
						if (footer_value_tFileInputDelimited_4 > 0 || random_value_tFileInputDelimited_4 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_4 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/adelj/Desktop/dataset/olist_order_reviews_dataset.csv", "UTF-8", ",", "\n",
								false, 1, 0, limit_tFileInputDelimited_4, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_4 != null && fid_tFileInputDelimited_4.nextRecord()) {
						rowstate_tFileInputDelimited_4.reset();

						row5 = null;

						boolean whetherReject_tFileInputDelimited_4 = false;
						row5 = new row5Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_4 = 0;

							columnIndexWithD_tFileInputDelimited_4 = 0;

							row5._review_id = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 1;

							row5._order_id = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 2;

							row5._review_score = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 3;

							row5._review_comment_title = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 4;

							row5._review_comment_message = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 5;

							row5._review_creation_date = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 6;

							row5._review_answer_timestamp = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 7;

							row5.Column7 = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							if (rowstate_tFileInputDelimited_4.getException() != null) {
								throw rowstate_tFileInputDelimited_4.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_4 = true;

							System.err.println(e.getMessage());
							row5 = null;

						}

						/**
						 * [tFileInputDelimited_4 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_4 main ] start
						 */

						currentComponent = "tFileInputDelimited_4";

						tos_count_tFileInputDelimited_4++;

						/**
						 * [tFileInputDelimited_4 main ] stop
						 */

						/**
						 * [tFileInputDelimited_4 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_4";

						/**
						 * [tFileInputDelimited_4 process_data_begin ] stop
						 */
// Start of branch "row5"
						if (row5 != null) {

							/**
							 * [tSortRow_3_SortOut main ] start
							 */

							currentVirtualComponent = "tSortRow_3";

							currentComponent = "tSortRow_3_SortOut";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row5"

								);
							}

							Comparablerow5Struct arrayRowtSortRow_3_SortOut = new Comparablerow5Struct();

							arrayRowtSortRow_3_SortOut._review_id = row5._review_id;
							arrayRowtSortRow_3_SortOut._order_id = row5._order_id;
							arrayRowtSortRow_3_SortOut._review_score = row5._review_score;
							arrayRowtSortRow_3_SortOut._review_comment_title = row5._review_comment_title;
							arrayRowtSortRow_3_SortOut._review_comment_message = row5._review_comment_message;
							arrayRowtSortRow_3_SortOut._review_creation_date = row5._review_creation_date;
							arrayRowtSortRow_3_SortOut._review_answer_timestamp = row5._review_answer_timestamp;
							arrayRowtSortRow_3_SortOut.Column7 = row5.Column7;
							list_tSortRow_3_SortOut.add(arrayRowtSortRow_3_SortOut);

							tos_count_tSortRow_3_SortOut++;

							/**
							 * [tSortRow_3_SortOut main ] stop
							 */

							/**
							 * [tSortRow_3_SortOut process_data_begin ] start
							 */

							currentVirtualComponent = "tSortRow_3";

							currentComponent = "tSortRow_3_SortOut";

							/**
							 * [tSortRow_3_SortOut process_data_begin ] stop
							 */

							/**
							 * [tSortRow_3_SortOut process_data_end ] start
							 */

							currentVirtualComponent = "tSortRow_3";

							currentComponent = "tSortRow_3_SortOut";

							/**
							 * [tSortRow_3_SortOut process_data_end ] stop
							 */

						} // End of branch "row5"

						/**
						 * [tFileInputDelimited_4 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_4";

						/**
						 * [tFileInputDelimited_4 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_4 end ] start
						 */

						currentComponent = "tFileInputDelimited_4";

					}
				} finally {
					if (!((Object) ("C:/Users/adelj/Desktop/dataset/olist_order_reviews_dataset.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_4 != null) {
							fid_tFileInputDelimited_4.close();
						}
					}
					if (fid_tFileInputDelimited_4 != null) {
						globalMap.put("tFileInputDelimited_4_NB_LINE", fid_tFileInputDelimited_4.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_4", true);
				end_Hash.put("tFileInputDelimited_4", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_4 end ] stop
				 */

				/**
				 * [tSortRow_3_SortOut end ] start
				 */

				currentVirtualComponent = "tSortRow_3";

				currentComponent = "tSortRow_3_SortOut";

				row5Struct[] array_tSortRow_3_SortOut = list_tSortRow_3_SortOut.toArray(new Comparablerow5Struct[0]);

				java.util.Arrays.sort(array_tSortRow_3_SortOut);

				globalMap.put("tSortRow_3", array_tSortRow_3_SortOut);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tSortRow_3_SortOut", true);
				end_Hash.put("tSortRow_3_SortOut", System.currentTimeMillis());

				/**
				 * [tSortRow_3_SortOut end ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row6");
				}

				int tos_count_tFileOutputDelimited_1 = 0;

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/out.csv")).getAbsolutePath().replace("\\",
								"/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
							fileName_tFileOutputDelimited_1.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
				if (filetFileOutputDelimited_1.exists()) {
					throw new RuntimeException("The particular file \"" + filetFileOutputDelimited_1.getAbsoluteFile()
							+ "\" already exist. If you want to overwrite the file, please uncheck the"
							+ " \"Throw an error if the file already exist\" option in Advanced settings.");
				}
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /** Start field tFileOutputDelimited_1:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																		 * Start field
																		 * tFileOutputDelimited_1:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null && directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {
						dir_tFileOutputDelimited_1.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;

				java.io.File fileToDelete_tFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				if (fileToDelete_tFileOutputDelimited_1.exists()) {
					fileToDelete_tFileOutputDelimited_1.delete();
				}
				outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false), "ISO-8859-15"));

				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tSortRow_3_SortIn begin ] start
				 */

				ok_Hash.put("tSortRow_3_SortIn", false);
				start_Hash.put("tSortRow_3_SortIn", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_3";

				currentComponent = "tSortRow_3_SortIn";

				int tos_count_tSortRow_3_SortIn = 0;

				row5Struct[] array_tSortRow_3_SortIn = (row5Struct[]) globalMap.remove("tSortRow_3");

				int nb_line_tSortRow_3_SortIn = 0;

				row5Struct current_tSortRow_3_SortIn = null;

				for (int i_tSortRow_3_SortIn = 0; i_tSortRow_3_SortIn < array_tSortRow_3_SortIn.length; i_tSortRow_3_SortIn++) {
					current_tSortRow_3_SortIn = array_tSortRow_3_SortIn[i_tSortRow_3_SortIn];
					row6._review_id = current_tSortRow_3_SortIn._review_id;
					row6._order_id = current_tSortRow_3_SortIn._order_id;
					row6._review_score = current_tSortRow_3_SortIn._review_score;
					row6._review_comment_title = current_tSortRow_3_SortIn._review_comment_title;
					row6._review_comment_message = current_tSortRow_3_SortIn._review_comment_message;
					row6._review_creation_date = current_tSortRow_3_SortIn._review_creation_date;
					row6._review_answer_timestamp = current_tSortRow_3_SortIn._review_answer_timestamp;
					row6.Column7 = current_tSortRow_3_SortIn.Column7;
					// increase number of line sorted
					nb_line_tSortRow_3_SortIn++;

					/**
					 * [tSortRow_3_SortIn begin ] stop
					 */

					/**
					 * [tSortRow_3_SortIn main ] start
					 */

					currentVirtualComponent = "tSortRow_3";

					currentComponent = "tSortRow_3_SortIn";

					tos_count_tSortRow_3_SortIn++;

					/**
					 * [tSortRow_3_SortIn main ] stop
					 */

					/**
					 * [tSortRow_3_SortIn process_data_begin ] start
					 */

					currentVirtualComponent = "tSortRow_3";

					currentComponent = "tSortRow_3_SortIn";

					/**
					 * [tSortRow_3_SortIn process_data_begin ] stop
					 */

					/**
					 * [tFileOutputDelimited_1 main ] start
					 */

					currentComponent = "tFileOutputDelimited_1";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row6"

						);
					}

					StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
					if (row6._review_id != null) {
						sb_tFileOutputDelimited_1.append(row6._review_id);
					}
					sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					if (row6._order_id != null) {
						sb_tFileOutputDelimited_1.append(row6._order_id);
					}
					sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					if (row6._review_score != null) {
						sb_tFileOutputDelimited_1.append(row6._review_score);
					}
					sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					if (row6._review_comment_title != null) {
						sb_tFileOutputDelimited_1.append(row6._review_comment_title);
					}
					sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					if (row6._review_comment_message != null) {
						sb_tFileOutputDelimited_1.append(row6._review_comment_message);
					}
					sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					if (row6._review_creation_date != null) {
						sb_tFileOutputDelimited_1.append(row6._review_creation_date);
					}
					sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					if (row6._review_answer_timestamp != null) {
						sb_tFileOutputDelimited_1.append(row6._review_answer_timestamp);
					}
					sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					if (row6.Column7 != null) {
						sb_tFileOutputDelimited_1.append(row6.Column7);
					}
					sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

					nb_line_tFileOutputDelimited_1++;
					resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

					outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());

					tos_count_tFileOutputDelimited_1++;

					/**
					 * [tFileOutputDelimited_1 main ] stop
					 */

					/**
					 * [tFileOutputDelimited_1 process_data_begin ] start
					 */

					currentComponent = "tFileOutputDelimited_1";

					/**
					 * [tFileOutputDelimited_1 process_data_begin ] stop
					 */

					/**
					 * [tFileOutputDelimited_1 process_data_end ] start
					 */

					currentComponent = "tFileOutputDelimited_1";

					/**
					 * [tFileOutputDelimited_1 process_data_end ] stop
					 */

					/**
					 * [tSortRow_3_SortIn process_data_end ] start
					 */

					currentVirtualComponent = "tSortRow_3";

					currentComponent = "tSortRow_3_SortIn";

					/**
					 * [tSortRow_3_SortIn process_data_end ] stop
					 */

					/**
					 * [tSortRow_3_SortIn end ] start
					 */

					currentVirtualComponent = "tSortRow_3";

					currentComponent = "tSortRow_3_SortIn";

				}

				globalMap.put("tSortRow_3_SortIn_NB_LINE", nb_line_tSortRow_3_SortIn);

				ok_Hash.put("tSortRow_3_SortIn", true);
				end_Hash.put("tSortRow_3_SortIn", System.currentTimeMillis());

				/**
				 * [tSortRow_3_SortIn end ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 end ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row6");
				}

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tSortRow_3_SortIn"
			globalMap.remove("tSortRow_3");

			try {

				/**
				 * [tFileInputDelimited_4 finally ] start
				 */

				currentComponent = "tFileInputDelimited_4";

				/**
				 * [tFileInputDelimited_4 finally ] stop
				 */

				/**
				 * [tSortRow_3_SortOut finally ] start
				 */

				currentVirtualComponent = "tSortRow_3";

				currentComponent = "tSortRow_3_SortOut";

				/**
				 * [tSortRow_3_SortOut finally ] stop
				 */

				/**
				 * [tSortRow_3_SortIn finally ] start
				 */

				currentVirtualComponent = "tSortRow_3";

				currentComponent = "tSortRow_3_SortIn";

				/**
				 * [tSortRow_3_SortIn finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 1);
	}

	public static class row14Struct implements routines.system.IPersistableRow<row14Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _product_id;

		public String get_product_id() {
			return this._product_id;
		}

		public String _product_category_name;

		public String get_product_category_name() {
			return this._product_category_name;
		}

		public Integer _product_name_lenght;

		public Integer get_product_name_lenght() {
			return this._product_name_lenght;
		}

		public Integer _product_description_lenght;

		public Integer get_product_description_lenght() {
			return this._product_description_lenght;
		}

		public Integer _product_photos_qty;

		public Integer get_product_photos_qty() {
			return this._product_photos_qty;
		}

		public Integer _product_weight_g;

		public Integer get_product_weight_g() {
			return this._product_weight_g;
		}

		public Integer _product_length_cm;

		public Integer get_product_length_cm() {
			return this._product_length_cm;
		}

		public Integer _product_height_cm;

		public Integer get_product_height_cm() {
			return this._product_height_cm;
		}

		public Integer _product_width_cm;

		public Integer get_product_width_cm() {
			return this._product_width_cm;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._product_id == null) ? 0 : this._product_id.hashCode());

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
			final row14Struct other = (row14Struct) obj;

			if (this._product_id == null) {
				if (other._product_id != null)
					return false;

			} else if (!this._product_id.equals(other._product_id))

				return false;

			return true;
		}

		public void copyDataTo(row14Struct other) {

			other._product_id = this._product_id;
			other._product_category_name = this._product_category_name;
			other._product_name_lenght = this._product_name_lenght;
			other._product_description_lenght = this._product_description_lenght;
			other._product_photos_qty = this._product_photos_qty;
			other._product_weight_g = this._product_weight_g;
			other._product_length_cm = this._product_length_cm;
			other._product_height_cm = this._product_height_cm;
			other._product_width_cm = this._product_width_cm;

		}

		public void copyKeysDataTo(row14Struct other) {

			other._product_id = this._product_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._product_id = readString(dis);

					this._product_category_name = readString(dis);

					this._product_name_lenght = readInteger(dis);

					this._product_description_lenght = readInteger(dis);

					this._product_photos_qty = readInteger(dis);

					this._product_weight_g = readInteger(dis);

					this._product_length_cm = readInteger(dis);

					this._product_height_cm = readInteger(dis);

					this._product_width_cm = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._product_id = readString(dis);

					this._product_category_name = readString(dis);

					this._product_name_lenght = readInteger(dis);

					this._product_description_lenght = readInteger(dis);

					this._product_photos_qty = readInteger(dis);

					this._product_weight_g = readInteger(dis);

					this._product_length_cm = readInteger(dis);

					this._product_height_cm = readInteger(dis);

					this._product_width_cm = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._product_id, dos);

				// String

				writeString(this._product_category_name, dos);

				// Integer

				writeInteger(this._product_name_lenght, dos);

				// Integer

				writeInteger(this._product_description_lenght, dos);

				// Integer

				writeInteger(this._product_photos_qty, dos);

				// Integer

				writeInteger(this._product_weight_g, dos);

				// Integer

				writeInteger(this._product_length_cm, dos);

				// Integer

				writeInteger(this._product_height_cm, dos);

				// Integer

				writeInteger(this._product_width_cm, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._product_id, dos);

				// String

				writeString(this._product_category_name, dos);

				// Integer

				writeInteger(this._product_name_lenght, dos);

				// Integer

				writeInteger(this._product_description_lenght, dos);

				// Integer

				writeInteger(this._product_photos_qty, dos);

				// Integer

				writeInteger(this._product_weight_g, dos);

				// Integer

				writeInteger(this._product_length_cm, dos);

				// Integer

				writeInteger(this._product_height_cm, dos);

				// Integer

				writeInteger(this._product_width_cm, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_product_id=" + _product_id);
			sb.append(",_product_category_name=" + _product_category_name);
			sb.append(",_product_name_lenght=" + String.valueOf(_product_name_lenght));
			sb.append(",_product_description_lenght=" + String.valueOf(_product_description_lenght));
			sb.append(",_product_photos_qty=" + String.valueOf(_product_photos_qty));
			sb.append(",_product_weight_g=" + String.valueOf(_product_weight_g));
			sb.append(",_product_length_cm=" + String.valueOf(_product_length_cm));
			sb.append(",_product_height_cm=" + String.valueOf(_product_height_cm));
			sb.append(",_product_width_cm=" + String.valueOf(_product_width_cm));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row14Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._product_id, other._product_id);
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

	public static class OnRowsEndStructtSortRow_7
			implements routines.system.IPersistableRow<OnRowsEndStructtSortRow_7> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _product_id;

		public String get_product_id() {
			return this._product_id;
		}

		public String _product_category_name;

		public String get_product_category_name() {
			return this._product_category_name;
		}

		public Integer _product_name_lenght;

		public Integer get_product_name_lenght() {
			return this._product_name_lenght;
		}

		public Integer _product_description_lenght;

		public Integer get_product_description_lenght() {
			return this._product_description_lenght;
		}

		public Integer _product_photos_qty;

		public Integer get_product_photos_qty() {
			return this._product_photos_qty;
		}

		public Integer _product_weight_g;

		public Integer get_product_weight_g() {
			return this._product_weight_g;
		}

		public Integer _product_length_cm;

		public Integer get_product_length_cm() {
			return this._product_length_cm;
		}

		public Integer _product_height_cm;

		public Integer get_product_height_cm() {
			return this._product_height_cm;
		}

		public Integer _product_width_cm;

		public Integer get_product_width_cm() {
			return this._product_width_cm;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._product_id == null) ? 0 : this._product_id.hashCode());

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
			final OnRowsEndStructtSortRow_7 other = (OnRowsEndStructtSortRow_7) obj;

			if (this._product_id == null) {
				if (other._product_id != null)
					return false;

			} else if (!this._product_id.equals(other._product_id))

				return false;

			return true;
		}

		public void copyDataTo(OnRowsEndStructtSortRow_7 other) {

			other._product_id = this._product_id;
			other._product_category_name = this._product_category_name;
			other._product_name_lenght = this._product_name_lenght;
			other._product_description_lenght = this._product_description_lenght;
			other._product_photos_qty = this._product_photos_qty;
			other._product_weight_g = this._product_weight_g;
			other._product_length_cm = this._product_length_cm;
			other._product_height_cm = this._product_height_cm;
			other._product_width_cm = this._product_width_cm;

		}

		public void copyKeysDataTo(OnRowsEndStructtSortRow_7 other) {

			other._product_id = this._product_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._product_id = readString(dis);

					this._product_category_name = readString(dis);

					this._product_name_lenght = readInteger(dis);

					this._product_description_lenght = readInteger(dis);

					this._product_photos_qty = readInteger(dis);

					this._product_weight_g = readInteger(dis);

					this._product_length_cm = readInteger(dis);

					this._product_height_cm = readInteger(dis);

					this._product_width_cm = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._product_id = readString(dis);

					this._product_category_name = readString(dis);

					this._product_name_lenght = readInteger(dis);

					this._product_description_lenght = readInteger(dis);

					this._product_photos_qty = readInteger(dis);

					this._product_weight_g = readInteger(dis);

					this._product_length_cm = readInteger(dis);

					this._product_height_cm = readInteger(dis);

					this._product_width_cm = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._product_id, dos);

				// String

				writeString(this._product_category_name, dos);

				// Integer

				writeInteger(this._product_name_lenght, dos);

				// Integer

				writeInteger(this._product_description_lenght, dos);

				// Integer

				writeInteger(this._product_photos_qty, dos);

				// Integer

				writeInteger(this._product_weight_g, dos);

				// Integer

				writeInteger(this._product_length_cm, dos);

				// Integer

				writeInteger(this._product_height_cm, dos);

				// Integer

				writeInteger(this._product_width_cm, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._product_id, dos);

				// String

				writeString(this._product_category_name, dos);

				// Integer

				writeInteger(this._product_name_lenght, dos);

				// Integer

				writeInteger(this._product_description_lenght, dos);

				// Integer

				writeInteger(this._product_photos_qty, dos);

				// Integer

				writeInteger(this._product_weight_g, dos);

				// Integer

				writeInteger(this._product_length_cm, dos);

				// Integer

				writeInteger(this._product_height_cm, dos);

				// Integer

				writeInteger(this._product_width_cm, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_product_id=" + _product_id);
			sb.append(",_product_category_name=" + _product_category_name);
			sb.append(",_product_name_lenght=" + String.valueOf(_product_name_lenght));
			sb.append(",_product_description_lenght=" + String.valueOf(_product_description_lenght));
			sb.append(",_product_photos_qty=" + String.valueOf(_product_photos_qty));
			sb.append(",_product_weight_g=" + String.valueOf(_product_weight_g));
			sb.append(",_product_length_cm=" + String.valueOf(_product_length_cm));
			sb.append(",_product_height_cm=" + String.valueOf(_product_height_cm));
			sb.append(",_product_width_cm=" + String.valueOf(_product_width_cm));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtSortRow_7 other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._product_id, other._product_id);
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

	public static class row13Struct implements routines.system.IPersistableRow<row13Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _product_id;

		public String get_product_id() {
			return this._product_id;
		}

		public String _product_category_name;

		public String get_product_category_name() {
			return this._product_category_name;
		}

		public Integer _product_name_lenght;

		public Integer get_product_name_lenght() {
			return this._product_name_lenght;
		}

		public Integer _product_description_lenght;

		public Integer get_product_description_lenght() {
			return this._product_description_lenght;
		}

		public Integer _product_photos_qty;

		public Integer get_product_photos_qty() {
			return this._product_photos_qty;
		}

		public Integer _product_weight_g;

		public Integer get_product_weight_g() {
			return this._product_weight_g;
		}

		public Integer _product_length_cm;

		public Integer get_product_length_cm() {
			return this._product_length_cm;
		}

		public Integer _product_height_cm;

		public Integer get_product_height_cm() {
			return this._product_height_cm;
		}

		public Integer _product_width_cm;

		public Integer get_product_width_cm() {
			return this._product_width_cm;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._product_id == null) ? 0 : this._product_id.hashCode());

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
			final row13Struct other = (row13Struct) obj;

			if (this._product_id == null) {
				if (other._product_id != null)
					return false;

			} else if (!this._product_id.equals(other._product_id))

				return false;

			return true;
		}

		public void copyDataTo(row13Struct other) {

			other._product_id = this._product_id;
			other._product_category_name = this._product_category_name;
			other._product_name_lenght = this._product_name_lenght;
			other._product_description_lenght = this._product_description_lenght;
			other._product_photos_qty = this._product_photos_qty;
			other._product_weight_g = this._product_weight_g;
			other._product_length_cm = this._product_length_cm;
			other._product_height_cm = this._product_height_cm;
			other._product_width_cm = this._product_width_cm;

		}

		public void copyKeysDataTo(row13Struct other) {

			other._product_id = this._product_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._product_id = readString(dis);

					this._product_category_name = readString(dis);

					this._product_name_lenght = readInteger(dis);

					this._product_description_lenght = readInteger(dis);

					this._product_photos_qty = readInteger(dis);

					this._product_weight_g = readInteger(dis);

					this._product_length_cm = readInteger(dis);

					this._product_height_cm = readInteger(dis);

					this._product_width_cm = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._product_id = readString(dis);

					this._product_category_name = readString(dis);

					this._product_name_lenght = readInteger(dis);

					this._product_description_lenght = readInteger(dis);

					this._product_photos_qty = readInteger(dis);

					this._product_weight_g = readInteger(dis);

					this._product_length_cm = readInteger(dis);

					this._product_height_cm = readInteger(dis);

					this._product_width_cm = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._product_id, dos);

				// String

				writeString(this._product_category_name, dos);

				// Integer

				writeInteger(this._product_name_lenght, dos);

				// Integer

				writeInteger(this._product_description_lenght, dos);

				// Integer

				writeInteger(this._product_photos_qty, dos);

				// Integer

				writeInteger(this._product_weight_g, dos);

				// Integer

				writeInteger(this._product_length_cm, dos);

				// Integer

				writeInteger(this._product_height_cm, dos);

				// Integer

				writeInteger(this._product_width_cm, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._product_id, dos);

				// String

				writeString(this._product_category_name, dos);

				// Integer

				writeInteger(this._product_name_lenght, dos);

				// Integer

				writeInteger(this._product_description_lenght, dos);

				// Integer

				writeInteger(this._product_photos_qty, dos);

				// Integer

				writeInteger(this._product_weight_g, dos);

				// Integer

				writeInteger(this._product_length_cm, dos);

				// Integer

				writeInteger(this._product_height_cm, dos);

				// Integer

				writeInteger(this._product_width_cm, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_product_id=" + _product_id);
			sb.append(",_product_category_name=" + _product_category_name);
			sb.append(",_product_name_lenght=" + String.valueOf(_product_name_lenght));
			sb.append(",_product_description_lenght=" + String.valueOf(_product_description_lenght));
			sb.append(",_product_photos_qty=" + String.valueOf(_product_photos_qty));
			sb.append(",_product_weight_g=" + String.valueOf(_product_weight_g));
			sb.append(",_product_length_cm=" + String.valueOf(_product_length_cm));
			sb.append(",_product_height_cm=" + String.valueOf(_product_height_cm));
			sb.append(",_product_width_cm=" + String.valueOf(_product_width_cm));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row13Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._product_id, other._product_id);
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

	public void tFileInputDelimited_5Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_5_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

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

				row13Struct row13 = new row13Struct();
				row14Struct row14 = new row14Struct();

				/**
				 * [tSortRow_7_SortOut begin ] start
				 */

				ok_Hash.put("tSortRow_7_SortOut", false);
				start_Hash.put("tSortRow_7_SortOut", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_7";

				currentComponent = "tSortRow_7_SortOut";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row13");
				}

				int tos_count_tSortRow_7_SortOut = 0;

				class Comparablerow13Struct extends row13Struct implements Comparable<Comparablerow13Struct> {

					public int compareTo(Comparablerow13Struct other) {

						return 0;
					}
				}

				java.util.List<Comparablerow13Struct> list_tSortRow_7_SortOut = new java.util.ArrayList<Comparablerow13Struct>();

				/**
				 * [tSortRow_7_SortOut begin ] stop
				 */

				/**
				 * [tFileInputDelimited_5 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_5", false);
				start_Hash.put("tFileInputDelimited_5", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_5";

				int tos_count_tFileInputDelimited_5 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_5 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_5 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_5 = null;
				int limit_tFileInputDelimited_5 = -1;
				try {

					Object filename_tFileInputDelimited_5 = "C:/Users/adelj/Desktop/dataset/olist_products_dataset.csv";
					if (filename_tFileInputDelimited_5 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_5 = 0, random_value_tFileInputDelimited_5 = -1;
						if (footer_value_tFileInputDelimited_5 > 0 || random_value_tFileInputDelimited_5 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_5 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/adelj/Desktop/dataset/olist_products_dataset.csv", "US-ASCII", ",", "\n",
								false, 1, 0, limit_tFileInputDelimited_5, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_5 != null && fid_tFileInputDelimited_5.nextRecord()) {
						rowstate_tFileInputDelimited_5.reset();

						row13 = null;

						boolean whetherReject_tFileInputDelimited_5 = false;
						row13 = new row13Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_5 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_5 = 0;

							row13._product_id = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 1;

							row13._product_category_name = fid_tFileInputDelimited_5
									.get(columnIndexWithD_tFileInputDelimited_5);

							columnIndexWithD_tFileInputDelimited_5 = 2;

							temp = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row13._product_name_lenght = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE",
											ex_tFileInputDelimited_5.getMessage());
									rowstate_tFileInputDelimited_5.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_product_name_lenght", "row13", temp, ex_tFileInputDelimited_5),
											ex_tFileInputDelimited_5));
								}

							} else {

								row13._product_name_lenght = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 3;

							temp = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row13._product_description_lenght = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE",
											ex_tFileInputDelimited_5.getMessage());
									rowstate_tFileInputDelimited_5.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_product_description_lenght", "row13", temp, ex_tFileInputDelimited_5),
											ex_tFileInputDelimited_5));
								}

							} else {

								row13._product_description_lenght = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 4;

							temp = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row13._product_photos_qty = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE",
											ex_tFileInputDelimited_5.getMessage());
									rowstate_tFileInputDelimited_5.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_product_photos_qty", "row13", temp, ex_tFileInputDelimited_5),
											ex_tFileInputDelimited_5));
								}

							} else {

								row13._product_photos_qty = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 5;

							temp = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row13._product_weight_g = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE",
											ex_tFileInputDelimited_5.getMessage());
									rowstate_tFileInputDelimited_5.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_product_weight_g", "row13", temp, ex_tFileInputDelimited_5),
											ex_tFileInputDelimited_5));
								}

							} else {

								row13._product_weight_g = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 6;

							temp = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row13._product_length_cm = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE",
											ex_tFileInputDelimited_5.getMessage());
									rowstate_tFileInputDelimited_5.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_product_length_cm", "row13", temp, ex_tFileInputDelimited_5),
											ex_tFileInputDelimited_5));
								}

							} else {

								row13._product_length_cm = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 7;

							temp = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row13._product_height_cm = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE",
											ex_tFileInputDelimited_5.getMessage());
									rowstate_tFileInputDelimited_5.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_product_height_cm", "row13", temp, ex_tFileInputDelimited_5),
											ex_tFileInputDelimited_5));
								}

							} else {

								row13._product_height_cm = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 8;

							temp = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row13._product_width_cm = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE",
											ex_tFileInputDelimited_5.getMessage());
									rowstate_tFileInputDelimited_5.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_product_width_cm", "row13", temp, ex_tFileInputDelimited_5),
											ex_tFileInputDelimited_5));
								}

							} else {

								row13._product_width_cm = null;

							}

							if (rowstate_tFileInputDelimited_5.getException() != null) {
								throw rowstate_tFileInputDelimited_5.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_5 = true;

							System.err.println(e.getMessage());
							row13 = null;

						}

						/**
						 * [tFileInputDelimited_5 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_5 main ] start
						 */

						currentComponent = "tFileInputDelimited_5";

						tos_count_tFileInputDelimited_5++;

						/**
						 * [tFileInputDelimited_5 main ] stop
						 */

						/**
						 * [tFileInputDelimited_5 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_5";

						/**
						 * [tFileInputDelimited_5 process_data_begin ] stop
						 */
// Start of branch "row13"
						if (row13 != null) {

							/**
							 * [tSortRow_7_SortOut main ] start
							 */

							currentVirtualComponent = "tSortRow_7";

							currentComponent = "tSortRow_7_SortOut";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row13"

								);
							}

							Comparablerow13Struct arrayRowtSortRow_7_SortOut = new Comparablerow13Struct();

							arrayRowtSortRow_7_SortOut._product_id = row13._product_id;
							arrayRowtSortRow_7_SortOut._product_category_name = row13._product_category_name;
							arrayRowtSortRow_7_SortOut._product_name_lenght = row13._product_name_lenght;
							arrayRowtSortRow_7_SortOut._product_description_lenght = row13._product_description_lenght;
							arrayRowtSortRow_7_SortOut._product_photos_qty = row13._product_photos_qty;
							arrayRowtSortRow_7_SortOut._product_weight_g = row13._product_weight_g;
							arrayRowtSortRow_7_SortOut._product_length_cm = row13._product_length_cm;
							arrayRowtSortRow_7_SortOut._product_height_cm = row13._product_height_cm;
							arrayRowtSortRow_7_SortOut._product_width_cm = row13._product_width_cm;
							list_tSortRow_7_SortOut.add(arrayRowtSortRow_7_SortOut);

							tos_count_tSortRow_7_SortOut++;

							/**
							 * [tSortRow_7_SortOut main ] stop
							 */

							/**
							 * [tSortRow_7_SortOut process_data_begin ] start
							 */

							currentVirtualComponent = "tSortRow_7";

							currentComponent = "tSortRow_7_SortOut";

							/**
							 * [tSortRow_7_SortOut process_data_begin ] stop
							 */

							/**
							 * [tSortRow_7_SortOut process_data_end ] start
							 */

							currentVirtualComponent = "tSortRow_7";

							currentComponent = "tSortRow_7_SortOut";

							/**
							 * [tSortRow_7_SortOut process_data_end ] stop
							 */

						} // End of branch "row13"

						/**
						 * [tFileInputDelimited_5 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_5";

						/**
						 * [tFileInputDelimited_5 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_5 end ] start
						 */

						currentComponent = "tFileInputDelimited_5";

					}
				} finally {
					if (!((Object) ("C:/Users/adelj/Desktop/dataset/olist_products_dataset.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_5 != null) {
							fid_tFileInputDelimited_5.close();
						}
					}
					if (fid_tFileInputDelimited_5 != null) {
						globalMap.put("tFileInputDelimited_5_NB_LINE", fid_tFileInputDelimited_5.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_5", true);
				end_Hash.put("tFileInputDelimited_5", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_5 end ] stop
				 */

				/**
				 * [tSortRow_7_SortOut end ] start
				 */

				currentVirtualComponent = "tSortRow_7";

				currentComponent = "tSortRow_7_SortOut";

				row13Struct[] array_tSortRow_7_SortOut = list_tSortRow_7_SortOut.toArray(new Comparablerow13Struct[0]);

				java.util.Arrays.sort(array_tSortRow_7_SortOut);

				globalMap.put("tSortRow_7", array_tSortRow_7_SortOut);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row13");
				}

				ok_Hash.put("tSortRow_7_SortOut", true);
				end_Hash.put("tSortRow_7_SortOut", System.currentTimeMillis());

				/**
				 * [tSortRow_7_SortOut end ] stop
				 */

				/**
				 * [tFileOutputDelimited_6 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_6", false);
				start_Hash.put("tFileOutputDelimited_6", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_6";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row14");
				}

				int tos_count_tFileOutputDelimited_6 = 0;

				String fileName_tFileOutputDelimited_6 = "";
				fileName_tFileOutputDelimited_6 = (new java.io.File(
						"C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/out.csv")).getAbsolutePath().replace("\\",
								"/");
				String fullName_tFileOutputDelimited_6 = null;
				String extension_tFileOutputDelimited_6 = null;
				String directory_tFileOutputDelimited_6 = null;
				if ((fileName_tFileOutputDelimited_6.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_6.lastIndexOf(".") < fileName_tFileOutputDelimited_6
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6;
						extension_tFileOutputDelimited_6 = "";
					} else {
						fullName_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6.substring(0,
								fileName_tFileOutputDelimited_6.lastIndexOf("."));
						extension_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6
								.substring(fileName_tFileOutputDelimited_6.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6.substring(0,
							fileName_tFileOutputDelimited_6.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_6.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6.substring(0,
								fileName_tFileOutputDelimited_6.lastIndexOf("."));
						extension_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6
								.substring(fileName_tFileOutputDelimited_6.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6;
						extension_tFileOutputDelimited_6 = "";
					}
					directory_tFileOutputDelimited_6 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_6 = true;
				java.io.File filetFileOutputDelimited_6 = new java.io.File(fileName_tFileOutputDelimited_6);
				globalMap.put("tFileOutputDelimited_6_FILE_NAME", fileName_tFileOutputDelimited_6);
				if (filetFileOutputDelimited_6.exists()) {
					throw new RuntimeException("The particular file \"" + filetFileOutputDelimited_6.getAbsoluteFile()
							+ "\" already exist. If you want to overwrite the file, please uncheck the"
							+ " \"Throw an error if the file already exist\" option in Advanced settings.");
				}
				int nb_line_tFileOutputDelimited_6 = 0;
				int splitedFileNo_tFileOutputDelimited_6 = 0;
				int currentRow_tFileOutputDelimited_6 = 0;

				final String OUT_DELIM_tFileOutputDelimited_6 = /** Start field tFileOutputDelimited_6:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_6:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_6 = /**
																		 * Start field
																		 * tFileOutputDelimited_6:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_6:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_6 != null && directory_tFileOutputDelimited_6.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_6 = new java.io.File(directory_tFileOutputDelimited_6);
					if (!dir_tFileOutputDelimited_6.exists()) {
						dir_tFileOutputDelimited_6.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_6 = null;

				java.io.File fileToDelete_tFileOutputDelimited_6 = new java.io.File(fileName_tFileOutputDelimited_6);
				if (fileToDelete_tFileOutputDelimited_6.exists()) {
					fileToDelete_tFileOutputDelimited_6.delete();
				}
				outtFileOutputDelimited_6 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_6, false), "ISO-8859-15"));

				resourceMap.put("out_tFileOutputDelimited_6", outtFileOutputDelimited_6);
				resourceMap.put("nb_line_tFileOutputDelimited_6", nb_line_tFileOutputDelimited_6);

				/**
				 * [tFileOutputDelimited_6 begin ] stop
				 */

				/**
				 * [tSortRow_7_SortIn begin ] start
				 */

				ok_Hash.put("tSortRow_7_SortIn", false);
				start_Hash.put("tSortRow_7_SortIn", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_7";

				currentComponent = "tSortRow_7_SortIn";

				int tos_count_tSortRow_7_SortIn = 0;

				row13Struct[] array_tSortRow_7_SortIn = (row13Struct[]) globalMap.remove("tSortRow_7");

				int nb_line_tSortRow_7_SortIn = 0;

				row13Struct current_tSortRow_7_SortIn = null;

				for (int i_tSortRow_7_SortIn = 0; i_tSortRow_7_SortIn < array_tSortRow_7_SortIn.length; i_tSortRow_7_SortIn++) {
					current_tSortRow_7_SortIn = array_tSortRow_7_SortIn[i_tSortRow_7_SortIn];
					row14._product_id = current_tSortRow_7_SortIn._product_id;
					row14._product_category_name = current_tSortRow_7_SortIn._product_category_name;
					row14._product_name_lenght = current_tSortRow_7_SortIn._product_name_lenght;
					row14._product_description_lenght = current_tSortRow_7_SortIn._product_description_lenght;
					row14._product_photos_qty = current_tSortRow_7_SortIn._product_photos_qty;
					row14._product_weight_g = current_tSortRow_7_SortIn._product_weight_g;
					row14._product_length_cm = current_tSortRow_7_SortIn._product_length_cm;
					row14._product_height_cm = current_tSortRow_7_SortIn._product_height_cm;
					row14._product_width_cm = current_tSortRow_7_SortIn._product_width_cm;
					// increase number of line sorted
					nb_line_tSortRow_7_SortIn++;

					/**
					 * [tSortRow_7_SortIn begin ] stop
					 */

					/**
					 * [tSortRow_7_SortIn main ] start
					 */

					currentVirtualComponent = "tSortRow_7";

					currentComponent = "tSortRow_7_SortIn";

					tos_count_tSortRow_7_SortIn++;

					/**
					 * [tSortRow_7_SortIn main ] stop
					 */

					/**
					 * [tSortRow_7_SortIn process_data_begin ] start
					 */

					currentVirtualComponent = "tSortRow_7";

					currentComponent = "tSortRow_7_SortIn";

					/**
					 * [tSortRow_7_SortIn process_data_begin ] stop
					 */

					/**
					 * [tFileOutputDelimited_6 main ] start
					 */

					currentComponent = "tFileOutputDelimited_6";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row14"

						);
					}

					StringBuilder sb_tFileOutputDelimited_6 = new StringBuilder();
					if (row14._product_id != null) {
						sb_tFileOutputDelimited_6.append(row14._product_id);
					}
					sb_tFileOutputDelimited_6.append(OUT_DELIM_tFileOutputDelimited_6);
					if (row14._product_category_name != null) {
						sb_tFileOutputDelimited_6.append(row14._product_category_name);
					}
					sb_tFileOutputDelimited_6.append(OUT_DELIM_tFileOutputDelimited_6);
					if (row14._product_name_lenght != null) {
						sb_tFileOutputDelimited_6.append(row14._product_name_lenght);
					}
					sb_tFileOutputDelimited_6.append(OUT_DELIM_tFileOutputDelimited_6);
					if (row14._product_description_lenght != null) {
						sb_tFileOutputDelimited_6.append(row14._product_description_lenght);
					}
					sb_tFileOutputDelimited_6.append(OUT_DELIM_tFileOutputDelimited_6);
					if (row14._product_photos_qty != null) {
						sb_tFileOutputDelimited_6.append(row14._product_photos_qty);
					}
					sb_tFileOutputDelimited_6.append(OUT_DELIM_tFileOutputDelimited_6);
					if (row14._product_weight_g != null) {
						sb_tFileOutputDelimited_6.append(row14._product_weight_g);
					}
					sb_tFileOutputDelimited_6.append(OUT_DELIM_tFileOutputDelimited_6);
					if (row14._product_length_cm != null) {
						sb_tFileOutputDelimited_6.append(row14._product_length_cm);
					}
					sb_tFileOutputDelimited_6.append(OUT_DELIM_tFileOutputDelimited_6);
					if (row14._product_height_cm != null) {
						sb_tFileOutputDelimited_6.append(row14._product_height_cm);
					}
					sb_tFileOutputDelimited_6.append(OUT_DELIM_tFileOutputDelimited_6);
					if (row14._product_width_cm != null) {
						sb_tFileOutputDelimited_6.append(row14._product_width_cm);
					}
					sb_tFileOutputDelimited_6.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_6);

					nb_line_tFileOutputDelimited_6++;
					resourceMap.put("nb_line_tFileOutputDelimited_6", nb_line_tFileOutputDelimited_6);

					outtFileOutputDelimited_6.write(sb_tFileOutputDelimited_6.toString());

					tos_count_tFileOutputDelimited_6++;

					/**
					 * [tFileOutputDelimited_6 main ] stop
					 */

					/**
					 * [tFileOutputDelimited_6 process_data_begin ] start
					 */

					currentComponent = "tFileOutputDelimited_6";

					/**
					 * [tFileOutputDelimited_6 process_data_begin ] stop
					 */

					/**
					 * [tFileOutputDelimited_6 process_data_end ] start
					 */

					currentComponent = "tFileOutputDelimited_6";

					/**
					 * [tFileOutputDelimited_6 process_data_end ] stop
					 */

					/**
					 * [tSortRow_7_SortIn process_data_end ] start
					 */

					currentVirtualComponent = "tSortRow_7";

					currentComponent = "tSortRow_7_SortIn";

					/**
					 * [tSortRow_7_SortIn process_data_end ] stop
					 */

					/**
					 * [tSortRow_7_SortIn end ] start
					 */

					currentVirtualComponent = "tSortRow_7";

					currentComponent = "tSortRow_7_SortIn";

				}

				globalMap.put("tSortRow_7_SortIn_NB_LINE", nb_line_tSortRow_7_SortIn);

				ok_Hash.put("tSortRow_7_SortIn", true);
				end_Hash.put("tSortRow_7_SortIn", System.currentTimeMillis());

				/**
				 * [tSortRow_7_SortIn end ] stop
				 */

				/**
				 * [tFileOutputDelimited_6 end ] start
				 */

				currentComponent = "tFileOutputDelimited_6";

				if (outtFileOutputDelimited_6 != null) {
					outtFileOutputDelimited_6.flush();
					outtFileOutputDelimited_6.close();
				}

				globalMap.put("tFileOutputDelimited_6_NB_LINE", nb_line_tFileOutputDelimited_6);
				globalMap.put("tFileOutputDelimited_6_FILE_NAME", fileName_tFileOutputDelimited_6);

				resourceMap.put("finish_tFileOutputDelimited_6", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row14");
				}

				ok_Hash.put("tFileOutputDelimited_6", true);
				end_Hash.put("tFileOutputDelimited_6", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_6 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tSortRow_7_SortIn"
			globalMap.remove("tSortRow_7");

			try {

				/**
				 * [tFileInputDelimited_5 finally ] start
				 */

				currentComponent = "tFileInputDelimited_5";

				/**
				 * [tFileInputDelimited_5 finally ] stop
				 */

				/**
				 * [tSortRow_7_SortOut finally ] start
				 */

				currentVirtualComponent = "tSortRow_7";

				currentComponent = "tSortRow_7_SortOut";

				/**
				 * [tSortRow_7_SortOut finally ] stop
				 */

				/**
				 * [tSortRow_7_SortIn finally ] start
				 */

				currentVirtualComponent = "tSortRow_7";

				currentComponent = "tSortRow_7_SortIn";

				/**
				 * [tSortRow_7_SortIn finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_6 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_6";

				if (resourceMap.get("finish_tFileOutputDelimited_6") == null) {

					java.io.Writer outtFileOutputDelimited_6 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_6");
					if (outtFileOutputDelimited_6 != null) {
						outtFileOutputDelimited_6.flush();
						outtFileOutputDelimited_6.close();
					}

				}

				/**
				 * [tFileOutputDelimited_6 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_5_SUBPROCESS_STATE", 1);
	}

	public static class row16Struct implements routines.system.IPersistableRow<row16Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _order_id;

		public String get_order_id() {
			return this._order_id;
		}

		public String _customer_id;

		public String get_customer_id() {
			return this._customer_id;
		}

		public String _order_status;

		public String get_order_status() {
			return this._order_status;
		}

		public java.util.Date _order_purchase_timestamp;

		public java.util.Date get_order_purchase_timestamp() {
			return this._order_purchase_timestamp;
		}

		public java.util.Date _order_approved_at;

		public java.util.Date get_order_approved_at() {
			return this._order_approved_at;
		}

		public String _order_delivered_carrier_date;

		public String get_order_delivered_carrier_date() {
			return this._order_delivered_carrier_date;
		}

		public String _order_delivered_customer_date;

		public String get_order_delivered_customer_date() {
			return this._order_delivered_customer_date;
		}

		public java.util.Date _order_estimated_delivery_date;

		public java.util.Date get_order_estimated_delivery_date() {
			return this._order_estimated_delivery_date;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._order_id == null) ? 0 : this._order_id.hashCode());

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
			final row16Struct other = (row16Struct) obj;

			if (this._order_id == null) {
				if (other._order_id != null)
					return false;

			} else if (!this._order_id.equals(other._order_id))

				return false;

			return true;
		}

		public void copyDataTo(row16Struct other) {

			other._order_id = this._order_id;
			other._customer_id = this._customer_id;
			other._order_status = this._order_status;
			other._order_purchase_timestamp = this._order_purchase_timestamp;
			other._order_approved_at = this._order_approved_at;
			other._order_delivered_carrier_date = this._order_delivered_carrier_date;
			other._order_delivered_customer_date = this._order_delivered_customer_date;
			other._order_estimated_delivery_date = this._order_estimated_delivery_date;

		}

		public void copyKeysDataTo(row16Struct other) {

			other._order_id = this._order_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._customer_id = readString(dis);

					this._order_status = readString(dis);

					this._order_purchase_timestamp = readDate(dis);

					this._order_approved_at = readDate(dis);

					this._order_delivered_carrier_date = readString(dis);

					this._order_delivered_customer_date = readString(dis);

					this._order_estimated_delivery_date = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._customer_id = readString(dis);

					this._order_status = readString(dis);

					this._order_purchase_timestamp = readDate(dis);

					this._order_approved_at = readDate(dis);

					this._order_delivered_carrier_date = readString(dis);

					this._order_delivered_customer_date = readString(dis);

					this._order_estimated_delivery_date = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// String

				writeString(this._customer_id, dos);

				// String

				writeString(this._order_status, dos);

				// java.util.Date

				writeDate(this._order_purchase_timestamp, dos);

				// java.util.Date

				writeDate(this._order_approved_at, dos);

				// String

				writeString(this._order_delivered_carrier_date, dos);

				// String

				writeString(this._order_delivered_customer_date, dos);

				// java.util.Date

				writeDate(this._order_estimated_delivery_date, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// String

				writeString(this._customer_id, dos);

				// String

				writeString(this._order_status, dos);

				// java.util.Date

				writeDate(this._order_purchase_timestamp, dos);

				// java.util.Date

				writeDate(this._order_approved_at, dos);

				// String

				writeString(this._order_delivered_carrier_date, dos);

				// String

				writeString(this._order_delivered_customer_date, dos);

				// java.util.Date

				writeDate(this._order_estimated_delivery_date, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_order_id=" + _order_id);
			sb.append(",_customer_id=" + _customer_id);
			sb.append(",_order_status=" + _order_status);
			sb.append(",_order_purchase_timestamp=" + String.valueOf(_order_purchase_timestamp));
			sb.append(",_order_approved_at=" + String.valueOf(_order_approved_at));
			sb.append(",_order_delivered_carrier_date=" + _order_delivered_carrier_date);
			sb.append(",_order_delivered_customer_date=" + _order_delivered_customer_date);
			sb.append(",_order_estimated_delivery_date=" + String.valueOf(_order_estimated_delivery_date));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row16Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._order_id, other._order_id);
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

	public static class OnRowsEndStructtSortRow_8
			implements routines.system.IPersistableRow<OnRowsEndStructtSortRow_8> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _order_id;

		public String get_order_id() {
			return this._order_id;
		}

		public String _customer_id;

		public String get_customer_id() {
			return this._customer_id;
		}

		public String _order_status;

		public String get_order_status() {
			return this._order_status;
		}

		public java.util.Date _order_purchase_timestamp;

		public java.util.Date get_order_purchase_timestamp() {
			return this._order_purchase_timestamp;
		}

		public java.util.Date _order_approved_at;

		public java.util.Date get_order_approved_at() {
			return this._order_approved_at;
		}

		public String _order_delivered_carrier_date;

		public String get_order_delivered_carrier_date() {
			return this._order_delivered_carrier_date;
		}

		public String _order_delivered_customer_date;

		public String get_order_delivered_customer_date() {
			return this._order_delivered_customer_date;
		}

		public java.util.Date _order_estimated_delivery_date;

		public java.util.Date get_order_estimated_delivery_date() {
			return this._order_estimated_delivery_date;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._order_id == null) ? 0 : this._order_id.hashCode());

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
			final OnRowsEndStructtSortRow_8 other = (OnRowsEndStructtSortRow_8) obj;

			if (this._order_id == null) {
				if (other._order_id != null)
					return false;

			} else if (!this._order_id.equals(other._order_id))

				return false;

			return true;
		}

		public void copyDataTo(OnRowsEndStructtSortRow_8 other) {

			other._order_id = this._order_id;
			other._customer_id = this._customer_id;
			other._order_status = this._order_status;
			other._order_purchase_timestamp = this._order_purchase_timestamp;
			other._order_approved_at = this._order_approved_at;
			other._order_delivered_carrier_date = this._order_delivered_carrier_date;
			other._order_delivered_customer_date = this._order_delivered_customer_date;
			other._order_estimated_delivery_date = this._order_estimated_delivery_date;

		}

		public void copyKeysDataTo(OnRowsEndStructtSortRow_8 other) {

			other._order_id = this._order_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._customer_id = readString(dis);

					this._order_status = readString(dis);

					this._order_purchase_timestamp = readDate(dis);

					this._order_approved_at = readDate(dis);

					this._order_delivered_carrier_date = readString(dis);

					this._order_delivered_customer_date = readString(dis);

					this._order_estimated_delivery_date = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._customer_id = readString(dis);

					this._order_status = readString(dis);

					this._order_purchase_timestamp = readDate(dis);

					this._order_approved_at = readDate(dis);

					this._order_delivered_carrier_date = readString(dis);

					this._order_delivered_customer_date = readString(dis);

					this._order_estimated_delivery_date = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// String

				writeString(this._customer_id, dos);

				// String

				writeString(this._order_status, dos);

				// java.util.Date

				writeDate(this._order_purchase_timestamp, dos);

				// java.util.Date

				writeDate(this._order_approved_at, dos);

				// String

				writeString(this._order_delivered_carrier_date, dos);

				// String

				writeString(this._order_delivered_customer_date, dos);

				// java.util.Date

				writeDate(this._order_estimated_delivery_date, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// String

				writeString(this._customer_id, dos);

				// String

				writeString(this._order_status, dos);

				// java.util.Date

				writeDate(this._order_purchase_timestamp, dos);

				// java.util.Date

				writeDate(this._order_approved_at, dos);

				// String

				writeString(this._order_delivered_carrier_date, dos);

				// String

				writeString(this._order_delivered_customer_date, dos);

				// java.util.Date

				writeDate(this._order_estimated_delivery_date, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_order_id=" + _order_id);
			sb.append(",_customer_id=" + _customer_id);
			sb.append(",_order_status=" + _order_status);
			sb.append(",_order_purchase_timestamp=" + String.valueOf(_order_purchase_timestamp));
			sb.append(",_order_approved_at=" + String.valueOf(_order_approved_at));
			sb.append(",_order_delivered_carrier_date=" + _order_delivered_carrier_date);
			sb.append(",_order_delivered_customer_date=" + _order_delivered_customer_date);
			sb.append(",_order_estimated_delivery_date=" + String.valueOf(_order_estimated_delivery_date));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtSortRow_8 other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._order_id, other._order_id);
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

	public static class row15Struct implements routines.system.IPersistableRow<row15Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String _order_id;

		public String get_order_id() {
			return this._order_id;
		}

		public String _customer_id;

		public String get_customer_id() {
			return this._customer_id;
		}

		public String _order_status;

		public String get_order_status() {
			return this._order_status;
		}

		public java.util.Date _order_purchase_timestamp;

		public java.util.Date get_order_purchase_timestamp() {
			return this._order_purchase_timestamp;
		}

		public java.util.Date _order_approved_at;

		public java.util.Date get_order_approved_at() {
			return this._order_approved_at;
		}

		public String _order_delivered_carrier_date;

		public String get_order_delivered_carrier_date() {
			return this._order_delivered_carrier_date;
		}

		public String _order_delivered_customer_date;

		public String get_order_delivered_customer_date() {
			return this._order_delivered_customer_date;
		}

		public java.util.Date _order_estimated_delivery_date;

		public java.util.Date get_order_estimated_delivery_date() {
			return this._order_estimated_delivery_date;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this._order_id == null) ? 0 : this._order_id.hashCode());

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
			final row15Struct other = (row15Struct) obj;

			if (this._order_id == null) {
				if (other._order_id != null)
					return false;

			} else if (!this._order_id.equals(other._order_id))

				return false;

			return true;
		}

		public void copyDataTo(row15Struct other) {

			other._order_id = this._order_id;
			other._customer_id = this._customer_id;
			other._order_status = this._order_status;
			other._order_purchase_timestamp = this._order_purchase_timestamp;
			other._order_approved_at = this._order_approved_at;
			other._order_delivered_carrier_date = this._order_delivered_carrier_date;
			other._order_delivered_customer_date = this._order_delivered_customer_date;
			other._order_estimated_delivery_date = this._order_estimated_delivery_date;

		}

		public void copyKeysDataTo(row15Struct other) {

			other._order_id = this._order_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._customer_id = readString(dis);

					this._order_status = readString(dis);

					this._order_purchase_timestamp = readDate(dis);

					this._order_approved_at = readDate(dis);

					this._order_delivered_carrier_date = readString(dis);

					this._order_delivered_customer_date = readString(dis);

					this._order_estimated_delivery_date = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this._order_id = readString(dis);

					this._customer_id = readString(dis);

					this._order_status = readString(dis);

					this._order_purchase_timestamp = readDate(dis);

					this._order_approved_at = readDate(dis);

					this._order_delivered_carrier_date = readString(dis);

					this._order_delivered_customer_date = readString(dis);

					this._order_estimated_delivery_date = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// String

				writeString(this._customer_id, dos);

				// String

				writeString(this._order_status, dos);

				// java.util.Date

				writeDate(this._order_purchase_timestamp, dos);

				// java.util.Date

				writeDate(this._order_approved_at, dos);

				// String

				writeString(this._order_delivered_carrier_date, dos);

				// String

				writeString(this._order_delivered_customer_date, dos);

				// java.util.Date

				writeDate(this._order_estimated_delivery_date, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this._order_id, dos);

				// String

				writeString(this._customer_id, dos);

				// String

				writeString(this._order_status, dos);

				// java.util.Date

				writeDate(this._order_purchase_timestamp, dos);

				// java.util.Date

				writeDate(this._order_approved_at, dos);

				// String

				writeString(this._order_delivered_carrier_date, dos);

				// String

				writeString(this._order_delivered_customer_date, dos);

				// java.util.Date

				writeDate(this._order_estimated_delivery_date, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_order_id=" + _order_id);
			sb.append(",_customer_id=" + _customer_id);
			sb.append(",_order_status=" + _order_status);
			sb.append(",_order_purchase_timestamp=" + String.valueOf(_order_purchase_timestamp));
			sb.append(",_order_approved_at=" + String.valueOf(_order_approved_at));
			sb.append(",_order_delivered_carrier_date=" + _order_delivered_carrier_date);
			sb.append(",_order_delivered_customer_date=" + _order_delivered_customer_date);
			sb.append(",_order_estimated_delivery_date=" + String.valueOf(_order_estimated_delivery_date));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row15Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this._order_id, other._order_id);
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

	public void tFileInputDelimited_6Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_6_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

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

				row15Struct row15 = new row15Struct();
				row16Struct row16 = new row16Struct();

				/**
				 * [tSortRow_8_SortOut begin ] start
				 */

				ok_Hash.put("tSortRow_8_SortOut", false);
				start_Hash.put("tSortRow_8_SortOut", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_8";

				currentComponent = "tSortRow_8_SortOut";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row15");
				}

				int tos_count_tSortRow_8_SortOut = 0;

				class Comparablerow15Struct extends row15Struct implements Comparable<Comparablerow15Struct> {

					public int compareTo(Comparablerow15Struct other) {

						return 0;
					}
				}

				java.util.List<Comparablerow15Struct> list_tSortRow_8_SortOut = new java.util.ArrayList<Comparablerow15Struct>();

				/**
				 * [tSortRow_8_SortOut begin ] stop
				 */

				/**
				 * [tFileInputDelimited_6 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_6", false);
				start_Hash.put("tFileInputDelimited_6", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_6";

				int tos_count_tFileInputDelimited_6 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_6 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_6 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_6 = null;
				int limit_tFileInputDelimited_6 = -1;
				try {

					Object filename_tFileInputDelimited_6 = "C:/Users/adelj/Desktop/dataset/olist_orders_dataset.csv";
					if (filename_tFileInputDelimited_6 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_6 = 0, random_value_tFileInputDelimited_6 = -1;
						if (footer_value_tFileInputDelimited_6 > 0 || random_value_tFileInputDelimited_6 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_6 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/adelj/Desktop/dataset/olist_orders_dataset.csv", "US-ASCII", ",", "\n", false,
								1, 0, limit_tFileInputDelimited_6, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_6 != null && fid_tFileInputDelimited_6.nextRecord()) {
						rowstate_tFileInputDelimited_6.reset();

						row15 = null;

						boolean whetherReject_tFileInputDelimited_6 = false;
						row15 = new row15Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_6 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_6 = 0;

							row15._order_id = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

							columnIndexWithD_tFileInputDelimited_6 = 1;

							row15._customer_id = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

							columnIndexWithD_tFileInputDelimited_6 = 2;

							row15._order_status = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);

							columnIndexWithD_tFileInputDelimited_6 = 3;

							temp = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);
							if (temp.length() > 0) {

								try {

									row15._order_purchase_timestamp = ParserUtils.parseTo_Date(temp, "dd-MM-yyyy");

								} catch (java.lang.Exception ex_tFileInputDelimited_6) {
									globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE",
											ex_tFileInputDelimited_6.getMessage());
									rowstate_tFileInputDelimited_6.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_order_purchase_timestamp", "row15", temp, ex_tFileInputDelimited_6),
											ex_tFileInputDelimited_6));
								}

							} else {

								row15._order_purchase_timestamp = null;

							}

							columnIndexWithD_tFileInputDelimited_6 = 4;

							temp = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);
							if (temp.length() > 0) {

								try {

									row15._order_approved_at = ParserUtils.parseTo_Date(temp, "dd-MM-yyyy");

								} catch (java.lang.Exception ex_tFileInputDelimited_6) {
									globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE",
											ex_tFileInputDelimited_6.getMessage());
									rowstate_tFileInputDelimited_6.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_order_approved_at", "row15", temp, ex_tFileInputDelimited_6),
											ex_tFileInputDelimited_6));
								}

							} else {

								row15._order_approved_at = null;

							}

							columnIndexWithD_tFileInputDelimited_6 = 5;

							row15._order_delivered_carrier_date = fid_tFileInputDelimited_6
									.get(columnIndexWithD_tFileInputDelimited_6);

							columnIndexWithD_tFileInputDelimited_6 = 6;

							row15._order_delivered_customer_date = fid_tFileInputDelimited_6
									.get(columnIndexWithD_tFileInputDelimited_6);

							columnIndexWithD_tFileInputDelimited_6 = 7;

							temp = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);
							if (temp.length() > 0) {

								try {

									row15._order_estimated_delivery_date = ParserUtils.parseTo_Date(temp, "dd-MM-yyyy");

								} catch (java.lang.Exception ex_tFileInputDelimited_6) {
									globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE",
											ex_tFileInputDelimited_6.getMessage());
									rowstate_tFileInputDelimited_6.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"_order_estimated_delivery_date", "row15", temp, ex_tFileInputDelimited_6),
											ex_tFileInputDelimited_6));
								}

							} else {

								row15._order_estimated_delivery_date = null;

							}

							if (rowstate_tFileInputDelimited_6.getException() != null) {
								throw rowstate_tFileInputDelimited_6.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_6 = true;

							System.err.println(e.getMessage());
							row15 = null;

						}

						/**
						 * [tFileInputDelimited_6 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_6 main ] start
						 */

						currentComponent = "tFileInputDelimited_6";

						tos_count_tFileInputDelimited_6++;

						/**
						 * [tFileInputDelimited_6 main ] stop
						 */

						/**
						 * [tFileInputDelimited_6 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_6";

						/**
						 * [tFileInputDelimited_6 process_data_begin ] stop
						 */
// Start of branch "row15"
						if (row15 != null) {

							/**
							 * [tSortRow_8_SortOut main ] start
							 */

							currentVirtualComponent = "tSortRow_8";

							currentComponent = "tSortRow_8_SortOut";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row15"

								);
							}

							Comparablerow15Struct arrayRowtSortRow_8_SortOut = new Comparablerow15Struct();

							arrayRowtSortRow_8_SortOut._order_id = row15._order_id;
							arrayRowtSortRow_8_SortOut._customer_id = row15._customer_id;
							arrayRowtSortRow_8_SortOut._order_status = row15._order_status;
							arrayRowtSortRow_8_SortOut._order_purchase_timestamp = row15._order_purchase_timestamp;
							arrayRowtSortRow_8_SortOut._order_approved_at = row15._order_approved_at;
							arrayRowtSortRow_8_SortOut._order_delivered_carrier_date = row15._order_delivered_carrier_date;
							arrayRowtSortRow_8_SortOut._order_delivered_customer_date = row15._order_delivered_customer_date;
							arrayRowtSortRow_8_SortOut._order_estimated_delivery_date = row15._order_estimated_delivery_date;
							list_tSortRow_8_SortOut.add(arrayRowtSortRow_8_SortOut);

							tos_count_tSortRow_8_SortOut++;

							/**
							 * [tSortRow_8_SortOut main ] stop
							 */

							/**
							 * [tSortRow_8_SortOut process_data_begin ] start
							 */

							currentVirtualComponent = "tSortRow_8";

							currentComponent = "tSortRow_8_SortOut";

							/**
							 * [tSortRow_8_SortOut process_data_begin ] stop
							 */

							/**
							 * [tSortRow_8_SortOut process_data_end ] start
							 */

							currentVirtualComponent = "tSortRow_8";

							currentComponent = "tSortRow_8_SortOut";

							/**
							 * [tSortRow_8_SortOut process_data_end ] stop
							 */

						} // End of branch "row15"

						/**
						 * [tFileInputDelimited_6 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_6";

						/**
						 * [tFileInputDelimited_6 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_6 end ] start
						 */

						currentComponent = "tFileInputDelimited_6";

					}
				} finally {
					if (!((Object) ("C:/Users/adelj/Desktop/dataset/olist_orders_dataset.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_6 != null) {
							fid_tFileInputDelimited_6.close();
						}
					}
					if (fid_tFileInputDelimited_6 != null) {
						globalMap.put("tFileInputDelimited_6_NB_LINE", fid_tFileInputDelimited_6.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_6", true);
				end_Hash.put("tFileInputDelimited_6", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_6 end ] stop
				 */

				/**
				 * [tSortRow_8_SortOut end ] start
				 */

				currentVirtualComponent = "tSortRow_8";

				currentComponent = "tSortRow_8_SortOut";

				row15Struct[] array_tSortRow_8_SortOut = list_tSortRow_8_SortOut.toArray(new Comparablerow15Struct[0]);

				java.util.Arrays.sort(array_tSortRow_8_SortOut);

				globalMap.put("tSortRow_8", array_tSortRow_8_SortOut);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row15");
				}

				ok_Hash.put("tSortRow_8_SortOut", true);
				end_Hash.put("tSortRow_8_SortOut", System.currentTimeMillis());

				/**
				 * [tSortRow_8_SortOut end ] stop
				 */

				/**
				 * [tFileOutputDelimited_7 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_7", false);
				start_Hash.put("tFileOutputDelimited_7", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_7";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row16");
				}

				int tos_count_tFileOutputDelimited_7 = 0;

				String fileName_tFileOutputDelimited_7 = "";
				fileName_tFileOutputDelimited_7 = (new java.io.File(
						"C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/out.csv")).getAbsolutePath().replace("\\",
								"/");
				String fullName_tFileOutputDelimited_7 = null;
				String extension_tFileOutputDelimited_7 = null;
				String directory_tFileOutputDelimited_7 = null;
				if ((fileName_tFileOutputDelimited_7.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_7.lastIndexOf(".") < fileName_tFileOutputDelimited_7
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7;
						extension_tFileOutputDelimited_7 = "";
					} else {
						fullName_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7.substring(0,
								fileName_tFileOutputDelimited_7.lastIndexOf("."));
						extension_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7
								.substring(fileName_tFileOutputDelimited_7.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7.substring(0,
							fileName_tFileOutputDelimited_7.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_7.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7.substring(0,
								fileName_tFileOutputDelimited_7.lastIndexOf("."));
						extension_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7
								.substring(fileName_tFileOutputDelimited_7.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7;
						extension_tFileOutputDelimited_7 = "";
					}
					directory_tFileOutputDelimited_7 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_7 = true;
				java.io.File filetFileOutputDelimited_7 = new java.io.File(fileName_tFileOutputDelimited_7);
				globalMap.put("tFileOutputDelimited_7_FILE_NAME", fileName_tFileOutputDelimited_7);
				if (filetFileOutputDelimited_7.exists()) {
					throw new RuntimeException("The particular file \"" + filetFileOutputDelimited_7.getAbsoluteFile()
							+ "\" already exist. If you want to overwrite the file, please uncheck the"
							+ " \"Throw an error if the file already exist\" option in Advanced settings.");
				}
				int nb_line_tFileOutputDelimited_7 = 0;
				int splitedFileNo_tFileOutputDelimited_7 = 0;
				int currentRow_tFileOutputDelimited_7 = 0;

				final String OUT_DELIM_tFileOutputDelimited_7 = /** Start field tFileOutputDelimited_7:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_7:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_7 = /**
																		 * Start field
																		 * tFileOutputDelimited_7:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_7:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_7 != null && directory_tFileOutputDelimited_7.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_7 = new java.io.File(directory_tFileOutputDelimited_7);
					if (!dir_tFileOutputDelimited_7.exists()) {
						dir_tFileOutputDelimited_7.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_7 = null;

				java.io.File fileToDelete_tFileOutputDelimited_7 = new java.io.File(fileName_tFileOutputDelimited_7);
				if (fileToDelete_tFileOutputDelimited_7.exists()) {
					fileToDelete_tFileOutputDelimited_7.delete();
				}
				outtFileOutputDelimited_7 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_7, false), "ISO-8859-15"));

				resourceMap.put("out_tFileOutputDelimited_7", outtFileOutputDelimited_7);
				resourceMap.put("nb_line_tFileOutputDelimited_7", nb_line_tFileOutputDelimited_7);

				/**
				 * [tFileOutputDelimited_7 begin ] stop
				 */

				/**
				 * [tSortRow_8_SortIn begin ] start
				 */

				ok_Hash.put("tSortRow_8_SortIn", false);
				start_Hash.put("tSortRow_8_SortIn", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_8";

				currentComponent = "tSortRow_8_SortIn";

				int tos_count_tSortRow_8_SortIn = 0;

				row15Struct[] array_tSortRow_8_SortIn = (row15Struct[]) globalMap.remove("tSortRow_8");

				int nb_line_tSortRow_8_SortIn = 0;

				row15Struct current_tSortRow_8_SortIn = null;

				for (int i_tSortRow_8_SortIn = 0; i_tSortRow_8_SortIn < array_tSortRow_8_SortIn.length; i_tSortRow_8_SortIn++) {
					current_tSortRow_8_SortIn = array_tSortRow_8_SortIn[i_tSortRow_8_SortIn];
					row16._order_id = current_tSortRow_8_SortIn._order_id;
					row16._customer_id = current_tSortRow_8_SortIn._customer_id;
					row16._order_status = current_tSortRow_8_SortIn._order_status;
					row16._order_purchase_timestamp = current_tSortRow_8_SortIn._order_purchase_timestamp;
					row16._order_approved_at = current_tSortRow_8_SortIn._order_approved_at;
					row16._order_delivered_carrier_date = current_tSortRow_8_SortIn._order_delivered_carrier_date;
					row16._order_delivered_customer_date = current_tSortRow_8_SortIn._order_delivered_customer_date;
					row16._order_estimated_delivery_date = current_tSortRow_8_SortIn._order_estimated_delivery_date;
					// increase number of line sorted
					nb_line_tSortRow_8_SortIn++;

					/**
					 * [tSortRow_8_SortIn begin ] stop
					 */

					/**
					 * [tSortRow_8_SortIn main ] start
					 */

					currentVirtualComponent = "tSortRow_8";

					currentComponent = "tSortRow_8_SortIn";

					tos_count_tSortRow_8_SortIn++;

					/**
					 * [tSortRow_8_SortIn main ] stop
					 */

					/**
					 * [tSortRow_8_SortIn process_data_begin ] start
					 */

					currentVirtualComponent = "tSortRow_8";

					currentComponent = "tSortRow_8_SortIn";

					/**
					 * [tSortRow_8_SortIn process_data_begin ] stop
					 */

					/**
					 * [tFileOutputDelimited_7 main ] start
					 */

					currentComponent = "tFileOutputDelimited_7";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row16"

						);
					}

					StringBuilder sb_tFileOutputDelimited_7 = new StringBuilder();
					if (row16._order_id != null) {
						sb_tFileOutputDelimited_7.append(row16._order_id);
					}
					sb_tFileOutputDelimited_7.append(OUT_DELIM_tFileOutputDelimited_7);
					if (row16._customer_id != null) {
						sb_tFileOutputDelimited_7.append(row16._customer_id);
					}
					sb_tFileOutputDelimited_7.append(OUT_DELIM_tFileOutputDelimited_7);
					if (row16._order_status != null) {
						sb_tFileOutputDelimited_7.append(row16._order_status);
					}
					sb_tFileOutputDelimited_7.append(OUT_DELIM_tFileOutputDelimited_7);
					if (row16._order_purchase_timestamp != null) {
						sb_tFileOutputDelimited_7
								.append(FormatterUtils.format_Date(row16._order_purchase_timestamp, "dd-MM-yyyy"));
					}
					sb_tFileOutputDelimited_7.append(OUT_DELIM_tFileOutputDelimited_7);
					if (row16._order_approved_at != null) {
						sb_tFileOutputDelimited_7
								.append(FormatterUtils.format_Date(row16._order_approved_at, "dd-MM-yyyy"));
					}
					sb_tFileOutputDelimited_7.append(OUT_DELIM_tFileOutputDelimited_7);
					if (row16._order_delivered_carrier_date != null) {
						sb_tFileOutputDelimited_7.append(row16._order_delivered_carrier_date);
					}
					sb_tFileOutputDelimited_7.append(OUT_DELIM_tFileOutputDelimited_7);
					if (row16._order_delivered_customer_date != null) {
						sb_tFileOutputDelimited_7.append(row16._order_delivered_customer_date);
					}
					sb_tFileOutputDelimited_7.append(OUT_DELIM_tFileOutputDelimited_7);
					if (row16._order_estimated_delivery_date != null) {
						sb_tFileOutputDelimited_7
								.append(FormatterUtils.format_Date(row16._order_estimated_delivery_date, "dd-MM-yyyy"));
					}
					sb_tFileOutputDelimited_7.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_7);

					nb_line_tFileOutputDelimited_7++;
					resourceMap.put("nb_line_tFileOutputDelimited_7", nb_line_tFileOutputDelimited_7);

					outtFileOutputDelimited_7.write(sb_tFileOutputDelimited_7.toString());

					tos_count_tFileOutputDelimited_7++;

					/**
					 * [tFileOutputDelimited_7 main ] stop
					 */

					/**
					 * [tFileOutputDelimited_7 process_data_begin ] start
					 */

					currentComponent = "tFileOutputDelimited_7";

					/**
					 * [tFileOutputDelimited_7 process_data_begin ] stop
					 */

					/**
					 * [tFileOutputDelimited_7 process_data_end ] start
					 */

					currentComponent = "tFileOutputDelimited_7";

					/**
					 * [tFileOutputDelimited_7 process_data_end ] stop
					 */

					/**
					 * [tSortRow_8_SortIn process_data_end ] start
					 */

					currentVirtualComponent = "tSortRow_8";

					currentComponent = "tSortRow_8_SortIn";

					/**
					 * [tSortRow_8_SortIn process_data_end ] stop
					 */

					/**
					 * [tSortRow_8_SortIn end ] start
					 */

					currentVirtualComponent = "tSortRow_8";

					currentComponent = "tSortRow_8_SortIn";

				}

				globalMap.put("tSortRow_8_SortIn_NB_LINE", nb_line_tSortRow_8_SortIn);

				ok_Hash.put("tSortRow_8_SortIn", true);
				end_Hash.put("tSortRow_8_SortIn", System.currentTimeMillis());

				/**
				 * [tSortRow_8_SortIn end ] stop
				 */

				/**
				 * [tFileOutputDelimited_7 end ] start
				 */

				currentComponent = "tFileOutputDelimited_7";

				if (outtFileOutputDelimited_7 != null) {
					outtFileOutputDelimited_7.flush();
					outtFileOutputDelimited_7.close();
				}

				globalMap.put("tFileOutputDelimited_7_NB_LINE", nb_line_tFileOutputDelimited_7);
				globalMap.put("tFileOutputDelimited_7_FILE_NAME", fileName_tFileOutputDelimited_7);

				resourceMap.put("finish_tFileOutputDelimited_7", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row16");
				}

				ok_Hash.put("tFileOutputDelimited_7", true);
				end_Hash.put("tFileOutputDelimited_7", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_7 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tSortRow_8_SortIn"
			globalMap.remove("tSortRow_8");

			try {

				/**
				 * [tFileInputDelimited_6 finally ] start
				 */

				currentComponent = "tFileInputDelimited_6";

				/**
				 * [tFileInputDelimited_6 finally ] stop
				 */

				/**
				 * [tSortRow_8_SortOut finally ] start
				 */

				currentVirtualComponent = "tSortRow_8";

				currentComponent = "tSortRow_8_SortOut";

				/**
				 * [tSortRow_8_SortOut finally ] stop
				 */

				/**
				 * [tSortRow_8_SortIn finally ] start
				 */

				currentVirtualComponent = "tSortRow_8";

				currentComponent = "tSortRow_8_SortIn";

				/**
				 * [tSortRow_8_SortIn finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_7 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_7";

				if (resourceMap.get("finish_tFileOutputDelimited_7") == null) {

					java.io.Writer outtFileOutputDelimited_7 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_7");
					if (outtFileOutputDelimited_7 != null) {
						outtFileOutputDelimited_7.flush();
						outtFileOutputDelimited_7.close();
					}

				}

				/**
				 * [tFileOutputDelimited_7 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_6_SUBPROCESS_STATE", 1);
	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String customer_id;

		public String getCustomer_id() {
			return this.customer_id;
		}

		public String customer_unique_id;

		public String getCustomer_unique_id() {
			return this.customer_unique_id;
		}

		public Integer customer_zip_code_prefix;

		public Integer getCustomer_zip_code_prefix() {
			return this.customer_zip_code_prefix;
		}

		public String customer_city;

		public String getCustomer_city() {
			return this.customer_city;
		}

		public String customer_state;

		public String getCustomer_state() {
			return this.customer_state;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.customer_id == null) ? 0 : this.customer_id.hashCode());

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
			final row2Struct other = (row2Struct) obj;

			if (this.customer_id == null) {
				if (other.customer_id != null)
					return false;

			} else if (!this.customer_id.equals(other.customer_id))

				return false;

			return true;
		}

		public void copyDataTo(row2Struct other) {

			other.customer_id = this.customer_id;
			other.customer_unique_id = this.customer_unique_id;
			other.customer_zip_code_prefix = this.customer_zip_code_prefix;
			other.customer_city = this.customer_city;
			other.customer_state = this.customer_state;

		}

		public void copyKeysDataTo(row2Struct other) {

			other.customer_id = this.customer_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this.customer_id = readString(dis);

					this.customer_unique_id = readString(dis);

					this.customer_zip_code_prefix = readInteger(dis);

					this.customer_city = readString(dis);

					this.customer_state = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this.customer_id = readString(dis);

					this.customer_unique_id = readString(dis);

					this.customer_zip_code_prefix = readInteger(dis);

					this.customer_city = readString(dis);

					this.customer_state = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.customer_id, dos);

				// String

				writeString(this.customer_unique_id, dos);

				// Integer

				writeInteger(this.customer_zip_code_prefix, dos);

				// String

				writeString(this.customer_city, dos);

				// String

				writeString(this.customer_state, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.customer_id, dos);

				// String

				writeString(this.customer_unique_id, dos);

				// Integer

				writeInteger(this.customer_zip_code_prefix, dos);

				// String

				writeString(this.customer_city, dos);

				// String

				writeString(this.customer_state, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("customer_id=" + customer_id);
			sb.append(",customer_unique_id=" + customer_unique_id);
			sb.append(",customer_zip_code_prefix=" + String.valueOf(customer_zip_code_prefix));
			sb.append(",customer_city=" + customer_city);
			sb.append(",customer_state=" + customer_state);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.customer_id, other.customer_id);
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

	public static class OnRowsEndStructtSortRow_1
			implements routines.system.IPersistableRow<OnRowsEndStructtSortRow_1> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String customer_id;

		public String getCustomer_id() {
			return this.customer_id;
		}

		public String customer_unique_id;

		public String getCustomer_unique_id() {
			return this.customer_unique_id;
		}

		public Integer customer_zip_code_prefix;

		public Integer getCustomer_zip_code_prefix() {
			return this.customer_zip_code_prefix;
		}

		public String customer_city;

		public String getCustomer_city() {
			return this.customer_city;
		}

		public String customer_state;

		public String getCustomer_state() {
			return this.customer_state;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.customer_id == null) ? 0 : this.customer_id.hashCode());

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
			final OnRowsEndStructtSortRow_1 other = (OnRowsEndStructtSortRow_1) obj;

			if (this.customer_id == null) {
				if (other.customer_id != null)
					return false;

			} else if (!this.customer_id.equals(other.customer_id))

				return false;

			return true;
		}

		public void copyDataTo(OnRowsEndStructtSortRow_1 other) {

			other.customer_id = this.customer_id;
			other.customer_unique_id = this.customer_unique_id;
			other.customer_zip_code_prefix = this.customer_zip_code_prefix;
			other.customer_city = this.customer_city;
			other.customer_state = this.customer_state;

		}

		public void copyKeysDataTo(OnRowsEndStructtSortRow_1 other) {

			other.customer_id = this.customer_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this.customer_id = readString(dis);

					this.customer_unique_id = readString(dis);

					this.customer_zip_code_prefix = readInteger(dis);

					this.customer_city = readString(dis);

					this.customer_state = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this.customer_id = readString(dis);

					this.customer_unique_id = readString(dis);

					this.customer_zip_code_prefix = readInteger(dis);

					this.customer_city = readString(dis);

					this.customer_state = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.customer_id, dos);

				// String

				writeString(this.customer_unique_id, dos);

				// Integer

				writeInteger(this.customer_zip_code_prefix, dos);

				// String

				writeString(this.customer_city, dos);

				// String

				writeString(this.customer_state, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.customer_id, dos);

				// String

				writeString(this.customer_unique_id, dos);

				// Integer

				writeInteger(this.customer_zip_code_prefix, dos);

				// String

				writeString(this.customer_city, dos);

				// String

				writeString(this.customer_state, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("customer_id=" + customer_id);
			sb.append(",customer_unique_id=" + customer_unique_id);
			sb.append(",customer_zip_code_prefix=" + String.valueOf(customer_zip_code_prefix));
			sb.append(",customer_city=" + customer_city);
			sb.append(",customer_state=" + customer_state);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtSortRow_1 other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.customer_id, other.customer_id);
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

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String customer_id;

		public String getCustomer_id() {
			return this.customer_id;
		}

		public String customer_unique_id;

		public String getCustomer_unique_id() {
			return this.customer_unique_id;
		}

		public Integer customer_zip_code_prefix;

		public Integer getCustomer_zip_code_prefix() {
			return this.customer_zip_code_prefix;
		}

		public String customer_city;

		public String getCustomer_city() {
			return this.customer_city;
		}

		public String customer_state;

		public String getCustomer_state() {
			return this.customer_state;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.customer_id == null) ? 0 : this.customer_id.hashCode());

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
			final row1Struct other = (row1Struct) obj;

			if (this.customer_id == null) {
				if (other.customer_id != null)
					return false;

			} else if (!this.customer_id.equals(other.customer_id))

				return false;

			return true;
		}

		public void copyDataTo(row1Struct other) {

			other.customer_id = this.customer_id;
			other.customer_unique_id = this.customer_unique_id;
			other.customer_zip_code_prefix = this.customer_zip_code_prefix;
			other.customer_city = this.customer_city;
			other.customer_state = this.customer_state;

		}

		public void copyKeysDataTo(row1Struct other) {

			other.customer_id = this.customer_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this.customer_id = readString(dis);

					this.customer_unique_id = readString(dis);

					this.customer_zip_code_prefix = readInteger(dis);

					this.customer_city = readString(dis);

					this.customer_state = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

				try {

					int length = 0;

					this.customer_id = readString(dis);

					this.customer_unique_id = readString(dis);

					this.customer_zip_code_prefix = readInteger(dis);

					this.customer_city = readString(dis);

					this.customer_state = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.customer_id, dos);

				// String

				writeString(this.customer_unique_id, dos);

				// Integer

				writeInteger(this.customer_zip_code_prefix, dos);

				// String

				writeString(this.customer_city, dos);

				// String

				writeString(this.customer_state, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.customer_id, dos);

				// String

				writeString(this.customer_unique_id, dos);

				// Integer

				writeInteger(this.customer_zip_code_prefix, dos);

				// String

				writeString(this.customer_city, dos);

				// String

				writeString(this.customer_state, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("customer_id=" + customer_id);
			sb.append(",customer_unique_id=" + customer_unique_id);
			sb.append(",customer_zip_code_prefix=" + String.valueOf(customer_zip_code_prefix));
			sb.append(",customer_city=" + customer_city);
			sb.append(",customer_state=" + customer_state);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.customer_id, other.customer_id);
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

	public void tFileInputJSON_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputJSON_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

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
				row2Struct row2 = new row2Struct();

				/**
				 * [tSortRow_1_SortOut begin ] start
				 */

				ok_Hash.put("tSortRow_1_SortOut", false);
				start_Hash.put("tSortRow_1_SortOut", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortOut";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tSortRow_1_SortOut = 0;

				class Comparablerow1Struct extends row1Struct implements Comparable<Comparablerow1Struct> {

					public int compareTo(Comparablerow1Struct other) {

						return 0;
					}
				}

				java.util.List<Comparablerow1Struct> list_tSortRow_1_SortOut = new java.util.ArrayList<Comparablerow1Struct>();

				/**
				 * [tSortRow_1_SortOut begin ] stop
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
					filenameOrStream_tFileInputJSON_1 = "C:/Users/adelj/Desktop/dataset/olist_customers_dataset.json";
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
					boolean whetherReject_tFileInputJSON_1 = false;
					row1 = new row1Struct();

					try {
						jsonPath_tFileInputJSON_1 = "customer_id";
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
							row1.customer_id = value_tFileInputJSON_1 == null ?

									null : value_tFileInputJSON_1.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_1) {
							globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
							row1.customer_id =

									null;
						}
						jsonPath_tFileInputJSON_1 = "customer_unique_id";
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
							row1.customer_unique_id = value_tFileInputJSON_1 == null ?

									null : value_tFileInputJSON_1.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_1) {
							globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
							row1.customer_unique_id =

									null;
						}
						jsonPath_tFileInputJSON_1 = "customer_zip_code_prefix";
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
								row1.customer_zip_code_prefix = ParserUtils
										.parseTo_Integer(value_tFileInputJSON_1.toString());
							} else {
								row1.customer_zip_code_prefix =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_1) {
							globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
							row1.customer_zip_code_prefix =

									null;
						}
						jsonPath_tFileInputJSON_1 = "customer_city";
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
							row1.customer_city = value_tFileInputJSON_1 == null ?

									null : value_tFileInputJSON_1.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_1) {
							globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
							row1.customer_city =

									null;
						}
						jsonPath_tFileInputJSON_1 = "customer_state";
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
							row1.customer_state = value_tFileInputJSON_1 == null ?

									null : value_tFileInputJSON_1.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_1) {
							globalMap.put("tFileInputJSON_1_ERROR_MESSAGE", e_tFileInputJSON_1.getMessage());
							row1.customer_state =

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
						 * [tSortRow_1_SortOut main ] start
						 */

						currentVirtualComponent = "tSortRow_1";

						currentComponent = "tSortRow_1_SortOut";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row1"

							);
						}

						Comparablerow1Struct arrayRowtSortRow_1_SortOut = new Comparablerow1Struct();

						arrayRowtSortRow_1_SortOut.customer_id = row1.customer_id;
						arrayRowtSortRow_1_SortOut.customer_unique_id = row1.customer_unique_id;
						arrayRowtSortRow_1_SortOut.customer_zip_code_prefix = row1.customer_zip_code_prefix;
						arrayRowtSortRow_1_SortOut.customer_city = row1.customer_city;
						arrayRowtSortRow_1_SortOut.customer_state = row1.customer_state;
						list_tSortRow_1_SortOut.add(arrayRowtSortRow_1_SortOut);

						tos_count_tSortRow_1_SortOut++;

						/**
						 * [tSortRow_1_SortOut main ] stop
						 */

						/**
						 * [tSortRow_1_SortOut process_data_begin ] start
						 */

						currentVirtualComponent = "tSortRow_1";

						currentComponent = "tSortRow_1_SortOut";

						/**
						 * [tSortRow_1_SortOut process_data_begin ] stop
						 */

						/**
						 * [tSortRow_1_SortOut process_data_end ] start
						 */

						currentVirtualComponent = "tSortRow_1";

						currentComponent = "tSortRow_1_SortOut";

						/**
						 * [tSortRow_1_SortOut process_data_end ] stop
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
				 * [tSortRow_1_SortOut end ] start
				 */

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortOut";

				row1Struct[] array_tSortRow_1_SortOut = list_tSortRow_1_SortOut.toArray(new Comparablerow1Struct[0]);

				java.util.Arrays.sort(array_tSortRow_1_SortOut);

				globalMap.put("tSortRow_1", array_tSortRow_1_SortOut);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tSortRow_1_SortOut", true);
				end_Hash.put("tSortRow_1_SortOut", System.currentTimeMillis());

				/**
				 * [tSortRow_1_SortOut end ] stop
				 */

				/**
				 * [tFileOutputJSON_1 begin ] start
				 */

				ok_Hash.put("tFileOutputJSON_1", false);
				start_Hash.put("tFileOutputJSON_1", System.currentTimeMillis());

				currentComponent = "tFileOutputJSON_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tFileOutputJSON_1 = 0;

				int nb_line_tFileOutputJSON_1 = 0;
				java.io.File file_tFileOutputJSON_1 = new java.io.File(
						"C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/out.json");
				java.io.File dir_tFileOutputJSON_1 = file_tFileOutputJSON_1.getParentFile();
				if (dir_tFileOutputJSON_1 != null && !dir_tFileOutputJSON_1.exists()) {
					dir_tFileOutputJSON_1.mkdirs();
				}
				java.io.PrintWriter outtFileOutputJSON_1 = new java.io.PrintWriter(new java.io.BufferedWriter(
						new java.io.FileWriter("C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/out.json")));
				outtFileOutputJSON_1.append("{\"" + "data" + "\":[");
				boolean isFirst_tFileOutputJSON_1 = true;

				/**
				 * [tFileOutputJSON_1 begin ] stop
				 */

				/**
				 * [tSortRow_1_SortIn begin ] start
				 */

				ok_Hash.put("tSortRow_1_SortIn", false);
				start_Hash.put("tSortRow_1_SortIn", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortIn";

				int tos_count_tSortRow_1_SortIn = 0;

				row1Struct[] array_tSortRow_1_SortIn = (row1Struct[]) globalMap.remove("tSortRow_1");

				int nb_line_tSortRow_1_SortIn = 0;

				row1Struct current_tSortRow_1_SortIn = null;

				for (int i_tSortRow_1_SortIn = 0; i_tSortRow_1_SortIn < array_tSortRow_1_SortIn.length; i_tSortRow_1_SortIn++) {
					current_tSortRow_1_SortIn = array_tSortRow_1_SortIn[i_tSortRow_1_SortIn];
					row2.customer_id = current_tSortRow_1_SortIn.customer_id;
					row2.customer_unique_id = current_tSortRow_1_SortIn.customer_unique_id;
					row2.customer_zip_code_prefix = current_tSortRow_1_SortIn.customer_zip_code_prefix;
					row2.customer_city = current_tSortRow_1_SortIn.customer_city;
					row2.customer_state = current_tSortRow_1_SortIn.customer_state;
					// increase number of line sorted
					nb_line_tSortRow_1_SortIn++;

					/**
					 * [tSortRow_1_SortIn begin ] stop
					 */

					/**
					 * [tSortRow_1_SortIn main ] start
					 */

					currentVirtualComponent = "tSortRow_1";

					currentComponent = "tSortRow_1_SortIn";

					tos_count_tSortRow_1_SortIn++;

					/**
					 * [tSortRow_1_SortIn main ] stop
					 */

					/**
					 * [tSortRow_1_SortIn process_data_begin ] start
					 */

					currentVirtualComponent = "tSortRow_1";

					currentComponent = "tSortRow_1_SortIn";

					/**
					 * [tSortRow_1_SortIn process_data_begin ] stop
					 */

					/**
					 * [tFileOutputJSON_1 main ] start
					 */

					currentComponent = "tFileOutputJSON_1";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row2"

						);
					}

					org.json.simple.JSONObject jsonRowtFileOutputJSON_1 = new org.json.simple.JSONObject();
					if (row2.customer_id != null) {

						jsonRowtFileOutputJSON_1.put("customer_id", row2.customer_id);

					} else {
						jsonRowtFileOutputJSON_1.put("customer_id", null);
					}

					if (row2.customer_unique_id != null) {

						jsonRowtFileOutputJSON_1.put("customer_unique_id", row2.customer_unique_id);

					} else {
						jsonRowtFileOutputJSON_1.put("customer_unique_id", null);
					}

					if (row2.customer_zip_code_prefix != null) {

						jsonRowtFileOutputJSON_1.put("customer_zip_code_prefix", row2.customer_zip_code_prefix);

					} else {
						jsonRowtFileOutputJSON_1.put("customer_zip_code_prefix", null);
					}

					if (row2.customer_city != null) {

						jsonRowtFileOutputJSON_1.put("customer_city", row2.customer_city);

					} else {
						jsonRowtFileOutputJSON_1.put("customer_city", null);
					}

					if (row2.customer_state != null) {

						jsonRowtFileOutputJSON_1.put("customer_state", row2.customer_state);

					} else {
						jsonRowtFileOutputJSON_1.put("customer_state", null);
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

					/**
					 * [tSortRow_1_SortIn process_data_end ] start
					 */

					currentVirtualComponent = "tSortRow_1";

					currentComponent = "tSortRow_1_SortIn";

					/**
					 * [tSortRow_1_SortIn process_data_end ] stop
					 */

					/**
					 * [tSortRow_1_SortIn end ] start
					 */

					currentVirtualComponent = "tSortRow_1";

					currentComponent = "tSortRow_1_SortIn";

				}

				globalMap.put("tSortRow_1_SortIn_NB_LINE", nb_line_tSortRow_1_SortIn);

				ok_Hash.put("tSortRow_1_SortIn", true);
				end_Hash.put("tSortRow_1_SortIn", System.currentTimeMillis());

				/**
				 * [tSortRow_1_SortIn end ] stop
				 */

				/**
				 * [tFileOutputJSON_1 end ] start
				 */

				currentComponent = "tFileOutputJSON_1";

				outtFileOutputJSON_1.print("]}");
				outtFileOutputJSON_1.close();
				globalMap.put("tFileOutputJSON_1_NB_LINE", nb_line_tFileOutputJSON_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tFileOutputJSON_1", true);
				end_Hash.put("tFileOutputJSON_1", System.currentTimeMillis());

				/**
				 * [tFileOutputJSON_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tSortRow_1_SortIn"
			globalMap.remove("tSortRow_1");

			try {

				/**
				 * [tFileInputJSON_1 finally ] start
				 */

				currentComponent = "tFileInputJSON_1";

				/**
				 * [tFileInputJSON_1 finally ] stop
				 */

				/**
				 * [tSortRow_1_SortOut finally ] start
				 */

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortOut";

				/**
				 * [tSortRow_1_SortOut finally ] stop
				 */

				/**
				 * [tSortRow_1_SortIn finally ] start
				 */

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortIn";

				/**
				 * [tSortRow_1_SortIn finally ] stop
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

		globalMap.put("tFileInputJSON_1_SUBPROCESS_STATE", 1);
	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

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

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.seller_id == null) ? 0 : this.seller_id.hashCode());

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
			final row4Struct other = (row4Struct) obj;

			if (this.seller_id == null) {
				if (other.seller_id != null)
					return false;

			} else if (!this.seller_id.equals(other.seller_id))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.seller_id = this.seller_id;
			other.seller_zip_code_prefix = this.seller_zip_code_prefix;
			other.seller_state = this.seller_state;
			other.seller_city = this.seller_city;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.seller_id = this.seller_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

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
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.seller_id, other.seller_id);
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

	public static class OnRowsEndStructtSortRow_2
			implements routines.system.IPersistableRow<OnRowsEndStructtSortRow_2> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

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

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.seller_id == null) ? 0 : this.seller_id.hashCode());

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
			final OnRowsEndStructtSortRow_2 other = (OnRowsEndStructtSortRow_2) obj;

			if (this.seller_id == null) {
				if (other.seller_id != null)
					return false;

			} else if (!this.seller_id.equals(other.seller_id))

				return false;

			return true;
		}

		public void copyDataTo(OnRowsEndStructtSortRow_2 other) {

			other.seller_id = this.seller_id;
			other.seller_zip_code_prefix = this.seller_zip_code_prefix;
			other.seller_state = this.seller_state;
			other.seller_city = this.seller_city;

		}

		public void copyKeysDataTo(OnRowsEndStructtSortRow_2 other) {

			other.seller_id = this.seller_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

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
		public int compareTo(OnRowsEndStructtSortRow_2 other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.seller_id, other.seller_id);
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

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_documents_sorting = new byte[0];
		static byte[] commonByteArray_PROJET_BI_documents_sorting = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

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

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.seller_id == null) ? 0 : this.seller_id.hashCode());

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
			final row3Struct other = (row3Struct) obj;

			if (this.seller_id == null) {
				if (other.seller_id != null)
					return false;

			} else if (!this.seller_id.equals(other.seller_id))

				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.seller_id = this.seller_id;
			other.seller_zip_code_prefix = this.seller_zip_code_prefix;
			other.seller_state = this.seller_state;
			other.seller_city = this.seller_city;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.seller_id = this.seller_id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_documents_sorting.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_documents_sorting.length == 0) {
						commonByteArray_PROJET_BI_documents_sorting = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_documents_sorting = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_documents_sorting, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_documents_sorting, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

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

			synchronized (commonByteArrayLock_PROJET_BI_documents_sorting) {

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
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.seller_id, other.seller_id);
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

	public void tFileInputJSON_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputJSON_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

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

				row3Struct row3 = new row3Struct();
				row4Struct row4 = new row4Struct();

				/**
				 * [tSortRow_2_SortOut begin ] start
				 */

				ok_Hash.put("tSortRow_2_SortOut", false);
				start_Hash.put("tSortRow_2_SortOut", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_2";

				currentComponent = "tSortRow_2_SortOut";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tSortRow_2_SortOut = 0;

				class Comparablerow3Struct extends row3Struct implements Comparable<Comparablerow3Struct> {

					public int compareTo(Comparablerow3Struct other) {

						return 0;
					}
				}

				java.util.List<Comparablerow3Struct> list_tSortRow_2_SortOut = new java.util.ArrayList<Comparablerow3Struct>();

				/**
				 * [tSortRow_2_SortOut begin ] stop
				 */

				/**
				 * [tFileInputJSON_2 begin ] start
				 */

				ok_Hash.put("tFileInputJSON_2", false);
				start_Hash.put("tFileInputJSON_2", System.currentTimeMillis());

				currentComponent = "tFileInputJSON_2";

				int tos_count_tFileInputJSON_2 = 0;

				class JsonPathCache_tFileInputJSON_2 {
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

				int nb_line_tFileInputJSON_2 = 0;

				JsonPathCache_tFileInputJSON_2 jsonPathCache_tFileInputJSON_2 = new JsonPathCache_tFileInputJSON_2();

				String loopPath_tFileInputJSON_2 = "$[*]";
				java.util.List<Object> resultset_tFileInputJSON_2 = new java.util.ArrayList<Object>();

				java.io.InputStream is_tFileInputJSON_2 = null;
				com.jayway.jsonpath.ParseContext parseContext_tFileInputJSON_2 = com.jayway.jsonpath.JsonPath
						.using(com.jayway.jsonpath.Configuration.defaultConfiguration());
				Object filenameOrStream_tFileInputJSON_2 = null;
				try {
					filenameOrStream_tFileInputJSON_2 = "C:/Users/adelj/Desktop/dataset/olist_sellers_dataset.json";
				} catch (java.lang.Exception e_tFileInputJSON_2) {
					globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());

					globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
					System.err.println(e_tFileInputJSON_2.getMessage());
				}

				com.jayway.jsonpath.ReadContext document_tFileInputJSON_2 = null;
				try {
					if (filenameOrStream_tFileInputJSON_2 instanceof java.io.InputStream) {
						is_tFileInputJSON_2 = (java.io.InputStream) filenameOrStream_tFileInputJSON_2;
					} else {

						is_tFileInputJSON_2 = new java.io.FileInputStream((String) filenameOrStream_tFileInputJSON_2);

					}

					document_tFileInputJSON_2 = parseContext_tFileInputJSON_2.parse(is_tFileInputJSON_2, "UTF-8");
					com.jayway.jsonpath.JsonPath compiledLoopPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
							.getCompiledJsonPath(loopPath_tFileInputJSON_2);
					Object result_tFileInputJSON_2 = document_tFileInputJSON_2.read(compiledLoopPath_tFileInputJSON_2,
							net.minidev.json.JSONObject.class);
					if (result_tFileInputJSON_2 instanceof net.minidev.json.JSONArray) {
						resultset_tFileInputJSON_2 = (net.minidev.json.JSONArray) result_tFileInputJSON_2;
					} else {
						resultset_tFileInputJSON_2.add(result_tFileInputJSON_2);
					}
				} catch (java.lang.Exception e_tFileInputJSON_2) {
					globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
					globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
					System.err.println(e_tFileInputJSON_2.getMessage());
				} finally {
					if (is_tFileInputJSON_2 != null) {
						is_tFileInputJSON_2.close();
					}
				}

				String jsonPath_tFileInputJSON_2 = null;
				com.jayway.jsonpath.JsonPath compiledJsonPath_tFileInputJSON_2 = null;

				Object value_tFileInputJSON_2 = null;
				Object root_tFileInputJSON_2 = null;
				for (Object row_tFileInputJSON_2 : resultset_tFileInputJSON_2) {
					nb_line_tFileInputJSON_2++;
					row3 = null;
					boolean whetherReject_tFileInputJSON_2 = false;
					row3 = new row3Struct();

					try {
						jsonPath_tFileInputJSON_2 = "seller_id";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							if (jsonPath_tFileInputJSON_2.startsWith("$")) {
								if (root_tFileInputJSON_2 == null) {
									root_tFileInputJSON_2 = document_tFileInputJSON_2
											.read(jsonPathCache_tFileInputJSON_2.getCompiledJsonPath("$"));
								}
								value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(root_tFileInputJSON_2);
							} else {
								value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);
							}
							row3.seller_id = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row3.seller_id =

									null;
						}
						jsonPath_tFileInputJSON_2 = "seller_zip_code_prefix";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							if (jsonPath_tFileInputJSON_2.startsWith("$")) {
								if (root_tFileInputJSON_2 == null) {
									root_tFileInputJSON_2 = document_tFileInputJSON_2
											.read(jsonPathCache_tFileInputJSON_2.getCompiledJsonPath("$"));
								}
								value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(root_tFileInputJSON_2);
							} else {
								value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);
							}
							if (value_tFileInputJSON_2 != null && !value_tFileInputJSON_2.toString().isEmpty()) {
								row3.seller_zip_code_prefix = ParserUtils
										.parseTo_Integer(value_tFileInputJSON_2.toString());
							} else {
								row3.seller_zip_code_prefix =

										null;
							}
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row3.seller_zip_code_prefix =

									null;
						}
						jsonPath_tFileInputJSON_2 = "seller_state";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							if (jsonPath_tFileInputJSON_2.startsWith("$")) {
								if (root_tFileInputJSON_2 == null) {
									root_tFileInputJSON_2 = document_tFileInputJSON_2
											.read(jsonPathCache_tFileInputJSON_2.getCompiledJsonPath("$"));
								}
								value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(root_tFileInputJSON_2);
							} else {
								value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);
							}
							row3.seller_state = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row3.seller_state =

									null;
						}
						jsonPath_tFileInputJSON_2 = "seller_city";
						compiledJsonPath_tFileInputJSON_2 = jsonPathCache_tFileInputJSON_2
								.getCompiledJsonPath(jsonPath_tFileInputJSON_2);

						try {

							if (jsonPath_tFileInputJSON_2.startsWith("$")) {
								if (root_tFileInputJSON_2 == null) {
									root_tFileInputJSON_2 = document_tFileInputJSON_2
											.read(jsonPathCache_tFileInputJSON_2.getCompiledJsonPath("$"));
								}
								value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(root_tFileInputJSON_2);
							} else {
								value_tFileInputJSON_2 = compiledJsonPath_tFileInputJSON_2.read(row_tFileInputJSON_2);
							}
							row3.seller_city = value_tFileInputJSON_2 == null ?

									null : value_tFileInputJSON_2.toString();
						} catch (com.jayway.jsonpath.PathNotFoundException e_tFileInputJSON_2) {
							globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
							row3.seller_city =

									null;
						}
					} catch (java.lang.Exception e_tFileInputJSON_2) {
						globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
						whetherReject_tFileInputJSON_2 = true;
						System.err.println(e_tFileInputJSON_2.getMessage());
						row3 = null;
						globalMap.put("tFileInputJSON_2_ERROR_MESSAGE", e_tFileInputJSON_2.getMessage());
					}
//}

					/**
					 * [tFileInputJSON_2 begin ] stop
					 */

					/**
					 * [tFileInputJSON_2 main ] start
					 */

					currentComponent = "tFileInputJSON_2";

					tos_count_tFileInputJSON_2++;

					/**
					 * [tFileInputJSON_2 main ] stop
					 */

					/**
					 * [tFileInputJSON_2 process_data_begin ] start
					 */

					currentComponent = "tFileInputJSON_2";

					/**
					 * [tFileInputJSON_2 process_data_begin ] stop
					 */
// Start of branch "row3"
					if (row3 != null) {

						/**
						 * [tSortRow_2_SortOut main ] start
						 */

						currentVirtualComponent = "tSortRow_2";

						currentComponent = "tSortRow_2_SortOut";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row3"

							);
						}

						Comparablerow3Struct arrayRowtSortRow_2_SortOut = new Comparablerow3Struct();

						arrayRowtSortRow_2_SortOut.seller_id = row3.seller_id;
						arrayRowtSortRow_2_SortOut.seller_zip_code_prefix = row3.seller_zip_code_prefix;
						arrayRowtSortRow_2_SortOut.seller_state = row3.seller_state;
						arrayRowtSortRow_2_SortOut.seller_city = row3.seller_city;
						list_tSortRow_2_SortOut.add(arrayRowtSortRow_2_SortOut);

						tos_count_tSortRow_2_SortOut++;

						/**
						 * [tSortRow_2_SortOut main ] stop
						 */

						/**
						 * [tSortRow_2_SortOut process_data_begin ] start
						 */

						currentVirtualComponent = "tSortRow_2";

						currentComponent = "tSortRow_2_SortOut";

						/**
						 * [tSortRow_2_SortOut process_data_begin ] stop
						 */

						/**
						 * [tSortRow_2_SortOut process_data_end ] start
						 */

						currentVirtualComponent = "tSortRow_2";

						currentComponent = "tSortRow_2_SortOut";

						/**
						 * [tSortRow_2_SortOut process_data_end ] stop
						 */

					} // End of branch "row3"

					/**
					 * [tFileInputJSON_2 process_data_end ] start
					 */

					currentComponent = "tFileInputJSON_2";

					/**
					 * [tFileInputJSON_2 process_data_end ] stop
					 */

					/**
					 * [tFileInputJSON_2 end ] start
					 */

					currentComponent = "tFileInputJSON_2";

				}
				globalMap.put("tFileInputJSON_2_NB_LINE", nb_line_tFileInputJSON_2);

				ok_Hash.put("tFileInputJSON_2", true);
				end_Hash.put("tFileInputJSON_2", System.currentTimeMillis());

				/**
				 * [tFileInputJSON_2 end ] stop
				 */

				/**
				 * [tSortRow_2_SortOut end ] start
				 */

				currentVirtualComponent = "tSortRow_2";

				currentComponent = "tSortRow_2_SortOut";

				row3Struct[] array_tSortRow_2_SortOut = list_tSortRow_2_SortOut.toArray(new Comparablerow3Struct[0]);

				java.util.Arrays.sort(array_tSortRow_2_SortOut);

				globalMap.put("tSortRow_2", array_tSortRow_2_SortOut);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tSortRow_2_SortOut", true);
				end_Hash.put("tSortRow_2_SortOut", System.currentTimeMillis());

				/**
				 * [tSortRow_2_SortOut end ] stop
				 */

				/**
				 * [tFileOutputJSON_2 begin ] start
				 */

				ok_Hash.put("tFileOutputJSON_2", false);
				start_Hash.put("tFileOutputJSON_2", System.currentTimeMillis());

				currentComponent = "tFileOutputJSON_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tFileOutputJSON_2 = 0;

				int nb_line_tFileOutputJSON_2 = 0;
				java.io.File file_tFileOutputJSON_2 = new java.io.File(
						"C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/out.json");
				java.io.File dir_tFileOutputJSON_2 = file_tFileOutputJSON_2.getParentFile();
				if (dir_tFileOutputJSON_2 != null && !dir_tFileOutputJSON_2.exists()) {
					dir_tFileOutputJSON_2.mkdirs();
				}
				java.io.PrintWriter outtFileOutputJSON_2 = new java.io.PrintWriter(new java.io.BufferedWriter(
						new java.io.FileWriter("C:/Users/adelj/TOS_BD-20211109_1610-V8.0.1/workspace/out.json")));
				outtFileOutputJSON_2.append("{\"" + "data" + "\":[");
				boolean isFirst_tFileOutputJSON_2 = true;

				/**
				 * [tFileOutputJSON_2 begin ] stop
				 */

				/**
				 * [tSortRow_2_SortIn begin ] start
				 */

				ok_Hash.put("tSortRow_2_SortIn", false);
				start_Hash.put("tSortRow_2_SortIn", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_2";

				currentComponent = "tSortRow_2_SortIn";

				int tos_count_tSortRow_2_SortIn = 0;

				row3Struct[] array_tSortRow_2_SortIn = (row3Struct[]) globalMap.remove("tSortRow_2");

				int nb_line_tSortRow_2_SortIn = 0;

				row3Struct current_tSortRow_2_SortIn = null;

				for (int i_tSortRow_2_SortIn = 0; i_tSortRow_2_SortIn < array_tSortRow_2_SortIn.length; i_tSortRow_2_SortIn++) {
					current_tSortRow_2_SortIn = array_tSortRow_2_SortIn[i_tSortRow_2_SortIn];
					row4.seller_id = current_tSortRow_2_SortIn.seller_id;
					row4.seller_zip_code_prefix = current_tSortRow_2_SortIn.seller_zip_code_prefix;
					row4.seller_state = current_tSortRow_2_SortIn.seller_state;
					row4.seller_city = current_tSortRow_2_SortIn.seller_city;
					// increase number of line sorted
					nb_line_tSortRow_2_SortIn++;

					/**
					 * [tSortRow_2_SortIn begin ] stop
					 */

					/**
					 * [tSortRow_2_SortIn main ] start
					 */

					currentVirtualComponent = "tSortRow_2";

					currentComponent = "tSortRow_2_SortIn";

					tos_count_tSortRow_2_SortIn++;

					/**
					 * [tSortRow_2_SortIn main ] stop
					 */

					/**
					 * [tSortRow_2_SortIn process_data_begin ] start
					 */

					currentVirtualComponent = "tSortRow_2";

					currentComponent = "tSortRow_2_SortIn";

					/**
					 * [tSortRow_2_SortIn process_data_begin ] stop
					 */

					/**
					 * [tFileOutputJSON_2 main ] start
					 */

					currentComponent = "tFileOutputJSON_2";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row4"

						);
					}

					org.json.simple.JSONObject jsonRowtFileOutputJSON_2 = new org.json.simple.JSONObject();
					if (row4.seller_id != null) {

						jsonRowtFileOutputJSON_2.put("seller_id", row4.seller_id);

					} else {
						jsonRowtFileOutputJSON_2.put("seller_id", null);
					}

					if (row4.seller_zip_code_prefix != null) {

						jsonRowtFileOutputJSON_2.put("seller_zip_code_prefix", row4.seller_zip_code_prefix);

					} else {
						jsonRowtFileOutputJSON_2.put("seller_zip_code_prefix", null);
					}

					if (row4.seller_state != null) {

						jsonRowtFileOutputJSON_2.put("seller_state", row4.seller_state);

					} else {
						jsonRowtFileOutputJSON_2.put("seller_state", null);
					}

					if (row4.seller_city != null) {

						jsonRowtFileOutputJSON_2.put("seller_city", row4.seller_city);

					} else {
						jsonRowtFileOutputJSON_2.put("seller_city", null);
					}

					if (!isFirst_tFileOutputJSON_2) {
						outtFileOutputJSON_2.append(",");
					}
					isFirst_tFileOutputJSON_2 = false;
					outtFileOutputJSON_2.append(jsonRowtFileOutputJSON_2.toJSONString());
					nb_line_tFileOutputJSON_2++;

					tos_count_tFileOutputJSON_2++;

					/**
					 * [tFileOutputJSON_2 main ] stop
					 */

					/**
					 * [tFileOutputJSON_2 process_data_begin ] start
					 */

					currentComponent = "tFileOutputJSON_2";

					/**
					 * [tFileOutputJSON_2 process_data_begin ] stop
					 */

					/**
					 * [tFileOutputJSON_2 process_data_end ] start
					 */

					currentComponent = "tFileOutputJSON_2";

					/**
					 * [tFileOutputJSON_2 process_data_end ] stop
					 */

					/**
					 * [tSortRow_2_SortIn process_data_end ] start
					 */

					currentVirtualComponent = "tSortRow_2";

					currentComponent = "tSortRow_2_SortIn";

					/**
					 * [tSortRow_2_SortIn process_data_end ] stop
					 */

					/**
					 * [tSortRow_2_SortIn end ] start
					 */

					currentVirtualComponent = "tSortRow_2";

					currentComponent = "tSortRow_2_SortIn";

				}

				globalMap.put("tSortRow_2_SortIn_NB_LINE", nb_line_tSortRow_2_SortIn);

				ok_Hash.put("tSortRow_2_SortIn", true);
				end_Hash.put("tSortRow_2_SortIn", System.currentTimeMillis());

				/**
				 * [tSortRow_2_SortIn end ] stop
				 */

				/**
				 * [tFileOutputJSON_2 end ] start
				 */

				currentComponent = "tFileOutputJSON_2";

				outtFileOutputJSON_2.print("]}");
				outtFileOutputJSON_2.close();
				globalMap.put("tFileOutputJSON_2_NB_LINE", nb_line_tFileOutputJSON_2);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tFileOutputJSON_2", true);
				end_Hash.put("tFileOutputJSON_2", System.currentTimeMillis());

				/**
				 * [tFileOutputJSON_2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tSortRow_2_SortIn"
			globalMap.remove("tSortRow_2");

			try {

				/**
				 * [tFileInputJSON_2 finally ] start
				 */

				currentComponent = "tFileInputJSON_2";

				/**
				 * [tFileInputJSON_2 finally ] stop
				 */

				/**
				 * [tSortRow_2_SortOut finally ] start
				 */

				currentVirtualComponent = "tSortRow_2";

				currentComponent = "tSortRow_2_SortOut";

				/**
				 * [tSortRow_2_SortOut finally ] stop
				 */

				/**
				 * [tSortRow_2_SortIn finally ] start
				 */

				currentVirtualComponent = "tSortRow_2";

				currentComponent = "tSortRow_2_SortIn";

				/**
				 * [tSortRow_2_SortIn finally ] stop
				 */

				/**
				 * [tFileOutputJSON_2 finally ] start
				 */

				currentComponent = "tFileOutputJSON_2";

				/**
				 * [tFileOutputJSON_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputJSON_2_SUBPROCESS_STATE", 1);
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
		final documents_sorting documents_sortingClass = new documents_sorting();

		int exitCode = documents_sortingClass.runJobInTOS(args);

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
			java.io.InputStream inContext = documents_sorting.class.getClassLoader()
					.getResourceAsStream("projet_bi/documents_sorting_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = documents_sorting.class.getClassLoader()
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
		try {
			errorCode = null;
			tFileInputDelimited_2Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_2) {
			globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_2.printStackTrace();

		}
		try {
			errorCode = null;
			tFileInputDelimited_3Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_3) {
			globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_3.printStackTrace();

		}
		try {
			errorCode = null;
			tFileInputDelimited_4Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_4) {
			globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_4.printStackTrace();

		}
		try {
			errorCode = null;
			tFileInputDelimited_5Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_5) {
			globalMap.put("tFileInputDelimited_5_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_5.printStackTrace();

		}
		try {
			errorCode = null;
			tFileInputDelimited_6Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_6) {
			globalMap.put("tFileInputDelimited_6_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_6.printStackTrace();

		}
		try {
			errorCode = null;
			tFileInputJSON_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputJSON_1) {
			globalMap.put("tFileInputJSON_1_SUBPROCESS_STATE", -1);

			e_tFileInputJSON_1.printStackTrace();

		}
		try {
			errorCode = null;
			tFileInputJSON_2Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputJSON_2) {
			globalMap.put("tFileInputJSON_2_SUBPROCESS_STATE", -1);

			e_tFileInputJSON_2.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : documents_sorting");
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
 * 485454 characters generated by Talend Open Studio for Big Data on the 16
 * janvier 2024, 3:35:30 PM CET
 ************************************************************************************************/