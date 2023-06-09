//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.06 at 10:07:40 AM UTC 
//


package uni.jena.fim.xprocess.v2.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Angaben zum Zustand einer Prozessklasse oder eines Prozesses.
 * 
 * <p>Java class for Zustandsangaben complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Zustandsangaben"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="erstellungszeitpunkt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="letzterAenderungszeitpunkt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="letzterBearbeiter" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.regierung-mv.de/xprozess/2}Code.Status" minOccurs="0"/&gt;
 *         &lt;element name="anmerkungLetzteAenderung" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin" minOccurs="0"/&gt;
 *         &lt;element name="gueltigkeitszeitraum" type="{http://www.regierung-mv.de/xprozess/2}Zeitraum" minOccurs="0"/&gt;
 *         &lt;element name="fachlicherFreigabezeitpunkt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="formellerFreigabezeitpunkt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Zustandsangaben", propOrder = {
    "erstellungszeitpunkt",
    "letzterAenderungszeitpunkt",
    "letzterBearbeiter",
    "status",
    "anmerkungLetzteAenderung",
    "gueltigkeitszeitraum",
    "fachlicherFreigabezeitpunkt",
    "formellerFreigabezeitpunkt"
})
public class Zustandsangaben {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar erstellungszeitpunkt;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar letzterAenderungszeitpunkt;
    protected String letzterBearbeiter;
    protected CodeStatus status;
    protected String anmerkungLetzteAenderung;
    protected Zeitraum gueltigkeitszeitraum;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fachlicherFreigabezeitpunkt;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar formellerFreigabezeitpunkt;

    /**
     * Gets the value of the erstellungszeitpunkt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getErstellungszeitpunkt() {
        return erstellungszeitpunkt;
    }

    /**
     * Sets the value of the erstellungszeitpunkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setErstellungszeitpunkt(XMLGregorianCalendar value) {
        this.erstellungszeitpunkt = value;
    }

    /**
     * Gets the value of the letzterAenderungszeitpunkt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLetzterAenderungszeitpunkt() {
        return letzterAenderungszeitpunkt;
    }

    /**
     * Sets the value of the letzterAenderungszeitpunkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLetzterAenderungszeitpunkt(XMLGregorianCalendar value) {
        this.letzterAenderungszeitpunkt = value;
    }

    /**
     * Gets the value of the letzterBearbeiter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLetzterBearbeiter() {
        return letzterBearbeiter;
    }

    /**
     * Sets the value of the letzterBearbeiter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLetzterBearbeiter(String value) {
        this.letzterBearbeiter = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link CodeStatus }
     *     
     */
    public CodeStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeStatus }
     *     
     */
    public void setStatus(CodeStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the anmerkungLetzteAenderung property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnmerkungLetzteAenderung() {
        return anmerkungLetzteAenderung;
    }

    /**
     * Sets the value of the anmerkungLetzteAenderung property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnmerkungLetzteAenderung(String value) {
        this.anmerkungLetzteAenderung = value;
    }

    /**
     * Gets the value of the gueltigkeitszeitraum property.
     * 
     * @return
     *     possible object is
     *     {@link Zeitraum }
     *     
     */
    public Zeitraum getGueltigkeitszeitraum() {
        return gueltigkeitszeitraum;
    }

    /**
     * Sets the value of the gueltigkeitszeitraum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zeitraum }
     *     
     */
    public void setGueltigkeitszeitraum(Zeitraum value) {
        this.gueltigkeitszeitraum = value;
    }

    /**
     * Gets the value of the fachlicherFreigabezeitpunkt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFachlicherFreigabezeitpunkt() {
        return fachlicherFreigabezeitpunkt;
    }

    /**
     * Sets the value of the fachlicherFreigabezeitpunkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFachlicherFreigabezeitpunkt(XMLGregorianCalendar value) {
        this.fachlicherFreigabezeitpunkt = value;
    }

    /**
     * Gets the value of the formellerFreigabezeitpunkt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFormellerFreigabezeitpunkt() {
        return formellerFreigabezeitpunkt;
    }

    /**
     * Sets the value of the formellerFreigabezeitpunkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFormellerFreigabezeitpunkt(XMLGregorianCalendar value) {
        this.formellerFreigabezeitpunkt = value;
    }

}
