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
import javax.xml.bind.annotation.XmlType;


/**
 * Die Struktur listet ein Unterelement eines übergeordneten Elements auf.
 * 
 * <p>Java class for Struktur complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Struktur"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="anzahl" type="{urn:xoev-de:fim:standard:xdatenfelder_2}AnzahlString"/&gt;
 *         &lt;element name="bezug" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin"/&gt;
 *         &lt;element name="enthaelt" type="{urn:xoev-de:fim:standard:xdatenfelder_2}Enthaelt"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Struktur", propOrder = {
    "anzahl",
    "bezug",
    "enthaelt"
})
public class Struktur {

    @XmlElement(required = true)
    protected String anzahl;
    @XmlElement(required = true)
    protected String bezug;
    @XmlElement(required = true)
    protected Enthaelt enthaelt;

    /**
     * Gets the value of the anzahl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnzahl() {
        return anzahl;
    }

    /**
     * Sets the value of the anzahl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnzahl(String value) {
        this.anzahl = value;
    }

    /**
     * Gets the value of the bezug property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBezug() {
        return bezug;
    }

    /**
     * Sets the value of the bezug property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBezug(String value) {
        this.bezug = value;
    }

    /**
     * Gets the value of the enthaelt property.
     * 
     * @return
     *     possible object is
     *     {@link Enthaelt }
     *     
     */
    public Enthaelt getEnthaelt() {
        return enthaelt;
    }

    /**
     * Sets the value of the enthaelt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Enthaelt }
     *     
     */
    public void setEnthaelt(Enthaelt value) {
        this.enthaelt = value;
    }

}
