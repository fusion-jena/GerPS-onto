//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.03.21 at 02:07:50 PM UTC 
//


package uni.jena.bpmn.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for tAssociation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tAssociation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.omg.org/spec/BPMN/20100524/MODEL}tArtifact"&gt;
 *       &lt;attribute name="sourceRef" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *       &lt;attribute name="targetRef" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" /&gt;
 *       &lt;attribute name="associationDirection" type="{http://www.omg.org/spec/BPMN/20100524/MODEL}tAssociationDirection" default="None" /&gt;
 *       &lt;anyAttribute processContents='lax' namespace='##other'/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tAssociation")
public class TAssociation
    extends TArtifact
{

    @XmlAttribute(name = "sourceRef", required = true)
    protected QName sourceRef;
    @XmlAttribute(name = "targetRef", required = true)
    protected QName targetRef;
    @XmlAttribute(name = "associationDirection")
    protected TAssociationDirection associationDirection;

    /**
     * Gets the value of the sourceRef property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getSourceRef() {
        return sourceRef;
    }

    /**
     * Sets the value of the sourceRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setSourceRef(QName value) {
        this.sourceRef = value;
    }

    /**
     * Gets the value of the targetRef property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getTargetRef() {
        return targetRef;
    }

    /**
     * Sets the value of the targetRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setTargetRef(QName value) {
        this.targetRef = value;
    }

    /**
     * Gets the value of the associationDirection property.
     * 
     * @return
     *     possible object is
     *     {@link TAssociationDirection }
     *     
     */
    public TAssociationDirection getAssociationDirection() {
        if (associationDirection == null) {
            return TAssociationDirection.NONE;
        } else {
            return associationDirection;
        }
    }

    /**
     * Sets the value of the associationDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAssociationDirection }
     *     
     */
    public void setAssociationDirection(TAssociationDirection value) {
        this.associationDirection = value;
    }

}
