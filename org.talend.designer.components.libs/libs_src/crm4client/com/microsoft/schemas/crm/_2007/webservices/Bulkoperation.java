/*
 * XML Type:  bulkoperation
 * Namespace: http://schemas.microsoft.com/crm/2007/WebServices
 * Java type: com.microsoft.schemas.crm._2007.webservices.Bulkoperation
 *
 * Automatically generated - do not modify.
 */
package com.microsoft.schemas.crm._2007.webservices;


/**
 * An XML bulkoperation(@http://schemas.microsoft.com/crm/2007/WebServices).
 *
 * This is a complex type.
 */
public interface Bulkoperation extends com.microsoft.schemas.crm._2006.webservices.BusinessEntity
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Bulkoperation.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE3DFDC56E75679F2AF264CA469AD5996").resolveHandle("bulkoperationf7e1type");
    
    /**
     * Gets the "activityid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Key getActivityid();
    
    /**
     * True if has "activityid" element
     */
    boolean isSetActivityid();
    
    /**
     * Sets the "activityid" element
     */
    void setActivityid(com.microsoft.schemas.crm._2006.webservices.Key activityid);
    
    /**
     * Appends and returns a new empty "activityid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Key addNewActivityid();
    
    /**
     * Unsets the "activityid" element
     */
    void unsetActivityid();
    
    /**
     * Gets the "actualdurationminutes" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber getActualdurationminutes();
    
    /**
     * True if has "actualdurationminutes" element
     */
    boolean isSetActualdurationminutes();
    
    /**
     * Sets the "actualdurationminutes" element
     */
    void setActualdurationminutes(com.microsoft.schemas.crm._2006.webservices.CrmNumber actualdurationminutes);
    
    /**
     * Appends and returns a new empty "actualdurationminutes" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber addNewActualdurationminutes();
    
    /**
     * Unsets the "actualdurationminutes" element
     */
    void unsetActualdurationminutes();
    
    /**
     * Gets the "actualend" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime getActualend();
    
    /**
     * True if has "actualend" element
     */
    boolean isSetActualend();
    
    /**
     * Sets the "actualend" element
     */
    void setActualend(com.microsoft.schemas.crm._2006.webservices.CrmDateTime actualend);
    
    /**
     * Appends and returns a new empty "actualend" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime addNewActualend();
    
    /**
     * Unsets the "actualend" element
     */
    void unsetActualend();
    
    /**
     * Gets the "actualstart" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime getActualstart();
    
    /**
     * True if has "actualstart" element
     */
    boolean isSetActualstart();
    
    /**
     * Sets the "actualstart" element
     */
    void setActualstart(com.microsoft.schemas.crm._2006.webservices.CrmDateTime actualstart);
    
    /**
     * Appends and returns a new empty "actualstart" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime addNewActualstart();
    
    /**
     * Unsets the "actualstart" element
     */
    void unsetActualstart();
    
    /**
     * Gets the "bulkoperationnumber" element
     */
    java.lang.String getBulkoperationnumber();
    
    /**
     * Gets (as xml) the "bulkoperationnumber" element
     */
    org.apache.xmlbeans.XmlString xgetBulkoperationnumber();
    
    /**
     * True if has "bulkoperationnumber" element
     */
    boolean isSetBulkoperationnumber();
    
    /**
     * Sets the "bulkoperationnumber" element
     */
    void setBulkoperationnumber(java.lang.String bulkoperationnumber);
    
    /**
     * Sets (as xml) the "bulkoperationnumber" element
     */
    void xsetBulkoperationnumber(org.apache.xmlbeans.XmlString bulkoperationnumber);
    
    /**
     * Unsets the "bulkoperationnumber" element
     */
    void unsetBulkoperationnumber();
    
    /**
     * Gets the "createdby" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup getCreatedby();
    
    /**
     * True if has "createdby" element
     */
    boolean isSetCreatedby();
    
    /**
     * Sets the "createdby" element
     */
    void setCreatedby(com.microsoft.schemas.crm._2006.webservices.Lookup createdby);
    
    /**
     * Appends and returns a new empty "createdby" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup addNewCreatedby();
    
    /**
     * Unsets the "createdby" element
     */
    void unsetCreatedby();
    
    /**
     * Gets the "createdon" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime getCreatedon();
    
    /**
     * True if has "createdon" element
     */
    boolean isSetCreatedon();
    
    /**
     * Sets the "createdon" element
     */
    void setCreatedon(com.microsoft.schemas.crm._2006.webservices.CrmDateTime createdon);
    
    /**
     * Appends and returns a new empty "createdon" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime addNewCreatedon();
    
    /**
     * Unsets the "createdon" element
     */
    void unsetCreatedon();
    
    /**
     * Gets the "createdrecordtypecode" element
     */
    com.microsoft.schemas.crm._2006.webservices.Picklist getCreatedrecordtypecode();
    
    /**
     * True if has "createdrecordtypecode" element
     */
    boolean isSetCreatedrecordtypecode();
    
    /**
     * Sets the "createdrecordtypecode" element
     */
    void setCreatedrecordtypecode(com.microsoft.schemas.crm._2006.webservices.Picklist createdrecordtypecode);
    
    /**
     * Appends and returns a new empty "createdrecordtypecode" element
     */
    com.microsoft.schemas.crm._2006.webservices.Picklist addNewCreatedrecordtypecode();
    
    /**
     * Unsets the "createdrecordtypecode" element
     */
    void unsetCreatedrecordtypecode();
    
    /**
     * Gets the "description" element
     */
    java.lang.String getDescription();
    
    /**
     * Gets (as xml) the "description" element
     */
    org.apache.xmlbeans.XmlString xgetDescription();
    
    /**
     * True if has "description" element
     */
    boolean isSetDescription();
    
    /**
     * Sets the "description" element
     */
    void setDescription(java.lang.String description);
    
    /**
     * Sets (as xml) the "description" element
     */
    void xsetDescription(org.apache.xmlbeans.XmlString description);
    
    /**
     * Unsets the "description" element
     */
    void unsetDescription();
    
    /**
     * Gets the "errornumber" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber getErrornumber();
    
    /**
     * True if has "errornumber" element
     */
    boolean isSetErrornumber();
    
    /**
     * Sets the "errornumber" element
     */
    void setErrornumber(com.microsoft.schemas.crm._2006.webservices.CrmNumber errornumber);
    
    /**
     * Appends and returns a new empty "errornumber" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber addNewErrornumber();
    
    /**
     * Unsets the "errornumber" element
     */
    void unsetErrornumber();
    
    /**
     * Gets the "failurecount" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber getFailurecount();
    
    /**
     * True if has "failurecount" element
     */
    boolean isSetFailurecount();
    
    /**
     * Sets the "failurecount" element
     */
    void setFailurecount(com.microsoft.schemas.crm._2006.webservices.CrmNumber failurecount);
    
    /**
     * Appends and returns a new empty "failurecount" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber addNewFailurecount();
    
    /**
     * Unsets the "failurecount" element
     */
    void unsetFailurecount();
    
    /**
     * Gets the "isbilled" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmBoolean getIsbilled();
    
    /**
     * True if has "isbilled" element
     */
    boolean isSetIsbilled();
    
    /**
     * Sets the "isbilled" element
     */
    void setIsbilled(com.microsoft.schemas.crm._2006.webservices.CrmBoolean isbilled);
    
    /**
     * Appends and returns a new empty "isbilled" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmBoolean addNewIsbilled();
    
    /**
     * Unsets the "isbilled" element
     */
    void unsetIsbilled();
    
    /**
     * Gets the "isworkflowcreated" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmBoolean getIsworkflowcreated();
    
    /**
     * True if has "isworkflowcreated" element
     */
    boolean isSetIsworkflowcreated();
    
    /**
     * Sets the "isworkflowcreated" element
     */
    void setIsworkflowcreated(com.microsoft.schemas.crm._2006.webservices.CrmBoolean isworkflowcreated);
    
    /**
     * Appends and returns a new empty "isworkflowcreated" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmBoolean addNewIsworkflowcreated();
    
    /**
     * Unsets the "isworkflowcreated" element
     */
    void unsetIsworkflowcreated();
    
    /**
     * Gets the "modifiedby" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup getModifiedby();
    
    /**
     * True if has "modifiedby" element
     */
    boolean isSetModifiedby();
    
    /**
     * Sets the "modifiedby" element
     */
    void setModifiedby(com.microsoft.schemas.crm._2006.webservices.Lookup modifiedby);
    
    /**
     * Appends and returns a new empty "modifiedby" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup addNewModifiedby();
    
    /**
     * Unsets the "modifiedby" element
     */
    void unsetModifiedby();
    
    /**
     * Gets the "modifiedon" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime getModifiedon();
    
    /**
     * True if has "modifiedon" element
     */
    boolean isSetModifiedon();
    
    /**
     * Sets the "modifiedon" element
     */
    void setModifiedon(com.microsoft.schemas.crm._2006.webservices.CrmDateTime modifiedon);
    
    /**
     * Appends and returns a new empty "modifiedon" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime addNewModifiedon();
    
    /**
     * Unsets the "modifiedon" element
     */
    void unsetModifiedon();
    
    /**
     * Gets the "operationtypecode" element
     */
    com.microsoft.schemas.crm._2006.webservices.Picklist getOperationtypecode();
    
    /**
     * True if has "operationtypecode" element
     */
    boolean isSetOperationtypecode();
    
    /**
     * Sets the "operationtypecode" element
     */
    void setOperationtypecode(com.microsoft.schemas.crm._2006.webservices.Picklist operationtypecode);
    
    /**
     * Appends and returns a new empty "operationtypecode" element
     */
    com.microsoft.schemas.crm._2006.webservices.Picklist addNewOperationtypecode();
    
    /**
     * Unsets the "operationtypecode" element
     */
    void unsetOperationtypecode();
    
    /**
     * Gets the "ownerid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Owner getOwnerid();
    
    /**
     * True if has "ownerid" element
     */
    boolean isSetOwnerid();
    
    /**
     * Sets the "ownerid" element
     */
    void setOwnerid(com.microsoft.schemas.crm._2006.webservices.Owner ownerid);
    
    /**
     * Appends and returns a new empty "ownerid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Owner addNewOwnerid();
    
    /**
     * Unsets the "ownerid" element
     */
    void unsetOwnerid();
    
    /**
     * Gets the "owningbusinessunit" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup getOwningbusinessunit();
    
    /**
     * True if has "owningbusinessunit" element
     */
    boolean isSetOwningbusinessunit();
    
    /**
     * Sets the "owningbusinessunit" element
     */
    void setOwningbusinessunit(com.microsoft.schemas.crm._2006.webservices.Lookup owningbusinessunit);
    
    /**
     * Appends and returns a new empty "owningbusinessunit" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup addNewOwningbusinessunit();
    
    /**
     * Unsets the "owningbusinessunit" element
     */
    void unsetOwningbusinessunit();
    
    /**
     * Gets the "parameters" element
     */
    java.lang.String getParameters();
    
    /**
     * Gets (as xml) the "parameters" element
     */
    org.apache.xmlbeans.XmlString xgetParameters();
    
    /**
     * True if has "parameters" element
     */
    boolean isSetParameters();
    
    /**
     * Sets the "parameters" element
     */
    void setParameters(java.lang.String parameters);
    
    /**
     * Sets (as xml) the "parameters" element
     */
    void xsetParameters(org.apache.xmlbeans.XmlString parameters);
    
    /**
     * Unsets the "parameters" element
     */
    void unsetParameters();
    
    /**
     * Gets the "regardingobjectid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup getRegardingobjectid();
    
    /**
     * True if has "regardingobjectid" element
     */
    boolean isSetRegardingobjectid();
    
    /**
     * Sets the "regardingobjectid" element
     */
    void setRegardingobjectid(com.microsoft.schemas.crm._2006.webservices.Lookup regardingobjectid);
    
    /**
     * Appends and returns a new empty "regardingobjectid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup addNewRegardingobjectid();
    
    /**
     * Unsets the "regardingobjectid" element
     */
    void unsetRegardingobjectid();
    
    /**
     * Gets the "scheduleddurationminutes" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber getScheduleddurationminutes();
    
    /**
     * True if has "scheduleddurationminutes" element
     */
    boolean isSetScheduleddurationminutes();
    
    /**
     * Sets the "scheduleddurationminutes" element
     */
    void setScheduleddurationminutes(com.microsoft.schemas.crm._2006.webservices.CrmNumber scheduleddurationminutes);
    
    /**
     * Appends and returns a new empty "scheduleddurationminutes" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber addNewScheduleddurationminutes();
    
    /**
     * Unsets the "scheduleddurationminutes" element
     */
    void unsetScheduleddurationminutes();
    
    /**
     * Gets the "scheduledend" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime getScheduledend();
    
    /**
     * True if has "scheduledend" element
     */
    boolean isSetScheduledend();
    
    /**
     * Sets the "scheduledend" element
     */
    void setScheduledend(com.microsoft.schemas.crm._2006.webservices.CrmDateTime scheduledend);
    
    /**
     * Appends and returns a new empty "scheduledend" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime addNewScheduledend();
    
    /**
     * Unsets the "scheduledend" element
     */
    void unsetScheduledend();
    
    /**
     * Gets the "scheduledstart" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime getScheduledstart();
    
    /**
     * True if has "scheduledstart" element
     */
    boolean isSetScheduledstart();
    
    /**
     * Sets the "scheduledstart" element
     */
    void setScheduledstart(com.microsoft.schemas.crm._2006.webservices.CrmDateTime scheduledstart);
    
    /**
     * Appends and returns a new empty "scheduledstart" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmDateTime addNewScheduledstart();
    
    /**
     * Unsets the "scheduledstart" element
     */
    void unsetScheduledstart();
    
    /**
     * Gets the "serviceid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup getServiceid();
    
    /**
     * True if has "serviceid" element
     */
    boolean isSetServiceid();
    
    /**
     * Sets the "serviceid" element
     */
    void setServiceid(com.microsoft.schemas.crm._2006.webservices.Lookup serviceid);
    
    /**
     * Appends and returns a new empty "serviceid" element
     */
    com.microsoft.schemas.crm._2006.webservices.Lookup addNewServiceid();
    
    /**
     * Unsets the "serviceid" element
     */
    void unsetServiceid();
    
    /**
     * Gets the "statecode" element
     */
    com.microsoft.schemas.crm._2007.webservices.BulkOperationStateInfo getStatecode();
    
    /**
     * True if has "statecode" element
     */
    boolean isSetStatecode();
    
    /**
     * Sets the "statecode" element
     */
    void setStatecode(com.microsoft.schemas.crm._2007.webservices.BulkOperationStateInfo statecode);
    
    /**
     * Appends and returns a new empty "statecode" element
     */
    com.microsoft.schemas.crm._2007.webservices.BulkOperationStateInfo addNewStatecode();
    
    /**
     * Unsets the "statecode" element
     */
    void unsetStatecode();
    
    /**
     * Gets the "statuscode" element
     */
    com.microsoft.schemas.crm._2006.webservices.Status getStatuscode();
    
    /**
     * True if has "statuscode" element
     */
    boolean isSetStatuscode();
    
    /**
     * Sets the "statuscode" element
     */
    void setStatuscode(com.microsoft.schemas.crm._2006.webservices.Status statuscode);
    
    /**
     * Appends and returns a new empty "statuscode" element
     */
    com.microsoft.schemas.crm._2006.webservices.Status addNewStatuscode();
    
    /**
     * Unsets the "statuscode" element
     */
    void unsetStatuscode();
    
    /**
     * Gets the "subject" element
     */
    java.lang.String getSubject();
    
    /**
     * Gets (as xml) the "subject" element
     */
    org.apache.xmlbeans.XmlString xgetSubject();
    
    /**
     * True if has "subject" element
     */
    boolean isSetSubject();
    
    /**
     * Sets the "subject" element
     */
    void setSubject(java.lang.String subject);
    
    /**
     * Sets (as xml) the "subject" element
     */
    void xsetSubject(org.apache.xmlbeans.XmlString subject);
    
    /**
     * Unsets the "subject" element
     */
    void unsetSubject();
    
    /**
     * Gets the "successcount" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber getSuccesscount();
    
    /**
     * True if has "successcount" element
     */
    boolean isSetSuccesscount();
    
    /**
     * Sets the "successcount" element
     */
    void setSuccesscount(com.microsoft.schemas.crm._2006.webservices.CrmNumber successcount);
    
    /**
     * Appends and returns a new empty "successcount" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber addNewSuccesscount();
    
    /**
     * Unsets the "successcount" element
     */
    void unsetSuccesscount();
    
    /**
     * Gets the "targetedrecordtypecode" element
     */
    com.microsoft.schemas.crm._2006.webservices.Picklist getTargetedrecordtypecode();
    
    /**
     * True if has "targetedrecordtypecode" element
     */
    boolean isSetTargetedrecordtypecode();
    
    /**
     * Sets the "targetedrecordtypecode" element
     */
    void setTargetedrecordtypecode(com.microsoft.schemas.crm._2006.webservices.Picklist targetedrecordtypecode);
    
    /**
     * Appends and returns a new empty "targetedrecordtypecode" element
     */
    com.microsoft.schemas.crm._2006.webservices.Picklist addNewTargetedrecordtypecode();
    
    /**
     * Unsets the "targetedrecordtypecode" element
     */
    void unsetTargetedrecordtypecode();
    
    /**
     * Gets the "targetmemberscount" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber getTargetmemberscount();
    
    /**
     * True if has "targetmemberscount" element
     */
    boolean isSetTargetmemberscount();
    
    /**
     * Sets the "targetmemberscount" element
     */
    void setTargetmemberscount(com.microsoft.schemas.crm._2006.webservices.CrmNumber targetmemberscount);
    
    /**
     * Appends and returns a new empty "targetmemberscount" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber addNewTargetmemberscount();
    
    /**
     * Unsets the "targetmemberscount" element
     */
    void unsetTargetmemberscount();
    
    /**
     * Gets the "timezoneruleversionnumber" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber getTimezoneruleversionnumber();
    
    /**
     * True if has "timezoneruleversionnumber" element
     */
    boolean isSetTimezoneruleversionnumber();
    
    /**
     * Sets the "timezoneruleversionnumber" element
     */
    void setTimezoneruleversionnumber(com.microsoft.schemas.crm._2006.webservices.CrmNumber timezoneruleversionnumber);
    
    /**
     * Appends and returns a new empty "timezoneruleversionnumber" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber addNewTimezoneruleversionnumber();
    
    /**
     * Unsets the "timezoneruleversionnumber" element
     */
    void unsetTimezoneruleversionnumber();
    
    /**
     * Gets the "utcconversiontimezonecode" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber getUtcconversiontimezonecode();
    
    /**
     * True if has "utcconversiontimezonecode" element
     */
    boolean isSetUtcconversiontimezonecode();
    
    /**
     * Sets the "utcconversiontimezonecode" element
     */
    void setUtcconversiontimezonecode(com.microsoft.schemas.crm._2006.webservices.CrmNumber utcconversiontimezonecode);
    
    /**
     * Appends and returns a new empty "utcconversiontimezonecode" element
     */
    com.microsoft.schemas.crm._2006.webservices.CrmNumber addNewUtcconversiontimezonecode();
    
    /**
     * Unsets the "utcconversiontimezonecode" element
     */
    void unsetUtcconversiontimezonecode();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation newInstance() {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static com.microsoft.schemas.crm._2007.webservices.Bulkoperation parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.microsoft.schemas.crm._2007.webservices.Bulkoperation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
