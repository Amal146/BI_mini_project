%~d0
cd %~dp0
java -Dtalend.component.manager.m2.repository="%cd%/../lib" -Xms256M -Xmx1024M -Dfile.encoding=UTF-8 -cp .;../lib/routines.jar;../lib/log4j-slf4j-impl-2.13.2.jar;../lib/log4j-api-2.13.2.jar;../lib/log4j-core-2.13.2.jar;../lib/log4j-1.2-api-2.13.2.jar;../lib/commons-collections-3.2.2.jar;../lib/jboss-marshalling-river-2.0.12.Final.jar;../lib/jboss-marshalling-2.0.12.Final.jar;../lib/talend_file_enhanced-1.1.jar;../lib/advancedPersistentLookupLib-1.3.jar;../lib/accessors-smart-2.4.7.jar;../lib/json_simple-1.1.jar;../lib/dom4j-2.1.3.jar;../lib/json-smart-2.4.7.jar;../lib/slf4j-api-1.7.29.jar;../lib/trove.jar;../lib/json-path-2.1.0.jar;../lib/talendcsv-1.0.0.jar;../lib/crypto-utils-0.31.12.jar;seller_address_id_correction_0_1.jar; projet_bi.seller_address_id_correction_0_1.Seller_address_id_correction %*
