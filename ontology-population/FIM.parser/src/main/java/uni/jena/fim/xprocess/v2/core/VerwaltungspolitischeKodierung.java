//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.06 at 10:07:40 AM UTC 
//


package uni.jena.fim.xprocess.v2.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Die Komponente "VerwaltungspolitischeKodierung" beinhaltet Informationen, die eine verwaltungspolitisch eindeutige Zuordnung ermöglichen. Sie dient der Identifikation einer Gemeinde oder sonstiger Gebietskörperschaften (Kreis, Bezirk, Gemeindeverband, Bundesland, Nation).
 * 
 * <p>Java class for VerwaltungspolitischeKodierung complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VerwaltungspolitischeKodierung"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="kreis" type="{http://www.regierung-mv.de/xprozess/2}Code.Kreis"/&gt;
 *         &lt;element name="bezirk" type="{http://www.regierung-mv.de/xprozess/2}Code.Bezirk"/&gt;
 *         &lt;element name="bundesland" type="{http://www.regierung-mv.de/xprozess/2}Code.Bundesland"/&gt;
 *         &lt;element name="gemeindeschluessel" type="{http://www.regierung-mv.de/xprozess/2}Code.Gemeindeschluessel"/&gt;
 *         &lt;element name="regionalschluessel" type="{http://www.regierung-mv.de/xprozess/2}Code.Regionalschluessel"/&gt;
 *         &lt;element name="nation" type="{http://www.regierung-mv.de/xprozess/2}Code.Staat"/&gt;
 *         &lt;element name="gemeindeverband" type="{http://www.regierung-mv.de/xprozess/2}Code.Gemeindeverband"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerwaltungspolitischeKodierung", propOrder = {
    "kreis",
    "bezirk",
    "bundesland",
    "gemeindeschluessel",
    "regionalschluessel",
    "nation",
    "gemeindeverband"
})
public class VerwaltungspolitischeKodierung {

    protected CodeKreis kreis;
    protected CodeBezirk bezirk;
    protected CodeBundesland bundesland;
    protected CodeGemeindeschluessel gemeindeschluessel;
    protected CodeRegionalschluessel regionalschluessel;
    protected CodeStaat nation;
    protected CodeGemeindeverband gemeindeverband;

    /**
     * Gets the value of the kreis property.
     * 
     * @return
     *     possible object is
     *     {@link CodeKreis }
     *     
     */
    public CodeKreis getKreis() {
        return kreis;
    }

    /**
     * Sets the value of the kreis property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeKreis }
     *     
     */
    public void setKreis(CodeKreis value) {
        this.kreis = value;
    }

    /**
     * Gets the value of the bezirk property.
     * 
     * @return
     *     possible object is
     *     {@link CodeBezirk }
     *     
     */
    public CodeBezirk getBezirk() {
        return bezirk;
    }

    /**
     * Sets the value of the bezirk property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeBezirk }
     *     
     */
    public void setBezirk(CodeBezirk value) {
        this.bezirk = value;
    }

    /**
     * Gets the value of the bundesland property.
     * 
     * @return
     *     possible object is
     *     {@link CodeBundesland }
     *     
     */
    public CodeBundesland getBundesland() {
        return bundesland;
    }

    /**
     * Sets the value of the bundesland property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeBundesland }
     *     
     */
    public void setBundesland(CodeBundesland value) {
        this.bundesland = value;
    }

    /**
     * Gets the value of the gemeindeschluessel property.
     * 
     * @return
     *     possible object is
     *     {@link CodeGemeindeschluessel }
     *     
     */
    public CodeGemeindeschluessel getGemeindeschluessel() {
        return gemeindeschluessel;
    }

    /**
     * Sets the value of the gemeindeschluessel property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeGemeindeschluessel }
     *     
     */
    public void setGemeindeschluessel(CodeGemeindeschluessel value) {
        this.gemeindeschluessel = value;
    }

    /**
     * Gets the value of the regionalschluessel property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRegionalschluessel }
     *     
     */
    public CodeRegionalschluessel getRegionalschluessel() {
        return regionalschluessel;
    }

    /**
     * Sets the value of the regionalschluessel property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRegionalschluessel }
     *     
     */
    public void setRegionalschluessel(CodeRegionalschluessel value) {
        this.regionalschluessel = value;
    }

    /**
     * Gets the value of the nation property.
     * 
     * @return
     *     possible object is
     *     {@link CodeStaat }
     *     
     */
    public CodeStaat getNation() {
        return nation;
    }

    /**
     * Sets the value of the nation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeStaat }
     *     
     */
    public void setNation(CodeStaat value) {
        this.nation = value;
    }

    /**
     * Gets the value of the gemeindeverband property.
     * 
     * @return
     *     possible object is
     *     {@link CodeGemeindeverband }
     *     
     */
    public CodeGemeindeverband getGemeindeverband() {
        return gemeindeverband;
    }

    /**
     * Sets the value of the gemeindeverband property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeGemeindeverband }
     *     
     */
    public void setGemeindeverband(CodeGemeindeverband value) {
        this.gemeindeverband = value;
    }

}
