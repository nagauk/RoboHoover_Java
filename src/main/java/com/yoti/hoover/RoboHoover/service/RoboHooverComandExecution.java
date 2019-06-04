package com.yoti.hoover.RoboHoover.service;

import java.awt.*;
import java.util.List;

/**
 * *Author  : 30620
 * *Date    : 5/24/2019
 **/
public interface RoboHooverComandExecution {
    public void executeCommands(List<Character> commands);
    public int getDirtPartsRemovalCount();
    public Point hooverPosition();
}
