package view;

import controller.ProcessingController;
import model.enums.ProcessingEnum;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProcessingView implements AppMenu{
    private final ProcessingController controller = new ProcessingController();
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();

        ProcessingEnum matchedCommand = null;

        for (ProcessingEnum command : ProcessingEnum.values()) {
            Matcher matcher = command.getMatcher(input);
            if (matcher != null && matcher.matches()) {
                matchedCommand = command;
                break;
            }
        }

        if (matchedCommand == null) {
            System.out.println("invalid command");
        }else {
            switch (matchedCommand){

            }
        }

    }

}
