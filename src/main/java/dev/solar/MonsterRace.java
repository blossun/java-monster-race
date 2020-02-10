package dev.solar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class MonsterRace {
    private Scanner scanner;
    private InputHandler inputHandler;
    private List<Monster> monsters;
    private int monsterCount;
    private int attemptCount;

    public MonsterRace() {
        scanner = new Scanner(System.in);
        inputHandler = new InputHandler(scanner);
        monsters = new ArrayList<>();
    }

    public static void main(String[] args) {
        MonsterRace monsterRace = new MonsterRace();
        monsterRace.play(monsterRace);
    }

    private void play(MonsterRace monsterRace) {
        monsterRace.inputInfo();
        monsterRace.addMonsters();
        monsterRace.move();
        monsterRace.printResult();
        monsterRace.printWinner();
        monsterRace.terminate();
    }

    private void inputInfo() {
        String promptMessage = "몬스터는 모두 몇 마리인가요?";
        monsterCount = inputHandler.inputProperType(promptMessage);
        promptMessage = "시도할 회수는 몇 회 인가요?";
        attemptCount = inputHandler.inputProperType(promptMessage);
    }

    private void addMonsters() {
        String promptMessage = "경주할 몬스터 이름과 종류를 입력하세요.(쉼표(,)를 기준으로 구분)";
        System.out.println(promptMessage);
        for (int i = 0; i < monsterCount; i++) {
            Monster monster = createMonster();
            monsters.add(monster);
        }
    }

    private Monster createMonster() {
        try {
            String[] monsterInfo = inputHandler.inputMonsterInfo();
            String inputMonsterName = monsterInfo[0];
            String inputMonsterType = monsterInfo[1];
            MonsterType monsterType = MonsterType.valueOfType(inputMonsterType);

            switch (monsterType.getMonsterTypeName()) {
                case "달리기": return new Run(inputMonsterName, inputMonsterType);
                case "비행": return new Fly(inputMonsterName, inputMonsterType);
                case "에스퍼": return new Espurr(inputMonsterName, inputMonsterType);
                default: throw new IllegalArgumentException("다시 입력해주세요.");
            }
        } catch (ArrayIndexOutOfBoundsException e) { //구분자 에러 처리
            System.out.println("형식에 맞게 입력해주세요. [이름, 타입]");
            return createMonster();
        } catch (IllegalArgumentException e) {
            System.out.println("몬스터 타입은 달리기, 비행, 에스퍼 중에서 골라주세요");
            return createMonster();
        }
    }

    private void move() {
        for (int i = 0; i < attemptCount; i++) {
            monsters.forEach(Monster::move);
        }
    }

    private void printResult() {
        Stream<Monster> monsterStream = monsters.stream();
        monsterStream.forEach(System.out::println);
    }

    private void printWinner() {
        Monster winner = pickWinner();

        System.out.printf("축하합니다! %s(이)가 몬스터 레이스에서 우승했습니다.", winner.getMonsterName());
    }

    private Monster pickWinner() {
        Comparator<Monster> comparator = Comparator.comparing(Monster::getForwardPosition);
        Monster winner = monsters.stream().max(comparator).get();

        return winner;
    }

    private void terminate() {
        scanner.close();
    }
}
