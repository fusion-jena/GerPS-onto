//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.06 at 10:07:40 AM UTC 
//


package uni.jena.fim.xprocess.v2.core;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Dieses Element bündelt alle Informationen zu einem konkreten Prozess in einer Version.
 * 
 * <p>Java class for Prozess complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Prozess"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin"/&gt;
 *         &lt;element name="version" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin"/&gt;
 *         &lt;element name="bezeichnung" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin" minOccurs="0"/&gt;
 *         &lt;element name="fachlichFreigebendeStelle" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin" minOccurs="0"/&gt;
 *         &lt;element name="klassifikation" type="{http://www.regierung-mv.de/xprozess/2}Klassifikation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="schlagwort" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="prozesssteckbrief" type="{http://www.regierung-mv.de/xprozess/2}Prozesssteckbrief" minOccurs="0"/&gt;
 *         &lt;element name="prozessstrukturbeschreibung" type="{http://www.regierung-mv.de/xprozess/2}Strukturbeschreibung" minOccurs="0"/&gt;
 *         &lt;element name="prozessmodell" type="{http://www.regierung-mv.de/xprozess/2}Prozessmodell" minOccurs="0"/&gt;
 *         &lt;element name="zustandsangaben" type="{http://www.regierung-mv.de/xprozess/2}Zustandsangaben" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Prozess", propOrder = {
    "id",
    "version",
    "name",
    "bezeichnung",
    "fachlichFreigebendeStelle",
    "klassifikation",
    "schlagwort",
    "prozesssteckbrief",
    "prozessstrukturbeschreibung",
    "prozessmodell",
    "zustandsangaben"
})
public class Prozess {

    @XmlElement(required = true)
    protected String id;
    protected String version;
    @XmlElement(required = true)
    protected String name;
    protected String bezeichnung;
    protected String fachlichFreigebendeStelle;
    protected List<Klassifikation> klassifikation;
    protected List<String> schlagwort;
    protected Prozesssteckbrief prozesssteckbrief;
    protected Strukturbeschreibung prozessstrukturbeschreibung;
    protected Prozessmodell prozessmodell;
    protected Zustandsangaben zustandsangaben;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the bezeichnung property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * Sets the value of the bezeichnung property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBezeichnung(String value) {
        this.bezeichnung = value;
    }

    /**
     * Gets the value of the fachlichFreigebendeStelle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFachlichFreigebendeStelle() {
        return fachlichFreigebendeStelle;
    }

    /**
     * Sets the value of the fachlichFreigebendeStelle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFachlichFreigebendeStelle(String value) {
        this.fachlichFreigebendeStelle = value;
    }

    /**
     * Gets the value of the klassifikation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the klassifikation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKlassifikation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Klassifikation }
     * 
     * 
     */
    public List<Klassifikation> getKlassifikation() {
        if (klassifikation == null) {
            klassifikation = new ArrayList<Klassifikation>();
        }
        return this.klassifikation;
    }

    /**
     * Gets the value of the schlagwort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the schlagwort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSchlagwort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSchlagwort() {
        if (schlagwort == null) {
            schlagwort = new ArrayList<String>();
        }
        return this.schlagwort;
    }

    /**
     * Gets the value of the prozesssteckbrief property.
     * 
     * @return
     *     possible object is
     *     {@link Prozesssteckbrief }
     *     
     */
    public Prozesssteckbrief getProzesssteckbrief() {
        return prozesssteckbrief;
    }

    /**
     * Sets the value of the prozesssteckbrief property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prozesssteckbrief }
     *     
     */
    public void setProzesssteckbrief(Prozesssteckbrief value) {
        this.prozesssteckbrief = value;
    }

    /**
     * Gets the value of the prozessstrukturbeschreibung property.
     * 
     * @return
     *     possible object is
     *     {@link Strukturbeschreibung }
     *     
     */
    public Strukturbeschreibung getProzessstrukturbeschreibung() {
        return prozessstrukturbeschreibung;
    }

    /**
     * Sets the value of the prozessstrukturbeschreibung property.
     * 
     * @param value
     *     allowed object is
     *     {@link Strukturbeschreibung }
     *     
     */
    public void setProzessstrukturbeschreibung(Strukturbeschreibung value) {
        this.prozessstrukturbeschreibung = value;
    }

    /**
     * Gets the value of the prozessmodell property.
     * 
     * @return
     *     possible object is
     *     {@link Prozessmodell }
     *     
     */
    public Prozessmodell getProzessmodell() {
        return prozessmodell;
    }

    /**
     * Sets the value of the prozessmodell property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prozessmodell }
     *     
     */
    public void setProzessmodell(Prozessmodell value) {
        this.prozessmodell = value;
    }

    /**
     * Gets the value of the zustandsangaben property.
     * 
     * @return
     *     possible object is
     *     {@link Zustandsangaben }
     *     
     */
    public Zustandsangaben getZustandsangaben() {
        return zustandsangaben;
    }

    /**
     * Sets the value of the zustandsangaben property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zustandsangaben }
     *     
     */
    public void setZustandsangaben(Zustandsangaben value) {
        this.zustandsangaben = value;
    }

}
