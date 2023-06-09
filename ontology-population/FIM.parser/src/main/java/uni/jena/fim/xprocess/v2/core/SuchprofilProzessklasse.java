//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.06 at 10:07:40 AM UTC 
//


package uni.jena.fim.xprocess.v2.core;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Es werden die Prozessklassen des Prozesskatalogs auf diejenigen eingeschränkt, die allen Suchparametern entsprechen.
 * 
 * <p>Java class for Suchprofil.Prozessklasse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Suchprofil.Prozessklasse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="suchtext" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin" minOccurs="0"/&gt;
 *         &lt;element name="sucheGliederungsebeneNummer" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="sucheHandlungsgrundlage" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin" minOccurs="0"/&gt;
 *         &lt;element name="sucheZiel" type="{http://www.regierung-mv.de/xprozess/2}Code.OperativesZiel" minOccurs="0"/&gt;
 *         &lt;element name="sucheHandlungsform" type="{http://www.regierung-mv.de/xprozess/2}Code.Handlungsform" minOccurs="0"/&gt;
 *         &lt;element name="sucheVerfahrensart" type="{http://www.regierung-mv.de/xprozess/2}Code.Verfahrensart" minOccurs="0"/&gt;
 *         &lt;element name="sucheVerwaltungspolitischeKodierung" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="sucheMerkmal" type="{http://www.regierung-mv.de/xprozess/2}Suchprofil.Merkmal" minOccurs="0"/&gt;
 *         &lt;element name="sucheKlassifikation" type="{http://www.regierung-mv.de/xprozess/2}Suchprofil.Klassifikation" minOccurs="0"/&gt;
 *         &lt;element name="aenderungszeitpunkt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="sucheStatus" type="{http://www.regierung-mv.de/xprozess/2}Code.Status" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="sucheGueltigkeit" type="{http://www.regierung-mv.de/xprozess/2}Zeitraum" minOccurs="0"/&gt;
 *         &lt;element name="sucheFachlicherFreigabezeitpunkt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="sucheFormellerFreigabezeitpunkt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="sucheHatProzesse" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Suchprofil.Prozessklasse", propOrder = {
    "suchtext",
    "sucheGliederungsebeneNummer",
    "sucheHandlungsgrundlage",
    "sucheZiel",
    "sucheHandlungsform",
    "sucheVerfahrensart",
    "sucheVerwaltungspolitischeKodierung",
    "sucheMerkmal",
    "sucheKlassifikation",
    "aenderungszeitpunkt",
    "sucheStatus",
    "sucheGueltigkeit",
    "sucheFachlicherFreigabezeitpunkt",
    "sucheFormellerFreigabezeitpunkt",
    "sucheHatProzesse"
})
public class SuchprofilProzessklasse {

    protected String suchtext;
    protected List<BigInteger> sucheGliederungsebeneNummer;
    protected String sucheHandlungsgrundlage;
    protected CodeOperativesZiel sucheZiel;
    protected CodeHandlungsform sucheHandlungsform;
    protected CodeVerfahrensart sucheVerfahrensart;
    protected List<String> sucheVerwaltungspolitischeKodierung;
    protected SuchprofilMerkmal sucheMerkmal;
    protected SuchprofilKlassifikation sucheKlassifikation;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar aenderungszeitpunkt;
    protected List<CodeStatus> sucheStatus;
    protected Zeitraum sucheGueltigkeit;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sucheFachlicherFreigabezeitpunkt;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sucheFormellerFreigabezeitpunkt;
    protected Boolean sucheHatProzesse;

    /**
     * Gets the value of the suchtext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuchtext() {
        return suchtext;
    }

    /**
     * Sets the value of the suchtext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuchtext(String value) {
        this.suchtext = value;
    }

    /**
     * Gets the value of the sucheGliederungsebeneNummer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sucheGliederungsebeneNummer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSucheGliederungsebeneNummer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getSucheGliederungsebeneNummer() {
        if (sucheGliederungsebeneNummer == null) {
            sucheGliederungsebeneNummer = new ArrayList<BigInteger>();
        }
        return this.sucheGliederungsebeneNummer;
    }

    /**
     * Gets the value of the sucheHandlungsgrundlage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSucheHandlungsgrundlage() {
        return sucheHandlungsgrundlage;
    }

    /**
     * Sets the value of the sucheHandlungsgrundlage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSucheHandlungsgrundlage(String value) {
        this.sucheHandlungsgrundlage = value;
    }

    /**
     * Gets the value of the sucheZiel property.
     * 
     * @return
     *     possible object is
     *     {@link CodeOperativesZiel }
     *     
     */
    public CodeOperativesZiel getSucheZiel() {
        return sucheZiel;
    }

    /**
     * Sets the value of the sucheZiel property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeOperativesZiel }
     *     
     */
    public void setSucheZiel(CodeOperativesZiel value) {
        this.sucheZiel = value;
    }

