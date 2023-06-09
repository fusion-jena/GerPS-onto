//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.06 at 10:07:40 AM UTC 
//


package uni.jena.fim.xprocess.v2.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Nachrichtenkopf für Nachrichten zwischen Prozessrepositorys.
 * 
 * <p>Java class for Nachrichtenkopf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Nachrichtenkopf"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nachrichtUUID" type="{http://www.regierung-mv.de/xprozess/2}String.UUID"/&gt;
 *         &lt;element name="nachrichtentyp" type="{http://www.regierung-mv.de/xprozess/2}Code.Nachricht"/&gt;
 *         &lt;element name="erstellungszeitpunkt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="leser" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin" minOccurs="0"/&gt;
 *         &lt;element name="autor" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Nachrichtenkopf", propOrder = {
    "nachrichtUUID",
    "nachrichtentyp",
    "erstellungszeitpunkt",
    "leser",
    "autor"
})
public class Nachrichtenkopf {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String nachrichtUUID;
    @XmlElement(required = true)
    protected CodeNachricht nachrichtentyp;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar erstellungszeitpunkt;
    protected String leser;
    protected String autor;

    /**
     * Gets the value of the nachrichtUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNachrichtUUID() {
        return nachrichtUUID;
    }

    /**
     * Sets the value of the nachrichtUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNachrichtUUID(String value) {
        this.nachrichtUUID = value;
    }

    /**
     * Gets the value of the nachrichtentyp property.
     * 
     * @return
     *     possible object is
     *     {@link CodeNachricht }
     *     
     */
    public CodeNachricht getNachrichtentyp() {
        return nachrichtentyp;
    }

    /**
     * Sets the value of the nachrichtentyp property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeNachricht }
     *     
     */
    public void setNachrichtentyp(CodeNachricht value) {
        this.nachrichtentyp = value;
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
     * Gets the value of the leser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeser() {
        return leser;
    }

    /**
     * Sets the value of the leser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeser(String value) {
        this.leser = value;
    }

    /**
     * Gets the value of the autor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Sets the value of the autor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutor(String value) {
        this.autor = value;
    }

}
