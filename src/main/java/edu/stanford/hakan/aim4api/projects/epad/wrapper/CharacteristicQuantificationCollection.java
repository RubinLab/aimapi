/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stanford.hakan.aim4api.projects.epad.wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hakan
 */
public class CharacteristicQuantificationCollection extends edu.stanford.hakan.aim4api.base.CharacteristicQuantificationCollection {

    public void addCharacteristicQuantification(CharacteristicQuantification newCharacteristicQuantification) {
        super.addCharacteristicQuantification(newCharacteristicQuantification);
    }

    public List<CharacteristicQuantification> getCharacteristicQuantificationListEpad() {
        List<edu.stanford.hakan.aim4api.base.CharacteristicQuantification> temp = super.getCharacteristicQuantificationList();
        List<CharacteristicQuantification> res = new ArrayList<CharacteristicQuantification>();
        for (int i = 0; i < temp.size(); i++) {
            res.add((CharacteristicQuantification) temp.get(i));
        }
        return res;
    }

    @Override
    public CharacteristicQuantification get(int index) {
        if (index >= 0 && index <= super.getCharacteristicQuantificationList().size() - 1) {
            return (CharacteristicQuantification) super.getCharacteristicQuantificationList().get(index);
        }
        return null;
    }
}
