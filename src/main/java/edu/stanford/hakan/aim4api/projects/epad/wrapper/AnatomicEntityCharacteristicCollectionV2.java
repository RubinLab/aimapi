/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import java.util.ArrayList;
import java.util.List;
import edu.stanford.hakan.aim4api.base.AimException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Hakan
 */
public class AnatomicEntityCharacteristicCollectionV2 {

    private final List<AnatomicEntityCharacteristic> listAnatomicEntityCharacteristic = new ArrayList<AnatomicEntityCharacteristic>();

    public void addAnatomicEntityCharacteristic(AnatomicEntityCharacteristic newAnatomicEntityCharacteristic) {
        this.listAnatomicEntityCharacteristic.add(newAnatomicEntityCharacteristic);
    }

    public List<AnatomicEntityCharacteristic> getAnatomicEntityCharacteristicList() {
        return this.listAnatomicEntityCharacteristic;
    }

    public int size() {
        return this.listAnatomicEntityCharacteristic.size();
    }

    public AnatomicEntityCharacteristic get(int index) {
        if (index <= this.listAnatomicEntityCharacteristic.size() - 1) {
            return this.listAnatomicEntityCharacteristic.get(index);
        }
        return null;
    }

    public Node getXMLNode(Document doc) throws AimException {

        Element AnatomicEntityCharacteristicCollection = doc
                .createElement("imagingPhysicalEntityCharacteristicCollection");
        for (int i = 0; i < this.listAnatomicEntityCharacteristic.size(); i++) {
            this.listAnatomicEntityCharacteristic.get(i).setTagName("ImagingPhysicalEntityCharacteristic");
            AnatomicEntityCharacteristicCollection.appendChild(this.listAnatomicEntityCharacteristic.get(i)
                    .getXMLNode(doc));
        }
        return AnatomicEntityCharacteristicCollection;
    }

    public void setXMLNode(Node node) {
        this.listAnatomicEntityCharacteristic.clear();
        NodeList tempList = node.getChildNodes();
        for (int j = 0; j < tempList.getLength(); j++) {
            Node currentNode = tempList.item(j);
            if ("ImagingPhysicalEntityCharacteristic".equals(currentNode.getNodeName())) {
                NamedNodeMap map = currentNode.getAttributes();
                if (map.getNamedItem("xsi:type") != null) {
                    String xsiType = map.getNamedItem("xsi:type").getNodeValue();

                } else {
                    AnatomicEntityCharacteristic obj = new AnatomicEntityCharacteristic();
                    obj.setXMLNode(tempList.item(j));
                    this.addAnatomicEntityCharacteristic(obj);
                }
            }
        }
    }

    public Node getRDFNode(Document doc, String unquieID, String Prefix) throws AimException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isEqualTo(Object other) {
        AnatomicEntityCharacteristicCollectionV2 oth = (AnatomicEntityCharacteristicCollectionV2) other;
        if (this.listAnatomicEntityCharacteristic.size() != oth.listAnatomicEntityCharacteristic.size()) {
            return false;
        }
        for (int i = 0; i < this.listAnatomicEntityCharacteristic.size(); i++) {
            if (this.listAnatomicEntityCharacteristic.get(i) == null ? oth.listAnatomicEntityCharacteristic.get(i) != null : !this.listAnatomicEntityCharacteristic.get(i).isEqualTo(oth.listAnatomicEntityCharacteristic.get(i))) {
                return false;
            }
        }
        return true;
    }

    public AnatomicEntityCharacteristicCollectionV2 getClone() {
        AnatomicEntityCharacteristicCollectionV2 res = new AnatomicEntityCharacteristicCollectionV2();
        for (int i = 0; i < this.listAnatomicEntityCharacteristic.size(); i++) {
            if (this.listAnatomicEntityCharacteristic.get(i) != null) {
                res.addAnatomicEntityCharacteristic(this.listAnatomicEntityCharacteristic.get(i).getClone());
            }
        }
        return res;
    }
}
