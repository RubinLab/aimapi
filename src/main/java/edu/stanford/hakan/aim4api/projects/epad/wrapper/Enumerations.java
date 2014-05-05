/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stanford.hakan.aim4api.projects.epad.wrapper;

/**
 *
 * @author Hakan
 */
public class Enumerations {

    public enum ROIShape {

        NONE, LINE, RECTANGLE, CIRCLE, OVAL, POLY, OPENPOLY, COMMENT, ARROW, SPLINE, OPENSPLINE, TEXT, ELLIPSE, NORMAL
    }

    public enum QuestionType {

        ANATOMICENTITY, IMAGINGOBSERVATION, INFERENCE
    }
}
