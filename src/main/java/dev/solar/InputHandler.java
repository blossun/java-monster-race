package dev.solar;

import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int inputProperType(String promptMessage) {
        try {
            System.out.println(promptMessage);
            int inputValue = Integer.parseInt(scanner.nextLine());

            if(inputValue < 0) {
                System.out.println("음수는 입력하실 수 없습니다. 다시 입력해주세요.");
                return inputProperType(promptMessage);
            }
            return inputValue;
        } catch (NumberFormatException e) {
            System.out.println("숫자 값만 입력하실 수 있습니다. 다시 입력해주세요.");
            return inputProperType(promptMessage);
        }
    }

    public String[] inputMonsterInfo() {
        try {
            String line = scanner.nextLine();
            String[] monsterInfo = line.replace(" ","").split(",");
            return monsterInfo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return inputMonsterInfo();
        }
    }
}