    /**
     * Gets the value of the sucheHandlungsform property.
     * 
     * @return
     *     possible object is
     *     {@link CodeHandlungsform }
     *     
     */
    public CodeHandlungsform getSucheHandlungsform() {
        return sucheHandlungsform;
    }

    /**
     * Sets the value of the sucheHandlungsform property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeHandlungsform }
     *     
     */
    public void setSucheHandlungsform(CodeHandlungsform value) {
        this.sucheHandlungsform = value;
    }

    /**
     * Gets the value of the sucheVerfahrensart property.
     * 
     * @return
     *     possible object is
     *     {@link CodeVerfahrensart }
     *     
     */
    public CodeVerfahrensart getSucheVerfahrensart() {
        return sucheVerfahrensart;
    }

    /**
     * Sets the value of the sucheVerfahrensart property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeVerfahrensart }
     *     
     */
    public void setSucheVerfahrensart(CodeVerfahrensart value) {
        this.sucheVerfahrensart = value;
    }

    /**
     * Gets the value of the sucheVerwaltungspolitischeKodierung property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sucheVerwaltungspolitischeKodierung property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSucheVerwaltungspolitischeKodierung().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSucheVerwaltungspolitischeKodierung() {
        if (sucheVerwaltungspolitischeKodierung == null) {
            sucheVerwaltungspolitischeKodierung = new ArrayList<String>();
        }
        return this.sucheVerwaltungspolitischeKodierung;
    }

    /**
     * Gets the value of the sucheMerkmal property.
     * 
     * @return
     *     possible object is
     *     {@link SuchprofilMerkmal }
     *     
     */
    public SuchprofilMerkmal getSucheMerkmal() {
        return sucheMerkmal;
    }

    /**
     * Sets the value of the sucheMerkmal property.
     * 
     * @param value
     *     allowed object is
     *     {@link SuchprofilMerkmal }
     *     
     */
    public void setSucheMerkmal(SuchprofilMerkmal value) {
        this.sucheMerkmal = value;
    }

    /**
     * Gets the value of the sucheKlassifikation property.
     * 
     * @return
     *     possible object is
     *     {@link SuchprofilKlassifikation }
     *     
     */
    public SuchprofilKlassifikation getSucheKlassifikation() {
        return sucheKlassifikation;
    }

    /**
     * Sets the value of the sucheKlassifikation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SuchprofilKlassifikation }
     *     
     */
    public void setSucheKlassifikation(SuchprofilKlassifikation value) {
        this.sucheKlassifikation = value;
    }

    /**
     * Gets the value of the aenderungszeitpunkt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAenderungszeitpunkt() {
        return aenderungszeitpunkt;
    }

    /**
     * Sets the value of the aenderungszeitpunkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAenderungszeitpunkt(XMLGregorianCalendar value) {
        this.aenderungszeitpunkt = value;
    }

    /**
     * Gets the value of the sucheStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sucheStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSucheStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodeStatus }
     * 
     * 
     */
    public List<CodeStatus> getSucheStatus() {
        if (sucheStatus == null) {
            sucheStatus = new ArrayList<CodeStatus>();
        }
        return this.sucheStatus;
    }

    /**
     * Gets the value of the sucheGueltigkeit property.
     * 
     * @return
     *     possible object is
     *     {@link Zeitraum }
     *     
     */
    public Zeitraum getSucheGueltigkeit() {
        return sucheGueltigkeit;
    }

    /**
     * Sets the value of the sucheGueltigkeit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zeitraum }
     *     
     */
    public void setSucheGueltigkeit(Zeitraum value) {
        this.sucheGueltigkeit = value;
    }

    /**
     * Gets the value of the sucheFachlicherFreigabezeitpunkt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSucheFachlicherFreigabezeitpunkt() {
        return sucheFachlicherFreigabezeitpunkt;
    }

    /**
     * Sets the value of the sucheFachlicherFreigabezeitpunkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSucheFachlicherFreigabezeitpunkt(XMLGregorianCalendar value) {
        this.sucheFachlicherFreigabezeitpunkt = value;
    }

    /**
     * Gets the value of the sucheFormellerFreigabezeitpunkt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSucheFormellerFreigabezeitpunkt() {
        return sucheFormellerFreigabezeitpunkt;
    }

    /**
     * Sets the value of the sucheFormellerFreigabezeitpunkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSucheFormellerFreigabezeitpunkt(XMLGregorianCalendar value) {
        this.sucheFormellerFreigabezeitpunkt = value;
    }

    /**
     * Gets the value of the sucheHatProzesse property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSucheHatProzesse() {
        return sucheHatProzesse;
    }

    /**
     * Sets the value of the sucheHatProzesse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSucheHatProzesse(Boolean value) {
        this.sucheHatProzesse = value;
    }

}
