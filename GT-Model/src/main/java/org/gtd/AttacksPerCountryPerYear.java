/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.gtd;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class AttacksPerCountryPerYear extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AttacksPerCountryPerYear\",\"namespace\":\"org.gtd\",\"fields\":[{\"name\":\"year\",\"type\":\"string\"},{\"name\":\"attackByCountry\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AttacksPerCountry\",\"fields\":[{\"name\":\"country\",\"type\":\"string\"},{\"name\":\"count\",\"type\":\"int\"},{\"name\":\"attackDetails\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"AttackDetails\",\"fields\":[{\"name\":\"location\",\"type\":{\"type\":\"record\",\"name\":\"Location\",\"fields\":[{\"name\":\"region\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"country\",\"type\":\"string\"},{\"name\":\"state\",\"type\":\"string\"},{\"name\":\"city\",\"type\":\"string\"},{\"name\":\"lattitude\",\"type\":\"string\"},{\"name\":\"longitude\",\"type\":\"string\"}]}},{\"name\":\"time\",\"type\":{\"type\":\"record\",\"name\":\"Time\",\"fields\":[{\"name\":\"year\",\"type\":\"string\"},{\"name\":\"month\",\"type\":\"string\"},{\"name\":\"dt\",\"type\":\"string\"}]}},{\"name\":\"attack\",\"type\":{\"type\":\"record\",\"name\":\"Attack\",\"fields\":[{\"name\":\"suicide\",\"type\":\"boolean\"},{\"name\":\"type\",\"type\":\"string\"},{\"name\":\"motive\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"weapon\",\"type\":[\"null\",\"string\"],\"default\":null}]}},{\"name\":\"target\",\"type\":{\"type\":\"record\",\"name\":\"Target\",\"fields\":[{\"name\":\"type\",\"type\":\"string\"},{\"name\":\"subType\",\"type\":\"string\"},{\"name\":\"corp\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"target1\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"nationality1\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"target2\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"nationality2\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"target3\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"nationality3\",\"type\":[\"null\",\"string\"],\"default\":null}]}}]}}}]}}}]}");
  @Deprecated public java.lang.CharSequence year;
  @Deprecated public java.util.List<org.gtd.AttacksPerCountry> attackByCountry;

  /**
   * Default constructor.
   */
  public AttacksPerCountryPerYear() {}

  /**
   * All-args constructor.
   */
  public AttacksPerCountryPerYear(java.lang.CharSequence year, java.util.List<org.gtd.AttacksPerCountry> attackByCountry) {
    this.year = year;
    this.attackByCountry = attackByCountry;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return year;
    case 1: return attackByCountry;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: year = (java.lang.CharSequence)value$; break;
    case 1: attackByCountry = (java.util.List<org.gtd.AttacksPerCountry>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'year' field.
   */
  public java.lang.CharSequence getYear() {
    return year;
  }

  /**
   * Sets the value of the 'year' field.
   * @param value the value to set.
   */
  public void setYear(java.lang.CharSequence value) {
    this.year = value;
  }

  /**
   * Gets the value of the 'attackByCountry' field.
   */
  public java.util.List<org.gtd.AttacksPerCountry> getAttackByCountry() {
    return attackByCountry;
  }

  /**
   * Sets the value of the 'attackByCountry' field.
   * @param value the value to set.
   */
  public void setAttackByCountry(java.util.List<org.gtd.AttacksPerCountry> value) {
    this.attackByCountry = value;
  }

  /** Creates a new AttacksPerCountryPerYear RecordBuilder */
  public static org.gtd.AttacksPerCountryPerYear.Builder newBuilder() {
    return new org.gtd.AttacksPerCountryPerYear.Builder();
  }
  
  /** Creates a new AttacksPerCountryPerYear RecordBuilder by copying an existing Builder */
  public static org.gtd.AttacksPerCountryPerYear.Builder newBuilder(org.gtd.AttacksPerCountryPerYear.Builder other) {
    return new org.gtd.AttacksPerCountryPerYear.Builder(other);
  }
  
  /** Creates a new AttacksPerCountryPerYear RecordBuilder by copying an existing AttacksPerCountryPerYear instance */
  public static org.gtd.AttacksPerCountryPerYear.Builder newBuilder(org.gtd.AttacksPerCountryPerYear other) {
    return new org.gtd.AttacksPerCountryPerYear.Builder(other);
  }
  
  /**
   * RecordBuilder for AttacksPerCountryPerYear instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AttacksPerCountryPerYear>
    implements org.apache.avro.data.RecordBuilder<AttacksPerCountryPerYear> {

    private java.lang.CharSequence year;
    private java.util.List<org.gtd.AttacksPerCountry> attackByCountry;

    /** Creates a new Builder */
    private Builder() {
      super(org.gtd.AttacksPerCountryPerYear.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(org.gtd.AttacksPerCountryPerYear.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing AttacksPerCountryPerYear instance */
    private Builder(org.gtd.AttacksPerCountryPerYear other) {
            super(org.gtd.AttacksPerCountryPerYear.SCHEMA$);
      if (isValidValue(fields()[0], other.year)) {
        this.year = data().deepCopy(fields()[0].schema(), other.year);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.attackByCountry)) {
        this.attackByCountry = data().deepCopy(fields()[1].schema(), other.attackByCountry);
        fieldSetFlags()[1] = true;
      }
    }

    /** Gets the value of the 'year' field */
    public java.lang.CharSequence getYear() {
      return year;
    }
    
    /** Sets the value of the 'year' field */
    public org.gtd.AttacksPerCountryPerYear.Builder setYear(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.year = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'year' field has been set */
    public boolean hasYear() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'year' field */
    public org.gtd.AttacksPerCountryPerYear.Builder clearYear() {
      year = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'attackByCountry' field */
    public java.util.List<org.gtd.AttacksPerCountry> getAttackByCountry() {
      return attackByCountry;
    }
    
    /** Sets the value of the 'attackByCountry' field */
    public org.gtd.AttacksPerCountryPerYear.Builder setAttackByCountry(java.util.List<org.gtd.AttacksPerCountry> value) {
      validate(fields()[1], value);
      this.attackByCountry = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'attackByCountry' field has been set */
    public boolean hasAttackByCountry() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'attackByCountry' field */
    public org.gtd.AttacksPerCountryPerYear.Builder clearAttackByCountry() {
      attackByCountry = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    public AttacksPerCountryPerYear build() {
      try {
        AttacksPerCountryPerYear record = new AttacksPerCountryPerYear();
        record.year = fieldSetFlags()[0] ? this.year : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.attackByCountry = fieldSetFlags()[1] ? this.attackByCountry : (java.util.List<org.gtd.AttacksPerCountry>) defaultValue(fields()[1]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
