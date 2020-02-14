package ru.kiril.delete;

import javafx.scene.control.CheckBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CmdHandler {
    public static final Logger logger = LogManager.getLogger();

    public void cmdRun(String ip, String disk, String fileIP) throws Exception {


        Runtime rt = Runtime.getRuntime();
        String command;

        command = "cmd.exe /c rd /s /q \"" + "\\\\" + ip + "\\" + disk + "$\\" + fileIP + "\"  ";
        logger.info("launch:" + command);

        Process proc = rt.exec(command);
        logger.info("stop ip:" + ip);
    }


}
