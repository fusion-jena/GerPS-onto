//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.03.06 at 11:34:55 AM UTC 
//


package uni.jena.fim.xdatenfelder.v2.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Technische Angaben für alle Nachrichten.
 * 
 * <p>Java class for Header complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Header"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nachrichtID" type="{http://www.w3.org/2001/XMLSchema}token"/&gt;
 *         &lt;element name="erstellungszeitpunkt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="referenzID" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Header", propOrder = {
    "nachrichtID",
    "erstellungszeitpunkt",
    "referenzID"
})
public class Header {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String nachrichtID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar erstellungszeitpunkt;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String referenzID;

    /**
     * Gets the value of the nachrichtID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNachrichtID() {
        return nachrichtID;
    }

    /**
     * Sets the value of the nachrichtID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNachrichtID(String value) {
        this.nachrichtID = value;
    }

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
     * Gets the value of the referenzID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenzID() {
        return referenzID;
    }

    /**
     * Sets the value of the referenzID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenzID(String value) {
        this.referenzID = value;
    }

}
