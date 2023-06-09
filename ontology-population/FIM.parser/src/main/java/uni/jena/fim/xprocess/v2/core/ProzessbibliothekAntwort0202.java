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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.regierung-mv.de/xprozess/2}Basisnachricht"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="anfrageUUID" type="{http://www.regierung-mv.de/xprozess/2}String.UUID"/&gt;
 *         &lt;element name="suchprofilZitat" type="{http://www.regierung-mv.de/xprozess/2}Suchprofil.Prozess" minOccurs="0"/&gt;
 *         &lt;element name="prozessbibliothek" type="{http://www.regierung-mv.de/xprozess/2}Prozessbibliothek" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "anfrageUUID",
    "suchprofilZitat",
    "prozessbibliothek"
})
@XmlRootElement(name = "prozessbibliothek.antwort.0202")
public class ProzessbibliothekAntwort0202
    extends Basisnachricht
{

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String anfrageUUID;
    protected SuchprofilProzess suchprofilZitat;
    protected Prozessbibliothek prozessbibliothek;

    /**
     * Gets the value of the anfrageUUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnfrageUUID() {
        return anfrageUUID;
    }

    /**
     * Sets the value of the anfrageUUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnfrageUUID(String value) {
        this.anfrageUUID = value;
    }

    /**
     * Gets the value of the suchprofilZitat property.
     * 
     * @return
     *     possible object is
     *     {@link SuchprofilProzess }
     *     
     */
    public SuchprofilProzess getSuchprofilZitat() {
        return suchprofilZitat;
    }

    /**
     * Sets the value of the suchprofilZitat property.
     * 
     * @param value
     *     allowed object is
     *     {@link SuchprofilProzess }
     *     
     */
    public void setSuchprofilZitat(SuchprofilProzess value) {
        this.suchprofilZitat = value;
    }

    /**
     * Gets the value of the prozessbibliothek property.
     * 
     * @return
     *     possible object is
     *     {@link Prozessbibliothek }
     *     
     */
    public Prozessbibliothek getProzessbibliothek() {
        return prozessbibliothek;
    }

    /**
     * Sets the value of the prozessbibliothek property.
     * 
     * @param value
     *     allowed object is
     *     {@link Prozessbibliothek }
     *     
     */
    public void setProzessbibliothek(Prozessbibliothek value) {
        this.prozessbibliothek = value;
    }

}
