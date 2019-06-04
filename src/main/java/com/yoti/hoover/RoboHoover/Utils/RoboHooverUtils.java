package com.yoti.hoover.RoboHoover.Utils;

import com.yoti.hoover.RoboHoover.Exceptions.RoboHooverException;
import com.yoti.hoover.RoboHoover.model.Room;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverRequest;
import com.yoti.hoover.RoboHoover.rest.dto.RoboHooverInputs;
import com.yoti.hoover.RoboHoover.service.RoboHooverComandExecution;
import com.yoti.hoover.RoboHoover.service.impl.RoboHooverCommandExecutionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * *Author  : Nagaraju Ukkalam
 * *Date    : 5/20/2019
 **/
public class RoboHooverUtils {

    private final Logger logger = LoggerFactory.getLogger(RoboHooverUtils.class);

    /****
     *
     * @param roboHooverReq
     * @return RoboHooverInputs
     * @throws RoboHooverException
     *
     * Validating roboHooverRequest details like Coordinates, dirtPatches, hooverInitialPosition and hooverDirections
     */
    public RoboHooverInputs validateInputs(RoboHooverRequest roboHooverReq) throws RoboHooverException {
        final int[] roomSize = roboHooverReq.getRoomSize();
        validateRoomSizeInput(roomSize);
        final int[] coords = roboHooverReq.getCoords();
        validateInitialPositionInput(coords, roomSize);
        final int[][] patches = roboHooverReq.getPatches();
        final Set<Point> patchesInput = validateAndConvertPatchesInput(patches, roomSize);
        final List<Character> commands = validateInstructionsInput(roboHooverReq.getInstructions());

        return new RoboHooverInputs(
                new Point(roomSize[0], roomSize[1]),
                new Point(coords[0], coords[1]),
                patchesInput,
                commands);
    }

    /***
     *
     * @param roomSize
     * @throws RoboHooverException
     *
     * Validating room size format weather it is in format like (x,y) and proper inputs
     */

    protected void validateRoomSizeInput(int[] roomSize) throws RoboHooverException {
        logger.debug("Validating input RoomSize: ", roomSize);
        if(null == roomSize || roomSize.length != 2 || roomSize[0] != roomSize[1] || roomSize[0] < 1 || roomSize[1] < 1) {
            throw new RoboHooverException(RoboHooverMessageConstants.EROOR_MESSAGE_ROOMSIZE);
        }
        logger.debug("RoomSize input validation passed!");
    }

    /****
     *
     * @param coords
     * @param roomSize
     * @throws RoboHooverException
     */

    protected void validateInitialPositionInput(int[] coords, int[] roomSize) throws RoboHooverException {
        logger.debug("Validating input Coordinates provided by user: ", coords);
        if(null == coords || coords.length != 2 || coords[0] > roomSize[0] || coords[1] > roomSize[1] || coords[0] < 0 || coords[1] < 0) {
            throw new RoboHooverException(RoboHooverMessageConstants.EROOR_MESSAGE_INITIAL_POSITION);
        }
        logger.debug("Coordinates input validation passed....");
    }

    /***
     *
     * @param patches
     * @param roomSize
     * @return
     * @throws RoboHooverException
     */
    protected Set<Point> validateAndConvertPatchesInput(int[][] patches, int[] roomSize) throws RoboHooverException {
        logger.debug("Validating input Patches: ", patches);
        if(null != patches) {
            Set<Point> patchesSet = new HashSet<>();
            for(int i=0; i<patches.length; i++) {
                if(patches[i].length==2) {
                    int currentX = patches[i][0];
                    int currentY = patches[i][1];
                    if(currentX<roomSize[0] && currentY<roomSize[1]) {
                        patchesSet.add(new Point(currentX, currentY));
                    }
                }
            }
            logger.debug("RoomSize input validation passed!");
            return patchesSet;
        }
        throw new RoboHooverException(RoboHooverMessageConstants.EROOR_MESSAGE_PATCHES);
    }

    /***
     *
     * @param instructions
     * @return
     * @throws RoboHooverException
     */
    protected List<Character> validateInstructionsInput(String instructions) throws RoboHooverException {
        logger.debug("Validating input Instructions: ", instructions);
        if(null != instructions) {
            LinkedList<Character> list;
            instructions = instructions.replaceAll("\\s+","").toUpperCase();
            Pattern pt = Pattern.compile("[^NESW]+[^URDL]+");
            Matcher match = pt.matcher(instructions);
            if(!match.matches()) {
                list = new LinkedList<>();
                final char[] chars = instructions.toCharArray();
                for(int i=0; i<chars.length;i++) {
                    list.add(chars[i]);
                }
            } else {
                throw new RoboHooverException(RoboHooverMessageConstants.EROOR_MESSAGE_INSTRUCTIONS);
            }
            logger.debug("RoomSize input validation passed!");
            return list;
        } else {
            throw new RoboHooverException(RoboHooverMessageConstants.EROOR_MESSAGE_INSTRUCTIONS);
        }
    }

    /****
     *
     * @param roboHooverInputs
     * @return RoboHooverCommandExecution
     * @throws RoboHooverException
     */
    public RoboHooverComandExecution validateInstructionsAndGetRoboHooverCommandExecution(RoboHooverInputs roboHooverInputs) throws RoboHooverException {
        long count = 0;
        RoboHooverComandExecution roboHooverComandExecution;
        List<Character> hooverDirectionCommands = roboHooverInputs.getHooverDirectionCommands();
        if(null != hooverDirectionCommands) {
            count = hooverDirectionCommands.stream().map(Character::toUpperCase).filter(hd -> !(hd.equals(new Character('N')) || hd.equals(new Character('E'))
             || hd.equals(new Character('S')) || hd.equals(new Character('W')) || hd.equals(new Character('U')) ||
                    hd.equals(new Character('R'))|| hd.equals(new Character('D')) || hd.equals(new Character('L')))).count();
        }
        if (count > 0){
            throw new RoboHooverException(RoboHooverMessageConstants.EROOR_MESSAGE_INSTRUCTIONS+":: Requested commands:"+hooverDirectionCommands);
        }else {
            long directions =hooverDirectionCommands.stream().map(Character::toUpperCase).filter(hd -> (hd.equals(new Character('N')) || hd.equals(new Character('E'))
                    || hd.equals(new Character('S')) || hd.equals(new Character('E')))).count();
            if(directions > 0){
                return new RoboHooverCommandExecutionImpl(roboHooverInputs.getHooverInitialPosition(),new Room(roboHooverInputs.getRoomSize(), roboHooverInputs.getPatchesPosition()));
            }
        }
        return null;
    }
}
