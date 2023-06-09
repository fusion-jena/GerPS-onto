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
import javax.xml.bind.annotation.XmlType;


/**
 * Angaben zur Referenzierung einer Prozessklasse auf eine andere Prozessklasse.
 * 
 * <p>Java class for ReferenzProzessklasse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferenzProzessklasse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="referenzierteProzessklasseID" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin"/&gt;
 *         &lt;element name="erlaeuterung" type="{http://xoev.de/latinchars/1_1/datatypes}String.Latin" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenzProzessklasse", propOrder = {
    "referenzierteProzessklasseID",
    "erlaeuterung"
})
public class ReferenzProzessklasse {

    @XmlElement(required = true)
    protected String referenzierteProzessklasseID;
    protected String erlaeuterung;

    /**
     * Gets the value of the referenzierteProzessklasseID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenzierteProzessklasseID() {
        return referenzierteProzessklasseID;
    }

    /**
     * Sets the value of the referenzierteProzessklasseID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenzierteProzessklasseID(String value) {
        this.referenzierteProzessklasseID = value;
    }

    /**
     * Gets the value of the erlaeuterung property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErlaeuterung() {
        return erlaeuterung;
    }

    /**
     * Sets the value of the erlaeuterung property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErlaeuterung(String value) {
        this.erlaeuterung = value;
    }

}
